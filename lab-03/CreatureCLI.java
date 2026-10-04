import java.io.FileNotFoundException;

/**
 * Program 3: CreatureCLI
 * A command line program to create, read, update, and delete creatures in a CSV file.
 * It uses the CreatureRegistry class to do the actual work.
 *
 * Usage:
 *   java CreatureCLI <file> list
 *   java CreatureCLI <file> read <index>
 *   java CreatureCLI <file> add <name> <species> <age> <weight> <size>
 *   java CreatureCLI <file> update <index> <name> <species> <age> <weight> <size>
 *   java CreatureCLI <file> delete <index>
 */
public class CreatureCLI {

    public static void main(String[] args) {
        // Need at least a file name and a command
        if (args.length < 2) {
            printHelp();
            System.exit(1);
        }

        String fileName = args[0];
        String command = args[1].toLowerCase();

        try {
            CreatureRegistry registry = new CreatureRegistry(fileName);

            switch (command) {
                case "list":
                    checkArgCount(args, 2);
                    if (registry.count() == 0) {
                        System.out.println("No creatures in the file.");
                    }
                    for (int i = 0; i < registry.count(); i++) {
                        System.out.println("[" + i + "] " + registry.getCreature(i));
                    }
                    break;

                case "read":
                    checkArgCount(args, 3);
                    int readIndex = Integer.parseInt(args[2]);
                    System.out.println(registry.getCreature(readIndex));
                    break;

                case "add":
                    checkArgCount(args, 7);
                    Creature newCreature = makeCreature(args, 2);
                    registry.addCreature(newCreature);
                    registry.save();
                    System.out.println("Added: " + newCreature);
                    break;

                case "update":
                    checkArgCount(args, 8);
                    int updateIndex = Integer.parseInt(args[2]);
                    Creature updated = makeCreature(args, 3);
                    registry.modifyCreature(updateIndex, updated);
                    registry.save();
                    System.out.println("Updated creature " + updateIndex + ": " + updated);
                    break;

                case "delete":
                    checkArgCount(args, 3);
                    int deleteIndex = Integer.parseInt(args[2]);
                    Creature removed = registry.getCreature(deleteIndex);
                    registry.deleteCreature(deleteIndex);
                    registry.save();
                    System.out.println("Deleted: " + removed);
                    break;

                default:
                    System.err.println("Unknown command: " + command);
                    printHelp();
                    System.exit(1);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: the file '" + fileName + "' does not exist.");
            System.exit(1);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Error: index, age, weight, and size must be whole numbers.");
            printHelp();
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            printHelp();
            System.exit(1);
        }
    }

    // Builds a Creature from 5 arguments starting at position "start"
    private static Creature makeCreature(String[] args, int start) {
        String name = args[start];
        String species = args[start + 1];
        int age = Integer.parseInt(args[start + 2]);
        int weight = Integer.parseInt(args[start + 3]);
        int size = Integer.parseInt(args[start + 4]);
        if (name.contains(",") || species.contains(",")) {
            throw new IllegalArgumentException("name and species cannot contain commas.");
        }
        return new Creature(name, species, age, weight, size);
    }

    // Makes sure the command got the right number of arguments
    private static void checkArgCount(String[] args, int expected) {
        if (args.length != expected) {
            throw new IllegalArgumentException("wrong number of arguments for '" + args[1] + "'.");
        }
    }

    // Help message for when the program is used the wrong way
    private static void printHelp() {
        System.err.println("Usage:");
        System.err.println("  java CreatureCLI <file> list");
        System.err.println("  java CreatureCLI <file> read <index>");
        System.err.println("  java CreatureCLI <file> add <name> <species> <age> <weight> <size>");
        System.err.println("  java CreatureCLI <file> update <index> <name> <species> <age> <weight> <size>");
        System.err.println("  java CreatureCLI <file> delete <index>");
        System.err.println();
        System.err.println("Example:");
        System.err.println("  java CreatureCLI creature-data.csv add Shelly Turtle 80 40 3");
    }
}
