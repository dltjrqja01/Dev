import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    static Stack<String> myStack = new Stack<String>();
    static Queue<String> tempQueue = new LinkedList<String>();
    static String buffer[];
    static int Curbuffer;
    static boolean ERROR;

    public static void main(String args[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String input = br.readLine();
                if (input.compareTo("q") == 0)
                    break;

                command(input);
            } catch (Exception e) {
                System.out.println("ERROR");
                System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
            }
        }
    }

    private static void command(String input) {
        tempQueue.clear();
        myStack.clear();
        int left_parenthesis = 0, right_parenthesis = 0;
        int idx = 1, iax = 0;
        boolean numStack = false;
        ERROR = false;
        Curbuffer = 0;
        input = input.trim();
        for (int f = 0; f < input.length(); f++) {
            input = input.replaceAll(" ", " ");

        }
        input = input.replaceAll(" ", "n");
        input = input.replaceAll("\t", "n");
        for (int f = 0; f < input.length(); f++) {
            input = input.replaceAll("nn", "n");

        }


        for (int e = 1; e < input.length(); e++) { // 공백처리
            if (((input.charAt(e) + "").equals("n")) && (isNum(input.charAt(e - 1) + "")) && (isNum(input.charAt(e + 1) + ""))) {
                System.out.println("1");
                ERROR = true;
            }
        }
        input = input.replace("n", "");

        for (int i = 0; i < input.length(); i++) { // 빈괄호 처리
            if (input.charAt(i) == '(') {
                left_parenthesis++;
                if (i < input.length() - 1 && input.charAt(i + 1) == ')')
                    System.out.println("2");
                    ERROR = true;
            }
            if (input.charAt(i) == ')')
                right_parenthesis++;
        }
        if (left_parenthesis != right_parenthesis)
            System.out.println("3");
            ERROR = true;
        String[] temp = input.split("");
        String numtemp = ""; // input 자리의 자릿수를 고려하기 위해

        while (idx < temp.length) {
            if (!isNum(temp[idx]) & numtemp.length() != 1 & !numStack) {
                tempQueue.offer(temp[idx]);
                numStack = false;
            } else if (!isNum(temp[idx]) & numStack) {
                tempQueue.offer(numtemp); // 문자이고, 그전에 숫자 배열이 있는 경우
                tempQueue.offer(temp[idx]);
                iax++;
                numtemp = "";
                numStack = false;
            } else if (isNum(temp[idx]) & idx == temp.length - 1) { // 숫자이고,
                numtemp = numtemp + temp[idx]; // 마지막
                tempQueue.offer(numtemp); // 문자인
            } else if (isNum(temp[idx])) { // 숫자인 경우
                numtemp = numtemp + temp[idx];
                numStack = true;
                iax--;
            }
            idx++;
            iax++;
        }

        String[] inputToArray = new String[iax];
        buffer = new String[iax];
        idx = 0;

        while (tempQueue.peek() != null) {
            inputToArray[idx] = tempQueue.poll();
            idx++;
        }
        inputToArray = unaryCheck(inputToArray);
        if (ERROR) {
            System.out.println("4");
            System.out.println("ERROR");
            return;
        }
        for (int i = 0; i < inputToArray.length; i++) {
            Read(inputToArray[i]);
        }
        while (!myStack.empty()) {
            buffer[Curbuffer] = myStack.pop();
            Curbuffer += 1;
        }
        long result = Calculate(buffer);
        if (ERROR)
            return;
        for (int i = 0; i < Curbuffer - 1; i++) {
            System.out.print(buffer[i] + " ");
        }
        System.out.println(buffer[Curbuffer - 1]);
        System.out.println(result);
    }

    private static String[] unaryCheck(String[] input) {

        for (int i = 0; i < input.length; i++) {
            if (!isNum(input[i]) & !((input[i].equals("+")) | (input[i].equals("-")) | (input[i].equals("/")) | (input[i].equals("*")) | (input[i].equals("(")) | (input[i].equals(")")) | (input[i].equals("%")) | (input[i].equals("^")))) {
                System.out.println("5");
                ERROR = true;
                return input;
            }
        }
        if (input[0].equals("-"))
            input[0] = "~";
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("-") & !isNum(input[i - 1]) & !input[i - 1].equals(")"))
                input[i] = "~";
        }
        return input;
    }

    private static boolean isNum(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static int opPriority(String input) {
        switch (input) {
            case "^":
                return 4;
            case "~":
                return 3;
            case "*":
            case "/":
            case "%":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    private static void Read(String input) {

        if (isNum(input)) {
            buffer[Curbuffer] = input;
            Curbuffer++;
        }
        else if (input.equals(")")) { // 우괄호인 경우, 좌괄호 등장할 때까지 pop
            while (myStack.peek().compareTo("(") != 0) {
                buffer[Curbuffer] = myStack.pop();
                Curbuffer++;
            }
            myStack.pop();
        }
        else { // 새로 들어온 연산자의 우선순위가 스택에 있는 것 보다 낮거나 같은 경우, pop 해준다
            if (!myStack.empty() && !input.equals("(") & opPriority(myStack.peek()) >= opPriority(input) & opPriority(input) < 3) {
                while (!myStack.empty() && opPriority(myStack.peek()) >= opPriority(input) & !myStack.peek().equals("(")) {
                    buffer[Curbuffer] = myStack.pop();
                    Curbuffer++;
                }
            }
            myStack.push(input); // 새로들어온 연산자를 스택에 push
        }
    }
    private static long Calculate(String[] buffer) {
        int idx = 0;
        long temp1, result, temp2 = 0;
        while (idx < buffer.length) {

            if (buffer[idx] == null)
                break;

            if (isNum(buffer[idx])) {
                myStack.push(buffer[idx]);
                idx += 1;
            } else {
                if (buffer[idx].equals("~")) {
                    temp1 = Long.parseLong(myStack.pop());
                } else {
                    temp1 = Long.parseLong(myStack.pop());
                    temp2 = Long.parseLong(myStack.pop());
                }

                switch (buffer[idx]) {
                    case "~":
                        myStack.push((temp1 * -1) + "");
                        break;
                    case "+":
                        myStack.push(Long.toString(temp2 + temp1));
                        break;
                    case "-":
                        myStack.push(Long.toString(temp2 - temp1));
                        break;
                    case "*":
                        myStack.push(Long.toString(temp2 * temp1));
                        break;
                    case "%":
                        myStack.push(Long.toString(temp2 % temp1));
                        break;
                    case "^":
                        if (temp1 < 0 & temp2 == 0) {
                            ERROR = true;
                            System.out.println("6");
                            System.out.println("ERROR");
                            return 0;
                        }
                        myStack.push(Long.toString((long) Math.pow(temp2, temp1)));
                        break;
                    case "/":
                        myStack.push(Long.toString((long) (temp2 / temp1)));
                        break;
                    default:
                        break;
                }
                idx++;
            }
        }
        result = Long.parseLong(myStack.pop());
        if (myStack.empty())
            return result;
        else {
            System.out.println("7");
            System.out.println("ERROR");
            ERROR = true;
            return 0;
        }
    }
}