
package Model.Domene;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Person implements Serializable {
    
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

    public String getEtternavn() {
        return etternavn;
    }


    public String getTlf() {
        return tlf;
    }

    @Override
    public String toString() {
        return fornavn +"\n "+ etternavn+"\n "+ tlf ;
    }
    
    public String toCSV(){
         return fornavn +";"+ etternavn+";"+ tlf+";" ;
    }
    
    
    
}
