/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Registrering;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Magnus
 */
public class DatoFormat {
       
public static String formaterDato(LocalDate dato, String klokkeslett){
    
    try{
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/YYYY");
    return formatter.format(dato)+"\n Klokkeslett: "+klokkeslett;
    }
    catch(Exception e){
        System.err.println(e.getMessage());
    }
    
      return "Feil";
    }
    
}
