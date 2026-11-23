import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

// Entry point for the app. Right now it just opens a window to prove your setup works.
// Claude will replace this with your real app during your first /build-feature.
public class Main {
    public static void main(String[] args) {
        // BEYOND CSC110: Swing windows must be created on the "event thread"; invokeLater does that.
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CSC110 Final Project");
            JLabel label = new JLabel("Setup works! Java + Swing are ready.", SwingConstants.CENTER);
            frame.add(label);
            frame.setSize(420, 160);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
