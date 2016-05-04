package com.ikyer.site.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecommendExample {
  
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public RecommendExample() {
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

    public Criteria andTitleIsNull() {
      addCriterion("title is null");
      return (Criteria) this;
    }

    public Criteria andTitleIsNotNull() {
      addCriterion("title is not null");
      return (Criteria) this;
    }

    public Criteria andTitleEqualTo(String value) {
      addCriterion("title =", value, "title");
      return (Criteria) this;
    }

    public Criteria andTitleNotEqualTo(String value) {
      addCriterion("title <>", value, "title");
      return (Criteria) this;
    }

    public Criteria andTitleGreaterThan(String value) {
      addCriterion("title >", value, "title");
      return (Criteria) this;
    }

    public Criteria andTitleGreaterThanOrEqualTo(String value) {
      addCriterion("title >=", value, "title");
      return (Criteria) this;
    }

    public Criteria andTitleLessThan(String value) {
      addCriterion("title <", value, "title");
      return (Criteria) this;
    }

    public Criteria andTitleLessThanOrEqualTo(String value) {
      addCriterion("title <=", value, "title");
      return (Criteria) this;
    }

    public Criteria andTitleLike(String value) {
      addCriterion("title like", value, "title");
      return (Criteria) this;
    }

    public Criteria andTitleNotLike(String value) {
      addCriterion("title not like", value, "title");
      return (Criteria) this;
    }

    public Criteria andTitleIn(List<String> values) {
      addCriterion("title in", values, "title");
      return (Criteria) this;
    }

    public Criteria andTitleNotIn(List<String> values) {
      addCriterion("title not in", values, "title");
      return (Criteria) this;
    }

    public Criteria andTitleBetween(String value1, String value2) {
      addCriterion("title between", value1, value2, "title");
      return (Criteria) this;
    }

    public Criteria andTitleNotBetween(String value1, String value2) {
      addCriterion("title not between", value1, value2, "title");
      return (Criteria) this;
    }

    public Criteria andKeywordsIsNull() {
      addCriterion("keywords is null");
      return (Criteria) this;
    }

    public Criteria andKeywordsIsNotNull() {
      addCriterion("keywords is not null");
      return (Criteria) this;
    }

    public Criteria andKeywordsEqualTo(String value) {
      addCriterion("keywords =", value, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsNotEqualTo(String value) {
      addCriterion("keywords <>", value, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsGreaterThan(String value) {
      addCriterion("keywords >", value, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
      addCriterion("keywords >=", value, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsLessThan(String value) {
      addCriterion("keywords <", value, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsLessThanOrEqualTo(String value) {
      addCriterion("keywords <=", value, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsLike(String value) {
      addCriterion("keywords like", value, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsNotLike(String value) {
      addCriterion("keywords not like", value, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsIn(List<String> values) {
      addCriterion("keywords in", values, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsNotIn(List<String> values) {
      addCriterion("keywords not in", values, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsBetween(String value1, String value2) {
      addCriterion("keywords between", value1, value2, "keywords");
      return (Criteria) this;
    }

    public Criteria andKeywordsNotBetween(String value1, String value2) {
      addCriterion("keywords not between", value1, value2, "keywords");
      return (Criteria) this;
    }

    public Criteria andDescriptionIsNull() {
      addCriterion("description is null");
      return (Criteria) this;
    }

    public Criteria andDescriptionIsNotNull() {
      addCriterion("description is not null");
      return (Criteria) this;
    }

    public Criteria andDescriptionEqualTo(String value) {
      addCriterion("description =", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotEqualTo(String value) {
      addCriterion("description <>", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionGreaterThan(String value) {
      addCriterion("description >", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
      addCriterion("description >=", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLessThan(String value) {
      addCriterion("description <", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLessThanOrEqualTo(String value) {
      addCriterion("description <=", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLike(String value) {
      addCriterion("description like", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotLike(String value) {
      addCriterion("description not like", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionIn(List<String> values) {
      addCriterion("description in", values, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotIn(List<String> values) {
      addCriterion("description not in", values, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionBetween(String value1, String value2) {
      addCriterion("description between", value1, value2, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotBetween(String value1, String value2) {
      addCriterion("description not between", value1, value2, "description");
      return (Criteria) this;
    }

    public Criteria andUrlIsNull() {
      addCriterion("url is null");
      return (Criteria) this;
    }

    public Criteria andUrlIsNotNull() {
      addCriterion("url is not null");
      return (Criteria) this;
    }

    public Criteria andUrlEqualTo(String value) {
      addCriterion("url =", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotEqualTo(String value) {
      addCriterion("url <>", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlGreaterThan(String value) {
      addCriterion("url >", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlGreaterThanOrEqualTo(String value) {
      addCriterion("url >=", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLessThan(String value) {
      addCriterion("url <", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLessThanOrEqualTo(String value) {
      addCriterion("url <=", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLike(String value) {
      addCriterion("url like", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotLike(String value) {
      addCriterion("url not like", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlIn(List<String> values) {
      addCriterion("url in", values, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotIn(List<String> values) {
      addCriterion("url not in", values, "url");
      return (Criteria) this;
    }

    public Criteria andUrlBetween(String value1, String value2) {
      addCriterion("url between", value1, value2, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotBetween(String value1, String value2) {
      addCriterion("url not between", value1, value2, "url");
      return (Criteria) this;
    }

    public Criteria andTidIsNull() {
      addCriterion("tid is null");
      return (Criteria) this;
    }

    public Criteria andTidIsNotNull() {
      addCriterion("tid is not null");
      return (Criteria) this;
    }

    public Criteria andTidEqualTo(Long value) {
      addCriterion("tid =", value, "tid");
      return (Criteria) this;
    }

    public Criteria andTidNotEqualTo(Long value) {
      addCriterion("tid <>", value, "tid");
      return (Criteria) this;
    }

    public Criteria andTidGreaterThan(Long value) {
      addCriterion("tid >", value, "tid");
      return (Criteria) this;
    }

    public Criteria andTidGreaterThanOrEqualTo(Long value) {
      addCriterion("tid >=", value, "tid");
      return (Criteria) this;
    }

    public Criteria andTidLessThan(Long value) {
      addCriterion("tid <", value, "tid");
      return (Criteria) this;
    }

    public Criteria andTidLessThanOrEqualTo(Long value) {
      addCriterion("tid <=", value, "tid");
      return (Criteria) this;
    }

    public Criteria andTidIn(List<Long> values) {
      addCriterion("tid in", values, "tid");
      return (Criteria) this;
    }

    public Criteria andTidNotIn(List<Long> values) {
      addCriterion("tid not in", values, "tid");
      return (Criteria) this;
    }

    public Criteria andTidBetween(Long value1, Long value2) {
      addCriterion("tid between", value1, value2, "tid");
      return (Criteria) this;
    }

    public Criteria andTidNotBetween(Long value1, Long value2) {
      addCriterion("tid not between", value1, value2, "tid");
      return (Criteria) this;
    }

    public Criteria andTopicIsNull() {
      addCriterion("topic is null");
      return (Criteria) this;
    }

    public Criteria andTopicIsNotNull() {
      addCriterion("topic is not null");
      return (Criteria) this;
    }

    public Criteria andTopicEqualTo(String value) {
      addCriterion("topic =", value, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicNotEqualTo(String value) {
      addCriterion("topic <>", value, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicGreaterThan(String value) {
      addCriterion("topic >", value, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicGreaterThanOrEqualTo(String value) {
      addCriterion("topic >=", value, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicLessThan(String value) {
      addCriterion("topic <", value, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicLessThanOrEqualTo(String value) {
      addCriterion("topic <=", value, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicLike(String value) {
      addCriterion("topic like", value, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicNotLike(String value) {
      addCriterion("topic not like", value, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicIn(List<String> values) {
      addCriterion("topic in", values, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicNotIn(List<String> values) {
      addCriterion("topic not in", values, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicBetween(String value1, String value2) {
      addCriterion("topic between", value1, value2, "topic");
      return (Criteria) this;
    }

    public Criteria andTopicNotBetween(String value1, String value2) {
      addCriterion("topic not between", value1, value2, "topic");
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

    public Criteria andStateEqualTo(Byte value) {
      addCriterion("state =", value, "state");
      return (Criteria) this;
    }

    public Criteria andStateNotEqualTo(Byte value) {
      addCriterion("state <>", value, "state");
      return (Criteria) this;
    }

    public Criteria andStateGreaterThan(Byte value) {
      addCriterion("state >", value, "state");
      return (Criteria) this;
    }

    public Criteria andStateGreaterThanOrEqualTo(Byte value) {
      addCriterion("state >=", value, "state");
      return (Criteria) this;
    }

    public Criteria andStateLessThan(Byte value) {
      addCriterion("state <", value, "state");
      return (Criteria) this;
    }

    public Criteria andStateLessThanOrEqualTo(Byte value) {
      addCriterion("state <=", value, "state");
      return (Criteria) this;
    }

    public Criteria andStateIn(List<Byte> values) {
      addCriterion("state in", values, "state");
      return (Criteria) this;
    }

    public Criteria andStateNotIn(List<Byte> values) {
      addCriterion("state not in", values, "state");
      return (Criteria) this;
    }

    public Criteria andStateBetween(Byte value1, Byte value2) {
      addCriterion("state between", value1, value2, "state");
      return (Criteria) this;
    }

    public Criteria andStateNotBetween(Byte value1, Byte value2) {
      addCriterion("state not between", value1, value2, "state");
      return (Criteria) this;
    }

    public Criteria andCreatedIsNull() {
      addCriterion("created is null");
      return (Criteria) this;
    }

    public Criteria andCreatedIsNotNull() {
      addCriterion("created is not null");
      return (Criteria) this;
    }

    public Criteria andCreatedEqualTo(Date value) {
      addCriterion("created =", value, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedNotEqualTo(Date value) {
      addCriterion("created <>", value, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedGreaterThan(Date value) {
      addCriterion("created >", value, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
      addCriterion("created >=", value, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedLessThan(Date value) {
      addCriterion("created <", value, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedLessThanOrEqualTo(Date value) {
      addCriterion("created <=", value, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedIn(List<Date> values) {
      addCriterion("created in", values, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedNotIn(List<Date> values) {
      addCriterion("created not in", values, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedBetween(Date value1, Date value2) {
      addCriterion("created between", value1, value2, "created");
      return (Criteria) this;
    }

    public Criteria andCreatedNotBetween(Date value1, Date value2) {
      addCriterion("created not between", value1, value2, "created");
      return (Criteria) this;
    }

    public Criteria andLastModifiedIsNull() {
      addCriterion("last_modified is null");
      return (Criteria) this;
    }

    public Criteria andLastModifiedIsNotNull() {
      addCriterion("last_modified is not null");
      return (Criteria) this;
    }

    public Criteria andLastModifiedEqualTo(Date value) {
      addCriterion("last_modified =", value, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedNotEqualTo(Date value) {
      addCriterion("last_modified <>", value, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedGreaterThan(Date value) {
      addCriterion("last_modified >", value, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedGreaterThanOrEqualTo(Date value) {
      addCriterion("last_modified >=", value, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedLessThan(Date value) {
      addCriterion("last_modified <", value, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedLessThanOrEqualTo(Date value) {
      addCriterion("last_modified <=", value, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedIn(List<Date> values) {
      addCriterion("last_modified in", values, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedNotIn(List<Date> values) {
      addCriterion("last_modified not in", values, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedBetween(Date value1, Date value2) {
      addCriterion("last_modified between", value1, value2, "lastModified");
      return (Criteria) this;
    }

    public Criteria andLastModifiedNotBetween(Date value1, Date value2) {
      addCriterion("last_modified not between", value1, value2, "lastModified");
      return (Criteria) this;
    }

    public Criteria andCreatorIsNull() {
      addCriterion("creator is null");
      return (Criteria) this;
    }

    public Criteria andCreatorIsNotNull() {
      addCriterion("creator is not null");
      return (Criteria) this;
    }

    public Criteria andCreatorEqualTo(String value) {
      addCriterion("creator =", value, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorNotEqualTo(String value) {
      addCriterion("creator <>", value, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorGreaterThan(String value) {
      addCriterion("creator >", value, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorGreaterThanOrEqualTo(String value) {
      addCriterion("creator >=", value, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorLessThan(String value) {
      addCriterion("creator <", value, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorLessThanOrEqualTo(String value) {
      addCriterion("creator <=", value, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorLike(String value) {
      addCriterion("creator like", value, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorNotLike(String value) {
      addCriterion("creator not like", value, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorIn(List<String> values) {
      addCriterion("creator in", values, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorNotIn(List<String> values) {
      addCriterion("creator not in", values, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorBetween(String value1, String value2) {
      addCriterion("creator between", value1, value2, "creator");
      return (Criteria) this;
    }

    public Criteria andCreatorNotBetween(String value1, String value2) {
      addCriterion("creator not between", value1, value2, "creator");
      return (Criteria) this;
    }

    public Criteria andRemarkIsNull() {
      addCriterion("remark is null");
      return (Criteria) this;
    }

    public Criteria andRemarkIsNotNull() {
      addCriterion("remark is not null");
      return (Criteria) this;
    }

    public Criteria andRemarkEqualTo(String value) {
      addCriterion("remark =", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotEqualTo(String value) {
      addCriterion("remark <>", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkGreaterThan(String value) {
      addCriterion("remark >", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkGreaterThanOrEqualTo(String value) {
      addCriterion("remark >=", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLessThan(String value) {
      addCriterion("remark <", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLessThanOrEqualTo(String value) {
      addCriterion("remark <=", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLike(String value) {
      addCriterion("remark like", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotLike(String value) {
      addCriterion("remark not like", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkIn(List<String> values) {
      addCriterion("remark in", values, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotIn(List<String> values) {
      addCriterion("remark not in", values, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkBetween(String value1, String value2) {
      addCriterion("remark between", value1, value2, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotBetween(String value1, String value2) {
      addCriterion("remark not between", value1, value2, "remark");
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
