/*
 * Copyright (C) 2019 Arvin, Arvin Rasheda
 *
 * This is for your eyes only!
 * Destroy after reading!
 */
package Controller;

import Model.Anggota;
import Model.Connector;
import View.frmAnggota;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class AnggotaController implements ActionListener{
    private Anggota data;
    private frmAnggota frm;
    private DefaultTableModel DftTableModel;
    
    public AnggotaController(Anggota data, frmAnggota frm)
    {
        this.data = data;
        this.frm = frm;
        this.frm.btnNew.addActionListener(this);
        this.frm.btnSave.addActionListener(this);
        this.frm.btnDelete.addActionListener(this);
        this.frm.btnUpdate.addActionListener(this);
        this.frm.txtKode.addActionListener(this);
    }
    
    public void load()
    {
        frm.setTitle("Master Anggota");
        frm.setLocationRelativeTo(null);
        frm.cmbJenisKelamin.addItem("Laki - Laki");
        frm.cmbJenisKelamin.addItem("Perempuan");
        frm.cmbJenisKelamin.addItem("Lainnya");
        loadTable();
    }
    
    private void loadTable(){
        Object[] Baris = {"Kode", "Nama", "Jenis Kelamin", "No Handphone"};
        DftTableModel = new DefaultTableModel(null, Baris);
        frm.Table.setModel(DftTableModel); //Table itu dari nama table di design 
        java.sql.Connection connection = new Connector().setConnection();
        
        try {
            String sql = "SELECT * FROM anggota";
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rslt = stmt.executeQuery(sql);// hasil statement query
            while (rslt.next()) {
                String kode = rslt.getString("kode");
                String nama = rslt.getString("nama");
                String jenis_kelamin = rslt.getString("jenis_kelamin");
                String no_hp = rslt.getString("no_hp");
                String[] dataField={kode, nama, jenis_kelamin, no_hp};
                DftTableModel.addRow(dataField);
            }
        } catch (Exception e) {
            
        }
    }
    
    public void clearText()
    {
        frm.txtKode.setText(null);
        frm.txtNama.setText(null);
        frm.txtHandphone.setText(null);
    }
    
    public void disableText()
    {
        frm.txtNama.setEnabled(false);
        frm.txtHandphone.setEnabled(false);
        frm.cmbJenisKelamin.setEnabled(false);
    }
    
    public void enableText()
    {
        frm.txtNama.setEnabled(true);
        frm.txtHandphone.setEnabled(true);
        frm.cmbJenisKelamin.setEnabled(true);
    }
    
    public void disableButton()
    {
        frm.btnDelete.setEnabled(false);
        frm.btnNew.setEnabled(false);
        frm.btnSave.setEnabled(false);
        frm.btnUpdate.setEnabled(false);
    }
    
    public void enableButton()
    {
        frm.btnDelete.setEnabled(true);
        frm.btnNew.setEnabled(true);
        frm.btnSave.setEnabled(true);
        frm.btnUpdate.setEnabled(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frm.btnNew) {
            
            enableText();
            disableButton();
            clearText();
            
            frm.btnSave.setEnabled(true);
        } else if(ae.getSource() == frm.btnSave){
            
            data.setKode(frm.txtKode.getText());
            data.setNama(frm.txtNama.getText());
            data.setJenis_kelamin(frm.cmbJenisKelamin.getSelectedItem().toString());
            data.setNo_hp(frm.txtHandphone.getText());
            
            if (data.createAnggota(data)) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan! ");
                disableText();
                disableButton();
                loadTable();
                frm.btnNew.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan data!");
            }
        } else if (frm.txtKode.equals(ae.getSource())) {
            
            data.setKode(frm.txtKode.getText());
            
            if(data.findAnggota(data)){
                frm.txtKode.setText(data.getKode());
                frm.txtNama.setText(data.getNama());
                frm.cmbJenisKelamin.setSelectedItem(data.getJenis_kelamin());
                frm.txtHandphone.setText(data.getNo_hp());
                enableText();
                disableButton();

                frm.btnUpdate.setEnabled(true);
                frm.btnDelete.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Gagal mengambil data.");
                clearText();
                disableButton();
                disableText();
                frm.btnNew.setEnabled(true);
            }
        } else if (ae.getSource() == frm.btnUpdate) {
            
            data.setKode(frm.txtKode.getText());
            data.setNama(frm.txtNama.getText());
            data.setJenis_kelamin(frm.cmbJenisKelamin.getSelectedItem().toString());
            data.setNo_hp(frm.txtHandphone.getText());
            
            if (data.updateAnggota(data)) {
                JOptionPane.showMessageDialog(null, "Data Berhasil di Update! ");
                disableText();
                disableButton();
                loadTable();
                frm.btnNew.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal mengupdate data!");
            }
        } else if (ae.getSource() == frm.btnDelete) {
            data.setKode(frm.txtKode.getText());
            if (data.deleteAnggota(data)) {
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus! ");
                disableText();
                disableButton();
                clearText();
                loadTable();

                frm.btnNew.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus data!");
            }
        }
    }
}
