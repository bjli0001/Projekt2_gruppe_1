public class Main {
    public static void main(String[] args) {
        System.out.println("Hej Delfin");


        Menu.menu(new String[]{"Opret medlem","Rediger medlem","Se resultater","Svømmehold"});
        switch (Menu.op){
           case 1 -> Medlem.opret();
           case 2 -> Medlem.rediger();
           case 3 -> Medlem.resultater();
           case 4 -> Medlem.hold();


    }
}