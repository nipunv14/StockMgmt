package col106.assignment5;
import java.util.Comparator;

public class StockMgmt implements StockMgmtInterface {
	//!!!!!!!*********DON'T CREATE YOUR OWN OBJECTS OF LLMergeSort*********!!!!!!!
	//use these only this object to sort
	LLMergeSort mergeSortobj;

	LinkedList<PurchaseNode> purchaseTransactions=new LinkedList<>();
	LinkedList<CategoryNode> categories;
	
	//DO NOT MNODIFY THIS CONSTRUCTOR
	public StockMgmt() {
		
		mergeSortobj = new LLMergeSort();
		categories = new LinkedList<CategoryNode>();
		
		categories.add(new CategoryNode(1, "mobile"));
		categories.add(new CategoryNode(2, "utensils"));
		categories.add(new CategoryNode(3, "sanitary"));
		categories.add(new CategoryNode(4, "medicalEquipment"));
		categories.add(new CategoryNode(5, "clothes"));
		
	}
	
	Node<CategoryNode> pointer = new Node<CategoryNode>();
	public void addItem(int categoryId, int itemId, String itemName, int stock) {
		//Your code goes here
		
		while(pointer.getData().categoryId != categoryId){
			pointer = pointer.getNext();
		}
		Node<ItemNode> pointeri = pointer.getData().getLinkedListOfCategory().getHead();
		pointeri.getData().itemId = itemId;
		pointeri.getData().itemName = itemName;
		pointeri.getData().stock = stock;
	}

	public void addItemTransaction(int categoryId, int itemId, String itemName, int numItemPurchased, int day, int month, int year) {
		//Your code goes here
		Node<CategoryNode> pointc = new Node<CategoryNode>();

		pointc = categories.getHead();
		while(pointc.getData().categoryId != categoryId){
			pointc = pointc.getNext();
		}
		
		Node<ItemNode> start = pointc.getData().getLinkedListOfCategory().getHead();
		
		while(start.getData().itemId != itemId){
			start = start.getNext();
		}
		start.getData().stock = start.getData().stock - numItemPurchased;

		Node<PurchaseNode> begin = start.getData().getPurchaseHead();

		while(begin.getNext() != null){
			begin = begin.getNext();
		}
		begin.getNext().getData().getDate().day = day;
		begin.getNext().getData().getDate().month= month;
		begin.getNext().getData().getDate().year = year;
		begin.getNext().getData().numItemPurchased = numItemPurchased;
	}

	//Query 1
	public LinkedList<ItemNode> sortByLastDate() {
		//Your code goes here
		return mergeSortobj.MergeSort(categories.getLinkedListOfCategory(), 1);
	}

	//Query 2
	public LinkedList<ItemNode> sortByPurchasePeriod(int day1, int month1, int year1, int day2, int month2, int year2) {
		//Your code goes here
		
		return null;
	}

	//Query 3
	public LinkedList<ItemNode> sortByStockForCategory(int category) {
		//Your code goes here
		Node<CategoryNode> a = new Node<CategoryNode>();
		a = categories.getHead();
		while(a.categoryId != category){
			a = a.getNext();
		}
		LinkedList<ItemNode> itemLst = a.getData().getLinkedListOfCategory();
		Node<ItemNode> itm = new Node<>();
		itm = itemLst.getHead();
		return mergeSortobj.MergeSort(itemLst, 3);
	}

	//Query 4
	public LinkedList<ItemNode> filterByCategorySortByDate(int category) {
		//Your code goes here
		Node<CategoryNode> a2 = new Node<>();
		a2 = categories.getHead();
		while(a2.categoryId != category){
			a2 = a2.getNext();
		}
		LinkedList<ItemNode> itemLst2 = a2.getData().getLinkedListOfCategory();
		itm2 = itemLst2.getHead();
		return mergeSortobj.MergeSort(itemLst2, 4);
	}

	//!!!!!*****DO NOT MODIFY THIS METHOD*****!!!!!//
	public LinkedList<ItemNode> checkMergeSort() {
		LinkedList<ItemNode> retLst = mergeSortobj.getGlobalList();
		mergeSortobj.clearGlobalList();
		return retLst;
	}
}
