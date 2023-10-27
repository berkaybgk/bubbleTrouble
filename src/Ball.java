public class Ball {

    /**
     * Ball class is used to create, move and display balls
     * It also has a creationTime instance variable which allows us to project the motion of the balls.
     */

    private double xCoordinate;
    private double yCoordinate;
    private int levelOfBall;
    private int creationTime;
    private double xVelocity;
    private double yVelocity;
    private static final double RADIUS_MULTIPLIER = 2; // The multiplier we use to calculate the radius that different levels of balls can have.
    private final double MIN_POSSIBLE_HEIGHT = 34/27.0 * 1.4; //player’s height in scale * 1.4, minimum possible heights differ among the levels
    private static final double MIN_POSSIBLE_RADIUS = 500*0.0175;
    private static final double GRAVITY = 0.000003; // m/s^2


    //No-arg constructor
    Ball() {
    }

    /*
    * The constructor which has 3 parameters generate a ball
    * X,Y are coordinates scaled proportional to the canvas
    * level of the ball determines its height and radius and takes the values 0-1-2
     */
    Ball(double xCoordinate, double yCoordinate, int levelOfBall) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.levelOfBall = levelOfBall;
    }

    //Getters and Setters
    public double getxCoordinate() {
        return xCoordinate;
    }
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public double getyCoordinate() {
        return yCoordinate;
    }
    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    public int getLevelOfBall() {
        return levelOfBall;
    }
    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }
    public double getxVelocity() {
        return xVelocity;
    }
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }
    public double returnScaledRadius() {
        if (levelOfBall == 2)
            return 0.64;
        else if (levelOfBall == 1)
            return 0.35;
        else
            return 0.18;
    }
    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
    public static double getGravity() {
        return GRAVITY;
    }

    //Methods
    public void drawBall(double xCoordinate, double yCoordinate, int levelOfBall, String ballImage) {
        if (levelOfBall == 2)
            StdDraw.picture(xCoordinate,yCoordinate,ballImage,1.28,1.28); // scale it according to the level of the ball!
        else if (levelOfBall == 1)
            StdDraw.picture(xCoordinate,yCoordinate,ballImage,0.7,0.7); // scale it according to the level of the ball!
        else if (levelOfBall == 0)
            StdDraw.picture(xCoordinate,yCoordinate,ballImage,0.36,0.36); // scale it according to the level of the ball!
    }
}
