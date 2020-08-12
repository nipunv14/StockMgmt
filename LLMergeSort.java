package col106.assignment5;

import java.util.Comparator;
import java.util.LinkedList;

/*
Implementation of MergeSort Algorithm :
    1. you get linked list of size <=1 you just return the list as it is already sorted
    2. Find mid node using findSplit method(Don't forget to add mid element to globalList before returning it)
    3. Create two LinkedList lt (with head = head and tail = mid) and rt (with head = mid.next and tail = tail)
    4. Now recursively MergSort lt and rt Linked lists(Maintain this order)
    5. Now merge these two lists that we got from recursive calls using given crieteria for ordering
    6. Return merged Linked list
*/


public class LLMergeSort <T extends Comparator>{ 
  LinkedList<T>  globalList = new LinkedList<T>();

  //CALL THIS METHOD AFTER EVERY CALL OF findSplit and DO NOT MODIFY THIS METHOD
  public void adjustGlobalPointer(T node){
      globalList.add(node);
  }

  // Utility function to get the middle of the linked list
  public Node<T> findSplit(LinkedList<T>  lst) {
    //find middle node of LL :
    //Enter your code here
    Node<T> middle = lst.getHead();
    Node<T> slow = lst.getHead();
    Node<T> fast = lst.getHead();
    
    if(lst.getHead() ==  null){
      middle = lst.getHead();
    }

    while(fast.getNext() != null && fast.getNext().getNext() != null){
      slow = slow.getNext();
      fast = fast.getNext().getNext();
    }
    middle = slow;

    //!!!!!*****DO NOT REMOVE THIS METHOD CALL (change the argument apprpriately)*****!!!!!
    adjustGlobalPointer(middle.getData()); //Add object of ItemNode after finding mid in each call
    return middle;
  }


  public LinkedList<T>  MergeSort(LinkedList<T>  lst, int id) {
    //Recursively Apply MergeSort, by calling function findSplit(..) to find middle node to split
    //Enter your code here
    LinkedList<T> lt = new LinkedList<T>();
    LinkedList<T> rt = new LinkedList<T>();
    
    lt.setHead(lst.getHead());
    lt.setTail(findSplit(lst));

    rt.setHead(findSplit(lst).getNext());
    rt.setTail(lst.getTail());

    Node<T> point = lt.getHead();
    while(point != findSplit(lst)){
      lt.add(point.getData());
      point = point.getNext();
    }
    point.setNext(null);
    rt.setHead(findSplit(lst).getNext());

    Node<T> p1 = rt.getHead();
    while(p1.getNext() != null){
      rt.add(p1.getData());
      p1 = p1.getNext();
    }

    if(id == 1){
      MergeSort(lt, id);
      MergeSort(rt, id);
      return Merge1(lt, rt);
    }
    else if(id == 2){
      MergeSort(lt, id);
      MergeSort(rt, id);
      return Merge2(lt, rt);
    }else if(id == 3){
      MergeSort(lt, id);
      MergeSort(rt, id);
      return Merge3(lt, rt);
    }
    else if(id == 4){
      MergeSort(lt, id);
      MergeSort(rt, id);
      return Merge4(lt, rt);
    }
    return null;
  }
  



  public LinkedList<T> Merge1(LinkedList<T> lt, LinkedList<T> rt){

    Node<T> lcurr = new Node<>();
    lcurr = lt.getHead();
    Node<T> rcurr = new Node<>();
    rcurr = rt.getHead();
    
    LinkedList<T> merger1 = new LinkedList<>();
    merger1.setHead(null);
    Node<T> myNode = new Node<>();
    myNode = merger1.getHead();

    while(lcurr.getNext() != null && rcurr.getNext() != null){
      ItemNode lcurri = ItemNode(lcurr);
      ItemNode rcurri = ItemNode(rcurr);
      ItemNode alpha = new Node<>(); 
      if(alpha.compareToDate(lcurri.getData(), rcurri.getData()) == 1){
        myNode.setNext(lcurr);
        lcurr = lcurr.getNext();
      }else if(alpha.compareToDate(lcurri.getData(), rcurri.getData()) ==-1){
        myNode.setNext(rcurr);
        rcurr = rcurr.getNext();
      }else{
        int a = alpha.compareToName(lcurri, rcurri);
        if(a == 1){
          myNode.setNext(lcurr);
          lcurr = lcurr.getNext();
        }else if(a == -1){
          myNode.setNext(rcurr);
          rcurr = rcurr.getNext();
        }
      }
      myNode = myNode.getNext();
    }

    if(lcurr != null){
      myNode.setNext(lcurr);
      lcurr = lcurr.getNext();
    }else if(rcurr != null){
      myNode.setNext(rcurr);
      rcurr = rcurr.getNext();
    }    
    return merger1;
  }



  public LinkedList<T> Merge2(LinkedList<T> lt, LinkedList<T> rt){
    if (lt == null){
      return rt;
    }
    if (rt == null){
      return lt;
    }
    Node<T> lcurr = new Node<T>();
    lcurr = lt.getHead();
    Node<T> rcurr = new Node<T>();
    rcurr = rt.getHead();
    
    LinkedList<T> merger2 = new LinkedList<T>();
    merger2.setHead(null);
    Node<T> myNode = new Node<T>();
    myNode = merger2.getHead();

    while(lcurr.getNext() != null && rcurr.getNext() != null){
      ItemNode lcurri = ItemNode(lcurr);
      ItemNode rcurri = ItemNode(rcurr);
      ItemNode alpha = new Node<>(); 
      if(alpha.compareToDate(lcurri.getData(), rcurri.getData()) == 1){
        myNode.setNext(lcurr);
        lcurr = lcurr.getNext();
      }else if(alpha.compareToDate(lcurri.getData(), rcurri.getData()) ==-1){
        myNode.setNext(rcurr);
        rcurr = rcurr.getNext();
      }else{
        int a = alpha.compareToName(lcurri, rcurri);
        if(a == 1){
          myNode.setNext(lcurr);
          lcurr = lcurr.getNext();
        }else if(a == -1){
          myNode.setNext(rcurr);
          rcurr = rcurr.getNext();
        }
      }
      myNode = myNode.getNext();
    }

    if(lcurr != null){
      myNode.setNext(lcurr);
      lcurr = lcurr.getNext();
    }else if(rcurr != null){
      myNode.setNext(rcurr);
      rcurr = rcurr.getNext();
    }    
    return merger2;
  }



  public LinkedList<T> Merge3(LinkedList<T> lt, LinkedList<T> rt){
    if (lt == null){
      return rt;
    }
    if (rt == null){
      return lt;
    }
    Node<T> lcurr = new Node<T>();
    lcurr = lt.getHead();
    Node<T> rcurr = new Node<T>();
    rcurr = rt.getHead();
    
    LinkedList<T> merger3 = new LinkedList<T>();
    merger3.setHead(null);
    Node<T> myNode = new Node<T>();
    myNode = merger3.getHead();

    while(lcurr.getNext() != null && rcurr.getNext() != null){
      ItemNode lcurri = ItemNode(lcurr);
      ItemNode rcurri = ItemNode(rcurr);
      ItemNode alpha = new Node<>(); 
      if(alpha.compareToStock(lcurri.getData(), rcurri.getData()) == 1){
        myNode.setNext(lcurr);
        lcurr = lcurr.getNext();
      }else if(alpha.compareToStock(lcurri.getData(), rcurri.getData()) ==-1){
        myNode.setNext(rcurr);
        rcurr = rcurr.getNext();
      }else{
        int a = alpha.compareToName(lcurri, rcurri);
        if(a == 1){
          myNode.setNext(lcurr);
          lcurr = lcurr.getNext();
        }else if(a == -1){
          myNode.setNext(rcurr);
          rcurr = rcurr.getNext();
        }
      }
      myNode = myNode.getNext();
    }

    if(lcurr != null){
      myNode.setNext(lcurr);
      lcurr = lcurr.getNext();
    }else if(rcurr != null){
      myNode.setNext(rcurr);
      rcurr = rcurr.getNext();
    }    
    return merger3;
  }





  public LinkedList<T> Merge4(LinkedList<T> lt, LinkedList<T> rt){
    if (lt == null){
      return rt;
    }
    if (rt == null){
      return lt;
    }
    Node<T> lcurr = new Node<T>();
    lcurr = lt.getHead();
    Node<T> rcurr = new Node<T>();
    rcurr = rt.getHead();
    
    LinkedList<T> merger4 = new LinkedList<T>();
    merger3.setHead(null);
    Node<T> myNode = new Node<T>();
    myNode = merger4.getHead();

    while(lcurr.getNext() != null && rcurr.getNext() != null){
      ItemNode lcurri = ItemNode(lcurr);
      ItemNode rcurri = ItemNode(rcurr);
      ItemNode alpha = new Node<>(); 
      if(alpha.compareToDate(lcurri.getData(), rcurri.getData()) == 1){
        myNode.setNext(lcurr);
        lcurr = lcurr.getNext();
      }else if(alpha.compareToDate(lcurri.getData(), rcurri.getData()) ==-1){
        myNode.setNext(rcurr);
        rcurr = rcurr.getNext();
      }else{
        int a = alpha.compareToName(lcurri, rcurri);
        if(a == 1){
          myNode.setNext(lcurr);
          lcurr = lcurr.getNext();
        }else if(a == -1){
          myNode.setNext(rcurr);
          rcurr = rcurr.getNext();
        }
      }
      myNode = myNode.getNext();
    }

    if(lcurr != null){
      myNode.setNext(lcurr);
      lcurr = lcurr.getNext();
    }else if(rcurr != null){
      myNode.setNext(rcurr);
      rcurr = rcurr.getNext();
    }    
    return merger4;
  }

  //DO NOT CALL OR MODIFY THESE METHODS IN YOUR CALL THIS IS FOR USE IN DRIVER CODE
  public LinkedList<T> getGlobalList() {
    return this.globalList;
  }

  public void clearGlobalList(){
    globalList  = new LinkedList<>();
  }








  public static void main(String args[]){
    /*llist = new LinkedList<Integer>();
    llist.add(5);
    llist.add(60);
    llist.add(78);
    llist.add(6);
    llist.add(67);
    llist.add(15);*/
    LLMergeSort myMerge;
    ItemNode a1 = new ItemNode(1, "iPhone", 10);
    ItemNode a2 = new ItemNode(2, "Samsung", 2);
    ItemNode a3 = new ItemNode(3, "dke", 14);
    ItemNode a4 = new ItemNode(4, "cnive", 7);
    ItemNode a5 = new ItemNode(5, "vmes", 13);
    ItemNode a6 = new ItemNode(6, "vmewo", 13);
    LinkedList<ItemNode> llist = new LinekdList<ItemNode>();
    ItemNode pt = new Node<>();
    llist.add(a1);
    llist.add(a2);
    llist.add(a3);
    llist.add(a4);
    llist.add(a5);
    llist.add(a6);

    myMerge.MergeSort(llist, 3);
    while(pt.getNext() != null){
      System.out.println(pt.getData().itemName);
    }
  }

}
