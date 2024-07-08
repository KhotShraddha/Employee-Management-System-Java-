package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection connection;
    Statement statement;
    public Conn()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3308/employeemanagement","root","12345678");
            statement =connection.createStatement();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
