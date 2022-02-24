package overview;

import java.net.URL;

public class OverviewCiiRank {
    private String baselineRank;
    private String optimizedRank;
    private Integer baselinePercentage;
    private Integer optimizedPercentage;
    private String baselineColor;
    private String optimizedColor;
    private URL triangleIcon;

    public String getBaselineRank() {
        return baselineRank;
    }

    public void setBaselineRank(String baselineRank) {
        this.baselineRank = baselineRank;
    }

    public String getOptimizedRank() {
        return optimizedRank;
    }

    public void setOptimizedRank(String optimizedRank) {
        this.optimizedRank = optimizedRank;
    }

    public Integer getBaselinePercentage() {
        return baselinePercentage;
    }

    public void setBaselinePercentage(Integer baselinePercentage) {
        this.baselinePercentage = baselinePercentage;
    }

    public Integer getOptimizedPercentage() {
        return optimizedPercentage;
    }

    public void setOptimizedPercentage(Integer optimizedPercentage) {
        this.optimizedPercentage = optimizedPercentage;
    }

    public String getBaselineColor() {
        return baselineColor;
    }

    public void setBaselineColor(String baselineColor) {
        this.baselineColor = baselineColor;
    }

    public String getOptimizedColor() {
        return optimizedColor;
    }

    public void setOptimizedColor(String optimizedColor) {
        this.optimizedColor = optimizedColor;
    }

    public URL getTriangleIcon() {
        return triangleIcon;
    }

    public void setTriangleIcon(URL triangleIcon) {
        this.triangleIcon = triangleIcon;
    }
}
