/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alberto
 */
public class BD {

    Connection conne = null;
    Statement st = null;

    public void conectar() {
        conne = null;
        st = null;
        String url = "jdbc:mysql://db4free.net:3306/cafedb";
        //String url = "jdbc:mysql://localhost:1527/cafedb";
        String us = "skaterolle";
        String pass = "melodi16";
        //String url = "jdbc:mysql://remotemysql.com:3306/eEUEVRqJcs";
        //String us = "eEUEVRqJcs";
        //String pass = "9ATd0pMnwA";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conne = (Connection) DriverManager.getConnection(url, us, pass);
            //st = (Statement) conn.createStatement();
            System.out.println("Conectado");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error de conexion");
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean desconectar() {
        boolean desc = false;
        try {
            conne.close();
            desc = true;
            System.out.println("Desconectado");
            return desc;
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desc;
    }

    public boolean consulta(String fro, String dri) throws SQLException {
        Statement stmt = conne.createStatement();
        ResultSet rs = null;
        //System.out.println("Realizo consulta");
        if("COLD".equals(fro)){
        rs = stmt.executeQuery("Select stock from COLD where drink='"+ dri +"';");
        }else{
        rs = stmt.executeQuery("Select stock from HOT where drink='"+ dri +"';");
        }
        rs.next();
        //System.out.println( "hola--"+rs.getInt(1));
        if (1 == rs.getInt(1)) {
            stmt.close();
            return true;
        }
        stmt.close();
        return false;
    }
}
