package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
@com.google.gwt.core.client.js.JsType
public interface NodeList {

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    int getLength();

    <T extends Node> T item(int index);
}
