/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.command;

import cz.upce.fei.boop.pujcovna.data.Pistol;
import cz.upce.fei.boop.pujcovna.data.Rifle;
import cz.upce.fei.boop.pujcovna.data.Shotgun;
import cz.upce.fei.boop.pujcovna.data.SniperRifle;
import cz.upce.fei.boop.pujcovna.data.Weapons;
import cz.upce.fei.boop.pujcovna.generator.Generator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.LinkSeznam;
import cz.upce.fei.boop.pujcovna.perzistence.ZapisCteni;
import spravce.SpravaProstredku;

/**
 *
 * @author 42070
 */
public class CommandMain {
    private static boolean stop = true;
//    private static LinkSeznam<Weapons> seznam;
    public static void main(String[] args) throws KolekceException {
//        seznam = new LinkSeznam();
        System.out.println("Zacatek programu");
        help();
        command();
        System.out.println("Konec programu");
    }

    private static void help() {
        List<String> listCommand = Arrays.asList("\nhelp, h     - výpis příkazů",
                "novy, no     - vytvoř novou instanci a vlož data za aktuální prvek",
                "najdi, na, n  - najdi v seznamu data podle hodnoty nějakém atributu",
                "odeber, od   - odeber data ze seznamu podle nějaké hodnoty atributu",
                "dej         - zobraz aktuální data v seznamu",
                "edituj, edit - edituj aktuální data v seznamu",
                "vyjmi       - vyjmi aktuální data ze seznamu",
                "prvni, pr    - nastav jako aktuální první data v seznamu",
                "dalsi, da    - přejdi na další data",
                "posledni, po - přejdi na poslední data",
                "pocet       - zobraz počet položek v seznamu",
                "obnov       - obnov seznam data z binárního souboru",
                "zalohuj     - zálohuj seznam dat do binárního souboru",
                "vypis       - zobraz seznam dat",
                "nactitext, nt- načti seznam data z textového souboru",
                "uloztext, uz - ulož seznam data do textového souboru",
                "generuj, g   - generuj náhodně data pro testování",
                "zrus        - zruš všechny data v seznamu",
                "exit        - ukončení programu");
        listCommand.forEach(System.out::println);
    }

    private static <E> void command() throws KolekceException {
        do {
            try {
                Scanner scaner = new Scanner(System.in);
                System.out.println("Napiste comandu");
                var a = scaner.nextLine();
                SpravaProstredku sprava = new SpravaProstredku();
                switch (a) {
                    case "help", "h" ->
                        help();
                    case "novy", "no" -> {
                        sprava.novy();                   
                    }
                    case "najdi", "na", "n" -> {
                        sprava.najdi();
                    }
                    case "odeber", "od" -> {
                        sprava.odeber();
                    }
                    case "dej" -> {
                        sprava.dej();
                    }
                    case "edituj", "edit" -> {
                        sprava.edituj();
                    }
                    case "vyjmi" -> {
                        sprava.vyjmi();
                    }
                    case "prvni", "pr" -> {
                        sprava.prvni();
                    }
                    case "dalsi", "da" -> {
                        sprava.dalsi();
                    }
                    case "posledni", "po" -> {
                        sprava.posledni();
                    }
                    case "pocet" ->{
                        sprava.pocet();
                    }
                    case "obnov" -> {
                        sprava.obnov();
                    }
                    case "zalohuj" -> {
                        sprava.zalohuj();
                    }
                    case "vypis" ->{
                        sprava.vypis();
                    }
                    case "nactitext", "nt" -> {
                        sprava.nactitext();
                    }
                    case "uloztext", "ut" -> {
                        sprava.uloztext();
                    }
                    case "generuj", "g" -> {
                        sprava.generuj();
                    }
                    case "zrus", "z" -> {
                        sprava.zrus();
                    }
                    case "exit" ->
                        stop = false;
                    
                }

            } catch (InputMismatchException e) {
                System.out.println("Zadejte cislo prosim");
            }

        } while (stop);
    }
}
