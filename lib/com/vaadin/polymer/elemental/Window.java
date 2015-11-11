package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
@com.google.gwt.core.client.js.JsType
public interface Window {
    
    int requestAnimationFrame(Object callback);
}
