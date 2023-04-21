/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spravce;

import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;

/**
 *
 * @author 42070
 */
interface Ovladani {
    
    public void novy() throws KolekceException;
    public void najdi();
    public void odeber() throws KolekceException;
    public void dej();
    public void edituj();
    public void vyjmi() throws KolekceException;
    public void prvni() throws KolekceException;
    public void dalsi() throws KolekceException;
    public void posledni() throws KolekceException;
    public void pocet();
    public void obnov();
    public void zalohuj();
    public void vypis();
    public void nactitext();
    public void uloztext();
    public void generuj();
    public void zrus();
    
        
    
}
