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
    private Map<Integer, Double> alphaInfo;
    private Map<Integer, Integer> heightInfo;
    private Map<Integer, Integer> nodeCntInfo;
    private Map<Integer, Integer> leafCntInfo;
    private Integer m;
    private Integer N;
    private Integer R;

    public Statistic(Integer m, Integer N, Integer R) {
        this.gistoInfo = new HashMap<>();
        this.alphaInfo = new HashMap<>();
        this.heightInfo = new HashMap<>();
        this.nodeCntInfo = new HashMap<>();
        this.leafCntInfo = new HashMap<>();
        this.m = m;
        this.N = N;
        this.R = R;
    }

    public Statistic(Map<Integer, Integer> gistoInfo, Map<Integer, Double> alphaInfo, Map<Integer, Integer> heightInfo,
                     Map<Integer, Integer> nodeCntInfo, Map<Integer, Integer> leafCntInfo, Integer m, Integer N, Integer R) {
        this.gistoInfo = gistoInfo;
        this.alphaInfo = alphaInfo;
        this.heightInfo = heightInfo;
        this.nodeCntInfo = nodeCntInfo;
        this.leafCntInfo = leafCntInfo;
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
        System.out.println("Average random value: " + df.format(getAverage()));
        System.out.println("Average alpha value: " + df.format(getAverageAlpha()));
        System.out.println("------------------------------------------");
    }

    public void addAlpha(Integer key, Double alpha) {
        if (alphaInfo.get(key) == null) {
            alphaInfo.put(key, alpha);
        } else {
            alphaInfo.replace(key, alphaInfo.get(key), alpha);
        }
    }

    public Double getAlpha(Integer key) {
        return alphaInfo.get(key);
    }

    public void addHeight(Integer key, Integer height) {
        if (heightInfo.get(key) == null) {
            heightInfo.put(key, height);
        } else {
            heightInfo.replace(key, heightInfo.get(key), height);
        }
    }

    public Integer getHeight(Integer key) {
        return heightInfo.get(key);
    }

    public void addNodeCnt(Integer key, Integer nodeCnt) {
        if (nodeCntInfo.get(key) == null) {
            nodeCntInfo.put(key, nodeCnt);
        } else {
            nodeCntInfo.replace(key, nodeCntInfo.get(key), nodeCnt);
        }
    }

    public Integer getNodeCnt(Integer key) {
        return nodeCntInfo.get(key);
    }


    public void addLeafCnt(Integer key, Integer leafCnt) {
        if (leafCntInfo.get(key) == null) {
            leafCntInfo.put(key, leafCnt);
        } else {
            leafCntInfo.replace(key, leafCntInfo.get(key), leafCnt);
        }
    }

    public Integer getLeafCnt(Integer key) {
        return leafCntInfo.get(key);
    }

    public Double getAverageAlpha() {
        Double res = 0.0;
        Integer cnt = 0;
        for (Double a : alphaInfo.values()) {
            res += a;
            ++cnt;
        }
        return res / cnt;
    }

    public Double getVarianceAlpha(){
        Double res = 0.0;
        Integer cnt = 0;
        Double avg = getAverageAlpha();
        Double sqAvg = avg*avg;
        for (Double a: alphaInfo.values()) {
            res+=(a*a - sqAvg);
            ++cnt;
        }
        return res / cnt;
    }

    public Double getAverageLeafCnt() {
        Double res = 0.0;
        Integer cnt = 0;
        for (Integer a : leafCntInfo.values()) {
            res += a;
            ++cnt;
        }
        return res / cnt;
    }

    public Double getAverageNodeCnt() {
        Double res = 0.0;
        Integer cnt = 0;
        for (Integer a : nodeCntInfo.values()) {
            res += a;
            ++cnt;
        }
        return res / cnt;
    }

    public Double getAverageHeight() {
        Double res = 0.0;
        Integer cnt = 0;
        for (Integer a : heightInfo.values()) {
            res += a;
            ++cnt;
        }
        return res / cnt;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        DecimalFormat df = new DecimalFormat("#.####");
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
        sb.append("Average random value: " + df.format(getAverage()) + "\n");
        sb.append("Average alpha value: " + df.format(getAverageAlpha()) + "\n");
        sb.append("Variance alpha value: " + df.format(getVarianceAlpha()) + "\n");
        sb.append("Average height value: " + df.format(getAverageHeight()) + "\n");
        sb.append("Average nodeCnt value: " + df.format(getAverageNodeCnt()) + "\n");
        sb.append("Average leafCnt value: " + df.format(getAverageLeafCnt()) + "\n");
        sb.append("------------------------------------------");
        return sb.toString();
    }
}
