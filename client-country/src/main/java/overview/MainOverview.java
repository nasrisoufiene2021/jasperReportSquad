package overview;

import Bound.MainBound;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainOverview {


    public static void main(String args[])  //static method
    {
        try{
            String fileName = "C:\\Temp\\test1.pdf";
            testExportTable();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void testExportTable() throws JRException, IOException {

        String systemPath = "jasperReports" + File.separator;
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "overview/overviewMasterReport.jrxml");
        JasperReport masteTableReport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        classPathResource = new ClassPathResource(systemPath + "overview/overviewSubreport.jrxml");
        JasperReport overviewSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        JRBeanCollectionDataSource overviewDataSource = new JRBeanCollectionDataSource(exctractOverviewDataSource());
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("overviewDataSource", overviewDataSource);
        parameters.put("overviewSubreport", overviewSubreport);

        String fileName = "C:\\Temp\\noa-final.pdf";
        JasperPrint jasperPrint = JasperFillManager.fillReport(masteTableReport, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(fileName));
    }

    private static List<Overview> exctractOverviewDataSource(){
        List<Overview> listOverview = new ArrayList<>();
        Overview overview = new Overview();
        overview.setTotalSaving(35.25f);
        overview.setBwChange(5);
        loadIcons(overview);
        listOverview.add(overview);
        return listOverview;
    }

    private static void loadIcons(Overview overview){
        URL icon = MainBound.class.getClassLoader().getResource( "icon/overviewIcon.png");
        overview.setOverviewIcon(icon);

        icon = MainBound.class.getClassLoader().getResource( "icon/totalSavingIcon.png");
        overview.setTotalSavingIcon(icon);

        icon = MainBound.class.getClassLoader().getResource( "icon/bwChangeIcon.png");
        overview.setBwChangeIcon(icon);

        icon = MainBound.class.getClassLoader().getResource( "icon/vesselIcon.png");
        overview.setVesselIcon(icon);

        icon = MainBound.class.getClassLoader().getResource( "icon/emissionIcon.png");
        overview.setEmissionIcon(icon);

        icon = MainBound.class.getClassLoader().getResource( "icon/rotationIcon.png");
        overview.setRotationIcon(icon);
    }

}
