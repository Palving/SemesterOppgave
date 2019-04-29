
package Model.Domene;

import Model.Registrering.DatoFormat;
import java.time.LocalDate;
import java.util.ArrayList;



public class Billett {
    
    private int plassNummer;
    private String lokaleNavn;
    private LocalDate dato;
    private int pris;
    private String kundeTlf;
    private String klokkeslett;
    private String arrangementNavn;
    
    public Billett(Arrangement arrang, int plassNummer, String kundeTlf){
       
        
        try{
         arrang.plussBillettsalg();
        this.plassNummer=plassNummer;
        this.lokaleNavn=arrang.getSted();
        this.pris=arrang.getBillettPris();
        this.kundeTlf=kundeTlf;
        this.dato=arrang.getDato();
        this.klokkeslett=arrang.getTidspunkt();
        this.arrangementNavn=arrang.getNavnPaaArrangement();
        }
        catch(Exception e){
            
        }
        
    }
    
    
    public Billett(Arrangement arrang, ArrayList<String> data){
    try {
         arrang.plussBillettsalg();
        this.plassNummer=Integer.parseInt(data.get(0));
         this.kundeTlf=data.get(1);
        this.lokaleNavn=arrang.getSted();
        this.dato=arrang.getDato();
        this.pris=arrang.getBillettPris();
        this.arrangementNavn=arrang.getNavnPaaArrangement();
       
    }    
    catch(Exception e){
        System.out.println(e.getMessage());
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

    public String getArrangementNavn() {
        return arrangementNavn;
    }

    public void setArrangementNavn(String arrangementNavn) {
        this.arrangementNavn = arrangementNavn;
    }

   
    
    
    @Override
    public String toString(){
      return this.plassNummer+"\n "+this.kundeTlf+"\n "+this.lokaleNavn+"\n "+DatoFormat.formaterDato(dato, klokkeslett)+"\n "+this.pris+"\n "+this.arrangementNavn;
    }
  
    public boolean equals(Billett billett){
        if (this==billett){
            return true;
        }
        if (getClass() != billett.getClass()){
            return false;
        }
        Billett other=billett;
        
        if (other.getArrangementNavn().equals(billett.getArrangementNavn()) 
                && other.getKundeTlf().equals(billett.getKundeTlf()) 
                && other.getLokaleNavn().equals(billett.getLokaleNavn()) 
                && other.getPris()==billett.getPris()
                && other.getPlassNummer()==billett.getPlassNummer()
                && other.getDato().equals(billett.getDato())){
            // samme info -> returner objekt som det matchet med
           // System.out.println("samme info -> returner objekt det matchet med");
            return true;
        }
        //System.out.println("ingen match -> registrer objekt sammen med andre objekt");
        return false;
        
    }
    
}
