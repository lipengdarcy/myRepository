package com.witsafe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CompanyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CompanyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordIsNull() {
            addCriterion("plainpassword is null");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordIsNotNull() {
            addCriterion("plainpassword is not null");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordEqualTo(String value) {
            addCriterion("plainpassword =", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordNotEqualTo(String value) {
            addCriterion("plainpassword <>", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordGreaterThan(String value) {
            addCriterion("plainpassword >", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("plainpassword >=", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordLessThan(String value) {
            addCriterion("plainpassword <", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordLessThanOrEqualTo(String value) {
            addCriterion("plainpassword <=", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordLike(String value) {
            addCriterion("plainpassword like", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordNotLike(String value) {
            addCriterion("plainpassword not like", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordIn(List<String> values) {
            addCriterion("plainpassword in", values, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordNotIn(List<String> values) {
            addCriterion("plainpassword not in", values, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordBetween(String value1, String value2) {
            addCriterion("plainpassword between", value1, value2, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordNotBetween(String value1, String value2) {
            addCriterion("plainpassword not between", value1, value2, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCompanytypeIsNull() {
            addCriterion("companyType is null");
            return (Criteria) this;
        }

        public Criteria andCompanytypeIsNotNull() {
            addCriterion("companyType is not null");
            return (Criteria) this;
        }

        public Criteria andCompanytypeEqualTo(Byte value) {
            addCriterion("companyType =", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeNotEqualTo(Byte value) {
            addCriterion("companyType <>", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeGreaterThan(Byte value) {
            addCriterion("companyType >", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("companyType >=", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeLessThan(Byte value) {
            addCriterion("companyType <", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeLessThanOrEqualTo(Byte value) {
            addCriterion("companyType <=", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeIn(List<Byte> values) {
            addCriterion("companyType in", values, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeNotIn(List<Byte> values) {
            addCriterion("companyType not in", values, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeBetween(Byte value1, Byte value2) {
            addCriterion("companyType between", value1, value2, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeNotBetween(Byte value1, Byte value2) {
            addCriterion("companyType not between", value1, value2, "companytype");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(String value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLike(String value) {
            addCriterion("department like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotLike(String value) {
            addCriterion("department not like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<String> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<String> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("department not between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("fax is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("fax is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("fax =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("fax <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("fax >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("fax >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("fax <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("fax <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("fax like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("fax not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("fax in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("fax not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("fax between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("fax not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andManagernameIsNull() {
            addCriterion("managerName is null");
            return (Criteria) this;
        }

        public Criteria andManagernameIsNotNull() {
            addCriterion("managerName is not null");
            return (Criteria) this;
        }

        public Criteria andManagernameEqualTo(String value) {
            addCriterion("managerName =", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameNotEqualTo(String value) {
            addCriterion("managerName <>", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameGreaterThan(String value) {
            addCriterion("managerName >", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameGreaterThanOrEqualTo(String value) {
            addCriterion("managerName >=", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameLessThan(String value) {
            addCriterion("managerName <", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameLessThanOrEqualTo(String value) {
            addCriterion("managerName <=", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameLike(String value) {
            addCriterion("managerName like", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameNotLike(String value) {
            addCriterion("managerName not like", value, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameIn(List<String> values) {
            addCriterion("managerName in", values, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameNotIn(List<String> values) {
            addCriterion("managerName not in", values, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameBetween(String value1, String value2) {
            addCriterion("managerName between", value1, value2, "managername");
            return (Criteria) this;
        }

        public Criteria andManagernameNotBetween(String value1, String value2) {
            addCriterion("managerName not between", value1, value2, "managername");
            return (Criteria) this;
        }

        public Criteria andManagertelIsNull() {
            addCriterion("managerTel is null");
            return (Criteria) this;
        }

        public Criteria andManagertelIsNotNull() {
            addCriterion("managerTel is not null");
            return (Criteria) this;
        }

        public Criteria andManagertelEqualTo(String value) {
            addCriterion("managerTel =", value, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelNotEqualTo(String value) {
            addCriterion("managerTel <>", value, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelGreaterThan(String value) {
            addCriterion("managerTel >", value, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelGreaterThanOrEqualTo(String value) {
            addCriterion("managerTel >=", value, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelLessThan(String value) {
            addCriterion("managerTel <", value, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelLessThanOrEqualTo(String value) {
            addCriterion("managerTel <=", value, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelLike(String value) {
            addCriterion("managerTel like", value, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelNotLike(String value) {
            addCriterion("managerTel not like", value, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelIn(List<String> values) {
            addCriterion("managerTel in", values, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelNotIn(List<String> values) {
            addCriterion("managerTel not in", values, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelBetween(String value1, String value2) {
            addCriterion("managerTel between", value1, value2, "managertel");
            return (Criteria) this;
        }

        public Criteria andManagertelNotBetween(String value1, String value2) {
            addCriterion("managerTel not between", value1, value2, "managertel");
            return (Criteria) this;
        }

        public Criteria andAddresspostIsNull() {
            addCriterion("addressPost is null");
            return (Criteria) this;
        }

        public Criteria andAddresspostIsNotNull() {
            addCriterion("addressPost is not null");
            return (Criteria) this;
        }

        public Criteria andAddresspostEqualTo(String value) {
            addCriterion("addressPost =", value, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostNotEqualTo(String value) {
            addCriterion("addressPost <>", value, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostGreaterThan(String value) {
            addCriterion("addressPost >", value, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostGreaterThanOrEqualTo(String value) {
            addCriterion("addressPost >=", value, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostLessThan(String value) {
            addCriterion("addressPost <", value, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostLessThanOrEqualTo(String value) {
            addCriterion("addressPost <=", value, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostLike(String value) {
            addCriterion("addressPost like", value, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostNotLike(String value) {
            addCriterion("addressPost not like", value, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostIn(List<String> values) {
            addCriterion("addressPost in", values, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostNotIn(List<String> values) {
            addCriterion("addressPost not in", values, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostBetween(String value1, String value2) {
            addCriterion("addressPost between", value1, value2, "addresspost");
            return (Criteria) this;
        }

        public Criteria andAddresspostNotBetween(String value1, String value2) {
            addCriterion("addressPost not between", value1, value2, "addresspost");
            return (Criteria) this;
        }

        public Criteria andTaxnoIsNull() {
            addCriterion("taxNo is null");
            return (Criteria) this;
        }

        public Criteria andTaxnoIsNotNull() {
            addCriterion("taxNo is not null");
            return (Criteria) this;
        }

        public Criteria andTaxnoEqualTo(String value) {
            addCriterion("taxNo =", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoNotEqualTo(String value) {
            addCriterion("taxNo <>", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoGreaterThan(String value) {
            addCriterion("taxNo >", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoGreaterThanOrEqualTo(String value) {
            addCriterion("taxNo >=", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoLessThan(String value) {
            addCriterion("taxNo <", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoLessThanOrEqualTo(String value) {
            addCriterion("taxNo <=", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoLike(String value) {
            addCriterion("taxNo like", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoNotLike(String value) {
            addCriterion("taxNo not like", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoIn(List<String> values) {
            addCriterion("taxNo in", values, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoNotIn(List<String> values) {
            addCriterion("taxNo not in", values, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoBetween(String value1, String value2) {
            addCriterion("taxNo between", value1, value2, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoNotBetween(String value1, String value2) {
            addCriterion("taxNo not between", value1, value2, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxtypeIsNull() {
            addCriterion("taxtype is null");
            return (Criteria) this;
        }

        public Criteria andTaxtypeIsNotNull() {
            addCriterion("taxtype is not null");
            return (Criteria) this;
        }

        public Criteria andTaxtypeEqualTo(Integer value) {
            addCriterion("taxtype =", value, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeNotEqualTo(Integer value) {
            addCriterion("taxtype <>", value, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeGreaterThan(Integer value) {
            addCriterion("taxtype >", value, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("taxtype >=", value, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeLessThan(Integer value) {
            addCriterion("taxtype <", value, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeLessThanOrEqualTo(Integer value) {
            addCriterion("taxtype <=", value, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeIn(List<Integer> values) {
            addCriterion("taxtype in", values, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeNotIn(List<Integer> values) {
            addCriterion("taxtype not in", values, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeBetween(Integer value1, Integer value2) {
            addCriterion("taxtype between", value1, value2, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("taxtype not between", value1, value2, "taxtype");
            return (Criteria) this;
        }

        public Criteria andTaxbankIsNull() {
            addCriterion("taxbank is null");
            return (Criteria) this;
        }

        public Criteria andTaxbankIsNotNull() {
            addCriterion("taxbank is not null");
            return (Criteria) this;
        }

        public Criteria andTaxbankEqualTo(String value) {
            addCriterion("taxbank =", value, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankNotEqualTo(String value) {
            addCriterion("taxbank <>", value, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankGreaterThan(String value) {
            addCriterion("taxbank >", value, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankGreaterThanOrEqualTo(String value) {
            addCriterion("taxbank >=", value, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankLessThan(String value) {
            addCriterion("taxbank <", value, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankLessThanOrEqualTo(String value) {
            addCriterion("taxbank <=", value, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankLike(String value) {
            addCriterion("taxbank like", value, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankNotLike(String value) {
            addCriterion("taxbank not like", value, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankIn(List<String> values) {
            addCriterion("taxbank in", values, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankNotIn(List<String> values) {
            addCriterion("taxbank not in", values, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankBetween(String value1, String value2) {
            addCriterion("taxbank between", value1, value2, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbankNotBetween(String value1, String value2) {
            addCriterion("taxbank not between", value1, value2, "taxbank");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoIsNull() {
            addCriterion("taxbankno is null");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoIsNotNull() {
            addCriterion("taxbankno is not null");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoEqualTo(String value) {
            addCriterion("taxbankno =", value, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoNotEqualTo(String value) {
            addCriterion("taxbankno <>", value, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoGreaterThan(String value) {
            addCriterion("taxbankno >", value, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoGreaterThanOrEqualTo(String value) {
            addCriterion("taxbankno >=", value, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoLessThan(String value) {
            addCriterion("taxbankno <", value, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoLessThanOrEqualTo(String value) {
            addCriterion("taxbankno <=", value, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoLike(String value) {
            addCriterion("taxbankno like", value, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoNotLike(String value) {
            addCriterion("taxbankno not like", value, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoIn(List<String> values) {
            addCriterion("taxbankno in", values, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoNotIn(List<String> values) {
            addCriterion("taxbankno not in", values, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoBetween(String value1, String value2) {
            addCriterion("taxbankno between", value1, value2, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxbanknoNotBetween(String value1, String value2) {
            addCriterion("taxbankno not between", value1, value2, "taxbankno");
            return (Criteria) this;
        }

        public Criteria andTaxaddressIsNull() {
            addCriterion("taxaddress is null");
            return (Criteria) this;
        }

        public Criteria andTaxaddressIsNotNull() {
            addCriterion("taxaddress is not null");
            return (Criteria) this;
        }

        public Criteria andTaxaddressEqualTo(String value) {
            addCriterion("taxaddress =", value, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressNotEqualTo(String value) {
            addCriterion("taxaddress <>", value, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressGreaterThan(String value) {
            addCriterion("taxaddress >", value, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressGreaterThanOrEqualTo(String value) {
            addCriterion("taxaddress >=", value, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressLessThan(String value) {
            addCriterion("taxaddress <", value, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressLessThanOrEqualTo(String value) {
            addCriterion("taxaddress <=", value, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressLike(String value) {
            addCriterion("taxaddress like", value, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressNotLike(String value) {
            addCriterion("taxaddress not like", value, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressIn(List<String> values) {
            addCriterion("taxaddress in", values, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressNotIn(List<String> values) {
            addCriterion("taxaddress not in", values, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressBetween(String value1, String value2) {
            addCriterion("taxaddress between", value1, value2, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxaddressNotBetween(String value1, String value2) {
            addCriterion("taxaddress not between", value1, value2, "taxaddress");
            return (Criteria) this;
        }

        public Criteria andTaxtelIsNull() {
            addCriterion("taxtel is null");
            return (Criteria) this;
        }

        public Criteria andTaxtelIsNotNull() {
            addCriterion("taxtel is not null");
            return (Criteria) this;
        }

        public Criteria andTaxtelEqualTo(String value) {
            addCriterion("taxtel =", value, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelNotEqualTo(String value) {
            addCriterion("taxtel <>", value, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelGreaterThan(String value) {
            addCriterion("taxtel >", value, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelGreaterThanOrEqualTo(String value) {
            addCriterion("taxtel >=", value, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelLessThan(String value) {
            addCriterion("taxtel <", value, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelLessThanOrEqualTo(String value) {
            addCriterion("taxtel <=", value, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelLike(String value) {
            addCriterion("taxtel like", value, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelNotLike(String value) {
            addCriterion("taxtel not like", value, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelIn(List<String> values) {
            addCriterion("taxtel in", values, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelNotIn(List<String> values) {
            addCriterion("taxtel not in", values, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelBetween(String value1, String value2) {
            addCriterion("taxtel between", value1, value2, "taxtel");
            return (Criteria) this;
        }

        public Criteria andTaxtelNotBetween(String value1, String value2) {
            addCriterion("taxtel not between", value1, value2, "taxtel");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedIsNull() {
            addCriterion("isEvaluated is null");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedIsNotNull() {
            addCriterion("isEvaluated is not null");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedEqualTo(Byte value) {
            addCriterion("isEvaluated =", value, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedNotEqualTo(Byte value) {
            addCriterion("isEvaluated <>", value, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedGreaterThan(Byte value) {
            addCriterion("isEvaluated >", value, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedGreaterThanOrEqualTo(Byte value) {
            addCriterion("isEvaluated >=", value, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedLessThan(Byte value) {
            addCriterion("isEvaluated <", value, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedLessThanOrEqualTo(Byte value) {
            addCriterion("isEvaluated <=", value, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedIn(List<Byte> values) {
            addCriterion("isEvaluated in", values, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedNotIn(List<Byte> values) {
            addCriterion("isEvaluated not in", values, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedBetween(Byte value1, Byte value2) {
            addCriterion("isEvaluated between", value1, value2, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsevaluatedNotBetween(Byte value1, Byte value2) {
            addCriterion("isEvaluated not between", value1, value2, "isevaluated");
            return (Criteria) this;
        }

        public Criteria andIsacceptedIsNull() {
            addCriterion("isAccepted is null");
            return (Criteria) this;
        }

        public Criteria andIsacceptedIsNotNull() {
            addCriterion("isAccepted is not null");
            return (Criteria) this;
        }

        public Criteria andIsacceptedEqualTo(Byte value) {
            addCriterion("isAccepted =", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedNotEqualTo(Byte value) {
            addCriterion("isAccepted <>", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedGreaterThan(Byte value) {
            addCriterion("isAccepted >", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedGreaterThanOrEqualTo(Byte value) {
            addCriterion("isAccepted >=", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedLessThan(Byte value) {
            addCriterion("isAccepted <", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedLessThanOrEqualTo(Byte value) {
            addCriterion("isAccepted <=", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedIn(List<Byte> values) {
            addCriterion("isAccepted in", values, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedNotIn(List<Byte> values) {
            addCriterion("isAccepted not in", values, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedBetween(Byte value1, Byte value2) {
            addCriterion("isAccepted between", value1, value2, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedNotBetween(Byte value1, Byte value2) {
            addCriterion("isAccepted not between", value1, value2, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andAddressidIsNull() {
            addCriterion("addressId is null");
            return (Criteria) this;
        }

        public Criteria andAddressidIsNotNull() {
            addCriterion("addressId is not null");
            return (Criteria) this;
        }

        public Criteria andAddressidEqualTo(Integer value) {
            addCriterion("addressId =", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidNotEqualTo(Integer value) {
            addCriterion("addressId <>", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidGreaterThan(Integer value) {
            addCriterion("addressId >", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidGreaterThanOrEqualTo(Integer value) {
            addCriterion("addressId >=", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidLessThan(Integer value) {
            addCriterion("addressId <", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidLessThanOrEqualTo(Integer value) {
            addCriterion("addressId <=", value, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidIn(List<Integer> values) {
            addCriterion("addressId in", values, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidNotIn(List<Integer> values) {
            addCriterion("addressId not in", values, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidBetween(Integer value1, Integer value2) {
            addCriterion("addressId between", value1, value2, "addressid");
            return (Criteria) this;
        }

        public Criteria andAddressidNotBetween(Integer value1, Integer value2) {
            addCriterion("addressId not between", value1, value2, "addressid");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeIsNull() {
            addCriterion("radioactiveType is null");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeIsNotNull() {
            addCriterion("radioactiveType is not null");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeEqualTo(String value) {
            addCriterion("radioactiveType =", value, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeNotEqualTo(String value) {
            addCriterion("radioactiveType <>", value, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeGreaterThan(String value) {
            addCriterion("radioactiveType >", value, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeGreaterThanOrEqualTo(String value) {
            addCriterion("radioactiveType >=", value, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeLessThan(String value) {
            addCriterion("radioactiveType <", value, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeLessThanOrEqualTo(String value) {
            addCriterion("radioactiveType <=", value, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeLike(String value) {
            addCriterion("radioactiveType like", value, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeNotLike(String value) {
            addCriterion("radioactiveType not like", value, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeIn(List<String> values) {
            addCriterion("radioactiveType in", values, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeNotIn(List<String> values) {
            addCriterion("radioactiveType not in", values, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeBetween(String value1, String value2) {
            addCriterion("radioactiveType between", value1, value2, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRadioactivetypeNotBetween(String value1, String value2) {
            addCriterion("radioactiveType not between", value1, value2, "radioactivetype");
            return (Criteria) this;
        }

        public Criteria andRegistertimeIsNull() {
            addCriterion("registerTime is null");
            return (Criteria) this;
        }

        public Criteria andRegistertimeIsNotNull() {
            addCriterion("registerTime is not null");
            return (Criteria) this;
        }

        public Criteria andRegistertimeEqualTo(Date value) {
            addCriterionForJDBCDate("registerTime =", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("registerTime <>", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeGreaterThan(Date value) {
            addCriterionForJDBCDate("registerTime >", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registerTime >=", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeLessThan(Date value) {
            addCriterionForJDBCDate("registerTime <", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registerTime <=", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeIn(List<Date> values) {
            addCriterionForJDBCDate("registerTime in", values, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("registerTime not in", values, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registerTime between", value1, value2, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registerTime not between", value1, value2, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertypeIsNull() {
            addCriterion("registerType is null");
            return (Criteria) this;
        }

        public Criteria andRegistertypeIsNotNull() {
            addCriterion("registerType is not null");
            return (Criteria) this;
        }

        public Criteria andRegistertypeEqualTo(Byte value) {
            addCriterion("registerType =", value, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeNotEqualTo(Byte value) {
            addCriterion("registerType <>", value, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeGreaterThan(Byte value) {
            addCriterion("registerType >", value, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("registerType >=", value, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeLessThan(Byte value) {
            addCriterion("registerType <", value, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeLessThanOrEqualTo(Byte value) {
            addCriterion("registerType <=", value, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeIn(List<Byte> values) {
            addCriterion("registerType in", values, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeNotIn(List<Byte> values) {
            addCriterion("registerType not in", values, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeBetween(Byte value1, Byte value2) {
            addCriterion("registerType between", value1, value2, "registertype");
            return (Criteria) this;
        }

        public Criteria andRegistertypeNotBetween(Byte value1, Byte value2) {
            addCriterion("registerType not between", value1, value2, "registertype");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNull() {
            addCriterion("isValid is null");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNotNull() {
            addCriterion("isValid is not null");
            return (Criteria) this;
        }

        public Criteria andIsvalidEqualTo(Byte value) {
            addCriterion("isValid =", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotEqualTo(Byte value) {
            addCriterion("isValid <>", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThan(Byte value) {
            addCriterion("isValid >", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThanOrEqualTo(Byte value) {
            addCriterion("isValid >=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThan(Byte value) {
            addCriterion("isValid <", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThanOrEqualTo(Byte value) {
            addCriterion("isValid <=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidIn(List<Byte> values) {
            addCriterion("isValid in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotIn(List<Byte> values) {
            addCriterion("isValid not in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidBetween(Byte value1, Byte value2) {
            addCriterion("isValid between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotBetween(Byte value1, Byte value2) {
            addCriterion("isValid not between", value1, value2, "isvalid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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