package com.vaadin.polymer;

import static jsinterop.annotations.JsPackage.GLOBAL;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

/**
 * PolymerElement is a class over the HTMLElement of elemental2 adding
 * some utility methods present in Polymer Elements, or missing in the
 * elemental2 API.
 */
@JsType(isNative=true, namespace = GLOBAL, name = "HTMLElement")
public class PolymerElement extends HTMLElement {

    /** Properties and methods added by Polymer API **/
    public HTMLElement root;
    public native void debounce(String name, PolymerFunction<?, ?> f, int timeout);
    /**
     * Polymer’s custom property shim evaluates and applies custom property values once at element creation time.
     * In order to have an element (and its subtree) re- evaluate custom property values due to dynamic changes
     * such as application of CSS classes, etc., call updateStyles().
     */
    public native void updateStyles();

    /** Missing properties in elemental2.dom.Element **/
    public String textContent;
}
