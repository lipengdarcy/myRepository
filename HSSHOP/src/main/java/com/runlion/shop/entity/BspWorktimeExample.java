package com.runlion.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class BspWorktimeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public BspWorktimeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
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
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
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

        public Criteria andWtidIsNull() {
            addCriterion("wtid is null");
            return (Criteria) this;
        }

        public Criteria andWtidIsNotNull() {
            addCriterion("wtid is not null");
            return (Criteria) this;
        }

        public Criteria andWtidEqualTo(Integer value) {
            addCriterion("wtid =", value, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidNotEqualTo(Integer value) {
            addCriterion("wtid <>", value, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidGreaterThan(Integer value) {
            addCriterion("wtid >", value, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidGreaterThanOrEqualTo(Integer value) {
            addCriterion("wtid >=", value, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidLessThan(Integer value) {
            addCriterion("wtid <", value, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidLessThanOrEqualTo(Integer value) {
            addCriterion("wtid <=", value, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidIn(List<Integer> values) {
            addCriterion("wtid in", values, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidNotIn(List<Integer> values) {
            addCriterion("wtid not in", values, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidBetween(Integer value1, Integer value2) {
            addCriterion("wtid between", value1, value2, "wtid");
            return (Criteria) this;
        }

        public Criteria andWtidNotBetween(Integer value1, Integer value2) {
            addCriterion("wtid not between", value1, value2, "wtid");
            return (Criteria) this;
        }

        public Criteria andWttypeIsNull() {
            addCriterion("wttype is null");
            return (Criteria) this;
        }

        public Criteria andWttypeIsNotNull() {
            addCriterion("wttype is not null");
            return (Criteria) this;
        }

        public Criteria andWttypeEqualTo(Short value) {
            addCriterion("wttype =", value, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeNotEqualTo(Short value) {
            addCriterion("wttype <>", value, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeGreaterThan(Short value) {
            addCriterion("wttype >", value, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeGreaterThanOrEqualTo(Short value) {
            addCriterion("wttype >=", value, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeLessThan(Short value) {
            addCriterion("wttype <", value, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeLessThanOrEqualTo(Short value) {
            addCriterion("wttype <=", value, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeIn(List<Short> values) {
            addCriterion("wttype in", values, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeNotIn(List<Short> values) {
            addCriterion("wttype not in", values, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeBetween(Short value1, Short value2) {
            addCriterion("wttype between", value1, value2, "wttype");
            return (Criteria) this;
        }

        public Criteria andWttypeNotBetween(Short value1, Short value2) {
            addCriterion("wttype not between", value1, value2, "wttype");
            return (Criteria) this;
        }

        public Criteria andWtbeginIsNull() {
            addCriterion("wtbegin is null");
            return (Criteria) this;
        }

        public Criteria andWtbeginIsNotNull() {
            addCriterion("wtbegin is not null");
            return (Criteria) this;
        }

        public Criteria andWtbeginEqualTo(String value) {
            addCriterion("wtbegin =", value, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginNotEqualTo(String value) {
            addCriterion("wtbegin <>", value, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginGreaterThan(String value) {
            addCriterion("wtbegin >", value, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginGreaterThanOrEqualTo(String value) {
            addCriterion("wtbegin >=", value, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginLessThan(String value) {
            addCriterion("wtbegin <", value, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginLessThanOrEqualTo(String value) {
            addCriterion("wtbegin <=", value, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginLike(String value) {
            addCriterion("wtbegin like", value, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginNotLike(String value) {
            addCriterion("wtbegin not like", value, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginIn(List<String> values) {
            addCriterion("wtbegin in", values, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginNotIn(List<String> values) {
            addCriterion("wtbegin not in", values, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginBetween(String value1, String value2) {
            addCriterion("wtbegin between", value1, value2, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtbeginNotBetween(String value1, String value2) {
            addCriterion("wtbegin not between", value1, value2, "wtbegin");
            return (Criteria) this;
        }

        public Criteria andWtendIsNull() {
            addCriterion("wtend is null");
            return (Criteria) this;
        }

        public Criteria andWtendIsNotNull() {
            addCriterion("wtend is not null");
            return (Criteria) this;
        }

        public Criteria andWtendEqualTo(String value) {
            addCriterion("wtend =", value, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendNotEqualTo(String value) {
            addCriterion("wtend <>", value, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendGreaterThan(String value) {
            addCriterion("wtend >", value, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendGreaterThanOrEqualTo(String value) {
            addCriterion("wtend >=", value, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendLessThan(String value) {
            addCriterion("wtend <", value, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendLessThanOrEqualTo(String value) {
            addCriterion("wtend <=", value, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendLike(String value) {
            addCriterion("wtend like", value, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendNotLike(String value) {
            addCriterion("wtend not like", value, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendIn(List<String> values) {
            addCriterion("wtend in", values, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendNotIn(List<String> values) {
            addCriterion("wtend not in", values, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendBetween(String value1, String value2) {
            addCriterion("wtend between", value1, value2, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtendNotBetween(String value1, String value2) {
            addCriterion("wtend not between", value1, value2, "wtend");
            return (Criteria) this;
        }

        public Criteria andWtremarksIsNull() {
            addCriterion("wtremarks is null");
            return (Criteria) this;
        }

        public Criteria andWtremarksIsNotNull() {
            addCriterion("wtremarks is not null");
            return (Criteria) this;
        }

        public Criteria andWtremarksEqualTo(String value) {
            addCriterion("wtremarks =", value, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksNotEqualTo(String value) {
            addCriterion("wtremarks <>", value, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksGreaterThan(String value) {
            addCriterion("wtremarks >", value, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksGreaterThanOrEqualTo(String value) {
            addCriterion("wtremarks >=", value, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksLessThan(String value) {
            addCriterion("wtremarks <", value, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksLessThanOrEqualTo(String value) {
            addCriterion("wtremarks <=", value, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksLike(String value) {
            addCriterion("wtremarks like", value, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksNotLike(String value) {
            addCriterion("wtremarks not like", value, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksIn(List<String> values) {
            addCriterion("wtremarks in", values, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksNotIn(List<String> values) {
            addCriterion("wtremarks not in", values, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksBetween(String value1, String value2) {
            addCriterion("wtremarks between", value1, value2, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtremarksNotBetween(String value1, String value2) {
            addCriterion("wtremarks not between", value1, value2, "wtremarks");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidIsNull() {
            addCriterion("wtpickpointid is null");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidIsNotNull() {
            addCriterion("wtpickpointid is not null");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidEqualTo(Integer value) {
            addCriterion("wtpickpointid =", value, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidNotEqualTo(Integer value) {
            addCriterion("wtpickpointid <>", value, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidGreaterThan(Integer value) {
            addCriterion("wtpickpointid >", value, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidGreaterThanOrEqualTo(Integer value) {
            addCriterion("wtpickpointid >=", value, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidLessThan(Integer value) {
            addCriterion("wtpickpointid <", value, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidLessThanOrEqualTo(Integer value) {
            addCriterion("wtpickpointid <=", value, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidIn(List<Integer> values) {
            addCriterion("wtpickpointid in", values, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidNotIn(List<Integer> values) {
            addCriterion("wtpickpointid not in", values, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidBetween(Integer value1, Integer value2) {
            addCriterion("wtpickpointid between", value1, value2, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtpickpointidNotBetween(Integer value1, Integer value2) {
            addCriterion("wtpickpointid not between", value1, value2, "wtpickpointid");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginIsNull() {
            addCriterion("wtweekbegin is null");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginIsNotNull() {
            addCriterion("wtweekbegin is not null");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginEqualTo(Short value) {
            addCriterion("wtweekbegin =", value, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginNotEqualTo(Short value) {
            addCriterion("wtweekbegin <>", value, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginGreaterThan(Short value) {
            addCriterion("wtweekbegin >", value, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginGreaterThanOrEqualTo(Short value) {
            addCriterion("wtweekbegin >=", value, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginLessThan(Short value) {
            addCriterion("wtweekbegin <", value, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginLessThanOrEqualTo(Short value) {
            addCriterion("wtweekbegin <=", value, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginIn(List<Short> values) {
            addCriterion("wtweekbegin in", values, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginNotIn(List<Short> values) {
            addCriterion("wtweekbegin not in", values, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginBetween(Short value1, Short value2) {
            addCriterion("wtweekbegin between", value1, value2, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekbeginNotBetween(Short value1, Short value2) {
            addCriterion("wtweekbegin not between", value1, value2, "wtweekbegin");
            return (Criteria) this;
        }

        public Criteria andWtweekendIsNull() {
            addCriterion("wtweekend is null");
            return (Criteria) this;
        }

        public Criteria andWtweekendIsNotNull() {
            addCriterion("wtweekend is not null");
            return (Criteria) this;
        }

        public Criteria andWtweekendEqualTo(Short value) {
            addCriterion("wtweekend =", value, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendNotEqualTo(Short value) {
            addCriterion("wtweekend <>", value, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendGreaterThan(Short value) {
            addCriterion("wtweekend >", value, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendGreaterThanOrEqualTo(Short value) {
            addCriterion("wtweekend >=", value, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendLessThan(Short value) {
            addCriterion("wtweekend <", value, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendLessThanOrEqualTo(Short value) {
            addCriterion("wtweekend <=", value, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendIn(List<Short> values) {
            addCriterion("wtweekend in", values, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendNotIn(List<Short> values) {
            addCriterion("wtweekend not in", values, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendBetween(Short value1, Short value2) {
            addCriterion("wtweekend between", value1, value2, "wtweekend");
            return (Criteria) this;
        }

        public Criteria andWtweekendNotBetween(Short value1, Short value2) {
            addCriterion("wtweekend not between", value1, value2, "wtweekend");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_worktime
     *
     * @mbggenerated do_not_delete_during_merge Mon Jul 27 16:49:40 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
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