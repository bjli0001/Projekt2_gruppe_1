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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTid() {
        return tid;
    }

    public void setTid(Double tid) {
        this.tid = tid;
    }

    public String getDiciplin() {
        return diciplin;
    }

    public void setDiciplin(String diciplin) {
        this.diciplin = diciplin;
    }
}
