package com.runlion.shop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BspNcenterinforExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public BspNcenterinforExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
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
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
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

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(String value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(String value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(String value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(String value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(String value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(String value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLike(String value) {
            addCriterion("eid like", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotLike(String value) {
            addCriterion("eid not like", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<String> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<String> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(String value1, String value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(String value1, String value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEnameIsNull() {
            addCriterion("ename is null");
            return (Criteria) this;
        }

        public Criteria andEnameIsNotNull() {
            addCriterion("ename is not null");
            return (Criteria) this;
        }

        public Criteria andEnameEqualTo(String value) {
            addCriterion("ename =", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotEqualTo(String value) {
            addCriterion("ename <>", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameGreaterThan(String value) {
            addCriterion("ename >", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameGreaterThanOrEqualTo(String value) {
            addCriterion("ename >=", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLessThan(String value) {
            addCriterion("ename <", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLessThanOrEqualTo(String value) {
            addCriterion("ename <=", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLike(String value) {
            addCriterion("ename like", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotLike(String value) {
            addCriterion("ename not like", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameIn(List<String> values) {
            addCriterion("ename in", values, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotIn(List<String> values) {
            addCriterion("ename not in", values, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameBetween(String value1, String value2) {
            addCriterion("ename between", value1, value2, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotBetween(String value1, String value2) {
            addCriterion("ename not between", value1, value2, "ename");
            return (Criteria) this;
        }

        public Criteria andPkcorpIsNull() {
            addCriterion("pkcorp is null");
            return (Criteria) this;
        }

        public Criteria andPkcorpIsNotNull() {
            addCriterion("pkcorp is not null");
            return (Criteria) this;
        }

        public Criteria andPkcorpEqualTo(String value) {
            addCriterion("pkcorp =", value, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpNotEqualTo(String value) {
            addCriterion("pkcorp <>", value, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpGreaterThan(String value) {
            addCriterion("pkcorp >", value, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpGreaterThanOrEqualTo(String value) {
            addCriterion("pkcorp >=", value, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpLessThan(String value) {
            addCriterion("pkcorp <", value, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpLessThanOrEqualTo(String value) {
            addCriterion("pkcorp <=", value, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpLike(String value) {
            addCriterion("pkcorp like", value, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpNotLike(String value) {
            addCriterion("pkcorp not like", value, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpIn(List<String> values) {
            addCriterion("pkcorp in", values, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpNotIn(List<String> values) {
            addCriterion("pkcorp not in", values, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpBetween(String value1, String value2) {
            addCriterion("pkcorp between", value1, value2, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andPkcorpNotBetween(String value1, String value2) {
            addCriterion("pkcorp not between", value1, value2, "pkcorp");
            return (Criteria) this;
        }

        public Criteria andCorpnameIsNull() {
            addCriterion("corpname is null");
            return (Criteria) this;
        }

        public Criteria andCorpnameIsNotNull() {
            addCriterion("corpname is not null");
            return (Criteria) this;
        }

        public Criteria andCorpnameEqualTo(String value) {
            addCriterion("corpname =", value, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameNotEqualTo(String value) {
            addCriterion("corpname <>", value, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameGreaterThan(String value) {
            addCriterion("corpname >", value, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameGreaterThanOrEqualTo(String value) {
            addCriterion("corpname >=", value, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameLessThan(String value) {
            addCriterion("corpname <", value, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameLessThanOrEqualTo(String value) {
            addCriterion("corpname <=", value, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameLike(String value) {
            addCriterion("corpname like", value, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameNotLike(String value) {
            addCriterion("corpname not like", value, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameIn(List<String> values) {
            addCriterion("corpname in", values, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameNotIn(List<String> values) {
            addCriterion("corpname not in", values, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameBetween(String value1, String value2) {
            addCriterion("corpname between", value1, value2, "corpname");
            return (Criteria) this;
        }

        public Criteria andCorpnameNotBetween(String value1, String value2) {
            addCriterion("corpname not between", value1, value2, "corpname");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("pid like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("pid not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPnameIsNull() {
            addCriterion("pname is null");
            return (Criteria) this;
        }

        public Criteria andPnameIsNotNull() {
            addCriterion("pname is not null");
            return (Criteria) this;
        }

        public Criteria andPnameEqualTo(String value) {
            addCriterion("pname =", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotEqualTo(String value) {
            addCriterion("pname <>", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameGreaterThan(String value) {
            addCriterion("pname >", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameGreaterThanOrEqualTo(String value) {
            addCriterion("pname >=", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLessThan(String value) {
            addCriterion("pname <", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLessThanOrEqualTo(String value) {
            addCriterion("pname <=", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLike(String value) {
            addCriterion("pname like", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotLike(String value) {
            addCriterion("pname not like", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameIn(List<String> values) {
            addCriterion("pname in", values, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotIn(List<String> values) {
            addCriterion("pname not in", values, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameBetween(String value1, String value2) {
            addCriterion("pname between", value1, value2, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotBetween(String value1, String value2) {
            addCriterion("pname not between", value1, value2, "pname");
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

        public Criteria andPriceEqualTo(String value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(String value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(String value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(String value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(String value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(String value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLike(String value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotLike(String value) {
            addCriterion("price not like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<String> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<String> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(String value1, String value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(String value1, String value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated do_not_delete_during_merge Tue Sep 01 14:19:10 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_ncenterinfor
     *
     * @mbggenerated Tue Sep 01 14:19:10 CST 2015
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