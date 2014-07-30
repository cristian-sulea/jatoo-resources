package jatoo.resources;

import java.util.Locale;

import org.junit.Test;

public class ResourcesTextsTest {

  @Test
  public void test() {

    ResourcesTexts texts = new ResourcesTexts(getClass());

    System.out.println(texts.getText("title"));
  }

  @Test
  public void testWithDifferentLocale() {

    ResourcesTexts texts = new ResourcesTexts(getClass());

    Locale.setDefault(new Locale("ro"));
    System.out.println(texts.getText("title"));
  }

  @Test
  public void testWithMissingLocale() {

    ResourcesTexts texts = new ResourcesTexts(getClass());

    Locale.setDefault(new Locale("en"));
    System.out.println(texts.getText("title"));
  }

}
