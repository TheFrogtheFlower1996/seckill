package com.zh.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 建达企业培育检测预警季度预警信息表
 * </p>
 *
 * @author zh
 * @since 2022-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SqQypyJcyjQuarterEnterpriseWarning implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一性编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 信用代码
     */
    private String enterpriseCode;

    /**
     * 证书编号
     */
    private String certNum;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 营收预警：0否1是
     */
    private Integer isRevenueWarning;

    /**
     * 利润预警：0否1是，2未知
     */
    private Integer isProfitWarning;

    /**
     * 研发人员预警：0否1是，2未知
     */
    private Integer isPersonalWarning;

    /**
     * 研发强度预警：0否1是，2未知
     */
    private Integer isManagementWarning;

    /**
     * 高新技术产品（服务）收入占比预警：0否1是，2未知
     */
    private Integer isGxjscpsrWarning;

    /**
     * 能源预警:0否1是，2未知
     */
    private Integer isEnergyCount;

    /**
     * 是否减免税:0否1是，2未知
     */
    private Integer isTaxDeduction;

    /**
     * 是否加计扣除:0否1是，2未知
     */
    private Integer isJjkcCount;

    /**
     * 研发强度是否达标:0否1是，2未知
     */
    private Integer isManagementStandards;

    /**
     * 研发人员占比是否达标:0否1是，2未知
     */
    private Integer isPersonalStandards;

    /**
     * 营收是否增长:0否1是，2未知
     */
    private Integer isRevenueUp;

    /**
     * 是否盈利:0否1是，2未知
     */
    private Integer isProfit;

    /**
     * 能耗是否优于规上:0否1是，2未知
     */
    private Integer isEnergyOutbalance;

    /**
     * 能耗是否优于规上2倍一下:0否1是，2未知
     */
    private Integer isEnergyOutbalanceDoubleDown;

    /**
     * 能耗是否优于规上2倍一上:0否1是，2未知
     */
    private Integer isEnergyOutbalanceDoubleUp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 通用状态：1,enabled,可用；2,disabled,不可用；
     */
    private Integer status;

    /**
     * 是否删除，1是0否
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String county;

    /**
     * 街道
     */
    private String street;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 季度
     */
    private Integer quarter;

    /**
     * 类别(高新技术企业:gq,省科技型企业：skx,国家型科技企业：gkx,小巨人：xjr,领军：lj)
     */
    private String type;

    /**
     * 企业住所
     */
    private String enterpriseDomicile;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系方式
     */
    private String contactInformation;

    /**
     * 当前资质
     */
    private String presentQualification;


}
