package overview;

import java.util.ArrayList;
import java.util.List;

public class BarConsoManager {
    public static List<BarConso> extractFuelConso(){

        BarConso barConso1 = new BarConso();
        barConso1.setPort("Baseline");
        barConso1.setPortExpression("FRJL");
        barConso1.setPortValue(80);


        BarConso barConso2 = new BarConso();
        barConso2.setPort("Optimized");
        barConso2.setPortExpression("FRJL");
        barConso2.setPortValue(160);

        BarConso barConso3 = new BarConso();
        barConso3.setPort("Baseline");
        barConso3.setPortExpression("BEANR");
        barConso3.setPortValue(130);

        BarConso barConso4 = new BarConso();
        barConso4.setPort("Optimized");
        barConso4.setPortExpression("BEANR");
        barConso4.setPortValue(100);


        BarConso barConso5 = new BarConso();
        barConso5.setPort("Baseline");
        barConso5.setPortExpression("NLRTM");
        barConso5.setPortValue(130);

        BarConso barConso6 = new BarConso();
        barConso6.setPort("Optimized");
        barConso6.setPortExpression("NLRTM");
        barConso6.setPortValue(100);


        BarConso barConso7 = new BarConso();
        barConso7.setPort("Baseline");
        barConso7.setPortExpression("USMIA");
        barConso7.setPortValue(130);

        BarConso barConso8 = new BarConso();
        barConso8.setPort("Optimized");
        barConso8.setPortExpression("USMIA");
        barConso8.setPortValue(100);

        BarConso barConso9 = new BarConso();
        barConso9.setPort("Baseline");
        barConso9.setPortExpression("DEBRV");
        barConso9.setPortValue(130);

        BarConso barConso10 = new BarConso();
        barConso10.setPort("Optimized");
        barConso10.setPortExpression("DEBRV");
        barConso10.setPortValue(100);

        BarConso barConso11 = new BarConso();
        barConso11.setPort("Baseline");
        barConso11.setPortExpression("USCH5");
        barConso11.setPortValue(130);

        BarConso barConso12 = new BarConso();
        barConso12.setPort("Optimized");
        barConso12.setPortExpression("USCH5");
        barConso12.setPortValue(100);

        List<BarConso> barConsoList = new ArrayList<>();
        barConsoList.add(barConso1);
        barConsoList.add(barConso2);
        barConsoList.add(barConso3);
        barConsoList.add(barConso4);
        barConsoList.add(barConso5);
        barConsoList.add(barConso6);
        barConsoList.add(barConso7);
        barConsoList.add(barConso8);
        barConsoList.add(barConso9);
        barConsoList.add(barConso10);
        barConsoList.add(barConso11);
        barConsoList.add(barConso12);


        return barConsoList;
    }

    public static List<String> extractBufferHourData(){
        List<String> bufferHourList = new ArrayList<>();
        bufferHourList.add("WB");
        bufferHourList.add("EB");
        bufferHourList.add("300 / 14%");
        bufferHourList.add("280 / 12%");
        bufferHourList.add("280 / 12%");
        bufferHourList.add("310 / 14%");
        bufferHourList.add("-20H / -2%");
        bufferHourList.add("310 / 14%");
        return bufferHourList;
    }
}
