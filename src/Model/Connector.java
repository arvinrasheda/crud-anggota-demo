/*
 * Copyright (C) 2019 Arvin, Arvin Rasheda
 *
 * This is for your eyes only!
 * Destroy after reading!
 */
package Model;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Connector {
    Connection conn; //Object Penghubung utama
    Statement st; // Object Create statemen seperti yang di cmd 
    ResultSet rs; // Object men set hasil 

    public Connection setConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_anggota","root",""); //mengambil tempat koneksi urlserver+db, user, password
            st = conn.createStatement(); //eksekusi statement untuk pemanggilan databasi dari conn
        } catch (ClassNotFoundException | SQLException e) { //menangkap error 
            JOptionPane.showMessageDialog(null, "Koneksi Gagal : " + e); // memunculkan pesan yang ada di dialog
        }
        return conn; //mengembalikan hasil koneksi disimpan di class setKoneksi
    }
}
