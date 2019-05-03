
package Model.Nedlastning;

import Model.Domene.*;
import Model.Registrering.Register;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openjfx.FeilmeldingSystem;


public class TilCSV extends NedlastingSystem {
    Register register=Register.getInstance();
    private File file;
    
    public TilCSV(File file){
        super(file);
    }
    
    @Override
    public void lagreTilFil(ObservableList<Object> objekter,String valgt){
        
        switch (valgt){
            case "Arrangement":
                ArrangementTilCSV(FXCollections.observableList(register.getArrangement()));
                break;
            case "KontaktPerson":
                kontaktPersonTilCSV(FXCollections.observableList(register.getKontaktPerson()));
                break;
            case "Lokale":
                LokaleTilCSV(FXCollections.observableList(register.getLokale()));
                break;
            case "Artist":
                ArtisterTilCSV(FXCollections.observableList(register.getArtister()));
                break;
            case "Billett":
                BillettTilCSV(FXCollections.observableList(register.getBillett()));
                break;
                  
           }
    }
    
    private void BillettTilCSV(ObservableList<Billett> objekter){
         PrintWriter printWriter = null;
       
        try {
            printWriter = new PrintWriter(super.file);
            
            for (Billett billett : objekter){
         
                printWriter.print(billett.toCSV()+"\n");
            }
            printWriter.close();
        }
        
      catch(FileNotFoundException e){
          FeilmeldingSystem.visFeilmelding(e.getMessage());
                    }

    }
    
    private void LokaleTilCSV(ObservableList<Lokale> objekter){
         PrintWriter printWriter = null;
       
       
        try {
            printWriter = new PrintWriter(super.file);
            
            for (Lokale lokale : objekter){
         System.out.println("Loop kjørt");
                printWriter.print(lokale.toCSV()+"\n");
            }
            printWriter.close();
        }
        
      catch(FileNotFoundException e){
              FeilmeldingSystem.visFeilmelding(e.getMessage());
      }

    }
    
    private void ArrangementTilCSV(ObservableList<Arrangement> objekter){
        PrintWriter printWriter = null;
       
       
        try {
            printWriter = new PrintWriter(super.file);
            
            for (Arrangement arrang : objekter){
         
                printWriter.print(arrang.getArtist().toCSV()+arrang.getKontaktPerson().toCSV()+arrang.toCSV()+"\n");
            }
            printWriter.close();
        }
        
      catch(FileNotFoundException e){
              FeilmeldingSystem.visFeilmelding(e.getMessage());
     }

}
   
    
    private void ArtisterTilCSV(ObservableList<Artist> objekter){
 PrintWriter printWriter = null;
       
       
        try {
            printWriter = new PrintWriter(super.file);
            
            for (Artist artist : objekter){
         
                printWriter.print(artist.toCSV()+"\n");
            }
            printWriter.close();
        }
        
      catch(FileNotFoundException e){
              FeilmeldingSystem.visFeilmelding(e.getMessage());
       }

   }
    
   private void kontaktPersonTilCSV(ObservableList<KontaktPerson> objekter){
       PrintWriter printWriter = null;
       
       
        try {
            printWriter = new PrintWriter(super.file);
            
            for (KontaktPerson kontaktPerson : objekter){
         
                printWriter.print(kontaktPerson.toCSV()+"\n");
            }
            printWriter.close();
        }
        
      catch(FileNotFoundException e){
           FeilmeldingSystem.visFeilmelding(e.getMessage());
        }

   }
}
