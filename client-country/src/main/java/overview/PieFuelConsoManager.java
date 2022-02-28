package overview;

import java.util.ArrayList;
import java.util.List;

public class PieFuelConsoManager {
    public static List<PieFuelConso> extractFuelConso(){
        PieFuelConso fuelConso = new PieFuelConso();

        fuelConso.setFuelName1("HSFO");
        fuelConso.setBlFuelConso1(1476);
        fuelConso.setOptFuelConso1(194);
        fuelConso.setFuelColor1("#04246a");

        fuelConso.setFuelName2("LSFO");
        fuelConso.setBlFuelConso2(2476);
        fuelConso.setOptFuelConso2(541);
        fuelConso.setFuelColor2("#2863fd");

        fuelConso.setFuelName3("LNG");
        fuelConso.setBlFuelConso3(3245);
        fuelConso.setOptFuelConso3(541);
        fuelConso.setFuelColor3("#f7c731");

        fuelConso.setFuelName4("MGO");
        fuelConso.setBlFuelConso4(4245);
        fuelConso.setOptFuelConso4(541);
        fuelConso.setFuelColor4("#B32EAC");

        fuelConso.setFuelName5("ETHANOL");
        fuelConso.setBlFuelConso5(5245);
        fuelConso.setOptFuelConso5(541);
        fuelConso.setFuelColor5("#3e64b8");

        fuelConso.setFuelName6("DO");
        fuelConso.setBlFuelConso6(1245);
        fuelConso.setOptFuelConso6(541);
        fuelConso.setFuelColor6("#e2014b");

        fuelConso.setFuelName7("METHANOL");
        fuelConso.setBlFuelConso7(1245);
        fuelConso.setOptFuelConso7(541);
        fuelConso.setFuelColor7("#00865d");

        fuelConso.setFuelName8("VLSFO");
        fuelConso.setBlFuelConso8(1245);
        fuelConso.setOptFuelConso8(541);
        fuelConso.setFuelColor8("#ff6c00");

        fuelConso.setFuelConsoTotal(54215125);
        fuelConso.setAnnualizedDiffTotal(54214);
        fuelConso.setDiffCmaVessel(145);

        List<PieFuelConso> fuelConsosList = new ArrayList<>();
        fuelConsosList.add(fuelConso);

        return fuelConsosList;
    }
}
