/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spravce;

import cz.upce.fei.boop.pujcovna.command.CommandMain;
import cz.upce.fei.boop.pujcovna.data.Pistol;
import cz.upce.fei.boop.pujcovna.data.Rifle;
import cz.upce.fei.boop.pujcovna.data.Shotgun;
import cz.upce.fei.boop.pujcovna.data.SniperRifle;
import cz.upce.fei.boop.pujcovna.data.Weapons;
import cz.upce.fei.boop.pujcovna.generator.Generator;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.LinkSeznam;
import cz.upce.fei.boop.pujcovna.perzistence.ZapisCteni;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 42070
 */
public class SpravaProstredku implements Ovladani{
    
    private static boolean stop = true;
    private static LinkSeznam<Weapons> seznam = new LinkSeznam();;
    Scanner scaner = new Scanner(System.in);
    
    /*
    * Metoda vytvoř novou instanci a vlož data za aktuální prvek.
    * Metoda zkontroluje, zda je nastaven aktualni prvek,
    * pokud ne, napíše o tom.
    * Pokud ano,uživatel zadavá různé vlastnosti,
    * a poté metoda vytvoří nový objekt weapon. 
    * Pokud Aktualni nastaven metoda vloží nový weapon do seznamu
    */
    @Override
    public void novy() throws KolekceException{
        if(seznam.isAktualniNull()){
            System.out.println("Neni nastaven aktualni prvek");
        }else {
            Weapons weapon = null;
            System.out.println("Zadej typ weapon: [p] - Pistol, [r] - Rifle, [s] - Shotgun, [sr] - Sniper rifle");
            var typ = scaner.nextLine();
            typ = typ.trim();
            System.out.println("Zadej nazev");
            var name = scaner.nextLine();
            name = name.trim();
            System.out.println("Zadej zemi vlastníka");
            var country = scaner.nextLine();
            country = country.trim();
            System.out.println("Zadej počet nábojů");
            int pocetN = scaner.nextInt();
            System.out.println("Zadej vahu");
            int vaha = scaner.nextInt();
            switch(typ){
                case "p" -> weapon = new Pistol(name, Weapons.cislo_id, country, pocetN, vaha);
                case "r" -> weapon = new Rifle(name, Weapons.cislo_id, country, pocetN, vaha);
                case "s" -> weapon = new Shotgun(name, Weapons.cislo_id, country, pocetN, vaha);
                case "sr" -> weapon = new SniperRifle(name, Weapons.cislo_id, country, pocetN, vaha);
                }
            if (!seznam.isAktualniNull()) {
                seznam.vlozZaAktualni(weapon);
            } else {
                System.out.println("Neni nastaven aktualni prvek. Nastavte a zkuste znovu");
            }
        }
    }
    
    /*
    * Metoda najdi v seznamu data podle hodnoty nějakém atributu
    * Uživatel musí zadat ID nebo jmeno prvku, metoda hleda ze seznamu požadovaný prvek s atributem které zadal uživatel
    */
    @Override
    public void najdi() {
    System.out.println("Zadej jmeno zbraně nebo jeji ID");
    var atribut = scaner.nextLine();
    atribut = atribut.trim();
    try{
        for (Weapons weapon : seznam) {
            if (weapon.getID() == Integer.parseInt(atribut)) {
                System.out.println("Byla nalezena weapon: " + weapon);
                break;  
            }
        }
    }
    catch (NumberFormatException e){
        for (Weapons weapon : seznam) {
            if (weapon.getName().equals(atribut)) {
                System.out.println("Byla nalezena weapon: " + weapon);
                break;  
            }
        }
    }
    }
    
    /*
    * Metoda odeber data ze seznamu podle nějaké hodnoty atributu
    * Metoda ověřuje zda jsou v seznamu prvky nebo seznam je prazdny,
    * Uživatel musí zadat jmeno prvka kterého chcí odebrat ze seznamu,
    * pak metoda hledá v seznamu požadovaný prvek a odstraní jej, pak zapíše do konzole weapon které odobral
    */
    @Override
    public void odeber() throws KolekceException{
        if (!seznam.jePrazdny()){
            System.out.println("Zadej jmeno zbraně");
            var name = scaner.nextLine();
            name = name.trim();
            seznam.nastavPrvni();
            for (Weapons weapon : seznam) {
                if (weapon.getName().equals(name)) {
                    seznam.odeberAktualni();
                    System.out.println("Byl odebran weapon: " + weapon);
                    break;
                }
                seznam.dalsi();
                }
        } else {
        System.out.println("Seznam je prazdny!!!");
        }
    }
    
    /*
    * Metoda zobraz aktuální data v seznamu
    * Metoda zapíše do konzole aktualní prvek, pokud on nastaven, pokud ne, vypíše o tom zpravu
    */
    @Override
    public void dej() {
        try{
            var weapon = seznam.dejAktualni();
            System.out.println("Aktualni weapon je \n" + weapon);
            }
            catch(Exception e){
                System.out.println("Aktualni weapon neni nastaven \n");
        }
    }

    /*
    * Metoda edituj aktuální data v seznamu
    * Uživatel musí vybrat atribut který chcí změnit
    * Metoda změní atribut podle vyberu
    */
    @Override
    public void edituj() {
        try{
            Weapons weapon = seznam.dejAktualni();
            System.out.println("Zacatek editace");
            System.out.printf("Atributy: name, country, naboj, vaha \n");
            boolean flag = true;
                do {
                System.out.println("Zadej atribut, ktery chcete zmenit, nebo exit pro ukonceni editace");
                String atribut = scaner.nextLine().toLowerCase();
                switch(atribut){
                    case "name" ->{
                        System.out.println("Zadej nove jmeno");
                        weapon.setName(scaner.nextLine());
                    }
                    case "country" ->{
                        System.out.println("Zadej nove zemmi");
                        weapon.setCountrysVlastnika(scaner.nextLine());
                    }
                    case "naboj" ->{
                        System.out.println("Zadej novy pocet naboje");
                        weapon.setPocetNaboj(scaner.nextInt());
                    }
                    case "vaha" -> {
                        System.out.println("Zadej novy ves");
                        weapon.setVaha(scaner.nextInt());
                    }
                    case "exit" -> {
                        flag = false;
                    }
                    default -> {
                        throw new IllegalArgumentException();
                        }    
                    }
                }while(flag);     
        }catch (KolekceException ex) {
            System.out.println("Aktualni prvek neni nastaven");
        } catch (IllegalArgumentException il) {
            System.out.println("Neplatny atribut");
        }
    }

    /*
    * Metoda vyjmi aktuální data ze seznamu
    * 
    */
    @Override
    public void vyjmi() throws KolekceException{
        if(seznam.isAktualniNull()){
            System.out.println("Neni nastaven aktualni prvek");
                            
        }else {
            var weapon = seznam.odeberAktualni();
            System.out.println("Byl odebran weapon\n" + weapon);
        }
    }

    /*
    * Metoda nastav jako aktuální první data v seznamu
    *
    */
    @Override
    public void prvni() throws KolekceException{
        if (!seznam.jePrazdny()){
        seznam.nastavPrvni();
        } else { System.out.println("Seznam je prazdny!!!");}
    }

    /*
    * Metoda přejdi na další data
    *
    */
    @Override
    public void dalsi() throws KolekceException{
        try{
            if (!seznam.jePrazdny()){
                if(seznam.isAktualniNull()){
                    System.out.println("Neni nastaven aktualni prvek");
                }else {
                    seznam.dalsi();
                }
            } else { System.out.println("Seznam je prazdny!!!");}
        } catch (KolekceException e){
            System.out.println("Neni nastaven aktualni prvek");
        }
    }

    /*
    * Metoda přejdi na poslední data
    *
    */
    @Override
    public void posledni() throws KolekceException{
        if (!seznam.jePrazdny()){
                seznam.nastavPosledni();
        }
        else { System.out.println("Seznam je prazdny!!!");}
    }

    /*
    * Metoda zobraz počet položek v seznamu
    *
    */
    @Override
    public void pocet() {
        var pocet = seznam.size();
        System.out.println("Pocet prvku je: " + pocet);
    }

    /*
    * Metoda obnov seznam data z binárního souboru
    * 
    */
    @Override
    public void obnov() {
        if (ZapisCteni.binarni.exists()) {
            try {
                seznam = ZapisCteni.nacti(ZapisCteni.binarni.getPath(), seznam);
                System.out.println("Seznam byl obnoven");
            }catch (IOException ex) {
                Logger.getLogger(CommandMain.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else {
            System.out.println("Soubor neexistuje");
        }
    }

    /*
    * Metoda zálohuj seznam dat do binárního souboru
    *
    */
    @Override
    public void zalohuj() {
        try {
            ZapisCteni.uloz(ZapisCteni.binarni.getPath(), seznam);
            System.out.println("Zalohovani probehlo uspesne");
        } catch (IOException ex) {
            Logger.getLogger(CommandMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * Metoda zobraz seznam dat
    * 
    */
    @Override
    public void vypis() {
        if (!seznam.jePrazdny()){
            for (var weapon : seznam) {
                System.out.println(weapon);
            }
        }else { System.out.println("Seznam je prazdny!!!");}
    }

    /*
    * Metoda načti seznam data z textového souboru
    *
    */
    @Override
    public void nactitext() {
        try {
            ZapisCteni.readText(ZapisCteni.textovy.getPath(), seznam);
            System.out.println("Nacteni probehlo uspesne");
        } catch (Exception e) {
            System.err.println("Doslo k chybe");
        }
    }

    /*
    * Metoda ulož seznam data do textového souboru
    *
    */
    @Override
    public void uloztext() {
        try {
            ZapisCteni.writeStream(seznam.stream(), ZapisCteni.textovy.getPath());
            System.out.println("Ulozeni probehlo uspesne");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(CommandMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * Metoda generuj náhodně data pro testování
    *
    */
    @Override
    public void generuj() {
        System.out.println("Zadej pocet prvku");
        int pocet = scaner.nextInt();
        Generator.generuj(seznam, pocet);
        System.out.println("Bylo vygenerovano " + pocet);
    }

    /*
    * Metoda zruš všechny data v seznamu
    *
    */
    @Override
    public void zrus() {
        seznam.zrus();
        Weapons.cislo_id = 1;
        System.out.println("Vsechny prvky seznamu byly zruseny");
    }
    
}
