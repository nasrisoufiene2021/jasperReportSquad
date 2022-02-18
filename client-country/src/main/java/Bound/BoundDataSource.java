package Bound;

import Bound.BoundData;

import java.util.ArrayList;
import java.util.List;

public class BoundDataSource {

    List<BoundData> boundDataSource = new ArrayList<BoundData>();

    public List<BoundData> getBoundDataSource() {
        return boundDataSource;
    }

    public void setBoundDataSource(List<BoundData> boundDataSource) {
        this.boundDataSource = boundDataSource;
    }
}
