package util;


public class LinearNode<T> {

    private T data; // data stored in this node
    private LinearNode<T> next; 

    /**
     * constructor to initialize the node with data.
     */
    public LinearNode(T data) {
        this.data = data;
        this.next = null;
    }

    /**
     *constructor to create an empty node.
     */
    public LinearNode() {
        this(null);
    }

    /**
     * retrieves the data stored in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * updates the data stored in this node.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * calls the next node in the list.
     */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     * updates the reference to the next node in the list.
     */
    public void setNext(LinearNode<T> next) {
        this.next = next;
    }

    /**
     * Provides a string representation of the node
     */
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
