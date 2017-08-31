package com.vaadin.polymer.elemental;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import elemental2.dom.EventInit;
import elemental2.dom.EventTarget;

import static jsinterop.annotations.JsPackage.GLOBAL;

@JsType(isNative=true, namespace=GLOBAL, name="Event")
public class Event extends elemental2.dom.Event{

    public Event(String type, EventInit eventInitDict) {
        super(type, eventInitDict);
    }

    public Event(String type) {
        super(type);
    }

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
