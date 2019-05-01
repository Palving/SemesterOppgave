/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Registrering;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Magnus
 */
public class DatoFormat implements Serializable {

private LocalDate dato;
private String klokkeslett;
    
    public DatoFormat(LocalDate dato, String klokkeslett){
        this.dato=dato;
        this.klokkeslett=klokkeslett;
    }
       
public static String formaterDato(LocalDate dato, String klokkeslett){
    
    try{
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/YYYY");
    return formatter.format(dato)+" "+klokkeslett;
    }
    catch(Exception e){
        System.err.println(e.getMessage());
    }
    
      return "Feil";
    }

@Override
public String toString(){
    return dato.toString()+" "+klokkeslett;
}
    
}
