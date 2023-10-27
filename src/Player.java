public class Player {

    /**
     * Player class is used to create, move and display the player model
     */

    private static final String modelFile = "player_back.png";
    private double xCoordinate;
    private double yCoordinate;
    private static final int PERIOD_OF_PLAYER = 6000;
    private static final double PLAYER_HEIGHT_WIDTH_RATE = 37.0/27.0;
    private static final double PLAYER_HEIGHT_SCALE_RATE = 1.0/8.0;

    //Constructor
    Player() {
        xCoordinate = 8;
        yCoordinate = 0.7;
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
    public static int getPERIOD_OF_PLAYER() {
        return PERIOD_OF_PLAYER;
    }
    public static String getModelFile() {
        return modelFile;
    }

    //Other method(s)
    public void drawPlayer(double xCoordinate, double yCoordinate, String modelFile) {
        StdDraw.picture(xCoordinate,yCoordinate,modelFile,0.7,31/27.0);
    }

}
