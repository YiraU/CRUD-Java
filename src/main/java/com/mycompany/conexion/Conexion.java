

package com.mycompany.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/jdbtest";
        String usuario = "root";
        String password = "";
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;
        
        
         try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            rs = statement.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
            
            //Insercion de datos
            statement.execute("INSERT INTO `usuarios` (`id`, `nombre`) VALUES (NULL, 'maria');");
            System.out.println("");
            rs = statement.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
            
            //Actualizacion de datos
            statement.execute("UPDATE `usuarios` SET `nombre` = 'pablo' WHERE `usuarios`.`id` = 5;");
            System.out.println("");
            rs = statement.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
            
            //Borrado de datos
            statement.execute("DELETE FROM usuarios WHERE `usuarios`.`id` = 2;");
            System.out.println("");
            rs = statement.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar los recursos en un bloque finally
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

        

