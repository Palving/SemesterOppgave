
package Model.Registrering;

import Model.Domene.*;
import java.io.Serializable;
import java.util.ArrayList;


public class Register implements Serializable {
    private static Register instance=new Register();
    
    private ArrayList<Artist> artister=new ArrayList<>();
    private ArrayList<Lokale> lokale=new ArrayList<>();
    private ArrayList<Arrangement> arrangement=new ArrayList<>();

   
    private ArrayList<KontaktPerson> kontaktPerson=new ArrayList<>();
    
    private Register(){
                                                                                                                                                                                                                                                                                                
    }
    
    public static Register getInstance(){
        return instance;
    }
    
    private String typeKlasse;
    public String registrer(ArrayList<Object> objekter){
       
        for (Object obj : objekter){
                typeKlasse=obj.getClass().toString();
                System.out.println(typeKlasse);
      
           switch(typeKlasse){
               case "class Model.Domene.Artist":
                   artister.add((Artist)obj);
                   System.out.println(artister.get(0).toString()+" registret");
                   break;
                   
               case "class Model.Domene.Lokale":
               lokale.add((Lokale)obj);
               System.out.println(obj.toString()+"registret");
               break;
               
               case "class Model.Domene.Arrangement":
                   arrangement.add((Arrangement)obj);
                   break;
                   
               case "class Model.Domene.KontaktPerson":
                   kontaktPerson.add((KontaktPerson)obj);
                   break;
                   
               
           } // end switch
        }
        return "fullført"+objekter.size()+" elementer registrert";
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
    
    
    public void test(){
        artister.add(new Artist("Jon","Rafoss","123","Sanger"));
        artister.add(new Artist("Mats","Grøsvik","321","Danser"));
        artister.add(new Artist("Magnus","Palving","333","Musiker"));
    }
    
    
    
}