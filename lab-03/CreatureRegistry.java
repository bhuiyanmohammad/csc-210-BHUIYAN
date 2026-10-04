import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program 2: CreatureRegistry
 * Takes the work from ProcessCreatureFile and turns it into a reusable class.
 * The creatures are kept in a private ArrayList (encapsulation),
 * and the class gives methods to count, get, add, modify, delete, and save.
 */
public class CreatureRegistry {

    private static final String HEADER = "name,species,age,weight,size";

    private String fileName;
    private ArrayList<Creature> creatures;

    // Constructor: reads all creatures from the file into the ArrayList
    public CreatureRegistry(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.creatures = new ArrayList<>();

        try (Scanner reader = new Scanner(new File(fileName))) {
            if (reader.hasNextLine()) {
                reader.nextLine(); // skip the header row
            }
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty()) {
                    creatures.add(Creature.fromCSV(line));
                }
            }
        }
    }

    // How many creatures are in the registry
    public int count() {
        return creatures.size();
    }

    // Gives back a COPY of the creature, so changing it won't change our data
    public Creature getCreature(int index) {
        checkIndex(index);
        return new Creature(creatures.get(index));
    }

    // Replaces the creature at the index with new values
    public void modifyCreature(int index, Creature updated) {
        checkIndex(index);
        creatures.set(index, new Creature(updated));
    }

    // Removes the creature at the index
    public void deleteCreature(int index) {
        checkIndex(index);
        creatures.remove(index);
    }

    // Adds a new creature to the end of the list
    public void addCreature(Creature creature) {
        creatures.add(new Creature(creature));
    }

    // Writes all creatures back to the file
    public void save() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(HEADER);
            for (Creature c : creatures) {
                writer.println(c.toCSV());
            }
        }
    }

    // Throws an exception if the index is not valid
    private void checkIndex(int index) {
        if (index < 0 || index >= creatures.size()) {
            throw new IndexOutOfBoundsException(
                "No creature at index " + index + " (there are " + creatures.size() + " creatures)");
        }
    }

    // Main program to test that the class works.
    // It makes a test copy of the data file so the real file is not changed.
    public static void main(String[] args) {
        String testFile = "registry-test.csv";

        try {
            // Make a test file to work with
            try (PrintWriter writer = new PrintWriter(testFile)) {
                writer.println(HEADER);
                writer.println("Sparky,Dragon,120,900,10");
                writer.println("Bubbles,Fish,2,1,1");
                writer.println("Rocky,Golem,500,2000,9");
            }

            CreatureRegistry registry = new CreatureRegistry(testFile);
            System.out.println("Test 1 - count after loading (expect 3): " + registry.count());

            // Test that getCreature gives a copy
            Creature copy = registry.getCreature(0);
            copy.setName("Changed");
            System.out.println("Test 2 - copy changed to: " + copy.getName()
                + ", registry still has (expect Sparky): " + registry.getCreature(0).getName());

            // Test add
            registry.addCreature(new Creature("Hoot", "Owl", 6, 3, 1));
            System.out.println("Test 3 - count after add (expect 4): " + registry.count());

            // Test modify
            registry.modifyCreature(1, new Creature("Bubbles", "Fish", 3, 2, 1));
            System.out.println("Test 4 - modified creature 1: " + registry.getCreature(1));

            // Test delete
            registry.deleteCreature(2);
            System.out.println("Test 5 - count after delete (expect 3): " + registry.count());

            // Test save and reload
            registry.save();
            CreatureRegistry reloaded = new CreatureRegistry(testFile);
            System.out.println("Test 6 - count after save and reload (expect 3): " + reloaded.count());
            for (int i = 0; i < reloaded.count(); i++) {
                System.out.println("    [" + i + "] " + reloaded.getCreature(i));
            }

            // Test bad index
            try {
                reloaded.getCreature(99);
                System.out.println("Test 7 - FAILED, no exception thrown");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Test 7 - bad index threw an exception as expected: " + e.getMessage());
            }

            // Clean up the test file
            new File(testFile).delete();
            System.out.println("All tests finished.");

        } catch (FileNotFoundException e) {
            System.err.println("File problem: " + e.getMessage());
            System.exit(1);
        }
    }
}
