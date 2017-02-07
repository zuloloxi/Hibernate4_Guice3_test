package com.benmccann.db;

import javax.persistence.EntityManager;
import com.google.inject.*;

public class ExamplePersistedClassDao {

  protected EntityManager entityManager;
  
  @Inject
  public ExamplePersistedClassDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  
  public void saveInNewTransaction(ExamplePersistedClass object) {
    entityManager.getTransaction().begin();
    save(object);
    entityManager.getTransaction().commit();
  }
  
  public void save(ExamplePersistedClass object) {
    entityManager.persist(object);
  }
  
  public ExamplePersistedClass getByOtherField(String otherField) {
    return (ExamplePersistedClass) entityManager
        .createQuery("select e from ExamplePersistedClass e where e.otherField=:otherField")
        .setParameter("otherField", otherField)
        .getSingleResult();
  }
}
