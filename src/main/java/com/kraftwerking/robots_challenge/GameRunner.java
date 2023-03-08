package main.java.com.kraftwerking.robots_challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameRunner implements Runnable { //preferable to extend Thread class, can extend other classes than Thread
    private static final String CURRENT_PATH = "./src/main/resources/";
    private static Board board;

    private int threadNumber;

    public void run(){
        System.out.println("Robots Challenge - threadNumber: " + threadNumber);
        System.out.println("----------------");
        Robot robot = new Robot(0,0,1,"R2D2",null, board,threadNumber);
        System.out.println("Robot: " + robot.getName() + " - threadNumber: " + threadNumber);

        List<String> commands = new ArrayList<>();
        List<String> commands2 = new ArrayList<>();

        try {
            commands = Files.readAllLines(Paths.get(CURRENT_PATH + "input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        robot.processCommands(commands);

        FastRobot fastrobot = new FastRobot(0,0,2,"C3PO",null, board,threadNumber);
        System.out.println("Robot: " + fastrobot.getName() + " - threadNumber: " + threadNumber);

        try {
            commands2 = Files.readAllLines(Paths.get(CURRENT_PATH + "input2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fastrobot.processCommands(commands2);

    }
    public GameRunner(int threadNumber) {
        board = new Board();
        this.threadNumber = threadNumber;
    }

}