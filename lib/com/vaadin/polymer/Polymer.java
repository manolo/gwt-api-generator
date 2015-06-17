package com.vaadin.polymer;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Timer;
import com.vaadin.polymer.elemental.Function;

public abstract class Polymer {

    private static Set<String> urlImported = new HashSet<>();

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
    private static void ensureTag(final String tagName, String src, Function onload, Function onerror) {
        if (src == null) {
            src = tagName + "/" + tagName + ".html";
        }
        importHref(src, onload, onerror);
    }

    /**
     * Inserts the appropriate &lt;import&gt; of a component given by url.
     *
     * @param href either an absolute url or a path relative to bower_components folder.
     */
    public static void importHref(String href) {
        importHref(href, null, null);
    }

    /**
     * Inserts the appropriate &lt;import&gt; of a component given by url.
     *
     * @param href either an absolute url or a path relative to bower_components folder.
     * @param ok callback to run in case of success
     */
    public static void importHref(String href, Function ok) {
        importHref(href, ok, null);
    }

    /**
     * Inserts the appropriate &lt;import&gt; of a component given by url.
     *
     * @param href either an absolute url or a path relative to bower_components folder.
     * @param ok callback to run in case of success
     * @param err callback to run in case of failure
     */
    public static void importHref(String href, Function ok, Function err) {
        if (!href.startsWith("http")) {
            if (!href.startsWith("bower_components")) {
                href = "bower_components/" + href;
            }
            href = GWT.getModuleBaseForStaticFiles() + href;
        }
        if (!urlImported.contains(href)) {
            urlImported.add(href);
            importHrefImpl(href, ok, err);
        }
    }

    /**
     * Returns a new instance of the Element. It loads the web component
     * from the bower_components/src url if it was not loaded yet.
     */
    public static <T> T createElement(final String tagName, final String... imports) {
        @SuppressWarnings("unchecked")
        final T e = (T)Document.get().createElement(tagName);
        ensureCustomElement(e, tagName, imports);
        return e;
    }

    public static <T> void ensureCustomElement(final T elem, String tagName,
            String... imports) {

        if (isRegisteredElement(elem)) {
            return;
        }

        // Delay this so as the developer gets an early version of the element and
        // can assign properties soon.
        new Timer() {
            public void run() {
                // We need to remove ownProperties from the element when it's not
                // registered because a bug in Polymer 1.0.x
                // https://github.com/Polymer/polymer/issues/1882
                saveProperties((Element)elem);
            }
        }.schedule(0);

        //
        new Timer() {
            public void run() {
                // Restore saved ownProperties
                restoreProperties((Element)elem);
            }
        }.schedule(5);

        imports = fixImports(imports);
        for (String src : fixImports(imports)) {
            ensureTag(tagName, src, null, null);
        }
    }

    private static String[] fixImports(String[] imports) {
        // FIXME(manolo): figure out a better way to do this
        return imports.length > 1 && imports[0].matches(".*[^\\w]import[^\\w].*") ?
            new String[] {"iron-icons/iron-icons.html", imports[0]} :
            imports;
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
        return !!e && e.constructor !== $wnd.HTMLElement && e.constructor != $wnd.HTMLUnknownElement;
    }-*/;

    /**
     * Dynamically import a link and monitors when it has been loaded.
     *
     * This could be done via Polymer importHref, but the method needs a custom element
     * instance to be run.
     */
    private native static Element importHrefImpl(String href, Function onload, Function onerror)
    /*-{
        // TODO(manolo): use Polymer.Base.importHref when it works in FF
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

    /**
     * Restore all properties saved previously to the element was
     * registered.
     */
    private static native void restoreProperties(Element e)
    /*-{
        if (e && e.__o) {
            var id = setInterval(function() {
                if (@com.vaadin.polymer.Polymer::isRegisteredElement(*)(e)) {
                    clearInterval(id);
                    for (i in e.__o) {
                        e[i] = e.__o[i];
                    }
                    delete e.__o;
                }
            }, 0);
        }
    }-*/;

    /**
     * Read all element properties and save in a JS object in the element,
     * so as we can restore then once the element is registered.
     */
    private static native boolean saveProperties(Element e)
    /*-{
        if (!@com.vaadin.polymer.Polymer::isRegisteredElement(*)(e)) {
            var o = {};
            for (i in e) {
                if (e.hasOwnProperty(i)) {
                    b = true;
                    o[i] = e[i];
                    delete(e[i]);
                    e.__o = o;
                }
            }
        }
    }-*/;
}
