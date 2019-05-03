
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
    
    // hvis plass og arrangement er like så skal ikke billetten kunnes registrerers
    public boolean sjekkLedigPlass(Billett billettÅsjekke){
        for (Billett b : billett){
            if (billettÅsjekke.getPlassNummer()==b.getPlassNummer() 
                 && billettÅsjekke.getArrangementNavn().equals(b.getArrangementNavn())){
                return true;
            }
        }
        
      return false;
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
                   
               case "class Model.Domene.Billett":
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
    
    // for visualisering
    public void fyllRegister(){
        artister.add(new Artist("Jon","Rafoss","98419854","Sanger"));
        artister.add(new Artist("Mats","Grøsvik","97512392","Danser"));
        artister.add(new Artist("Magnus","Palving","97571228","Musiker"));
        
        lokale.add(new Lokale("Jæger",150));
        lokale.add(new Lokale("Garasjen",400));
     
      kontaktPerson.add(new KontaktPerson("Lars","Larsen","97562229","Lyd AS","bralyd.no","Bra mann"));
        kontaktPerson.add(new KontaktPerson("Kåre","Kent","97832131","Party AS","party.com","digger party"));
        LocalDate tidNow=LocalDate.now();
        arrangement.add(new Arrangement(artister.get(0), kontaktPerson.get(0), "Fest","Party","Sang også dans","Oslo","23:00",tidNow,50));
        arrangement.add(new Arrangement(artister.get(1),kontaktPerson.get(1),"Dåp","Gudstjeneste"," gutt skal bli frelst","Manglerud Kirke","12:00",tidNow,10));
        billett.add(new Billett(arrangement.get(0),500,"20"));
    }
    
    
    
}
