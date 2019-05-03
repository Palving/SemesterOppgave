/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Domene;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author palvi
 */
public class KontaktPerson extends Person implements Serializable  {
    
    private String nettSide;
    private String firma;
    private String info;
    
    public KontaktPerson(String fornavn, String etternavn, String tlf,String nettside, String firma, String info){
        super(fornavn, etternavn, tlf);
        this.nettSide=nettside;
        this.firma=firma;
        this.info=info;
    }
     public KontaktPerson(ArrayList<String> data){
        super(data.get(0),data.get(1),data.get(2));
        this.nettSide=data.get(3);
        this.firma=data.get(4);
        this.info=data.get(5);
    }

    public String getNettSide() {
        return nettSide;
    }

  

    public String getFirma() {
        return firma;
    }


    public String getInfo() {
        return info;
    }


    @Override
    public String toString() {
        return super.toString()+"\n " + nettSide + "\n " + firma + "\n " + info;
    }
    public String toCSV(){
        return super.toCSV()+ nettSide + ";" + firma + ";" + info+";";
    }
    
    public boolean equals(KontaktPerson kontaktPerson){
        if (this==kontaktPerson){
            System.out.print("samme type");
            return true;
        }
        if (getClass() !=kontaktPerson.getClass()){
            return false;
        }
        KontaktPerson other=kontaktPerson;
        
        if (other.getFornavn().equals(kontaktPerson.getFornavn())
          && other.getEtternavn().equals(kontaktPerson.getEtternavn()) 
          && other.getTlf().equals(kontaktPerson.getTlf()) 
          && other.getFirma().equals(kontaktPerson.getFirma())
          && other.getInfo().equals(kontaktPerson.getInfo()) 
          && other.getNettSide().equals(kontaktPerson.getNettSide())){
           
                     return true;
                 }
        return false;
        
    }
    
    
    
}
