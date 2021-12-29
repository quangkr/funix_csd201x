public class ChessBoard {
    private int size;

    /**
     * @param size
     */
    public ChessBoard(int size) {
        this.size = size;
    }
    
    private boolean isQueenSafe(int[] rows, int col, int row) {
        if (col == 0) {
            return true;
        }

        for (int i=0; i<col; i++) {
            if (rows[i] == row || Math.abs(col - i) == Math.abs(row - rows[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean solveNQueenStep(int[] rows, int col) {
        if (col >= size) {
            return true;
        }

        for (int i=0; i<size; i++) {
            if (isQueenSafe(rows, col, i)) {
                rows[col] = i;
                if (solveNQueenStep(rows, col+1)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public int[] solveNQueen() {
        int[] result = new int[size];
        for (int i=0; i<size; i++) {
            result[i] = -1;
        }
        solveNQueenStep(result, 0);

        return result;
    }
}
