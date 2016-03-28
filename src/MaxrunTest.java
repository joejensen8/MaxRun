
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Created by Joe on 10/14/2015.
 */
public class MaxrunTest
{
    Maxrun<Integer> myRun;

    @Before
    public void initialize() throws Exception
    {
        myRun = Maxrun.initialize(5);
    }

    @Test
    public void testOffer() throws Exception
    {
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(1);
        Maxrun.Test myTest = myRun.new Test();
        /*
        Code Coverage, Good data
        Branch/Boundary Test: newestElement < k
            This test will occur on the first offer
        Branch/Boundary Test: numElements != k
            This test will occur on the first offer
        Branch/Boundary Test: numElements < k
            This test will occur on the first offer
         */
        myRun.offer(1);
        assertEquals(myTest.getMyArray(), testList);

        /*
        Branch/Boundary Test: numElements = k
            will occur when we offer the kth time and from then on
        Good data
         */
        myRun.offer(2);myRun.offer(3);myRun.offer(4);myRun.offer(5);
        testList.add(2);testList.add(3);testList.add(4);testList.add(5);
        assertEquals(myTest.getMyArray(), testList);

        /*
        Code Coverage, Good data
        Branch/Boundary Test: newestElement >= k
            This test will require us offering more than k times (offer 6 times)
        Boundary Test: numElements = k
            This will happen when we start offering elements after hitting k
        */
        myRun.offer(6);
        testList.set(0, 6);
        assertEquals(myTest.getMyArray(), testList);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testOfferNull() throws Exception
    {
        /*
        Code coverage, Bad data, Branch coverage
            offering null element should throw exception
         */
        myRun.offer(null);
    }

    @Test (expected = RuntimeException.class)
    public void testMaxError() throws Exception
    {
        /*
        Code Coverage, Bad data
        Branch Coverage: numElements = 0
         */
        myRun.max();
    }

    @Test
    public void testMax() throws Exception
    {
        /*
        Code Coverage, Good data
        Branch Coverage: numElements != 0
         */
        myRun.offer(1);
        assertEquals(new Integer(1), myRun.max());

        /*
        Legacy Tests
         */
        myRun.offer(1);
        assertEquals(myRun.max(), new Integer(1));
        myRun.offer(2);
        myRun.offer(3);
        myRun.offer(4);
        myRun.offer(5);
        assertEquals(myRun.max(), new Integer(5));
        myRun.offer(6);
        assertEquals(myRun.max(), new Integer(6));
        myRun.offer(7);
        myRun.offer(8);
        myRun.offer(9);
        myRun.offer(10);
        myRun.offer(11);
        myRun.offer(12);
        assertEquals(myRun.max(), new Integer(12));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInitializeNegativeK() throws Exception
    {
        /*
        Code Coverage, Branch Coverage, Boundary Test, Bad data
            k < 0
         */
        Maxrun<Integer> run = Maxrun.initialize(-1);
    }

    @Test
    public void testInitializeGoodK() throws Exception
    {
        /*
        Code Coverage, Branch Coverage, Boundary Test, Good data
            k > 0
         */
        Maxrun<Integer> run = Maxrun.initialize(1);
        Maxrun.Test myTest = run.new Test();
        assertEquals(myTest.getMyK(), 1);
        assertEquals(myTest.getMyNewestElement(), -1);
        assertEquals(myTest.getMyNumElements(), 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInitializeZeroK() throws Exception
    {
        /*
        Boundary Test, Bad data
            k = 0
         */
        Maxrun<Integer> run = Maxrun.initialize(0);
    }

    @Test
    public void stressTest() throws Exception
    {
        /*
        Stress Test:
            Create Maxrun with high k value (1,000,000), and offer a lot of times (1,800,000)
         */
        Maxrun<Integer> runner = Maxrun.initialize(1000000);
        for (int i = 0; i < 1800000; i++)
        {
            runner.offer((int) (Math.random() * 100));
        }

        assertTrue(runner.max() < new Integer(100));
    }
}