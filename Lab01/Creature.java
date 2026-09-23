public class Creature {
    String name;

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