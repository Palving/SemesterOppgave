
package Model.Nedlastning;

import Model.Domene.Arrangement;
import Model.Domene.KontaktPerson;
import Model.Opplastning.OpplastingSystem;
import Model.Registrering.Register;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

class FinnObjekter{
    public String finnKontaktPerson(ArrayList artistStringArray){
        Register register = Register.getInstance();
        ArrayList KP =register.getKontaktPerson();
        register.registrer(artistStringArray.get(0));
        
        String ut = "";
        String s = artistStringArray.get(1)+ "";
        String[] test = s.split("\n ");
        for(int j = 0; j < test.length; j++){
                    ut+= test[j]+";";
                
                }
        /*
        String ut = "1";
        for (int i = 0 ; i<KP.size(); i++){
            if(KP.get(i).equals(artistStringArray.get(0))){
                String s = KP.get(i) +"";
                System.out.print(KP.get(i) +"");
                String[] splittet =s.split("\n ");
                for(int j = 0; j < splittet.length; j++){
                    ut+= splittet[i]+";";
                
                }
                
                
            }
        }*/
        System.out.print(ut);
        
       // KontaktPerson kntktPerson = new KontaktPerson(arrayFinnKontaktPerson);
        //register.registrer(kntktPerson);
        return ut;
    }
    
    public String finnArtist(ArrayList artistStringArray){
        Register register = Register.getInstance();
        ArrayList Artist =register.getArtister();
        register.registrer(artistStringArray.get(1));
        
        String ut = "";
        String s = artistStringArray.get(1)+ "";
        String[] test = s.split("\n ");
        for(int j = 0; j < test.length; j++){
                    ut+= test[j]+";";
                
                }
        /*for (int i = 0 ; i<Artist.size(); i++){
            if(Artist.get(i).equals(artistStringArray.get(1))){
                String s = Artist.get(i) +"";
                String[] splittet =s.split("\n ");
                for(int j = 0; j < splittet.length; j++){
                    ut+= splittet[i]+";";
                
                }
                
            }
        }*/
        return ut;
    }
    
    public String ArrangementTilBillet (ArrayList arrangement){
        String ut = "";
        for (int i = 2; i <arrangement.size(); i++){
            ut +=arrangement.get(i) + ";";
        }
        return ut;
        
    }
}

public class TilCSV extends NedlastingSystem {
    Register register=Register.getInstance();
    private File file;
    
    public TilCSV(File file){
        super(file);
    }
    
    public void lagreFil(int valgt){
        switch (valgt){
            case 1:
                ArrangementTilCSV();
                break;
            case 2:
                kontaktPersonTilCSV();
                break;
            case 3:
                LokaleTilCSV();
                break;
            case 4:
                break;
            case 5:
                break;
                    
            
                    
                    }
    }
    
    public void Billet(){
        PrintWriter printWriter = null;
        FinnObjekter f = new FinnObjekter();
            
        try {
            printWriter = new PrintWriter (file);
            
            ArrayList lokale =register.getLokale();
            
            for (int i = 0 ; i < lokale.size(); i ++){
            String lokaleString = lokale.get(i) +"";
            String[] lokaleStringArray = lokaleString.split("\n ");
            for(int j = 0; j < lokaleStringArray.length; j++){
                if (j==6){
                    printWriter.print(f.finnKontaktPerson(lokale));
                    printWriter.print(f.finnArtist(lokale));
                    printWriter.print(f.ArrangementTilBillet(lokale));
                    
                    //printWriter.print()
                }
                printWriter.print(lokaleStringArray[j] + ";");
            }
            printWriter.print("\n");
            }
                    
            printWriter.close (); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        System.out.println("funka");
    }
    
    public void LokaleTilCSV(){
        PrintWriter printWriter = null;
        
            
        try {
            printWriter = new PrintWriter (file);
            
            ArrayList lokale =register.getLokale();
            //FinnObjekter k = new FinnObjekter();
            
            for (int i = 0 ; i < lokale.size(); i ++){
            String lokaleString = lokale.get(i) +"";
            String[] lokaleStringArray = lokaleString.split("\n ");
            for(int j = 0; j < lokaleStringArray.length; j++){
                
                printWriter.print(lokaleStringArray[j] + ";");
                        
            }
            printWriter.print("\n");
            }
                    
            printWriter.close (); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        System.out.println("funka");
    }
    
    public void ArrangementTilCSV(){
        PrintWriter printWriter = null;
        FinnObjekter k = new FinnObjekter();
            
        try {
            printWriter = new PrintWriter (file);
            
            ArrayList arrangement =register.getArrangement();
            
            for (int i = 0 ; i < arrangement.size(); i ++){
            String arrangementString = arrangement.get(i) +"";
            String[] arrangementStringArray = arrangementString.split("\n ");
            /*ArrayList<String> data=new ArrayList<>(arrangementStringArray);
            Arrangement arrang=new Arrangement(data);*/
            
            for(int j = 0; j < arrangementStringArray.length; j++){
               /* if (j == 0){
                    String l = k.finnKontaktPerson(arrangement);
                    printWriter.print(l);
                    System.out.println("Funka1" + l);
                }
                if(j == 1){
                    printWriter.print(k.finnArtist(arrangement));
                    System.out.println("Funka2");
                }
                else{*/
                printWriter.print(arrangementStringArray[j] + ";");
                //}
            }
            printWriter.print("\n");
            }
                    
            printWriter.close (); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        System.out.println("funka");
   
    }
    
    public void ArtisterTilCSV(){

        PrintWriter printWriter = null;
        
            
        try {
            printWriter = new PrintWriter (file);
            
            ArrayList artister =register.getArtister();
            
            for (int i = 0 ; i < artister.size(); i ++){
            String artistString = artister.get(i) +"";
            String[] artistStringArray = artistString.split("\n ");
            for(int j = 0; j < artistStringArray.length; j++){
                printWriter.print(artistStringArray[j] + ";");
            }
            printWriter.print("\n");
            }
                    
            printWriter.close (); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        System.out.println("funka");
   }
    
   public void kontaktPersonTilCSV(){
       PrintWriter printWriter = null;
        
            
        try {
            printWriter = new PrintWriter (file);
            
            ArrayList kontaktPerson =register.getKontaktPerson();
            
            for (int i = 0 ; i < kontaktPerson.size(); i ++){
            String kontaktPersonString = kontaktPerson.get(i) +"";
            String[] kontaktPersonStringArray = kontaktPersonString.split(" ");
            for(int j = 1; j < kontaktPersonStringArray.length; j++){
                printWriter.print(kontaktPersonStringArray[j] + ";");
            }
            printWriter.print("\n");
            }
                    
            printWriter.close (); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        System.out.println("funka");
   }
   
}
