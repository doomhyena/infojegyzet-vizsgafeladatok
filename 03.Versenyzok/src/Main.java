import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    static List<Versenyzok> lista = new ArrayList<>();

    public static void feladat(int n) {
        System.out.println(n + ". feladat:");
    }

    private static void feltolt() {
        String fileName = "pilotak.csv";

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {

            String line = br.readLine();
            if (line == null) {
                System.err.println("Üres állomány: " + fileName);
                return;
            }

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length < 4) continue;

                String nev = parts[0].trim();
                String szulet = parts[1].trim();
                String nemzet = parts[2].trim();
                String rajtszamRaw = parts[3].trim();

                LocalDate datum = LocalDate.parse(szulet, inputFormatter);
                lista.add(new Versenyzok(nev, datum, nemzet, rajtszamRaw));
            }

            System.out.println("A fájl sikeresen beolvasva! (" + lista.size() + " sor)");

        } catch (Exception e) {
            System.err.println("Hiba a fájl beolvasásakor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        feladat(2);
        feltolt();

        feladat(3);
        System.out.println(lista.size());

        feladat(4);
        if (!lista.isEmpty()) {
            Versenyzok last = lista.get(lista.size() - 1);
            System.out.println(last.nev);
        } else {
            System.out.println("(nincs adat)");
        }

        feladat(5);
        LocalDate cutoff = LocalDate.of(1901, 1, 1);
        DateTimeFormatter outputFmt = DateTimeFormatter.ofPattern("yyyy. MM. dd.");
        for (Versenyzok p : lista) {
            if (p.szuletesiDatum.isBefore(cutoff)) {
                System.out.println(p.nev + " (" + p.szuletesiDatum.format(outputFmt) + ")");
            }
        }

        feladat(6);
        Integer minRajtszam = null;
        Versenyzok minVersenyzok = null;
        for (Versenyzok p : lista) {
            if (p.rajtszam != null) {
                if (minRajtszam == null || p.rajtszam < minRajtszam) {
                    minRajtszam = p.rajtszam;
                    minVersenyzok = p;
                }
            }
        }
        if (minVersenyzok != null) {
            System.out.println(minVersenyzok.nemzetiseg);
        } else {
            System.out.println("(nincs rajtszámos pilóta)");
        }

        feladat(7);
        Map<Integer, Integer> countByNumber = new LinkedHashMap<>();
        List<Integer> order = new ArrayList<>();

        for (Versenyzok p : lista) {
            if (p.rajtszam != null) {
                countByNumber.put(p.rajtszam, countByNumber.getOrDefault(p.rajtszam, 0) + 1);
                if (!order.contains(p.rajtszam)) order.add(p.rajtszam);
            }
        }

        List<String> duplicates = new ArrayList<>();
        for (Integer num : order) {
            if (countByNumber.get(num) > 1) {
                duplicates.add(String.valueOf(num));
            }
        }
        
        if (duplicates.isEmpty()) {
            System.out.println("(nincs duplikált rajtszám)");
        } else {
            System.out.println(String.join(", ", duplicates));
        }
    }
}
