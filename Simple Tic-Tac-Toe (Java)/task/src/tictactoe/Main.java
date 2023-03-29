package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /**
     *
     * @param board_converted
     * @param value
     * @return
     */
    public static int diagonal(String[][] board_converted, String value) {
        if ((board_converted[0][0].equals(value) && board_converted[1][1].equals(value) && board_converted[2][2].equals(value))
                || (board_converted[0][2].equals(value) && board_converted[1][1].equals(value) && board_converted[2][0].equals(value))) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param board_converted
     * @return
     */
    public static int row_X(String[][] board_converted) {
        String[] X_row = new String[]{"X", "X", "X"};
        for (String[] row : board_converted) {
            if (Arrays.toString(row).equals(Arrays.toString(X_row))) {
                return 1;
            }
        }
        return 0;
    }

    public static int row_O(String[][] board_converted) {
        String[] X_row = new String[]{"O", "O", "O"};
        for (String[] row : board_converted) {
            if (Arrays.toString(row).equals(Arrays.toString(X_row))) {
                return 1;
            }
        }
        return 0;
    }

    public static int column(String[][] board_converted, String value) {
        for (int i = 0; i < 3; i++) {
            if (board_converted[0][i].equals(value) && board_converted[1][i].equals(value) && board_converted[2][i].equals(value)) {
                return 1;
            }
        }
        return 0;
    }
    /**
     *
     * @param board
     */
    public static int state(String[][] board) {
        String[][] board_converted = new String[3][3];
        int counter = 0;
        int X_count = 0;
        int Y_count = 0;
        int empty_counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board_converted[i][j] = board[i][j];
                counter++;

                if (board[i][j].equals("X")) {
                    X_count++;
                } else if (board[i][j].equals("O")) {
                    Y_count++;
                } else {
                    empty_counter++;
                }
            }
        }
        int X_check_diagonal = diagonal(board_converted, "X");
        int O_check_diagonal = diagonal(board_converted, "O");

        int X_cross_check = row_X(board_converted);
        int O_cross_check = row_O(board_converted);

        int X_column_check = column(board_converted, "X");
        int O_column_check = column(board_converted, "O");

        int x_wins = X_column_check | X_check_diagonal | X_cross_check;
        int o_wins = O_column_check | O_check_diagonal | O_cross_check;

        if (X_count - Y_count > 1 || Y_count - X_count > 1) {
           return -1; //impossible
        } else if (o_wins > 0 && x_wins > 0) {
            return -1;
        } else if (X_cross_check == 1 || X_column_check == 1 || X_check_diagonal == 1) {
            return 1; //x wins
        } else if (O_cross_check == 1 || O_column_check == 1 || O_check_diagonal == 1) {
            return 0; //o wins

        } else {
            if (empty_counter > 0) {
                return 3; //continue
            } else {
                return 2; //draw
            }
        }
    }
    public static String[][] board_formatter(String board){
        String[][] board_converted = new String[3][3];
        int counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String character = String.valueOf(board.charAt(counter));
                board_converted[i][j] = character;
                counter++;
            }
        }
        return board_converted;
    }
    public static void printer(String[][] input) {
        System.out.println("---------");
        for (int i = 0;i<3;i++){
            System.out.print("| ");
            for (int j = 0;j<3;j++){
                if (input[i][j].equals("_")){
                    System.out.print("  ");
                }
                else{
                    System.out.print(input[i][j]+" ");
                }
            }
            System.out.print("|\n");

        }
        System.out.println("---------");
    }

    public static void user_input(String[][] board, Scanner scanner,int turn){
        int x = 0;
        int y = 0;
        int failure = 0;

        while(failure==0) {
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    y = scanner.nextInt();
                    if (x < 1 || x > 3 || y < 1 || y > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                    else{
                        x--;
                        y--;
                        if (board[x][y].equals("_")){
                            if (turn %2 == 0) {
                                board[x][y] = "X";
                            }
                            else{
                                board[x][y] = "O";
                            }
                            printer(board);
                            failure = 1;
                        }
                        else{
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    }
                }else{
                    System.out.println("You should enter numbers!");
                }
            }else{
                System.out.println("You should enter numbers!");
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int state_of_play = 3;
        int turn = 0;

        String[][] board = board_formatter("_________");
        printer(board);
        while (state_of_play ==3) {
            user_input(board, scanner,turn);
            state_of_play = state(board);
            turn ++;
        }
        switch (state_of_play){
            case 1:
                System.out.println("X wins");
                break;
            case 0:
                System.out.println("O wins");
                break;
            case 2:
                System.out.println("Draw");
                break;

        }
    }
}
