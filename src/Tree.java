import java.util.ArrayList;
import java.util.List;

/**
 * Class for tree.
 * Contains information about levels and etc.
 * Levlels contains lists with Nodes
 *
 * @param <T>
 * @author Nikita Govokhin
 * @see Node
 */
public class Tree<T> {
    private Integer height = 0;
    private Integer nodeCnt = 0;
    private List<List<Node<T>>> levels;

    public Tree() {
        this.levels = new ArrayList<>();
    }

    public Tree(List<List<Node<T>>> levels) {
        this.levels = levels;
    }

    public void addLevel(List<Node<T>> level) {
        levels.add(level);
        ++height;
        nodeCnt += level.size();
    }

    public List<Node<T>> getLevel(Integer index) {
        return levels.get(index);
    }

    public Integer getLeafCnt() {
        Integer cnt = 0;
        for (List<Node<T>> level : levels) {
            for (Node node : level) {
                if (node.isLeaf()) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        System.out.println("-----------------------------------------------");
        System.out.println("Height: " + height);
        System.out.println("Node count: " + nodeCnt);
        System.out.println("Leaf count: " + getLeafCnt());
        for (int i = 0; i < levels.size(); i++) {
            sb.append(i + ": ");
            for (Node<T> node : levels.get(i)) {
                sb.append(node.toString() + " ");
            }
            sb.append("\n");
        }
        System.out.println("-----------------------------------------------");
        return sb.toString();
    }
}
