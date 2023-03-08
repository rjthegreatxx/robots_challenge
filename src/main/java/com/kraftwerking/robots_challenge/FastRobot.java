package main.java.com.kraftwerking.robots_challenge;

public class FastRobot extends Robot{

    private final Board board;
    public FastRobot(int posX, int posY, int id, String name, String direction, Board board,int threadNumber) {
        super(posX, posY, id, name, direction, board,threadNumber);
        this.board = board;
    }

    @Override
    public String moveRobot(int posX, int posY, int id, String direction) {
        int newX = posX;
        int newY = posY;

        switch (direction) {
            case "NORTH":
                newY = newY + 2;
                break;
            case "SOUTH":
                newY = newY - 2;
                break;
            case "EAST":
                newX = newX + 2;
                break;
            case "WEST":
                newX = newX - 2;
                break;

        }
        return this.board.placeRobot(newX,newY,id);
    }
}