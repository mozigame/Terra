package com.babel.terra.po;

import com.babel.terra.enums.BaseFlowType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * User: joey
 * Date: 2018/1/31
 * Time: 1:37
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(indexName = "ares_cash_flow", type = "cash_flow_v1")
@Data
public class CashFlowBase {

    private String id;
    private Long platInfoId;
    private Long memberId;
    private String memberName;
    private Long agentId;
    private String agentName;
    private Integer levelId;

    /**
     * 账变行为
     * 充提相关金流：
     * 公司入款
     * 线上入款
     * 人工入款
     * 会员出款
     * 人工提出
     * 给予优惠
     * 会员出款扣款
     * 给予返水
     * <p>
     * 投注相关金流：
     * 投注
     * 投注返点
     * 派奖
     * 撤单返款
     * 撤销返点
     * 撤销派奖
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


    public static CashFlowBase parse(CashFlow t) {
        CashFlowBase cb = new CashFlowBase();
        cb.setId(t.getCashFlowId());
        cb.setPlatInfoId(t.getPlatInfoId());
        cb.setMemberId(t.getMemberId());
        cb.setMemberName(t.getMemberName());
        cb.setAgentId(t.getAgentId());
        cb.setAgentName(t.getAgentName());
        cb.setLevelId(t.getLevelId());
        cb.setTradeType(t.getChangeType());
        cb.setBaseFlowType(BaseFlowType.bet.getCode());
        cb.setAmount(t.getAmount());
        cb.setBalance(t.getBalance());
        cb.setCreateTime(t.getCreateTime());
        cb.setModifyTime(t.getModifyUser());
        return cb;
    }

    public static CashFlowBase parse(MemberTrade t) {
        CashFlowBase cb = new CashFlowBase();
        cb.setId(t.getOrderNo());
        cb.setPlatInfoId(t.getPlatInfoId());
        cb.setMemberId(t.getMemberId());
        cb.setMemberName(t.getMemberName());
        cb.setAgentId(t.getAgentId());
        cb.setAgentName(t.getAgentName());
        cb.setLevelId(t.getLevelId().intValue());
        cb.setTradeType(t.getTradeType());
        cb.setBaseFlowType(BaseFlowType.in_out_cash.getCode());
        cb.setAmount(t.getTradeAmount());
        cb.setBalance(t.getBalance());
        cb.setCreateTime(t.getCreateTime());
        return cb;
    }

}
