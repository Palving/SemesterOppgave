package Model.Domene;

import java.io.Serializable;
import java.util.ArrayList;
import org.openjfx.FeilmeldingSystem;

public class Lokale implements Serializable {
    private String lokaleNavn;
    private int antallPlasser;

    public Lokale (String lokaleNavn, int antallPlasser){
        this.lokaleNavn = lokaleNavn;
        this.antallPlasser = antallPlasser;

    }
    public Lokale(ArrayList<String> data) {
       
        try{
             this.lokaleNavn=data.get(0);
             this.antallPlasser=Integer.parseInt(data.get(1));
        }
        catch(NumberFormatException e){
            FeilmeldingSystem.visFeilmelding("Antall plasser må være tall");
        }
       
    }
    
     public String getLokaleNavn(){
        return lokaleNavn;
    }

    public int getAntallPlasser(){
        return antallPlasser;
    }

    
    @Override
    public String toString(){
        return lokaleNavn+"\n "+antallPlasser;
    }
    public String toCSV(){
        return lokaleNavn+";"+antallPlasser+";";
    }

}