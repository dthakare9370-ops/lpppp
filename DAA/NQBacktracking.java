
public class NQBacktracking {

    static int N = 8;

    int[] ld = new int[30];
    int[] rd = new int[30];
    int[] cl = new int[30];

    int x, y;

    // Constructor
    NQBacktracking(int x_, int y_) {
        this.x = x_;
        this.y = y_;
    }

    // Print Solution
    void printSolution(int[][] board) {

        System.out.println("N Queen Backtracking Solution:");
        System.out.println("Given initial position of 1st queen at row: "
                + x + " column: " + y);
        System.out.println();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Backtracking Utility Function
    boolean solveNQUtil(int[][] board, int col) {

        // Base Case
        if (col >= N) {
            return true;
        }

        // Skip the column containing the first queen
        if (col == y) {
            return solveNQUtil(board, col + 1);
        }

        for (int i = 0; i < N; i++) {

            // Skip the row containing the first queen
            if (i == x) {
                continue;
            }

            // Check diagonals and rows
            if (ld[i - col + N - 1] != 1 &&
                rd[i + col] != 1 &&
                cl[i] != 1) {

                // Place Queen
                board[i][col] = 1;

                ld[i - col + N - 1] = 1;
                rd[i + col] = 1;
                cl[i] = 1;

                // Recursive Call
                if (solveNQUtil(board, col + 1)) {
                    return true;
                }

                // BACKTRACK
                board[i][col] = 0;

                ld[i - col + N - 1] = 0;
                rd[i + col] = 0;
                cl[i] = 0;
            }
        }

        return false;
    }

    // Main Solve Function
    boolean solveNQ() {

        int[][] board = new int[N][N];

        // Place first queen at given position
        board[x][y] = 1;

        ld[x - y + N - 1] = 1;
        rd[x + y] = 1;
        cl[x] = 1;

        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution(board);

        return true;
    }

    // Main Method
    public static void main(String[] args) {

        int x = 3;
        int y = 2;

        NQBacktracking nq = new NQBacktracking(x, y);

        nq.solveNQ();
    }
}
