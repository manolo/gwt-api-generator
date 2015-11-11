package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
@com.google.gwt.core.client.js.JsType
public interface Event {

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    Detail getDetail();
    
    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    EventTarget getTarget();

    @JsType(isNative=true)
    @com.google.gwt.core.client.js.JsType
    public interface Detail {
    }
}
