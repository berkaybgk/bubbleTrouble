public class Arrow {

    /**
     * Arrow class is used to create and move the arrow,
     * Remember that there can be only a single arrow during the game
     */

    private double xCoordinate;
    private double yCoordinate;
    private static final int PERIOD_OF_ARROW = 1500; //The total time required for an arrow to traverse through the Y axis excluding bar.

    //Constructors
    Arrow () {}
    Arrow(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    //Getters and setters
    public double getxCoordinate() {
        return xCoordinate;
    }
    public double getyCoordinate() {
        return yCoordinate;
    }
    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

}
