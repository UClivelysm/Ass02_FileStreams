import javax.swing.*;
import java.awt.*;
import java.io.File;

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

    File inputFile;

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
        cardTwo = productSearchPanel();
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
    private JPanel productSearchPanel() {



        JPanel searchMainPanel = new JPanel(new BorderLayout());
        //top Panel
        JPanel searchTitlePanel = new JPanel(new BorderLayout());
        JLabel searchTitleLabel = new JLabel("Rand Product Search", SwingConstants.CENTER);
        JLabel searchFileNameLabel = new JLabel("Input File:", SwingConstants.CENTER);
        searchTitlePanel.add(searchTitleLabel, BorderLayout.NORTH);
        searchTitlePanel.add(searchFileNameLabel, BorderLayout.SOUTH);


        //center panel
        JPanel SearchCenterPanel = new JPanel(new BorderLayout());
        JTextArea searchTextArea = new JTextArea();
        searchTextArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        searchTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(searchTextArea);
        SearchCenterPanel.add(scrollPane, BorderLayout.CENTER);



        //bottom Panel
        JPanel searchBottomPanel = new JPanel(new GridLayout(2, 2));

        JPanel searchSearchPanel = new JPanel(new GridLayout(1, 2));
        JLabel searchSearchLabel = new JLabel("Search Term:");
        JTextField searchSearchField = new JTextField(35);

        searchSearchPanel.add(searchSearchLabel);
        searchSearchPanel.add(searchSearchField);

        JButton SearchButton = new JButton("Search Product");
        SearchButton.addActionListener(e -> {
            if (inputFile == null) {
                JOptionPane.showMessageDialog(null, "No file selected.", "Invalid File", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Retrieve and trim the search term from the text field
            String searchTerm = searchSearchField.getText().trim();

            // Validate that the search term is not empty
            if (searchTerm.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Search term cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate that the search term has a length of 35 characters or fewer
            if (searchTerm.length() > 35) {
                JOptionPane.showMessageDialog(null, "Search term must be 35 characters or fewer.", "Invalid Search Term", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call the method to process the search term (to be implemented)
            if (inputFile != null) {
                String filteredString = FilePicker.SearchedStreamRead(inputFile, searchTerm);
                searchTextArea.setText(filteredString);
            } else {
                JOptionPane.showMessageDialog(null, "No file selected.", "Invalid File", JOptionPane.ERROR_MESSAGE);
            }


        });
        JButton addInputFileButton = new JButton("Add Input File");
        addInputFileButton.addActionListener(e -> {
            System.out.println("Add Input File");
            inputFile = FilePicker.GetFile();
            if (inputFile != null) {
                System.out.println("Input File: " + inputFile.getAbsolutePath());
                searchFileNameLabel.setText("Input File: " + inputFile.getAbsolutePath());
            } else {
                System.out.println("Input File: No File Selected");
            }
        });
        JButton removeInputFileButton = new JButton("Remove Input File");
        removeInputFileButton.addActionListener(e -> {
            System.out.println("Remove Input File");
            inputFile = null;
            searchFileNameLabel.setText("Input File: No File Selected");
        });

        searchBottomPanel.add(searchSearchPanel);
        searchBottomPanel.add(SearchButton);
        searchBottomPanel.add(addInputFileButton);
        searchBottomPanel.add(removeInputFileButton);

        searchMainPanel.add(searchTitlePanel, BorderLayout.NORTH);
        searchMainPanel.add(SearchCenterPanel, BorderLayout.CENTER);
        searchMainPanel.add(searchBottomPanel, BorderLayout.SOUTH);


        return searchMainPanel;
    }
}
