package gui;

import ErrorHandler.ErrorObserver.ErrSubscriber;
import ErrorHandler.GenError;
import app.AppCore;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import resource.implementation.InformationResource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainFrame extends JFrame implements Subscriber, ErrSubscriber {

    private static MainFrame instance = null;

    private AppCore appCore;
    private JScrollPane jsp;
    private JPanel bottomStatus;
    private String string = "";
    private JButton button;
    private JPanel panel;
    private JTable jTable;
    private JTextArea jTextArea2;
    private JTextArea jTextArea;


    private MainFrame() {

    }

    public static MainFrame getInstance(){
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    private void initialise() {

        this.setLayout(null);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        setTitle("SQL QUERY BUILDER");
        jTable = new JTable();
        button=new JButton("Send");
        jTextArea=new JTextArea();
        JScrollPane jScrollPane3 = new JScrollPane(jTextArea);


        jTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        jTable.setFillsViewportHeight(true);


        jTextArea2 = new JTextArea();

        jScrollPane3.setBounds(0, 0,1000,50);
        button.setBounds(430,65, 100, 30 );


        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(0, 105, 980, 300);




       // jTextArea2.setBounds(0, 450, 980, 130);
        jTextArea2.setEditable(false);

        JScrollPane jScrollPane2 = new JScrollPane(jTextArea2);
        jScrollPane2.setBounds(0, 415, 980, 130);

        this.add(jScrollPane3);
        this.add(button);
        this.add(jScrollPane);
        this.add(jScrollPane2);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                string = "";
                jTextArea2.setText("");
                appCore.getTableModel().setRowCount(0);
                appCore.getTableModel().setColumnCount(0);
                MainFrame.getInstance().obradiUpit(jTextArea.getText());
            }
        });


        this.pack();
        this.setLocationRelativeTo(null);
        setSize(1000, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        this.jTable.setModel(appCore.getTableModel());
    }

    public void obradiUpit(String upit){
        String resenje = appCore.getQueryBuilder().queryEngine(upit);
        if(!resenje.equals("False")) {
            appCore.readDataFromTable(resenje);
        }

    }

    @Override
    public void update(Notification notification) {

        if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println((InformationResource)notification.getData());
        }

        else{
            jTable.setModel((TableModel) notification.getData());
        }

    }

    public AppCore getAppCore() {
        return appCore;
    }

    @Override
    public void updateErrorSubscribers(GenError error) {
        this.string += error.getMessage();
        this.string += "\n";


        jTextArea2.setText(string);


    }
}
