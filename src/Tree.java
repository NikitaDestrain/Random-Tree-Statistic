import java.util.ArrayList;
import java.util.List;

/**
 * Class for tree.
 * <br>
 * Contains information about levels and etc.
 * <br>
 * Levlels contains lists with Nodes
 *
 * @param <T> type of value for Node
 * @author Nikita Govokhin
 * @see Node
 */
public class Tree<T> {
    private Integer height = 0;
    private Integer nodeCnt = 0;
    private Double alpha = 0.0;
    private List<List<Node<T>>> levels;

    public Tree() {
        this.levels = new ArrayList<>();
    }

    public Tree(List<List<Node<T>>> levels) {
        this.levels = levels;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Integer getNodeCnt() {
        return nodeCnt;
    }

    public Integer getHeight() {
        return height;
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
        sb.append("-----------------------------------------------\n");
        sb.append("Height: " + height + "\n");
        sb.append("Node count: " + nodeCnt + "\n");
        sb.append("Leaf count: " + getLeafCnt() + "\n");
        sb.append("Alpha: " + alpha + "\n");
        for (int i = 0; i < levels.size(); i++) {
            sb.append(i + ": ");
            for (Node<T> node : levels.get(i)) {
                sb.append(node.toString() + " ");
            }
            sb.append("\n");
        }
        sb.append("-----------------------------------------------");
        return sb.toString();
    }
}
