package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewEmployee extends JFrame implements ActionListener {
    JTable table;
    Choice choiceEMP;
    JButton search1,print,update,back;
    ViewEmployee()
    {
        getContentPane().setBackground(new Color(255,131,122));
        JLabel search=new JLabel("Search by employee ID");
        search.setBounds(20,20,150,20);
        add(search);

        choiceEMP =new Choice();
        choiceEMP.setBounds(180,20,150,20);
        add(choiceEMP);

        try{
            Conn c=new Conn();
            ResultSet resultSet=c.statement.executeQuery("select * from employee");
            while (resultSet.next())
            {
                choiceEMP.add(resultSet.getString("empID"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        table=new JTable();
        try{
            Conn c=new Conn();
            ResultSet resultSet=c.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        JScrollPane jp=new JScrollPane(table);
        jp.setBounds(0,100,900,600);
        add(jp);

        search1=new JButton("Search");
        search1.setBounds(20,70,80,20);
        search1.addActionListener(this);
        add(search1);

        print=new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        update=new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);

        back=new JButton("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(this);
        add(back);


        setSize(900,700);
        setLocation(300,100);
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search1){
            String query="select * from employee where empID ='"+choiceEMP.getSelectedItem()+"'";

            try{
                Conn c=new Conn();
                ResultSet resultSet=c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));


            }catch (Exception e1)
            {
                e1.printStackTrace();
            }
        } else if (e.getSource()==print) {
            try {
                table.print();

            }catch (Exception e2)
            {
                e2.printStackTrace();
            }

        }else if (e.getSource()==update){
            setVisible(false);
            new UpdateEmployee(choiceEMP.getSelectedItem());



        }
        else {
            setVisible(false);
            new main_class();
        }

    }
    public static void main(String[] args) {
        new ViewEmployee();
    }


}
