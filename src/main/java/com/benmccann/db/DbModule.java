package com.benmccann.db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @author Ben McCann (benmccann.com)
 */
public class DbModule extends AbstractModule {

  private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE
      = new ThreadLocal<EntityManager>();

  public void configure() {
  }

  @Provides @Singleton
  public EntityManagerFactory provideEntityManagerFactory() {
    Map<String, String> properties = new HashMap<String, String>();
    properties.put("javax.persistence.jdbc.driver", "org.hsqldb.jdbcDriver");
    properties.put("javax.persistence.jdbc.url", "jdbc:hsqldb:hsql://localhost/testdb");
	properties.put("javax.persistence.jdbc.user", "sa");
    properties.put("javax.persistence.jdbc.password", "");
    properties.put("hibernate.connection.charSet", "UTF-8");
    properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
	properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
	properties.put("hibernate.max_fetch_depth", "3");
	properties.put("hibernate.id.new_generator_mappings", "true");
	properties.put("hibernate.hbm2ddl.auto", "create");
    return Persistence.createEntityManagerFactory("db-manager", properties);
  }
  
  @Provides
  public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
    EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
    if (entityManager == null) {
      ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
    }
    return entityManager;
  }

}
