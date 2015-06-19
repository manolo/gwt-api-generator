package com.vaadin.polymer;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTMLPanel;

public class PolymerWidget extends HTMLPanel {

    public PolymerWidget(String tag, String src, String html) {
        super(tag, html);
        Polymer.ensureCustomElement(getElement(), tag, src);
    }

    public PolymerWidget(String tag, SafeHtml safeHtml) {
        super(tag, safeHtml.asString());
    }

    public void setDisabled(boolean disabled) {
        this.setBooleanAttribute("disabled", disabled);
    }

    public void setDisabled(String disabled) {
        this.setBooleanAttribute("disabled", Boolean.valueOf(disabled));
    }

    public void setBooleanAttribute(String name, boolean value) {
        if(value) {
            this.getElement().setAttribute(name, "");
        } else {
            this.getElement().removeAttribute(name);
        }

    }

    // TODO: Remove this hack for paper-radio-button
    public void setName(String value) {
        getElement().setAttribute("name", value);
    }

    // TODO: Remove this hack for paper-tabs
    public void setLink(String link) {
        getElement().setAttribute("link", link);
    }

    public void setToggle(String toggle) {
        getElement().setAttribute("toggle", toggle);
    }
    public void setNoink(String noink) {
        getElement().setAttribute("noink", noink);
    }
    public void setDismissive(String dismissive) {
        getElement().setAttribute("dismissive", dismissive);
    }
    public void setAffirmative(String affirmative) {
        getElement().setAttribute("affirmative", affirmative);
    }
    public void setAutofocus(String autofocus) {
        getElement().setAttribute("autofocus", autofocus);
    }
    public void setTabindex(int index) {
        getElement().setAttribute("tabindex", "" + index);
    }

    // TODO: Remove this hacks for paper-menu-button
    public void setLabel(String label) {
        getElement().setAttribute("label", label);
    }

    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }
}
