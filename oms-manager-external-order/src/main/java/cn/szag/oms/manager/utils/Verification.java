package cn.szag.oms.manager.utils;

import java.util.HashMap;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.szag.oms.manager.ajax.AjaxRes;
import cn.szag.oms.manager.domain.User;


public class Verification {
	/**
	 * 令牌验证
	 * @title: getUser
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019�?5�?20�? 下午3:46:00
	 * @param token
	 * @return
	 * @throws UserException
	 * @throws OAuthSystemException
	 * @throws OAuthProblemException
	 * @return: User
	 */
	@SuppressWarnings("unused")
	public static  User getUser(String token,RedisUtil redisUtil){
		User user = null;
		AjaxRes ar = userInfo(token,redisUtil);
		int res = ar.getRes();
		if (res == 2) {
			throw new UserException(ar);
		} else if (res == 0) {
			ar.setRes(-1);
			throw new UserException(ar);
		} else {
			user = (User) ar.getObj();
		}

		return user;
	}

	
	/**
	 * 令牌有效期验
	 * @title: userInfo
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019�?5�?20�? 下午3:54:07
	 * @param token
	 * @return
	 * @return: AjaxRes
	 */
	public static AjaxRes userInfo(String token,RedisUtil redisUtil) {
		AjaxRes ar = new AjaxRes();
		if (StringUtils.isEmpty(token)) {
			ar.setFailMsg("没有参数");
			return ar;
		}
		try {
			HashMap<String, Object> map = redisUtil.getData(token);
			System.out.println(map);
			long time = 0;
			try {
				time = Long.parseLong(map.get("time").toString());
			} catch (NullPointerException e) {
				System.out.println("redis服务未启动或用户未登陆！");
				throw new RuntimeException();
			}
			String data = redisUtil.get(token);
			if (time > 0) {
				if (time <= 60000L) {
					String token_new = JSONObject.parseObject(data, User.class).getId();
					redisUtil.del(token);
					redisUtil.set(token_new, data);

					ar.setRes(2);
					ar.setSucceed(token_new);
					return ar;
				}

				User user = JSONObject.parseObject(data, User.class);
				ar.setSucceed(user);
			} else {
				ar.setRes(0);
				ar.setFailMsg("验证失败");
			}
		} catch (Exception e) {
			ar.setFailMsg("验证失败");
		}

		return ar;
	}
}
