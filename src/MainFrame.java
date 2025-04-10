import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel northPanel;
    JPanel centerPanel;
    JPanel southPanel;

    JButton testButton;
    JButton quitButton;

    JLabel placeholderLabel;

    public MainFrame() {
        super("Lab 10: Sorted Array List");

        setLayout(new BorderLayout());

        // Add panels to their respective positions
        add(createNorthPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createSouthPanel(), BorderLayout.SOUTH);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
    }

    private JPanel createNorthPanel() {
        northPanel = new JPanel();
        placeholderLabel = new JLabel("Placeholder Content", SwingConstants.CENTER);
        northPanel.add(placeholderLabel);
        return northPanel;
    }

    private JPanel createCenterPanel() {
        centerPanel = new JPanel(new CardLayout());
        // This is intentionally left empty for now
        return centerPanel;
    }

    private JPanel createSouthPanel() {
        southPanel = new JPanel();

        testButton = new JButton("Test");
        quitButton = new JButton("Quit");

        // Add lambda-based action listeners
        testButton.addActionListener(e -> System.out.println("Test button clicked!"));
        quitButton.addActionListener(e -> System.exit(0));

        southPanel.add(testButton);
        southPanel.add(quitButton);

        return southPanel;
    }
}
