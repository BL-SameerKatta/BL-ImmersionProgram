class Animal {
    public void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog Barks");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Cat Meows");
    }
}

public class Day13_PolymorphismDemo {
    public static void main(String[] args) {
        Animal[] animals = {new Dog(), new Cat()};

        for (Animal animal : animals) {
            animal.sound();
        }
    }
}