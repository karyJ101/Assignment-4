public class Node {
    private String value;
    private Node nextNode;

    public Node(String value){
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public String getValue(){
        return this.value;
    }
}
