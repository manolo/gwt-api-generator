package com.vaadin.polymer.webapi;

import com.google.gwt.core.client.js.JsType;

@JsType
public interface Node extends EventTarget {
    public void appendChild(Object child);
}
