package overview;

import java.net.URL;
import java.util.List;

public class TimeWindowChangeList {

    private List<TimeWindowChange> timeWindowChangeList;
    private URL baselineIcon;
    private URL optimizedIcon;

    public List<TimeWindowChange> getTimeWindowChangeList() {
        return timeWindowChangeList;
    }

    public void setTimeWindowChangeList(List<TimeWindowChange> timeWindowChangeList) {
        this.timeWindowChangeList = timeWindowChangeList;
    }

    public URL getBaselineIcon() {
        return baselineIcon;
    }

    public void setBaselineIcon(URL baselineIcon) {
        this.baselineIcon = baselineIcon;
    }

    public URL getOptimizedIcon() {
        return optimizedIcon;
    }

    public void setOptimizedIcon(URL optimizedIcon) {
        this.optimizedIcon = optimizedIcon;
    }
}
