/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Lagring;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Lagring {
    private String vei;    
    
    public Lagring (String vei){
        this.vei=vei;
    }
    
    public void lesFil (){
        try(RandomAccessFile reader = new RandomAccessFile(vei, "r")) {
        String[] array;
        String line = reader.readLine(); // skip meta data
        
        while((line = reader.readLine()) != null) {
            array=line.split(";");
            switch(array.length){
                case 4:
                    System.out.println("Artist");
                    break;
                case 7:
                    System.out.println("Arrangement");
                    
                    break;
                case 2:
                    System.out.println("Lokale");
                    //break;
                case 3:
                    for(String s : array){
                        System.out.println(s);
                    }
                    break;
                case 6:
                    System.out.println("Kontakt person");
                    break; 
            }
           
            /*for( int i = 0 ; i< array.length ; i ++){
                array[i] = null;
            }*/
            
                    
            //register.registrer(objekter);
        }
        } catch (FileNotFoundException e) {
                System.err.println("Could not find the specified file");
        }   catch (IOException e) {
                System.err.println("Could not read the specified file. Cause: " + e.getCause());
            }

    } 
}
