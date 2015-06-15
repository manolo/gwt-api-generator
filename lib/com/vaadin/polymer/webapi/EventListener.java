package com.vaadin.polymer.webapi;

import com.google.gwt.core.client.js.JsType;

@JsType
public interface EventListener {
    void handleEvent(Event event);
}
