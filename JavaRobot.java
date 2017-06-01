/**
 * JavaRobot class
 */

public class JavaRobot {
    private Coordinates coordinates = new Coordinates(0,0);
    private boolean robotPlacedOnTable = false;
    private Orientation robotOrientation = null;

    public enum Orientation{
        // Each direction has two ordinal values
        // to determine the next direction the
        // robot will be facing when it is turned
        NORTH(1,3),
        EAST(2,0),
        SOUTH(3,1),
        WEST(0,2);

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

    void placeRobot(int x, int y, String facing){
        relocateRobot(x, y, facing);
        robotPlacedOnTable = true;
    }

    void relocateRobot(int x, int y, String facing){
        this.coordinates.xCoordinate = x;
        this.coordinates.yCoordinate = y;
        this.robotOrientation = Orientation.valueOf(facing);
    }

    boolean isRobotOnTable(){
        return robotPlacedOnTable;
    }

    void report() {
        System.out.println("Output: " + this.coordinates.xCoordinate + ","
                                      + this.coordinates. yCoordinate + ","
                                      + this.robotOrientation);
    }

    void turn(RotationDirection rotation) {
        robotOrientation = robotOrientation.turn(rotation);
    }

    void move() {
        switch (robotOrientation) {
            case NORTH:
                coordinates.changeCoordinates(OperationType.INCREMENT, Path.Y_AXIS);
                break;
            case SOUTH:
                coordinates.changeCoordinates(OperationType.DECREMENT, Path.Y_AXIS);
                break;
            case EAST:
                coordinates.changeCoordinates(OperationType.INCREMENT, Path.X_AXIS);
                break;
            case WEST:
                coordinates.changeCoordinates(OperationType.DECREMENT, Path.X_AXIS);
                break;
            default:
                System.out.println("Invalid direction!\n");
                break;
        }
    }

    private class Coordinates{
        private int xCoordinate = 0;
        private int yCoordinate = 0;

        Coordinates(int x, int y){
            xCoordinate = x;
            yCoordinate = y;
        }

        void changeCoordinates(OperationType ot, Path pt){
            if(ot == OperationType.INCREMENT){
                if(pt == Path.X_AXIS && xCoordinate < 5){
                    this.xCoordinate++;
                }
                else if( pt == Path.Y_AXIS && yCoordinate < 5){
                    this.yCoordinate++;
                }
            }else{
                if(pt == Path.X_AXIS && xCoordinate > 0){
                    this.xCoordinate--;
                }
                else if( pt == Path.Y_AXIS && yCoordinate > 0){
                    this.yCoordinate--;
                }
            }
        }
    }
}