package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import cache.InMemoryCache;

/**
 * @author Dileep V
 *
 *Test class for Expired Cache Objects.
 */
public class TestExpiredCacheObjects {

  

   
   @Test
   public void testExpiredCacheObjects() {	
     
	  
   	
	   // Test with timeToLiveInSeconds = 1 second
       // timerIntervalInSeconds = 1 second
       // maxItems = 10
   	
   	InMemoryCache cache = new InMemoryCache(1,1,10);	  
	   
        cache.put("Dileep", "Dileep");
        cache.put("Vesireddy", "Vesireddy");
        // Adding 3 seconds sleep.. Both above objects will be removed from
        // Cache because of timeToLiveInSeconds value
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        //validate cache size.
        assertEquals(0, cache.size());     
   }
}