/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Domene;

/**
 *
 * @author palvi
 */
public class Artist extends Person {
    private String typeArtist;
    
    public Artist(String typeArtist, String fornavn, String etternavn, String tlf){
        super(fornavn, etternavn, tlf);
        this.typeArtist=typeArtist;
        
    }
    
}
