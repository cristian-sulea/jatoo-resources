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

import java.awt.Image;

import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class ease the work with images, offering also the possibility to have a
 * fallback (very useful for example when a project extends another project).
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 1.1, October 10, 2016
 */
public final class ResourcesImages {

  /** The logger. */
  private final Log logger;

  /** The class that will provide the base name. */
  private final Class<?> clazz;

  /** The fallback to be used when a resource is not found. */
  private final ResourcesImages fallback;

  /**
   * Creates a {@link ResourcesImages} object using as base name the package of
   * the specified class.
   * 
   * @param clazz
   *          the class that will provide the base name
   */
  public ResourcesImages(final Class<?> clazz) {
    this(clazz, (ResourcesImages) null);
  }

  /**
   * Creates a {@link ResourcesImages} object using as base name the package of
   * the specified class. If a resource is not found, the fallback will be used
   * before returning.
   * 
   * @param clazz
   *          the class that will provide the base name
   * @param fallback
   *          the class to be used to create the {@link ResourcesImages} to be
   *          used in case a resource is not found in this one
   */
  public ResourcesImages(final Class<?> clazz, final Class<?> fallback) {
    this(clazz, new ResourcesImages(fallback));
  }

  /**
   * Creates a {@link ResourcesImages} object using as base name the package of
   * the specified class. If a resource is not found, the fallback will be used
   * before returning.
   * 
   * @param clazz
   *          the class that will provide the base name
   * @param fallback
   *          the {@link ResourcesImages} to be used in case a resource is not
   *          found in this one
   */
  public ResourcesImages(final Class<?> clazz, final ResourcesImages fallback) {

    logger = LogFactory.getLog(clazz);

    this.clazz = clazz;
    this.fallback = fallback;
  }

  /**
   * Gets the {@link ImageIcon} with the given name. If the icon was not found,
   * the fallback (if it was provided) will be used.
   * 
   * @param name
   *          the name of the desired icon
   * 
   * @return the icon with the given name, o <code>null</code> if the icon was
   *         not found
   */
  public ImageIcon getImageIcon(final String name) {

    ImageIcon icon;

    try {
      icon = new ImageIcon(clazz.getResource(name));
    }

    catch (Exception e) {

      if (fallback != null) {
        icon = fallback.getImageIcon(name);
      }

      else {
        icon = null;
        logger.error("no image icon #" + name);
      }
    }

    return icon;
  }

  /**
   * Gets the {@link Image} with the given name. If the image was not found, the
   * fallback (if it was provided) will be used.
   * 
   * @param name
   *          the name of the desired image
   * 
   * @return the icon with the given name, o <code>null</code> if the icon was
   *         not found
   */
  public Image getImage(final String name) {

    Image image;

    try {
      image = new ImageIcon(clazz.getResource(name)).getImage();
    }

    catch (Exception e) {

      if (fallback != null) {
        image = fallback.getImage(name);
      }

      else {
        image = null;
        logger.error("no image #" + name);
      }
    }

    return image;
  }

}
