package util;

public interface LinkedListADT<T> {

    // check if the list is empty
    boolean isEmpty();

    // get the size of the list
    int size();

    // add a generic object to the end of the list
    void add(T element);

    // add a generic object in its correct sorted position in the list
    void addSorted(T element);

    // delete a generic object from the list
    void delete(T element);

    // display all the objects in the list
    void display();

    // get the first element in the list
    T getFirst();

    // get the last element in the list
    T getLast();

    // get all elements in the list as an array
    T[] getAllElements();
}
