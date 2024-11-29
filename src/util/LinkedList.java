/*
 * File Name: LinkedList.java
 * Author: Lesbek Tamirlan
 * Description: list implementation for Employee objects.
 */

package util;

import application.Employee;
import java.util.ArrayList;
import java.util.List;


public class LinkedList<T> {

    private LinearNode<T> head;  // first node in the list
    private int size;  // size of the list

    // Constructor to find an empty list
    public LinkedList() {
        head = null;
        size = 0;
    }

    // check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // return the size of the list
    public int size() {
        return size;
    }

    // add object if not exist
    public void add(T element) {
        if (contains(element)) {
            System.out.println("Error: The object already exists in the list.");
            return;
        }

        LinearNode<T> newNode = new LinearNode<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            LinearNode<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    // add object if correct
    public void addSorted(Employee employee) {
        LinearNode<Employee> newNode = new LinearNode<>(employee);

        if (isEmpty() || ((Employee) head.getData()).compareTo(employee) > 0) {
            newNode.setNext((LinearNode<Employee>) head);
            head = (LinearNode<T>) newNode;
        } else {
            LinearNode<T> current = head;
            while (current.getNext() != null && employee.compareTo((Employee) current.getNext().getData()) > 0) {
                current = current.getNext();
            }
            newNode.setNext((LinearNode<Employee>) current.getNext());
            current.setNext((LinearNode<T>) newNode);
        }
        size++;
    }

    // add an object at a specific position in the list
    public void add(T element, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Error: Invalid position.");
            return;
        }

        LinearNode<T> newNode = new LinearNode<>(element);

        if (position == 1) {
            // insert at the beginning
            newNode.setNext(head);
            head = newNode;
        } else {
            LinearNode<T> current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }

    // delete an object from the list if matches
    public void delete(T element) {
        if (isEmpty()) {
            System.out.println("Error: Cannot delete from an empty list.");
            return;
        }

        // check if the head needs to be removed
        if (head.getData().equals(element)) {
            head = head.getNext();
            size--;
        } else {
            LinearNode<T> current = head;
            while (current.getNext() != null && !current.getNext().getData().equals(element)) {
                current = current.getNext();
            }
            if (current.getNext() != null) {
                current.setNext(current.getNext().getNext());
                size--;
            } else {
                System.out.println("Error: Element not found in the list.");
            }
        }
    }

    // method to display all elements in the list
    public void display() {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        LinearNode<T> current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    // get the first element in the list
    public T getFirst() {
        return isEmpty() ? null : head.getData();
    }

    // get the last element in the list
    public T getLast() {
        if (isEmpty()) {
            return null;
        }

        LinearNode<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current.getData();
    }

    // get the next element in the list
    public T getNext(T currentElement) {
        LinearNode<T> current = head;
        while (current != null) {
            if (current.getData().equals(currentElement) && current.getNext() != null) {
                return current.getNext().getData();
            }
            current = current.getNext();
        }
        return null;  // if no next element exists
    }

    // check if the list already contains a specific object
    public boolean contains(T element) {
        LinearNode<T> current = head;
        while (current != null) {
            if (current.getData().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // get all elements as an array
    public T[] getAllElements() {
        T[] elements = (T[]) new Object[size];
        LinearNode<T> current = head;
        int i = 0;
        while (current != null) {
            elements[i] = current.getData();
            current = current.getNext();
            i++;
        }
        return elements;
    }

    // get all employees from the list 
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        LinearNode<T> current = head;

        while (current != null) {
            if (current.getData() instanceof Employee) {
                employeeList.add((Employee) current.getData());
            }
            current = current.getNext();
        }

        return employeeList;
    }
}
