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

import java.awt.Image;

import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class ease the work with images, offering also the possibility to have a
 * fallback (very useful for example when a project extends another project).
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 1.0, July 2, 2014
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
    this(clazz, null);
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
