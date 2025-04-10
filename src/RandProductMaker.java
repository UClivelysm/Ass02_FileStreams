import javax.swing.*;
import java.awt.*;

public class RandProductMaker{
    public static JPanel getFramePanel(){
        return createMainPanel();
    }
    private static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Rand Product", SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);


        return mainPanel;
    }
    public static JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(e -> {
            System.out.println("Add Product");
        });
        JButton removeProductButton = new JButton("Remove Product");
        removeProductButton.addActionListener(e -> {
            System.out.println("Remove Product");
        });

        bottomPanel.add(addProductButton);
        bottomPanel.add(removeProductButton);
        return bottomPanel;
    }

}
