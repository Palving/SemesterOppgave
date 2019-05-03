package Model.Nedlastning;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.Domene.*;
import Model.Registrering.Register;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import org.openjfx.FeilmeldingSystem;


public class TilJOBJ extends NedlastingSystem{
   
    private Register register=Register.getInstance();
    private File file;
    
    public TilJOBJ(File file){
        super(file);
    }
  
 @Override
    public void lagreTilFil(ObservableList<Object> data, String valgt){
       
        try{
             FileOutputStream fos = new FileOutputStream(super.getPath());
             ObjectOutputStream out= new ObjectOutputStream(fos);
        
           ArrayList<Object> liste=new ArrayList<>();
            for (Object a : data){
              
               liste.add(a);
             
            }
            switch(valgt){
                case "Artist":
                   ArrayList<Artist> artistListe=new ArrayList<>();
                    for (Object a : liste){
                        artistListe.add((Artist)a);
                        System.out.print(a.toString());
                    }

                    out.writeObject(artistListe);
                     break;
                  
                case "Lokale":
                    ArrayList<Lokale> lokaleListe=new ArrayList<>();
                    for (Object a : liste){
                        lokaleListe.add((Lokale)a);
                    }
                      out.writeObject(lokaleListe);
                      break;
            
                 case "Arrangement":
                ArrayList<Arrangement> arrangListe=new ArrayList<>();
                    for(Object a : liste){
                    arrangListe.add((Arrangement)a);
                          }
            out.writeObject(arrangListe);
            break;
            
            case "KontaktPerson":
                ArrayList<KontaktPerson> kontaktPersonListe=new ArrayList<>();
            for(Object a : liste){
            kontaktPersonListe.add((KontaktPerson)a);
        }
            out.writeObject(kontaktPersonListe);
            break;
            
            case "Billett":
                ArrayList<Billett> billettListe=new ArrayList<>();
                for(Object obj : liste){
                    billettListe.add((Billett)obj);
                }
                out.writeObject(billettListe);
                break;
            }
         
             out.close();
         
          
        }
        catch(IOException e){
               FeilmeldingSystem.visFeilmelding(e.getMessage());
                 
             }
        }
        
    
    }
    


