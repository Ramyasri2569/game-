import java.util.Scanner;

public class TicTacToe {

    private static char[][] board = new char[3][3]; 
    private static char currentPlayerSymbol = 'R';
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe ");
        System.out.println("Players:");
        System.out.println(" - Ramya uses 'R'");
        System.out.println(" - Mabbuu uses 'M'");
        System.out.println(" - Enter your move as: row column ( 0 2 for top-right cell)");
        System.out.println();

        boolean playAgain = true;
        while (playAgain) {
            initializeBoard();
            playGame();

            System.out.print("Do you want to play again? (yes/no): ");
            String choice = sc.next().toLowerCase();
            playAgain = choice.equals("yes");
        }

        System.out.println(" Thank you for playing, Ramya & Mabbuu!");
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayerSymbol = 'R'; 
    }

    private static void printBoard() {
        System.out.println("     0     1     2"); 
        for (int i = 0; i < 3; i++) {
            System.out.println("   ┌─────┬─────┬─────┐");
            System.out.print(i + "  ");
            for (int j = 0; j < 3; j++) {
                System.out.print("│  " + board[i][j] + "  ");
            }
            System.out.println("│");
        }
        System.out.println("   └─────┴─────┴─────┘");
    }

    private static void playGame() {
        int moves = 0;
        boolean gameOver = false;

        while (!gameOver && moves < 9) {
            printBoard();
            String currentPlayerName = (currentPlayerSymbol == 'R') ? "Ramya" : "Mabbuu";
            System.out.println(currentPlayerName + ", enter your move (row and column): ");

            int row = sc.nextInt();
            int col = sc.nextInt();

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayerSymbol;
                moves++;

                if (checkWinner(row, col)) {
                    printBoard();
                    System.out.println("Congratulations " + currentPlayerName + "! You win this round");
                    gameOver = true;
                } else if (moves == 9) {
                    printBoard();
                    System.out.println("It's a draw!");
                } else {
                    currentPlayerSymbol = (currentPlayerSymbol == 'R') ? 'M' : 'R';
                }
            } else {
                System.out.println(" Invalid move! Try again.");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static boolean checkWinner(int row, int col) {
        if (board[row][0] == currentPlayerSymbol &&
            board[row][1] == currentPlayerSymbol &&
            board[row][2] == currentPlayerSymbol)
            return true;

        if (board[0][col] == currentPlayerSymbol &&
            board[1][col] == currentPlayerSymbol &&
            board[2][col] == currentPlayerSymbol)
            return true;

        if (row == col &&
            board[0][0] == currentPlayerSymbol &&
            board[1][1] == currentPlayerSymbol &&
            board[2][2] == currentPlayerSymbol)
            return true;

        if (row + col == 2 &&
            board[0][2] == currentPlayerSymbol &&
            board[1][1] == currentPlayerSymbol &&
            board[2][0] == currentPlayerSymbol)
            return true;

        return false;
    }
}
