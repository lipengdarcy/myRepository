package com.runlion.shop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BspProductsregionspriceLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public BspProductsregionspriceLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
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
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPriceidIsNull() {
            addCriterion("priceId is null");
            return (Criteria) this;
        }

        public Criteria andPriceidIsNotNull() {
            addCriterion("priceId is not null");
            return (Criteria) this;
        }

        public Criteria andPriceidEqualTo(Integer value) {
            addCriterion("priceId =", value, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidNotEqualTo(Integer value) {
            addCriterion("priceId <>", value, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidGreaterThan(Integer value) {
            addCriterion("priceId >", value, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("priceId >=", value, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidLessThan(Integer value) {
            addCriterion("priceId <", value, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidLessThanOrEqualTo(Integer value) {
            addCriterion("priceId <=", value, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidIn(List<Integer> values) {
            addCriterion("priceId in", values, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidNotIn(List<Integer> values) {
            addCriterion("priceId not in", values, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidBetween(Integer value1, Integer value2) {
            addCriterion("priceId between", value1, value2, "priceid");
            return (Criteria) this;
        }

        public Criteria andPriceidNotBetween(Integer value1, Integer value2) {
            addCriterion("priceId not between", value1, value2, "priceid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidIsNull() {
            addCriterion("productregionsId is null");
            return (Criteria) this;
        }

        public Criteria andProductregionsidIsNotNull() {
            addCriterion("productregionsId is not null");
            return (Criteria) this;
        }

        public Criteria andProductregionsidEqualTo(Integer value) {
            addCriterion("productregionsId =", value, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidNotEqualTo(Integer value) {
            addCriterion("productregionsId <>", value, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidGreaterThan(Integer value) {
            addCriterion("productregionsId >", value, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("productregionsId >=", value, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidLessThan(Integer value) {
            addCriterion("productregionsId <", value, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidLessThanOrEqualTo(Integer value) {
            addCriterion("productregionsId <=", value, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidIn(List<Integer> values) {
            addCriterion("productregionsId in", values, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidNotIn(List<Integer> values) {
            addCriterion("productregionsId not in", values, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidBetween(Integer value1, Integer value2) {
            addCriterion("productregionsId between", value1, value2, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andProductregionsidNotBetween(Integer value1, Integer value2) {
            addCriterion("productregionsId not between", value1, value2, "productregionsid");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPricetypeIsNull() {
            addCriterion("priceType is null");
            return (Criteria) this;
        }

        public Criteria andPricetypeIsNotNull() {
            addCriterion("priceType is not null");
            return (Criteria) this;
        }

        public Criteria andPricetypeEqualTo(String value) {
            addCriterion("priceType =", value, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeNotEqualTo(String value) {
            addCriterion("priceType <>", value, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeGreaterThan(String value) {
            addCriterion("priceType >", value, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeGreaterThanOrEqualTo(String value) {
            addCriterion("priceType >=", value, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeLessThan(String value) {
            addCriterion("priceType <", value, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeLessThanOrEqualTo(String value) {
            addCriterion("priceType <=", value, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeLike(String value) {
            addCriterion("priceType like", value, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeNotLike(String value) {
            addCriterion("priceType not like", value, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeIn(List<String> values) {
            addCriterion("priceType in", values, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeNotIn(List<String> values) {
            addCriterion("priceType not in", values, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeBetween(String value1, String value2) {
            addCriterion("priceType between", value1, value2, "pricetype");
            return (Criteria) this;
        }

        public Criteria andPricetypeNotBetween(String value1, String value2) {
            addCriterion("priceType not between", value1, value2, "pricetype");
            return (Criteria) this;
        }

        public Criteria andBuyminquanIsNull() {
            addCriterion("buyminquan is null");
            return (Criteria) this;
        }

        public Criteria andBuyminquanIsNotNull() {
            addCriterion("buyminquan is not null");
            return (Criteria) this;
        }

        public Criteria andBuyminquanEqualTo(Double value) {
            addCriterion("buyminquan =", value, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanNotEqualTo(Double value) {
            addCriterion("buyminquan <>", value, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanGreaterThan(Double value) {
            addCriterion("buyminquan >", value, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanGreaterThanOrEqualTo(Double value) {
            addCriterion("buyminquan >=", value, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanLessThan(Double value) {
            addCriterion("buyminquan <", value, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanLessThanOrEqualTo(Double value) {
            addCriterion("buyminquan <=", value, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanIn(List<Double> values) {
            addCriterion("buyminquan in", values, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanNotIn(List<Double> values) {
            addCriterion("buyminquan not in", values, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanBetween(Double value1, Double value2) {
            addCriterion("buyminquan between", value1, value2, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andBuyminquanNotBetween(Double value1, Double value2) {
            addCriterion("buyminquan not between", value1, value2, "buyminquan");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andEdittimeIsNull() {
            addCriterion("edittime is null");
            return (Criteria) this;
        }

        public Criteria andEdittimeIsNotNull() {
            addCriterion("edittime is not null");
            return (Criteria) this;
        }

        public Criteria andEdittimeEqualTo(Date value) {
            addCriterion("edittime =", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeNotEqualTo(Date value) {
            addCriterion("edittime <>", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeGreaterThan(Date value) {
            addCriterion("edittime >", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeGreaterThanOrEqualTo(Date value) {
            addCriterion("edittime >=", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeLessThan(Date value) {
            addCriterion("edittime <", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeLessThanOrEqualTo(Date value) {
            addCriterion("edittime <=", value, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeIn(List<Date> values) {
            addCriterion("edittime in", values, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeNotIn(List<Date> values) {
            addCriterion("edittime not in", values, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeBetween(Date value1, Date value2) {
            addCriterion("edittime between", value1, value2, "edittime");
            return (Criteria) this;
        }

        public Criteria andEdittimeNotBetween(Date value1, Date value2) {
            addCriterion("edittime not between", value1, value2, "edittime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeIsNull() {
            addCriterion("planeffecttime is null");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeIsNotNull() {
            addCriterion("planeffecttime is not null");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeEqualTo(Date value) {
            addCriterion("planeffecttime =", value, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeNotEqualTo(Date value) {
            addCriterion("planeffecttime <>", value, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeGreaterThan(Date value) {
            addCriterion("planeffecttime >", value, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("planeffecttime >=", value, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeLessThan(Date value) {
            addCriterion("planeffecttime <", value, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeLessThanOrEqualTo(Date value) {
            addCriterion("planeffecttime <=", value, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeIn(List<Date> values) {
            addCriterion("planeffecttime in", values, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeNotIn(List<Date> values) {
            addCriterion("planeffecttime not in", values, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeBetween(Date value1, Date value2) {
            addCriterion("planeffecttime between", value1, value2, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andPlaneffecttimeNotBetween(Date value1, Date value2) {
            addCriterion("planeffecttime not between", value1, value2, "planeffecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeIsNull() {
            addCriterion("effecttime is null");
            return (Criteria) this;
        }

        public Criteria andEffecttimeIsNotNull() {
            addCriterion("effecttime is not null");
            return (Criteria) this;
        }

        public Criteria andEffecttimeEqualTo(Date value) {
            addCriterion("effecttime =", value, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeNotEqualTo(Date value) {
            addCriterion("effecttime <>", value, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeGreaterThan(Date value) {
            addCriterion("effecttime >", value, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("effecttime >=", value, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeLessThan(Date value) {
            addCriterion("effecttime <", value, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeLessThanOrEqualTo(Date value) {
            addCriterion("effecttime <=", value, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeIn(List<Date> values) {
            addCriterion("effecttime in", values, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeNotIn(List<Date> values) {
            addCriterion("effecttime not in", values, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeBetween(Date value1, Date value2) {
            addCriterion("effecttime between", value1, value2, "effecttime");
            return (Criteria) this;
        }

        public Criteria andEffecttimeNotBetween(Date value1, Date value2) {
            addCriterion("effecttime not between", value1, value2, "effecttime");
            return (Criteria) this;
        }

        public Criteria andIseffectIsNull() {
            addCriterion("iseffect is null");
            return (Criteria) this;
        }

        public Criteria andIseffectIsNotNull() {
            addCriterion("iseffect is not null");
            return (Criteria) this;
        }

        public Criteria andIseffectEqualTo(String value) {
            addCriterion("iseffect =", value, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectNotEqualTo(String value) {
            addCriterion("iseffect <>", value, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectGreaterThan(String value) {
            addCriterion("iseffect >", value, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectGreaterThanOrEqualTo(String value) {
            addCriterion("iseffect >=", value, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectLessThan(String value) {
            addCriterion("iseffect <", value, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectLessThanOrEqualTo(String value) {
            addCriterion("iseffect <=", value, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectLike(String value) {
            addCriterion("iseffect like", value, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectNotLike(String value) {
            addCriterion("iseffect not like", value, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectIn(List<String> values) {
            addCriterion("iseffect in", values, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectNotIn(List<String> values) {
            addCriterion("iseffect not in", values, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectBetween(String value1, String value2) {
            addCriterion("iseffect between", value1, value2, "iseffect");
            return (Criteria) this;
        }

        public Criteria andIseffectNotBetween(String value1, String value2) {
            addCriterion("iseffect not between", value1, value2, "iseffect");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated do_not_delete_during_merge Thu Aug 06 11:23:34 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_productsregionsprice_log
     *
     * @mbggenerated Thu Aug 06 11:23:34 CST 2015
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