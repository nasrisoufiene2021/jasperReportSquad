import net.sf.jasperreports.engine.JasperReport;

public class ClientByCountry {

    private String country;
    private int total;
    private JasperReport startToShiftSubreport;

    public ClientByCountry(String country, int total) {
        this.country = country;
        this.total = total;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public JasperReport getStartToShiftSubreport() {
        return startToShiftSubreport;
    }

    public void setStartToShiftSubreport(JasperReport startToShiftSubreport) {
        this.startToShiftSubreport = startToShiftSubreport;
    }
}