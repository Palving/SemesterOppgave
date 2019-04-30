
package Model.Domene;

import Model.Avvik.InvalidIntValueException;
import Model.Registrering.DatoFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Arrangement {
    
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
    private DatoFormat tid;
    
  
    
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
        this.tid=new DatoFormat(this.dato, this.tidspunkt);
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
            System.err.println(e.getMessage());
        }
       
         
    // exception på int
    }
  
       
    
    protected void plussBillettsalg(){
        this.billettSalg++;
    }

    public KontaktPerson getKontaktPerson() {
        return kontaktPerson;
    }

    public void setKontaktPerson(KontaktPerson kontaktPerson) {
        this.kontaktPerson = kontaktPerson;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNavnPaaArrangement() {
        return navnPaaArrangement;
    }

    public void setNavnPaaArrangement(String navnPaaArrangement) {
        this.navnPaaArrangement = navnPaaArrangement;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSted() {
        return sted;
    }

    public void setSted(String sted) {
        this.sted = sted;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato=dato;
    }
    
    public String getTidspunkt(){
        return this.tidspunkt;
    }

    public int getBillettPris() {
        return billettPris;
    }

    public void setBillettPris(int billettPris) {
        this.billettPris = billettPris;
    }

    public int getBillettSalg() {
        return billettSalg;
    }

    public void setBillettSalg(int billettSalg) {
        this.billettSalg = billettSalg;
    }
    

    /*@Override
    public String toString() {
        return  type + "\n " + navnPaaArrangement + "\n " + program + "\n " + sted + "\n "+DatoFormat.formaterDato(dato, tidspunkt)+ "\n " + billettPris + "\n " + billettSalg + "\n " + kontaktPerson.getEtternavn() + "\n " + artist.getEtternavn();
    }*/
    @Override
     public String toString() {
        return  tidspunkt+"\n "+ type + "\n " + navnPaaArrangement + "\n " + program + "\n " + billettPris+"kr"+"\n "+ tidspunkt;
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
            // samme info -> returner objekt som det matchet med
           // System.out.println("samme info -> returner objekt det matchet med");
            return true;
        }
        //System.out.println("ingen match -> registrer objekt sammen med andre objekt");
        return false;
        
    }
    
    
}
