
package Model.Domene;

import Model.Registrering.DatoFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;



public class Billett {
    
    private Integer plassNummer;
    private String lokaleNavn;
    private LocalDate dato;
    private Integer pris;
    private String kundeTlf;
    private String klokkeslett;
    
    public Billett(Arrangement arrang, Integer plassNummer, String kundeTlf){
       
        
        try{
         arrang.plussBillettsalg();
        this.plassNummer=plassNummer;
        this.lokaleNavn=arrang.getSted();
        this.pris=arrang.getBillettPris();
        this.kundeTlf=kundeTlf;
        this.dato=arrang.getDato();
        this.klokkeslett=arrang.getTidspunkt();
        }
        catch(Exception e){
            
        }
        
    }
    
    
    public Billett(Arrangement arrang, ArrayList<String> data){
    try {
         arrang.plussBillettsalg();
        this.plassNummer=Integer.parseInt(data.get(0));
        this.lokaleNavn=arrang.getSted();
        this.dato=arrang.getDato();
       // this.klokkeslett=arrang.getTidspunkt();
        this.pris=arrang.getBillettPris();
        //this.lokaleNavn=data.get(1);
        //this.dato=new SimpleDateFormat("dd/MM/yyyy").parse(data.get(2));
        this.kundeTlf=data.get(1);
       
    }    
    catch(Exception e){
        
    }
    
    }

    public int getPlassNummer() {
        return plassNummer;
    }

    public void setPlassNummer(int plassNummer) {
        this.plassNummer = plassNummer;
    }

    public String getLokaleNavn() {
        return lokaleNavn;
    }

    public void setLokaleNavn(String lokaleNavn) {
        this.lokaleNavn = lokaleNavn;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String getKundeTlf() {
        return kundeTlf;
    }

    public void setKundeTlf(String kundeTlf) {
        this.kundeTlf = kundeTlf;
    }

   
    
    
    @Override
    public String toString(){
      return "\n Plassnummer:"+this.plassNummer+"\n Lokalenavn: "+this.lokaleNavn+DatoFormat.formaterDato(dato, klokkeslett)+"\n Pris "+this.pris+"+\n Tlf: "+this.kundeTlf;
    }
    
}
