public class TestMyArray {
    public static void main(String[] args) {
        MyArrayList <String> myArray = new MyArrayList <String>();
        myArray.add("objeto1");
        myArray.add("objeto2");
        myArray.add("objeto3");
        myArray.add("objeto4");
        myArray.add("objeto5");
        myArray.add("objeto6");
        myArray.add("objeto7");
        myArray.add("objeto8");
        myArray.add("objeto9");
        myArray.add("objeto10");
        System.out.println(myArray);

        myArray.delete(4);
        System.out.println(myArray);

        System.out.println(myArray.get(6));
    }
}