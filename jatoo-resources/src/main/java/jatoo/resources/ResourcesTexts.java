/*
 * Copyright (C) 2014 Cristian Sulea ( http://cristian.sulea.net )
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jatoo.resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class ease the work with texts, offering also the possibility to have a
 * fallback (very useful for example when a project extends another project).
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 3.0, July 2, 2014
 */
public class ResourcesTexts {

  private final Log logger;

  private final Class<?> clazz;
  private final ResourcesTexts fallback;

  private final ResourceBundle resourceBundle;

  public ResourcesTexts(final Class<?> clazz) {
    this(clazz, null);
  }

  public ResourcesTexts(final Class<?> clazz, final ResourcesTexts fallback) {

    logger = LogFactory.getLog(clazz);

    ResourceBundle resourceBundle;

    try {
      resourceBundle = ResourceBundle.getBundle(clazz.getPackage().getName() + ".texts");
    }

    catch (Exception e) {
      resourceBundle = null;
      logger.error("no texts for class: " + clazz.getName() + ", getText(key) will return the key", e);
    }

    this.clazz = clazz;
    this.fallback = fallback;

    this.resourceBundle = resourceBundle;
  }

  public String getText(final String key) {

    String text;

    if (resourceBundle == null) {
      text = "#" + key;
      logger.error("no texts for class: " + clazz.getName() + ", returning the key: #" + key);
    }

    try {
      text = resourceBundle.getString(key);
    }

    catch (MissingResourceException e) {

      if (fallback != null) {
        text = fallback.getText(key);
      }

      else {
        text = "#" + key;
        logger.error("no key #" + key);
      }
    }

    return text;
  }

}
