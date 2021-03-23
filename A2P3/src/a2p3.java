
import java.io.*;
import java.util.*;

public class a2p3 {

    static int sumOfAllNumbers;
    static int counting;
    static BufferedReader br = null;
    static FileReader fr = null;
    static FileWriter fw = null;

    public static void main(String[] args) throws Exception {
        File input = new File("input.txt");
        File output = new File("output.txt");
        if (!input.exists()) {
            System.out.println("Input file does not exists! ");
            System.exit(0);
        }
        //==============================
        Scanner in = new Scanner(input);
        PrintWriter out = new PrintWriter(output);
        //==============================

        int number_of_instructions = in.nextInt();
        String command;
        for (int i = 0; i < number_of_instructions; i++) {
            command = in.next();
            if (command.equalsIgnoreCase("PrintNumSquares")) {
                int number = in.nextInt();
                out.println("PrintNumSquares for series till " + number + ":");
                out.println(PrintNumSquares(number));
                out.println();
            } else if (command.equalsIgnoreCase("DegreeOfSum")) {
                int number = in.nextInt();
                out.print("DegreeOfSum for number " + number + " is ");
                out.println(DegreeOfSum(number));
                out.println();
            } else if (command.equalsIgnoreCase("printPattern")) {
                int number = in.nextInt();
                char pattern = in.next().charAt(0);
                out.println("printPattern for size " + number + " using symbol " + pattern + " ");
                out.println();
                printPatternReverse(out, number - 1, 1, number, pattern);

                printPattern(out, 0, number - 1, number, pattern);
                out.println();
            }
        }
        readDataFromFile(out, in);
        out.flush();
        out.close();

    }

    public static void readDataFromFile(PrintWriter out, Scanner in) throws Exception {
        String tokens[] = null;
        try {
            fr = new FileReader("input2.txt");
            br = new BufferedReader(fr);
        } catch (Exception io) {
            System.out.println(io);
        }
        String s = null;
        s = br.readLine();
        int number = Integer.parseInt(s);
        int count = 1;
        s = br.readLine();
        try {
            fw = new FileWriter("output2.txt");
            out = new PrintWriter(fw);
        } catch (Exception ioe) {
            System.err.print(ioe);
        }

        while (count != number + 1) {
            tokens = s.split(" ");
            if (tokens[0].equals("KAUmath")) {
                int k = Integer.parseInt(tokens[1]);
                int n = Integer.parseInt(tokens[2]);
                if (k > n) {
                    int temp = n;
                    n = k;
                    k = temp;
                }
                sumOfAllNumbers = 0;
                try {
                    out.println("KAUmath:  " + sum(k, n));
                    out.println();

                } catch (Exception ioe) {
                    System.err.print(ioe);
                }

            } else if (tokens[0].equals("KAUshape")) {
                try {
                    out.println("KAUshape:");

                } catch (Exception ioe) {
                    System.err.print(ioe);
                }
                int k = Integer.parseInt(tokens[1]);
                printDiamond(out, 0, k - 1, k);
                printDiamondReverse(out, k - 2, 2, k);
                try {
                    out.println();

                } catch (Exception ioe) {
                    System.err.print(ioe);
                }

            } else if (tokens[0].equals("KAUhop")) {
                counting = 0;
                int hops[] = new int[tokens.length - 1];
                try {
                    for (int i = 1; i < tokens.length; i++) {
                        hops[i - 1] = Integer.parseInt(tokens[i]);
                    }
                } catch (Exception ioe) {
                    System.err.print(ioe);
                }

                String print;
                if (solvable(0, hops)) {
                    print = "KAUhop:  Solvable";
                } else {
                    print = "KAUhop:  Not Solvable";
                }
                try {
                    out.println(print);
                    out.println();

                } catch (Exception ioe) {
                    System.err.print(ioe);
                }
            } else if (tokens[0].equals("KAUgot34")) {
                counting = 0;
                int k = Integer.parseInt(tokens[1]);
                String print;
                if (KAUgot34(k)) {
                    print = "KAUgot34:  Solvable";
                } else {
                    print = "KAUgot34:  Not Solvable";
                }
                try {
                    out.println(print);
                    out.println();

                } catch (Exception ioe) {
                    System.err.print(ioe);
                }

            }
            count++;
            s = br.readLine();
        }
    }

    public static String PrintNumSquares(int number) {
        if (number <= 1) {
            return "" + 1;
        } else if (number % 2 == 0) {
            return (number * number) + " " + PrintNumSquares(number - 1);
        } else {
            return PrintNumSquares(number - 1) + " " + (number * number);
        }

    }

    public static int DegreeOfSum(int number) {
        if (number / 10 <= 0) {
            return 1;
        } else {
            int sum = 0;
            while (number != 0) {
                sum += (number % 10);
                number /= 10;
            }
            if (sum / 10 <= 0) {
                return DegreeOfSum(sum);
            } else {
                return 1 + DegreeOfSum(sum);
            }
        }

    }

    public static void printPattern(PrintWriter out, int stearics, int spaces, int maxHeight, char symbol) {
        if (stearics == maxHeight) {
            return;
        }
        String s = "";
        for (int i = 0; i < spaces - i - 1; i++) {
            s = s + " ";
        }
        for (int i = 0; i < stearics + 1; i++) {
            s = s + symbol;
        }

        try {
            out.println(s);

        } catch (Exception ioe) {
            out.print(ioe);
        }
        printPattern(out, stearics + 2, spaces - 2, maxHeight, symbol);

    }

    public static void printPatternReverse(PrintWriter out, int stearics, int spaces, int maxHeight, char symbol) {
        if (stearics < 1) {
            return;
        }
        String s = "";
        for (int i = 0; i < spaces - i - 1; i++) {
            s = s + " ";
        }
        for (int i = 0; i < stearics; i++) {
            s = s + symbol;
        }
        try {
            out.println(s);

        } catch (Exception ioe) {
            out.print(ioe);
        }
        printPatternReverse(out, stearics - 2, spaces + 2, maxHeight, symbol);

    }

    public static void printDiamond(PrintWriter out, int stearics, int spaces, int maxHeight) {
        if (stearics == maxHeight + 1) {
            return;
        }
        String s = "";
        for (int i = 0; i < spaces; i++) {
            s = s + " ";
        }
        for (int i = 0; i < stearics + 1; i++) {
            s = s + "* ";
        }
        try {
            out.println(s);
            out.flush();
        } catch (Exception ioe) {
            System.err.print(ioe);
        }
        printDiamond(out, stearics + 2, spaces - 2, maxHeight);

    }

    public static void printDiamondReverse(PrintWriter out, int stearics, int spaces, int maxHeight) {
        if (stearics < 1) {
            return;
        }
        String s = "";
        for (int i = 0; i < spaces; i++) {
            s = s + " ";
        }
        for (int i = 0; i < stearics; i++) {
            s = s + "* ";
        }
        try {
            out.println(s);
            out.flush();
        } catch (Exception ioe) {
            System.err.print(ioe);
        }
        printDiamondReverse(out, stearics - 2, spaces + 2, maxHeight);

    }

    public static int fibonnacci(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fibonnacci(n - 1) + fibonnacci(n - 2);
        }
    }

    public static int sum(int k, int n) {
        if (k <= n) {
            sum(k + 1, n);
            int fibonacciNumberGreaterThanZero = 0;
            int fibonacciNumber = fibonnacci(k);

            if (fibonacciNumber < 10) {
                sumOfAllNumbers = sumOfAllNumbers + fibonacciNumber;
            } else {
                while (fibonacciNumber != 0) {
                    fibonacciNumberGreaterThanZero = fibonacciNumberGreaterThanZero + fibonacciNumber % 10;
                    fibonacciNumber = fibonacciNumber / 10;
                }
                sumOfAllNumbers = sumOfAllNumbers + fibonacciNumberGreaterThanZero;

            }

        }
        return sumOfAllNumbers;

    }

    public static boolean solvable(int square, int hops[]) {
        counting++;
        if (counting == 200) {
            return false;
        }
        if (square < 0 || hops.length <= square) {
            return false;
        }
        if (hops[square] == 0) {
            return true;
        }
        return solvable(square + hops[square], hops) || solvable(square - hops[square], hops);
    }

    public static void diamondRecursive(PrintWriter out, int k) {
        try {
            out.println("KAUshape:");

        } catch (Exception ioe) {
            System.err.print(ioe);
        }

        drawDiamond(out, "*", 0, k / 2 - 1);
        try {
            out.println();
        } catch (Exception ioe) {
            System.err.print(ioe);
        }

    }

    public static void drawDiamond(PrintWriter out, String seed, int turn, int centerLevel) {

        if (seed.length() <= 0) {
            return;
        }

        char[] chars = new char[Math.abs(turn - centerLevel - 1)];
        Arrays.fill(chars, ' ');
        String spaces = new String(chars);

        try {
            out.print(spaces);
            out.println(seed);
        } catch (Exception ioe) {
            System.err.print(ioe);
        }

        System.out.print(spaces);

        System.out.println(seed);

        if (turn <= centerLevel) {
            seed += "**";
        } else {
            if (seed.length() >= 3) {
                seed = seed.substring(1, seed.length() - 1);
            } else {
                seed = "";
            }
        }

        turn++;

        drawDiamond(out, seed, turn, centerLevel);
    }

    public static boolean KAUgot34(int number) {
        if (number < 34) {
            return false;
        }

        if (number == 34) {
            return true;
        }
        if (number % 5 == 0 && number / 2 >= 34) {
            number = number - 34;
            return KAUgot34(number);
        } else if (number % 2 == 0 && number / 2 >= 34) {
            number = number / 2;
            return KAUgot34(number);
        } else if (number % 3 == 0 || number % 4 == 0) {
            int lastDigit = number % 10;
            int secondLastDigit = (number % 100) / 10;
            number = number - lastDigit * secondLastDigit;
            counting++;
            if (counting == 200) {
                return false;
            }
            return KAUgot34(number);
        }
        return false;

    }

}
