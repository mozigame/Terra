package com.babel.terra.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


//@EqualsAndHashCode(callSuper = true)
@Data
public class UserOrderPO implements Serializable, Cloneable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * cid
     */
    private Long cid;

    /**
     * 平台商id
     */
    private Long platInfoId;


    /**
     * 父方案号 : 父方案号或追号
     */
    private String parentOrderId;

    /**
     * 方案号
     */
    private String orderId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 彩种id
     */
    private Long lotteryId;

    /**
     * 代理id
     */
    private Long agentId;

    /**
     * 代理id
     */
    private Long levelId;

    /**
     * 玩法id
     */
    private Long playId;

    /**
     * 期号
     */
    private Long pcode;

    /**
     * 日期
     */
    private Long pdate;

    /**
     * 投注时间
     */
    private Long betTime;


    /**
     * 彩种单边或者双边：1单边，2双边
     */
    private Integer sideType;

    /**
     * 账户类型
     */
    private Integer acType;

    /**
     * 注数
     */
    private Integer betCount;

    /**
     * 注单金额
     */
    private Long betAmount;

    /**
     * 有效投注金额
     */
    private Long validBetAmount;

    /**
     * 下注号码
     */
    private String betContent;

    /**
     * 倍数
     */
    private Integer multiple;

    /**
     * 元角分模式 : 元角分模式
     */
    private String moneyMode;


    /**
     * 派彩
     */
    private Long payoff = 0L;


    /**
     * 注单状态 :
     * 1,等待开奖
     * 31,未中奖
     * 32,已派彩
     * 4,用户撤单
     * 5,系统撤单
     * 6,中奖停追
     * 71,存在异常
     * 81,异常处理中
     */
    private Integer orderStatus;

    /**
     * 来源
     */
    private Integer source;


    /**
     * create_time
     */
    private Long createTime;

    /**
     * create_user
     */
    private Long createUser;

    /**
     * modify_time
     */
    private Long modifyTime;

    /**
     * modify_user
     */
    private Long modifyUser;



    private String winNumber;   //奖号

    /*-------------------不入库的数据-------------------------*/
//    @Transient


    private int isTied; //是否是和(平局),0.否，1.是

    private String issueAlias;

    private String memberName;


}