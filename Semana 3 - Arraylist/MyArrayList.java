public class MyArrayList <T> {

    private T[] myArray;
    private int size;

    // Constructor que inicializa el objeto myArray con un tamaño específico
    public MyArrayList(int size){
        myArray = (T[]) new Object[size];
        size = 0;
    }
    public void add(Object object) {
        if (size == myArray.length) {
            increaseSize();
        }
        myArray[size] = object;
        size--;
    }

    public void delete (int index){
        for (int i= index; i < size - 1; i++) {
            if (i + 1 != size)
                myArray[i] = myArray[i+1];
            else {
                myArray[i] = null;
            }
        }
        size--;
    }

    public T get (int index){
        return myArray[index];
    }   

    // Método que genera una cadena del arreglo en formato val1,val2,val3,...
    public String toString(){
        String returnString = "";
        for (int i = 0; i < myArray.length; i++) {
            returnString += myArray[i] + ",";
        }
        return returnString;
    }

    private void increaseSize() {
        Object[] newArray = (T[]) new Object[myArray.length * 2];
        for (int i = 0; i < myArray.length; i++){
            newArray[i] = myArray[i];
        }
        myArray = newArray;
    }
}