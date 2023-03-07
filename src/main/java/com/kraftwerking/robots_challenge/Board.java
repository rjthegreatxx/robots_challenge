package main.java.com.kraftwerking.robots_challenge;

public class Board {
    private final int[][] board;

    public Board() {
        board = new int[5][5]; // create a 5x5 board
    }

    public void printBoard() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkValidMove(int x, int y, int id) {
        //will go off board
        if (x > 4) {
            System.out.println(x + "," + y + " not a valid move - IGNORED");
            return false;
        }
        if (y > 4) {
            System.out.println(x + "," + y + " not a valid move - IGNORED");
            return false;
        }

        //if not 0 or id then another robot on spot
        if(board[x][y] != 0 && board[x][y] != id){
            System.out.println(x + "," + y + " not a valid move - IGNORED");
            return false; //a robot already placed at point
        }
        return true;
    }

    public String placeRobot(int x, int y, int id) {
        if(!checkValidMove(x,y,id)){
            return "Not a valid move";
        }

        deleteRobot(id);
        board[x][y] = id;
        printBoard();
        return "Placed robot at " + x + "," + y;
    }

    private void deleteRobot(int id) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if(board[row][col] == id){
                    board[row][col] = 0;
                }
            }
        }
    }
}