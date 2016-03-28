
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Joe on 10/11/2015.
 */
public class Maxrun <T extends Comparable<T>>
{
    /*
    variable newestElement (integer)
    */
    private int newestElement;
    /*
    variable numElements (integer)
    */
    private int numElements;
    /*
    variable kArray (array)
    */
    private ArrayList<T> kArray;

    // not from pseudo code - needed for ArrayList "set" vs "add"
    private int k;

    /*
    INITIALIZE(k)
    */
    public static Maxrun initialize(int k) throws Exception
    {
        if (k <= 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            return new Maxrun(k);
        }
    }

    private Maxrun(int k)
    {
        /*
        intialize an array to size k
        */
        kArray = new ArrayList<>(k);
        /*
        variable newestElement: (indicates newest element added) - initially -1
        */
        newestElement = -1;
        /*
        variable numElements: initialized to 0. Tracks number of elements added if less than k
        */
        numElements = 0;

        // setting variable k, not from pseudo code
        this.k = k;
    }

    /*
    OFFER(x)
    */
    public void offer(T x)
    {
        /*
        Not from pseudo code
         */
        if (x == null)
        {
            throw new IllegalArgumentException();
        }
        /*
        newestElement <- newestElement + 1
        */
        newestElement++;
        /*
        if newestElement is out of array bounds
        */
        if (newestElement >= k)
        {
            /*
            newestElement <- 0
            */
            newestElement = 0;
        }
        /*
        add x to array at newestElement index
        */
        if (numElements != k)
            kArray.add(newestElement, x);
        else
            kArray.set(newestElement, x);
        /*
        if numElements is less than k (the size of our list)
        */
        if (numElements < k)
        {
            /*
            numElements <- numElements + 1
            */
            numElements++;
        }
    }

    /*
    MAX
    */
    public T max()
    {
        /*
        if numElements is 0
        */
        if (numElements == 0)
        {
            /*
            exception: no elements have been offered thus far!
             */
            throw new RuntimeException();
        }
        /*
        find and return the max of the array
        */
        return Collections.max(kArray);
    }

    /**
     * This inner class is used to provide access to various private fields for testing
     */
    public class Test
    {
        private ArrayList<T> myArray = kArray;
        private int myNewestElement = newestElement;
        private int myNumElements = numElements;
        private int myK = k;

        public ArrayList<T> getMyArray()
        {
            return new ArrayList<>(myArray);
        }

        public int getMyNewestElement()
        {
            return myNewestElement;
        }

        public int getMyNumElements()
        {
            return myNumElements;
        }

        public int getMyK()
        {
            return myK;
        }
    }

}
