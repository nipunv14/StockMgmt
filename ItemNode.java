package col106.assignment5;
import java.util.Comparator;
//import java.util.LinkedList;


public class ItemNode implements Comparable<ItemNode>, ItemInterface{

	int itemId;
	String itemName;
	int stock;
	LinkedList<PurchaseNode> trans = new LinkedList<>();

	public ItemNode(int itemId, String itemName, int stock){
		this.itemId = itemId;
		this.itemName = itemName;
		this.stock = stock;
	}

	public int getItemId(){
		//Enter your code here
		return itemId;
	}

	public String getItemName(){
		//Enter your code here
		return itemName;
	}

	public int getStockLeft(){
		//Enter your code here
		return stock;
	}

	public void addTransaction(PurchaseNode purchaseT){
		//Enter your code here																				 
		Node<PurchaseNode> tr1 = this.getPurchaseHead();
		while(tr1.getNext() != null){
			tr1 = tr1.getNext();
		}
		trans.add(purchaseT);
	}

	public Node<PurchaseNode> getPurchaseHead(){
		//Enter your code here
		return trans.getHead();
	}

	public int compareToStock(ItemNode n1, ItemNode n2 ){
		if(n1.getStockLeft() < n2.getStockLeft()){
			return 1;
		}else if(n1.getStockLeft() == n2.getStockLeft()){
			return -1;
		}
		return 0;
	}

	public  int compareToName(ItemNode n1, ItemNode n2){
		return n1.getItemName().compareTo(n2.getItemName());	
	}

	public int compareToDate(ItemNode n1, ItemNode n2){
		Node<PurchaseNode> p1 = n1.getPurchaseHead();
		Node<PurchaseNode> p2 = n2.getPurchaseHead();
		int year1 = 0;
		int date1 = 0;
		int month1 = 0;
		int year2 = 0;
		int date2 = 0;
		int month2 = 0;
		if(p1 == null){
			year1 = 1970;
			date1 = 1;
			month1 = 8;
		}else{
			while(p1.getNext() != null){
				p1 = p1.getNext();
			}
			date1 = p1.getData().getDate().day;
			month1 = p1.getData().getDate().month;
			year1 = p1.getData().getDate().year;
		}
		if(p2 == null){
			year2 = 1970;
			date2 = 1;
			month2 = 8;
		}else{
			date2 = p2.getData().getDate().day;
			month2 = p2.getData().getDate().month;
			year2 = p2.getData().getDate().year;
		}
		if(year1 > year2){
			return 1;
		}else if(year1 == year2){
			if(month1 > month2){
				return 1;
			}else if(month2 == month1){
				if(day1 > day2){
					return 1;
				}
				else if(day1 ==day2){
					return 0;
				}else{
					return -1;
				}
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}
}