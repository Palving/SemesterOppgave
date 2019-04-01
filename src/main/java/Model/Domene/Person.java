
package Model.Domene;

public abstract class Person {
    
    private String fornavn;
    private String etternavn;
    private String tlf;
    
    
    public Person(String fornavn, String etternavn, String tlf){
        this.fornavn=fornavn;
        this.etternavn=etternavn;
        this.tlf=tlf;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
    
    
    
}
