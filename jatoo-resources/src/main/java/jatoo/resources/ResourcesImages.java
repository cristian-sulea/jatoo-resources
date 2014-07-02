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
public class ResourcesImages {

  private final Log logger;

  private final Class<?> clazz;
  private final ResourcesImages fallback;

  public ResourcesImages(final Class<?> clazz) {
    this(clazz, null);
  }

  public ResourcesImages(final Class<?> clazz, final ResourcesImages fallback) {

    logger = LogFactory.getLog(clazz);

    this.clazz = clazz;
    this.fallback = fallback;
  }

  public ImageIcon getImageIcon(String name) {

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
