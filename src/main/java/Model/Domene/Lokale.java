package Model.Domene;

class Lokale {
    private String lokaleNavn;
    private int antallPlasser;

    public Lokale (String lokaleNavn, int antallPlasser){
        this.lokaleNavn = lokaleNavn;
        this.antallPlasser = antallPlasser;

    }

    public String getLokaleNavn(){
        return lokaleNavn;
    }

    public void setLokaleNavn(String lokale){
        this.lokaleNavn = lokale;
    }

    public int getAntallPlasser(){
        return antallPlasser;
    }

    public void setAntallPlasser(int antallPlasser){
        this.antallPlasser = antallPlasser;
    }
}