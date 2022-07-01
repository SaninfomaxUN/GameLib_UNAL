package com.gamelib.Logic.Structures;

public class HashMapQuadratic<K, V> {
    public TableEntry<K, V>[] hashTable; // dictionary entries
    private int numberOfEntries;
    private int locationsUsed; // number of table locations not null
    private static final int DEFAULT_SIZE = 101; // must be prime
    private static final double MAX_LOAD_FACTOR = 0.5; // fraction of hash table that can be filled

    public int lengthArray(){
        return hashTable.length;
    }

    public HashMapQuadratic() {
        this(DEFAULT_SIZE); // call next constructor

    } // end default constructor

    public HashMapQuadratic(int tableSize) {
        // ensure that table is prime size at least as big as user wants;
        // if tableSize is prime, do not change it
        int primeSize = getNextPrime(tableSize);

        hashTable = new TableEntry[primeSize];
        numberOfEntries = 0;
        locationsUsed = 0;
    } // end constructor

    // -------------------------
    // We've added this method to display the hash table for illustration and testing
    // -------------------------
    public void display() {
        for (int index = 0; index < hashTable.length; index++)
        {
            if (hashTable[index] == null)
                System.out.println("null ");
            else if (hashTable[index].isRemoved())
                System.out.println("notIn ");
            else
                System.out.println(hashTable[index].getKey() + " " + hashTable[index].getValue());
        } // end for
        System.out.println();
    } // end display
    // -------------------------

    // 20.14
    public V add(K key, V value)
    {
        V oldValue;
        if (isHashTableTooFull())
            rehash();
        int index = getHashIndex(key);
        index = quadraticProbe(index, key);
        assert (index >= 0) && (index < hashTable.length);
        if ( (hashTable[index] == null) || hashTable[index].isRemoved()){ // key not found, so insert new entry
            hashTable[index] = new TableEntry<>(key, value);
            numberOfEntries++;
            locationsUsed++;
            oldValue = null;
        }else{
            // key found; get old value for return and then replace it
            oldValue = hashTable[index].getValue();
            hashTable[index].setValue(value);
        } // end if

        return oldValue;
    } // end add


    // 20.12
    public V remove(K key){
        V removedValue = null;

        int index = getHashIndex(key);
        index = locate(index, key);

        if (index != -1)
        {
            // key found; flag entry as removed and return its value
            removedValue = hashTable[index].getValue();
            hashTable[index].setToRemoved();
            numberOfEntries--;
        } // end if
        // else not found; result is null

        return removedValue;
    } // end remove

    public V getValue(K key){
        V result = null;

        int index = getHashIndex(key);
        index = locate(index, key);

        if (index != -1)
            result = hashTable[index].getValue(); // key found; get value

        // else not found; result is null

        return result;
    } // end getValue


    private int quadraticProbe(int index, K key){
        boolean found = false;
        int removedStateIndex = -1; // index of first location in
        // removed state
        int i=0;
        while ( !found && (hashTable[index] != null) && i< hashTable.length){
            if (hashTable[index].isIn()){
                if (key.equals(hashTable[index].getKey())){
                    found = true; // key found
                }else{
                    // follow probe sequence
                    index = (index + i*i) % hashTable.length; // linear probing
                    i++;
                }

            }else{ // skip entries that were removed

                // save index of first location in removed state
                if (removedStateIndex == -1){
                    removedStateIndex = index;

                    index = (index + i*i) % hashTable.length;         // linear probing
                    i++ ;
                }

            } // end if
        } // end while
        // Assertion: either key or null is found at hashTable[index]

        if (found || (removedStateIndex == -1) ){
            return index;             // index of either key or null
        }else{
            return removedStateIndex; // index of an available location
        }

    } // end probe

    public int locate(int index, K key){
        boolean found = false;
        int i=0;
        while ( !found && (hashTable[index] != null) && i<hashTable.length){

            if ( hashTable[index].isIn() && key.equals(hashTable[index].getKey()) ){
                found = true; // key found

            }else {
                // follow probe sequence
                index = (index + i*i) % hashTable.length; // linear probing
                i++;
            }
        } // end while
        // Assertion: either key or null is found at hashTable[index]

        int result = -1;
        if (found)
            result = index;

        return result;
    } // end locate

    public boolean contains(K key) {
        return getValue(key) != null;
    } // end contains


    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty


    public boolean isFull() {
        return false;
    } // end isFull


    public int getSize() {
        return numberOfEntries;
    } // end getSize


    public final void clear() {
        for (int index = 0; index < hashTable.length; index++)
            hashTable[index] = null;

        numberOfEntries = 0;
        locationsUsed = 0;
    } // end clear

    public int getHashIndex(K key){

        int hashIndex = key.hashCode() % hashTable.length;

        if (hashIndex < 0)
        {
            hashIndex = hashIndex + hashTable.length;
        } // end if

        return hashIndex;
    } // end getHashIndex

    /** Task: Increases the size of the hash table to a prime > twice its old size. */
    private void rehash() {
        TableEntry<K, V>[] oldTable = hashTable;
        int oldSize = hashTable.length;
        int newSize = getNextPrime(oldSize + oldSize);
        hashTable = new TableEntry[newSize]; // increase size of array
        numberOfEntries = 0; // reset number of dictionary entries, since
        // it will be incremented by add during rehash
        locationsUsed = 0;

        // rehash dictionary entries from old array to the new and bigger
        // array; skip both null locations and removed entries
        for (int index = 0; index < oldSize; index++) {
            if ( (oldTable[index] != null) && oldTable[index].isIn() ){
                add(oldTable[index].getKey(), oldTable[index].getValue());
            }

        } // end for
    } // end rehash

    /** @return true if lambda > MAX_LOAD_FACTOR for hash table;
     *       otherwise returns false. */
    private boolean isHashTableTooFull() {
        return locationsUsed > MAX_LOAD_FACTOR * hashTable.length;
    } // end isHashTableTooFull

    private int getNextPrime(int integer) {
        // if even, add 1 to make odd
        if (integer % 2 == 0) {
            integer++;
        } // end if

        // test odd integers
        while(!isPrime(integer)) {
            integer = integer + 2;
        } // end while

        return integer;
    } // end getNextPrime

    private boolean isPrime(int integer) {
        boolean result;
        boolean done = false;

        // 1 and even numbers are not prime
        if ( (integer == 1) || (integer % 2 == 0) ) {
            result = false;

        } else if ( (integer == 2) || (integer == 3) ){ // 2 and 3 are prime
            result = true;

        } else{ // integer is odd and >= 5
            assert (integer % 2 != 0) && (integer >= 5);

            // a prime is odd and not divisible by every odd integer up to its square root
            result = true; // assume prime
            for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2) {

                if (integer % divisor == 0) {
                    result = false; // divisible; not prime
                    done = true;
                } // end if
            } // end for
        } // end if

        return result;
    } // end isPrime


    // 20.09
    class TableEntry<S, T> {
        private S key;
        private T value;
        private boolean inTable; // true if entry is in table

        private TableEntry(S searchKey, T dataValue) {
            key = searchKey;
            value = dataValue;
            inTable = true;
        } // end constructor

        public S getKey() {
            return key;
        } // end getKey

        private T getValue() {
            return value;
        } // end getValue

        private void setValue(T newValue) {
            value = newValue;
        } // end setValue

        private boolean isIn() {
            return inTable;
        } // end isIn

        private boolean isRemoved(){ // opposite of isIn
            return !inTable;
        } // end isRemoved

        // state = true means entry in use; false means entry not in use, ie deleted
        private void setToRemoved() {
            key = null;
            value = null;
            inTable = false;
        } // end setToRemoved

        private void setToIn(){ // not used
            inTable = true;
        } // end setToIn
    } // end TableEntry
} // end HashedDictionary
