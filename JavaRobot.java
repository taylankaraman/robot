/**
 * JavaRobot class
 */
public class JavaRobot {
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private boolean robotPlacedOnTable = false;
    private String facingDirection = "";

    public enum Rotation{ CLOCKWISE, ANTICLOCKWISE}

    public JavaRobot() {
    }

    public void placeRobot(int x, int y, String facing){
        relocateRobot(x, y, facing);
        robotPlacedOnTable = true;
    }

    public void relocateRobot(int x, int y, String facing){
        xCoordinate = x;
        yCoordinate = y;
        facingDirection = facing;
    }

    public boolean isRobotOnTable(){
        return robotPlacedOnTable;
    }

    public void report() {
        System.out.println("Output: " + this.xCoordinate + "," + this.yCoordinate + "," + this.facingDirection);
    }

    public void turn(Rotation rotation) {

        if(this.facingDirection.equals("NORTH")){
            this.facingDirection = rotation == Rotation.CLOCKWISE ? "EAST" : "WEST";
            return;
        }
        if(this.facingDirection.equals("WEST")){
            this.facingDirection = rotation == Rotation.CLOCKWISE ? "NORTH" : "SOUTH";
            return;
        }
        if(this.facingDirection.equals("SOUTH")){
            this.facingDirection = rotation == Rotation.CLOCKWISE ? "WEST" : "EAST";
            return;
        }
        if(this.facingDirection.equals("EAST")){
            this.facingDirection = rotation == Rotation.CLOCKWISE ? "SOUTH" : "NORTH";
            return;
        }
    }

    public void move() {
        switch (this.facingDirection) {
            case "NORTH":
                if(this.yCoordinate + 1 > 5) {
                    System.out.println("Robot cannot go off the table!\n");
                }else{
                    this.yCoordinate += 1;
                }
                break;
            case "SOUTH":
                if(this.yCoordinate - 1 < 0) {
                    System.out.println("Robot cannot go off the table!\n");
                }else{
                    this.yCoordinate -= 1;
                }
                break;
            case "EAST":
                if(this.xCoordinate + 1 > 5) {
                    System.out.println("Robot cannot go off the table!\n");
                }else{
                    this.xCoordinate += 1;
                }
                break;
            case "WEST":
                if(this.xCoordinate - 1 < 0) {
                    System.out.println("Robot cannot go off the table!\n");
                }else{
                    this.xCoordinate -= 1;
                }
                break;
            default:
                System.out.println("Invalid direction!\n");
                break;
        }
    }
}