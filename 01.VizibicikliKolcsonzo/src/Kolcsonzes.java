public class Kolcsonzes {
    private String nev;
    private String Jazon;
    private int Eora;
    private int Eperc;
    private int Vora;
    private int Vperc;

    public Kolcsonzes(String nev, String jazon, int eora, int eperc, int vora, int vperc) {
        this.nev = nev;
        Jazon = jazon;
        Eora = eora;
        Eperc = eperc;
        Vora = vora;
        Vperc = vperc;
    }

    public String getNev() {
        return nev;
    }

    public String getJazon() {
        return Jazon;
    }

    public int getEora() {
        return Eora;
    }

    public int getEperc() {
        return Eperc;
    }

    public int getVora() {
        return Vora;
    }

    public int getVperc() {
        return Vperc;
    }

    @Override
    public String toString() {
        return "Kolcsonzes{" +
                "nev='" + nev + '\'' +
                ", Jazon=" + Jazon +
                ", Eora=" + Eora +
                ", Eperc=" + Eperc +
                ", Vora=" + Vora +
                ", Vperc=" + Vperc +
                '}';
    }
}
