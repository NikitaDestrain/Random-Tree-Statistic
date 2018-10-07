import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class which run algorithm for tree creation.
 * <br>
 * Process statistic and write it to file.
 *
 * @author Nikita Govokhin
 */
public class Main {

    private final static String STATISTIC_FILE_NAME = "output/result/statisticinfo.txt";
    private final static String STATISTIC_FILE_NAME_2 = "output/result/statisticinfo2.txt";
    private final static String TREE_FILE_NAME = "output/result/treeinfo.txt";
    private final static String fileSign = "\nAuthor: Nikita Govokhin, 6412";

    public static void main(String[] args) {
        Integer m = 5;
        Integer N = 200;
        Integer R = 400;
        Boolean showLogs = false;
        Boolean showRandom = false;
        Boolean showTrees = false;

        RandomTreeGenerator randomTreeGenerator = RandomTreeGenerator.getInstance();
        List<Tree<NodeInfo>> trees = randomTreeGenerator.createTrees(m, N, R + 1, false, showLogs, showRandom);
        Statistic statistic = randomTreeGenerator.getStatistic();
        Tree<NodeInfo> regularTree = randomTreeGenerator.createTrees(m - 1, N, 1, true, showLogs, showRandom).get(0);
        Statistic regularStatistic = randomTreeGenerator.getStatistic();
        AlgoUtils algoUtils = AlgoUtils.getInstance();

        if (showTrees) {
            System.out.println("Tree information");
            System.out.printf("Count of trees: %d\n", trees.size());
            for (int i = 0; i < trees.size(); i++) {
                System.out.println("Random tree");
                System.out.println(trees.get(i).toString());
            }
            System.out.println();
            System.out.println("Regular tree");
            System.out.println(regularTree.toString());
        }

        System.out.println();
        if (statistic != null && regularStatistic != null) {
            System.out.println("All");
            System.out.println(statistic);
            System.out.println("Regular");
            System.out.println(regularStatistic);
        } else {
            System.out.println("[ERROR]: Statistic was not created! See logs.");
        }


        try (PrintWriter pw = new PrintWriter(new File(STATISTIC_FILE_NAME));) {
            pw.write("Result:\n");
            pw.write("\nRandom\n" + statistic.toString());
            pw.write("\n\nRegular\n" + regularStatistic.toString());
            pw.write("\n\n");
            pw.write(fileSign);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Tree<NodeInfo> highestTree = trees.get(0);
        Integer number = 0;
        try (PrintWriter pw = new PrintWriter(new File(TREE_FILE_NAME));) {
            pw.write("Tree:\n");
            for (int i = 1; i < trees.size(); i++) {
                if (highestTree.getNodeCnt() < trees.get(i).getNodeCnt()) {
                    highestTree = trees.get(i);
                    number = i;
                }
            }
            pw.write(highestTree.toString());
            pw.write("\n" + algoUtils.getStatistic(number));
            pw.write("\n");
            pw.write("\nRegular:\n" + regularTree);
            pw.write("\n" + algoUtils.getStatistic(-1));
            pw.write("\n");
            pw.write("\n\n");
            pw.write(fileSign);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter pw = new PrintWriter(new File(STATISTIC_FILE_NAME_2));) {
            pw.write("400 trees statistic:\n");
            pw.write("NodeCnt:\n");
            for (int i = 1; i < trees.size(); i++) {
                pw.write(statistic.getNodeCnt(i) + "\n");
            }
            pw.write("LeafCnt:\n");
            for (int i = 1; i < trees.size(); i++) {
                pw.write(statistic.getLeafCnt(i) + "\n");
            }
            pw.write("Height:\n");
            for (int i = 1; i < trees.size(); i++) {
                pw.write(statistic.getHeight(i) + "\n");
            }
            pw.write("Alpha:\n");
            for (int i = 1; i < trees.size(); i++) {
                pw.write(statistic.getAlpha(i) + "\n");
            }
            pw.write("\nAvg node cnt: " + statistic.getAverageNodeCnt());
            pw.write("\nAvg leaf cnt: " + statistic.getAverageLeafCnt());
            pw.write("\nAvg height: " + statistic.getAverageHeight());
            pw.write("\nVariance alpha: " + statistic.getVarianceAlpha());
            pw.write("\nAvg alpha: " + statistic.getAverageAlpha() + "\n");
            pw.write(fileSign);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Double avg = algoUtils.getStatistic(number).getAverage();
        if ((avg > 2.75) || (avg < 2.25)) {
            System.out.println("[INFO]: Average is outside allowable limit!");
        }
        Double alpha = algoUtils.getStatistic(number).getAlpha(number);
        Double regualarAlpha = algoUtils.getStatistic(-1).getAlpha(0);
        if ((alpha < regualarAlpha)) {
            System.out.println("[INFO]: Program contains error!");
        }
    }
}
