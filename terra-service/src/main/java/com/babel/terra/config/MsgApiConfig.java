package com.babel.terra.config;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//@Component 
//@ConfigurationProperties(prefix = "customer.msgApi")
public class MsgApiConfig implements IMsgApi {
	private final Logger log = LoggerFactory.getLogger(MsgApiConfig.class);
	private String url;
	private String uid;
	private String pwd;
	private String channel;
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
		log.info("-----msgApi--url="+url);
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
		log.info("-----msgApi--uid="+uid);
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
		log.info("-----msgApi--channel="+channel);
	}

	public void sendNoticeMsg(String sendFrom, String msgInfo) {
		if (StringUtils.isEmpty(this.getUrl())) {
			log.warn("-----sendNoticeMsg cancel by :url empty");
			return;
		}
//		try {
//			String sendResult = MsgSender.sendMessage(this, sendFrom, msgInfo);
//			log.info("----sendNoticeMsg--msgInfo=" + msgInfo + " result=" + sendResult);
//		} catch (Exception e) {
//			log.error("-----sendNoticeMsg--", e);
//		}
	}
	
}
