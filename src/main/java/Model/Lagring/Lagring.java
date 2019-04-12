/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Lagring;

import Model.Domene.Artist;
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
                    register.registrer(artist);
                    break;
                case 7:
                    System.out.println("Arrangement");
                   /* Artist artist=new artist(liste);
                    objekter.add(artist);
                    reg.nyArtist();*/
                    break;
                case 2:
                    System.out.println("Lokale");
                    /*Artist artist=new artist(liste);
                    objekter.add(artist);
                    reg.nyArtist();*/
                    break;
                case 3:
                    System.out.println("Bilett");
                    /*Artist artist=new artist(liste);
                    objekter.add(artist);
                    reg.nyArtist();*/
                    break;
                case 6:
                    System.out.println("Kontakt person");
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
