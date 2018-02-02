package com.babel.terra.po;

/**
 * User: joey
 * Date: 2018/1/31
 * Time: 1:32
 */

import lombok.Data;

/**
 * 金流，注单
 */
@Data
public class CashFlow {

    private Long cid;

    private String cashFlowId;  //金流id

    private String orderOperId;   //注单操作id

    private Long lotteryId; //彩种id

    private Long platInfoId;    //平台商id

    private Long memberId;  //会员id
    private String memberName;  //会员账号

    private Long agentId;   //代理id
    private String agentName;   //代理账号

    private Integer levelId;   //层级id

    private Long playId;    //玩法id

    private String parentOrderId;   //父方案号 : 父方案号或追号

    private String orderId; //方案号

    private String orderInfo;   //方案信息json

    private Integer changeType; //账变类型

    private Long amount;    //金额

    private String flowInfo;    //流向

    private Long balance;    //余额

    private Long pdate;  //统计日期

    private Long createTime;    //create_time

    private Long modifyUser;    //modify_user

    private String remark;  //备注
    /**
     * 订单真实期号
     */
    private String issueAlias;


}
