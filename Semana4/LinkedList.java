package Semana4;

public class LinkedList {
    private Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void add(Object o) {
        if (head == null) {
            head = new Node(o);
        } else {
            Node newHead = new Node(o);
            newHead.setNextNode(head);
            head = newHead;
        }
    }

    public String toString(){
        Node iter = head;
        String returnString = "";
        while(iter != null){
            returnString = returnString + " , " + iter.getData();
            iter = iter.getNextNode();
        }
        return returnString;
    }

    public int size(){
        Node iter = head;
        int size = 0;
        while(iter != null){
            size++;
            iter = iter.getNextNode();
        }
        return size;
    }

    public void delete(int index){
        Node iter = head;
        int i = 0;
        while (iter != null){
            if(i+1 == index){
                iter.setNextNode(iter.getNextNode().getNextNode());
                break;
            }else {
                iter = iter.getNextNode();
                i++;
            }
        }
    }
}
