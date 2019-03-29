package Model.Domene;

class Lokale {
    private String lokale;
    private int antallPlasser;

    public Lokale (String lokale, int plasser){
        this.lokale = lokale;
        this.plasser = antallPlasser;

    }

    public String getLokale(){
        return lokale;
    }

    public String setLokale(String lokale){
        this.lokale= lokale;
    }

    public int getAntallPlasser(){
        return antallPlasser;
    }

    public int setAntallPlasser(int antallPlasser){
        this.antallPlasser = antallPlasser;
    }
}