module.exports = {
  customise: function(file, cb) {
    
    //
    // iron-a11y-keys lacks the fire-keys-pressed annotation.
    if (/iron-a11y-keys.html/.test(file.relative)) {
      file.contents = new Buffer(String(file.contents)
        .replace(/(\n.*?_fireKeysPressed:)/, function(m, $1) {
          console.log("WARNING: patching iron-a11y-keys.html event keys-pressed");

           return  "\n" +
                   "/**\n" +
                   " * @event keys-pressed\n" +
                   " * @param {Object} detail\n" +
                   " *  @param {boolean} detail.shift true if shift key is pressed\n" +
                   " *  @param {boolean} detail.ctrl true if ctrl key is pressed\n" +
                   " *  @param {boolean} detail.meta true if meta key is pressed\n" +
                   " *  @param {boolean} detail.alt true if alt key is pressed\n" +
                   " *  @param {String} detail.key the normalized key\n" +
                   " */" + $1;
        })
      );
    }
    
    //
    // Hydrolysis does not support yet new events syntax
    file.contents = new Buffer(
      String(file.contents).replace(/([ \t]+\* +)@event +([\w\-]+) +\{\{(.+)\}\} *detail.*\n/g, function(m, $1, $2, $3) {
        var ret = $1 + "@event " + $2 + "\n" +
        		  $1 + " @param {Object} detail\n";
        
        var detail = $3.split(/ *, */);
        for (i in detail) {
          console.log(detail[i])
          ret += detail[i].replace(/(\w+): *(\w+)/g, $1 + "  @param {$2} detail.$1 \n")
        }

        console.log("WARNING: patching event: " + $2 + " in component " + file.relative);
        return ret;
    }));
    
    cb(null, file);
  }

}
