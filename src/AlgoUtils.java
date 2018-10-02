import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AlgoUtils {

    private static AlgoUtils instance;
    private DecimalFormat decimalFormat;
    public static String CEILING = "CEILING";

    private AlgoUtils() {
        decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
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
}
