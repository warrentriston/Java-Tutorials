import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.sql.*;
import java.util.Scanner;

public class JDBC {
    public static void main(String[] args)
    {
        System.out.println("Hello");
        String url = "jdbc:mysql://localhost:3306/jdbctut";
        String username = "root";
        String password = "root123@";

        Scanner scanner = new Scanner((System.in));
        System.out.println("Select Option \n 1. Insert \n 2. Update \n 3. Delete \n 4. Retrieve");
        byte option = Byte.parseByte(scanner.next());
        switch(option) {
            case 1:
                insert(url, username, password);
                break;
            case 2:
                update(url, username, password);
                break;
            case 3:
                delete(url, username, password);
                break;
            case 4:
                retrieve(url, username, password);
                break;
            default :
                System.out.println("Invalid Selection");
                break;
        }
    }

    static void insert(String url, String username, String password)
    {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("SuccessFull");
            String insert = "INSERT into person(firstname, secondname) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(insert);
            Scanner scanner = new Scanner((System.in));
            System.out.println("Enter First Name");
            String firstname = scanner.next();
            System.out.println("Enter Second Name");
            String secondname = scanner.next();
            statement.setString(1,firstname);
            statement.setString(2, secondname);
            int execute = statement.executeUpdate();
            if(execute > 0)
            {
                System.out.println("Insertion successful");
            }
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("Insertion not successful");
        }
    }

    static void retrieve(String url, String username, String password)
    {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("SuccessFull");
            String fetch = "Select * from person";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(fetch);
            while(resultSet.next())
            {
                System.out.println("First Name :" + resultSet.getString("firstname"));
                System.out.println("Second Name :" + resultSet.getString("secondname"));
            }
            statement.close();
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("Retrieval Error");
        }
    }

    static void update(String url, String username, String password)
    {
        try {
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("SuccessFull");
        String insert = "UPDATE person SET firstname = ? Where id = ?";
        PreparedStatement statement = connection.prepareStatement(insert);
        Scanner scanner = new Scanner((System.in));
        System.out.println("Enter ID to be Updated");
        String id = scanner.next();
        System.out.println("Enter First Name to be Updated");
        String firstname = scanner.next();
        statement.setString(1,firstname);
        statement.setString(2,id);
        int execute = statement.executeUpdate();
        if(execute > 0)
        {
            System.out.println("Update successful");
        }
        connection.close();
    }
        catch(Exception e)
        {
            System.out.println("Update not successfull");
        }
    }

    static void delete(String url, String username, String password)
    {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("SuccessFull");
            String insert = "DELETE from person Where id = ?";
            PreparedStatement statement = connection.prepareStatement(insert);
            Scanner scanner = new Scanner((System.in));
            System.out.println("Enter ID to be Deletd");
            String id = scanner.next();
            statement.setString(1,id);
            int execute = statement.executeUpdate();
            if(execute > 0)
            {
                System.out.println("Update successful");
            }
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("Delete successfull");
        }
    }
}
