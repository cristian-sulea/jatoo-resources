package jatoo.resources;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class ResourcesTextsTest {

  @Test
  public void test() {

    ResourcesTexts texts = new ResourcesTexts(getClass());

    Assert.assertEquals("Application Title", texts.getText("title"));
    Assert.assertEquals("Application Description", texts.getText("description"));
  }

  @Test
  public void testWithLocale1() {

    Locale.setDefault(new Locale("ro"));

    ResourcesTexts texts = new ResourcesTexts(getClass());

    Assert.assertEquals("Titlul applicatiei", texts.getText("title"));
    Assert.assertEquals("Descrierea applicatiei", texts.getText("description"));
  }

  @Test
  public void testWithLocale2() {

    Locale.setDefault(new Locale("it"));

    ResourcesTexts texts = new ResourcesTexts(getClass());

    Assert.assertEquals("Application Title (it)", texts.getText("title"));
    Assert.assertEquals("Application Description (it)", texts.getText("description"));
  }

  @Test
  public void testWithMissingLocale() {

    Locale.setDefault(new Locale("xx"));

    ResourcesTexts texts = new ResourcesTexts(getClass());

    Assert.assertEquals("Application Title", texts.getText("title"));
    Assert.assertEquals("Application Description", texts.getText("description"));
  }

  @Test
  public void testWithMissingKey() {

    Locale.setDefault(new Locale("xx"));

    ResourcesTexts texts = new ResourcesTexts(getClass());

    Assert.assertEquals("#yy", texts.getText("yy"));
    Assert.assertEquals("#zz", texts.getText("zz"));
  }

}
