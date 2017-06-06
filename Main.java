import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main class with main function
 */
class Main {
    public static void main(String[] args) {

        boolean quit = false;
        Pattern command_pattern = Pattern.compile("^QUIT|^MOVE|^LEFT|^RIGHT|^REPORT|^PLACE\\s[0-4],[0-4],[a-zA-Z]{4,6}",Pattern.CASE_INSENSITIVE);
        String parameters = "";
        JavaRobot robot = new JavaRobot();

        System.out.println("ROBOT\n ");
        System.out.println("Valid commands: PLACE [X,Y,DIRECTION] / MOVE / LEFT / RIGHT / REPORT / QUIT\n\n ");

        do{
            System.out.println("Enter command: ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            Matcher m = command_pattern.matcher(input);
            if(m.matches()){
                input = input.toUpperCase();
            }else{
                System.out.println("Please enter a valid command.");
                continue;
            }

            Scanner scanner_command = new Scanner(input);
            String command = scanner_command.findInLine("[a-zA-Z]{4,6}");

            if(command.equals("PLACE")) parameters = input.split(" ")[1];

            if(command.equals("QUIT")) {
                quit = true;
                continue;
            }else{
                String facing = "";
                int x = 0;
                int y = 0;

                if (!command.equals("PLACE") && !robot.isRobotOnTable()) {
                    System.out.println("You need to PLACE the robot first.\n");
                    continue;
                } else if (command.equals("PLACE")) {

                    Scanner scanner_input = new Scanner(parameters);
                    x = Integer.parseInt(scanner_input.findInLine("\\d"));
                    y = Integer.parseInt(scanner_input.findInLine("\\d"));
                    facing = scanner_input.findInLine("[a-zA-Z]{4,6}");

                    robot.placeRobot(x, y, facing);

                } else if (!command.equals("PLACE") && robot.isRobotOnTable()) {

                    if(command.equals("MOVE")) robot.move();
                    if(command.equals("LEFT")) robot.turn(JavaRobot.RotationDirection.COUNTERCLOCKWISE);
                    if(command.equals("RIGHT")) robot.turn(JavaRobot.RotationDirection.CLOCKWISE);
                    if(command.equals("REPORT")) System.out.println(robot.report());
                }else{
                    System.out.println("Invalid command.\n");
                }
            }
        }
        while (!quit);

        System.exit(0);
    }
}