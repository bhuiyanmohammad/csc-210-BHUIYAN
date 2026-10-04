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