package cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dileep V.
 * 
 *         This class provides the simple caching object using
 *         ConcurrentHashMap.
 */
public class InMemoryCache {

	private long timeToLive;
	private Map<String, InMemoryCacheObject> inMemoryCacheMap;

	/**
	 * 
	 * This inner class provides the InMemoryCacheObject instance.
	 */
	protected class InMemoryCacheObject {
		public long lastAccessed = System.currentTimeMillis();
		public Object value;

		protected InMemoryCacheObject(Object value) {
			this.value = value;
		}
	}

	/**
	 * @param timeToLive
	 * @param timerInterval
	 * @param maxItems
	 * 
	 *            parameterized constructor which will be running a Thread to
	 *            call the cleanup() method based on the params timeToLive and
	 *            timerInterval.
	 */
	public InMemoryCache(long timeToLive, long timerInterval, int maxItems) {

		this.timeToLive = timeToLive * 1000;

		inMemoryCacheMap = new ConcurrentHashMap<String, InMemoryCacheObject>(maxItems);

		if (timeToLive > 0 && timerInterval > 0) {

			Thread t = new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(timerInterval * 1000);
						} catch (InterruptedException ex) {
						}
						cleanup();
					}
				}
			});

			t.setDaemon(true);
			t.start();
		}
	}

	/**
	 * @param key
	 * @param value
	 * 
	 * 
	 *            PUT method
	 */
	@SuppressWarnings("unchecked")
	public void put(String key, Object value) {
		inMemoryCacheMap.put(key, new InMemoryCacheObject(value));
	}

	/**
	 * @param key
	 * 
	 *            GET method
	 */
	@SuppressWarnings("unchecked")
	public Object get(String key) {

		InMemoryCacheObject c = (InMemoryCacheObject) inMemoryCacheMap.get(key);
		if (c == null)
			return null;
		else {
			c.lastAccessed = System.currentTimeMillis();
			return c.value;
		}
	}

	/**
	 * @param Key
	 * 
	 *            REMOVE Method
	 */
	public void remove(String key) {
		inMemoryCacheMap.remove(key);
	}

	/**
	 * Method which provides the cache size.
	 */
	public int size() {
		return inMemoryCacheMap.size();
	}

	/**
	 * CLEANUP method.
	 */
	@SuppressWarnings("unchecked")
	public void cleanup() {
		long now = System.currentTimeMillis();
		Iterator<Entry<String, InMemoryCacheObject>> itr = inMemoryCacheMap.entrySet().iterator();
		List<String> deleteKey = new ArrayList<String>((inMemoryCacheMap.size() / 2) + 1);
		InMemoryCacheObject c = null;

		while (itr.hasNext()) {
			Entry<String, InMemoryCacheObject> entry = itr.next();
			c = entry.getValue();
			if (c != null && (now > (timeToLive + c.lastAccessed))) {
				deleteKey.add(entry.getKey());
			}
		}

		for (String tempkey : deleteKey) {
			inMemoryCacheMap.remove(tempkey);
		}

		Thread.yield();
	}
}
