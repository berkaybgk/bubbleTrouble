/**
 * Author : Berkay Bugra Gok
 * ID : 2021400258
 * Date : 14.04.2023
 *
 * This code animates the replica of the game "Bubble Trouble" with its classes
 * Left and Right arrow keys is used to move the player model.
 * Space is used to shoot an arrow. (Remember that there can be only a single arrow on the screen)
 * The game has a duration of 40 seconds, if time finishes and there are still ball(s) on the screen,
 * you have lost the game and can repeat it by pressing Y. You can also terminate the program by pressing N.
 * If you collide with a ball, the case is the same with the one above, but you can try as many times as you want.
 * If you have popped all the balls, you win the game but remember, Popping a ball except the ones which are at the
 * smallest level cause the ball to split up and create smaller balls.
 *
 */

public class Main {
    public static void main(String[] args) {

        StdDraw.setCanvasSize(800, 500);
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(-1, 9.0);
        StdDraw.enableDoubleBuffering();

        Environment mainObject = new Environment();
        mainObject.runGame();

    }
}