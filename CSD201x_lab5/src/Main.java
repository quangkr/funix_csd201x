
public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.printf("%20s\n", decimalToBinary(0));
        System.out.printf("%20s\n", decimalToBinary(1));
        System.out.printf("%20s\n", decimalToBinary(2));
        System.out.printf("%20s\n", decimalToBinary(3));
        System.out.printf("%20s\n", decimalToBinary(4));
        System.out.printf("%20s\n", decimalToBinary(5));
        System.out.printf("%20s\n", decimalToBinary(6));
    }

    static String decimalToBinary(int input) {
        if (input == 0) {
            return "0";
        }

        String result = "";
        Stack<Byte> stack = new Stack<>();
        
        int remain = input;
        while (remain > 0) {
            stack.push((byte) (remain % 2));
            remain /= 2;
        }
        
        while (!stack.isEmpty()) {
            result += Byte.toString(stack.pop());
        }
        
        return result;
    }
}
