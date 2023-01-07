package subClasses;
import Main.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;
/**
 *@Description: to show GUI
 *@name GUI class
 *@Author: echo
 *@Date: 21:08 2021/1/4
**/
public class GUI extends JFrame{
    private JTextField textField1;
    public GUI() {
        super("Testing JTextField and JPasswordField");
        setLayout(new FlowLayout());
        //J components
        JLabel label1=new JLabel("input file/dir");
        add(label1);
        textField1=new JTextField(20);
        add(textField1);
        JButton button1= new JButton("submit");
        JButton button2= new JButton("save");
        JButton button3= new JButton("clear");
        JButton button4= new JButton("exit");
        add(button1);
        add(button2);
        add(button3);
        add(button4);

        textField1.addActionListener(new SubmitButton());
        button1.addActionListener(new SubmitButton());
        button2.addActionListener(new SaveButton());
        button3.addActionListener(new ClearButton());
        button4.addActionListener(new ExitButton());

    }
    /**
     *@Description: four functions to response the action
     *@name xxxButton
     *@Author: echo
     *@Date: 21:08 2021/1/4
    **/
    private class SubmitButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        File filePath =new File(textField1.getText());
        if(filePath.exists()){
            try {
                JFrame jf=new JFrame("show file");
                jf.setBounds(600,300,300,150);
                TextArea content = new TextArea("", 5, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
//                JTextField jTextField = new JTextField();
//                jTextField.setPreferredSize(new Dimension(400, 500));
//                jTextField.setText(mainClass.listFir(filePath));
//                jTextField.setEditable(false);
                content.setText(mainClass.listFir(filePath));
                System.out.println("-------------------");
                System.out.println(mainClass.listFir(filePath));
                jf.add(content);
                jf.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //no this filePath --> input again or exit the programme
            Object[]options={"input again","exit"};
            int m= JOptionPane.showOptionDialog(null,"wrong path","Error",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
            if(m==1){
                System.exit(10);
            }
        }
        }
    }
    private class SaveButton implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            System.out.println("23333333333");
            File filePath=new File(textField1.getText());
            try {
                if (filePath.exists()) {
                    mainClass.saveFile(filePath);
                    Object[]options={"OK","exit"};
                    int m= JOptionPane.showOptionDialog(null,"save successfully","Save",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
                     if(m==1){
                        System.exit(10);
                    }
                }else {
                    Object[]options={"OK","exit"};
                    int m= JOptionPane.showOptionDialog(null,"failed to save","Error",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
                    if(m==1){
                        System.exit(10);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class ClearButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            textField1.setText("");
        }
    }
    public class ExitButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(10);
        }
    }

}