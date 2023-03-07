package main.java.com.kraftwerking.robots_challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameRunner {
    private static final String CURRENT_PATH = "./src/main/resources/";
    private static Board board;

    public void runGame(){
        System.out.println("Robots Challenge");
        System.out.println("----------------");
        Robot robot = new Robot(0,0,1,"R2D2",null, board);
        System.out.println("Robot: " + robot.getName());

        List<String> commands = new ArrayList<>();
        List<String> commands2 = new ArrayList<>();

        try {
            commands = Files.readAllLines(Paths.get(CURRENT_PATH + "input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        robot.processCommands(commands);

        FastRobot fastrobot = new FastRobot(0,0,2,"C3PO",null, board);
        System.out.println("----------------");
        System.out.println("Robot: " + fastrobot.getName());

        try {
            commands2 = Files.readAllLines(Paths.get(CURRENT_PATH + "input2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fastrobot.processCommands(commands2);

    }
    public GameRunner() {
        board = new Board();
    }

}