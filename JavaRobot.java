/**
 * <h1>JavaRobot class</h1>
 * The JavaRobot class creates the robot, holds it's position on the table
 * and manipulates it's position and facing on the table
 * <p>
 *
 * @author  Taylan Karaman
 * @version 1.0
 * @since   2017-06-8
 */

public class JavaRobot {
    private final Coordinates coordinates = new Coordinates();
    private boolean isRobotOnTable = false;
    private Orientation robotOrientation = null;

    public enum Orientation{
        // Each direction has two ordinal values
        // to determine the next direction the
        // robot will be facing when it is turned
        NORTH(1,3),
        EAST (2,0),
        SOUTH(3,1),
        WEST (0,2);

        private int nextClockWise = 0;
        private int nextCounterClockwise = 0;

        Orientation(int nextClockWise, int nextCounterClockwise){
            this.nextClockWise = nextClockWise;
            this.nextCounterClockwise = nextCounterClockwise;
        }

        public Orientation turn(RotationDirection rotationDirection){

            if(rotationDirection == RotationDirection.CLOCKWISE ){
                return Orientation.values()[this.nextClockWise];
            }else{
                return Orientation.values()[this.nextCounterClockwise];
            }
        }
    }
    public enum RotationDirection { CLOCKWISE, COUNTERCLOCKWISE}
    public enum OperationType { INCREMENT, DECREMENT}
    public enum Path { X_AXIS, Y_AXIS}

    /**
     * This method is used to place the robot on the table
     * or relocate it to another position if already placed.
     * @param x This is the x coordinate of the point that the robot will be placed
     * @param y This is the y coordinate of the point that the robot will be placed
     * @param facing This is the direction that the robot will be facing when placed
     */
    void placeRobot(int x, int y, String facing){
        if(!isRobotOnTable) isRobotOnTable = true;
        this.coordinates.setCoordinates(x, y);
        this.robotOrientation = Orientation.valueOf(facing.toUpperCase());
    }

    /**
     * This method is used to check if the robot is on the table or not
     * @return boolean This returns a boolean if the robot is on the table or not
     */
    boolean isRobotOnTable() {
        return isRobotOnTable;
    }

    /**
     * This method is used to report the current position and the facing of the robot
     * @return String This returns the position and orientation of the robot
     */
    String report() {
        return "Output: " + this.coordinates.xCoordinate + ","
                                      + this.coordinates. yCoordinate + ","
                                      + this.robotOrientation;
    }

    /**
     * This method is used to change the orientation of the robot
     * @param rotation This is the enum constant depicting the direction of the turn
     */
    void turn(RotationDirection rotation) {
        robotOrientation = robotOrientation.turn(rotation);
    }

    void move() {
        if(robotOrientation == Orientation.NORTH) coordinates.changeCoordinates(OperationType.INCREMENT, Path.Y_AXIS);
        if(robotOrientation == Orientation.SOUTH) coordinates.changeCoordinates(OperationType.DECREMENT, Path.Y_AXIS);
        if(robotOrientation == Orientation.EAST) coordinates.changeCoordinates(OperationType.INCREMENT, Path.X_AXIS);
        if(robotOrientation == Orientation.WEST) coordinates.changeCoordinates(OperationType.DECREMENT, Path.X_AXIS);
    }

    private class Coordinates{
        private int xCoordinate = 0;
        private int yCoordinate = 0;

        void setCoordinates(int x, int y){
            xCoordinate = x;
            yCoordinate = y;
        }

        void changeCoordinates(OperationType ot, Path pt){
            if(ot == OperationType.INCREMENT){
                if(pt == Path.X_AXIS && xCoordinate <= 4){
                    this.xCoordinate++;
                }
                else if( pt == Path.Y_AXIS && yCoordinate <= 4){
                    this.yCoordinate++;
                }
            }else{
                if(pt == Path.X_AXIS && xCoordinate >= 1){
                    this.xCoordinate--;
                }
                else if( pt == Path.Y_AXIS && yCoordinate >= 1){
                    this.yCoordinate--;
                }
            }
        }
    }
}