package Semana4;

public class Node {
    private Object data;
    private Node nextNode;

    public Node(Object data) {
        this.data = data;
        this.nextNode = null;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    Node getNextNode() {
        return nextNode;
    }
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

}
