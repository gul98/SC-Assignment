package App;

import javax.swing.*;
import java.awt.event.*;

public class DeleteEmployee extends JDialog {
    private JPanel contentPane;
    private JButton buttonDelete;
    private JButton buttonCancel;
    private JTextField id;

    public DeleteEmployee() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonDelete);

        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDelete();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onDelete() {
        // add your code here
        Manager manager = new Manager();
        manager.deleteEmployee(id.getText().toString());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
