package org.darcy.test.EhCacheTest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.LoggerFactory;

/**
 * 
 * @author XXX
 * @version $Id: EncacheTest.java, v 0.1 2014年8月8日 下午5:30:03 XXX Exp $
 */
public class EncacheTest {
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(EncacheTest.class);
	// 一些配置参数
	// private final static String configFileName = "ehcache.xml";
	// private final static int maxEntriesLocalHeap = 1000;
	private static CacheManager cacheManager;
	static String cacheName = "cache1";

	public static void main(String[] args) {
		ehcacheSetUp();
		ehcacheUse();
	}

	private static void ehcacheSetUp() {
		logger.info("Setup ehcache");

		cacheManager = CacheManager.create();

		// CacheConfiguration configuration = new
		// CacheConfiguration(configFileName,
		// maxEntriesLocalHeap);

		// Cache cache = new Cache(configuration);
		cacheManager.addCache(cacheName);

	}

	private static void ehcacheUse() {
		Cache cache1 = cacheManager.getCache(cacheName);
		String key = "key1";
		String value = "value1";

		writeSomeData(cache1, key, value);

		Element element = readSomeData(cache1, key, value);

		System.out.println(element);
	}

	private static void writeSomeData(Cache cache, String key, String value) {
		cache.put(new Element(key, value));
	}

	private static Element readSomeData(Cache cache, String key, String value) {
		return cache.get(key);
	}
}
