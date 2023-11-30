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

    public static void saveResults(Hold hold) throws IOException{
        System.out.println("John");
        FileWriter fil = new FileWriter(hold.holdnavn+"_tider.txt");
        PrintWriter ud = new PrintWriter(fil);
        ud.println(hold.tiderFri.stream().map(Object::toString).collect(Collectors.joining(":")));
        ud.println(hold.tiderRyg.stream().map(Object::toString).collect(Collectors.joining(":")));
        ud.println(hold.tiderButterfly.stream().map(Object::toString).collect(Collectors.joining(":")));
        ud.println(hold.tiderBryst.stream().map(Object::toString).collect(Collectors.joining(":")));
        fil.close();

    }
//    public static void saveCustomer(ArrayList<Customer> dataLines) throws IOException {
//        FileWriter fil = new FileWriter("customers.txt");
//        PrintWriter ud = new PrintWriter(fil);
//
//        for (Customer k: dataLines) {
//            ArrayList<String> bookArr = new ArrayList<>();
//            for (String[] s: k.bookings){
//
//                bookArr.add(String.join(".",s));
//
//            }
//            ud.println(k.tlfnr +","+ k.name +","+ String.join(";",bookArr));
//        }
//        fil.close();
//    }
}
