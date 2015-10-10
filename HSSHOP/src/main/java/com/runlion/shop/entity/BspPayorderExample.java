package com.runlion.shop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BspPayorderExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public BspPayorderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
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
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
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

        public Criteria andOidIsNull() {
            addCriterion("oid is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("oid is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(Integer value) {
            addCriterion("oid =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(Integer value) {
            addCriterion("oid <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(Integer value) {
            addCriterion("oid >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(Integer value) {
            addCriterion("oid >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(Integer value) {
            addCriterion("oid <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(Integer value) {
            addCriterion("oid <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<Integer> values) {
            addCriterion("oid in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<Integer> values) {
            addCriterion("oid not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(Integer value1, Integer value2) {
            addCriterion("oid between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(Integer value1, Integer value2) {
            addCriterion("oid not between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNull() {
            addCriterion("paytype is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("paytype is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(Integer value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(Integer value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(Integer value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(Integer value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(Integer value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<Integer> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<Integer> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(Integer value1, Integer value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(Integer value1, Integer value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypenameIsNull() {
            addCriterion("paytypeName is null");
            return (Criteria) this;
        }

        public Criteria andPaytypenameIsNotNull() {
            addCriterion("paytypeName is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypenameEqualTo(String value) {
            addCriterion("paytypeName =", value, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameNotEqualTo(String value) {
            addCriterion("paytypeName <>", value, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameGreaterThan(String value) {
            addCriterion("paytypeName >", value, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameGreaterThanOrEqualTo(String value) {
            addCriterion("paytypeName >=", value, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameLessThan(String value) {
            addCriterion("paytypeName <", value, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameLessThanOrEqualTo(String value) {
            addCriterion("paytypeName <=", value, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameLike(String value) {
            addCriterion("paytypeName like", value, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameNotLike(String value) {
            addCriterion("paytypeName not like", value, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameIn(List<String> values) {
            addCriterion("paytypeName in", values, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameNotIn(List<String> values) {
            addCriterion("paytypeName not in", values, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameBetween(String value1, String value2) {
            addCriterion("paytypeName between", value1, value2, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPaytypenameNotBetween(String value1, String value2) {
            addCriterion("paytypeName not between", value1, value2, "paytypename");
            return (Criteria) this;
        }

        public Criteria andPayamountIsNull() {
            addCriterion("payamount is null");
            return (Criteria) this;
        }

        public Criteria andPayamountIsNotNull() {
            addCriterion("payamount is not null");
            return (Criteria) this;
        }

        public Criteria andPayamountEqualTo(BigDecimal value) {
            addCriterion("payamount =", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotEqualTo(BigDecimal value) {
            addCriterion("payamount <>", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountGreaterThan(BigDecimal value) {
            addCriterion("payamount >", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("payamount >=", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountLessThan(BigDecimal value) {
            addCriterion("payamount <", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("payamount <=", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountIn(List<BigDecimal> values) {
            addCriterion("payamount in", values, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotIn(List<BigDecimal> values) {
            addCriterion("payamount not in", values, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payamount between", value1, value2, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payamount not between", value1, value2, "payamount");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNull() {
            addCriterion("paytime is null");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNotNull() {
            addCriterion("paytime is not null");
            return (Criteria) this;
        }

        public Criteria andPaytimeEqualTo(Date value) {
            addCriterion("paytime =", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotEqualTo(Date value) {
            addCriterion("paytime <>", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThan(Date value) {
            addCriterion("paytime >", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("paytime >=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThan(Date value) {
            addCriterion("paytime <", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThanOrEqualTo(Date value) {
            addCriterion("paytime <=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIn(List<Date> values) {
            addCriterion("paytime in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotIn(List<Date> values) {
            addCriterion("paytime not in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeBetween(Date value1, Date value2) {
            addCriterion("paytime between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotBetween(Date value1, Date value2) {
            addCriterion("paytime not between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOsnIsNull() {
            addCriterion("osn is null");
            return (Criteria) this;
        }

        public Criteria andOsnIsNotNull() {
            addCriterion("osn is not null");
            return (Criteria) this;
        }

        public Criteria andOsnEqualTo(String value) {
            addCriterion("osn =", value, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnNotEqualTo(String value) {
            addCriterion("osn <>", value, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnGreaterThan(String value) {
            addCriterion("osn >", value, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnGreaterThanOrEqualTo(String value) {
            addCriterion("osn >=", value, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnLessThan(String value) {
            addCriterion("osn <", value, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnLessThanOrEqualTo(String value) {
            addCriterion("osn <=", value, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnLike(String value) {
            addCriterion("osn like", value, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnNotLike(String value) {
            addCriterion("osn not like", value, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnIn(List<String> values) {
            addCriterion("osn in", values, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnNotIn(List<String> values) {
            addCriterion("osn not in", values, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnBetween(String value1, String value2) {
            addCriterion("osn between", value1, value2, "osn");
            return (Criteria) this;
        }

        public Criteria andOsnNotBetween(String value1, String value2) {
            addCriterion("osn not between", value1, value2, "osn");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andNccompanyIsNull() {
            addCriterion("nccompany is null");
            return (Criteria) this;
        }

        public Criteria andNccompanyIsNotNull() {
            addCriterion("nccompany is not null");
            return (Criteria) this;
        }

        public Criteria andNccompanyEqualTo(String value) {
            addCriterion("nccompany =", value, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyNotEqualTo(String value) {
            addCriterion("nccompany <>", value, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyGreaterThan(String value) {
            addCriterion("nccompany >", value, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyGreaterThanOrEqualTo(String value) {
            addCriterion("nccompany >=", value, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyLessThan(String value) {
            addCriterion("nccompany <", value, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyLessThanOrEqualTo(String value) {
            addCriterion("nccompany <=", value, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyLike(String value) {
            addCriterion("nccompany like", value, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyNotLike(String value) {
            addCriterion("nccompany not like", value, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyIn(List<String> values) {
            addCriterion("nccompany in", values, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyNotIn(List<String> values) {
            addCriterion("nccompany not in", values, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyBetween(String value1, String value2) {
            addCriterion("nccompany between", value1, value2, "nccompany");
            return (Criteria) this;
        }

        public Criteria andNccompanyNotBetween(String value1, String value2) {
            addCriterion("nccompany not between", value1, value2, "nccompany");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_payOrder
     *
     * @mbggenerated do_not_delete_during_merge Wed Sep 23 10:31:05 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_payOrder
     *
     * @mbggenerated Wed Sep 23 10:31:05 CST 2015
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