package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import cache.InMemoryCache;

/**
 * @author Dileep V
 *
 *Test class for add and remove objects  to the cache.
 */
public class TestAddRemoveObjects {

	@SuppressWarnings("unused")
	private static final String pic = "pic";

   
   @Test
   public void testAddRemoveObjects() {	
     
	// Test with timeToLiveInSeconds = 200 seconds
       // timerIntervalInSeconds = 500 seconds
       // maxItems = 5
   	
   	InMemoryCache cache = new InMemoryCache(200,500,5);
	   
       cache.put("JSON", "JSON");
       cache.put("JSON1", "JSON1");
       cache.put("JSON2", "JSON2");
       cache.put("xml", "xml");
       cache.put("xml1", "xml1");
       cache.remove("JSON");
       cache.put("xml2", "xml2");
       
       //Validate the cache size.
       assertEquals(5, cache.size());     
   }
}