package _20210325_Convert_To_DAO2.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import _20210325_Convert_To_DAO2.persistence.models.User;

public class UserDao implements Dao<User> {
    
    public UserDao() {

        DbController.connect();
        DbController.createUserTable();        
    }
    
    @Override
    public Optional<User> get(int id) {
        return Optional.of(DbController.selectOne(id));
    }
    
    @Override
    public List<User> getAll() {
        return DbController.selectAll();
    }
    
    @Override
    public void save(User user) {
        DbController.insertUser(user.getName());
    }
    
    @Override
    public void update(User user, String[] params) {

        user.setName(Objects.requireNonNull(
            params[0], "Name cannot be null"));
        
    }
    
    @Override
    public void delete(int id) {
        DbController.delete(id);
    }
}

class DbController {
    static Connection conn;

    public static void insertUser(String name){
        String sql = "INSERT INTO users(name) VALUES(?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL\n"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Table users is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connect() {
        String url = "jdbc:sqlite:./src/_20210325_Convert_To_DAO2/mydb.db";
        try {
            conn = DriverManager.getConnection(url);            
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            e.printStackTrace();
        }         
    }


    public static User selectOne(int id){
        String sql = "SELECT name FROM users";
        ArrayList<User> users = new ArrayList<User>();

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                User user = new User(rs.getString("name"));
                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users.get(id);
    }

    public static ArrayList<User>selectAll(){
        String sql = "SELECT name FROM users";
        ArrayList<User> users = new ArrayList<User>();

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                User user = new User(rs.getString("name"));
                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public static void delete(int id){
        String sql = "DELETE from users where id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
