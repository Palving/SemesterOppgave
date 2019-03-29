
package Model.Domene;

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
    
    public Arrangement(String type, String navnPaaArrangement,String program, String sted, Date tidspunkt, int billettPris, int billettSalg, Artist artist, KontaktPerson kontaktPerson){
        this.type=type;
        this.navnPaaArrangement=navnPaaArrangement;
        this.program=program;
        this.sted=sted;
        this.tidspunkt=tidspunkt;
        this.billettPris=billettPris;
        this.billettSalg=billettSalg;
        this.kontaktPerson=kontaktPerson;
        this.artist=artist;
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
