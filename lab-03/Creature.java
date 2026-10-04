public class Creature {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int newWeight) {
        weight = newWeight;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        age = newAge;
    }

    private String species;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String newSpecies) {
        species = newSpecies;
    }

    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int newSize) {
        size = newSize;
    }

    public Creature(String creatureName) {
        name = creatureName;
    }

    // ---- Added for Lab 03 ----

    // Constructor that sets every property at once
    public Creature(String creatureName, String creatureSpecies, int creatureAge, int creatureWeight, int creatureSize) {
        name = creatureName;
        species = creatureSpecies;
        age = creatureAge;
        weight = creatureWeight;
        size = creatureSize;
    }

    // Copy constructor - makes a brand new Creature with the same values
    public Creature(Creature other) {
        this(other.name, other.species, other.age, other.weight, other.size);
    }

    // Turns this creature into one line of CSV text
    // Order: name,species,age,weight,size
    public String toCSV() {
        return name + "," + species + "," + age + "," + weight + "," + size;
    }

    // Builds a creature from one line of CSV text
    public static Creature fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Bad creature line: " + line);
        }
        String name = parts[0].trim();
        String species = parts[1].trim();
        int age = Integer.parseInt(parts[2].trim());
        int weight = Integer.parseInt(parts[3].trim());
        int size = Integer.parseInt(parts[4].trim());
        return new Creature(name, species, age, weight, size);
    }

    @Override
    public String toString() {
        return name + " the " + species + " (age: " + age + ", weight: " + weight + ", size: " + size + ")";
    }

    // ---- End of Lab 03 additions ----

    public void speak() {
        System.out.println(name + " says hello!");
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void move() {
        System.out.println(name + " is moving around.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    public static void main(String[] args) {
        Creature myCreature = new Creature("Dragon");
        myCreature.speak();
        myCreature.eat();
        myCreature.move();
        myCreature.sleep();
    }
}
