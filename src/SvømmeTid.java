import java.util.ArrayList;

public class SvømmeTid implements Comparable<SvømmeTid> {

    String name;
    private Double tid;
    private String diciplin;
    private String datoForTid;

    public SvømmeTid(String name, Double tid, String diciplin, String datoForTid) {
        this.name = name;
        this.tid = tid;
        this.diciplin = diciplin;
        this.datoForTid = datoForTid;
    }
    // Konstruktor for klassen SvømmeTid

    @Override
    public String toString() {
        return name + ", " + diciplin + ", " + tid + ", " + datoForTid;
    }
    //Returnerer en streng, der indeholder navnet på svømmeren (name), svømmedisciplinen (diciplin), svømmetiden i minutter (tid), og datoen for svømmetiden (datoForTid).

    public String toPrint(){
        String min = String.valueOf(Math.round(Math.floor(tid/60)));
        String sek = String.valueOf(tid%60);

        return name+" "+min+":"+sek;
    }
    // Konvertere svømmetiden, der er angivet i sekunder, til et mere almindeligt minuts/sekunder-format og returnerer dette format som en streng.

    @Override
    public int compareTo(SvømmeTid o) {
        double diff = this.tid - o.getTid();

        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        } else {
            return 0;
        }

    }
    // Sammenligner to svømmetider og returnerer en int-værdi, der angiver deres rækkefølge.

    public Double getTid() {
        return tid;
    } //Returnerer værdien af det private felt tid. Det tillader andre klasser at få adgang til værdien uden direkte at manipulere medlemmet.

    public void setTid(Double tid) {
        this.tid = tid;
    } // Tillader andre klasser at ændre værdien af det private felt tid.

}

class KonkurrenceTid extends SvømmeTid{
    int placering;
    String stævneNavn;

    public KonkurrenceTid(String name, Double tid, String diciplin, int placering, String stævneNavn, String datoForTid) {
        super(name, tid, diciplin, datoForTid);
        this.placering=placering;
        this.stævneNavn=stævneNavn;
    }
    //Kalder på konstruktøren i overstående klasse og tilføjer nogle flere informationer til denne klasse

    @Override
    public String toString() {
        return super.toString()+","+placering+","+stævneNavn;
    }

    public String toPrint(){
        return super.toPrint()+", "+placering+". plads, "+stævneNavn;
    }
    // Returnere fællesegenskaber + egenskaber specifikt for KonkurrenceTids klassen
}