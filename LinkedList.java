package col106.assignment5;

public class LinkedList<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public LinkedList() {
    head = null;
    tail = null;
    size = 0;
  }

  public Node<T> getHead(){
  	return this.head;
  }

  public Node<T> getTail(){
    return this.tail;
  }

  public void setHead(Node<T> node3){
    if(head == null){
      head = node3;
      head.setNext(null);
    }
  }

  public void setTail(Node<T> node4){
    if (tail == null){
      tail = node4;
    }else{
      tail = node4;
      tail.setNext(null);
    }
  }
  public void add(T node2) {
    Node<T> node = new Node(node2);
    node.setNext(null);

    if(head==null){
		  head = node;
		  head.setNext(null);
      tail = head;
  	}
  	else{
      tail.setNext(node);
      tail = node;
  	}

    size++;
  }
  
  public int getSize() {
    return size;
  }

  public String toString() {
    Node<T> current = head;
    String elements = "";
    while (current != null) {
      elements += "[" + current.getData().toString() + "]";
      current = current.getNext();
    }
    return elements;
  }
}


