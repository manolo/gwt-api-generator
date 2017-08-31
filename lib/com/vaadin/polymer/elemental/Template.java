package com.vaadin.polymer.elemental;

import static jsinterop.annotations.JsPackage.GLOBAL;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import elemental2.dom.HTMLElement;

import com.google.gwt.core.client.JsArray;

@JsType(isNative=true, namespace=GLOBAL, name="HTMLElement")
public class Template extends HTMLElement {
    @JsProperty public native String getIs();
    @JsProperty public native void setIs(String value);

    @JsProperty public native String getAs();
    @JsProperty public native void setAs(String value);

    @JsProperty public native String getIdexAs();
    @JsProperty public native void setIndexAs(String value);

    @JsProperty public native JsArray<?> getItems();
    @JsProperty public native void setItems(JsArray<?> items);

    @JsProperty public native void setFilter(Function<Boolean, ?> items);

    @JsProperty public native void setIf(String value);
}