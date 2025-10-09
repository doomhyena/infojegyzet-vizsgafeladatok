import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;


public class Main {
    public static void feladat(int n) {
        System.out.println(n+".feladat: ");
    }

    public static void main(String[] args) throws IOException {

        feladat(2);
        List<Nobel> dijak = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("nobel.csv"), StandardCharsets.UTF_8);
        for (int i = 1; i < lines.size(); i++) {
            String[] m = lines.get(i).split(";");
            int ev = Integer.parseInt(m[0]);
            String tipus = m[1];
            String keresztnev = m[2];
            String vezeteknev = m.length > 3 ? m[3] : "";
            dijak.add(new Nobel(ev, tipus, keresztnev, vezeteknev));
        }

        feladat(3);
        for (Nobel n : dijak) {
            if (n.keresztnev.equals("Arthur B.") && n.vezeteknev.equals("McDonald")) {
                System.out.println("Arthur B. McDonald díj típusa: " + n.tipus);
            }
        }

        feladat(4);
        for (Nobel n : dijak) {
            if (n.ev == 2017 && n.tipus.equals("irodalmi")) {
                System.out.println("2017-ben az irodalmi díjat kapta: " + n.keresztnev + " " + n.vezeteknev);
            }
        }

        feladat(5);
        for (Nobel n : dijak) {
            if (n.ev >= 1990 && n.tipus.equals("béke") && n.vezeteknev.isEmpty()) {
                System.out.println(n.ev + ": " + n.keresztnev);
            }
        }

        feladat(6);
        for (Nobel n : dijak) {
            if (n.keresztnev.contains("Curie") || n.vezeteknev.contains("Curie")) {
                System.out.println(n.ev + ": " + n.keresztnev + " " + n.vezeteknev + " (" + n.tipus + ")");
            }
        }

        feladat(7);
        Map<String, Integer> stat = new TreeMap<>();
        for (Nobel n : dijak) {
            stat.put(n.tipus, stat.getOrDefault(n.tipus, 0) + 1);
        }
        for (String tipus : stat.keySet()) {
            System.out.println(tipus + ": " + stat.get(tipus) + " db");
        }

        feladat(8);
        PrintWriter ki = new PrintWriter("orvosi.txt", "UTF-8");
        for (Nobel n : dijak) {
            if (n.tipus.equals("orvosi")) {
                ki.println(n.ev + ";" + n.keresztnev + ";" + n.vezeteknev);
            }
        }
        ki.close();
    }
}