package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
@com.google.gwt.core.client.js.JsType
public interface Node extends EventTarget {

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    NodeList getChildNodes();

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    Node getFirstChild();

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    Node getLastChild();

    boolean hasChildNodes();

    void appendChild(Object child);
    void removeChild(Object child);
}
