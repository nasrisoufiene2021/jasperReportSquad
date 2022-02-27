package overview;

import java.util.ArrayList;

public class PieFuelConsoList {
    public ArrayList<PieFuelConso> getDataBeanList() {
        ArrayList<PieFuelConso> dataBeanList = new ArrayList<PieFuelConso>();

        dataBeanList.add(produce("DO", 0, null));
        dataBeanList.add(produce("LSFO", 68,"LSFO"));
        dataBeanList.add(produce("LNG", 22, "LNG"));
        dataBeanList.add(produce("Methanol", 22, "Methanol"));
        dataBeanList.add(produce("Ethanol", 42, "Ethanol"));
        dataBeanList.add(produce("VLSFO", 52, "VLSFO"));
        dataBeanList.add(produce("HSFO", 82, "HSFO"));
        dataBeanList.add(produce("bla", 82, "bla"));

        return dataBeanList;
    }

    /*
     * This method returns a DataBean object, with subjectName ,
     * and marks set in it.
     */
    private PieFuelConso produce(String keyFuel, Integer consoFuel, String nameFuel) {
        PieFuelConso dataBean = new PieFuelConso();
        dataBean.setKeyFuel(keyFuel);
        dataBean.setConsoFuel(consoFuel);
        dataBean.setNameFuel(nameFuel);
        dataBean.setColorFuel1("#e2014b");
        return dataBean;
    }
}
