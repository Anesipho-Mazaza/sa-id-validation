package sa_id_validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("South African ID Validator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Enter SA ID Number:");
        JTextField idField = new JTextField(15);
        JButton validateButton = new JButton("Validate");
        JLabel resultLabel = new JLabel(" ");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(validateButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(resultLabel, gbc);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                boolean valid = ValidateSaId.isIdNumberValid(id);
                if (valid) {
                    resultLabel.setText("✅ Valid South African ID number");
                    resultLabel.setForeground(new Color(0, 128, 0));
                } else {
                    resultLabel.setText("❌ Invalid South African ID number");
                    resultLabel.setForeground(Color.RED);
                }
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
