package com.vaadin.polymer;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.vaadin.polymer.elemental.Function;

public abstract class Polymer {

    private static Set<String> urlImported = new HashSet<>();

    /**
     * Ensures that the tagName has been registered, otherwise injects
     * the appropriate &lt;import&gt; tag in the document header.
     *
     * It loads the component from following the convention:
     * bower_components/tagName/tagName.html
     */
    public static void ensureTag(String tagName) {
        ensureTag(tagName, null);
    }

    /**
     * Ensures that the tagName has been registered, otherwise injects
     * the appropriate &lt;import&gt; tag in the document header.
     *
     * @param tagName
     * @param url component path relative to bower_components folder.
     *        if null it imports bower_components/tagName/tagName.html
     */
    public static void ensureTag(final String tagName, String src) {
        ensureTag(tagName, src, null, null);
    }

    /**
     * Ensures that the tagName has been registered, otherwise injects
     * the appropriate &lt;import&gt; tag in the document header and
     * notifies via callbacks whether the component is available.
     *
     * @param tagName
     * @param url component path relative to bower_components folder.
     *        if null it imports bower_components/tagName/tagName.html
     * @param onload
     * @param onerror
     */
    public static void ensureTag(final String tagName, String src, Function onload, Function onerror) {
        if (src == null) {
            src = tagName + "/" + tagName + ".html";
        }
        if (!src.startsWith("http")) {
            if (!src.startsWith("bower_components")) {
                src = "bower_components/" + src;
            }
            src = GWT.getModuleBaseForStaticFiles() + src;
        }
        importHref(src, onload, onerror);
    }

    /**
     * Inserts the appropriate &lt;import&gt; of a component given by url.
     * @deprecated use {@link #importHref(String, Function, Function)} instead
     */
    public static void ensureHTMLImport(final String href) {
        importHref(href, null, null);
    }

    /**
     * Inserts the appropriate &lt;import&gt; of a component given by url
     * and notifies via callback whether the component is available.
     */
    public static void importHref(final String href, Function ok, Function err) {
        if (!urlImported.contains(href)) {
            urlImported.add(href);
            importHrefImpl(href, ok, err);
        }
    }

    /**
     * Returns a new instance of the Element. It loads the web component
     * from the bower_components/src url if it was not loaded yet.
     */
    public static <T> T createElement(String tagName, String... imports) {
        @SuppressWarnings("unchecked")
        final T e = (T)Document.get().createElement(tagName);
        if (!isRegisteredElement(e)) {
            for (String src : imports) {
                ensureTag(tagName, src);
            }
        }
        return e;
    }

    /**
     * Returns a new instance of the Element. It loads the web component
     * from the bower_components/tagName/tagName.html url if not loaded yet.
     */
    public static <T> T createElement(String tagName) {
        return createElement(tagName, new String[]{});
    }

    /**
     * Returns the JsInterop instance of Document
     */
    public static com.vaadin.polymer.elemental.Document getDocument() {
        return (com.vaadin.polymer.elemental.Document)Document.get();
    }

    /**
     * Check whether a certain custom element has been registered.
     */
    private native static boolean isRegisteredElement(Object e)
    /*-{
        return !!e && e.constructor !== $wnd.HTMLElement;
    }-*/;

    /**
     * Dynamically import a link and monitors when it has been loaded.
     *
     * This could be done via Polymer importHref, but the method needs a custom element
     * instance to be run.
     */
    private native static Element importHrefImpl(String href, Function onload, Function onerror)
    /*-{
        var l = $doc.createElement('link');
        l.rel = 'import';
        l.href = href;
        if (onload) {
          l.onload = function() {
             onload.@com.vaadin.polymer.elemental.Function::call(*)();
          }
        }
        if (onerror) {
          l.onerror = function() {
              onerror.@com.vaadin.polymer.elemental.Function::call(*)();
          }
        }
        $doc.head.appendChild(l);
        return l;
    }-*/;
}
