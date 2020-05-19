/**
 * 
 */
package cn.szag.oms.manager.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

public class KafkaProvider {
	private static Logger LOG = LoggerFactory.getLogger(KafkaProvider.class);
	public static final ClassPathXmlApplicationContext CONTEXT = new ClassPathXmlApplicationContext(
			"/spring/spring-kafka.xml");

	// 用于整合
	public void reciver(String key, String value) {
		System.out.println("发送数据成功");
		System.out.println("key=" + key + "\t" + "value= " + value);
		String topic = "topicTest001";
		Integer partition = 2;
		Long timeStamp = new Date().getTime();
		sendMessage(topic, timeStamp, key, value);
	}

	/**
	 * 发送数据
	 */
	public <K, T> void sendMessage(String topic, Long timestamp, K key, T data) {
		@SuppressWarnings("unchecked")
		KafkaTemplate<K, T> kafkaTemplate = (KafkaTemplate<K, T>) CONTEXT.getBean("KafkaTemplate");
		ListenableFuture<SendResult<K, T>> listenableFuture = null;
		listenableFuture = kafkaTemplate.send(topic, key, data);

		// if (kafkaTemplate.getDefaultTopic().equals(topic)) {
		// listenableFuture = kafkaTemplate.sendDefault(partition, timestamp,
		// key, data);
		// } else {
		// listenableFuture = kafkaTemplate.send(topic, partition, timestamp,
		// key, data);
		// }
		// 发送成功回调
		SuccessCallback<SendResult<K, T>> successCallback = new SuccessCallback<SendResult<K, T>>() {
			@Override
			public void onSuccess(SendResult<K, T> result) {
				// 成功业务逻辑
				System.out.println("推送数据至kafka成功 --topic:" + topic);
				LOG.info("推送数据至kafka成功 --topic:" + topic);
			}
		};
		// 发送失败回调
		FailureCallback failureCallback = new FailureCallback() {
			@Override
			public void onFailure(Throwable ex) {
				// 失败业务逻辑.
				System.out.println("推送数据至kafka失败 --topic:" + topic);
				LOG.info("推送数据至kafka失败--topic:" + topic);
				throw new RuntimeException(ex);
			}
		};
		listenableFuture.addCallback(successCallback, failureCallback);
	}
}
