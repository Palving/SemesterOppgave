/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Lagring;

import Model.Domene.Arrangement;
import Model.Domene.Artist;
import Model.Domene.KontaktPerson;
import Model.Domene.Lokale;
import Model.Registrering.Register;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;



public class Lagring {
    private String vei;    
    
    public Lagring (String vei){
        this.vei=vei;
        
    }
    
    public KontaktPerson finnKontaktPerson(ArrayList<String> array3){
        ArrayList<String> array5 = new ArrayList<>();
        for (int i = 4 ; i< 10 ; i++){
            array5.add(array3.get(i));
        }
        KontaktPerson kntktPerson = new KontaktPerson(array5);
        register.registrer(kntktPerson);
        return kntktPerson;
    }
    
    public Artist FinnArtist(ArrayList<String> array3){
        //Artist NyArtist = new Artist();
        ArrayList<String> array4 = new ArrayList<>();
        for (int i = 0 ; i< 4 ; i++){
            //array3.get(i) = array4.add(i);
            array4.add(array3.get(i));
        }
        Artist NyArtist = new Artist(array4);
        register.registrer(NyArtist);
        
        return NyArtist;
    }
    
    private Register register=Register.getInstance();
    public void lesFil (){
        //ArrayList<Object> objekter=new ArrayList<>();
        try(RandomAccessFile reader = new RandomAccessFile(vei, "r")) {
        //ArrayList<String> array = new ArrayList<>();
        String line = reader.readLine(); // skip meta data
        
        while((line = reader.readLine()) != null) {
            String[] array1 = line.split(";"); // convertere til en Arraylist
            //List<String> array2 = Arrays.asList(line.split(";"));
            ArrayList<String> array3 = new ArrayList<>(Arrays.asList(line.split(";")));
            
            

//array=line.split(";");
            switch(array3.size()){
                case 4:
                    System.out.println("Artist");
                    Artist artist=new Artist(array3);
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
                    Artist artisten=FinnArtist(array3);
                    KontaktPerson kontaktPerson1 =finnKontaktPerson(array3);
                    
                    ArrayList<String> arrangement1 = new ArrayList<>();
                    for (int i = 10 ; i< array3.size() ; i++ ){
                        arrangement1.add(array3.get(i));
                    }
                        
                    
                    for (Artist a : register.getArtister()){
                        if (a.equals(artisten)){
                            System.out.println("samme fornavn!!!");
                        }
                        
                    }
                    
                  // Arrangement arrangement = new Arrangement(artisten, kontaktPerson1,arrangement1  );
                  // register.registrer(arrangement);
                   /* Artist artist=new artist(liste);
                    objekter.add(artist);
                    reg.nyArtist();*/
                    break;
                case 2:
                    System.out.println("Lokale");
                    Lokale nyLokale = new Lokale(array3);
                    break;
                case 3:
                    System.out.println("Billett");
                    
                    /*Artist artist=new artist(liste);
                    objekter.add(artist);
                    reg.nyArtist();*/
                    break;
                case 6:
                    System.out.println("Kontakt person");
                    KontaktPerson kontaktPerson = new KontaktPerson(array3);
                    register.registrer(kontaktPerson);
                   /* Artist artist=new artist(liste);
                    objekter.add(artist);
                    reg.nyArtist();*/
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
