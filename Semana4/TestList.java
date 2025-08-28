package Semana4;

public class TestList {
    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        myList.add("Objeto 1");
        myList.add("Objeto 2");
        myList.add("Objeto 3");
        myList.add("Objeto 4");
        myList.add("Objeto 5");
        myList.add("Objeto 6");
        System.out.println(myList.size());
        System.out.println(myList);
        myList.delete(2);
        System.out.println(myList);
    }
}
