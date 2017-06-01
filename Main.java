import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main class with main function
 */
public class Main {
    public static void main(String[] args) {

        List<String> validCommands = new ArrayList<>(Arrays.asList("PLACE", "MOVE", "LEFT", "RIGHT", "REPORT", "QUIT"));
        List<String> validDirections = new ArrayList<>(Arrays.asList("NORTH", "EAST", "SOUTH", "WEST"));
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        JavaRobot robot = new JavaRobot();

        do{
            System.out.println("Enter command(QUIT to exit): ");

            String command = "";
            String input[] = scanner.nextLine().split(" ");

            if(input.length > 0) {
                command = input[0].toUpperCase();
            }

            if(command.equals("QUIT")) {
                quit = true;
                continue;
            }else if(!validCommands.contains(command)){
                System.out.println("Please enter a valid command. PLACE X,Y,DIRECTION / MOVE / LEFT / RIGHT / REPORT\n");
                continue;
            }else{
                String facing = "";
                int x = 0;
                int y = 0;

                // The robot is not on the table yet
                if (!command.equals("PLACE") && !robot.isRobotOnTable()) {
                    System.out.println("You need to PLACE the robot first.\n");
                    continue;
                } else if (command.equals("PLACE")) { // Put the robot on the table

                    String parameters;
                    if(input.length > 1){
                        parameters = input[1];
                    }else {
                        System.out.println("PLACE commands needs parameters. X,Y,DIRECTION");
                        continue;
                    }

                    String[] parts = parameters.split(",");

                    if(parts.length == 3){
                        x = Integer.parseInt(parts[0]);
                        y = Integer.parseInt(parts[1]);
                        facing = parts[2].toUpperCase();
                    } else {
                        System.out.println("PLACE commands needs parameters. X,Y,DIRECTION");
                        continue;
                    }

                    if( x >= 0 && x < 6 && y >= 0 && y < 6 && validDirections.contains(facing.toUpperCase())){

                        if(!robot.isRobotOnTable()){
                            robot.placeRobot(x, y, facing);
                        }else{
                            robot.relocateRobot(x, y, facing);
                        }

                    } else {
                        System.out.println("PLACE command has incorrect parameters. Cannot place robot.");
                        continue;
                    }

                    // The robot is already on the table
                } else if (!command.equals("PLACE") && robot.isRobotOnTable()) {

                    if(command.equals("MOVE")) robot.move();
                    if(command.equals("LEFT")) robot.turn(JavaRobot.RotationDirection.COUNTERCLOCKWISE);
                    if(command.equals("RIGHT")) robot.turn(JavaRobot.RotationDirection.CLOCKWISE);
                    if(command.equals("REPORT")) robot.report();
                }else{
                    System.out.println("Invalid command.\n");
                }
            }
        }
        while (!quit);

        System.exit(0);
    }
}