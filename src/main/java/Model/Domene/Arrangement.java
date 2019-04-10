
package Model.Domene;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Arrangement {
    private String type;
    private String navnPaaArrangement;
    private String program;
    private String sted;
    private Date dato;
    private String tidspunkt;
    private int billettPris;
    private int billettSalg=0;
    private KontaktPerson kontaktPerson;
    private Artist artist;
    
    public Arrangement(Artist artist, KontaktPerson kontaktPerson, String type, String navnPaaArrangement, String program, String sted, String tidspunkt,Date dato, int billettPris){
        this.type=type;
        this.navnPaaArrangement=navnPaaArrangement;
        this.program=program;
        this.sted=sted;
        this.dato=new Date(1,1,2020);
        this.tidspunkt=tidspunkt;
        this.billettPris=billettPris;
        this.kontaktPerson=kontaktPerson;
        this.artist=artist;
    }
    public Arrangement(Artist artist, KontaktPerson kontaktPerson, ArrayList<String> data){
        try{
        this.artist=artist;
        this.kontaktPerson=kontaktPerson;
        this.type=data.get(0);
        this.navnPaaArrangement=data.get(1);
        this.program=data.get(2);
        this.sted=data.get(3);
        this.tidspunkt="klokken 20:30";
        this.dato=new SimpleDateFormat("dd/MM/yyyy").parse(data.get(4));
    }
        catch(Exception e){
            
        }
        }
       
    
    public void plussBillettsalg(){
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

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
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
    

    @Override
    public String toString() {
        return  "Type: " + type + "\n Navn: " + navnPaaArrangement + "\n Program " + program + "\n Sted; " + sted + "\n Dato: "+dato+ "\n Tidspunkt: " + tidspunkt + "\n Pris: " + billettPris + "\n Billetter Solgt: " + billettSalg + "\n Kontaktperson: " + kontaktPerson.getEtternavn() + "\n Artist: " + artist.getEtternavn();
    }
    
    public String visData(){
        return "Navn: "+navnPaaArrangement+"\n Sted: "+sted+"\n Dato"+dato+"\n Tidspunkt"+tidspunkt;
    }
    
    
    
}
