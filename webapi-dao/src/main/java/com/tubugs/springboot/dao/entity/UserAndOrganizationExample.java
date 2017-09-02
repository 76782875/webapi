package com.tubugs.springboot.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAndOrganizationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserAndOrganizationExample() {
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

        public Criteria andUser_noIsNull() {
            addCriterion("user_no is null");
            return (Criteria) this;
        }

        public Criteria andUser_noIsNotNull() {
            addCriterion("user_no is not null");
            return (Criteria) this;
        }

        public Criteria andUser_noEqualTo(Long value) {
            addCriterion("user_no =", value, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noNotEqualTo(Long value) {
            addCriterion("user_no <>", value, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noGreaterThan(Long value) {
            addCriterion("user_no >", value, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noGreaterThanOrEqualTo(Long value) {
            addCriterion("user_no >=", value, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noLessThan(Long value) {
            addCriterion("user_no <", value, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noLessThanOrEqualTo(Long value) {
            addCriterion("user_no <=", value, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noIn(List<Long> values) {
            addCriterion("user_no in", values, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noNotIn(List<Long> values) {
            addCriterion("user_no not in", values, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noBetween(Long value1, Long value2) {
            addCriterion("user_no between", value1, value2, "user_no");
            return (Criteria) this;
        }

        public Criteria andUser_noNotBetween(Long value1, Long value2) {
            addCriterion("user_no not between", value1, value2, "user_no");
            return (Criteria) this;
        }

        public Criteria andOrganization_idIsNull() {
            addCriterion("organization_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganization_idIsNotNull() {
            addCriterion("organization_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganization_idEqualTo(Long value) {
            addCriterion("organization_id =", value, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idNotEqualTo(Long value) {
            addCriterion("organization_id <>", value, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idGreaterThan(Long value) {
            addCriterion("organization_id >", value, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idGreaterThanOrEqualTo(Long value) {
            addCriterion("organization_id >=", value, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idLessThan(Long value) {
            addCriterion("organization_id <", value, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idLessThanOrEqualTo(Long value) {
            addCriterion("organization_id <=", value, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idIn(List<Long> values) {
            addCriterion("organization_id in", values, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idNotIn(List<Long> values) {
            addCriterion("organization_id not in", values, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idBetween(Long value1, Long value2) {
            addCriterion("organization_id between", value1, value2, "organization_id");
            return (Criteria) this;
        }

        public Criteria andOrganization_idNotBetween(Long value1, Long value2) {
            addCriterion("organization_id not between", value1, value2, "organization_id");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(Date value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(Date value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(Date value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<Date> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
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