import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void feladat(int n) {
        System.out.println(n+".feladat: ");
    }
    public static void main(String[] args) {
        feladat(2);
        List<JackieData> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("jackie.txt"), StandardCharsets.UTF_8))) {

            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                int year = Integer.parseInt(parts[0]);
                int races = Integer.parseInt(parts[1]);
                int wins = Integer.parseInt(parts[2]);
                int podiums = Integer.parseInt(parts[3]);
                int poles = Integer.parseInt(parts[4]);
                int fastests = Integer.parseInt(parts[5]);
                dataList.add(new JackieData(year, races, wins, podiums, poles, fastests));
            }

        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasásakor!");
            return;
        }

        feladat(3);
        System.out.println(dataList.size());

        feladat(4);
        JackieData max = Collections.max(dataList, Comparator.comparingInt(d -> d.races));
        System.out.println(max.year);

        feladat(5);
        int wins60s = 0, wins70s = 0;
        for (JackieData d : dataList) {
            if (d.year >= 1960 && d.year < 1970) wins60s += d.wins;
            else if (d.year >= 1970 && d.year < 1980) wins70s += d.wins;
        }
        System.out.println("70-es évek: " + wins70s + " megnyert verseny");
        System.out.println("60-as évek: " + wins60s + " megnyert verseny");

        feladat(6);
        try (PrintWriter pw = new PrintWriter("jackie.html", StandardCharsets.UTF_8)) {
            pw.println("<!doctype html>");
            pw.println("<html>");
            pw.println("<head><meta charset=\"utf-8\"><style>td { border:1px solid black; }</style></head>");
            pw.println("<body>");
            pw.println("<h1>Jackie Stewart</h1>");
            pw.println("<table>");
            for (JackieData d : dataList) {
                pw.printf("<tr><td>%d</td><td>%d</td><td>%d</td></tr>%n", d.year, d.races, d.wins);
            }
            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");
        } catch (IOException e) {
            System.out.println("Hiba a HTML fájl létrehozásakor!");
        }
        System.out.println("jackie.html létrehozva");
    }
}