package com.vaadin.polymer.paper.widget;

import com.vaadin.polymer.PolymerWidget;

/**
 * Customized class to add behaviors not supported by hydrolysis yet.
 */
public abstract class PaperButtonBase extends PolymerWidget {

    public PaperButtonBase(String tag, String src, String html) {
        super(tag, src, html);
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
