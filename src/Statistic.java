import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for statistic.
 *
 * @author Nikita Govokhin
 */
public class Statistic {
    private Map<Integer, Integer> gistoInfo;
    private Integer m;
    private Integer N;
    private Integer R;

    public Statistic(Integer m, Integer N, Integer R) {
        this.gistoInfo = new HashMap<>();
        this.m = m;
        this.N = N;
        this.R = R;
    }

    public Statistic(Map<Integer, Integer> gistoInfo, Integer m, Integer N, Integer R) {
        this.gistoInfo = gistoInfo;
        this.m = m;
        this.N = N;
        this.R = R;
    }

    public void addCount(Integer key) {
        if (gistoInfo.get(key) == null) {
            gistoInfo.put(key, 1);
        } else {
            Integer value = gistoInfo.get(key);
            gistoInfo.replace(key, gistoInfo.get(key), ++value);
        }
    }

    public void reduceCount(Integer key) {
        if (gistoInfo.get(key) != null) {
            Integer value = gistoInfo.get(key);
            gistoInfo.replace(key, gistoInfo.get(key), --value);
        }
    }

    public Double getAverage() {
        Double res = 0.0;
        Integer cnt = 0;
        for (Integer key : gistoInfo.keySet()) {
            res += key * gistoInfo.get(key);
            cnt += gistoInfo.get(key);
        }
        return res / cnt;
    }

    public void printStatistic() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        System.out.println("Statistic information");
        System.out.println("------------------------------------------");
        for (Integer key : gistoInfo.keySet()) {
            System.out.println(key + ": " + gistoInfo.get(key));
        }
        System.out.println("Average: " + df.format(getAverage()));
        System.out.println("------------------------------------------");
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        sb.append("Statistic information\n");
        sb.append("------------------------------------------\n");
        sb.append("Parameters:");
        sb.append("\nm = " + m);
        sb.append("\nN = " + N);
        sb.append("\nR = " + R);
        sb.append("\n----------------\n");
        sb.append("\nStatistic of values:\n");
        for (Integer key : gistoInfo.keySet()) {
            sb.append(key + ": " + gistoInfo.get(key) + "\n");
        }
        sb.append("Average: " + df.format(getAverage()) + "\n");
        sb.append("------------------------------------------");
        return sb.toString();
    }
}
