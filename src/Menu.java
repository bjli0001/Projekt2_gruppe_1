import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static int op;
    static Scanner input = new Scanner(System.in);

    static void menu(String[] list){
        for(int i=0; i<list.length; i++){

            System.out.println("Tast "+(i+1)+": "+list[i]);

        }
        System.out.println("Vælg menupunkt [1,"+list.length+"]:");
        op = inInt(list.length);
    }

    //Viser en given liste af muligheder og lader brugeren vælge en af dem.

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

    //En inputmetode, der bruges til at læse et heltal fra brugeren. Hænger sammen med 'Menu'- metoden
}
