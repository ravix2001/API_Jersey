package org.example.api_jersey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

    Connection conn = null;

    public AlienRepository() {
        String url = "jdbc:mysql://localhost:3306/api_jersey_db";
        String user = "root";
        String password = "Ravi@2001";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Alien> getAliens() {
        List<Alien> aliens = new ArrayList<>();
        String sql = "select * from alien";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Alien alien = new Alien();
                alien.setId(rs.getInt("id"));
                alien.setName(rs.getString("name"));
                alien.setColor(rs.getString("color"));
                aliens.add(alien);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return aliens;
    }
    public Alien getAlienById(int id) {
        Alien alien = new Alien();
        String sql = "select * from alien where id = " + id;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                alien.setId(rs.getInt("id"));
                alien.setName(rs.getString("name"));
                alien.setColor(rs.getString("color"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return alien;
    }

    public void createAlien(Alien alien) {
        String sql = "insert into alien (id, name, color) values (?, ?, ?)";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, alien.getId());
            preparedStatement.setString(2, alien.getName());
            preparedStatement.setString(3, alien.getColor());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAlien(Alien alien) {
        String sql = "update alien set name = ?, color = ? where id = ? ";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, alien.getName());
            preparedStatement.setString(2, alien.getColor());
            preparedStatement.setInt(3, alien.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAlien(int id) {
        String sql = "delete from alien where id = ?";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
