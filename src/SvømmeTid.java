import java.util.ArrayList;

public class SvømmeTid {

    private String name;
    private String diciplin;
    private Double tid;

    public SvømmeTid(String name, Double tid, String diciplin) {
        this.name = name;
        this.diciplin = diciplin;
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiciplin() {
        return diciplin;
    }

    public void setDiciplin(String diciplin) {
        this.diciplin = diciplin;
    }

    public Double getTid() {
        return tid;
    }

    public void setTid(Double tid) {
        this.tid = tid;
    }
}
