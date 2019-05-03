
package Model.Domene;

import Model.Registrering.DatoFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import org.openjfx.FeilmeldingSystem;



public class Billett implements Serializable  {
    
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
        this.klokkeslett=arrang.getTidspunkt();
        this.pris=arrang.getBillettPris();
        this.arrangementNavn=arrang.getNavnPaaArrangement();
       
    }    
    catch(Exception e){
       FeilmeldingSystem.visFeilmelding("Plassnummer må være tall");
    }
    
    }

    public int getPlassNummer() {
        return plassNummer;
    }

   

    public String getLokaleNavn() {
        return lokaleNavn;
    }

   

    public LocalDate getDato() {
        return dato;
    }


    public int getPris() {
        return pris;
    }

  

    public String getKundeTlf() {
        return kundeTlf;
    }


    public String getArrangementNavn() {
        return arrangementNavn;
    }

   
    
    
    @Override
    public String toString(){
      return this.plassNummer+"\n "+this.kundeTlf+"\n "+this.lokaleNavn+"\n "+DatoFormat.formaterDato(dato, klokkeslett)+"\n "+this.pris+"kr"+"\n "+this.arrangementNavn;
    }
    
    public String toCSV(){
         return this.plassNummer+";"+this.kundeTlf+";"+this.lokaleNavn+";"+DatoFormat.formaterDato(dato, klokkeslett)+";"+this.pris+";"+this.arrangementNavn+";";
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
          
            return true;
        }
       
        return false;
        
    }
    
}
