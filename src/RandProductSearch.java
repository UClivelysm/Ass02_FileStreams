import javax.swing.*;
import java.awt.*;

public class RandProductSearch {
    public static JPanel getFramePanel(){
        return createMainPanel();
    }
    private static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Rand Product Search", SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
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
