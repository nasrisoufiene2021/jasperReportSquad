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
    private String tieBuffer;
    private String weatherFactor;
    private String ttConst1;
    private String ttConst2;
    private String ttConst3;
    private String ttConst4;

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

    public String getTieBuffer() {
        return tieBuffer;
    }

    public void setTieBuffer(String tieBuffer) {
        this.tieBuffer = tieBuffer;
    }

    public String getWeatherFactor() {
        return weatherFactor;
    }

    public void setWeatherFactor(String weatherFactor) {
        this.weatherFactor = weatherFactor;
    }

    public String getTtConst1() {
        return ttConst1;
    }

    public void setTtConst1(String ttConst1) {
        this.ttConst1 = ttConst1;
    }

    public String getTtConst2() {
        return ttConst2;
    }

    public void setTtConst2(String ttConst2) {
        this.ttConst2 = ttConst2;
    }

    public String getTtConst3() {
        return ttConst3;
    }

    public void setTtConst3(String ttConst3) {
        this.ttConst3 = ttConst3;
    }

    public String getTtConst4() {
        return ttConst4;
    }

    public void setTtConst4(String ttConst4) {
        this.ttConst4 = ttConst4;
    }

    // getter-setter
}