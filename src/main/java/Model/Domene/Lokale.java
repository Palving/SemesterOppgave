package Model.Domene;

class Lokale {
    private String lokale;
    private int antallPlasser;

    public Lokale (String lokale, int antallPlasser){
        this.lokale = lokale;
        this.antallPlasser = antallPlasser;

    }

    public String getLokale(){
        return lokale;
    }

    public void setLokale(String lokale){
        this.lokale = lokale;
    }

    public int getAntallPlasser(){
        return antallPlasser;
    }

    public void setAntallPlasser(int antallPlasser){
        this.antallPlasser = antallPlasser;
    }
}