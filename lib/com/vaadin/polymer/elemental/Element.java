package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace=GLOBAL)
@com.google.gwt.core.client.js.JsType
public interface Element extends Node {

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    String getInnerHTML();
    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    void setInnerHTML(String s);

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    String getTextContent();
    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    void setTextContent(String s);

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    DOMTokenList getClassList();

    @JsProperty
    @com.google.gwt.core.client.js.JsProperty
    CSSStyleDeclaration getStyle();

    void setAttribute(String name, Object value);
    String getAttribute(String name);
    boolean hasAttribute(String name);
    void removeAttribute(String name);

    <T extends Element> T querySelector(String selector);
    NodeList querySelectorAll(String selector);
}
