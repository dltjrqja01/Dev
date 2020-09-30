public class Stack {
    char[] stack = new char[100];
    float[] nstack = new float[100];
    int index = 0;
    int nindex = 0;
    public Stack(){ }

    void push(char ch){
        index++;
        stack[index] = ch;
    }

    void npush(float num){
        nindex++;
        nstack[nindex] = num;
    }

    char pop(){
        char tmp = stack[index];
        index--;
        return tmp;
    }

    float npop(){
        float tmp = nstack[nindex];
        nindex--;
        return tmp;
    }

    char top(){
        return stack[index];
    }

    float ntop(){
        return nstack[nindex];
    }

    boolean isEmpty(){
        return index == 0;
    }

    boolean nisEmpty(){
        return nindex == 0;
    }
}
