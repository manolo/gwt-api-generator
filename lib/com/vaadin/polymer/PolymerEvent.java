package com.vaadin.polymer;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import elemental2.dom.EventInit;
import elemental2.dom.EventTarget;

import static jsinterop.annotations.JsPackage.GLOBAL;


/**
 * PolymerEvent is a class over the Element implementation of elemental2 adding
 * some properties present in Polymer Events, or missing in the elemental2 API.
 */
@JsType(isNative=true, namespace=GLOBAL, name="Event")
public class PolymerEvent extends elemental2.dom.Event {

    public PolymerEvent(String type, EventInit eventInitDict) {
        super(type, eventInitDict);
    }

    public PolymerEvent(String type) {
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

