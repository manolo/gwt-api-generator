package com.vaadin.polymer.elemental;

import jsinterop.annotations.JsType;

import static jsinterop.annotations.JsPackage.GLOBAL;

@JsType(isNative=true, namespace=GLOBAL)
public interface EventTarget {
    void addEventListener(String type, EventListener listener);
}
