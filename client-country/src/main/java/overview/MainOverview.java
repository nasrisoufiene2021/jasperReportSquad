package overview;

import Bound.MainBound;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
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
        ClassPathResource classPathResource2 = new ClassPathResource(systemPath + "overview/overviewMasterReport2.jrxml");
        JasperReport masteTableReport = JasperCompileManager.compileReport(classPathResource.getInputStream());
        JasperReport masteTableReport2 = JasperCompileManager.compileReport(classPathResource2.getInputStream());

        classPathResource = new ClassPathResource(systemPath + "overview/overviewSubreport.jrxml");
        JasperReport overviewSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        JRBeanCollectionDataSource overviewDataSource = new JRBeanCollectionDataSource(exctractOverviewDataSource());
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("overviewDataSource", overviewDataSource);
        parameters.put("overviewSubreport", overviewSubreport);


        Map<String, Object> parameters2 = new HashMap<String, Object>();
        parameters2.put("overviewDataSource", overviewDataSource);
        parameters2.put("overviewSubreport", overviewSubreport);

   //     prepareOverviewCiiRankSUbreport(parameters);
   //     prepareTimeWindowChangeSubreport(parameters);
   //     preparePieBunkerConsoSubreport(parameters);
   //     prepareFuelConsoSubreport(parameters);
  //      prepareBarBunkerConsoSubreport(parameters);

        String fileName = "C:\\Temp\\noa-final.pdf";

        List<BarConso> barBunkerConsoList = BarConsoManager.extractFuelConso();
        JRBeanCollectionDataSource barBunkerConsoDataSource = new JRBeanCollectionDataSource(barBunkerConsoList);

        JasperFillManager jasperFillManager1 = JasperFillManager.getInstance(new SimpleJasperReportsContext());
        JasperFillManager jasperFillManager2 = JasperFillManager.getInstance(new SimpleJasperReportsContext());

        JasperPrint jasperPrint2 = jasperFillManager1.fill(masteTableReport2, parameters, new JREmptyDataSource());

        JasperPrint jasperPrint1 = jasperFillManager2.fill(masteTableReport, parameters2, new JREmptyDataSource());


        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        jasperPrintList.add(jasperPrint1);
        jasperPrintList.add(jasperPrint2);

   //     jasperPrint = multipageLinking(jasperPrint, jasperPrint2);


        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList)); //Set as export input my list with JasperPrint s
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName)); //or any other out streaam
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCreatingBatchModeBookmarks(true); //add this so your bookmarks work, you may set other parameters
        exporter.setConfiguration(configuration);

        exporter.exportReport();
    //    JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(fileName));
    }


    static void prepareJasperPrint1(){

    }


    static void prepareJasperPrint2(){

    }

    private static JasperPrint multipageLinking(JasperPrint page1, JasperPrint page2) {
        List pages = page2.getPages();
        for (int count = 0; count < pages.size(); count++) {
            page1.addPage((JRPrintPage) pages.get(count));
        }

        return page1;
    }


    private static void prepareBarBunkerConsoSubreport(Map<String, Object> parameters) throws IOException, JRException {
        String systemPath = "jasperReports" + File.separator;
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "overview/barBunkerConsoSubreport.jrxml");
        JasperReport barBunkerConsoSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        List<BarConso> barBunkerConsoList = BarConsoManager.extractFuelConso();
        JRBeanCollectionDataSource barBunkerConsoDataSource = new JRBeanCollectionDataSource(barBunkerConsoList);
        JRBeanCollectionDataSource barBunkerConsoDataSource2 = new JRBeanCollectionDataSource(barBunkerConsoList);

        parameters.put("barBunkerConsoDataSource", barBunkerConsoDataSource);
        parameters.put("barBunkerConsoDataSource2", barBunkerConsoDataSource2);
        parameters.put("barBunkerConsoSubreport", barBunkerConsoSubreport);


        String consoTotal1 = "WB: +100 t";
        String avgSpeed1 = "+0.2 kts";
        String consoTotal2 = "WB: +100 t";
        String avgSpeed2 = "+0.2 kts";

        parameters.put("consoTotal1", consoTotal1);
        parameters.put("avgSpeed1", avgSpeed1);
        parameters.put("consoTotal2", consoTotal2);
        parameters.put("avgSpeed2", avgSpeed2);



    }

    private static void preparePieBunkerConsoSubreport(Map<String, Object> parameters) throws IOException, JRException {
        String systemPath = "jasperReports" + File.separator;
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "overview/pieBunkerConsoSubreport.jrxml");
        JasperReport pieBunkerConsoSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        List<PieFuelConso> pieFuelConsoList = PieFuelConsoManager.extractFuelConso();
        JRBeanCollectionDataSource pieBunkerConsoDataSource = new JRBeanCollectionDataSource(pieFuelConsoList);

        parameters.put("pieBunkerConsoDataSource", pieBunkerConsoDataSource);
        parameters.put("pieBunkerConsoSubreport", pieBunkerConsoSubreport);
    }

    private static void prepareFuelConsoSubreport(Map<String, Object> parameters) throws IOException, JRException {
        String systemPath = "jasperReports" + File.separator;
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "overview/fuelConsoSubreport.jrxml");
        JasperReport fuelConsoSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        parameters.put("fuelConsoDataSource", new JRBeanCollectionDataSource(PieFuelConsoManager.extractFuelConso()));
        parameters.put("fuelConsoSubreport", fuelConsoSubreport);
    }


    private static void prepareOverviewCiiRankSUbreport(Map<String, Object> parameters) throws IOException, JRException {
        String systemPath = "jasperReports" + File.separator;
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "overview/overviewCiiRankSubreport.jrxml");
        JasperReport overviewCiiRankSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        JRBeanCollectionDataSource overviewCiiRankDataSource = new JRBeanCollectionDataSource(exctractOverviewCiiRankDataSource());
        parameters.put("overviewCiiRankDataSource", overviewCiiRankDataSource);
        parameters.put("overviewCiiRankSubreport", overviewCiiRankSubreport);
    }

    private static void prepareTimeWindowChangeSubreport(Map<String, Object> parameters) throws IOException, JRException {
        String systemPath = "jasperReports" + File.separator;
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "overview/timeWindowChangeSubreport.jrxml");
        JasperReport timeWindowSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        JRBeanCollectionDataSource timeWindowDataSource = new JRBeanCollectionDataSource(exctractTimeWindowDataSource());
        parameters.put("timeWindowDataSource", timeWindowDataSource);
        parameters.put("timeWindowSubreport", timeWindowSubreport);
    }

    public static List<TimeWindowChangeList> exctractTimeWindowDataSource(){

        List<TimeWindowChange> timeWindowChangeList = new ArrayList<>();

        for(int i = 0; i<70; i++){
            TimeWindowChange timeWindowChange = new TimeWindowChange();

            timeWindowChange.setBound("B");
            timeWindowChange.setPort("HKHKG");
            timeWindowChange.setBlBerthRotationDay("D003");
            timeWindowChange.setBlBerthRotationTime("Sar 04:00");
            timeWindowChange.setLbUnberthRotationDay("D004");
            timeWindowChange.setBlUnberthRotationTime("Mon 18:20");
            timeWindowChange.setOptBerthRotationDay("D-01");
            timeWindowChange.setOptBerthRotationTime("Wen 04:00");
            timeWindowChange.setOptUnberthRotationDay("D002");
            timeWindowChange.setOptUnberthRotationTime("Fri 22:00");

            timeWindowChange.setOptBerthBackColor("#d5f6eb");
            timeWindowChange.setOptBerthForeColor("#327864");

            timeWindowChange.setOptUnberthBackColor("#fff0db");
            timeWindowChange.setOptUnberthForeColor("#d2740f");

            timeWindowChangeList.add(timeWindowChange);
        }

        TimeWindowChangeList itemList = new TimeWindowChangeList();
        itemList.setTimeWindowChangeList(timeWindowChangeList);

        List<TimeWindowChangeList> dataSourcetList = new ArrayList<>();
        dataSourcetList.add(itemList);

        return dataSourcetList;
    }

    public static List<OverviewCiiRank> exctractOverviewCiiRankDataSource(){
        List<OverviewCiiRank> overviewCiiRankList = new ArrayList<>();
        OverviewCiiRank overviewCiiRank = new OverviewCiiRank();
        overviewCiiRank.setBaselineRank("C");
        overviewCiiRank.setOptimizedRank("B");
        overviewCiiRank.setBaselinePercentage(78);
        overviewCiiRank.setOptimizedPercentage(50);
        overviewCiiRank.setBaselineColor("#98bf30");
        overviewCiiRank.setOptimizedColor("#eee522");
        overviewCiiRank.setTriangleIcon(loadIcon("triangleIcon.png"));
        overviewCiiRankList.add(overviewCiiRank);
        return overviewCiiRankList;
    }

    private static List<Overview> exctractOverviewDataSource(){
        List<Overview> listOverview = new ArrayList<>();
        Overview overview = new Overview();
        overview.setTotalSaving(35.25f);
        overview.setBwChange(5);
        overview.setVessel("BUXCLIFF");
        overview.setTotalEmission(2145214);
        overview.setRotationDuration("Curent - 7 days");
        overview.setOtherRotationDuration(42);
        overview.setFrequence("weekly");
        overview.setVessels(6);
        overview.setPortOfCall(10);
        overview.setCmaCgm(6);
        overview.setVesselClass(5100);
        overview.setServiceType("OA-EW");
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

    public static URL loadIcon(String iconName){
        String iconPath = "icon" + File.separator;
        return MainOverview.class.getClassLoader().getResource( iconPath + iconName);
    }

    public static void printUUID(JasperPrint jasperPrint){
        for(JRPrintPage page :  jasperPrint.getPages()){
            for(JRPrintElement element : page.getElements()){
                System.out.println("==> "+element.getUUID());
            }
        }
    }
}
