define([
    'dojo',
    'nhs/main',
    'dojo/_base/declare',
    'nhs/_Widget',
	'dojox/grid/EnhancedGrid',
	'dojox/grid/enhanced/plugins/IndirectSelection',
	'dojox/grid/enhanced/plugins/Pagination',
    'dojox/grid/enhanced/plugins/Filter',
    'dojox/grid/enhanced/plugins/Selector',
	'nhs/data/JsonRpcStore'
], function(dojo, nhs, declare, _Widget, EnhancedGrid) {

return declare('nhs.grid.DataGrid', [ _Widget, EnhancedGrid ], {

    _tmpStore: null,

	postMixInProperties: function() {

		if(dojo.isString(this.store)) {
			var registry = nhs.get('jsonrpcstore.registry');
			this.store = registry.get(this.store);
            this._tmpStore = this.store;
		}

		this.inherited(arguments);
	},

	postCreate: function() {

        this._setStore(this._tmpStore);

        this.inherited(arguments);
	},

    buildRendering: function() {

        this.inherited(arguments);

        // IE<7 hack
        if(dojo.isIE <= 7) {
            var connects = this.focus._connects
            for(var i=0; i < connects.length; i++) {
                if(connects[i][1] == '_onFetchComplete') {
                    // remove a reference to _FocusManager._delayedCellFocus
                    delete connects[i];
                }
            }
        }
    },

	_render: function() {

		if(this.domNode.parentNode) {
			this.scroller.init(this.get('rowCount'), this.keepRows, this.rowsPerPage);
            // IE hack
            if(!dojo.isIE) {
                // with the following line included grid fails to render the content
                this.prerender();
            }
            this._fetch(0, true);
		}
	},

    _onFetchComplete: function() {

        this.inherited(arguments);

        // remove focus from grid
        this.focus._blurHeader();
    }

});

});
