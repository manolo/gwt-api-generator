package com.vaadin.polymer.webapi;

import com.google.gwt.core.client.js.JsType;

@JsType
public interface EventTarget {
    void addEventListener(String type, EventListener listener);
}
