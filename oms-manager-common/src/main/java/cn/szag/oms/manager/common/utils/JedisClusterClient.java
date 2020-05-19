package cn.szag.oms.manager.common.utils;

import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * Java访问Redis集群
 * 
 * @ClassName: JedisClusterClient
 * @Description: TODO
 * @author dengyanghao
 * @date 2019418 上午9:41:51
 */
@Component
public class JedisClusterClient {
	private static int count = 0;
	JedisPoolConfig config = new JedisPoolConfig();
	Set<HostAndPort> clusterNodes;
	JedisCluster jedisCluster;
	JedisPool jedisPool = new JedisPool();

	/**
	 * 私有构函
	 */
	public JedisClusterClient() {
		clusterInit();
	}

	private void genClusterNode() {
		this.clusterNodes = new HashSet();
		this.clusterNodes.add(new HostAndPort("192.168.150.204", 7001));
		this.clusterNodes.add(new HostAndPort("192.168.150.204", 7002));
		this.clusterNodes.add(new HostAndPort("192.168.150.204", 7003));
		this.clusterNodes.add(new HostAndPort("192.168.150.204", 7004));
		this.clusterNodes.add(new HostAndPort("192.168.150.204", 7005));
		this.clusterNodes.add(new HostAndPort("192.168.150.204", 7006));
	}

	private void genJedisConfig() {
		config = new JedisPoolConfig();
		config.setMaxTotal(1000);
		config.setMaxIdle(1000);
		config.setTestOnBorrow(true);
	}

	public void clusterInit() {
		genClusterNode();
		genJedisConfig();
		jedisCluster = new JedisCluster(clusterNodes, 5000, config);
	}

	/**
	 * 获取Jedis对象 @Title: getJedis @Description: TODO @param @return @author
	 * dengyanghao @return Jedis @throws
	 */
	private synchronized Jedis getJedis() {
		Jedis jedis = null;
		if (null != jedisPool) {
			try {
				jedis = jedisPool.getResource();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jedis;
	}

	/**
	 * 回收Jedis对象资源 @Title: returnResource @Description: TODO @param @param
	 * jedis @author dengyanghao @return void @throws
	 */
	private synchronized void returnResource(Jedis jedis) {
		if (null != jedis) {
			jedisPool.returnBrokenResource(jedis);
		}
	}

	private synchronized void returnBrokenResource(Jedis jedis) {
		if (null != jedis) {
			jedisPool.returnBrokenResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: get @Description: TODO @param @param key @param @return @author
	 * dengyanghao @return String @throws
	 */
	public String get(String key) {
		/*Jedis jedis = getJedis();
		String json = null;
		try {
			json = jedisCluster.get(key);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}*/
		return jedisCluster.get(key);
	}

	/**
	 * 
	 * @Title: set @Description: TODO @param @param key @param @param
	 * value @param @return @author dengyanghao @return String @throws
	 */
	public String set(String key, String value) {
		/*Jedis jedis = getJedis();
		String json = null;
		try {
			json = jedisCluster.set(key, value);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			returnResource(jedis);
		}*/
		return jedisCluster.set(key, value);
	}

	public String set(String key, String value, int seconds) {
		/*Jedis jedis = getJedis();
		String json = null;
		try {
			json = jedisCluster.setex(key, seconds, value);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			returnResource(jedis);
		}*/
		return jedisCluster.setex(key, seconds, value);
	}

	public Long del(String key) {
		/*long i = 0;
		Jedis jedis = getJedis();
		try {
			i = jedisCluster.del(key);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			returnResource(jedis);
		}*/
		return jedisCluster.del(key);
	}

	public HashMap<String, Object> getData(String key) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("time", jedisCluster.ttl(key));
			map.put("data", jedisCluster.get(key));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return map;
	}

	/**
	 * 设置读秒 @Title: getTtl @Description: TODO @param @param
	 * key @param @return @author dengyanghao @return long @throws
	 */
	public long getTtl(String key) {
		/*long i = 0;
		Jedis jedis = getJedis();
		try {
			i = jedisCluster.ttl(key);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			returnResource(jedis);
		}*/
		return jedisCluster.ttl(key);
	}

	/**
	 * 获取请求次数 @Title: incr @Description: TODO @param @param
	 * key @param @return @author dengyanghao @return long @throws
	 */
	public long incr(String key) {
		return jedisCluster.incr(key);
	}

	/**
	 * 设置个key的过期时间（单位：秒
	 * 
	 * @param key
	 *            key
	 * @param seconds
	 *            多少秒后过期
	 * @return 1：设置了过期时间 0：没有设置过期时/不能设置过期时间
	 */
	public long expire(String key, int seconds) {
		if (key == null || key.equals("")) {
			return 0;
		}
		/*long i = 0;
		Jedis jedis = getJedis();
		try {
			i = jedisCluster.expire(key, seconds);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			returnResource(jedis);
		}*/

		return jedisCluster.expire(key, seconds);
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public boolean exists(String key) {
		/*boolean isExist = false;
		Jedis jedis = getJedis();
		try {
			isExist = jedisCluster.exists(key);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			returnResource(jedis);
		}*/
		return jedisCluster.exists(key);
	}
	/**
	 * 根据key查询value
	 * 
	 * @param key
	 *            
	 * @return
	 */
	public Object getValue(String key) {
		return jedisCluster.get(key);
	}
	public static void main(String[] args) {
		JedisClusterClient jd = new JedisClusterClient();
		jd.set("123456", "123456");
		System.out.println(jd.del("123456"));
	}
}
