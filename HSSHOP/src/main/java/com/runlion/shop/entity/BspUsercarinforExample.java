package com.runlion.shop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BspUsercarinforExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public BspUsercarinforExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
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
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
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

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andCarnumIsNull() {
            addCriterion("carnum is null");
            return (Criteria) this;
        }

        public Criteria andCarnumIsNotNull() {
            addCriterion("carnum is not null");
            return (Criteria) this;
        }

        public Criteria andCarnumEqualTo(String value) {
            addCriterion("carnum =", value, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumNotEqualTo(String value) {
            addCriterion("carnum <>", value, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumGreaterThan(String value) {
            addCriterion("carnum >", value, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumGreaterThanOrEqualTo(String value) {
            addCriterion("carnum >=", value, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumLessThan(String value) {
            addCriterion("carnum <", value, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumLessThanOrEqualTo(String value) {
            addCriterion("carnum <=", value, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumLike(String value) {
            addCriterion("carnum like", value, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumNotLike(String value) {
            addCriterion("carnum not like", value, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumIn(List<String> values) {
            addCriterion("carnum in", values, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumNotIn(List<String> values) {
            addCriterion("carnum not in", values, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumBetween(String value1, String value2) {
            addCriterion("carnum between", value1, value2, "carnum");
            return (Criteria) this;
        }

        public Criteria andCarnumNotBetween(String value1, String value2) {
            addCriterion("carnum not between", value1, value2, "carnum");
            return (Criteria) this;
        }

        public Criteria andMotornumIsNull() {
            addCriterion("motornum is null");
            return (Criteria) this;
        }

        public Criteria andMotornumIsNotNull() {
            addCriterion("motornum is not null");
            return (Criteria) this;
        }

        public Criteria andMotornumEqualTo(String value) {
            addCriterion("motornum =", value, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumNotEqualTo(String value) {
            addCriterion("motornum <>", value, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumGreaterThan(String value) {
            addCriterion("motornum >", value, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumGreaterThanOrEqualTo(String value) {
            addCriterion("motornum >=", value, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumLessThan(String value) {
            addCriterion("motornum <", value, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumLessThanOrEqualTo(String value) {
            addCriterion("motornum <=", value, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumLike(String value) {
            addCriterion("motornum like", value, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumNotLike(String value) {
            addCriterion("motornum not like", value, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumIn(List<String> values) {
            addCriterion("motornum in", values, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumNotIn(List<String> values) {
            addCriterion("motornum not in", values, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumBetween(String value1, String value2) {
            addCriterion("motornum between", value1, value2, "motornum");
            return (Criteria) this;
        }

        public Criteria andMotornumNotBetween(String value1, String value2) {
            addCriterion("motornum not between", value1, value2, "motornum");
            return (Criteria) this;
        }

        public Criteria andMobilenumIsNull() {
            addCriterion("mobilenum is null");
            return (Criteria) this;
        }

        public Criteria andMobilenumIsNotNull() {
            addCriterion("mobilenum is not null");
            return (Criteria) this;
        }

        public Criteria andMobilenumEqualTo(String value) {
            addCriterion("mobilenum =", value, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumNotEqualTo(String value) {
            addCriterion("mobilenum <>", value, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumGreaterThan(String value) {
            addCriterion("mobilenum >", value, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumGreaterThanOrEqualTo(String value) {
            addCriterion("mobilenum >=", value, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumLessThan(String value) {
            addCriterion("mobilenum <", value, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumLessThanOrEqualTo(String value) {
            addCriterion("mobilenum <=", value, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumLike(String value) {
            addCriterion("mobilenum like", value, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumNotLike(String value) {
            addCriterion("mobilenum not like", value, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumIn(List<String> values) {
            addCriterion("mobilenum in", values, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumNotIn(List<String> values) {
            addCriterion("mobilenum not in", values, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumBetween(String value1, String value2) {
            addCriterion("mobilenum between", value1, value2, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andMobilenumNotBetween(String value1, String value2) {
            addCriterion("mobilenum not between", value1, value2, "mobilenum");
            return (Criteria) this;
        }

        public Criteria andTelnumIsNull() {
            addCriterion("telnum is null");
            return (Criteria) this;
        }

        public Criteria andTelnumIsNotNull() {
            addCriterion("telnum is not null");
            return (Criteria) this;
        }

        public Criteria andTelnumEqualTo(String value) {
            addCriterion("telnum =", value, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumNotEqualTo(String value) {
            addCriterion("telnum <>", value, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumGreaterThan(String value) {
            addCriterion("telnum >", value, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumGreaterThanOrEqualTo(String value) {
            addCriterion("telnum >=", value, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumLessThan(String value) {
            addCriterion("telnum <", value, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumLessThanOrEqualTo(String value) {
            addCriterion("telnum <=", value, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumLike(String value) {
            addCriterion("telnum like", value, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumNotLike(String value) {
            addCriterion("telnum not like", value, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumIn(List<String> values) {
            addCriterion("telnum in", values, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumNotIn(List<String> values) {
            addCriterion("telnum not in", values, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumBetween(String value1, String value2) {
            addCriterion("telnum between", value1, value2, "telnum");
            return (Criteria) this;
        }

        public Criteria andTelnumNotBetween(String value1, String value2) {
            addCriterion("telnum not between", value1, value2, "telnum");
            return (Criteria) this;
        }

        public Criteria andZipcodeIsNull() {
            addCriterion("zipcode is null");
            return (Criteria) this;
        }

        public Criteria andZipcodeIsNotNull() {
            addCriterion("zipcode is not null");
            return (Criteria) this;
        }

        public Criteria andZipcodeEqualTo(String value) {
            addCriterion("zipcode =", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotEqualTo(String value) {
            addCriterion("zipcode <>", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeGreaterThan(String value) {
            addCriterion("zipcode >", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeGreaterThanOrEqualTo(String value) {
            addCriterion("zipcode >=", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLessThan(String value) {
            addCriterion("zipcode <", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLessThanOrEqualTo(String value) {
            addCriterion("zipcode <=", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLike(String value) {
            addCriterion("zipcode like", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotLike(String value) {
            addCriterion("zipcode not like", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeIn(List<String> values) {
            addCriterion("zipcode in", values, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotIn(List<String> values) {
            addCriterion("zipcode not in", values, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeBetween(String value1, String value2) {
            addCriterion("zipcode between", value1, value2, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotBetween(String value1, String value2) {
            addCriterion("zipcode not between", value1, value2, "zipcode");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andLastusetimeIsNull() {
            addCriterion("lastusetime is null");
            return (Criteria) this;
        }

        public Criteria andLastusetimeIsNotNull() {
            addCriterion("lastusetime is not null");
            return (Criteria) this;
        }

        public Criteria andLastusetimeEqualTo(Date value) {
            addCriterion("lastusetime =", value, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeNotEqualTo(Date value) {
            addCriterion("lastusetime <>", value, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeGreaterThan(Date value) {
            addCriterion("lastusetime >", value, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastusetime >=", value, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeLessThan(Date value) {
            addCriterion("lastusetime <", value, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeLessThanOrEqualTo(Date value) {
            addCriterion("lastusetime <=", value, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeIn(List<Date> values) {
            addCriterion("lastusetime in", values, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeNotIn(List<Date> values) {
            addCriterion("lastusetime not in", values, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeBetween(Date value1, Date value2) {
            addCriterion("lastusetime between", value1, value2, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastusetimeNotBetween(Date value1, Date value2) {
            addCriterion("lastusetime not between", value1, value2, "lastusetime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeIsNull() {
            addCriterion("lastuptime is null");
            return (Criteria) this;
        }

        public Criteria andLastuptimeIsNotNull() {
            addCriterion("lastuptime is not null");
            return (Criteria) this;
        }

        public Criteria andLastuptimeEqualTo(Date value) {
            addCriterion("lastuptime =", value, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeNotEqualTo(Date value) {
            addCriterion("lastuptime <>", value, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeGreaterThan(Date value) {
            addCriterion("lastuptime >", value, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastuptime >=", value, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeLessThan(Date value) {
            addCriterion("lastuptime <", value, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeLessThanOrEqualTo(Date value) {
            addCriterion("lastuptime <=", value, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeIn(List<Date> values) {
            addCriterion("lastuptime in", values, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeNotIn(List<Date> values) {
            addCriterion("lastuptime not in", values, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeBetween(Date value1, Date value2) {
            addCriterion("lastuptime between", value1, value2, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andLastuptimeNotBetween(Date value1, Date value2) {
            addCriterion("lastuptime not between", value1, value2, "lastuptime");
            return (Criteria) this;
        }

        public Criteria andUserroleidIsNull() {
            addCriterion("userroleid is null");
            return (Criteria) this;
        }

        public Criteria andUserroleidIsNotNull() {
            addCriterion("userroleid is not null");
            return (Criteria) this;
        }

        public Criteria andUserroleidEqualTo(Integer value) {
            addCriterion("userroleid =", value, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidNotEqualTo(Integer value) {
            addCriterion("userroleid <>", value, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidGreaterThan(Integer value) {
            addCriterion("userroleid >", value, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("userroleid >=", value, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidLessThan(Integer value) {
            addCriterion("userroleid <", value, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidLessThanOrEqualTo(Integer value) {
            addCriterion("userroleid <=", value, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidIn(List<Integer> values) {
            addCriterion("userroleid in", values, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidNotIn(List<Integer> values) {
            addCriterion("userroleid not in", values, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidBetween(Integer value1, Integer value2) {
            addCriterion("userroleid between", value1, value2, "userroleid");
            return (Criteria) this;
        }

        public Criteria andUserroleidNotBetween(Integer value1, Integer value2) {
            addCriterion("userroleid not between", value1, value2, "userroleid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated do_not_delete_during_merge Wed Sep 09 15:07:36 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_usercarinfor
     *
     * @mbggenerated Wed Sep 09 15:07:36 CST 2015
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