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

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class ease the work with texts, offering also the possibility to have a
 * fallback (very useful for example when a project extends another project).
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 3.1, July 29, 2014
 */
public final class ResourcesTexts {

  /** The logger. */
  private final Log logger;

  /** The class that will provide the base name. */
  private final Class<?> clazz;

  /** The fallback to be used when a resource is not found. */
  private final ResourcesTexts fallback;

  /** The resource bundle with the locale specific texts. */
  private final ResourceBundle resourceBundle;

  /**
   * Creates a {@link ResourcesTexts} object using as base name the package of
   * the specified class.
   * 
   * @param clazz
   *          the class that will provide the base name
   */
  public ResourcesTexts(final Class<?> clazz) {
    this(clazz, null);
  }

  /**
   * Creates a {@link ResourcesTexts} object using as base name the package of
   * the specified class. If a resource is not found, the fallback will be used
   * before returning.
   * 
   * @param clazz
   *          the class that will provide the base name
   * @param fallback
   *          the {@link ResourcesTexts} to be used in case a resource is not
   *          found in this one
   */
  public ResourcesTexts(final Class<?> clazz, final ResourcesTexts fallback) {

    logger = LogFactory.getLog(clazz);

    ResourceBundle resourceBundleTmp;

    try {
      resourceBundleTmp = ResourceBundle.getBundle(clazz.getPackage().getName() + ".texts");
    }

    catch (Exception e) {
      resourceBundleTmp = null;
      logger.error("no texts for class: " + clazz.getName() + ", getText(key) will return the key", e);
    }

    this.clazz = clazz;
    this.fallback = fallback;

    this.resourceBundle = resourceBundleTmp;
  }

  /**
   * Gets the text ({@link String}) for the given key. If the text was not
   * found, the fallback (if it was provided) will be used.
   * 
   * @param key
   *          the key for the desired text
   * 
   * @return the text for the given key, or the key if the text was not found
   */
  public String getText(final String key) {

    String text;

    if (resourceBundle == null) {
      text = "#" + key;
      logger.error("no texts for class: " + clazz.getName() + ", returning the key: #" + key);
    }

    else {

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
    }

    return text;
  }

  /**
   * Format the given arguments using the text for the given key. See
   * {@link #getText(String)} for more details.
   * 
   * @param key
   *          the key for the desired text
   * @param arguments
   *          object(s) to format
   * 
   * @return the formatted text
   * 
   * @see #getText(String)
   */
  public String getText(final String key, final Object... arguments) {
    return MessageFormat.format(getText(key), arguments);
  }

}
