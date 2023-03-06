package main.java.com.kraftwerking.robots_challenge;

public class Robot {
    private int posX;
    private int posY;
    private int id;
    private String direction;
    private String name;

    private Board board;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getDirection() {
        return direction;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Robot(int posX, int posY, int id, String name, String direction, Board board) {
        this.posX = posX;
        this.posY = posY;
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.board = board;
    }

    public String placeRobotOnBoard(int x, int y, int id) {
        return board.placeRobot(x,y,id);
    }

    public String moveRobot(int posX, int posY, int id, String direction) {
        int newX = posX;
        int newY = posY;

        switch (direction) {
            case "NORTH":
                newY = newY + 1;
                break;
            case "SOUTH":
                newY = newY - 1;
                break;
            case "EAST":
                newX = newX + 1;
                break;
            case "WEST":
                newX = newX - 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + direction);
        }
        return board.placeRobot(newX,newY,id);
    }

    public String rotateRobot(String command) {
        if(command.equals("LEFT")){
            if(this.direction.equals("NORTH")){
                this.direction = "WEST";
            } else if(this.direction.equals("SOUTH")){
                this.direction = "EAST";
            } else if(this.direction.equals("EAST")){
                this.direction = "SOUTH";
            } else if(this.direction.equals("WEST")){
                this.direction = "NORTH";
            }

        }

        if(command.equals("RIGHT")){
            if(this.direction.equals("NORTH")){
                this.direction = "EAST";
            } else if(this.direction.equals("SOUTH")){
                this.direction = "WEST";
            } else if(this.direction.equals("EAST")){
                this.direction = "NORTH";
            } else if(this.direction.equals("WEST")){
                this.direction = "SOUTH";
            }

        }

        return "Rotated LEFT new direction: " + this.direction;
    }
}
