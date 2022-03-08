package com.bolin.logistics.model;

public class Pay {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay.id
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay.goods_info_id
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    private Long goodsInfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay.total_payment
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    private Integer totalPayment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay.pay_mode
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    private Integer payMode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay.transfer_fee
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    private Integer transferFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay.carry_goods_fee
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    private Integer carryGoodsFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay.gmt_create
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay.gmt_modified
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay.id
     *
     * @return the value of pay.id
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay.id
     *
     * @param id the value for pay.id
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay.goods_info_id
     *
     * @return the value of pay.goods_info_id
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay.goods_info_id
     *
     * @param goodsInfoId the value for pay.goods_info_id
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay.total_payment
     *
     * @return the value of pay.total_payment
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public Integer getTotalPayment() {
        return totalPayment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay.total_payment
     *
     * @param totalPayment the value for pay.total_payment
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public void setTotalPayment(Integer totalPayment) {
        this.totalPayment = totalPayment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay.pay_mode
     *
     * @return the value of pay.pay_mode
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public Integer getPayMode() {
        return payMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay.pay_mode
     *
     * @param payMode the value for pay.pay_mode
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay.transfer_fee
     *
     * @return the value of pay.transfer_fee
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public Integer getTransferFee() {
        return transferFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay.transfer_fee
     *
     * @param transferFee the value for pay.transfer_fee
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public void setTransferFee(Integer transferFee) {
        this.transferFee = transferFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay.carry_goods_fee
     *
     * @return the value of pay.carry_goods_fee
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public Integer getCarryGoodsFee() {
        return carryGoodsFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay.carry_goods_fee
     *
     * @param carryGoodsFee the value for pay.carry_goods_fee
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public void setCarryGoodsFee(Integer carryGoodsFee) {
        this.carryGoodsFee = carryGoodsFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay.gmt_create
     *
     * @return the value of pay.gmt_create
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay.gmt_create
     *
     * @param gmtCreate the value for pay.gmt_create
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay.gmt_modified
     *
     * @return the value of pay.gmt_modified
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay.gmt_modified
     *
     * @param gmtModified the value for pay.gmt_modified
     *
     * @mbg.generated Wed Mar 09 00:49:58 CST 2022
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}