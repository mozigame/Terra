package com.babel.terra.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component 
@ConfigurationProperties(prefix = "customer")
public class CustomerConfig {
	private final Logger log = LoggerFactory.getLogger(CustomerConfig.class);
	private String runType;
	//forsetti对接payment,cms,config时的配置信息缓存时间，为0表示不缓存
	private Integer cacheInterval;
	/**
	 * 配置见注册中心的git
	 */
	 private Integer lotteryRunSideType;
	 /**
	  * www.baidu.com|plat_owner_test
	  */
	 private List<String> domainAppidBinds;//真实会员平台商appid配置
	 /**
	  * 权限资源配置
	  * exp:/v2/api-docs|ROLE_ADMIN
	  */
	 private List<String> permitResRoles;
	 private List<String> whiteIpList; 
	private boolean orderSaveBatchRunType;

	/**
	 * @return the domainAppidBinds
	 */
	public List<String> getDomainAppidBinds() {
		return domainAppidBinds;
	}

	/**
	 * @param domainAppidBinds the domainAppidBinds to set
	 */
	public void setDomainAppidBinds(List<String> domainAppidBinds) {
		this.domainAppidBinds = domainAppidBinds;
		domainAppidMap= getDomainAppidListToMap(domainAppidBinds);
	}
	
	private Map<String, String> domainAppidMap=new HashMap<>();
	private Map<String, String> getDomainAppidMap(){
		if(domainAppidMap.isEmpty()){
			domainAppidMap= getDomainAppidListToMap(domainAppidBinds);
			log.info("-----domainAppidBinds="+domainAppidBinds
					 +"\n domainAppidMap="+domainAppidMap);
		}
		return domainAppidMap;
	}

	


	/**
	 * @return the permitResRoles
	 */
	public List<String> getPermitResRoles() {
		return permitResRoles;
	}

	private Map<String, String> permitResRoleMap=new HashMap<>();
	public Map<String, String> getPermitResRoleMap(){
		if(permitResRoleMap.isEmpty()){
			permitResRoleMap= getDomainAppidListToMap(permitResRoles);
			log.info("-----permitResRoles="+permitResRoles
					 +"\n permitResRoleMap="+permitResRoleMap);
		}
		return permitResRoleMap;
	}
	/**
	 * @param permitResRoles the permitResRoles to set
	 */
	public void setPermitResRoles(List<String> permitResRoles) {
		this.permitResRoles = permitResRoles;
		permitResRoleMap= getDomainAppidListToMap(permitResRoles);
	}


	/**
	 * @param lotteryRunSideType the lotteryRunSideType to set
	 */
	public void setLotteryRunSideType(Integer lotteryRunSideType) {
		this.lotteryRunSideType = lotteryRunSideType;
		log.info("-----lotteryRunSideType--"+lotteryRunSideType);
	}


	/**
	 * @return the lotteryRunSideType
	 */
	public Integer getLotteryRunSideType() {
		return lotteryRunSideType;
	}

	public boolean isOrderSaveBatchRunType() {
		return orderSaveBatchRunType;
	}

	public void setOrderSaveBatchRunType(boolean orderSaveBatchRunType) {
		this.orderSaveBatchRunType = orderSaveBatchRunType;
		log.info("-----orderSaveBatchRunType--"+orderSaveBatchRunType);
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public boolean isRunTypeProd(){
		return "prod".equals(runType);
	}

	/**
	 * @return the cacheInterval
	 */
	public Integer getCacheInterval() {
		return cacheInterval;
	}

	/**
	 * @param cacheInterval the cacheInterval to set
	 */
	public void setCacheInterval(Integer cacheInterval) {
		this.cacheInterval = cacheInterval;
		log.info("-----cacheInterval--"+cacheInterval);
	}

	/**
	 * @return the whiteIpList
	 */
	public List<String> getWhiteIpList() {
		return whiteIpList;
	}

	/**
	 * @param whiteIpList the whiteIpList to set
	 */
	public void setWhiteIpList(List<String> whiteIpList) {
		this.whiteIpList = whiteIpList;
		log.info("-----whiteIpList--"+whiteIpList);
	}
	
	public static Map<String, String> getDomainAppidListToMap(List<String> domainAppidBinds) {
        Map<String, String> map = new HashMap();
        if(domainAppidBinds != null) {
            Iterator var2 = domainAppidBinds.iterator();

            while(true) {
                String[] strings;
                do {
                    if(!var2.hasNext()) {
                        return map;
                    }

                    String domainAppId = (String)var2.next();
                    strings = domainAppId.split("\\|");
                } while(strings.length != 2);

                String domain = strings[0];
                String appid = strings[1].trim();
                String[] domains = domain.split(",");
                String[] var8 = domains;
                int var9 = domains.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    String str = var8[var10];
                    map.put(str.trim(), appid);
                }
            }
        } else {
            return map;
        }
    }
	
	
}
