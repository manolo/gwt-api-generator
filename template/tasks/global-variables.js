var args = require('minimist')(process.argv.slice(2));

var ns = args.groupId || "com.vaadin.polymer";
var currentDir = process.cwd() + '/';

var clientDirBase = currentDir + (args.javaDir || 'src/main/java/').replace(/,+$/, "");
var publicDirBase = currentDir + (args.resourcesDir || 'src/main/resources/').replace(/,+$/, "");

var clientDir = clientDirBase + '/' + ns.replace(/\./g,'/') + "/";
var publicDir = publicDirBase + '/' + ns.replace(/\./g, '/') + "/public/";

module.exports = {
  ns: ns,
  artifactId: args.artifactId || "elements",
  currentDir: currentDir,
  clientDirBase: clientDirBase,
  publicDirBase: publicDirBase,
  clientDir: clientDir,
  publicDir: publicDir,
  bowerDir: publicDir + "bower_components/",
  bowerPackages: (args.package || 'PolymerElements/paper-elements').split(/[, ]+/)
};



