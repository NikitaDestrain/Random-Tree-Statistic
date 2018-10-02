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
    private final static String TREE_FILE_NAME = "output/result/treeinfo.txt";
    private final static String fileSign = "\nAuthor: Nikita Govokhin, 6412";

    public static void main(String[] args) {
        Integer m = 4;
        Integer N = 200;
        Integer R = 400;
        Boolean showLogs = true;
        Boolean showRandom = false;
        Boolean showTrees = false;

        RandomTreeGenerator randomTreeGenerator = RandomTreeGenerator.getInstance();
        List<Tree<NodeInfo>> trees = randomTreeGenerator.createTrees(m, N, R, false, showLogs, showRandom);
        Statistic statistic = randomTreeGenerator.getStatistic();
        Tree<NodeInfo> regularTree = randomTreeGenerator.createTrees(m, N, 1, true, showLogs, showRandom).get(0);
        Statistic regularStatistic = randomTreeGenerator.getStatistic();

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
        if (statistic != null) {
            System.out.println("All");
            statistic.printStatistic();
            System.out.println("Regular");
            regularStatistic.printStatistic();
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
        try (PrintWriter pw = new PrintWriter(new File(TREE_FILE_NAME));) {
            pw.write("Tree:\n");
            pw.write(trees.get(2).toString());
            pw.write("\n\n");
            pw.write(fileSign);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
