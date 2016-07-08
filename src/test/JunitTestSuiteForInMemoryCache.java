package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * @author Dileep V
 *
 *Junit test suite for InMemoryCache class.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestAddRemoveObjects.class,
   TestExpiredCacheObjects.class,
   TestObjectsCleanupTime.class,
   TestPutAndGetImageToCache.class
})
public class JunitTestSuiteForInMemoryCache {   
}  	