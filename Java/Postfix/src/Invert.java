public class Invert extends Stack {
    char[] invert = new char[100];
    int index = 0;

    public Invert() { }

    public void change(char[] infix){
        int length = infix.length;
        for(int i = 0; i<length;i++){
            if(infix[i] == '('){
                push(infix[i]);
            }

            else if(infix[i] == ')'){
                while(top() != '(') {
                    invert[index++] = ' ';
                    invert[index++] = pop();
                }
                pop();
            }

            else if(infix[i] >= '0'&&infix[i] <= '9'){
                invert[index++] = infix[i];
            }

            else if(infix[i] == ' '){
                continue;
            }

            else if(infix[i] == '*'||infix[i] == '/'){
                invert[index++] = ' ';
                if(top() == '*'||top() == '/'){
                    invert[index++] = pop();
                   invert[index++] = ' ';
                    push(infix[i]);
                }
                else {
                    push(infix[i]);
                }
            }
            else if(infix[i] == '+'|| infix[i] == '-'){
                invert[index++] = ' ';
                if(top() == '*' || top() == '/'){
                    invert[index++] = pop();
                    invert[index++] = ' ';
                    push(infix[i]);
                }
                else {
                    push(infix[i]);
                }
            }
        }
        while(isEmpty() != true){
            invert[index++] = ' ';
            invert[index++] = pop();
        }
    }

    void invertPrint(){
        for(int i = 0;i<index;i++){
            System.out.print(invert[i]);
        }
        System.out.println();
    }
}
