// Morgane Bentzinger - 26106253

import java.util.ArrayList;

public class Heap {
    // array based heap
    public ArrayList<Entry> internalList;

    // creates an empty ArrayList for the heap
    public Heap() {
        internalList = new ArrayList<>();
    }

    // returns an entry's parent index in the array
    public int getParentIndex(int childIndex) {
        int parentIndex;

        if(childIndex % 2 == 0) {
            parentIndex = childIndex / 2 - 1;
        } else {
            parentIndex = childIndex / 2;
        }

        return parentIndex;
    }

    // returns an entry's left child index in the array
    public int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    // returns an entry's right child index in the array
    public int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    // checks if an entry has a left child
    public boolean hasLeftChild(int parentIndex) {
        return parentIndex * 2 + 1 < internalList.size();
    }

    // checks if an entry has a right child
    public boolean hasRightChild(int parentIndex) {
        return parentIndex * 2 + 2 < internalList.size();
    }

    // restores heap order after insertion
    public void upheap(int index) {
        Entry currentEntry = internalList.get(index);

        int parentIndex = this.getParentIndex(index);
        Entry parentEntry = internalList.get(parentIndex);

        if(currentEntry.getKey() > parentEntry.getKey()) { // if the current Entry is bigger than the parent Entry, the heap-order property is satisfied
            return;
        } else { // if the current Entry is smaller than the parent entry, switch both Entries
            internalList.set(index, parentEntry);
            internalList.set(parentIndex, currentEntry);
            if(parentIndex > 0) // if the current entry is not at the root position
                upheap(parentIndex); // recursively call upheap with the new index of the inserted entry
        }
    }

    // restores heap order after removal
    public void downheap(int index) {

        if(!hasLeftChild(index)) { // if the entry doesn't have a left child, we've reached the bottom of the tree
            return;
        }

        Entry currentEntry = internalList.get(index);

        int leftChildIndex = this.getLeftChildIndex(index);
        Entry leftChildEntry = internalList.get(leftChildIndex);

        int rightChildIndex;
        Entry rightChildEntry;

        // if the node does not have a right child, we only need to compare with the left child
        if(!hasRightChild(index)) {
            // if the current Entry is larger than the left child Entry, switch both Entries
            if(currentEntry.getKey() > leftChildEntry.getKey()) {
                internalList.set(index, leftChildEntry);
                internalList.set(leftChildIndex, currentEntry);
                downheap(leftChildIndex); // recursively call downheap with the new index of the original root Entry
            } else { // if the current Entry is smaller than the left child Entry, the heap-order property is satisfied
                return;
            }
        } else { // if the Entry has a right child
            // assign values to previously initialized variables rightChildIndex & rightChildEntry
            rightChildIndex = this.getRightChildIndex(index);
            rightChildEntry = internalList.get(rightChildIndex);

            // compare left and right child Entry to get the smallest Entry, and store index and Entry
            // Here I followed the instructions for downheap in Data Structures & Algorithms 6th Edition (Goodrich, Tamassia, Goldwasser):
            // "It is worth noting that when p has two children, we intentionally consider the smaller key of the two children." (p.374)
            int smallestChildIndex = leftChildEntry.getKey() < rightChildEntry.getKey() ? leftChildIndex : rightChildIndex;
            Entry smallestChildEntry = leftChildEntry.getKey() < rightChildEntry.getKey() ? leftChildEntry : rightChildEntry;

            // if the current Entry is larger than the smallest child, switch both Entries
            if(currentEntry.getKey() > smallestChildEntry.getKey()) {
                internalList.set(index, smallestChildEntry);
                internalList.set(smallestChildIndex, currentEntry);
                downheap(smallestChildIndex); // recursively call downheap with the new index of the original root Entry
            } else { // if the current Entry is smaller than the left child Entry, the heap-order property is satisfied
                return;
            }
        }

    }

    public void insert(int key, int value) {
        Entry newEntry = new Entry(key, value);

        internalList.add(newEntry); // add new Entry to the end of the heap array

        if(internalList.size() > 1) { // if there is more than one Entry in the heap after adding the new one
            this.upheap(internalList.size() - 1); // restore heap order property with upheap
        }
    }

    // returns the entry with the smallest key (null if heap is empty)
    public Entry min() {
        if(internalList.isEmpty()) {
            return null;
        }
        else {
            return internalList.get(0); // return the first element of the heap
        }
    }

    // removes and returns entry with the smallest key (null if heap is empty)
    public Entry removeMin() {
        if(internalList.isEmpty()) {
            return null;
        }
        // if there is only one Entry in the heap, no need to restore heap-order property after removing (now empty)
        else if (internalList.size() == 1) {
            Entry minEntry = internalList.get(0);
            internalList.remove(0);
            return minEntry;
        }
        else {
            Entry minEntry = internalList.get(0); // store entry for return

            int lastEntryIndex = internalList.size() - 1;
            Entry lastEntry = internalList.get(lastEntryIndex);

            internalList.set(0, lastEntry); // put the last Entry at the root of the heap (index 0 of the array)
            internalList.remove(lastEntryIndex); // remove the last entry

            this.downheap(0); // restore heap-order property with downheap

            return minEntry;
        }
    }

    // returns the size of the heap
    public int size() {
        return internalList.size();
    }

    // checks if the heap is empty
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public String toString() {
        if(internalList.isEmpty()) return "The heap is empty.";

        StringBuilder returnString = new StringBuilder();
        for (Entry entry: internalList) {
            returnString.append(entry.toString()); // use Entry's toString to print each entry
        }
        return returnString.toString();
    }
}
