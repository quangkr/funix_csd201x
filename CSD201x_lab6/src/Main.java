import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ChessBoard board = new ChessBoard(8);
        int[] r = board.solveNQueen();

        System.out.println(Arrays.toString(r));
        printChessBoard(r);
    }

    static void printChessBoard(int[] rows) {
        int[] transpose = new int[rows.length];
        for (int i = 0; i < rows.length; i++) {
            transpose[rows[i]] = i;
        }

        for (int i = 0; i < transpose.length; i++) {
            System.out.print("|");
            for (int j = 0; j < transpose.length; j++) {
                System.out.print((transpose[i] == j ? "x" : " ") + "|");
            }
            System.out.println();
        }
    }
}
