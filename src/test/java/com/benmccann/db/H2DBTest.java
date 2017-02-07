package com.benmccann.db;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.benmccann.db.DbModule;
import com.benmccann.db.ExamplePersistedClass;
import com.benmccann.db.ExamplePersistedClassDao;

public class H2DBTest {

  @Test
  public void testDb() throws SQLException {
    Injector injector = Guice.createInjector(new DbModule());
    ExamplePersistedClassDao examplePersistedClassDao = injector.getInstance(ExamplePersistedClassDao.class); 
    //ExamplePersistedClass quote=new ExamplePersistedClass();	
    
    ExamplePersistedClass example = new ExamplePersistedClass();
    example.setOtherField("hello world");
    examplePersistedClassDao .saveInNewTransaction(example);

    ExamplePersistedClass retrieved = examplePersistedClassDao.getByOtherField("hello world");

    Assert.assertEquals(example.getId(), retrieved.getId());
    Assert.assertEquals(example.getOtherField(), retrieved.getOtherField());
  }

}
