/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2007-2012 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
/*global Ext*/
Ext.override(Ext.form.TextArea, {
  wordWrap : true,
  onRender : function(ct, position) {
    if (!this.el)
    {
      this.defaultAutoCreate = {
        tag : "textarea",
        style : "width:100px;height:60px;",
        autocomplete : "off"
      };
    }
    Ext.form.TextArea.superclass.onRender.call(this, ct, position);
    if (this.grow)
    {
      this.textSizeEl = Ext.DomHelper.append(document.body, {
        tag : "pre",
        cls : "x-form-grow-sizer"
      });
      if (this.preventScrollbars)
      {
        this.el.setStyle("overflow", "hidden");
      }
      this.el.setHeight(this.growMin);
    }
    this.el.setOverflow('auto');
    if (this.wordWrap === false)
    {
      if (!Ext.isIE)
      {
        this.el.set({
          wrap : 'off'
        });
      }
      else
      {
        this.el.dom.wrap = "off";
      }
    }
  }
});
