import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RandProductMaker{
    public static JPanel getFramePanel(){
        return createMainPanel();
    }
    private static JPanel createMainPanel() {
        ArrayList<Product> products = new ArrayList<Product>();

        JPanel mainPanel = new JPanel(new BorderLayout());

        //top panel
        JLabel titleLabel = new JLabel("Rand Product", SwingConstants.CENTER);

        //center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);






        //bottom panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(e -> {
            // Get the parent frame from the current panel so the dialog is centered relative to MainFrame
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(mainPanel);

            // Create and display the custom ProductMakerDialog
            ProductMakerDialog dialog = new ProductMakerDialog(parentFrame);
            dialog.setVisible(true);

            // Retrieve the new product (if one was created)
            Product newProduct = dialog.getProduct();

            if (newProduct != null) {
                products.add(newProduct);
                // Optionally update your text area (or other UI component) with the new product's information
                textArea.setText("");
                for (Product p : products) {
                    textArea.append(p.toString() + "\n");
                }
            } else {
                // Optionally alert that the operation was cancelled
                JOptionPane.showMessageDialog(parentFrame,
                        "Product creation was cancelled.",
                        "Cancelled",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton removeProductButton = new JButton("Remove Product");
        removeProductButton.addActionListener(e -> {
            // Get the parent frame so any messages are centered correctly
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(mainPanel);

            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame,
                        "No products to remove.",
                        "Remove Product",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Remove the last product from the ArrayList
                products.remove(products.size() - 1);

                // Clear the TextArea
                textArea.setText("");

                // Loop through the updated products list and print each product's toString() output
                for (Product p : products) {
                    textArea.append(p.toString() + "\n");
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
        bottomPanel.add(addProductButton);
        bottomPanel.add(removeProductButton);
        bottomPanel.add(saveFileButton);


        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);



        return mainPanel;
    }


}
