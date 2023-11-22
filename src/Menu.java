import java.util.InputMismatchException;
import java.util.Scanner;
class Hej {
s
}
public class Menu {
    static int op;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

    }
    static void menu(String[] list){
        for(int i=0; i<list.length; i++){

            System.out.println("Tast "+(i+1)+": "+list[i]);

        }
        System.out.println("Vælg menupunkt [1,"+list.length+"]:");
        op = inInt(list.length);
    }

    static int inInt(int size) {

        boolean OK=false;
        int num=0;
        do {
            try {
                num= input.nextInt();
                input.nextLine();
                if (num<=size) {
                    OK = true;
                }
                else {
                    System.out.println("Ulovligt input. Prøv igen");
                    input.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ulovligt input. Prøv igen");
                input.nextLine();
            }
        } while(!OK);
        return num;
    }

//    static String inTlf() {
//        boolean cont = true;
//        String tlf = "";
//        while (cont) {
//            System.out.println("Indtast telefon nr.");
//            tlf = input.nextLine();
//
//            if (tlf.length() == 8) {
//                try {
//                    Integer.parseInt(tlf);
//                    cont = false;
//                } catch (Exception e) {
//                    System.out.println("Ugyldigt telefon nr.");
//                }
//            } else System.out.println("Ugyldigt telefon nr.");
//        }
//        return tlf;
//    }
}
