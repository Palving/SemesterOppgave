package Model.Domene;

import java.io.Serializable;
import java.util.ArrayList;

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
        catch(Exception e){
            
        }
       
    }
    
    public String toString(){
        return lokaleNavn+"\n "+antallPlasser;
    }

    public String getLokaleNavn(){
        return lokaleNavn;
    }

    public void setLokaleNavn(String lokale){
        this.lokaleNavn = lokale;
    }

    public int getAntallPlasser(){
        return antallPlasser;
    }

    public void setAntallPlasser(int antallPlasser){
        this.antallPlasser = antallPlasser;
    }
}