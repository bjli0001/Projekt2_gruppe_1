import java.util.ArrayList;

public class SvømmeTid {

    private String name;
    private Double tid;
    private String diciplin;

    public SvømmeTid(String name, Double tid, String diciplin) {
        this.name = name;
        this.tid = tid;
        this.diciplin = diciplin;
    }

    @Override
    public String toString() {
        return name + ", " + diciplin+ ", " + tid;
    }



//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Double getTid() {
//        return tid;
//    }
//
//    public void setTid(Double tid) {
//        this.tid = tid;
//    }
//
//    public String getDiciplin() {
//        return diciplin;
//    }
//
//    public void setDiciplin(String diciplin) {
//        this.diciplin = diciplin;
//    }
}

class KonkurrenceTid extends SvømmeTid{
    int placering;
    String stævneNavn;

    public KonkurrenceTid(String name, Double tid, String diciplin, int placering, String stævneNavn) {
        super(name, tid, diciplin);
        this.placering=placering;
        this.stævneNavn=stævneNavn;
    }

    @Override
    public String toString() {
        return super.toString()+", "+placering+", "+stævneNavn;
    }
}