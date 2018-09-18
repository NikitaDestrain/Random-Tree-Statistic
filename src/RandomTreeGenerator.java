import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTreeGenerator {
    private List<Tree<NodeInfo>> trees;
    private Integer counterR;
    private Statistic statistic;

    private static RandomTreeGenerator instance;

    private RandomTreeGenerator() {
    }

    public static RandomTreeGenerator getInstance() {
        if (instance == null) instance = new RandomTreeGenerator();
        return instance;
    }

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
                    for (int j = 0; j < value; j++) {
                        Node<NodeInfo> node = new Node<>(new NodeInfo(++name, parent.getData().getName()), parent);
                        parent.addChild(node);
                        level.add(node);
                        if (showLogs) {
                            System.out.printf("[INFO]: Created node {%d, %d}\n", name, parent.getData().getName());
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
                ++counterR;
                if (showLogs) {
                    System.out.printf("[INFO]: Add tree to map\n");
                    System.out.printf("[INFO]: Count trees in map is %d\n", counterR);
                }
                trees.add(tree);
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