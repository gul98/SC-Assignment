package App;

import javax.swing.*;
import java.awt.event.*;

public class AdminPanel extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton addEmployeeButton;
    private JButton deleteEmployeeButton;
    private JButton addAdminButton;

    public AdminPanel() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAddEmloyee();
            }
        });
        deleteEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDeleteEmployee();
            }
        });
        addAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAddAdmin();
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

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    private void onDeleteEmployee(){
        DeleteEmployee dialog = new DeleteEmployee();
        dialog.pack();
        dialog.setVisible(true);

    }
    private void onAddEmloyee(){
        AddEmployeeForm dialog = new AddEmployeeForm();
        dialog.pack();
        dialog.setVisible(true);
    }
    private void onAddAdmin(){
        AddAdmin dialog = new AddAdmin();
        dialog.pack();
        dialog.setVisible(true);
    }
}
