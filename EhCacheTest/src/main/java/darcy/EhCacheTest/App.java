package darcy.EhCacheTest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
cache manager：缓存管理器，以前是只允许单例的，不过现在也可以多实例了
cache：缓存管理器内可以放置若干cache，存放数据的实质，所有cache都实现了Ehcache接口
element：单条缓存数据的组成单位
system of record（SOR）：可以取到真实数据的组件，可以是真正的业务逻辑、外部接口调用、
存放真实数据的数据库等等，缓存就是从SOR中读取或者写入到SOR中去的。
 
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        CacheManager manager = CacheManager.newInstance("src/config/ehcache.xml");  
        manager.addCache("testCache");  
        Cache test = manager.getCache("testCache");  
        test.put(new Element("key1", "value1"));  
        manager.shutdown(); 
    }
    
    public Cache createCache(){
    	int maxElements = 1000;
    	Cache testCache = new Cache(
    			  new CacheConfiguration("testCache", maxElements)
    			    .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
    			    .overflowToDisk(true)
    			    .eternal(false)
    			    .timeToLiveSeconds(60)
    			    .timeToIdleSeconds(30)
    			    .diskPersistent(false)
    			    .diskExpiryThreadIntervalSeconds(0));
    	return testCache;
    }
}
