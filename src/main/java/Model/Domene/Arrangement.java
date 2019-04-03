
package Model.Domene;

import java.util.ArrayList;
import java.util.Date;

public class Arrangement {
    private String type;
    private String navnPaaArrangement;
    private String program;
    private String sted;
    private Date tidspunkt;
    private int billettPris;
    private int billettSalg;
    private KontaktPerson kontaktPerson;
    private Artist artist;
    
    public Arrangement(String type, String navnPaaArrangement, String program, String sted, Date tidspunkt, int billettPris, Artist artist, KontaktPerson kontaktPerson){
        this.type=type;
        this.navnPaaArrangement=navnPaaArrangement;
        this.program=program;
        this.sted=sted;
        this.tidspunkt=tidspunkt;
        this.billettPris=billettPris;
        this.kontaktPerson=kontaktPerson;
        this.artist=artist;
    }
    public Arrangement(Artist artist, KontaktPerson kontaktPerson, ArrayList<String> data){
        this.artist=artist;
        this.kontaktPerson=kontaktPerson;
        this.navnPaaArrangement=data.get(0);
        this.program=data.get(1);
        this.sted=data.get(2);
        //this.tidspunkt=(Date)data.get(3);
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

    public Date getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(Date tidspunkt) {
        this.tidspunkt = tidspunkt;
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
}
