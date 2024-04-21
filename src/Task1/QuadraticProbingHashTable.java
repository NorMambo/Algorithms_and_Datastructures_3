package Task1;

import Task2.Car;

import java.util.ArrayList;
import java.util.Iterator;
import SupportClasses.Functions;

import static java.lang.Math.sqrt;

public class QuadraticProbingHashTable<T> implements Iterable {
    private int currentSize; // The number of occupied cells
    private HashEntry<T> [] array; // The array of elements

    private ArrayList<Double> offsets = new ArrayList<>();

    private int collisions = 0;
    private int insertionNr = 0;
    private int firstCollisionAtInsertion = 0;

    public QuadraticProbingHashTable( int size ) {
        allocateArray( size );
        makeEmpty( );
    }
    public void makeEmpty( ) {
        currentSize = 0;
        for( int i = 0; i < array.length; i++ ) {
            array[i] = null;
        }
    }

    public boolean contains( T item ) {
        int currentPos = findPos( item );
        return isActive( currentPos );
    }
    public void insert(T item) {

        // Insert item as active
        int currentPos = findPos(item);
        if(isActive( currentPos ))
            return;

        insertionNr++;
        array[ currentPos ] = new HashEntry<>(item, true);

//        if( ++currentSize > array.length / 2 )
//            rehash( );
    }

    public void remove(T item) {
        int currentPos = findPos(item);
        if(isActive( currentPos ))
            array[ currentPos ].isActive = false;
    }

    private static class HashEntry<T> {
        public T element; // the element
        public boolean isActive; // false if marked deleted
        public HashEntry( T e ) { this( e, true ); }
        public HashEntry(T e, boolean i ) { element = e; isActive = i; }
    }

    // allocateArray makes sure that the size used will be transformed into
    private void allocateArray( int arraySize ) {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    private boolean isActive( int currentPos ) {
        return array[currentPos] != null && array[ currentPos ].isActive;
    }

    private int findPos(T item) {

        int offset = 1;
        int currentPos;

        // hashing for task 2
        if (item instanceof Car)
            currentPos = ((Car) item).hash(array.length);
            // hashing for task 1
        else
            currentPos = hash(item);

        while (array[currentPos] != null && !array[currentPos].element.equals(item)) {
            if (collisions == 0)
                firstCollisionAtInsertion = insertionNr + 1;
            collisions++;
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length)
                currentPos -= array.length;
        }

        offsets.add((double) offset);
        return currentPos;
    }

    public double getAvgOffset() {
        Functions fs = new Functions();
        double avg = fs.findAvg(offsets);
        return avg;
    }

    public double getMaxOffset() {
        double max = 0;
        for (int i = 0; i < offsets.size(); i++) {
            if (i == 0)
                max = offsets.get(i);
            else
                if(offsets.get(i) > max)
                    max = offsets.get(i);
        }
        this.offsets = new ArrayList<>();
        return max;
    }

    public int getFirstCollisionAtInsertion() {
        return firstCollisionAtInsertion;
    }

    public int getInsertions() {
        return insertionNr;
    }

//    private void rehash( ) {
//        HashEntry<T>[] oldArray = array;
//        // Create a new double-sized, empty table
//        allocateArray( nextPrime( 2 * oldArray.length ) );
//        currentSize = 0;
//        // Copy table over
//        for( int i = 0; i < oldArray.length; i++ )
//            if( oldArray[ i ] != null && oldArray[ i ].isActive )
//                insert( oldArray[ i ].element );
//    }
//    private int hash( T item ) {
//        int hashVal = item.hashCode();
//
//        hashVal %= array.length;
//        if (hashVal < 0)
//            hashVal += array.length;
//
//        return hashVal;
//    }
    private int hash(T item) {
        int ascii;
        int sum = 0;
        for (int i = 0; i < item.toString().length(); ++i) {
            char c = item.toString().charAt(i);
            ascii = c;
            sum += ascii;
        }
//        System.out.println(sum);
        return sum%array.length;
    }

    /*
     * Returns the next prime number if the current one isn't prime.
     */
    private static int nextPrime( int num ) {

        if (isPrime(num))
            return num;

        int counter;
        num++;
        while(true){
            int l = (int) sqrt(num);
            counter = 0;
            for(int i = 2; i <= l; i ++){
                if(num % i == 0)
                    counter++;
            }
            if(counter == 0)
                return num;
            else{
                num++;
                continue;
            }
        }
    }

    /*
     * Checks whether the number is prime.
     */
    private static boolean isPrime( int num ) {
        boolean prime = true;
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                prime = false;
                break;
            }
        }
        return (prime);
    }

    public int getCollisions() {
        return collisions;
    }

    @Override
    public Iterator iterator() {
        return new arrIterator();
    }

    private class arrIterator implements Iterator {

        int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < array.length;
        }

        @Override
        public Object next() {
            HashEntry tmp = array[pos];
            pos++;
            if (tmp == null || !tmp.isActive)
                return "__";
            else
                return tmp.element.toString();
        }
    }
    public static void main(String[] args) {

        QuadraticProbingHashTable q = new QuadraticProbingHashTable<>(101); // 37

        // all inserted elements collide because they have the same ascii value,
        // so the distances from the first occupied position are:
        // pos 8   -  7  =  1   -> (1ˆ2)
        // pos 11  -  7  =  4   -> (2ˆ2)
        // pos 16  -  7  =  9   -> (3ˆ2)
        // pos 23  -  7  =  16  -> (4ˆ2)
        // pos 32  -  7  =  25  -> (5ˆ2)
        // quadratic probing works

        q.insert(93);
        q.insert(75);
        q.insert('l');
        q.insert(48);
        q.insert(57);
        q.insert(66);
        q.insert(84);

        for (Object o : q) {
            System.out.print(o + " ");
        }

        System.out.println("\n\nContains 93: " + q.contains(93));
        System.out.println("Removing 93");
        q.remove(93);
        System.out.println("Contains 93: " + q.contains(93) + "\n");

        for (Object o : q) {
            System.out.print(o + " ");
        }
    }
}
