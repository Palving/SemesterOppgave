/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Nedlastning;

import Model.Domene.Artist;
import Model.Domene.Billett;
import Model.Domene.KontaktPerson;
import Model.Domene.Lokale;
import Model.Registrering.Register;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jonny
 */
public class TilCSV extends NedlastingSystem {
    private String vei;
    private Register register;
    public TilCSV (String vei){
        super(vei);
    }
    
    
     public KontaktPerson finnKontaktPerson(ArrayList<String> array3){
        ArrayList<String> arrayFinnKontaktPerson = new ArrayList<>();
        for (int i = 4 ; i< 10 ; i++){
            arrayFinnKontaktPerson.add(array3.get(i));
        }
        KontaktPerson kntktPerson = new KontaktPerson(arrayFinnKontaktPerson);
        register.registrer(kntktPerson);
        return kntktPerson;
    }
    
    public Artist FinnArtist(ArrayList<String> array3){
        //Artist NyArtist = new Artist();
        ArrayList<String> arrayFinnArtist = new ArrayList<>();
        for (int i = 0 ; i< 4 ; i++){
            //array3.get(i) = array4.add(i);
            arrayFinnArtist.add(array3.get(i));
        }
        Artist NyArtist = new Artist(arrayFinnArtist);
        register.registrer(NyArtist);
        
        return NyArtist;
    }
    
    //private Register register=Register.getInstance();
    public void lesFil (){
        //ArrayList<Object> objekter=new ArrayList<>();
        try(RandomAccessFile reader = new RandomAccessFile(vei, "r")) {
        //ArrayList<String> array = new ArrayList<>();
        String line = reader.readLine(); // skip meta data
        
        while((line = reader.readLine()) != null) {
            String[] array1 = line.split(";"); // convertere til en Arraylist
            //List<String> array2 = Arrays.asList(line.split(";"));
            ArrayList<String> linjeLestInn = new ArrayList<>(Arrays.asList(line.split(";")));
            for (int i = 0 ; i< linjeLestInn.size();i++){
                System.out.print(linjeLestInn.get(i));
            }
            

//array=line.split(";");
            switch(linjeLestInn.size()){
                case 4:
                    System.out.println("Artist");
                    System.out.println(linjeLestInn.size()+"");
                    Artist artist=new Artist(linjeLestInn);
                    System.out.println(artist);
                    //objekter.add(artist);
                    for (Artist a : register.getArtister()){
                        if (a.equals(artist)){
                            System.out.println("samme fornavn!!!");
                        }
                        
                    }
                    register.registrer(artist);
                    break;
                case 7:
                    System.out.println("Arrangement");
                    Artist artisten=FinnArtist(linjeLestInn);
                    KontaktPerson kontaktPerson1 =finnKontaktPerson(linjeLestInn);
                    
                    ArrayList<String> arrangement1 = new ArrayList<>();
                    for (int i = 10 ; i< linjeLestInn.size() ; i++ ){
                        arrangement1.add(linjeLestInn.get(i));
                    }
                        
                    
                    for (Artist a : register.getArtister()){
                        if (a.equals(artisten)){
                            System.out.println("samme fornavn!!!");
                        }
                        
                    }
                    
                  // Arrangement arrangement = new Arrangement(artisten, kontaktPerson1,arrangement1  );
                  // register.registrer(arrangement);
                   
                    break;
                case 2:
                    System.out.println("Lokale");
                    Lokale nyLokale = new Lokale(linjeLestInn);
                    for (Lokale a : register.getLokale()){
                        if (a.equals(nyLokale)){
                            System.out.println("samme fornavn!!!");
                        }
                        
                    }
                    register.registrer(nyLokale);
                    break;
                case 3:
                    System.out.println("Billett");
                    //Billett billett = new Billett(array3);
                    for (Billett a : register.getBillett()){
                        if (a.equals(billett)){
                            System.out.println("samme fornavn!!!");
                        }
                        
                    }
                
                    
                    //register.registrer(billett);
                    System.out.println("3");
                    break;
                case 6:
                    System.out.println("Kontakt person");
                    KontaktPerson kontaktPerson = new KontaktPerson(linjeLestInn);
                    for (KontaktPerson a : register.getKontaktPerson()){
                        if (a.equals(kontaktPerson)){
                            System.out.println("samme fornavn!!!");
                        }
                        
                    }
                    register.registrer(kontaktPerson);
                   
                    break; 
            }
           
            //register.registrer(objekter);
        }
        } catch (FileNotFoundException e) {
                System.err.println("Could not find the specified file");
        }   catch (IOException e) {
                System.err.println("Could not read the specified file. Cause: " + e.getCause());
            }

    } 
}
