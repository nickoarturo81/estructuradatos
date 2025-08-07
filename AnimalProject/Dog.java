public class Dog implements Animal {

    // Atributes
    private String breed;
    private String size;

    // Constructor
    public Dog(String breed, String size) {
        this.breed = breed;
        this.size = size;
    }
    //Implementation of eat() from Animal interface
    @Override
    public void eat() {
        System.out.println("The dog eat a bone.");
    }

    //Implementation of sleep() from Animal interface
    @Override
    public void sleep() {
        System.out.println("The dog is sleeping");
    }

    @Override
    public void makeSound() {
        System.out.println("The dog says guau guau and scares a thief");
    }

    @Override
    public void move() {
        System.out.println("The dog runs very fast");
    }

    //getter

    public String getBreed() {
        return breed;
    }

    public String getSize() {
        return size;
    }
}