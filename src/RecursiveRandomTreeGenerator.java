import java.util.*;

public class RecursiveRandomTreeGenerator {

    private Random random;
    private List<Tree> trees;
    private static RecursiveRandomTreeGenerator instance;
    private Integer countNode;
    private Integer m;

    private RecursiveRandomTreeGenerator() {
        countNode = 0;
        random = new Random();
        trees = new ArrayList<>();
    }

    public static RecursiveRandomTreeGenerator getInstance() {
        if (instance == null) instance = new RecursiveRandomTreeGenerator();
        return instance;
    }

    public List getLastBuild() {
        return Collections.unmodifiableList(trees);
    }

    private Boolean treeStatus;
    //todo do exit condition
    private void doAction(Integer nodeName, Node<NodeInfo> node) {
        Integer parentName = node.getData().getName();
        Boolean continueLoop = true;
        while (continueLoop) {
            Integer value = random.nextInt(m);

            if (value == 0) break;

            Node newNode = new Node(new NodeInfo(++nodeName, parentName));
            node.addChild(newNode);
            doAction(nodeName, newNode);
        }
    }

    public List getRandomTrees(Integer m, Integer N, Integer R) {
        trees = new ArrayList<>();
        this.m = m;

        Integer treeCnt = 0;
        while (!treeCnt.equals(R)) {
            countNode = 0;
            treeStatus = true;
            Tree tree = new Tree();
            Node root = new Node(new NodeInfo(countNode, 0));

            doAction(countNode, root);

        }

        return Collections.unmodifiableList(trees);
    }
}
