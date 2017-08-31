package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import elemental2.dom.HTMLElement;

@JsType(isNative=true, namespace=GLOBAL, name="HTMLElement")
public class PolymerHTMLElement extends HTMLElement {
    /**
     * Polymer’s custom property shim evaluates and applies custom property values once at element creation time.
     * In order to have an element (and its subtree) re- evaluate custom property values due to dynamic changes
     * such as application of CSS classes, etc., call updateStyles().
     */
    public native void updateStyles();

    @JsProperty public native HTMLElement getRoot();

    public native void debounce(String name, Function f, int timeout);
}