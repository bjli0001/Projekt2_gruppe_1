import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ToFile {

    public static void saveList(ArrayList<Medlem> medlemmer) throws IOException {
        FileWriter fil = new FileWriter("medlemmer.txt");
        PrintWriter ud = new PrintWriter(fil);

        for (Medlem m: medlemmer){
            ud.println(m.toString());
        }
        fil.close();
    }
    //Denne metode tager en liste af Medlem-objekter som input og gemmer disse medlemmer i en tekstfil ved navn "medlemmer.txt".

    public static void saveResults(Hold hold) throws IOException{
        FileWriter fil = new FileWriter(hold.holdnavn+"_tider.txt");
        PrintWriter ud = new PrintWriter(fil);
        ud.println(hold.tiderFri.stream().map(Object::toString).collect(Collectors.joining(":")));
        ud.println(hold.tiderRyg.stream().map(Object::toString).collect(Collectors.joining(":")));
        ud.println(hold.tiderButterfly.stream().map(Object::toString).collect(Collectors.joining(":")));
        ud.println(hold.tiderBryst.stream().map(Object::toString).collect(Collectors.joining(":")));
        fil.close();

    }
    // Tager et Hold-objekt som input og gemmer svømmetiderne for dette hold i en tekstfil. Filnavnet dannes ved at tilføje "_tider.txt" til holdnavnet. For hvert disciplin (Fri svømning, Rygcrawl, Butterfly, Brystsvømning)
}
