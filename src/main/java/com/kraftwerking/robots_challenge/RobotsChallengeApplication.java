package main.java.com.kraftwerking.robots_challenge;

public class RobotsChallengeApplication {
    public static void main(String[] args) {
        //GameRunner implements the Runnable interface, is concurrent
        //2 instances of GameRunner executing concurrently
        //multiple concurrent threads, if used run would be executed sequentially
        GameRunner gameRunner = new GameRunner(1);
        GameRunner gameRunner2 = new GameRunner(2);

        Thread newGameRunnerThread = new Thread(gameRunner);
        newGameRunnerThread.start(); //run() would execute as a regular method
        //join() waits for this thread to die before moving to next line, defeats purpose of running 2 games at same time
/*        try {
            newGameRunnerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        Thread newGameRunnerThread2 = new Thread(gameRunner2);
        newGameRunnerThread2.start();
/*        try {
            newGameRunnerThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }
}