package jatoo.resources;

import org.junit.Assert;
import org.junit.Test;

public class ResourcesImagesTest {

  @Test
  public void test() {

    ResourcesImages images = new ResourcesImages(getClass());

    Assert.assertNotNull(images.getImage("image.png"));
    Assert.assertNull(images.getImage("image2.png"));
  }

}
