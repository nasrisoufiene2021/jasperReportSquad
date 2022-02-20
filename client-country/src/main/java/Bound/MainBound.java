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
    //    startToShiftList1.add(s2);
    //    startToShiftList1.add(s3);
     //   startToShiftList1.add(s4);
        boundData1.setStartToShiftList(startToShiftList1);
        boundData1.setTieBuffer("12h");
        boundData1.setWeatherFactor("1,1 knts");
        boundData1.setTtConst1("Lorem");
        boundData1.setTtConst2("ipsum");
        boundData1.setTtConst3("Lorem");
        boundData1.setTtConst4("ipsum");

        BoundData boundData2 = new BoundData();
        boundData2.setPod("GBLGP");
        boundData2.setPol("GBLGPDLGP");
        boundData2.setAbm("B");
        boundData2.setDayBound("Fr");
        boundData2.setHourBound("10:00");
        List<StartToShift> startToShiftList2 = new ArrayList<>();
        startToShiftList2.add(s1);
    //    startToShiftList2.add(s2);
    //    startToShiftList2.add(s3);
    //    startToShiftList2.add(s4);
        boundData2.setStartToShiftList(startToShiftList2);
        boundData2.setTieBuffer("0h");
        boundData2.setWeatherFactor("0 knts");
        boundData2.setTtConst1("Lorem");
        boundData2.setTtConst2("ipsum");
        boundData2.setTtConst3("Lorem");
        boundData2.setTtConst4("ipsum");

        BoundData boundData3 = new BoundData();
        boundData3.setPod("GBLGP");
        boundData2.setPol("GBLGPDLGP");
        boundData3.setAbm("B");
        boundData3.setDayBound("Fr");
        boundData3.setHourBound("10:00");
        List<StartToShift> startToShiftList3 = new ArrayList<>();
   //     startToShiftList3.add(s1);
        startToShiftList3.add(s2);
        startToShiftList3.add(s3);
        startToShiftList3.add(s4);
        boundData3.setStartToShiftList(startToShiftList3);
        boundData3.setTieBuffer("2h");
        boundData3.setWeatherFactor("0 knts");
        boundData3.setTtConst1("Lorem");
        boundData3.setTtConst2("ipsum");
        boundData3.setTtConst3("Lorem");
        boundData3.setTtConst4("ipsum");
        /* Add Items to List */
        List<BoundData> listItems = new ArrayList<BoundData>();

        listItems.add(boundData1);
        listItems.add(boundData2);
        listItems.add(boundData3);

        /* Convert List to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource boundDataSource = new JRBeanCollectionDataSource(listItems);
        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("boundDataSource", boundDataSource);
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

        for(BoundData boundData : listItems) {
            int startToShiftSize = boundData.getStartToShiftList().size();
            int rectHeight = jasperPrint.getPages().get(0).getElements().get(indexRect).getHeight();

            if (startToShiftSize > 2) {
                jasperPrint.getPages().get(0).getElements().get(indexRect).setHeight(rectHeight + 11 * (startToShiftSize - 2));
            }

            jasperPrint.getPages().get(0).getElements().get(indexRect).setY(newPosY);
            rectHeight = jasperPrint.getPages().get(0).getElements().get(indexRect).getHeight();

            shiftOptionCenter(jasperPrint, rectHeight, newPosY, indexRect);
            currentStartCenter(jasperPrint, rectHeight, newPosY, indexRect);
            startShiftCenter(jasperPrint, newPosY, startToShiftSize, indexRect);
            tieBufferCenter(jasperPrint, rectHeight, newPosY, indexRect + startToShiftSize * 3 + 8);
            weatherFactorCenter(jasperPrint, rectHeight, newPosY, indexRect + startToShiftSize * 3 + 8);
            transitTimeConstCenter(jasperPrint, rectHeight, newPosY, indexRect + startToShiftSize * 3 + 8);
            newPosY += rectHeight;
            indexRect += 14 + 3 * startToShiftSize;
        }
    }

    public static void shiftOptionCenter(JasperPrint jasperPrint, int rectHeight, int rectPosY, int indexRect){
        jasperPrint.getPages().get(0).getElements().get(indexRect+1).setY(rectPosY+rectHeight/2-10);
        jasperPrint.getPages().get(0).getElements().get(indexRect+2).setY(rectPosY+rectHeight/2+1);
    }

    public static void currentStartCenter(JasperPrint jasperPrint, int rectHeight, int rectPosY, int indexRect){
        jasperPrint.getPages().get(0).getElements().get(indexRect+3).setY(rectPosY+rectHeight/2-4);
        jasperPrint.getPages().get(0).getElements().get(indexRect+4).setY(rectPosY+rectHeight/2-4);
        jasperPrint.getPages().get(0).getElements().get(indexRect+5).setY(rectPosY+rectHeight/2-4);
        jasperPrint.getPages().get(0).getElements().get(indexRect+6).setY(rectPosY+rectHeight/2-3);
        jasperPrint.getPages().get(0).getElements().get(indexRect+7).setY(rectPosY+rectHeight/2-4);
    }

    public static void startShiftCenter(JasperPrint jasperPrint, int rectPosY, int startToShiftSize, int indexRect){
        if(startToShiftSize == 1){
            jasperPrint.getPages().get(0).getElements().get(indexRect+8).setY(rectPosY+11);
            jasperPrint.getPages().get(0).getElements().get(indexRect+9).setY(rectPosY+12);
            jasperPrint.getPages().get(0).getElements().get(indexRect+10).setY(rectPosY+11);
        }
        else if(startToShiftSize == 2){
            jasperPrint.getPages().get(0).getElements().get(indexRect+8).setY(rectPosY+5);
            jasperPrint.getPages().get(0).getElements().get(indexRect+9).setY(rectPosY+6);
            jasperPrint.getPages().get(0).getElements().get(indexRect+10).setY(rectPosY+5);
            jasperPrint.getPages().get(0).getElements().get(indexRect+11).setY(rectPosY+15);
            jasperPrint.getPages().get(0).getElements().get(indexRect+12).setY(rectPosY+16);
            jasperPrint.getPages().get(0).getElements().get(indexRect+13).setY(rectPosY+15);
        } else if(startToShiftSize > 2) {
            for(int i = 0; i<startToShiftSize ; i++){
                jasperPrint.getPages().get(0).getElements().get(indexRect+i*3+8).setY(rectPosY+5 + i*11);
                jasperPrint.getPages().get(0).getElements().get(indexRect+i*3+9).setY(rectPosY+6 + i*11);
                jasperPrint.getPages().get(0).getElements().get(indexRect+i*3+10).setY(rectPosY+5 + i*11);
            }
        }
    }

    public static void tieBufferCenter(JasperPrint jasperPrint, int rectHeight, int rectPosY, int pos){
        jasperPrint.getPages().get(0).getElements().get(pos).setY(rectPosY+rectHeight/2-4);
    }

    public static void weatherFactorCenter(JasperPrint jasperPrint, int rectHeight, int rectPosY, int pos){
        jasperPrint.getPages().get(0).getElements().get(pos+1).setY(rectPosY+rectHeight/2-4);
    }

    public static void transitTimeConstCenter(JasperPrint jasperPrint, int rectHeight, int rectPosY, int pos){
        jasperPrint.getPages().get(0).getElements().get(pos+2).setY(rectPosY+rectHeight/2-10);
        jasperPrint.getPages().get(0).getElements().get(pos+3).setY(rectPosY+rectHeight/2-10);
        jasperPrint.getPages().get(0).getElements().get(pos+4).setY(rectPosY+rectHeight/2+1);
        jasperPrint.getPages().get(0).getElements().get(pos+5).setY(rectPosY+rectHeight/2+1);
    }

}
