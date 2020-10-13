import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Please Enter Your Expression");
            String expression = input.nextLine();
            System.out.println(evaluate(expression));
        }
        catch (Exception ex){
            System.out.println("Invalid Entry! This expression cannot be evaluated.");
        }

    }

    public static String evaluate(String expression){
        EvaluateExpression operators = new EvaluateExpression(); // Used to hold operators " ( + - * / ) "
        EvaluateExpression operands  = new EvaluateExpression(); // Used to hold operands
        expression = expression.replace(" ", ""); // remove any spaces found
        StringBuilder multiDigit = new StringBuilder(); // used for extracting numbers made or more digits

        for(int i = 0; i < expression.length(); i++){
            // checks for numbers
            if(Character.isDigit(expression.charAt(i))){
               if(i == expression.length() - 1){ // Checks if i is positioned at last index of string
                   multiDigit.append(expression.charAt(i)); //extract character and append to builder
                   operands.push(multiDigit.toString()); // directly push builder to stack
                   multiDigit.delete(0, multiDigit.length()); // Resets string builder once pushed to stack
               }
               else{ // if i is not at last index
                   multiDigit.append(expression.charAt(i)); // only append to builder
               }
            }

            else { // If character found is not digit then check builder length
                if(multiDigit.length() > 0){ // Only push builder if a value is found
                    operands.push(multiDigit.toString());
                    multiDigit.delete(0, multiDigit.length());
                }
            }

            //if + or -, evaluate operator at top of stack against top two values in operand stack
            // push resulting value into operand stack then push next extracted operator into operator stack
            if(expression.charAt(i) == '+'|| expression.charAt(i) == '-'){
                // Ensure top of stack is neither null nor an open parenthesis
                if (!operators.peek().equals("null") && !operators.peek().equals("(")) {
                    operands.push(processValues(operators, operands));
                    operators.push(expression.charAt(i) + "");
                }
                else{ // if top of stack is null or open parenthesis then push
                    operators.push(expression.charAt(i) + "");
                }
            }

            // If extracted char is * or /, check for * or / in top of stack
            // If found, process operator against top two values in operand stack
            // then push new value into operand stack
            // else if * or / not found in stack, push extracted operator to the top of operator stack
            else if(expression.charAt(i) == '*'|| expression.charAt(i) == '/' ){
                if((operators.peek().equals("*") || operators.peek().equals("/"))){
                    operands.push(processValues(operators,operands));
                    operators.push(expression.charAt(i) + "");
                }
                else {
                    operators.push(expression.charAt(i) + "");
                }
            }

            // If '(' found push directly to operator stack
            else if(expression.charAt(i) == '('){
                operators.push(expression.charAt(i) + "");
            }

            // if ')' found, process all operators against operand stack values until '(' is found
            // operands will be processed two at a time
            else if(expression.charAt(i) == ')'){
                //operators.push(expression.charAt(i) + "");
                while (!operators.peek().equals("(")){
                    operands.push(processValues(operators,operands));
                }
            }

            //if end of string is reached, then process the top 2 operands and top operator
            if(i >= expression.length() - 1){

                operands.push(processValues(operators,operands));
            }
        }
        // Check if operators stack is empty
        // if not, process stacks until operator stack is empty
        if(operators.size() >= 1){
            while(operators.size() > 0){
                if(operators.size() == 1 && operators.peek().equals("(")){
                    operators.pop();
                    break;
                }
                else{
                    operands.push(processValues(operators,operands));
                }
            }
        }
        // Return final result in operand stack
        return operands.peek();
    }

    public static String processValues(EvaluateExpression operators, EvaluateExpression operands){
        // first two values in operand stack are popped
        // Order is swapped to properly evaluate values left to right

        if(operators.peek().equals("(")){ // Ensure Open parenthesis are being removed
            operators.pop();
        }
        int firstNumber = Integer.parseInt(operands.pop());
        int secondNumber = Integer.parseInt(operands.pop());
        String symbol = operators.pop();
        int result = 0;
        // Evaluates extracted symbol and performs respective operation
        switch (symbol){
            case "+": result = secondNumber + firstNumber;
                break;

            case "-": result = secondNumber - firstNumber;
                break;

            case "*": result = secondNumber * firstNumber;
                break;

            case "/": result = secondNumber / firstNumber;
                break;
        }
        return result + "";
    }

}
