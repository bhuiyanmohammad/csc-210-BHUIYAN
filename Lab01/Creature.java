package Lab01;
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

    public Creature(String creatureName) {
        name = creatureName;

    }

    public void speak() {
        System.out.println(name + " says hello!");
    }
        public static void main(String[] args) {
        Creature myCreature = new Creature("Dragon");
        myCreature.speak();
    }
}