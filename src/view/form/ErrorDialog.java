package view.form;

import javax.swing.*;

public class ErrorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel labelErrorMessage;

    public ErrorDialog(String message) {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setSize(400, 100);
        setTitle("Message");

        labelErrorMessage.setText(message);
        buttonOK.addActionListener(e -> dispose());

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

}
