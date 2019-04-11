
package Model.Registrering;

import Model.Domene.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


public class Register implements Serializable {
    private static Register instance=new Register();
    
    private ArrayList<Artist> artister=new ArrayList<>();
    private ArrayList<Lokale> lokale=new ArrayList<>();
    private ArrayList<Arrangement> arrangement=new ArrayList<>();
    private ArrayList<KontaktPerson> kontaktPerson=new ArrayList<>();
    private ArrayList<Billett> billett=new ArrayList<>();
    
    private Register(){
                                                                                                                                                                                                                                                                                                
    }
    
    public static Register getInstance(){
        return instance;
    }
    
    private String typeKlasse;
    
    // Tar inn arrayliste av type objekt og plasserer de i riktig arraylist
    public String registrer(Object obj){
       
                typeKlasse=obj.getClass().toString();
               
           switch(typeKlasse){
               case "class Model.Domene.Artist":
                   artister.add((Artist)obj);
                   break;
                   
               case "class Model.Domene.Lokale":
               lokale.add((Lokale)obj);
               
               break;
               
               case "class Model.Domene.Arrangement":
                   arrangement.add((Arrangement)obj);
                   break;
                   
               case "class Model.Domene.KontaktPerson":
                   kontaktPerson.add((KontaktPerson)obj);
                   break;
                   
               case "class Model.Domene.Bilett":
                   billett.add((Billett)obj);
                   break;
                   
               
           } // end switch
     
        return "fullført"+obj.toString()+" elementer registrert";
    }
    
    public ArrayList<Artist> getArtister(){
        return artister;
    }
     public ArrayList<Lokale> getLokale() {
        return lokale;
    }

    public ArrayList<Arrangement> getArrangement() {
        return arrangement;
    }

    public ArrayList<KontaktPerson> getKontaktPerson() {
        return kontaktPerson;
    }
    
    public ArrayList<Billett> getBillett(){
        return billett;
    }
    
    
    public void test(){
        artister.add(new Artist("Jon","Rafoss","123","Sanger"));
        artister.add(new Artist("Mats","Grøsvik","321","Danser"));
        artister.add(new Artist("Magnus","Palving","333","Musiker"));
        
        kontaktPerson.add(new KontaktPerson("Kåre","Kent","123","hehe","hoho","h"));
        LocalDate kødd=LocalDate.now();
        arrangement.add(new Arrangement(artister.get(0), kontaktPerson.get(0), "Fest","Party","Sang også dans","Oslo","23:00",kødd,50));
    }
    
    
    
}
