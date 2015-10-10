package com.runlion.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class BspOrderextExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public BspOrderextExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
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
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
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

        public Criteria andOextidIsNull() {
            addCriterion("oextid is null");
            return (Criteria) this;
        }

        public Criteria andOextidIsNotNull() {
            addCriterion("oextid is not null");
            return (Criteria) this;
        }

        public Criteria andOextidEqualTo(Integer value) {
            addCriterion("oextid =", value, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidNotEqualTo(Integer value) {
            addCriterion("oextid <>", value, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidGreaterThan(Integer value) {
            addCriterion("oextid >", value, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidGreaterThanOrEqualTo(Integer value) {
            addCriterion("oextid >=", value, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidLessThan(Integer value) {
            addCriterion("oextid <", value, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidLessThanOrEqualTo(Integer value) {
            addCriterion("oextid <=", value, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidIn(List<Integer> values) {
            addCriterion("oextid in", values, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidNotIn(List<Integer> values) {
            addCriterion("oextid not in", values, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidBetween(Integer value1, Integer value2) {
            addCriterion("oextid between", value1, value2, "oextid");
            return (Criteria) this;
        }

        public Criteria andOextidNotBetween(Integer value1, Integer value2) {
            addCriterion("oextid not between", value1, value2, "oextid");
            return (Criteria) this;
        }

        public Criteria andOexttypeIsNull() {
            addCriterion("oexttype is null");
            return (Criteria) this;
        }

        public Criteria andOexttypeIsNotNull() {
            addCriterion("oexttype is not null");
            return (Criteria) this;
        }

        public Criteria andOexttypeEqualTo(String value) {
            addCriterion("oexttype =", value, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeNotEqualTo(String value) {
            addCriterion("oexttype <>", value, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeGreaterThan(String value) {
            addCriterion("oexttype >", value, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeGreaterThanOrEqualTo(String value) {
            addCriterion("oexttype >=", value, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeLessThan(String value) {
            addCriterion("oexttype <", value, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeLessThanOrEqualTo(String value) {
            addCriterion("oexttype <=", value, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeLike(String value) {
            addCriterion("oexttype like", value, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeNotLike(String value) {
            addCriterion("oexttype not like", value, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeIn(List<String> values) {
            addCriterion("oexttype in", values, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeNotIn(List<String> values) {
            addCriterion("oexttype not in", values, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeBetween(String value1, String value2) {
            addCriterion("oexttype between", value1, value2, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOexttypeNotBetween(String value1, String value2) {
            addCriterion("oexttype not between", value1, value2, "oexttype");
            return (Criteria) this;
        }

        public Criteria andOextvalueIsNull() {
            addCriterion("oextvalue is null");
            return (Criteria) this;
        }

        public Criteria andOextvalueIsNotNull() {
            addCriterion("oextvalue is not null");
            return (Criteria) this;
        }

        public Criteria andOextvalueEqualTo(String value) {
            addCriterion("oextvalue =", value, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueNotEqualTo(String value) {
            addCriterion("oextvalue <>", value, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueGreaterThan(String value) {
            addCriterion("oextvalue >", value, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueGreaterThanOrEqualTo(String value) {
            addCriterion("oextvalue >=", value, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueLessThan(String value) {
            addCriterion("oextvalue <", value, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueLessThanOrEqualTo(String value) {
            addCriterion("oextvalue <=", value, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueLike(String value) {
            addCriterion("oextvalue like", value, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueNotLike(String value) {
            addCriterion("oextvalue not like", value, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueIn(List<String> values) {
            addCriterion("oextvalue in", values, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueNotIn(List<String> values) {
            addCriterion("oextvalue not in", values, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueBetween(String value1, String value2) {
            addCriterion("oextvalue between", value1, value2, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextvalueNotBetween(String value1, String value2) {
            addCriterion("oextvalue not between", value1, value2, "oextvalue");
            return (Criteria) this;
        }

        public Criteria andOextremarksIsNull() {
            addCriterion("oextremarks is null");
            return (Criteria) this;
        }

        public Criteria andOextremarksIsNotNull() {
            addCriterion("oextremarks is not null");
            return (Criteria) this;
        }

        public Criteria andOextremarksEqualTo(String value) {
            addCriterion("oextremarks =", value, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksNotEqualTo(String value) {
            addCriterion("oextremarks <>", value, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksGreaterThan(String value) {
            addCriterion("oextremarks >", value, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksGreaterThanOrEqualTo(String value) {
            addCriterion("oextremarks >=", value, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksLessThan(String value) {
            addCriterion("oextremarks <", value, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksLessThanOrEqualTo(String value) {
            addCriterion("oextremarks <=", value, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksLike(String value) {
            addCriterion("oextremarks like", value, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksNotLike(String value) {
            addCriterion("oextremarks not like", value, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksIn(List<String> values) {
            addCriterion("oextremarks in", values, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksNotIn(List<String> values) {
            addCriterion("oextremarks not in", values, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksBetween(String value1, String value2) {
            addCriterion("oextremarks between", value1, value2, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextremarksNotBetween(String value1, String value2) {
            addCriterion("oextremarks not between", value1, value2, "oextremarks");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeIsNull() {
            addCriterion("oextlabeltype is null");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeIsNotNull() {
            addCriterion("oextlabeltype is not null");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeEqualTo(String value) {
            addCriterion("oextlabeltype =", value, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeNotEqualTo(String value) {
            addCriterion("oextlabeltype <>", value, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeGreaterThan(String value) {
            addCriterion("oextlabeltype >", value, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeGreaterThanOrEqualTo(String value) {
            addCriterion("oextlabeltype >=", value, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeLessThan(String value) {
            addCriterion("oextlabeltype <", value, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeLessThanOrEqualTo(String value) {
            addCriterion("oextlabeltype <=", value, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeLike(String value) {
            addCriterion("oextlabeltype like", value, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeNotLike(String value) {
            addCriterion("oextlabeltype not like", value, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeIn(List<String> values) {
            addCriterion("oextlabeltype in", values, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeNotIn(List<String> values) {
            addCriterion("oextlabeltype not in", values, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeBetween(String value1, String value2) {
            addCriterion("oextlabeltype between", value1, value2, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabeltypeNotBetween(String value1, String value2) {
            addCriterion("oextlabeltype not between", value1, value2, "oextlabeltype");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueIsNull() {
            addCriterion("oextlabelvalue is null");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueIsNotNull() {
            addCriterion("oextlabelvalue is not null");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueEqualTo(String value) {
            addCriterion("oextlabelvalue =", value, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueNotEqualTo(String value) {
            addCriterion("oextlabelvalue <>", value, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueGreaterThan(String value) {
            addCriterion("oextlabelvalue >", value, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueGreaterThanOrEqualTo(String value) {
            addCriterion("oextlabelvalue >=", value, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueLessThan(String value) {
            addCriterion("oextlabelvalue <", value, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueLessThanOrEqualTo(String value) {
            addCriterion("oextlabelvalue <=", value, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueLike(String value) {
            addCriterion("oextlabelvalue like", value, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueNotLike(String value) {
            addCriterion("oextlabelvalue not like", value, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueIn(List<String> values) {
            addCriterion("oextlabelvalue in", values, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueNotIn(List<String> values) {
            addCriterion("oextlabelvalue not in", values, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueBetween(String value1, String value2) {
            addCriterion("oextlabelvalue between", value1, value2, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelvalueNotBetween(String value1, String value2) {
            addCriterion("oextlabelvalue not between", value1, value2, "oextlabelvalue");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameIsNull() {
            addCriterion("oextlabelname is null");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameIsNotNull() {
            addCriterion("oextlabelname is not null");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameEqualTo(String value) {
            addCriterion("oextlabelname =", value, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameNotEqualTo(String value) {
            addCriterion("oextlabelname <>", value, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameGreaterThan(String value) {
            addCriterion("oextlabelname >", value, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameGreaterThanOrEqualTo(String value) {
            addCriterion("oextlabelname >=", value, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameLessThan(String value) {
            addCriterion("oextlabelname <", value, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameLessThanOrEqualTo(String value) {
            addCriterion("oextlabelname <=", value, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameLike(String value) {
            addCriterion("oextlabelname like", value, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameNotLike(String value) {
            addCriterion("oextlabelname not like", value, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameIn(List<String> values) {
            addCriterion("oextlabelname in", values, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameNotIn(List<String> values) {
            addCriterion("oextlabelname not in", values, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameBetween(String value1, String value2) {
            addCriterion("oextlabelname between", value1, value2, "oextlabelname");
            return (Criteria) this;
        }

        public Criteria andOextlabelnameNotBetween(String value1, String value2) {
            addCriterion("oextlabelname not between", value1, value2, "oextlabelname");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_orderext
     *
     * @mbggenerated do_not_delete_during_merge Wed Aug 19 11:38:12 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bsp_orderext
     *
     * @mbggenerated Wed Aug 19 11:38:12 CST 2015
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