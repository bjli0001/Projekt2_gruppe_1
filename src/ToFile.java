import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ToFile {

    public static void saveList(ArrayList<String[]> dataLines,String s) throws IOException {
        FileWriter fil = new FileWriter(s);
        PrintWriter ud = new PrintWriter(fil);

        for (String[] sa: dataLines){

            ud.println(String.join(",",sa));
        }
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
