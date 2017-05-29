/*
 * Copyright (C) Cristian Sulea ( http://cristian.sulea.net )
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
