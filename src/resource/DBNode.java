package resource;


public abstract class DBNode {

    private String name;
    private DBNode parent;

    public DBNode(String name, DBNode parent) {
        this.name = name;
        this.parent = parent;
    }


    public String getName() {
        return name;
    }

    public DBNode getParent() {
        return parent;
    }
}
