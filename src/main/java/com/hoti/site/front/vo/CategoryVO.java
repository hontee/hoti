package com.hoti.site.front.vo;

public class CategoryVO extends BaseVO {

  private static final long serialVersionUID = 4611166724116299154L;
  private Long parent;
  private Long weight;

  public Long getParent() {
    return parent;
  }

  public void setParent(Long parent) {
    this.parent = parent;
  }

  public Long getWeight() {
    return weight;
  }

  public void setWeight(Long weight) {
    this.weight = weight;
  }

}
