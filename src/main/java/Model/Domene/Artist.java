/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Domene;

import java.util.ArrayList;


/**
 *
 * @author palvi
 */
public class Artist extends Person {
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

    public void setTypeArtist(String typeArtist) {
        this.typeArtist = typeArtist;
    }
    
    @Override
    public String toString(){
        return "Artist: "+super.getFornavn()+" "+super.getEtternavn()+" "+super.getTlf()+" "+getTypeArtist();
    }
    
}
