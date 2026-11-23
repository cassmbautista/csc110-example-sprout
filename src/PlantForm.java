import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// The Add/Edit Plant form. It only collects what the user typed; Garden decides if it's valid.
public class PlantForm {
    private JTextField nameField = new JTextField(15);
    private JTextField speciesField = new JTextField(15);
    private JTextField roomField = new JTextField(15);
    private JTextField intervalField = new JTextField(5);
    private JTextField lastWateredField = new JTextField(5);
    private JLabel errorLabel = new JLabel(" ");

    // Starts empty (for Add), with "last watered" set to today.
    public PlantForm(int today) {
        lastWateredField.setText("" + today);
        errorLabel.setForeground(Color.RED);
    }

    // Starts filled in with an existing plant (for Edit).
    public PlantForm(Plant plant) {
        this(plant.getLastWateredDay());
        nameField.setText(plant.getName());
        speciesField.setText(plant.getSpecies());
        roomField.setText(plant.getRoom());
        intervalField.setText("" + plant.getWaterEveryDays());
    }

    // Shows the form. Returns true if the user clicked OK, false for Cancel.
    public boolean show(Component parent, String title) {
        // BEYOND CSC110: GridLayout(rows, columns) lines up the labels and text boxes in a grid.
        JPanel panel = new JPanel(new GridLayout(6, 2, 6, 6));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Species:"));
        panel.add(speciesField);
        panel.add(new JLabel("Room:"));
        panel.add(roomField);
        panel.add(new JLabel("Water every (days):"));
        panel.add(intervalField);
        panel.add(new JLabel("Last watered (day):"));
        panel.add(lastWateredField);
        panel.add(errorLabel);
        int choice = JOptionPane.showConfirmDialog(parent, panel, title, JOptionPane.OK_CANCEL_OPTION);
        return choice == JOptionPane.OK_OPTION;
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public String getName() {
        return nameField.getText();
    }

    public String getSpecies() {
        return speciesField.getText();
    }

    public String getRoom() {
        return roomField.getText();
    }

    public int getInterval() {
        return readNumber(intervalField);
    }

    public int getLastWatered() {
        return readNumber(lastWateredField);
    }

    // Turns the text into an int. Not-a-number becomes -1 so Garden's rules reject it with a clear message.
    public static int readNumber(JTextField field) {
        // BEYOND CSC110: Integer.parseInt throws an error on text like "abc"; try/catch handles it.
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
