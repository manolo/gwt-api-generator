var _ = require('lodash');

module.exports = {
  marked: require('marked').setOptions({
    gfm: true,
    tables: true,
    breaks: true,
    pedantic: true,
    sanitize: false,
    smartLists: true,
    smartypants: true
  }),
  javaKeywords: ['for', 'switch'], // TODO: if it's necessary add other keywords as well
  findBehavior: function(name) {
    for (var i = 0; i < global.parsed.length; i++) {
      if (this.className(global.parsed[i].is) == this.className(name)) {
        return global.parsed[i];
      }
    }
  },
  isBehavior: function(item) {
    return ((item && item.type) || this.type) == 'behavior';
  },
  getNestedBehaviors: function(item, name) {
    var _this = this;
    var properties = [];
    var events = [];

    var behavior = this.findBehavior(name)
    if (behavior) {
      events = behavior.events;

      behavior.properties.forEach(function(prop) {
        prop.isBehavior = true;
        prop.behavior = _this.className(item.is);
        properties.push(prop);
      });

      if(behavior.behaviors) {
        behavior.behaviors.forEach(function(b) {
          var nestedBehaviors = _this.getNestedBehaviors(item, b);
          properties = _.union(properties, nestedBehaviors.properties);
          events = _.union(events, nestedBehaviors.events);
        });
      }
    }

    return {properties: properties, events: events};
  },
  className: function (name) {
    return this.camelCase(name || this['name']);
  },
  elementClassName: function(name) {
    return this.className(name) + (this.isBehavior() ? '' : 'Element');
  },
  baseClassName: function () {
    var _this = this;
    // Always extend native HTMLElement
    var e = ['HTMLElement'];
    if (this.behaviors && this.behaviors.length) {
      this.behaviors.forEach(function(name){
        // CoreResizable -> CoreResizableElement, core-input -> CoreInputElment
        if (name && name.match(/[A-Z\-]/)) {
          if (_this.findBehavior(name)) {
            e.push(_this.camelCase(name));
          } else {
            console.log("NOT FOUND: " + name + " " + _this.camelCase(name));
          }
        } else {
          // input -> HTMLInputElement, table -> HTMLTableElement
          e.push('HTML' + _this.elementClassName(name));
        }
      });
    }
    return "extends " + e.join(',');
  },
  baseWidgetName: function () {
    var e = this['extends'];
    if (e && e.match(/[A-Z\-]/)) {
      // CoreResizable -> CoreResizable, core-drop-downBase -> CoreDropdownBase
      return this.camelCase(e);
    } else {
      return 'PolymerWidget';
    }
  },
  camelCase: function(s) {
    return (s || '').replace(/^Polymer\./, '').replace(/[^\-\w\.]/g,'').replace(/(\b|-|\.)\w/g, function (m) {
      return m.toUpperCase().replace(/[-\.]/g, '');
    });
  },
  computeMethodName: function(s) {
    return (s || '').replace(/-\w/g, function (m) {
      return m.toUpperCase().replace(/-/, '');
    });
  },
  computeName: function(s) {
    return (s || '').replace(/[^\w\-\.:]/g, '');
  },
  computeType: function(t) {
    if (/^string$/i.test(t)) return 'String';
    if (/^boolean/i.test(t)) return 'boolean';
    if (/^array/i.test(t)) return 'JsArray';
    if (/^element/i.test(t)) return 'Element';
    if (/^number/i.test(t)) return 'double';
    if (/^function/i.test(t)) return 'Function';
    return "JavaScriptObject";
  },
  removeDuplicates: function(arr, prop) {
    for (var i = 0; i < arr.length; i++) {
      for (var j = arr.length - 1; j > i; j--) {
        if (arr[i][prop] == arr[j][prop]) {
          arr.splice(j, 1);
        }
      }
    }
  },
  removePrivateApi: function(arr, prop) {
    for (var i = arr.length - 1; i >= 0; i--) {
      if (/^(_.*|ready|created)$/.test(arr[i][prop])) {
        arr.splice(i, 1);
      }
    }
  },
  hasItems: function(array) {
    return array && array.length > 0;
  },
  hasEvents: function() {
    return this.hasItems(this.events);
  },
  hasAttributes: function() {
    return this.hasItems(this.attributes);
  },
  hasProperties: function() {
    return this.hasItems(this.properties);
  },
  hasParams: function() {
    return this.hasItems(this.params);
  },
  capitalizeFirstLetter: function(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
  },
  computeGetterWithPrefix: function(item) {
    var name = item.name.replace(/^detail\./,'');
    // replaced isXXX methods with getXXX temporary because of bug in JsInterop
    //var prefix = /^boolean/i.test(item.type) ? 'is' : 'get';
    var prefix = 'get';
    if (this.startsWith(name, prefix)) {
      return name;
    } else {
      return prefix + this.capitalizeFirstLetter(this.computeMethodName(name));
    }
  },
  computeSetterWithPrefix: function(item) {
    return 'set' + this.capitalizeFirstLetter(this.computeMethodName(item.name));
  },
  startsWith: function (str, substr){
    return str.indexOf(substr) === 0;
  },
  typedParamsString: function(method) {
    var result = [];
    if (method.params) {
      method.params.forEach(function(param) {
        var type = this.computeType(param.type);
        result.push(type + ' ' + this.computeMethodName(param.name));
      }, this);
    }
    return result.join(', ');
  },
  paramsString: function(method) {
    var result = [];
    if (method.params) {
      method.params.forEach(function(param) {
        result.push(this.computeMethodName(param.name));
      }, this);
    }
    return result.join(', ');
  },
  extraSetter: function(attribute) {
    var type = this.computeType(attribute.type);
    if (type === 'String') {
      return '';
    } else if (type === 'boolean') {
      return 'public void ' + this.computeSetterWithPrefix(attribute) + '(String ' + attribute.name + ') {\n' +
        '        setBooleanAttribute("' + attribute.name + '", true);\n' +
        '    }';
    } else {
      return 'public void ' + this.computeSetterWithPrefix(attribute) + '(String ' + this.computeMethodName(attribute.name) + ') {\n' +
        '        getElement().setAttribute("' + attribute.name + '", ' + this.computeMethodName(attribute.name) + ');\n' +
        '    }';
    }
  },
  getDescription: function(spaces, o) {
    o = o || this;
    var desc = o.description || o.desc || '';
    desc = this.marked(desc);
    return (desc).trim().split('\n').join('\n' + spaces + '* ').replace(/\*\//g, "* /");
  },
  disclaimer: function() {
    var projectName = this.bowerData.name || "unknown";
    var projectLicense = this.bowerData.license || "unknown";

    var projectAuthors = this.bowerData.authors || this.bowerData.author;
    if (projectAuthors && projectAuthors.map) {
      projectAuthors = projectAuthors.map(function(author) {
        return author.name ? author.name : author;
      }).toString();
    }
    projectAuthors = projectAuthors || "unknown author";

    return "/*\n" +
    " * This code was generated with Vaadin Web Component GWT API Generator, \n" +
    " * from " + projectName + " project by " + projectAuthors + "\n" +
    " * that is licensed with " + projectLicense + " license.\n" +
    " */";
  },
  j2s: function(json, msg) {
    msg = msg || '';
    var cache = [];
    console.log(msg + JSON.stringify(json, function(key, value) {
        if (typeof value === 'object' && value !== null) {
            if (cache.indexOf(value) !== -1) {
                return;
            }
            cache.push(value);
        }
        return value;
    }));
  }
};
