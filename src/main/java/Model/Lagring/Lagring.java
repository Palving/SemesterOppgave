/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Lagring;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class Lagring {
    private String vei;    
    
    public Lagring (String vei){
        this.vei=vei;
        
    }
    
    public void lesFil (){
        ArrayList<Object> objekter=new ArrayList<>();
        try(RandomAccessFile reader = new RandomAccessFile(vei, "r")) {
        String[] array;
        String line = reader.readLine(); // skip meta data
        
        while((line = reader.readLine()) != null) {
            array=line.split(";");
            switch(array.length){
                case 4:
                    System.out.println("Artist");
                   /* Artist artist=new artist(liste);
                    objekter.add(artist);
                    reg.nyArtist();*/
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
