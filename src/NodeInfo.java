public class NodeInfo {
    private Integer name;
    private Integer parentName;

    public NodeInfo(Integer name, Integer parentName) {
        this.name = name;
        this.parentName = parentName;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getParentName() {
        return parentName;
    }

    public void setParentName(Integer parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "{" + name + ":" + parentName + "}";
    }
}
