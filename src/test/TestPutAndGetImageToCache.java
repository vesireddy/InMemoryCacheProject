package test;

import static org.junit.Assert.assertEquals;
import java.io.File;
import org.junit.Test;
import cache.InMemoryCache;


/**
 * @author Dileep V
 *
 *Test class for putting and getting images to the cache.
 */
public class TestPutAndGetImageToCache {

	private static final String pic = "pic";

   
   @Test
   public void testPutAndGetImageToCache() {	
     
	// Test with timeToLiveInSeconds = 200 seconds
       // timerIntervalInSeconds = 500 seconds
       // maxItems = 1
   	
   	InMemoryCache cache = new InMemoryCache(200,500,1);
       
       File file = new File("index.png");
       cache.put(pic, file);
       assertEquals(1, cache.size()); 
       System.out.println(cache.get(pic));
       
       //assertion for to validate Image stored in cache.
       assertEquals(file, cache.get(pic)); 
    
   }
}