import Bound.BoundData;
import Bound.BoundDataSource;
import Bound.StartToShift;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String args[])  //static method
    {
        try{
            String fileName = "C:\\Temp\\test1.pdf";
            testExportTable();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void testExport() throws JRException, IOException {

        String systemPath = "jasperReports" + File.separator;
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "clients-example.jrxml");
        JasperReport masterReport = JasperCompileManager.compileReport(classPathResource.getInputStream());



        // Client list
        List<Client> clientList = new ArrayList<>();
        Client client1 = new Client(1,"soufiene","nasri");
        Client client2 = new Client(2,"houssem","iheb");
        Client client3 = new Client(3,"zizouna","hodhli");
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        JRBeanCollectionDataSource clientDS = new JRBeanCollectionDataSource(clientList);
        ClassPathResource classPathResource2 = new ClassPathResource(systemPath + "client-list.jrxml");
        JasperReport clientSubreport = JasperCompileManager.compileReport(classPathResource2.getInputStream());

        classPathResource = new ClassPathResource(systemPath + "overviewSubreport.jrxml");
        JasperReport startToShiftSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());


        // Client Country List
        List<ClientByCountry> clientByCountryList = new ArrayList<>();
        ClientByCountry clientByCountry1 = new ClientByCountry("fdsfsd",1);
        ClientByCountry clientByCountry2 = new ClientByCountry("q   az  a",5);
        ClientByCountry clientByCountry3 = new ClientByCountry("poipoi",7);


        clientByCountryList.add(clientByCountry1);
        clientByCountryList.add(clientByCountry2);
        clientByCountryList.add(clientByCountry3);
        JRBeanCollectionDataSource clientByCountryDS = new JRBeanCollectionDataSource(clientByCountryList);
        ClassPathResource classPathResource3 = new ClassPathResource(systemPath + "client-list.jrxml");
        JasperReport clientCountrySubreport = JasperCompileManager.compileReport(classPathResource3.getInputStream());

        // ... add them to the parameters Map object
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("SUBREPORT_DIR", "/");
        parameters.put("clientSubreport", clientSubreport);
        parameters.put("CLIENT_DS", clientDS);
        parameters.put("clientCountrySubreport", clientCountrySubreport);
        parameters.put("BY_COUNTRY_DS", clientByCountryDS);

        List<CountryDataset> countryDatasetsList = new ArrayList<>();
        List<String> s = new ArrayList<>();
        s.add("hfghfdghfghfg");
        s.add("mlsdfglmsd");
        CountryDataset countryDataset1 = new CountryDataset();
        countryDataset1.setPol("dfsfdd");
        countryDataset1.setPolList(s);
        CountryDataset countryDataset2 = new CountryDataset();
        countryDataset2.setPol("dfsfdd");
        countryDataset2.setPolList(s);
        countryDatasetsList.add(countryDataset1);
        countryDatasetsList.add(countryDataset2);
        parameters.put("countryDatasetsList", countryDatasetsList);
        parameters.put("startToShiftSubreport", startToShiftSubreport);

        String fileName = "C:\\Temp\\noa-final.pdf";
        JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(fileName));
    }

    public static void testExportTable() throws JRException, IOException {

        String systemPath = "jasperReports" + File.separator;
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "MasterTable.jrxml");
        JasperReport masteTableReport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        classPathResource = new ClassPathResource(systemPath + "boundSubreport.jrxml");
        JasperReport boundSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        classPathResource = new ClassPathResource(systemPath + "overviewSubreport.jrxml");
        JasperReport startToShiftSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());
        List<BoundData> listItems = new ArrayList<BoundData>();

        /* Create Items */
        BoundData boundData1 = new BoundData();
        boundData1.setPod("NLRTM");
        boundData1.setPol("NLRTMDUMI");
        boundData1.setAbm("A");
        boundData1.setDayBound("Mo");
        boundData1.setHourBound("17:00");
   //     boundData1.setStartToShiftSubreport(startToShiftSubreport);

        StartToShift s1 = new StartToShift();
        s1.setDays("Mo, Tu, We, Th, Fr, Sa");
        s1.setHour("11:30");
        StartToShift s2 = new StartToShift();
        s2.setDays("Mo, We, Fr");
        s2.setHour("09:00");
        StartToShift s3 = new StartToShift();
        s3.setDays("All days");
        s3.setHour("14:00");
        StartToShift s4 = new StartToShift();
        s4.setDays("Mo, Tu, We, Th, Fr, Sa");
        s4.setHour("11:30");
        List<StartToShift> startToShiftList = new ArrayList<>();
        boundData1.setStartToShiftList(startToShiftList);

        BoundData boundData2 = new BoundData();
        boundData2.setPod("GBLGP");
        boundData2.setPol("GBLGPDLGP");
        boundData2.setAbm("B");
        boundData2.setDayBound("Fr");
        boundData2.setHourBound("10:00");
        boundData2.setStartToShiftList(startToShiftList);
    //    boundData2.setStartToShiftSubreport(startToShiftSubreport);

        /* Add Items to List */
        listItems.add(boundData1);
        listItems.add(boundData2);

        List<BoundDataSource> datasource = new ArrayList<>();
        BoundDataSource listItem = new BoundDataSource();
        listItem.setBoundDataSource(listItems);

        datasource.add(listItem);

        /* Convert List to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(datasource);
        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("boundDataSource", itemsJRBean);
        parameters.put("boundSubreport", boundSubreport);
        parameters.put("boundSubreport", boundSubreport);
   //     parameters.put("startToShiftSubreport", startToShiftSubreport);

        String fileName = "C:\\Temp\\noa-final.pdf";
        JasperPrint jasperPrint = JasperFillManager.fillReport(masteTableReport, parameters, new JREmptyDataSource());
        jasperPrint.getPages().get(0).getElements().get(0).setHeight(150);
        JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(fileName));
    }
}
