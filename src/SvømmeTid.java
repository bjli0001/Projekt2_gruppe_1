import java.util.ArrayList;

public class SvømmeTid implements Comparable<SvømmeTid> {

    String name;
    private Double tid;


    public SvømmeTid(String name, Double tid) {
        this.name = name;
        this.tid = tid;
    }

    @Override
    public String toString() {
        return name+","+tid;
    }

    public String toPrint(){
        String min = String.valueOf(Math.round(Math.floor(tid/60)));
        String sek = String.valueOf(tid%60);

        return name+" "+min+":"+sek;
    }

    @Override
    public int compareTo(SvømmeTid o) {
        double diff = this.tid - o.getTid();

        if (diff>0) {
        return 1;
        }
        else if (diff<0) { return -1; }
        else {
            return 0;
        }

    }


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
    public Double getTid() {
        return tid;
    }

    public void setTid(Double tid) {
        this.tid = tid;
    }

  /*  public String getDiciplin() {
        return diciplin;
    //}
//
    //public void setDiciplin(String diciplin) {
        this.diciplin = diciplin;
    } */
}

class KonkurrenceTid extends SvømmeTid{
    int placering;
    String stævneNavn;

    public KonkurrenceTid(String name, Double tid, int placering, String stævneNavn) {
        super(name, tid);
        this.placering=placering;
        this.stævneNavn=stævneNavn;
    }

    @Override
    public String toString() {
        return super.toString()+","+placering+","+stævneNavn;
    }

    public String toPrint(){
        return super.toPrint()+", "+placering+". plads, "+stævneNavn;
    }
}