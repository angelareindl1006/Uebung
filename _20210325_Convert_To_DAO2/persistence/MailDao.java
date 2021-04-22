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

import _20210325_Convert_To_DAO2.persistence.models.Mail;

public class MailDao implements Dao<Mail> {
    
    public MailDao() {
        DbController2.connect();
        DbController2.createMailTable();   
    }
    
    @Override
    public Optional<Mail> get(int id) {
        
        return Optional.of(DbController2.selectOne(id));
    }
    
    @Override
    public List<Mail> getAll() {
        
        return DbController2.selectAll();
    }
    
    @Override
    public void save(Mail mail) {
        DbController2.insertMail(mail.getAddress(), mail.getId());
    }
    
    @Override
    public void update(Mail mail, String[] params) {

        // mail.setAddress(Objects.requireNonNull(
        //   params[0], "Address cannot be null"));
        // mail.setId(Objects.requireNonNull(
        //   params[1], "Id cannot be null"));
        
    }
    
    @Override
    public void delete(int id) {
        DbController2.delete(id);
    }
}

class DbController2 {
    static Connection conn;

    public static void insertMail(String address, int id){
        String sql = "INSERT INTO mails(address,id) VALUES(?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, address);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createMailTable() {
        String sql = "CREATE TABLE IF NOT EXISTS mails (\n"
                // + "	id integer PRIMARY KEY,\n"
                + "	address text NOT NULL,\n"
                + "	id integer\n"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Table mails is created!");
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


    public static Mail selectOne(int id1){
        String sql = "SELECT address, id FROM mails";
        ArrayList<Mail> mails = new ArrayList<Mail>();

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            
            while (rs.next()) {
                Mail mail = new Mail(rs.getString("address"), rs.getInt("id"));
                mails.add(mail);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return mails.get(id1);
    }

    public static ArrayList<Mail>selectAll(){
        String sql = "SELECT address, id FROM mails";
        ArrayList<Mail> mails = new ArrayList<Mail>();

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            
            while (rs.next()) {
                Mail mail = new Mail(rs.getString("address"), rs.getInt("id"));
                mails.add(mail);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return mails;
    }

    public static void delete(int id){
        String sql = "DELETE from Mails where id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
