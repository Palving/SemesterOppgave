
package Model.Domene;

import java.util.ArrayList;

public abstract class Person {
    
    private String fornavn;
    private String etternavn;
    private String tlf;
    
    
    public Person(String fornavn, String etternavn, String tlf){
        this.fornavn=fornavn;
        this.etternavn=etternavn;
        this.tlf=tlf;
    }
     public Person(ArrayList<String> data){
         this.fornavn=data.get(0);
         this.etternavn=data.get(1);
         this.tlf=data.get(2);
        
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    @Override
    public String toString() {
        return " "+fornavn +" "+ etternavn+" "+ tlf ;
    }
    
    
    
}
