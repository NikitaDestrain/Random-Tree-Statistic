import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Random tree generator can create trees with random count of child for all nodes and collect statistic.
 * <br>
 * It is singleton class.
 *
 * @author Nikita Govokhin
 */
public class RandomTreeGenerator {
    private List<Tree<NodeInfo>> trees;
    private Integer counterR;
    private Statistic statistic;
    private AlgoUtils algoUtils;

    private static RandomTreeGenerator instance;

    private RandomTreeGenerator() {
        algoUtils = AlgoUtils.getInstance();
    }

    public static RandomTreeGenerator getInstance() {
        if (instance == null) instance = new RandomTreeGenerator();
        return instance;
    }

    /**
     * @param m          is max value for random value
     * @param N          is count of nodes which can be contained in Tree
     * @param R          is count of trees which should be created
     * @param isRegular  is type of Tree
     * @param showLogs   is boolean value for switch on/off logs
     * @param showRandom is boolean value for switch on/off random value logs
     * @return List of trees
     * @see Tree
     */
    public List<Tree<NodeInfo>> createTrees(Integer m, Integer N, Integer R, Boolean isRegular, Boolean showLogs, Boolean showRandom) {
        trees = new ArrayList<>();
        statistic = new Statistic(m, N, R);
        counterR = 0;
        Boolean counterCheck = !counterR.equals(R);
        while (counterCheck) {
            if (showLogs) {
                System.out.printf("[BUILD]: Try to create tree number %d\n", counterR);
            }
            Integer name = 0;
            Tree<NodeInfo> tree = new Tree<>();
            Statistic statisticTree = new Statistic(m, N, 1);
            Boolean continueLoop = true;
            Node<NodeInfo> root = new Node<>(new NodeInfo(name, name), null);
            List<Node<NodeInfo>> prevLevel = new ArrayList<>();

            prevLevel.add(root);
            tree.addLevel(prevLevel);
            Boolean badTree = false;
            counterCheck = !counterR.equals(R);
            while (continueLoop) {
                List<Node<NodeInfo>> level = new ArrayList<>();
                for (int k = 0; k < prevLevel.size(); k++) {
                    Integer value;
                    if (!isRegular) {
                        Random random = new Random();
                        value = random.nextInt(m);
                        if (showLogs && showRandom) {
                            System.out.printf("[SUB-INFO]: Random value %d\n", value);
                        }
                    } else {
                        value = m;
                    }
                    Node<NodeInfo> parent = prevLevel.get(k);
                    if (value == 0) {
                        statistic.addCount(value);
                        statisticTree.addCount(value);
                        if (showLogs) {
                            System.out.println("[INFO]: Exit for zero");
                        }
                        if (name + 1 < 10) {
                            badTree = true;
                        }
                        continueLoop = false;
                        break;
                    }
                    statistic.addCount(value);
                    statisticTree.addCount(value);
                    for (int j = 0; j < value; j++) {
                        if (name < N) {
                            Node<NodeInfo> node = new Node<>(new NodeInfo(++name, parent.getData().getName()), parent);
                            parent.addChild(node);
                            level.add(node);
                            if (showLogs) {
                                System.out.printf("[INFO]: Created node {%d, %d}\n", name, parent.getData().getName());
                            }
                        }
                    }
                    if (name + 1 > N) {
                        if (showLogs) {
                            System.out.printf("[INFO]: Tree was created\n");
                        }
                        continueLoop = false;
                        break;
                    }
                }
                if (level.size() != 0) {
                    tree.addLevel(level);
                }
                prevLevel = level;
            }
            if (showLogs) {
                System.out.printf("[INFO]: Tree contains %d nodes\n", name + 1);
            }
            if (!badTree) {
                Double alpha;
                if (!isRegular) {
                    alpha = algoUtils.getAlpha(tree.getNodeCnt(), tree.getLeafCnt());
                } else {
                    alpha = new Double(m - 1) / new Double(m - 2);
                }
                statistic.addAlpha(counterR, alpha);
                statistic.addHeight(counterR, tree.getHeight());
                statistic.addLeafCnt(counterR, tree.getLeafCnt());
                statistic.addNodeCnt(counterR, tree.getNodeCnt());
                statisticTree.addAlpha(counterR, alpha);
                statisticTree.addHeight(counterR, tree.getHeight());
                statisticTree.addLeafCnt(counterR, tree.getLeafCnt());
                statisticTree.addNodeCnt(counterR, tree.getNodeCnt());
                tree.setAlpha(alpha);
                ++counterR;
                if (showLogs) {
                    System.out.printf("[INFO]: Add tree to map\n");
                    System.out.printf("[INFO]: Count trees in map is %d\n", counterR);
                }
                trees.add(tree);
                if (!isRegular) {
                    algoUtils.addStatistic(counterR - 1, statisticTree);
                } else {
                    algoUtils.addStatistic(-1, statisticTree);
                }
                if (showLogs) {
                    System.out.println("[RESULT]: SUCCESS");
                }

                if (counterR.equals(R)) {
                    counterCheck = false;
                    if (showLogs) {
                        System.out.println();
                        System.out.println("[MAIN-RESULT]: All tress created");
                    }
                }
            } else {
                if (showLogs) {
                    System.out.printf("[INFO]: Tree is bad\n");
                    System.out.println("[RESULT]: FAILED");
                }
            }

            if (showLogs) {
                System.out.println();
            }
        }
        return trees;
    }

    public List<Tree<NodeInfo>> getLastTreesBuild() {
        return trees;
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
