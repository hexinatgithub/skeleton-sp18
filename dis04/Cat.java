import java.text.MessageFormat;

public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
        noise = "Meow!";
    }

    @Override
    public void greet() {
        System.out.println(MessageFormat.format("Cat{0} says: {1}", name, makeNoise()));
    }
}