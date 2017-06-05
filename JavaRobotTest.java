import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JavaRobotTest {
    @Test
    void placeRobot() {
        JavaRobot robot = new JavaRobot();
        robot.placeRobot(3, 2, String.valueOf(JavaRobot.Orientation.NORTH));
        String result = robot.report();
        assertEquals("Output: 3,2,NORTH", result);
    }

    @Test
    void isRobotOnTable() {
        JavaRobot robot = new JavaRobot();
        robot.placeRobot(3, 2, String.valueOf(JavaRobot.Orientation.NORTH));
        assertEquals(true, robot.isRobotOnTable());
    }

    @Test
    void turn() {
        JavaRobot robot = new JavaRobot();
        robot.placeRobot(3, 2, String.valueOf(JavaRobot.Orientation.NORTH));
        robot.turn(JavaRobot.RotationDirection.CLOCKWISE);
        String result = robot.report();
        assertEquals("Output: 3,2,EAST", result);

        robot.turn(JavaRobot.RotationDirection.COUNTERCLOCKWISE);
        result = robot.report();
        assertEquals("Output: 3,2,NORTH", result);
    }

    @Test
    void move() {
        JavaRobot robot = new JavaRobot();
        robot.placeRobot(3, 2, String.valueOf(JavaRobot.Orientation.NORTH));
        robot.move();
        String result = robot.report();
        assertEquals("Output: 3,3,NORTH", result);

        robot.turn(JavaRobot.RotationDirection.COUNTERCLOCKWISE);
        robot.move();
        result = robot.report();
        assertEquals("Output: 2,3,WEST", result);
    }

}