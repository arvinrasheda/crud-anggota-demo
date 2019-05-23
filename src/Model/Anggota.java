/*
 * Copyright (C) 2019 Arvin, Arvin Rasheda
 *
 * This is for your eyes only!
 * Destroy after reading!
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Anggota extends Connector{
    private String kode;
    private String nama;
    private String jenis_kelamin;
    private String no_hp;
    
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }
    
    public String getNo_hp(){
        return no_hp;
    }
    
    public void setNo_hp(String no_hp){
        this.no_hp = no_hp;
    }
    
    public boolean createAnggota(Anggota data)
    {
        PreparedStatement ps = null;
        Connection conn = setConnection();
        
        String sql = "INSERT INTO anggota (kode,nama,jenis_kelamin,no_hp) VALUES (?,?,?,?)";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, data.getKode());
            ps.setString(2, data.getNama());
            ps.setString(3, data.getJenis_kelamin());
            ps.setString(4, data.getNo_hp());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean findAnggota(Anggota data)
    {
        PreparedStatement ps = null;
        Connection conn = setConnection();
        ResultSet rs = null;
        
        String sql = "SELECT kode,nama,jenis_kelamin,no_hp FROM anggota where kode=?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, data.getKode());
            
            rs = ps.executeQuery();
            if(rs.next()){
                data.setKode(rs.getString("kode"));
                data.setNama(rs.getString("nama"));
                data.setJenis_kelamin(rs.getString("jenis_kelamin"));
                data.setNo_hp(rs.getString("no_hp"));
                
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean updateAnggota(Anggota data)
    {
        PreparedStatement ps = null;
        Connection conn = setConnection();
        
        String sql = "UPDATE anggota SET kode=?, nama=?, jenis_kelamin=?, no_hp=? WHERE kode=?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, data.getKode());
            ps.setString(2, data.getNama());
            ps.setString(3, data.getJenis_kelamin());
            ps.setString(4, data.getNo_hp());
            ps.setString(5, data.getKode());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean deleteAnggota(Anggota data)
    {
        PreparedStatement ps = null;
        Connection conn = setConnection();
        
        String sql = "DELETE FROM anggota WHERE kode=?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, data.getKode());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
