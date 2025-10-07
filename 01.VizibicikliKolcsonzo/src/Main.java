import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void feladat(int n) {
        System.out.println(n+". feladat: ");
    }

    public static void main(String[] args) {
        feladat(4);
        List<Kolcsonzes> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("kolcsonzesek.txt"))) {
            br.readLine();
            String sor;
            while ((sor = br.readLine()) != null) {
                String[] adatok = sor.split(";");
                String nev = adatok[0];
                String jazon = adatok[1];
                int eora = Integer.parseInt(adatok[2]);
                int eperc = Integer.parseInt(adatok[3]);
                int vora = Integer.parseInt(adatok[4]);
                int vperc = Integer.parseInt(adatok[5]);
                lista.add(new Kolcsonzes(nev, jazon, eora, eperc, vora, vperc));
            }
            System.out.println("Az adatok beolvasva.");
        } catch (Exception e) {
            System.out.println("Hiba a fájl beolvasásakor: " + e.getMessage());
        }

        feladat(5);
        System.out.println(lista.size() + " kölcsönzés történt.");
        feladat(6);
        Scanner sc = new Scanner(System.in);
        System.out.println("Kérem a keresett nevet: ");
        String keresettNev = sc.nextLine();
        boolean talalt = false;
        for (Kolcsonzes k : lista) {
            if (k.getNev().equalsIgnoreCase(keresettNev)) {
                System.out.println(k.getEora() + ":" + String.format("%02d", k.getEperc()) +
                        " - " + k.getVora() + ":" + String.format("%02d", k.getVperc()));
                talalt = true;
            }
        }
        if (!talalt) {
            System.out.println("Nem volt ilyen nevű kölcsönző!");
        }
        feladat(7);
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Adjon meg egy időpontot (óra:perc): ");
        String idopont = sc2.nextLine();

        String[] idoAdatok = idopont.split(":");
        int ora = Integer.parseInt(idoAdatok[0]);
        int perc = Integer.parseInt(idoAdatok[1]);
        int beirtPerc = ora * 60 + perc;

        System.out.println("Ekkor vízen lévő járművek:");

        for (Kolcsonzes k : lista) {
            int kezdes = k.getEora() * 60 + k.getEperc();
            int vege = k.getVora() * 60 + k.getVperc();

            if (beirtPerc >= kezdes && beirtPerc < vege) {
                System.out.printf(
                        "%s – %s (%02d:%02d - %02d:%02d)%n",
                        k.getJazon(),
                        k.getNev(),
                        k.getEora(), k.getEperc(),
                        k.getVora(), k.getVperc()
                );
            }
        }
        feladat(8);
        int osszBevetel = 0;
        for (Kolcsonzes k : lista) {
            int kezdes = k.getEora() * 60 + k.getEperc();
            int vege = k.getVora() * 60 + k.getVperc();
            int elteltIdo = vege - kezdes;
            int fizetendo = (elteltIdo / 30) * 2400;
            if (elteltIdo % 30 != 0) {
                fizetendo += 2400;
            }
            osszBevetel += fizetendo;
        }
        System.out.println("A napi bevétel: " + osszBevetel + " Ft");
        feladat(9);
        try (java.io.PrintWriter pw = new java.io.PrintWriter("f.txt", "UTF-8")) {
            for (Kolcsonzes k : lista) {
                if (k.getJazon().equalsIgnoreCase("F")) {
                    pw.printf("%s; %02d:%02d-%02d:%02d%n",
                            k.getNev(),
                            k.getEora(), k.getEperc(),
                            k.getVora(), k.getVperc());
                }
            }
            System.out.println("Az f.txt állomány elkészült.");
        } catch (Exception e) {
            System.out.println("Hiba az f.txt írásakor: " + e.getMessage());
        }
        feladat(10);
        java.util.Map<String, Integer> stat = new java.util.TreeMap<>();

        for (Kolcsonzes k : lista) {
            stat.put(k.getJazon(), stat.getOrDefault(k.getJazon(), 0) + 1);
        }

        for (String jazon : stat.keySet()) {
            System.out.println(jazon + " - " + stat.get(jazon));
        }
    }
}
