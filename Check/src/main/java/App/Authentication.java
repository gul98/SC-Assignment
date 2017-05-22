package App;

import javax.swing.*;
import java.awt.event.*;

public class Authentication extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonRegister;
    private JTextField id;
    private JPasswordField password;

    public Authentication() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });



        buttonRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onRegister() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.print(e);

                if(e.getID()==WindowEvent.WINDOW_CLOSING){
                    dispose();
                }
            }
        });

        // call onRegister() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        App app= new App();

//        App app= new App();
//
        String eId=id.getText().toString();
        String ePassword=password.getText().toString();
        System.out.println("id: " +eId+" - password:"+ePassword);
        if(app.authenticate(eId,ePassword)){

            //get type of user and open the corresponding panel
            int type= app.getType(eId);
            if(type==0){
                AdminPanel adminPanel= new AdminPanel();
                adminPanel.pack();
                dispose();
                adminPanel.setVisible(true);

            }
            else if(type == 1){}
            else if(type == 2) {}


        }


    }
    public void onCancel(){
        dispose();
    }


    public static void main(String[] args) {
        Authentication dialog = new Authentication();
        dialog.pack();
        dialog.setVisible(true);

    }
}
