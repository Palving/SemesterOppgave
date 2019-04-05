
package Model.Registrering;

import Model.Domene.Artist;
import java.io.Serializable;
import java.util.ArrayList;


public class Register implements Serializable {
    private ArrayList<Artist> artister=new ArrayList<>();
    
    public Register(){
        test();
        System.out.println(artister.get(0).toString());
    }
    
    
    public String registrer(ArrayList<Object> objekter){
        for (Object obj : objekter){
            if (obj.getClass().equals("Model.Domene.Artist")){
                System.out.println("registrer metode kalt");
                artister.add((Artist) obj);
            }
        }
        return "fullført";
    }
    
    public ArrayList<Artist> getArtister(){
        return artister;
    }
    
    
    public void test(){
        artister.add(new Artist("Jon","Rafoss","123","Sanger"));
        artister.add(new Artist("Mats","Grøsvik","321","Danser"));
        artister.add(new Artist("Magnus","Palving","333","Musiker"));
    }
    
    
    
}
