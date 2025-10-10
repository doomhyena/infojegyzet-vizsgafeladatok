import java.time.LocalDate;

public class Versenyzok {
    String nev;
    LocalDate szuletesiDatum;
    String nemzetiseg;
    String rajtszamRaw;
    Integer rajtszam;

    public Versenyzok(String nev, LocalDate szuletesiDatum, String nemzetiseg, String rajtszamRaw) {
        this.nev = nev;
        this.szuletesiDatum = szuletesiDatum;
        this.nemzetiseg = nemzetiseg;
        this.rajtszamRaw = rajtszamRaw;

        if (rajtszamRaw == null || rajtszamRaw.trim().isEmpty()) {
            this.rajtszam = null;
        } else {
            try {
                this.rajtszam = Integer.parseInt(rajtszamRaw.trim());
            } catch (NumberFormatException e) {
                this.rajtszam = null;
            }
        }
    }
}
