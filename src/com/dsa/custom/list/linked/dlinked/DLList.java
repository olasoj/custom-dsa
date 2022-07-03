package com.dsa.custom.list.linked.dlinked;


// Linked list implementation
class DLList<E>
//        implements List<E>
{
    private final DLink<E> head;
    protected DLink<E> curr;
    int cnt;
    private DLink<E> tail;

    //Constructors
    DLList(int size) {
        this();
    }

    DLList() {
// Pointer to list header
// Pointer to last element
// Access to current element
// Size of list
// Constructor -- Ignore size
        curr = tail = head = null;
        //new DLink<E>(); // Create header
        cnt = 0;
    }

    // Insert "it" at current position
    public void insert(E it) {
        curr.setNext(new DLink<E>(it, curr.next(), curr));
        if (curr.next().next() != null)
            curr.next().next().setPrev(curr.next());
        if (tail == curr) tail = curr.next();  // New tail
        cnt++;
    }

    public void append(E it) { // Append "it" to list
        tail.setNext(new DLink<E>(it, null, tail));
        tail = tail.next();
        cnt++;
    }

    // Remove and return first element in right partition
    public E remove() {
        if (curr.next() == null) return null; // Nothing to remove
        E it = curr.next().element();      // Remember value
        if (curr.next().next() != null)
            curr.next().next().setPrev(curr);
        else tail = curr;         // Removed last Object: set tail
        curr.setNext(curr.next().next());  // Remove from list
        cnt--;                             // Decrement the count
        return it;                         // Return value removed
    }

    // Move curr one step left; no change if at front
    public void prev() {
        if (curr != head) curr = curr.prev();
    }

    public void moveToStart()     // Set curr at list start
    {
        curr = head;
    }


    public void moveToEnd()  // Set curr at list end
    {
        curr = tail;
    }

    // Move curr one step right; no change if already at end
    public void next() {
        if (curr != tail) {
            curr = curr.next();
        }
    }

    public int length() {
        return cnt;
    }

    // Return the position of the current element
//    public int currPos() {
//        Link<E> temp = head;
//        int i;
//        for (i = 0; curr != temp; i++)
//            temp = temp.next();
//        return i;
//    }

    // Move down list to "pos" position
    public void moveToPos(int pos) {
        assert (pos >= 0) && (pos <= cnt) : "Position out of range";
        curr = head;
        for (int i = 0; i < pos; i++) curr = curr.next();
    }

    public E getValue() {   // Return current element
        if (curr.next() == null) return null;
        return curr.next().element();
    }
}
