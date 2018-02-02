package com.babel.terra.po;

import com.babel.terra.enums.BaseFlowType;
import lombok.Data;

/**
 * 会员金流，出入款优惠之类
 */
@Data
public class MemberTrade {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 会员账号
     */
    private String memberName;

    /**
     * 代理id
     */
    private Long agentId;

    /**
     * 代理账号
     */
    private String agentName;

    /**
     * 层级id
     */
    private Long levelId;

    /**
     * 层级名称
     */
    private String levelName;

    /**
     * 平台商id
     */
    private Long platInfoId;

    /**
     * 交易id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 父订单编号
     */
    private String orderParentNo;

    /**
     * 交易金额
     */
    private Long tradeAmount;

    /**
     * 余额
     */
    private Long balance;

    /**
     * 备注
     */
    private String remark;

    /**
     * 金流备注
     */
    private String cashRemark;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 交易类型交易类型（1-公司转账，2-钱包快充，3-网银支付，4-快捷支付）
     */
    private Integer tradeType;

    /**
     * 存取类型账变方式（产品定）(派奖,人工入款,公司入款,人工提款,会员出款)
     */
    private Integer actionType;

    /**
     * 交易类型
     */
    private Integer dealType;

    /**
     *
     */
    private Integer flowType;

    /**
     * (0-存款，1-取款)
     */
    private Integer chargeType;

    /**
     * 统计日期
     */
    private Integer pdate;

    /**
     * 交易记录状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 次数
     */
    private int times;
    /**
     * 总数
     */
    private int total;

    /**
     * 总金额
     */
    private long sumAmount;


    //开始时间
    private Long startTime;

    //结束时间
    private Long endTime;

    public static MemberTrade parse(CashFlowBase t) {
        MemberTrade cb = new MemberTrade();
        cb.setOrderNo(t.getId());
        cb.setPlatInfoId(t.getPlatInfoId());
        cb.setMemberId(t.getMemberId());
        cb.setMemberName(t.getMemberName());
        cb.setAgentId(t.getAgentId());
        cb.setAgentName(t.getAgentName());
        if (t.getLevelId() != null) {
            cb.setLevelId(Long.valueOf(t.getLevelId()));
        }
        cb.setTradeType(t.getTradeType());
        cb.setTradeAmount(t.getAmount());
        cb.setBalance(t.getBalance());
        cb.setCreateTime(t.getCreateTime());
        return cb;
    }

}