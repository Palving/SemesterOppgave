
package Model.Domene;

import java.util.ArrayList;
import java.util.Date;



public class Billett {
    
    private int plassNummer;
    private String lokaleNavn;
    private Date dato;
    private int pris;
    private String kundeTlf;
    
    public Billett(int plassNummer, String lokaleNavn, Date dato, int pris, String kundeTlf){
        this.plassNummer=plassNummer;
        this.lokaleNavn=lokaleNavn;
        this.dato=dato;
        this.pris=pris;
        this.kundeTlf=kundeTlf;
        
    }
    public Billett(ArrayList<String> data){
        
    }
    
}
