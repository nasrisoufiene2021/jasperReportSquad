package Bound;

import net.sf.jasperreports.engine.JasperReport;

import java.net.URL;
import java.util.List;

public class BoundData {

    private String pol;
    private String pod;
    private String abm;
    private String hourBound;
    private String dayBound;

    private URL headerIcon;
    private List<StartToShift> startToShiftList;

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getAbm() {
        return abm;
    }

    public void setAbm(String abm) {
        this.abm = abm;
    }

    public String getHourBound() {
        return hourBound;
    }

    public void setHourBound(String hourBound) {
        this.hourBound = hourBound;
    }

    public String getDayBound() {
        return dayBound;
    }

    public void setDayBound(String dayBound) {
        this.dayBound = dayBound;
    }

    public List<StartToShift> getStartToShiftList() {
        return startToShiftList;
    }

    public void setStartToShiftList(List<StartToShift> startToShiftList) {
        this.startToShiftList = startToShiftList;
    }

    public URL getHeaderIcon() {
        return headerIcon;
    }

    public void setHeaderIcon(URL headerIcon) {
        this.headerIcon = headerIcon;
    }

    // getter-setter
}