/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Opplastning;

import Model.Domene.Arrangement;
import Model.Domene.Artist;
import Model.Domene.Billett;
import Model.Domene.KontaktPerson;
import Model.Domene.Lokale;
import Model.Registrering.Register;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import org.openjfx.FeilmeldingSystem;

/**
 *
 * @author jonny
 */
public class FraCSV extends OpplastingSystem{
    private File file;
    
    public FraCSV(File file){
        super(file);
    }
    
    public KontaktPerson finnKontaktPerson(ArrayList<String> linjeLestInn){
        Register register = Register.getInstance();
        ArrayList<String> arrayFinnKontaktPerson = new ArrayList<>();
        for (int i = 4 ; i< 10 ; i++){
            arrayFinnKontaktPerson.add(linjeLestInn.get(i));
        }
        KontaktPerson kntktPerson = new KontaktPerson(arrayFinnKontaktPerson);
        //register.registrer(kntktPerson);
        System.out.print("funka2");
        return kntktPerson;
    }
    
    public Artist FinnArtist(ArrayList<String> linjeLestInn){
        //Artist NyArtist = new Artist();
        Register register = Register.getInstance();
        ArrayList<String> arrayFinnArtist = new ArrayList<>();
        for (int i = 0 ; i< 4 ; i++){
            //array3.get(i) = array4.add(i);
            arrayFinnArtist.add(linjeLestInn.get(i));
        }
        Artist NyArtist = new Artist(arrayFinnArtist);
        //register.registrer(NyArtist);
        System.out.print("funka1");
        return NyArtist;
    }
    
    public Arrangement FinnArrangement(ArrayList<String> linjeLestInn){
        
                    Artist artisten=FinnArtist(linjeLestInn);
                    KontaktPerson kontaktPerson1 =finnKontaktPerson(linjeLestInn);
                    
                   
                     String hentInnStringDato = linjeLestInn.get(15);
                    String riktigFormat =hentInnStringDato.replace(".", "");
                    //System.out.print(k);
                    
                   LocalDate dato =  LocalDate.parse(riktigFormat);
                    int billettSalg = Integer.parseInt(linjeLestInn.get(16));
                   
                    
                   Arrangement arrangement = new Arrangement(artisten, kontaktPerson1, linjeLestInn.get(10) , linjeLestInn.get(11) , linjeLestInn.get(12) , linjeLestInn.get(13) ,linjeLestInn.get(14), dato , billettSalg );
                   //register.registrer(arrangement);
                   return arrangement;
    }
    
    
    @Override
    public void lagreTilFil() throws ClassNotFoundException {
    
        Register register = Register.getInstance();
    
        String csvFilNavn=super.file.getPath();
        System.out.println(csvFilNavn);
        BufferedReader br = null;
        String line = "";
      

        try {
            br = new BufferedReader(new FileReader(csvFilNavn));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                ArrayList<String> linjeLestInn = new ArrayList<>(Arrays.asList(line.split(";")));
                if (linjeLestInn.size()!=4 )
                System.out.print("funka :" + linjeLestInn.size());
                
                switch(linjeLestInn.size()){
                case 4:
                    System.out.println("Artist");
                    
                    Artist artist=new Artist(linjeLestInn);
               
                    register.registrer(artist);
                    break;
                case 17:
                   Arrangement arrangement = FinnArrangement(linjeLestInn);
            
                   register.registrer(arrangement);
                    break;
                case 2:
                    Lokale nyLokale = new Lokale(linjeLestInn);
                  
                    register.registrer(nyLokale);
                    break;
                case 19:
                    Arrangement arrangementIBillet = FinnArrangement(linjeLestInn);
                  
                    int pris = Integer.parseInt(linjeLestInn.get(16));           
                    register.registrer(new Billett(arrangementIBillet,pris, linjeLestInn.get(17)));
                    break;
                case 6:
                    KontaktPerson kontaktPerson = new KontaktPerson(linjeLestInn);
                 
                    register.registrer(kontaktPerson); 
                    break; 
                
                }   
            }
         
        } 
        catch (FileNotFoundException e) {
               FeilmeldingSystem.visFeilmelding(e.getMessage());
        } 
        catch (IOException e) {
               FeilmeldingSystem.visFeilmelding(e.getMessage());
        } 
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                       FeilmeldingSystem.visFeilmelding(e.getMessage());
                }
            }
        }
    }

   

   
}




