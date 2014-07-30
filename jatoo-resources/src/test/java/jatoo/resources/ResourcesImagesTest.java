package jatoo.resources;

import org.junit.Test;

public class ResourcesImagesTest {

  @Test
  public void test() {

    ResourcesImages images = new ResourcesImages(getClass());

    System.out.println(images.getImage("background.png"));
    System.out.println(images.getImageIcon("icon-ok.png"));
  }

}
