/*
 * Copyright (C) Cristian Sulea ( http://cristian.sulea.net )
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
