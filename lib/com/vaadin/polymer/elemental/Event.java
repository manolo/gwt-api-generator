package com.vaadin.polymer.elemental;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import static jsinterop.annotations.JsPackage.GLOBAL;

@JsType(isNative=true, namespace=GLOBAL)
public interface Event {
    @JsProperty
    String getType();

    @JsProperty
    Detail getDetail();

    @JsProperty
    EventTarget getTarget();

    @JsType(isNative=true)
    public interface Detail {
    }
}
