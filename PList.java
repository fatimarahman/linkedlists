import java.util.*;

public class PList implements PListInterface {

  private ObjectNode head;

  public PList(){
    // head is a dummy node, because we're using a headed linked list
    head = new ObjectNode(-1, null);
  }


  public void add(Object item) {
    // adds item to the start of the PList
    // create temp variable to store original starter value
    ObjectNode temp = head.getNext();
    head.setNext(new ObjectNode(item, temp));
  }

  public void append(Object item) {
    // places item at the end of the PList
    ObjectNode runner = head.getNext();
    if (head.getNext() == null){
      // case if list is empty
      head.setNext(new ObjectNode(item, null));
    } else {
      // case if list has items already
      while(runner.getNext() != null){
        runner = runner.getNext();
      } runner.setNext(new ObjectNode(item, null));
    }
  }

  public void concatenate(PListInterface other) {
    // joins plist to this PList to create a longer list
    for (int i = 0; i < other.length(); i++)
      this.append(other.get(i));
  }

  // overloaded concatenate for honors portion
  // public void concatenate(int index){
    // accepts an index into the linked list of plists, concatenate onto primary list
    // PList other = (PList) plists.get(index);
    //  for (int i = 0; i < other.length(); i++)
    //    this.append(other.get(i));
    // honestly, not even necessary to concatenate another list from plists onto primary list!
  // }

  public void delete(int index) {
    // removes the item at index from the PList.
    // if the index does not exist, nothing happens
    ObjectNode trailer = head;
    ObjectNode ptr = head.getNext();
    int idx = 0;
    if (ptr == null){
      // case if list is empty
      System.out.println("The list is empty. Add some items and try again.");
      return;
    } else if (index == 0) {
      // case if you want to delete first item in list
      head.setNext(ptr.getNext());
      return;
    } else {
      // case if you want to remove beyond the first element
      while((index > idx) && (ptr != null)){
        trailer = ptr;
        ptr = ptr.getNext();
        idx++;
      }
      if (idx == index)
        trailer.setNext(ptr.getNext());
    }
  }

  public Object get(int index) {
    // returns the data item at index
    ObjectNode runner = head.getNext();
    int idx = 0;
    if (index == 0){
      // case if you want to return first item in the list
      return runner.getData();
    } else if (index > 0 && index < this.length()) {
      // case if you want to return anything beyond first item
      while((index > idx) && (runner != null)){
        runner = runner.getNext();
        idx++;
      }
      if (idx == index)
        return runner.getData();
    }
    // if index doesn't exist within the list
    return("Index not found.");
  }


  public void insert(Object item, int index) {
    // places item at the specified index
    // if the index is past the end of the list,
    // item is added at the end of the PList
    ObjectNode trailer = head;
    ObjectNode ptr = head.getNext();
    int idx = 0;
    if (index == 0){
      // case if you want to insert at the beginning
      this.add(item);
      return;
    } else if (index >= this.length()){
      // case if the index is past the end of the list
      this.append(item);
      return;
    } else {
        // general case if index is within the list
        while ((idx < index) && (ptr != null)){
          trailer = ptr;
          ptr = ptr.getNext();
          idx++;
        }
        if(index == idx){
          // if the index is found, insert the item
          trailer.setNext(new ObjectNode(item, ptr));
          return;
        } else {
          // special case if index is a negative number, etc
          System.out.println("Index not found. Try again.");
          return;
        }
    }
  }

  public int length() {
    // returns length of the PList
    int len = 0;
    ObjectNode runner = head.getNext();
    if (runner == null){
      // special case, if list is empty
      System.out.println("List is empty.");
      return len;
    } else {
      // case if list isn't empty
      while (runner != null){
        runner = runner.getNext();
        len++;
      } return len;
    }
  }

  public void print() {
    // displays to the screen the items in PList from beginning to end
    // this is an option used for testing and verification
    ObjectNode runner = head.getNext();
    while (runner != null){
      System.out.println(runner);
      runner = runner.getNext();
    }
  }

  public void remove(Object item) {
    // removes the data entry that matches item from the PList
    ObjectNode trailer = head;
    ObjectNode ptr = head.getNext();
    if (item.equals(ptr.getData())){
      // special case, if you want to remove first element
      trailer.setNext(ptr.getNext());
      return;
    } else {
      // case if you want to remove beyond first element
      for (int i = 0; i < this.length(); i++){
        if (item.equals(this.get(i))){
          this.delete(i);
          return;
        }
      } // for loop
      // case if item not found in list
      System.out.println("Item not found in list. Try again!");
      return;
    }
  }

  public void sort() {
    // sorts all items in PList by comparing the toString() values
    // of each data item
    if (head.getNext() == null){
      // special case, if the list is empty
      System.out.println("The list is empty and cannot be sorted. Add some elements and try again.");
      return;
    } else {
      // case if the list actually has elements
      // let's use a bubble sort!
      int len = this.length();
      for(int i = 0; i < len; i++){
        for(int j = 1; j < len-i; j++){
          Object temp1 = this.get(j-1);
          Object temp2 = this.get(j);
          String s1 = temp1.toString();
          String s2 = temp2.toString();
          // if s2 is lexographically greater than s1, .compareTo() will return a negative number
          if ((s1).compareTo(s2) > 0){
              this.getNode(j-1).setData(temp2);
              this.getNode(j).setData(temp1);
          }
        } // inner for loop
      } // outer for loop
    } // else statement
  } // sort method

  public ObjectNode getNode(int index){
    // helper function for sort() to get a node
    ObjectNode runner = head.getNext();
    for (int i = 0; i < index; i++){
      runner = runner.getNext();
    } return runner;
  }

  public String toString() {
    // gives you the length & elements in a string
    String str = "";
    for (int i = 0; i < this.length(); i++)
      str += this.get(i) + "  ";
    return "Length of string: " + this.length() +". Elements in string: " + str + ".";
  }

  public boolean equals(PListInterface o) {
    // compares two plists using .toString() values
    if (this.length() != o.length())
      return false;
    else {
      for (int i = 0; i < this.length(); i++){
        String s1 = (this.get(i)).toString();
        String s2 = (o.get(i)).toString();
        if ((s1).compareTo(s2) != 0)
          return false;
      }
      return true;
    }
  }

  public static void main(String[] args) {
    // switch case statement to implement any of the 10 functions
    displayMenu(); // displays the user interface
    PList plists = new PList(); // plist of plists
    PList a = new PList();
    plists.add(a); // just so plists won't be empty, i.e. to avoid countless error messages about an empty plists variable. this is why select starts indexing at 1.
    PList sample = new PList(); // sample used for hard code concat example
    // data looks like: 4 3 79
    sample.append(3);
    sample.add(4);
    sample.append(79);
    Scanner s = new Scanner(System.in);
    while (s.hasNext()){
      switch(s.nextLine()){
        case "select":
          System.out.println("Input an index to choose your primary plist from the list of PLists. SELECT STARTS INDEXING AT 1.");
          System.out.println("Just to reiterate, SELECT STARTS INDEXING AT 1.");
          int k = s.nextInt();
          a = (PList) plists.get(k); // plist chosen from list of plists using get() method
          break;
        case "create":
          a = new PList();
          plists.append(a);
          System.out.println("New PList created! Now use any of the functions, such as add or append, to alter your list.");
          break;
        case "add":
          System.out.println("Input an object to add to the linked list.");
          a.add(s.nextLine());
          System.out.println("Added!");
          break;
        case "append":
          System.out.println("Input an object to append to the linked list.");
          a.append(s.nextLine());
          System.out.println("Appended!");
          break;
        case "concatenate1":
          System.out.println("Hard coded sample concatenation. Sample PList that was concatenated in main method.");
          a.concatenate(sample);
          System.out.println("Concatenated!");
          break;
        case "concatenate2":
          // HONORS
          System.out.println("Input an index from the list of PLists. We will concatenate this list to your primary list, then remove it from the list of PLists.");
          int g = s.nextInt();
          PList v = (PList) plists.get(g);
          a.concatenate(v);
          plists.delete(g);
          System.out.println("Concatenated!");
          break;
        case "delete":
          System.out.println("Input the index to delete an object at.");
          a.delete(s.nextInt());
          System.out.println("Deleted!");
          break;
        case "get":
          System.out.println("Input an index to get data from.");
          int h = s.nextInt();
          System.out.println(a.get(h));
          break;
        case "insert":
          System.out.println("Input an index to insert the data into.");
          int y = s.nextInt();
          System.out.println("Input an object to insert.");
          Object u = s.next();
          a.insert(u, y);
          System.out.println("Inserted!");
          break;
        case "length":
          System.out.println(a.length());
          break;
        case "print":
          a.print();
          break;
        case "sort":
          a.sort();
          System.out.println("Sorted!");
          break;
        case "remove":
          System.out.println("Input an item in the list to remove.");
          a.remove(s.next());
          System.out.println("Removed!");
          break;
        case "exit":
          System.exit(0);
          break;
      } // switch
    } // while loop
  }

    /* EXAMPLE TESTS RUN
    To test out all the methods, I created instances of PList in the main before creating the switch case,
    then tested out each method. Some examples:
    PList what = new PList();
    what.append(134);
    what.add(1000);
    what.append(32);
    what.add(666);
    what.print();
    what.delete(0);
    what.remove(32);
    what.print();
    what.sort();
    I used .print() intermittently to see where my code/my list was at.
    */

  public static void displayMenu(){
    // method used to display user options
    // Yes, I am aware there is an easier way to write this, I'm just too lazy lol
    System.out.println("~~~~PLIST USER INTERFACE~~~~");
    System.out.println("Some notes: PList is indexed starting from 0. When you type in your commands, please type using lowercase.");
    System.out.println("COMMANDS");
    System.out.println("If you haven't made any PLists yet, start with the 'create' command.");
    System.out.println("'create' - creates a new PList");
    System.out.println("'select' - selects a PList from the PLists you've created already, starts indexing at 1 NOT 0");
    System.out.println("'add' - adds item to the start of the PList");
    System.out.println("'append' - places item at the end of the PList");
    System.out.println("'concatenate1' - joins a plist to your current PList to create a longer list");
    System.out.println("'concatenate2' - joins a plist from list of plists to primary PList to create a longer list");
    System.out.println("'delete' - removes the item at a specified index from the PList");
    System.out.println("'get' - returns the data item at a specified index");
    System.out.println("'insert' - places item at the specified index");
    System.out.println("'length' - returns length of your current PList");
    System.out.println("'print' - displays all the items in your current PList from beginning to end");
    System.out.println("'remove' - removes the data entry (that matches the item you input) from your current PList");
    System.out.println("'sort' - sorts all the items in your PList by comparing lexicographical values");
    System.out.println("'exit' - get out of the program!");
    System.out.println("Want to create a new plist? Just 'create' again!");
    System.out.println("***************");
  }


}
