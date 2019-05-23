/*
 * Copyright (C) 2019 Arvin, Arvin Rasheda
 *
 * This is for your eyes only!
 * Destroy after reading!
 */
package anggota;

import Controller.AnggotaController;
import Model.Anggota;
import View.frmAnggota;

/**
 *
 * @author User
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Anggota data = new Anggota();
        frmAnggota frm = new frmAnggota();
        AnggotaController  ctrl = new AnggotaController(data, frm);
        ctrl.load();
        frm.setVisible(true);
    }
    
}
