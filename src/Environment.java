import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * This java file is the Environment class.
 * It only has a single method which is named runGame() which does not take any parameters
 * It allows the user to run the game in the Main java file
 * It is a recursive function in some way since when the game finishes due to the time quota or collision,
 * user is able to restart the game by pressing Y
 */

public class Environment {

    //Constructors
    Environment() {
    }

    public void runGame() {

        double startTime = System.currentTimeMillis();

        boolean gameIsOn = true;
        boolean didBallCollide = false;

        Player player = new Player();
        double changeCoordinate = 16.0 * 18 / Player.getPERIOD_OF_PLAYER();
        Bar bar = new Bar();

        ArrayList<Ball> ballsList = new ArrayList<>();
        ballsList.add(new Ball(16.0/4,0.8,2));
        ballsList.add(new Ball(16.0/3,0.8,1));
        ballsList.add(new Ball(16.0/4,0.8,0));

        ballsList.get(0).setxVelocity(16.0/900);
        ballsList.get(0).setCreationTime(0);
        ballsList.get(0).setyVelocity(9.0/145);

        ballsList.get(1).setxVelocity(-16.0/900);
        ballsList.get(1).setCreationTime(0);
        ballsList.get(1).setyVelocity(9.0/180);

        ballsList.get(2).setxVelocity(16.0/900);
        ballsList.get(2).setCreationTime(0);
        ballsList.get(2).setyVelocity(9.0/230);

        ArrayList<Arrow> arrowList = new ArrayList<>(); // size should be 1 or 0

        int keyboardPauseDuration = 1;

        while (gameIsOn) {

            double timePassed = System.currentTimeMillis()  - startTime;
            boolean gameWon = false;
            if (ballsList.size() == 0)
                gameWon = true;

            if (timePassed > 40000) { // if the time has ended, show the "game_screen.png"
                StdDraw.clear();
                StdDraw.picture(8, 4.5, "background.png", 16, 9);
                StdDraw.picture(8, -0.5, "bar.png");
                player.drawPlayer(player.getxCoordinate(), player.getyCoordinate(), Player.getModelFile());
                StdDraw.picture(8,9/2.18,"game_screen.png",16/3.8,9/4.0);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                StdDraw.text(16/2.0,9/2.0,"GAME OVER!");
                StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
                StdDraw.text(16/2.0,9/2.3,"To Replay Click “Y”");
                StdDraw.text(16/2.0,9/2.6,"To Quit Click “N”");
                StdDraw.show();
                if (StdDraw.isKeyPressed(KeyEvent.VK_N)) { // N terminates the program
                    StdDraw.pause(keyboardPauseDuration);
                    gameIsOn = false;
                    System.exit(0);
                }
                else if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) { // Y restarts the session
                    StdDraw.pause(keyboardPauseDuration);
                    gameIsOn = false;
                    runGame();
                }
            }

            else if (gameWon) { // if the player has cleared all the balls, show the "game_screen.png"
                StdDraw.picture(8,9/2.18,"game_screen.png",16/3.8,9/4.0);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                StdDraw.text(16/2.0,9/2.0,"YOU WON!");
                StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
                StdDraw.text(16/2.0,9/2.3,"To Replay Click “Y”");
                StdDraw.text(16/2.0,9/2.6,"To Quit Click “N”");
                StdDraw.show();
                if (StdDraw.isKeyPressed(KeyEvent.VK_N)) { // N terminates the program
                    StdDraw.pause(keyboardPauseDuration);
                    gameIsOn = false;
                    System.exit(0);
                }
                else if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) { // Y restarts the session
                    StdDraw.pause(keyboardPauseDuration);
                    gameIsOn = false;
                    runGame();
                }
            }

            else { // if the time is not up
                if (didBallCollide) { //If ball collides with the player, terminate the program.
                    StdDraw.picture(8, 4.5, "background.png", 16, 8.6);
                    player.drawPlayer(player.getxCoordinate(), player.getyCoordinate(), Player.getModelFile());
                    StdDraw.picture(8,9/2.18,"game_screen.png",16/3.8,9/4.0);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                    StdDraw.text(16/2.0,9/2.0,"GAME OVER!");
                    StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
                    StdDraw.text(16/2.0,9/2.3,"To Replay Click “Y”");
                    StdDraw.text(16/2.0,9/2.6,"To Quit Click “N”");
                    StdDraw.show();
                    if (StdDraw.isKeyPressed(KeyEvent.VK_N)) { // N terminates the program
                        StdDraw.pause(keyboardPauseDuration);
                        gameIsOn = false;
                        System.exit(0);
                    }
                    else if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) { // Y restarts the session
                        StdDraw.pause(keyboardPauseDuration);
                        gameIsOn = false;
                        runGame();
                    }
                }
                else { // Game is still being played, animate it.
                    StdDraw.clear();
                    StdDraw.picture(8, 9 / 2.0, "background.png", 16, 9);

                    double[] leftCornerCoordinates = new double[2];
                    double[] rightCornerCoordinates = new double[2];
                    leftCornerCoordinates[0] = player.getxCoordinate() - 0.35;
                    leftCornerCoordinates[1] = player.getyCoordinate() + 31.0/54;
                    rightCornerCoordinates[0] = player.getxCoordinate() + 0.35;
                    rightCornerCoordinates[1] = player.getyCoordinate() + 31.0/54;

                    // Check if the ball collides with the player, terminate if it does.
                    // We will consider our player model a rectangle and the ball as a circle as it really is.
                    // collision cases are a little complicated, but basically I will examine the collision in 5 regions
                    //Left-Right Down, Left-Right Up, directly up.
                    for (Ball currentBall: ballsList) {
                        if (currentBall.getyCoordinate() <= leftCornerCoordinates[1]) { //if the ball is low enough (directly right or left)
                            if (currentBall.getxCoordinate() < player.getxCoordinate()) { // if in the left down region
                                if (leftCornerCoordinates[0] - currentBall.getxCoordinate() < currentBall.returnScaledRadius()) {
                                    didBallCollide = true;
                                    break;
                                }
                            }
                            else if (currentBall.getxCoordinate() > player.getxCoordinate()) { // if in the right down region
                                if (currentBall.getxCoordinate() - rightCornerCoordinates[0] < currentBall.returnScaledRadius()) {
                                    didBallCollide = true;
                                    break;
                                }
                            }
                        }

                        else { // ball is in one of the top 3 regions
                            if ((currentBall.getxCoordinate() > leftCornerCoordinates[0]) && (currentBall.getxCoordinate() < rightCornerCoordinates[0])) { // if the ball is on the directly top region
                                if (currentBall.getyCoordinate() - rightCornerCoordinates[1] < currentBall.returnScaledRadius()) {
                                    didBallCollide = true;
                                    break;
                                }
                            }
                            else if (currentBall.getxCoordinate() < leftCornerCoordinates[0]) { // if the ball is in the top left region
                                if ((Math.pow((Math.pow(currentBall.getyCoordinate()-leftCornerCoordinates[1],2) + Math.pow(leftCornerCoordinates[0]- currentBall.getxCoordinate(),2)),0.5)) < currentBall.returnScaledRadius()) {
                                    didBallCollide = true;
                                    break;
                                }
                            }
                            else if (currentBall.getxCoordinate() > rightCornerCoordinates[0]) { // if the ball is in the top right region
                                if ((Math.pow((Math.pow(currentBall.getyCoordinate()-rightCornerCoordinates[1],2) + Math.pow(currentBall.getxCoordinate() - rightCornerCoordinates[0],2)),0.5)) < currentBall.returnScaledRadius()) {
                                    didBallCollide = true;
                                    break;
                                }
                            }
                        }
                    }

                    //Check if the arrow intersects with any of the balls, if not; draw it
                    if (ballsList.size() >= 1) {
                        int indexWillBeRemoved = -1;
                        for (int ballCount = 0; ballCount < ballsList.size(); ballCount++) {
                            Ball currentBall = ballsList.get(ballCount);
                            if (arrowList.size() > 0) {
                                double tempX = currentBall.getxCoordinate();
                                double tempY = currentBall.getyCoordinate();
                                double distanceToLowerArrow = Math.abs(currentBall.getxCoordinate()-arrowList.get(0).getxCoordinate());
                                double distanceToUpperArrow = Math.pow((Math.pow((arrowList.get(0).getxCoordinate() - tempX), 2) + Math.pow((arrowList.get(0).getyCoordinate() + 4.5 - tempY), 2)), 0.5);
                                if (distanceToUpperArrow <= currentBall.returnScaledRadius()) { // The case in which arrows uppermost part hits the ball
                                    // If the ball is close enough to be popped
                                    if (ballsList.size() > 1)
                                        arrowList.remove(0);
                                    indexWillBeRemoved = ballCount;
                                    break;
                                }

                                else if ((distanceToLowerArrow < currentBall.returnScaledRadius()) && (arrowList.get(0).getyCoordinate() + 4.5 > currentBall.getyCoordinate())) { // The case in which the ball collides the lower part of the arrow
                                    if (ballsList.size() > 1)
                                        arrowList.remove(0);
                                    indexWillBeRemoved = ballCount;
                                    break;
                                }
                            }
                        }
                        if (! (indexWillBeRemoved == -1)) {
                            Ball poppingBall = ballsList.get(indexWillBeRemoved);
                            ballsList.remove(indexWillBeRemoved);
                            if (! (poppingBall.getLevelOfBall() == 0)) {
                                ballsList.add(new Ball(poppingBall.getxCoordinate(), poppingBall.getyCoordinate(), poppingBall.getLevelOfBall()-1));
                                Ball tempBall = ballsList.get(ballsList.size()-1);
                                if (tempBall.getLevelOfBall() == 1) {
                                    tempBall.setxVelocity(16.0/900);
                                    tempBall.setyVelocity(9.0/180);
                                    ballsList.add(new Ball(poppingBall.getxCoordinate(), poppingBall.getyCoordinate(), 1));
                                    ballsList.get(ballsList.size()-1).setxVelocity(-16.0/900);
                                    ballsList.get(ballsList.size()-1).setyVelocity(9.0/180);
                                }
                                else if (tempBall.getLevelOfBall() == 0) {
                                    tempBall.setxVelocity(16.0/900);
                                    tempBall.setyVelocity(9.0/230);
                                    ballsList.add(new Ball(poppingBall.getxCoordinate(), poppingBall.getyCoordinate(), 0));
                                    ballsList.get(ballsList.size()-1).setxVelocity(-16.0/900);
                                    ballsList.get(ballsList.size()-1).setyVelocity(9.0/230);
                                }
                            }
                        }
                    }

                    for (int i = 0; i< ballsList.size(); i++) {
                        Ball currentBall = ballsList.get(i);
                        if ((currentBall.getyCoordinate() < 0.84) && (currentBall.getLevelOfBall() == 2))
                            currentBall.setyVelocity(9.0/145);
                        else if ((currentBall.getyCoordinate() < 0.6) && (currentBall.getLevelOfBall() == 1))
                            currentBall.setyVelocity(9.0/175);
                        else if ((currentBall.getyCoordinate() < 0.36) && (currentBall.getLevelOfBall() == 0))
                            currentBall.setyVelocity(9.0/225);

                        if ((currentBall.getxCoordinate()+currentBall.getxVelocity() < currentBall.returnScaledRadius()) || (currentBall.getxCoordinate()+currentBall.getxVelocity() > 16.0- currentBall.returnScaledRadius()))
                            currentBall.setxVelocity(currentBall.getxVelocity()*-1);
                        else if ((currentBall.getxCoordinate()+currentBall.getxVelocity() > 0.0) && (currentBall.getxVelocity() < 0.0)) // if the ball is moving left and has not collided with the left wall
                            currentBall.setxCoordinate(currentBall.getxCoordinate()+currentBall.getxVelocity());
                        else if ((currentBall.getxCoordinate()+currentBall.getxVelocity() < 16.0) && (currentBall.getxVelocity() > 0.0))
                            currentBall.setxCoordinate(currentBall.getxCoordinate()+currentBall.getxVelocity());
                        currentBall.setyCoordinate(currentBall.getyCoordinate() + currentBall.getyVelocity());
                        currentBall.setyVelocity(currentBall.getyVelocity()-Ball.getGravity()*200);
                    }

                    for (int i = 0; i<arrowList.size(); i++) { // arrow is drawn before the bar.
                        StdDraw.picture(arrowList.get(i).getxCoordinate(),arrowList.get(i).getyCoordinate(),"arrow.png");
                        arrowList.get(i).setyCoordinate(arrowList.get(i).getyCoordinate() + 9.0*15.5/1500);
                        if (arrowList.get(i).getyCoordinate() >= 4.5) {
                            arrowList.remove(i);
                        }
                    }
                    StdDraw.picture(8, -0.5, "bar.png");
                    
                    //Movement and actions of the player
                    if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                        StdDraw.pause(keyboardPauseDuration);
                        if (arrowList.size() == 0)
                            arrowList.add(new Arrow(player.getxCoordinate(), player.getyCoordinate()-4.5));
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                        StdDraw.pause(keyboardPauseDuration);
                        if (!(player.getxCoordinate() - changeCoordinate >= 15.5))
                            player.setxCoordinate(player.getxCoordinate() + changeCoordinate);
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                        StdDraw.pause(keyboardPauseDuration);
                        if (!(player.getxCoordinate() - changeCoordinate <= 0.4))
                            player.setxCoordinate(player.getxCoordinate() - changeCoordinate);
                    }

                    //Drawings
                    bar.drawShrinkingBar(timePassed);
                    player.drawPlayer(player.getxCoordinate(), player.getyCoordinate(), Player.getModelFile());
                    for (int i = 0; i<ballsList.size(); i++) {
                        ballsList.get(i).drawBall(ballsList.get(i).getxCoordinate(), ballsList.get(i).getyCoordinate(), ballsList.get(i).getLevelOfBall(), "ball.png");
                    }

                    StdDraw.show();
                }
            }
        }
    }
}
