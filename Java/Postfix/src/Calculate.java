public class Calculate extends Invert {

    public Calculate() {}

    void cal() {
        float tmp = -1;

        for (int i = 0; i < invert.length; i++) {
            if (invert[i] >= '0' && invert[i] <= '9') {
                if (tmp == -1) tmp = Integer.parseInt(String.valueOf(invert[i]));
                else tmp = (tmp * 10) + Integer.parseInt(String.valueOf(invert[i]));
            } else if (invert[i] == ' ') {
                if (invert[i + 1] == '+' || invert[i + 1] == '-' || invert[i + 1] == '*' || invert[i + 1] == '/') {
                    continue;
                } else {
                    npush(tmp);
                    tmp = -1;
                }
            }
            else if (invert[i] == '+' || invert[i] == '-' || invert[i] == '*' || invert[i] == '/') {
                float num1 = tmp;
                float num2 = npop();

                if (invert[i] == '+') {
                    tmp = (float)num2 + num1;
                } else if (invert[i] == '-') {
                    tmp = (float)num2 - num1;
                } else if (invert[i] == '*') {
                    tmp = (float)num2 * num1;
                } else if (invert[i] == '/') {
                    tmp = (float)num2 / num1;
                }
            }
        }
        npush(tmp);
    }
    void print(){
        float res = npop();
        System.out.println(res);
    }
}
