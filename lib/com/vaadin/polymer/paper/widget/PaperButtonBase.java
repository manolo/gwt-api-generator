/*
 * This code was generated with Vaadin Web Component GWT API Generator, 
 * from paper-button project by The Polymer Authors
 * that is licensed with http://polymer.github.io/LICENSE.txt license.
 */
package com.vaadin.polymer.paper.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.vaadin.polymer.PolymerWidget;

public abstract class PaperButtonBase extends PolymerWidget {
    
    public PaperButtonBase(String tag, SafeHtml safeHtml) {
        super(tag, safeHtml);
    }

    public void setToggles(boolean value) {
        setBooleanAttribute("tobbles", value);
    }
    public boolean getToggles() {
        return getBooleanAttribute("toggles");
    }

    public void setActive(boolean value) {
        setBooleanAttribute("active", value);
    }
    public boolean getActive() {
        return getBooleanAttribute("toggles");
    }
}
