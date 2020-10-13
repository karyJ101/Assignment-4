public class LinkList {
    private Node head;
    private int size;

    public void push(String value){
        Node newNode = new Node(value);
        newNode.setNextNode(head);
        head = newNode;
        size++;
    }

    public String peek(){
        if(size <= 0){
            return "null";
        }
        return this.head.getValue();
    }

    public String pop(){
        if(size > 0){
            Node removeNode = head;
            head = head.getNextNode();
            size--;
            removeNode.setNextNode(null);
            return removeNode.getValue();
        }
        return "null";
    }

    public int getSize() {
        return size;
    }
}
