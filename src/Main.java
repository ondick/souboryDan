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
        String radek1;
        String nejdelsi = "";
        int pocetStudentu=0;
        int pocetPraha=0;
        String vek = "";
        int vek1;
        int vek2=0;
        try {
            try (BufferedReader reader1 = Files.newBufferedReader(soubor1);
                 BufferedReader reader2 = Files.newBufferedReader(soubor2);) {


                while ((radek=reader1.readLine()) != null) {
                    pocet+=1;
                    if (radek.startsWith(hledanyText)) {
                        System.out.println(radek);
                    }
                    if (radek.length()>nejdelsi.length()) {
                        nejdelsi = radek;
                    }
                }
                System.out.println(pocet);
                System.out.println("Nejdelsi radek: " + nejdelsi);
                while ((radek1=reader2.readLine()) != null) {
                    pocetStudentu++;

                    if (radek1.contains("Praha")) {
                        pocetPraha+=1;
                    }
                    String[] datumSplit = vek.split(" ,");
                    vek = datumSplit[1] + " ," + datumSplit[0] + " ," + datumSplit[2];
                    vek1=Integer.parseInt(datumSplit[0]);
                    if (vek1) {}

                }
                System.out.println("");
                System.out.println("Pocet studentu: " + pocetStudentu);
                System.out.println("Pocet z prahy: " + pocetPraha);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}