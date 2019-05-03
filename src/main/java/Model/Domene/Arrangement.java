
package Model.Domene;

import Model.Registrering.DatoFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import org.openjfx.FeilmeldingSystem;

public class Arrangement implements Serializable {
    
    private KontaktPerson kontaktPerson;
    private Artist artist;
    private String sted;
    
    private String type;
    private String navnPaaArrangement;
    private String program;
    private int billettPris;
    private LocalDate dato;
    private int billettSalg=0;
    private String tidspunkt;
 
    public Arrangement(Artist artist, KontaktPerson kontaktPerson, String type, String navnPaaArrangement, String program, String sted, String tidspunkt,LocalDate dato, int billettPris){
        this.type=type;
        this.navnPaaArrangement=navnPaaArrangement;
        this.program=program;
        this.sted=sted;
        this.dato=dato;
        this.tidspunkt=tidspunkt;
        this.billettPris=billettPris;
        this.kontaktPerson=kontaktPerson;
        this.artist=artist;
       
    }
    public Arrangement(Artist artist, KontaktPerson kontaktPerson, ArrayList<String> data, LocalDate dato) throws NumberFormatException{
        
        try{
        this.artist=artist;
        this.kontaktPerson=kontaktPerson;                
        this.tidspunkt=data.get(0);
        this.type=data.get(1);
        this.navnPaaArrangement=data.get(2);
        this.program=data.get(3);  
        this.billettPris=Integer.parseInt(data.get(4));
        this.sted=data.get(5);
        this.dato=dato;
    
        }
        catch(NumberFormatException e){
           FeilmeldingSystem.visFeilmelding("Billetpris kan ikke være bokstaver");
        }
   
    }
  
       
    
    protected void plussBillettsalg(){
        this.billettSalg++;
    }

    public KontaktPerson getKontaktPerson() {
        return kontaktPerson;
    }

  

    public Artist getArtist() {
        return artist;
    }

   

    public String getType() {
        return type;
    }


    public String getNavnPaaArrangement() {
        return navnPaaArrangement;
    }


    public String getProgram() {
        return program;
    }

  

    public String getSted() {
        return sted;
    }

   

    public LocalDate getDato() {
        return dato;
    }

   
    public String getTidspunkt(){
        return this.tidspunkt;
    }

    public int getBillettPris() {
        return billettPris;
    }

  

    public int getBillettSalg() {
        return billettSalg;
    }

    public void setBillettSalg(int billettSalg) {
        this.billettSalg = billettSalg;
    }
    

    
    @Override
     public String toString() {
        return  tidspunkt+"\n "+ type + "\n " + navnPaaArrangement + "\n " + program + "\n " + billettPris+"kr"+"\n "+ tidspunkt;
     }
     
     public String toCSV(){
         return  tidspunkt+";"+ type + ";" +sted+";"+ navnPaaArrangement + ";" + program + ";" + billettPris+";"+billettSalg+";";
     }
    
  public boolean equals(Arrangement arrang){
        if (this==arrang){
            return true;
        }
        if (getClass() !=arrang.getClass()){
            return false;
        }
        Arrangement other=arrang;
        
        if (other.getArtist().equals(arrang.getArtist()) 
                && other.getKontaktPerson().equals(arrang.getKontaktPerson()) 
                && other.getBillettPris()==arrang.getBillettPris()
                && other.getProgram().equals(arrang.getProgram())
                && other.getSted().equals(arrang.getSted())
                && other.getType().equals(arrang.getType())
                && other.getNavnPaaArrangement().equals(arrang.getNavnPaaArrangement())){
           
            return true;
        }
        
        return false;
        
    }
    
    
}
