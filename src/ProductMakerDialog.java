import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductMakerDialog extends JDialog {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField idField;
    private JTextField costField;

    private Product product = null;

    public ProductMakerDialog(Frame owner) {
        super(owner, "Add Product", true);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Input panel with labels and text fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Name (max 35 characters):"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Description (max 75 characters):"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("ID (numeric, max 6 digits):"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Cost (max 6 digits before decimal, 2 after):"));
        costField = new JTextField();
        inputPanel.add(costField);

        add(inputPanel, BorderLayout.CENTER);

        // Button panel for OK/Cancel
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> onOK());
        cancelButton.addActionListener(e -> onCancel());

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getOwner());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                product = null;
            }
        });
    }

    private void onOK() {
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        String id = idField.getText().trim();
        String costStr = costField.getText().trim();

        // Validate name (non-empty, max 35 characters)
        if (name.isEmpty() || name.length() > 35) {
            JOptionPane.showMessageDialog(this,
                    "Name must be between 1 and 35 characters.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate description (max 75 characters)
        if (description.length() > 75) {
            JOptionPane.showMessageDialog(this,
                    "Description must be at most 75 characters.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate ID (only numeric and max 6 digits)
        if (!id.matches("\\d{1,6}")) {
            JOptionPane.showMessageDialog(this,
                    "ID must be numeric and up to 6 digits.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate cost (matches pattern: up to 6 digits before decimal and up to 2 after)
        if (!costStr.matches("\\d{1,6}(\\.\\d{1,2})?")) {
            JOptionPane.showMessageDialog(this,
                    "Cost must be a number with at most 6 digits before the decimal and up to 2 digits after.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double cost;
        try {
            cost = Double.parseDouble(costStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Cost is not a valid number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If all validations pass, create the product.
        product = new Product(id, name, description, cost);
        dispose();
    }

    private void onCancel() {
        product = null;
        dispose();
    }

    public Product getProduct() {
        return product;
    }
}
