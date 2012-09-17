define([
    'dojo'
], function(dojo) {

	// module:
	//		nhs/_base/kernel
	// summary:
	//		This module is the foundational module of the nhs boot sequence; it defines the nhs object.

    // create nhs
    var c = nhs = {
        topic: {}
    };

	// set base URL
	c.baseUrl = '/';

	// set resources URL
	var pos = dojo.baseUrl.lastIndexOf('/dojo/');
	c.resourcesUrl = dojo.baseUrl.substring(0, pos);

	return nhs;
});
