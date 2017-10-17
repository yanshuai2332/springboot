package com.yan.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author yanshuai
 * @date 17/10/9
 */
@Component
public class RedisService {

	@Autowired
	private RedisTemplate redisTemplate;
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void set(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void delete(String key) {
		redisTemplate.opsForValue().getOperations().delete(key);
	}

	public void delete(Collection keys) {
		redisTemplate.opsForValue().getOperations().delete(keys);
	}

}
