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
public class KontaktPerson extends Person {
    
    private String nettSide;
    private String firma;
    private String info;
    
    public KontaktPerson(String fornavn, String etternavn, String tlf,String nettside, String firma, String info){
        super(fornavn, etternavn, tlf);
        this.nettSide=nettside;
        this.firma=firma;
        this.info=info;
    }

    public String getNettSide() {
        return nettSide;
    }

    public void setNettSide(String nettSide) {
        this.nettSide = nettSide;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
    
}
