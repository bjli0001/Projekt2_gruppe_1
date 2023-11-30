import java.util.ArrayList;

public class SvømmeTid implements Comparable<SvømmeTid> {

    private String name;
    private Double tid;
    private String diciplin;
    private String datoForTid;

    public SvømmeTid(String name, Double tid, String diciplin, String datoForTid) {
        this.name = name;
        this.tid = tid;
        this.diciplin = diciplin;
        this.datoForTid = datoForTid;
    }

    @Override
    public String toString() {
        return name + ", " + diciplin + ", " + tid + ", " + datoForTid;
    }


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

}

  /*  public String getDiciplin() {
        return diciplin;
    //}
//
//    public void setDiciplin(String diciplin) {
//        this.diciplin = diciplin;
//    }
}*/

class KonkurrenceTid extends SvømmeTid{
    int placering;
    String stævneNavn;

    public KonkurrenceTid(String name, Double tid, String diciplin, int placering, String stævneNavn, String datoForTid) {
        super(name, tid, diciplin, datoForTid);
        this.placering=placering;
        this.stævneNavn=stævneNavn;
    }

    @Override
    public String toString() {
        return super.toString()+", "+placering+", "+stævneNavn;
    }
}
