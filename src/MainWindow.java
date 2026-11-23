import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

// The main window. It shows the Garden and turns button clicks into Garden method calls.
// It never checks rules itself: it asks Garden, then shows the result and auto-saves.
public class MainWindow extends JFrame {
    public static final String SAVE_FILE = "data/garden.txt";
    public static final String SAMPLE_FILE = "data/sample-garden.txt";
    private static final String[] COLUMNS = {"Name", "Species", "Room", "Every", "Last watered", "Next", "Status"};

    private Garden garden;
    private ArrayList<Plant> shownPlants = new ArrayList<Plant>(); // the rows currently in the table
    private boolean refreshing = false; // true while we rebuild the dropdowns, so they don't trigger refresh again

    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel dayLabel = new JLabel();
    private JLabel summaryLabel = new JLabel();
    private JLabel statusLabel = new JLabel(" ");
    private JTextField searchField = new JTextField(10);
    private JComboBox<String> roomBox = new JComboBox<String>();
    private JComboBox<String> statusBox = new JComboBox<String>(
            new String[] {Garden.ALL, Plant.OVERDUE, Plant.DUE_TODAY, Plant.OK});

    public MainWindow() {
        super("Sprout - Plant Care Tracker");
        garden = loadGarden();
        setLayout(new BorderLayout());
        add(buildTopBar(), BorderLayout.NORTH);
        add(new JScrollPane(buildTable()), BorderLayout.CENTER);
        add(buildBottom(), BorderLayout.SOUTH);
        setSize(950, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        refresh();
    }

    // Loads the saved garden, or the sample garden the very first time.
    private Garden loadGarden() {
        if (GardenFileStore.exists(SAVE_FILE)) {
            return GardenFileStore.load(SAVE_FILE);
        }
        return GardenFileStore.load(SAMPLE_FILE);
    }

    // ---------- Building the screen ----------

    private JPanel buildTopBar() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(dayLabel);
        top.add(makeButton("Next Day", "nextDay"));
        top.add(new JLabel("   Search:"));
        top.add(searchField);
        top.add(new JLabel("Room:"));
        top.add(roomBox);
        top.add(new JLabel("Status:"));
        top.add(statusBox);

        // BEYOND CSC110: listeners run code when something happens (typing, picking from a dropdown).
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { refresh(); }
            public void removeUpdate(DocumentEvent e) { refresh(); }
            public void changedUpdate(DocumentEvent e) { refresh(); }
        });
        roomBox.addActionListener(e -> { if (!refreshing) { refresh(); } });
        statusBox.addActionListener(e -> { if (!refreshing) { refresh(); } });
        return top;
    }

    private JTable buildTable() {
        // BEYOND CSC110: a table model holds the rows; overriding isCellEditable makes the table read-only.
        tableModel = new DefaultTableModel(COLUMNS, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setDefaultRenderer(Object.class, new StatusColorRenderer());
        return table;
    }

    private JPanel buildBottom() {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(makeButton("Add", "add"));
        buttons.add(makeButton("Edit", "edit"));
        buttons.add(makeButton("Remove", "remove"));
        buttons.add(makeButton("Water Today", "water"));
        buttons.add(makeButton("Log Care...", "log"));
        buttons.add(makeButton("History", "history"));

        JPanel bottom = new JPanel(new GridLayout(3, 1));
        bottom.add(buttons);
        bottom.add(summaryLabel);
        bottom.add(statusLabel);
        return bottom;
    }

    // Makes a button that calls handle(action) when clicked.
    private JButton makeButton(String label, String action) {
        JButton button = new JButton(label);
        // BEYOND CSC110: a lambda (e -> ...) is a tiny method that runs when the button is clicked.
        button.addActionListener(e -> handle(action));
        return button;
    }

    // ---------- Button actions ----------

    private void handle(String action) {
        if (action.equals("nextDay")) {
            garden.advanceDay();
            finishChange(Garden.OK, "It's now Day " + garden.getToday() + ".");
        } else if (action.equals("add")) {
            addPlant();
        } else {
            Plant selected = getSelectedPlant();
            if (selected == null) {
                showStatus("Select a plant first.", true);
            } else if (action.equals("edit")) {
                editPlant(selected);
            } else if (action.equals("remove")) {
                removePlant(selected);
            } else if (action.equals("water")) {
                String result = garden.waterToday(selected.getName());
                finishChange(result, "Watered " + selected.getName() + " on Day " + garden.getToday() + ".");
            } else if (action.equals("log")) {
                logCare(selected);
            } else if (action.equals("history")) {
                showHistory(selected);
            }
        }
    }

    // Keeps showing the form until the input is valid or the user cancels.
    private void addPlant() {
        PlantForm form = new PlantForm(garden.getToday());
        while (form.show(this, "Add Plant")) {
            String result = garden.addPlant(form.getName(), form.getSpecies(), form.getRoom(),
                    form.getInterval(), form.getLastWatered());
            if (result.equals(Garden.OK)) {
                finishChange(result, "Added " + form.getName().trim() + ".");
                return;
            }
            form.showError(result);
        }
    }

    private void editPlant(Plant plant) {
        String oldName = plant.getName();
        PlantForm form = new PlantForm(plant);
        while (form.show(this, "Edit " + oldName)) {
            String result = garden.editPlant(oldName, form.getName(), form.getSpecies(), form.getRoom(),
                    form.getInterval(), form.getLastWatered());
            if (result.equals(Garden.OK)) {
                finishChange(result, "Updated " + form.getName().trim() + ".");
                return;
            }
            form.showError(result);
        }
    }

    private void removePlant(Plant plant) {
        String question = "Remove " + plant.getName() + " and its "
                + plant.getHistory().size() + " care events?";
        int choice = JOptionPane.showConfirmDialog(this, question, "Remove plant", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String result = garden.removePlant(plant.getName());
            finishChange(result, "Removed " + plant.getName() + ".");
        }
    }

    private void logCare(Plant plant) {
        JComboBox<String> typeBox = new JComboBox<String>(
                new String[] {CareEvent.WATER, CareEvent.FERTILIZE, CareEvent.REPOT});
        JTextField dayField = new JTextField("" + garden.getToday(), 5);
        JTextField noteField = new JTextField(15);
        JPanel panel = new JPanel(new GridLayout(3, 2, 6, 6));
        panel.add(new JLabel("Type:"));
        panel.add(typeBox);
        panel.add(new JLabel("Day:"));
        panel.add(dayField);
        panel.add(new JLabel("Note (optional):"));
        panel.add(noteField);

        int choice = JOptionPane.showConfirmDialog(this, panel, "Log Care: " + plant.getName(),
                JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION) {
            String type = (String) typeBox.getSelectedItem();
            String result = garden.logCare(plant.getName(), type, PlantForm.readNumber(dayField), noteField.getText());
            finishChange(result, "Logged " + type + " for " + plant.getName() + ".");
        }
    }

    private void showHistory(Plant plant) {
        String text = "";
        for (CareEvent event : plant.getHistory()) {
            text = text + event + "\n";
        }
        if (text.isEmpty()) {
            text = "No care logged yet.";
        }
        JOptionPane.showMessageDialog(this, text, "History: " + plant.getName(), JOptionPane.PLAIN_MESSAGE);
    }

    // After any change: show the result, and if it worked, save and refresh the screen.
    private void finishChange(String result, String successMessage) {
        if (result.equals(Garden.OK)) {
            GardenFileStore.save(garden, SAVE_FILE);
            refresh();
            showStatus(successMessage, false);
        } else {
            showStatus(result, true);
        }
    }

    // ---------- Updating the screen ----------

    // Redraws the day, the room dropdown, the table (with filters), and the summary.
    private void refresh() {
        refreshing = true;
        dayLabel.setText("Today: Day " + garden.getToday());
        updateRoomBox();
        refreshing = false;

        String room = (String) roomBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();
        shownPlants = garden.search(searchField.getText(), room, status);

        tableModel.setRowCount(0);
        for (Plant plant : shownPlants) {
            tableModel.addRow(new Object[] {
                plant.getName(), plant.getSpecies(), plant.getRoom(), plant.getWaterEveryDays(),
                "Day " + plant.getLastWateredDay(), plant.nextWaterDay(), plant.getStatus(garden.getToday())
            });
        }
        summaryLabel.setText(buildSummary());
    }

    // Rebuilds the Room dropdown from the garden's rooms, keeping the current choice if it still exists.
    private void updateRoomBox() {
        String chosen = (String) roomBox.getSelectedItem();
        roomBox.removeAllItems();
        roomBox.addItem(Garden.ALL);
        for (String room : garden.getRooms()) {
            roomBox.addItem(room);
        }
        if (chosen != null) {
            roomBox.setSelectedItem(chosen);
        }
    }

    private String buildSummary() {
        Plant worst = garden.mostOverduePlant();
        String worstText = "none";
        if (worst != null) {
            int days = worst.daysOverdue(garden.getToday());
            worstText = worst.getName() + " (" + days + " day";
            if (days > 1) {
                worstText = worstText + "s";
            }
            worstText = worstText + ")";
        }
        return " Plants: " + garden.getPlants().size()
                + "  |  Overdue: " + garden.countWithStatus(Plant.OVERDUE)
                + "  |  Due today: " + garden.countWithStatus(Plant.DUE_TODAY)
                + "  |  Waterings (7 days): " + garden.wateringsInLastWeek()
                + "  |  Most overdue: " + worstText;
    }

    private void showStatus(String message, boolean isError) {
        if (isError) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText(" ✖ " + message);
        } else {
            statusLabel.setForeground(new Color(0, 120, 0));
            statusLabel.setText(" ✔ " + message);
        }
    }

    private Plant getSelectedPlant() {
        int row = table.getSelectedRow();
        if (row < 0) {
            return null;
        }
        return shownPlants.get(row);
    }

    // BEYOND CSC110: a "renderer" decides how each table cell looks. This one colors rows by status.
    private class StatusColorRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                String status = shownPlants.get(row).getStatus(garden.getToday());
                if (status.equals(Plant.OVERDUE)) {
                    cell.setBackground(new Color(255, 214, 214));
                } else if (status.equals(Plant.DUE_TODAY)) {
                    cell.setBackground(new Color(255, 244, 196));
                } else {
                    cell.setBackground(Color.WHITE);
                }
            }
            return cell;
        }
    }
}
