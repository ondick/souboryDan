import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) throws IOException {
        Path soubor1 = Path.of("data", "tajemstvi.txt");
        Path soubor2 = Path.of("data", "studenti.txt");

        if (!Files.exists(soubor1)) {
            Files.createDirectories(soubor1.getParent());
        }
        if (!Files.exists(soubor2)) {
            Files.createDirectories(soubor2.getParent());
        }
        int pocet = 0;
        String hledanyText = "Heslo dne je:";
        String radek;
        String nejdelsi = "";
        try {
            try (BufferedReader reader1 = Files.newBufferedReader(soubor1);
                 BufferedReader reader2 = Files.newBufferedReader(soubor2)) {


                while ((radek = reader1.readLine()) != null) {
                    pocet += 1;
                    if (radek.startsWith(hledanyText)) {
                        System.out.println(radek);
                    }
                    if (radek.length() > nejdelsi.length()) {
                        nejdelsi = radek;
                    }
                }
                System.out.println(pocet);
                System.out.println("Nejdelsi radek: " + nejdelsi);


                int pocetStudentu=0;
                int pocetPraha=0;
                String zatoulanyStudent = "";
                while ((radek = reader2.readLine()) != null) {
                    if (radek.isBlank()) {
                        continue;
                    }

                    pocetStudentu++;

                    // Rozdělení podle čárky: casti[0] = jméno, casti[1] = věk, casti[2] = město
                    String[] casti = radek.split(",");
                    String jmeno = casti[0].trim();
                    int vekš = Integer.parseInt(casti[1].trim());
                    String mesto = casti[2].trim();

                    if (mesto.equalsIgnoreCase("Praha")) {
                        pocetPraha++;
                    }

                    if (vekš > 50) {
                        zatoulanyStudent = jmeno;
                    }
                }

                // Výpis výsledků pro studenti.txt
                System.out.println("=== STUDENTI.TXT ===");
                System.out.println("Počet studentů: " + pocetStudentu);
                System.out.println("Počet studentů z Prahy: " + pocetPraha);
                System.out.println("Zatoulaný student (>50 let): " + zatoulanyStudent);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } finally {

        }
    }

}