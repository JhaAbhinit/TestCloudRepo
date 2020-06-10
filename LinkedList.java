Q- get nth node from the end in Linked List:- 
Approach 1:- len-n+1 th node from start 
Approach 2:- slow & fast pointer concept. 
First move fast pointer to n nodes from head. Now move both pointers one by one until fast pointer reaches end. Now slow pointer will point to nth node from the end. Return slow pointer.
--------------------------------------------------------------------------------------------
Q- Detect a loop in LinkedList:- 

Approach 1:- Use Hashing:Store the node addresses in a HashSet.
boolean detectLoop(Node head) 
{ 
    HashSet<Node> s = new HashSet<Node>(); 
    while (head != null)
	{ 
		if (s.contains(head)) 
            return true; 
		else
			s.add(head); 
        head = head.next; 
    } 
	return false;
}  

Approach 2:- Floyd Cycle Finding algorithm 
Use slow & fast pointer.If these pointers meet at the same node then there is a loop.  If pointers do not meet then linked list doesn’t have a loop.
---------------------------------------------------------------
Detect & Reemove Loop In a Linked List:- 
Approach :-
1) Detect Loop using Floyd’s Cycle detection algo and get the pointer to a loop node.
2) Count the number of nodes in loop. Let the count be k.
3) Fix one pointer to the head and another to kth node from head.
4) Move both pointers at the same pace, they will meet at loop starting node.
5) Get pointer to the last node of loop and make next of it as NULL.
int detectAndRemoveLoop(Node node)
{ 
    Node slow = node, fast = node; 
    while (slow != null && fast != null && fast.next != null)
	{ 
        slow = slow.next; 
        fast = fast.next.next; 
        if (slow == fast)
		{ 
            removeLoop(slow, node); 
            return 1; 
        } 
    } 
    return 0; 
}
void removeLoop(Node loop, Node head)
{ 
    Node ptr1 = loop; 
    Node ptr2 = loop; 
    // Count the number of nodes in loop 
    int k = 1, i; 
    while (ptr1.next != ptr2) { 
        ptr1 = ptr1.next; 
        k++; 
    } 
    // Fix one pointer to head 
    ptr1 = head; 
    // And the other pointer to k nodes after head 
    ptr2 = head; 
    for (i = 0; i < k; i++) { 
        ptr2 = ptr2.next; 
    } 
    /*  Move both pointers at the same pace, 
    they will meet at loop starting node */
    while (ptr2 != ptr1) { 
        ptr1 = ptr1.next; 
        ptr2 = ptr2.next; 
    } 
    // Get pointer to the last node 
    while (ptr2.next != ptr1) { 
        ptr2 = ptr2.next; 
    } 
	/* Set the next node of the loop ending node 
    to fix the loop */
    ptr2.next = null; 
}
-----------------------------------------------------------------------------
Q- Count of number of nodes in Loop 
Length of Loop in Linked List 
--------------------------------------------------------------------
Q-Swap two nodes in a linked list without swapping data:- 

This may look a simple problem, but is interesting question as it has following cases to be handled.
1) x and y may or may not be adjacent.
2) Either x or y may be a head node.
3) Either x or y may be last node.
4) x and/or y may not be present in linked list.

public void swapNodes(int x, int y) 
{ 
    if (x == y) 
		return; 
  
	// Search for x (keep track of prevX and CurrX) 
    Node prevX = null, currX = head; 
    while (currX != null && currX.data != x) 
    { 
        prevX = currX; 
        currX = currX.next; 
    } 
    // Search for y (keep track of prevY and currY) 
    Node prevY = null, currY = head; 
    while (currY != null && currY.data != y) 
    { 
        prevY = currY; 
        currY = currY.next; 
    } 
    // If either x or y is not present, nothing to do 
    if (currX == null || currY == null) 
        return; 
    // If x is not head of linked list 
    if (prevX != null) 
        prevX.next = currY; 
    else //make y the new head 
        head = currY; 
  
	// If y is not head of linked list 
    if (prevY != null) 
        prevY.next = currX; 
    else // make x the new head 
        head = currX; 
  
	// Swap next pointers 
    Node temp = currX.next; 
    currX.next = currY.next; 
    currY.next = temp; 
} 
--------------------------------------------------------------------------------------------
Reverse a Linked List in a given group of size k :-

Node reverse(Node head, int k) 
{ 
    Node current = head; 
    Node next = null; 
    Node prev = null; 
         
    int count = 0; 
    /* Reverse first k nodes of linked list */
    while (count < k && current != null)  
    { 
        next = current.next; 
        current.next = prev; 
        prev = current; 
        current = next; 
        count++; 
    } 
  
    /* next is now a pointer to (k+1)th node  
    Recursively call for the list starting from current. 
    And make rest of the list as next of first node */
    if (next != null)  
        head.next = reverse(next, k); 
  
    // prev is now head of input list 
    return prev; 
}     
----------------------------------------------------------------
Print the given Linked List in Reveresed Order:-
void printReverse(Node head) 
{ 
    if (head == null) 
		return; 
  
    // print list of head node 
    printReverse(head.next); 
    // After everything else is printed 
    System.out.print(head.data+" "); 
}
-------------------------------------------------------------------------------
Add two numbers represented by linked lists:-
Input:
  First List: 5->6->3  // represents number 365
  Second List: 8->4->2 //  represents number 248
Output
  Resultant list: 3->1->6  // represents number 613
  
Node addTwoLists(Node first, Node second)
{ 
    Node res = null; 
    Node prev = null; 
    Node temp = null; 
    int carry = 0, sum; 
  
	while (first != null || second != null) //while both lists exist 
    { 
        sum = carry + (first != null ? first.data : 0) + (second != null ? second.data : 0); 
  
        carry = sum/10;
        sum = sum % 10; 
  
        // Create a new node with sum as data 
        temp = new Node(sum); 
  
        // if this is the first node then set it as head of 
        // the resultant list 
        if (res == null) { 
            res = temp; 
        } else // If this is not the first node then connect it to the rest. 
        { 
            prev.next = temp; 
        } 
        // Set prev for next insertion 
        prev = temp; 
        // Move first and second pointers to next nodes 
        if (first != null) { 
            first = first.next; 
        } 
        if (second != null) { 
            second = second.next; 
        } 
    } 
    if (carry > 0) { 
        temp.next = new Node(carry); 
    } 
    // return head of the resultant list 
    return res; 
}
--------------------------------------------------------------------------------------
Flattening a Linked List:- 
5 -> 10 -> 19 -> 28
|    |     |     |
V    V     V     V
7    20    22    35
|          |     |
V          V     V
8          50    40
|                |
V                V
30               45   

Here, all linked lists are sorted.
output list should be 5->7->8->10->19->20->22->28->30->35->40->45->50.

The idea is to use Merge() process of merge sort for linked lists. We use merge() to merge lists one by one. 
Node flatten(Node root) 
{ 
    // Base Cases 
    if (root == null || root.right == null) 
        return root; 
    // recur for list on right 
    root.right = flatten(root.right); 
    // now merge 
    root = merge(root, root.right); 
    return root; 
} 
Node merge(Node a, Node b) 
{ 
    if (a == null) 
		return b; 
    if (b == null)  
		return a; 
 
    // compare the data members of the two linked lists 
    // and put the larger one in the result 
    Node result; 
    if (a.data < b.data) 
    { 
        result = a; 
        result.down =  merge(a.down, b); 
    } 
    else
    { 
        result = b; 
        result.down = merge(a, b.down); 
    } 
    return result; 
}  
-----------------------------------------------------------
Q- Remove duplicate nodes from the sorted Linked List:- 
Traverse the list from the head (or start) node. While traversing, compare each node with its next node. If data of next node is same as current node then delete the next node. Before we delete a node, we need to store next pointer of the node.

Q- Unsorted LinkedList:-
1. This is the simple way where two loops are used. Outer loop is used to pick the elements one by one and inner loop compares the picked element with rest of the elements.
2.Using HashSet 
3. by Sorting then removing 

----------------------------------------------------------------------------
Q- Make middle Node as head in a Linked List:-
The idea is to first find middle of a linked list using two pointers.We also keep track of previous of first pointer so that we can remove middle node from its current position and can make it head.