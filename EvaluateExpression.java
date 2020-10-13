import java.util.LinkedList;

public class EvaluateExpression {
    private LinkList stack;

    public EvaluateExpression(){
        stack = new LinkList();
    }

    public void push(String String){
        stack.push(String);
    }

    public String pop(){
        return stack.pop();
    }

    public String peek(){
        return stack.peek();
    }

    public int size(){
        return stack.getSize();
    }
}
