package overview;

import java.util.ArrayList;
import java.util.List;

public class BarConsoManager {
    public static List<BarConso> extractFuelConso(){

        BarConso barConso1 = new BarConso();
        barConso1.setBlOrOpt("Baseline");
        barConso1.setPortCode("FRJL");
        barConso1.setConso(80);
        barConso1.setSpeed(0);


        BarConso barConso2 = new BarConso();
        barConso2.setBlOrOpt("Optimized");
        barConso2.setPortCode("FRJL");
        barConso2.setConso(160);
        barConso2.setSpeed(540);

        BarConso barConso3 = new BarConso();
        barConso3.setBlOrOpt("Baseline");
        barConso3.setPortCode("BEANR");
        barConso3.setConso(130);
        barConso3.setSpeed(330);

        BarConso barConso4 = new BarConso();
        barConso4.setBlOrOpt("Optimized");
        barConso4.setPortCode("BEANR");
        barConso4.setConso(100);
        barConso4.setSpeed(50);


        BarConso barConso5 = new BarConso();
        barConso5.setBlOrOpt("Baseline");
        barConso5.setPortCode("NLRTM");
        barConso5.setConso(530);
        barConso5.setSpeed(260);

        BarConso barConso6 = new BarConso();
        barConso6.setBlOrOpt("Optimized");
        barConso6.setPortCode("NLRTM");
        barConso6.setConso(330);
        barConso6.setSpeed(480);


        BarConso barConso7 = new BarConso();
        barConso7.setBlOrOpt("Baseline");
        barConso7.setPortCode("USMIA");
        barConso7.setConso(1000);
        barConso7.setSpeed(650);

        BarConso barConso8 = new BarConso();
        barConso8.setBlOrOpt("Optimized");
        barConso8.setPortCode("USMIA");
        barConso8.setConso(760);
        barConso8.setSpeed(30);

        BarConso barConso9 = new BarConso();
        barConso9.setBlOrOpt("Baseline");
        barConso9.setPortCode("DEBRV");
        barConso9.setConso(130);
        barConso9.setSpeed(1230);

        BarConso barConso10 = new BarConso();
        barConso10.setBlOrOpt("Optimized");
        barConso10.setPortCode("DEBRV");
        barConso10.setConso(10);

        BarConso barConso11 = new BarConso();
        barConso11.setBlOrOpt("Baseline");
        barConso11.setPortCode("USCH5");
        barConso11.setConso(930);
        barConso11.setSpeed(908);

        BarConso barConso12 = new BarConso();
        barConso12.setBlOrOpt("Optimized");
        barConso12.setPortCode("USCH5");
        barConso12.setConso(309);
        barConso12.setSpeed(507);

        BarConso barConsoFictive = new BarConso();
        barConsoFictive.setBlOrOpt("");
        barConsoFictive.setPortCode("");
        barConsoFictive.setConso(0);
        barConsoFictive.setSpeed(0);

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
        barConsoList.add(barConsoFictive);


        return barConsoList;
    }
}
