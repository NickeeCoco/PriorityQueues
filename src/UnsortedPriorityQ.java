// Morgane Bentzinger - 26106253

import java.util.ArrayList;

public class UnsortedPriorityQ {
    // array based priority queue
    public ArrayList<Entry> internalList;

    // creates an empty ArrayList for the unsorted priority queue
    public UnsortedPriorityQ() {
        internalList = new ArrayList<Entry>();
    }

    // inserts a new entry in the priority queue
    public void insert(int key, int value) {
        Entry newEntry = new Entry(key, value);
        internalList.add(newEntry); // add new entry to the end of the array
    }

    // returns the entry with the smallest key (null if priority queue is empty)
    public Entry min() {
        if(internalList.isEmpty()) {
            return null; // if the array is empty, return null
        }
        else if(internalList.size() == 1) {
            return internalList.get(0); // if the array contains only one element, return the element
        }
        else {
            Entry minEntry = internalList.get(0); // store smallest entry for returning
            int minKey = internalList.get(0).getKey(); // store smallest key for comparison

            // compare each entry in the array to find the smallest key entry
            for (Entry entry: internalList) {
                if(entry.getKey() < minKey) {
                    minKey = entry.getKey();
                    minEntry = entry;
                }
            }
            return minEntry;
        }
    }

    // removes and returns entry with the smallest key (null if priority queue is empty)
    public Entry removeMin() {
        if(internalList.isEmpty()) {
            return null;
        }
        else if(internalList.size() == 1) {
            Entry removedEntry = internalList.get(0);
            internalList.remove(0);
            return removedEntry;
        }
        else {
            int removedEntryIndex = internalList.indexOf(this.min());
            Entry removedEntry = internalList.get(removedEntryIndex);
            internalList.remove(removedEntryIndex);
            return removedEntry;
        }
    }

    // returns the size of the queue
    public int size() {
        return internalList.size();
    }

    // checks if the queue is empty
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public String toString() {
        if(internalList.isEmpty()) {
            return "The list is empty.";
        }

        StringBuilder returnString = new StringBuilder();

        for (Entry entry: internalList) {
            returnString.append(entry.toString()); // use Entry's toString to print each entry
        }

        return returnString.toString();
    }

}
