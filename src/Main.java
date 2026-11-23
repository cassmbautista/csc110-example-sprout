import javax.swing.SwingUtilities;

// Entry point: opens Sprout's main window.
public class Main {
    public static void main(String[] args) {
        // BEYOND CSC110: Swing windows must be created on the "event thread"; invokeLater does that.
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
