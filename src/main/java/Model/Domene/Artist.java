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
public class Artist extends Person implements Serializable  {
    private String typeArtist;
    
    public Artist(String fornavn, String etternavn, String tlf, String typeArtist){
        super(fornavn, etternavn, tlf);
        this.typeArtist=typeArtist;
        
    }
    public Artist(ArrayList<String> data){
        super(data.get(0),data.get(1),data.get(2));
        typeArtist=data.get(3);
    }
   
    public String getTypeArtist() {
        return typeArtist;
    }

   public boolean equals(Artist artist){
        if (this==artist){
            return true;
        }
        if (getClass() !=artist.getClass()){
            return false;
        }
        Artist other=artist;
        
        if (other.getFornavn().equals(artist.getFornavn()) 
                && other.getEtternavn().equals(artist.getEtternavn()) 
                && other.getTlf().equals(artist.getTlf()) 
                && other.getTypeArtist().equals(artist.getTypeArtist())){
        
            return true;
        }
       
        return false;
        
    }
    
    @Override
    public String toString(){
        return super.getFornavn()+"\n "+super.getEtternavn()+"\n "+super.getTlf()+"\n "+getTypeArtist();
    }
    public String toCSV(){
        return  super.toCSV()+getTypeArtist()+";";
    }
    
}
