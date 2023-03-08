package main.java.com.kraftwerking.robots_challenge;

import java.util.List;

public class Robot implements RoboticMovement{
    private int posX;
    private int posY;
    private int id;
    private String direction;
    private String name;

    private Board board;

    private int threadNumber;

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

    void processCommands(List<String> commands) {
        boolean firstCommand = false;
        for (String command : commands) {
            String result;
            if(!firstCommand){
                if(command.startsWith("PLACE ")){
                    firstCommand = true;
                    //process place command
                    result = processCommand(command);
                } else {
                    System.out.println("First command must be PLACE");
                    continue;
                }
            }  else {
                result = processCommand(command);
            }

            System.out.println(result);
        }
    }

    public String processCommand(String command){
        String result;
        if(command.startsWith("PLACE ")){
            System.out.println(command + " - threadNumber: " + threadNumber);
            command = command.replaceAll("\\s", "").replaceAll("PLACE", "");
            String[] commandList = command.split(",");
            int x = Integer.parseInt(commandList[0]);
            int y = Integer.parseInt(commandList[1]);
            String direction = commandList[2];
            result = placeRobotOnBoard(x,y,getId());

            if(!result.equals("Not a valid move")){
                setPosX(Integer.parseInt(commandList[0]));
                setPosY(Integer.parseInt(commandList[1]));
                setDirection(direction);
            }
            return result;
        }

        if(command.equals("REPORT")){
            System.out.println(command + " - threadNumber: " + threadNumber);
            getBoard().printBoard();
            return "----------------";
        }

        switch (command) {
            case "MOVE":
                System.out.println(command + " - threadNumber: " + threadNumber);
                result = moveRobot(getPosX(),getPosY(),getId(),getDirection());

                if(result.startsWith("Placed robot at")){
                    int newX = result.charAt(result.length() - 3)  - '0';
                    int newy = result.charAt(result.length() - 1)  - '0';
                    setPosX(newX);
                    setPosY(newy);
                }
                break;
            case "LEFT": //merge LEFT RIGHT cases
            case "RIGHT":
                System.out.println(command + " - threadNumber: " + threadNumber);
                result = rotateRobot(command);
                break;
            default:
                result = "Invalid command " + command;
                break;
        }
        return result;
    }

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

    public Robot(int posX, int posY, int id, String name, String direction, Board board, int threadNumber) {
        this.posX = posX;
        this.posY = posY;
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.board = board;
        this.threadNumber = threadNumber;
    }
}