import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Lab7 {
    

    public static void main(String args[]) {
        try {
            Scanner console = new Scanner(System.in);
            System.out.println("Please input the file name:");
            String filename = console.nextLine();
            File data = new File(filename);
            console.close();
            Scanner scan = new Scanner(data);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println(line + " = " + evaluatePostfix(line));
            }
            scan.close();
        } catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int evaluatePostfix(String expr) {
        StackArrayListBased<String> S = new StackArrayListBased<>();
        String[] array = expr.split(" ");
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            String ch = array[i];
            if (!ch.equals("+") && !ch.equals("-") && !ch.equals("*") && !ch.equals("/")) {
                S.push(ch);
            }
            else if (!S.isEmpty()){
                int rOprnd = Integer.parseInt(S.peek());
                S.pop();
                int lOprnd = Integer.parseInt(S.peek());
                S.pop();
                result = apply(ch,lOprnd,rOprnd);
                S.push(Integer.toString(result));
            }
        }
        return result;
    }

    public static int apply(String str, int op1, int op2) {
        if (str.equals("+")) return op1 + op2;
        else if (str.equals("-")) return op1 - op2;
        else if (str.equals("*")) return op1 * op2;
        else return op1 / op2;
    }
}
