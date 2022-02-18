package Bound;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRTemplatePrintRectangle;
import net.sf.jasperreports.engine.fill.JRTemplatePrintText;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;


public class MainBound {

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
        ClassPathResource classPathResource = new ClassPathResource(systemPath + "Bound/MasterTable.jrxml");
        JasperReport masteTableReport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        classPathResource = new ClassPathResource(systemPath + "Bound/BoundSubreport.jrxml");
        JasperReport boundSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        classPathResource = new ClassPathResource(systemPath + "Bound/startToShiftSubreport.jrxml");
        JasperReport startToShiftSubreport = JasperCompileManager.compileReport(classPathResource.getInputStream());

        List<BoundData> listItems = new ArrayList<BoundData>();

        /* Create Items */
        BoundData boundData1 = new BoundData();
        boundData1.setPod("NLRTM");
        boundData1.setPol("NLRTMDUMI");
        boundData1.setAbm("A");
        boundData1.setDayBound("Mo");
        boundData1.setHourBound("17:00");

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
        List<StartToShift> startToShiftList1 = new ArrayList<>();
        startToShiftList1.add(s1);
        startToShiftList1.add(s2);
    //    startToShiftList1.add(s3);
    //    startToShiftList1.add(s4);
        boundData1.setStartToShiftList(startToShiftList1);

        BoundData boundData2 = new BoundData();
        boundData2.setPod("GBLGP");
        boundData2.setPol("GBLGPDLGP");
        boundData2.setAbm("B");
        boundData2.setDayBound("Fr");
        boundData2.setHourBound("10:00");
        List<StartToShift> startToShiftList2 = new ArrayList<>();
        startToShiftList2.add(s1);
        startToShiftList2.add(s2);
        startToShiftList2.add(s3);
        //    startToShiftList.add(s4);
        boundData2.setStartToShiftList(startToShiftList2);

        /* Add Items to List */
        listItems.add(boundData1);
        listItems.add(boundData2);

        /* Convert List to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);
        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("boundDataSource", itemsJRBean);
        parameters.put("boundSubreport", boundSubreport);
        parameters.put("startToShiftSubreport", startToShiftSubreport);
        URL headerIcon = MainBound.class.getClassLoader().getResource(File.separator+"icon" + File.separator+ "hourIcon.png");
        parameters.put("headerIcon", headerIcon);

        String fileName = "C:\\Temp\\noa-final.pdf";
        JasperPrint jasperPrint = JasperFillManager.fillReport(masteTableReport, parameters, new JREmptyDataSource());

        fixPosition(jasperPrint,0, listItems);

        JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(fileName));
    }

    static public void fixPosition(JasperPrint jasperPrint, int positionOfRect, List<BoundData> listItems){

        int indexRect = 6;
        int newPosY = jasperPrint.getPages().get(0).getElements().get(indexRect).getY();
        int rectHeight = jasperPrint.getPages().get(0).getElements().get(indexRect).getHeight();

        for(BoundData boundData : listItems){
            int startToShiftSize = boundData.getStartToShiftList().size();
            int rectPosY = jasperPrint.getPages().get(0).getElements().get(indexRect).getY();

            if(startToShiftSize>2) {
                jasperPrint.getPages().get(0).getElements().get(indexRect).setHeight(rectHeight + 11 * (startToShiftSize - 2));
                jasperPrint.getPages().get(0).getElements().get(indexRect).setY(newPosY);
            }

            rectHeight = jasperPrint.getPages().get(0).getElements().get(indexRect).getHeight();
            startShiftCenter(jasperPrint, newPosY, startToShiftSize, indexRect);
            currentStartCenter(jasperPrint, rectHeight, newPosY, indexRect);
            shiftOptionCenter(jasperPrint, rectHeight, newPosY, indexRect);
            newPosY += rectHeight;
            indexRect += 14;
        }
        /*
        int maxNb = listItems.get(0).getStartToShiftList().size();
        int rectHeight = jasperPrint.getPages().get(0).getElements().get(6).getHeight();
        int rectPosY = jasperPrint.getPages().get(0).getElements().get(6).getY();

        if(maxNb>2)
            jasperPrint.getPages().get(0).getElements().get(6).setHeight(rectHeight+11*(maxNb-2));
        else{
            startShiftCenter(jasperPrint, rectHeight, rectPosY, maxNb);
        }
        shiftOptionCenter(jasperPrint, rectHeight, rectPosY);
        currentStartCenter(jasperPrint, rectHeight, rectPosY);


        jasperPrint.getPages().get(0).getElements().get(6).setHeight(200);
        JRPrintElement jrPrintElement = new JRTemplatePrintRectangle();
        jrPrintElement.setX(200);
        jrPrintElement.setY(200);
        jrPrintElement.setHeight(200);
        jrPrintElement.setWidth(200);
        UUID uuid = new UUID(125412l,122121l);
        jrPrintElement.setUUID(uuid);
        jrPrintElement.setForecolor(new Color(255, 0, 0));
        jasperPrint.getPages().get(0).getElements().add(jrPrintElement);
        jasperPrint.getPages().get(0).getElements().get(6).setX(170);
        jasperPrint.getPages().get(0).getElements().get(9).setHeight(200);

         */
    }

    public static void shiftOptionCenter(JasperPrint jasperPrint, int rectHeight, int rectPosY, int indexRect){
        jasperPrint.getPages().get(0).getElements().get(indexRect+1).setY(rectPosY+rectHeight/2-10);
        jasperPrint.getPages().get(0).getElements().get(indexRect+2).setY(rectPosY+rectHeight/2+1);
    }

    public static void currentStartCenter(JasperPrint jasperPrint, int rectHeight, int rectPosY, int indexRect){
        jasperPrint.getPages().get(0).getElements().get(indexRect+3).setY(rectPosY+rectHeight/2-4);
        jasperPrint.getPages().get(0).getElements().get(indexRect+4).setY(rectPosY+rectHeight/2-4);
        jasperPrint.getPages().get(0).getElements().get(indexRect+5).setY(rectPosY+rectHeight/2-4);
        jasperPrint.getPages().get(0).getElements().get(indexRect+6).setY(rectPosY+rectHeight/2-4);
        jasperPrint.getPages().get(0).getElements().get(indexRect+7).setY(rectPosY+rectHeight/2-4);
    }

    public static void startShiftCenter(JasperPrint jasperPrint, int rectPosY, int startToShiftSize, int indexRect){
        if(startToShiftSize == 1){
            jasperPrint.getPages().get(0).getElements().get(indexRect+8).setY(rectPosY+11);
            jasperPrint.getPages().get(0).getElements().get(indexRect+9).setY(rectPosY+11);
            jasperPrint.getPages().get(0).getElements().get(indexRect+10).setY(rectPosY+11);
        }
        if(startToShiftSize == 2){
            jasperPrint.getPages().get(0).getElements().get(indexRect+8).setY(rectPosY+5);
            jasperPrint.getPages().get(0).getElements().get(indexRect+9).setY(rectPosY+5);
            jasperPrint.getPages().get(0).getElements().get(indexRect+10).setY(rectPosY+5);
            jasperPrint.getPages().get(0).getElements().get(indexRect+11).setY(rectPosY+15);
            jasperPrint.getPages().get(0).getElements().get(indexRect+12).setY(rectPosY+15);
            jasperPrint.getPages().get(0).getElements().get(indexRect+13).setY(rectPosY+15);
        } else {
            for(int i = 0; i<startToShiftSize ; i++){
                jasperPrint.getPages().get(0).getElements().get(indexRect+i*3+8).setY(rectPosY+5 + i*10);
                jasperPrint.getPages().get(0).getElements().get(indexRect+i*3+9).setY(rectPosY+5 + i*10);
                jasperPrint.getPages().get(0).getElements().get(indexRect+i*3+10).setY(rectPosY+5 + i*10);
            }
        }
    }


}
