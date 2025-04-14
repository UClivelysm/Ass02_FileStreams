import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RandProductSearch {
    public static JPanel getFramePanel(){
        return productSearchPanel();
    }
    private static JPanel productSearchPanel() {
        File inputFile = null;
//        String filteredString = "";


        JPanel mainPanel = new JPanel(new BorderLayout());
        //top Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Rand Product Search", SwingConstants.CENTER);
        JLabel fileNameLabel = new JLabel("Input File:", SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(fileNameLabel, BorderLayout.SOUTH);


        //center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);



        //bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2));

        JPanel searchPanel = new JPanel(new GridLayout(1, 2));
        JLabel searchLabel = new JLabel("Search Term:");
        JTextField searchField = new JTextField(35);

        searchPanel.add(searchLabel);
        JButton SearchButton = new JButton("Search Product");
        SearchButton.addActionListener(e -> {
            if (inputFile == null) {
                JOptionPane.showMessageDialog(null, "No file selected.", "Invalid File", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Retrieve and trim the search term from the text field
            String searchTerm = searchField.getText().trim();

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
                textArea.setText(filteredString);
            } else {
                JOptionPane.showMessageDialog(null, "No file selected.", "Invalid File", JOptionPane.ERROR_MESSAGE);
            }


        });
        JButton addInputFileButton = new JButton("Add Input File");
        addInputFileButton.addActionListener(e -> {

        });

        bottomPanel.add(searchPanel);
        bottomPanel.add(SearchButton);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);


        return mainPanel;
    }
    public static JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        JButton addProductButton = new JButton("Search Product");
        addProductButton.addActionListener(e -> {
            System.out.println("Add Product");
        });


        bottomPanel.add(addProductButton);
        return bottomPanel;
    }
}
