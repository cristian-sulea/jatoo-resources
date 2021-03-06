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

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class ease the work with texts, offering also the possibility to have a
 * fallback (very useful for example when a project extends another project).
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 3.4, October 12, 2016
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
   * the specified class. If a resource is not found, the fallback will be used
   * before returning.
   * 
   * @param clazz
   *          the class that will provide the base name
   * @param fallback
   *          the {@link ResourcesTexts} to be used in case a resource is not
   *          found in this one
   * @param language
   *          the language for which texts are desired
   */
  public ResourcesTexts(final Class<?> clazz, final ResourcesTexts fallback, final String language) {

    logger = LogFactory.getLog(clazz);

    ResourceBundle resourceBundleTmp;

    try {
      if (language == null) {
        resourceBundleTmp = ResourceBundle.getBundle(clazz.getPackage().getName() + ".texts");
      } else {
        resourceBundleTmp = ResourceBundle.getBundle(clazz.getPackage().getName() + ".texts", new Locale(language));
      }
    }

    catch (Exception e) {
      resourceBundleTmp = null;
      if (fallback == null) {
        logger.error("no texts for class: " + clazz.getName() + ", getText(key) will return the key", e);
      }
    }

    this.clazz = clazz;
    this.fallback = fallback;

    this.resourceBundle = resourceBundleTmp;
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
    this(clazz, fallback, null);
  }

  /**
   * Creates a {@link ResourcesTexts} object using as base name the package of
   * the specified class. If a resource is not found, the fallback will be used
   * before returning.
   * 
   * @param clazz
   *          the class that will provide the base name
   * @param fallback
   *          the class to be used to create the fallback {@link ResourcesTexts}
   *          to be used in case a resource is not found in this one
   * @param language
   *          the language for which texts are desired
   */
  public ResourcesTexts(final Class<?> clazz, final Class<?> fallback, String language) {
    this(clazz, new ResourcesTexts(fallback, language), language);
  }

  /**
   * Creates a {@link ResourcesTexts} object using as base name the package of
   * the specified class. If a resource is not found, the fallback will be used
   * before returning.
   * 
   * @param clazz
   *          the class that will provide the base name
   * @param fallback
   *          the class to be used to create the fallback {@link ResourcesTexts}
   *          to be used in case a resource is not found in this one
   */
  public ResourcesTexts(final Class<?> clazz, final Class<?> fallback) {
    this(clazz, new ResourcesTexts(fallback), null);
  }

  /**
   * Creates a {@link ResourcesTexts} object using as base name the package of
   * the specified class.
   * 
   * @param clazz
   *          the class that will provide the base name
   * @param language
   *          the language for which texts are desired
   */
  public ResourcesTexts(final Class<?> clazz, String language) {
    this(clazz, (ResourcesTexts) null, language);
  }

  /**
   * Creates a {@link ResourcesTexts} object using as base name the package of
   * the specified class.
   * 
   * @param clazz
   *          the class that will provide the base name
   */
  public ResourcesTexts(final Class<?> clazz) {
    this(clazz, (ResourcesTexts) null, null);
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
      if (fallback != null) {
        text = fallback.getText(key);
      } else {
        text = "#" + key;
        logger.error("no texts for class: " + clazz.getName() + ", returning the key: #" + key);
      }
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
