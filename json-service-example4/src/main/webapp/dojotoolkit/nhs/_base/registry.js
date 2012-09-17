define([
    'dojo',
    './kernel'
], function(dojo, nhs) {

	// module:
	//		nhs/_base/registry
	// summary:
	//		This module provides an internal registry for the library.

	// create internal registry
	var registry = {};

	nhs.set = function(name, value) {
		registry[name.toLowerCase()] = value;
	};
	nhs.get = function(name) {
		return registry[name.toLowerCase()];
	};
	nhs.has = function(name) {
		return typeof(registry[name.toLowerCase()]) != 'undefined';
	};
	nhs.unset = function(name) {
		delete registry[name.toLowerCase()];
	};

    return nhs;
});
