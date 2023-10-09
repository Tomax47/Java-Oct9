import java.sql.*;
import java.util.Scanner;

public class Main {


    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1234";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/users";

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        Statement statement = connection.createStatement();

        String SELECT_USER_WHERE_NAME = "SELECT * FROM db_test_accounts WHERE name=";

        System.out.print("Enter the name : ");
        String userName = scan.nextLine();

        String sql_query = SELECT_USER_WHERE_NAME+"'"+userName+"'";
        ResultSet result = statement.executeQuery(sql_query);

        while (result.next()) {
            System.out.println("User id : "+result.getInt("id")+" | Full-name : "+result.getString("name")
                    +" "+result.getString("surname"));
        }

        System.out.println("\n-------------");
        System.out.print("Email : ");
        String email = scan.nextLine();
        System.out.print("Password : ");
        String passwd = scan.nextLine();
        System.out.print("Name : ");
        String name = scan.nextLine();
        System.out.print("Surname : ");
        String surname = scan.nextLine();

        try {
            String INSERT_USER = "INSERT INTO db_test_accounts (email, password, name, surname) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,passwd);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,surname);
            preparedStatement.executeUpdate();

            System.out.println("User has been added!");
        } catch (SQLException e) {
            throw new Exception(e);
        }


    }
}