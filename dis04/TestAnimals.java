public class TestAnimals {
    public static void main(String[] args) {
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);

        a.greet();          // (A) Animal Pluto says: Huh?
        c.greet();          // (B) Cat Garfield says: Meow!
        d.greet();          // (C) Dog Fido says: WOOF!
        a = c;
        ((Cat) a).greet();  // (D) Cat Garfield says: Meow!
        a.greet();          // (E) Cat Garfield says: Meow!

        // a = new Dog("Spot", 10);
        // d = a;

        // Why would this code produce a compiler error? How could we fix this error? This
        // code produces a compiler error in the second line. The static type of d is Dog while
        // the static type of a is Animal. Dog is a subclass of Animal, so this assignment will
        // fail at compile time because not all Animals are Dogs. Use casting to address the
        // problem.

        // d = (Dog) a;
        // This represents a promise to the compiler that at runtime, a will be bound to an
        // object that is compatible with the Dog type.
    }
}