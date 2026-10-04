import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program 1: ProcessCreatureFile
 * Reads creatures from creature-data.csv into an ArrayList,
 * adds, removes, and changes some creatures,
 * then writes the updated list back to the same file.
 */
public class ProcessCreatureFile {

    public static void main(String[] args) {
        String fileName = "creature-data.csv";
        ArrayList<Creature> creatures = new ArrayList<>();
        String header = "name,species,age,weight,size";

        // Step 1: read the file into the ArrayList
        try (Scanner reader = new Scanner(new File(fileName))) {
            if (reader.hasNextLine()) {
                header = reader.nextLine(); // first line is the header row
            }
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty()) {
                    creatures.add(Creature.fromCSV(line));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find the file: " + fileName);
            System.exit(1);
        }

        System.out.println("Creatures read from the file:");
        printAll(creatures);

        // Step 2: add some creatures
        creatures.add(new Creature("Shelly", "Turtle", 80, 40, 3));
        creatures.add(new Creature("Blaze", "Phoenix", 300, 25, 5));
        System.out.println("\nAfter adding Shelly and Blaze:");
        printAll(creatures);

        // Step 3: remove some creatures
        creatures.remove(1); // removes the second creature in the list
        System.out.println("\nAfter removing the creature at index 1:");
        printAll(creatures);

        // Step 4: change some properties of existing creatures
        creatures.get(0).setAge(creatures.get(0).getAge() + 1);   // Sparky has a birthday
        creatures.get(2).setName("Mittens");                      // rename Whiskers
        creatures.get(2).setWeight(12);                           // Mittens gained weight
        System.out.println("\nAfter changing some properties:");
        printAll(creatures);

        // Step 5: write everything back to the file
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(header);
            for (Creature c : creatures) {
                writer.println(c.toCSV());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not write to the file: " + fileName);
            System.exit(1);
        }

        System.out.println("\nSaved " + creatures.size() + " creatures back to " + fileName);
    }

    // Helper method to print every creature with its index
    private static void printAll(ArrayList<Creature> creatures) {
        for (int i = 0; i < creatures.size(); i++) {
            System.out.println("  [" + i + "] " + creatures.get(i));
        }
    }
}
