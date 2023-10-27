public class Bar {
    /**
     * Bar class is very simple
     * it is used to create a bar and draw it in the linearly changing color
     */
    private static final int TOTAL_GAME_DURATION = 40000;

    // Constructor
    Bar() {
    }

    // Other method(s)
    public void drawShrinkingBar(double timePassed) {
        StdDraw.setPenColor(225,(int) (225-225*timePassed/TOTAL_GAME_DURATION),0);
        StdDraw.filledRectangle(8-8.0*timePassed/TOTAL_GAME_DURATION,-0.45,8-8.0*timePassed/TOTAL_GAME_DURATION,0.28);
    }

}
