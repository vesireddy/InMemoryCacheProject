package test;

import org.junit.Test;

import cache.InMemoryCache;

/**
 * @author Dileep V
 *
 *Test class for Objects Cleanup Time for cache.
 */
public class TestObjectsCleanupTime {

  

   
   @Test
   public void testObjectsCleanupTime() {	
     
	   int size = 500000;
   	
	   // Test with timeToLiveInSeconds = 100 second
       // timerIntervalInSeconds = 100 second
       // maxItems = 500000
   	
	   InMemoryCache cache = new InMemoryCache(100,100,500000);
	   
       for (int i = 0; i < size; i++) {
           String value = Integer.toString(i);
           cache.put(value, value);
       }

       try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

       long start = System.currentTimeMillis();
       cache.cleanup();
       double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
       
       //Validate the finish time not equal to zero.
        assert( 0 != finish);     
   }
}