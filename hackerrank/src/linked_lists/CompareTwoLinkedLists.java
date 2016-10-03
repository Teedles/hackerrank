package linked_lists;

public class CompareTwoLinkedLists {

	
	public static void main(String[] args) {
		
		Node headA = new Node();
		Node headB = new Node();
		
		headA.data = 9;
		headB.data = 6;
		
		if ((CompareLists(headA, headB)) == 1) {
			System.out.println("The lists contain the same data!");
			System.exit(0);
		}
		
		System.out.println("The lists do not contain the same data!");
		System.exit(1);
		
	}
	
static int CompareLists(Node headA, Node headB) {

	if (headA.data == headB.data) {
		return 1;
	}
	return 0;  
}

public static class Node {
    int data;
    Node next;
    
    Node() {
    	
    }
 }

}
