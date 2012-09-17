define([
    'dojo/_base/declare',
    'nhs/_Widget',
    'dijit/layout/BorderContainer'
], function(declare, _Widget, BorderContainer) {

return declare('nhs.BorderContainer', [ _Widget, BorderContainer ], {

	onReady: function() {

		this.resize();
	}

});

});
