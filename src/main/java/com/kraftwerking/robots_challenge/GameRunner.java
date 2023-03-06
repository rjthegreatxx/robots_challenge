package main.java.com.kraftwerking.robots_challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameRunner {
    private static final String CURRENT_PATH = "./src/main/resources/";
    private final Robot robot;

    public void runGame(){
        System.out.println("Robots Challenge");
        System.out.println("----------------");

        List<String> commands = new ArrayList<>();

        try {
            commands = Files.readAllLines(Paths.get(CURRENT_PATH + "input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        processCommands(commands);
    }

    private void processCommands(List<String> commands) {
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

            if(result.length() > 0) System.out.println(result);
        }
    }

    public String processCommand(String command){
        String result;
        if(command.startsWith("PLACE ")){
            System.out.println(command);
            command = command.replaceAll("\\s", "").replaceAll("PLACE", "");
            String[] commandList = command.split(",");
            int x = Integer.parseInt(commandList[0]);
            int y = Integer.parseInt(commandList[1]);
            String direction = commandList[2];
            result = robot.placeRobotOnBoard(x,y,robot.getId());

            if(!result.equals("Not a valid move")){
                robot.setPosX(Integer.parseInt(commandList[0]));
                robot.setPosY(Integer.parseInt(commandList[1]));
                robot.setDirection(direction);
            }
            return result;
        }

        if(command.equals("REPORT")){
            System.out.println(command);
            robot.getBoard().printBoard();
            return "";
        }

        switch (command) {
            case "MOVE":
                System.out.println(command);
                result = robot.moveRobot(robot.getPosX(),robot.getPosY(),robot.getId(),robot.getDirection());

                if(result.startsWith("Placed robot at")){
                    int newX = result.charAt(result.length() - 3)  - '0';
                    int newy = result.charAt(result.length() - 1)  - '0';
                    robot.setPosX(newX);
                    robot.setPosY(newy);
                }
                break;
            case "LEFT":
                result = robot.rotateRobot(command);
                break;
            case "RIGHT":
                result = robot.rotateRobot(command);
                break;
            default:
                result = "Invalid command " + command;
                break;
        }
        return result;
    }
    public GameRunner() {
        Board board = new Board();
        this.robot = new Robot(0,0,1,"R2D2",null, board);
    }

}
