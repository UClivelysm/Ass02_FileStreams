import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    CardLayout cardLayout;
    JPanel northPanel;
    JPanel centerPanel;
    JPanel southPanel;

    JPanel cardOne;
    JPanel cardTwo;

    JButton cardOneButton;
    JButton cardTwoButton;
    JButton quitButton;

    JLabel mainLabel;

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
        mainLabel = new JLabel("Random Product Maker", SwingConstants.CENTER);
        northPanel.add(mainLabel);
        return northPanel;
    }

    private JPanel createCenterPanel() {
        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);
        cardOne = RandProductMaker.getFramePanel();
        cardTwo = RandProductSearch.getFramePanel();
        centerPanel.add(cardOne, "view1");
        centerPanel.add(cardTwo, "view2");

        cardLayout.show(centerPanel, "view1");
        // This is intentionally left empty for now
        return centerPanel;
    }

    private JPanel createSouthPanel() {
        southPanel = new JPanel();

        cardOneButton = new JButton("Switch to Card One");
        cardTwoButton = new JButton("Switch to Card Two");
        quitButton = new JButton("Quit");

        // Add lambda-based action listeners
        cardOneButton.addActionListener(e -> {
            cardLayout.show(centerPanel, "view1");
            mainLabel.setText("Random Product Maker");
            System.out.println("Clicked card one");
        });
        cardTwoButton.addActionListener(e -> {
            cardLayout.show(centerPanel, "view2");
            mainLabel.setText("Random Product Searcher");
            System.out.println("Clicked card two");
        });
        quitButton.addActionListener(e -> System.exit(0));

        southPanel.add(cardOneButton);
        southPanel.add(cardTwoButton);
        southPanel.add(quitButton);

        return southPanel;
    }
}
