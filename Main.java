import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Toy Robot Simulator</h1>
 * The Robot program implements an application that
 * places a robot on a table and moves it around and prints
 * the output on the screen.
 * <p>
 * @author  Taylan Karaman
 * @version 1.0
 * @since   2017-06-8
 */
public class Main {

    /**
     * This is the main method which takes user input and makes use of JavaRobot class.
     * @param args Unused.
     */
    public static void main(String[] args) {

        Pattern input_pattern = Pattern.compile("^QUIT|^MOVE|^LEFT|^RIGHT|^REPORT|^PLACE\\s[0-4],[0-4],(EAST|WEST|NORTH|SOUTH)",Pattern.CASE_INSENSITIVE);
        Pattern command_pattern = Pattern.compile("QUIT|MOVE|LEFT|RIGHT|REPORT|PLACE",Pattern.CASE_INSENSITIVE);
        Pattern direction_pattern = Pattern.compile("EAST|WEST|NORTH|SOUTH",Pattern.CASE_INSENSITIVE);
        JavaRobot robot = new JavaRobot();

        System.out.println("ROBOT\n ");
        System.out.println("Valid commands: PLACE [X,Y,DIRECTION] / MOVE / LEFT / RIGHT / REPORT / QUIT\n\n ");

        do{
            System.out.println("Enter command: ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            Matcher m = input_pattern.matcher(input);

            if(!m.matches()){
                System.out.println("Please enter a valid command.");
                continue;
            }

            Scanner scanner_command = new Scanner(input);
            String command = scanner_command.findInLine(command_pattern);
            command = command.toUpperCase();
            
            if(command.equals("QUIT")) break;

            if(command.equals("PLACE")) {
                String parameters = input.split(" ")[1];
                Scanner scanner_parameters = new Scanner(parameters);
                int x = Integer.parseInt(scanner_parameters.findInLine("\\d"));
                int y = Integer.parseInt(scanner_parameters.findInLine("\\d"));
                String facing = scanner_parameters.findInLine(direction_pattern);
                robot.placeRobot(x, y, facing);
            }else{
                if (!robot.isRobotOnTable()) System.out.println("You need to PLACE the robot first.\n");
                else{
                    if(command.equals("MOVE")) robot.move();
                    if(command.equals("LEFT")) robot.turn(JavaRobot.RotationDirection.COUNTERCLOCKWISE);
                    if(command.equals("RIGHT")) robot.turn(JavaRobot.RotationDirection.CLOCKWISE);
                    if(command.equals("REPORT")) System.out.println(robot.report());
                }
            }
        }
        while (true);

        System.exit(0);
    }
}