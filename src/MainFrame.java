import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

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
    ArrayList<Product> products = new ArrayList<Product>();

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
        cardOne = createMakerPanel();
        cardTwo = productSearchPanel();
        centerPanel.add(cardOne, "view1");
        centerPanel.add(cardTwo, "view2");

        cardLayout.show(centerPanel, "view1");
        // This is intentionally left empty for now
        return centerPanel;
    }

    private JPanel createSouthPanel() {
        southPanel = new JPanel();

        cardOneButton = new JButton("Switch to Product Maker");
        cardTwoButton = new JButton("Switch to Product Searcher");
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
    int entryCount = 0;

    private JPanel createMakerPanel() {

        ArrayList<Product> products = new ArrayList<Product>();

        JPanel makerMainPanel = new JPanel(new BorderLayout());

        //top panel
        JPanel makerTopPanel = new JPanel(new GridLayout(2, 1));
        JLabel makerTitleLabel = new JLabel("Product Maker", SwingConstants.CENTER);
        JLabel enteryCountLabel = new JLabel("Entries:", SwingConstants.CENTER);
        makerTopPanel.add(makerTitleLabel);
        makerTopPanel.add(enteryCountLabel);



        //center panel
        JPanel makerCenterPanel = new JPanel(new BorderLayout());
        JTextArea makerTextArea = new JTextArea();
        makerTextArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        makerTextArea.setEditable(false);
        JScrollPane makerScrollPane = new JScrollPane(makerTextArea);
        makerCenterPanel.add(makerScrollPane, BorderLayout.CENTER);






        //bottom panel
        JPanel makerBottomPanel = new JPanel(new GridLayout(1, 3));
        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(e -> {


            // Create and display the custom ProductMakerDialog
            ProductMakerDialog dialog = new ProductMakerDialog(MainFrame.this);
            dialog.setVisible(true);

            // Retrieve the new product (if one was created)
            Product newProduct = dialog.getProduct();

            if (newProduct != null) {
                products.add(newProduct);
                entryCount = products.size();
                enteryCountLabel.setText("Entries: " + entryCount);
                // Optionally update your text area (or other UI component) with the new product's information
                makerTextArea.setText("");
                for (Product p : products) {
                    makerTextArea.append(p.toFormattedString() + "\n\n");
                }
            } else {
                // Optionally alert that the operation was cancelled
                JOptionPane.showMessageDialog(MainFrame.this,
                        "Product creation was cancelled.",
                        "Cancelled",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton removeProductButton = new JButton("Remove Product");
        removeProductButton.addActionListener(e -> {
            // Get the parent frame so any messages are centered correctly
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(makerMainPanel);

            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame,
                        "No products to remove.",
                        "Remove Product",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Remove the last product from the ArrayList
                products.remove(products.size() - 1);
                entryCount = products.size();
                enteryCountLabel.setText("Entries: " + entryCount);


                // Clear the TextArea
                makerTextArea.setText("");

                // Loop through the updated products list and print each product's toString() output
                for (Product p : products) {
                    makerTextArea.append(p.toFormattedString() + "\n\n");
                }
            }
        });
        JButton saveFileButton = new JButton("Save to File");
        saveFileButton.addActionListener(e -> {
            // Check if there are any products to save.
            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No products to save.",
                        "Save File",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Build a string with each product's formatted output on a new line.
            StringBuilder sb = new StringBuilder();
            for (Product product : products) {
                sb.append(product.toString()).append("\n");
            }

            // Send the complete string to be saved using FilePicker's SmartFileWriter.
            FilePicker.SmartFileWriter(sb.toString());
        });


        //add all to panel.
        makerBottomPanel.add(addProductButton);
        makerBottomPanel.add(removeProductButton);
        makerBottomPanel.add(saveFileButton);


        makerMainPanel.add(makerTopPanel, BorderLayout.NORTH);
        makerMainPanel.add(makerCenterPanel, BorderLayout.CENTER);
        makerMainPanel.add(makerBottomPanel, BorderLayout.SOUTH);



        return makerMainPanel;
    }

    private JPanel productSearchPanel() {



        JPanel searchMainPanel = new JPanel(new BorderLayout());
        //top Panel
        JPanel searchTitlePanel = new JPanel(new BorderLayout());
        JLabel searchTitleLabel = new JLabel("Product Searcher", SwingConstants.CENTER);
        JLabel searchFileNameLabel = new JLabel("Input File:", SwingConstants.CENTER);
        searchTitlePanel.add(searchTitleLabel, BorderLayout.NORTH);
        searchTitlePanel.add(searchFileNameLabel, BorderLayout.SOUTH);


        //center panel
        JPanel SearchCenterPanel = new JPanel(new BorderLayout());
        JTextArea searchTextArea = new JTextArea();
        searchTextArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        searchTextArea.setEditable(false);
        JScrollPane searchScrollPane = new JScrollPane(searchTextArea);
        SearchCenterPanel.add(searchScrollPane, BorderLayout.CENTER);



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
                JOptionPane.showMessageDialog(null,
                        "No file selected.",
                        "Invalid File",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Retrieve and trim the search term from the text field.
            String searchTerm = searchSearchField.getText().trim();

            // Validate that the search term is not empty.
            if (searchTerm.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Search term cannot be empty.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate that the search term has a length of 35 characters or fewer.
            if (searchTerm.length() > 35) {
                JOptionPane.showMessageDialog(null,
                        "Search term must be 35 characters or fewer.",
                        "Invalid Search Term",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Retrieve the filtered string based on the search term.
            String filteredString = FilePicker.NoCaseSearchedStreamRead(inputFile, searchTerm);

            // Split the filteredString into lines.
            String[] lines = filteredString.split("\\r?\\n");

            // Create a temporary ArrayList<Product> to hold converted products.
            ArrayList<Product> tempProducts = new ArrayList<>();

            // Process each line: convert it into a Product using Product.toProduct().
            for (String line : lines) {
                // Skip empty lines (if any).
                if (line.trim().isEmpty()) continue;
                try {
                    Product prod = Product.toProduct(line);
                    tempProducts.add(prod);
                } catch (Exception ex) {
                    // Optionally log or notify conversion issues for the given line.
                    System.err.println("Error converting line to product: " + line);
                }
            }

            // Build a temporary string with each product's formatted output (one per line).
            StringBuilder sb = new StringBuilder();
            for (Product prod : tempProducts) {
                sb.append(prod.toFormattedString()).append("\n" + "\n");
            }

            // Set the text of the searchTextArea to the final formatted string.
            searchTextArea.setText(sb.toString());
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
