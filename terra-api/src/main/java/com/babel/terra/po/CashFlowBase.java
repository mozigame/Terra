package com.babel.terra.po;

import lombok.Data;

/**
 * User: joey
 * Date: 2018/1/31
 * Time: 1:37
 */
@Data
public class CashFlowBase {

    private String cashFlowId;
    private Long memberId;
    private String memberAccount;
    private Long agentId;
    private String agentAccount;
    private Long levelId;

    /**
     * 账变行为
     * 充提相关金流：
        公司入款
        线上入款
        人工入款
        会员出款
        人工提出
        给予优惠
        会员出款扣款
        给予返水

     * 投注相关金流：
        投注
        投注返点
        派奖
        撤单返款
        撤销返点
        撤销派奖
     */
    private Integer tradeType;
    /**
     * 账变方式
     */
    private Integer actionType;
    /**
     * 基础金流类型，
     * 1.充提
     * 2.投注
     */
    private Integer baseFlowType;
    /**
     * 金额
     */
    private Long amount;
    /**
     * 可用余额
     */
    private Long balance;
    /**
     * 备注
     */
    private String remark;

    private Long createTime;
    /**
     * 账变时间
     */
    private Long modifyTime;



}
