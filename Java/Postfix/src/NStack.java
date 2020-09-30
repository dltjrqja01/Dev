public class NStack {
    int[] stack = new int[100];
    int index = 0;

    public NStack(){ }

    void push(int ch){
        index++;
        stack[index] = ch;
    }

    int pop(){
        int tmp = stack[index];
        index--;
        return tmp;
    }

    int top(){
        return stack[index];
    }

    boolean isEmpty(){
        return index == 0;
    }
}
