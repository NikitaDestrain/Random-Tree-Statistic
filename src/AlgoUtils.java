import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class AlgoUtils {

    private static AlgoUtils instance;
    private DecimalFormat decimalFormat;
    public static String CEILING = "CEILING";
    public Map<Integer, Statistic> statisticMap;

    private AlgoUtils() {
        decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        statisticMap = new HashMap<>();
    }

    public static AlgoUtils getInstance() {
        if (instance == null) instance = new AlgoUtils();
        return instance;
    }

    public String getStringAlpha(Integer nodeCnt, Integer leafCnt) {
        return decimalFormat.format(new Double(nodeCnt) / new Double(leafCnt));
    }

    public Double getAlpha(Integer nodeCnt, Integer leafCnt) {
        return new Double(nodeCnt) / new Double(leafCnt);
    }

    public Double getRegularAlpha(Integer m) {
        return new Double(m - 1) / new Double(m - 2);
    }

    public void setRoundingMode(String mode) {
        switch (mode) {
            case "CEILING":
                decimalFormat.setRoundingMode(RoundingMode.CEILING);
                break;
            default:
                System.out.println("ERROR: Unknown mode");
                break;
        }
    }

    public void addStatistic(Integer key, Statistic statistic) {
        statisticMap.putIfAbsent(key, statistic);
    }

    public Statistic getStatistic(Integer key) {
        return statisticMap.get(key);
    }
}
