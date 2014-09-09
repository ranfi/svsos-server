package com.svsos.api.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;

public interface RedisService {

	/**
	 * 通过key删除
	 * 
	 * @param key
	 */
	public abstract long del(String... keys);

	/**
	 * 通过key删除
	 * 
	 * @param key
	 */
	public long del(final Set<byte[]> keys);

	/**
	 * 添加key value 并且设置存活时间(byte)
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public abstract void set(byte[] key, byte[] value, long liveTime);

	/**
	 * 添加key List 并且设置存活时间(byte)
	 * 
	 * @param key
	 * @param values
	 * @param liveTime
	 */
	public abstract void setList(String key, List<? extends Object> values, long liveTime);

	/**
	 * 添加实体到List,并且设置存活时间(byte)
	 * 
	 * @param key
	 * @param values
	 * @param liveTime
	 */
	public abstract void setList(String key, Object entity, long liveTime);

	/**
	 * 添加实体到List的最右边,并且设置存活时间(byte)
	 * 
	 * @param key
	 * @param values
	 * @param liveTime
	 */
	public abstract void setRightList(String key, Object entity, long liveTime);

	/**
	 * 获取List对象
	 * 
	 * @param key
	 * @return
	 */
	public abstract <T> List<T> getList(String key);

	/**
	 * 获取区间List对象
	 * 
	 * @param key
	 * @return
	 */
	public abstract <T> List<T> getRangeList(String key, int startIndex, int endIndex);

	/**
	 * 添加key, entity, 并且设置存活时间(byte)
	 * 
	 * @param key
	 * @param entity
	 * @param liveTime
	 */
	public abstract void setEntity(String key, Object entity, long liveTime);

	/**
	 * 获取实体
	 * 
	 * @param key
	 * @return
	 */
	public abstract <T> T getEntity(String key);

	/**
	 * 添加key value 并且设置存活时间
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 *            单位秒
	 */
	public abstract void set(String key, String value, long liveTime);

	/**
	 * 添加key value
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void set(String key, String value);

	/**
	 * 添加key value (字节)(序列化)
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void set(byte[] key, byte[] value);

	/**
	 * 使用hash添加key,value
	 * 
	 * @param key
	 * @param paramKey
	 * @param paramValue
	 */
	public abstract void set(String key, Object paramKey, Object paramValue);

	/**
	 * 使用hash添加key,Map
	 * 
	 * @param key
	 * @param paramValue
	 */
	public abstract void setMap(String key, Map<? extends Object, ? extends Object> paramMap, long liveTime);

	/**
	 * 获取Map对象
	 * 
	 * @param key
	 * @return
	 */
	public abstract Map<? extends Object, ? extends Object> getMap(String key);

	/**
	 * 从hash里面获取缓存键值
	 * 
	 * @param key
	 * @param paramKey
	 * @return
	 */
	public abstract <T> T get(String key, String paramKey);

	/**
	 * 
	 * @param key
	 * @param data
	 * @param queryCallback
	 * @param liveTime
	 * @return
	 */
	public abstract <T> T findEntityForCacheOrDb(final String key, QueryCallback<T> queryCallback, long liveTime);

	/**
	 * 
	 * @param key
	 * @param data
	 * @param queryCallback
	 * @param liveTime
	 * @return
	 */
	public abstract Map<? extends Object, ? extends Object> findMapForCacheOrDb(final String key,
			QueryCallback<Map<? extends Object, ? extends Object>> queryCallback, long liveTime);

	/**
	 * 
	 * @param key
	 * @param data
	 * @param queryCallback
	 * @param liveTime
	 * @return
	 */
	public abstract <T> List<T> findListForCacheOrDb(final String key, QueryCallback<List<T>> queryCallback,
			long liveTime);

	/**
	 * 通过正则匹配keys
	 * 
	 * @param pattern
	 * @return
	 */
	public abstract Set<byte[]> keys(String pattern);

	public ListOperations<Serializable, Object> getOpsForList();
	
	public SetOperations<Serializable, Object> getOpsForSet();
	
	public ZSetOperations<Serializable, Object> getOpsForZset();

	/**
	 * 检查key是否已经存在
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean exists(String key);

	/**
	 * 清空redis 所有数据
	 * 
	 * @return
	 */
	public abstract String flushDB();

	/**
	 * 查看redis里有多少数据
	 */
	public abstract long dbSize();

	/**
	 * 检查是否连接成功
	 * 
	 * @return
	 */
	public abstract String ping();

}
