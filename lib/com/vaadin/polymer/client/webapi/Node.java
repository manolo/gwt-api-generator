package com.vaadin.polymer.client.webapi;

import com.google.gwt.core.client.js.JsType;

@JsType
public interface Node extends EventTarget {
    public void appendChild(Object child);
}
