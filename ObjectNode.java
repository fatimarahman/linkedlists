// ObjectNode.java
// Similar to IntNode.java except ObjectNode.java uses
// more general Object data in place of int data
// March 2019

public class ObjectNode {

    private Object data;
    private ObjectNode next;

    public ObjectNode() {}  // default constructor

    public ObjectNode(Object data, ObjectNode next) {
        this.data = data;
        this.next = next;
    }

    // accessor methods

    public Object getData() {
        return data;
    }

    public ObjectNode getNext() {
        return next;
    }

    public void setData(Object value) {
        data = value;
    }

    public void setNext(ObjectNode ptr) {
        next = ptr;
    }

    public boolean equals(Object anotherObject) {
        // returns true iff both fields of
        // the corresponding nodes are ==

        if (anotherObject instanceof ObjectNode) {
          ObjectNode temp = (ObjectNode) anotherObject;
          if (data == temp.getData() &&
              next == temp.getNext())
            return true;
        }
        return false;
    }

    public String toString() {
        return data.toString();
    }

    public static void main(String[] args) {
        // tests the ObjectNode class by building a short list of
        // various data items

        ObjectNode start = null;
        ObjectNode ptr;


    } // main

 }  // ObjectNode
