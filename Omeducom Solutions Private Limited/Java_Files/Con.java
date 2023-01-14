import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Con{
    Connection connection;
    Statement statement;
    Con(){
        final String DBURL = "jdbc:mysql://localhost:3306/userdb";
        final String USERNAME = "root";
        final String PASSWORD = "Apple@0827";
        final String driver = "com.mysql.cj.jdbc.Driver";
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            statement = (Statement) connection.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new Con();
    }
}