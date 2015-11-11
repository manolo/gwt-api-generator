package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
@com.google.gwt.core.client.js.JsType
public interface Document extends Node {
    EventTarget getElementById(String id);
    <T> T createElement(String tag);
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty HTMLElement getHead();
    @com.google.gwt.core.client.js.JsProperty
    @JsProperty HTMLElement getBody();
}
