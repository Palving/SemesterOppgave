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


public class TilJOBJ extends NedlastingSystem{
   
    private Register register=Register.getInstance();
    private File file;
    
    public TilJOBJ(File file){
        super(file);
    }
  

    public void lagreFil(ObservableList<Object> data, String valgt){
       
        
        try{
           //  ArrayList<Artist> artister=register.getArtister();
             FileOutputStream fos = new FileOutputStream(super.getPath());
              ObjectOutputStream out= new ObjectOutputStream(fos);
           // ObjectOutputStream out=null;
          // ObservableList<Lokale> liste=FXCollections.observableArrayList();
           ArrayList<Object> liste=new ArrayList<>();
            for (Object a : data){
                //out.
               liste.add(a);
               // out.writeObject(a);
               // out.close();
            }
            switch(valgt){
                case "Artist":
                   ArrayList<Artist> artistListe=new ArrayList<>();
                    for (Object a : liste){
                        artistListe.add((Artist)a);
                        System.out.print(a.toString());
                    }
                     //out.writeObject(artistListe);
                    System.out.println("artistliste lagret");
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
           // out.writeObject(liste);
          
             out.close();
         
          
        }
        catch(IOException e){
            
        }
        
    
    }
    
    
}

