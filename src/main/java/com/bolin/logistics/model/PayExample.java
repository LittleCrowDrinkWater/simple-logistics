package com.bolin.logistics.model;

import java.util.ArrayList;
import java.util.List;

public class PayExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public PayExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdIsNull() {
            addCriterion("goods_info_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdIsNotNull() {
            addCriterion("goods_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdEqualTo(Long value) {
            addCriterion("goods_info_id =", value, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdNotEqualTo(Long value) {
            addCriterion("goods_info_id <>", value, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdGreaterThan(Long value) {
            addCriterion("goods_info_id >", value, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_info_id >=", value, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdLessThan(Long value) {
            addCriterion("goods_info_id <", value, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_info_id <=", value, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdIn(List<Long> values) {
            addCriterion("goods_info_id in", values, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdNotIn(List<Long> values) {
            addCriterion("goods_info_id not in", values, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdBetween(Long value1, Long value2) {
            addCriterion("goods_info_id between", value1, value2, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_info_id not between", value1, value2, "goodsInfoId");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentIsNull() {
            addCriterion("total_payment is null");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentIsNotNull() {
            addCriterion("total_payment is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentEqualTo(Integer value) {
            addCriterion("total_payment =", value, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentNotEqualTo(Integer value) {
            addCriterion("total_payment <>", value, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentGreaterThan(Integer value) {
            addCriterion("total_payment >", value, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_payment >=", value, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentLessThan(Integer value) {
            addCriterion("total_payment <", value, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentLessThanOrEqualTo(Integer value) {
            addCriterion("total_payment <=", value, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentIn(List<Integer> values) {
            addCriterion("total_payment in", values, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentNotIn(List<Integer> values) {
            addCriterion("total_payment not in", values, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentBetween(Integer value1, Integer value2) {
            addCriterion("total_payment between", value1, value2, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andTotalPaymentNotBetween(Integer value1, Integer value2) {
            addCriterion("total_payment not between", value1, value2, "totalPayment");
            return (Criteria) this;
        }

        public Criteria andPayModeIsNull() {
            addCriterion("pay_mode is null");
            return (Criteria) this;
        }

        public Criteria andPayModeIsNotNull() {
            addCriterion("pay_mode is not null");
            return (Criteria) this;
        }

        public Criteria andPayModeEqualTo(Integer value) {
            addCriterion("pay_mode =", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotEqualTo(Integer value) {
            addCriterion("pay_mode <>", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeGreaterThan(Integer value) {
            addCriterion("pay_mode >", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_mode >=", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeLessThan(Integer value) {
            addCriterion("pay_mode <", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_mode <=", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeIn(List<Integer> values) {
            addCriterion("pay_mode in", values, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotIn(List<Integer> values) {
            addCriterion("pay_mode not in", values, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeBetween(Integer value1, Integer value2) {
            addCriterion("pay_mode between", value1, value2, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_mode not between", value1, value2, "payMode");
            return (Criteria) this;
        }

        public Criteria andTransferFeeIsNull() {
            addCriterion("transfer_fee is null");
            return (Criteria) this;
        }

        public Criteria andTransferFeeIsNotNull() {
            addCriterion("transfer_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTransferFeeEqualTo(Integer value) {
            addCriterion("transfer_fee =", value, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeNotEqualTo(Integer value) {
            addCriterion("transfer_fee <>", value, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeGreaterThan(Integer value) {
            addCriterion("transfer_fee >", value, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("transfer_fee >=", value, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeLessThan(Integer value) {
            addCriterion("transfer_fee <", value, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeLessThanOrEqualTo(Integer value) {
            addCriterion("transfer_fee <=", value, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeIn(List<Integer> values) {
            addCriterion("transfer_fee in", values, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeNotIn(List<Integer> values) {
            addCriterion("transfer_fee not in", values, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeBetween(Integer value1, Integer value2) {
            addCriterion("transfer_fee between", value1, value2, "transferFee");
            return (Criteria) this;
        }

        public Criteria andTransferFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("transfer_fee not between", value1, value2, "transferFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeIsNull() {
            addCriterion("carry_goods_fee is null");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeIsNotNull() {
            addCriterion("carry_goods_fee is not null");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeEqualTo(Integer value) {
            addCriterion("carry_goods_fee =", value, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeNotEqualTo(Integer value) {
            addCriterion("carry_goods_fee <>", value, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeGreaterThan(Integer value) {
            addCriterion("carry_goods_fee >", value, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("carry_goods_fee >=", value, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeLessThan(Integer value) {
            addCriterion("carry_goods_fee <", value, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeLessThanOrEqualTo(Integer value) {
            addCriterion("carry_goods_fee <=", value, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeIn(List<Integer> values) {
            addCriterion("carry_goods_fee in", values, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeNotIn(List<Integer> values) {
            addCriterion("carry_goods_fee not in", values, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeBetween(Integer value1, Integer value2) {
            addCriterion("carry_goods_fee between", value1, value2, "carryGoodsFee");
            return (Criteria) this;
        }

        public Criteria andCarryGoodsFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("carry_goods_fee not between", value1, value2, "carryGoodsFee");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pay
     *
     * @mbg.generated do_not_delete_during_merge Tue Mar 08 14:25:09 CST 2022
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pay
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}