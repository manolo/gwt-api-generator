package com.vaadin.polymer.elemental;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import static jsinterop.annotations.JsPackage.GLOBAL;

@JsType(isNative=true, namespace=GLOBAL, name="Event")
public class Event extends elemental2.dom.Event{
    @JsProperty
    public native String getType();

    @JsProperty
    public native Detail getDetail();

    @JsProperty
    public native EventTarget getTarget();

    @JsType(isNative=true)
    public interface Detail {
    }
}
