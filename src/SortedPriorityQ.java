// Morgane Bentzinger - 26106253

import java.util.ArrayList;

public class SortedPriorityQ {
    // array based priority queue
    public ArrayList<Entry> internalList;

    // creates an empty ArrayList for the sorted priority queue
    public SortedPriorityQ() {
        internalList = new ArrayList<>();
    }

    // inserts a new entry in the priority queue
    public void insert(int key, int value) {
        Entry newEntry = new Entry(key, value);

        if(internalList.isEmpty()) {
            internalList.add(newEntry); // if empty, add value
        } else {
            Integer indexOfFirstLargerEntry = null; // store index of the first larger key entry than the new entry in the array

            // go through the array to find the first entry that has a larger key than the one we are inserting
            for (Entry entry: internalList) {
                if(entry.getKey() > newEntry.getKey()) {
                    indexOfFirstLargerEntry = internalList.indexOf(entry);
                    break; // stop the loop
                }
            }

            if(indexOfFirstLargerEntry != null) { // if there is an entry larger than the current
                internalList.add(indexOfFirstLargerEntry, newEntry); // we add the new entry at its index
            } else {
                internalList.add(newEntry); // if there is no larger entry, we add the new entry to the end of the array
            }
        }
    }

    // returns the entry with the smallest key (null if priority queue is empty)
    public Entry min() {
        if(internalList.isEmpty()) {
            return null;
        }
        else {
            return internalList.get(0); // return the first element
        }
    }

    // removes and returns entry with the smallest key (null if priority queue is empty)
    public Entry removeMin() {
        if(internalList.isEmpty()) {
            return null;
        }
        else  {
            Entry removedEntry = internalList.get(0); // keep removed entry for returning
            internalList.remove(0);
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
