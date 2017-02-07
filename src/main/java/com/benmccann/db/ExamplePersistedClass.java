package com.benmccann.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExamplePersistedClass {

  @Id
  @GeneratedValue
  private Long id;
  
  private String otherField;

  public ExamplePersistedClass() {}

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setOtherField(String otherField) {
    this.otherField = otherField;
  }

  public String getOtherField() {
    return otherField;
  }

}