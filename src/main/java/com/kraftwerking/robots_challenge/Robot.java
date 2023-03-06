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

        }
        return board.placeRobot(newX,newY,id);
    }

    public String rotateRobot(String command) {
        if(command.equals("LEFT")){
            switch (this.direction) {
                case "NORTH":
                    this.direction = "WEST";
                    break;
                case "SOUTH":
                    this.direction = "EAST";
                    break;
                case "EAST":
                    this.direction = "SOUTH";
                    break;
                case "WEST":
                    this.direction = "NORTH";
                    break;
            }

        }

        if(command.equals("RIGHT")){
            switch (this.direction) {
                case "NORTH":
                    this.direction = "EAST";
                    break;
                case "SOUTH":
                    this.direction = "WEST";
                    break;
                case "EAST":
                    this.direction = "NORTH";
                    break;
                case "WEST":
                    this.direction = "SOUTH";
                    break;
            }

        }

        return "Rotated " + command + " new direction: " + this.direction;
    }
}
