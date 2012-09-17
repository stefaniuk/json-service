define([
    'dojo',
    'nhs/main',
    'dojo/_base/declare',
    'dojo/_base/connect',
    'dojo/fx',
    'dojo/dom-geometry',
    'dojo/_base/lang',
    'nhs/_WidgetTemplate',
    'nhs/grid/DataGrid',
    'dojo/text!./templates/NhsGridMultilevel.html',
    'nhs/BorderContainer',
    'dijit/layout/ContentPane',
    'dijit/form/Button',
    'dojox/grid/enhanced/plugins/Filter',
    'dojox/grid/enhanced/plugins/exporter/CSVWriter',
    'dojox/grid/enhanced/plugins/Printer',
    'dojox/grid/enhanced/plugins/Pagination'
], function(dojo, nhs, declare, connect, fx, domGeom, lang, _WidgetTemplate, DataGrid, template) {

return declare('nhs.grid.NhsGridMultilevel', [ _WidgetTemplate ], {

    // module:
    //        nhs/grid/NhsGridMultilevel
    // summary:
    //        SEE: http://dojotoolkit.org/reference-guide/1.8/dojox/grid/DataGrid.html

    templateString: template,

    layout: null,

    grid: null,

    postCreate: function() {

        this.inherited(arguments);

        this._buildGrid(0, null);
    },

    startup: function() {

        this.inherited(arguments);

        this.grid.startup();
    },

    _buildGrid: function(id, row) {

        if(this.grid) {
            this.grid.destroy();
        }
        this.grid = new DataGrid({
            id: this.id + '_grid_' + id,
            structure: this.layout[id].structure,
            plugins : lang.mixin(lang.clone(this.layout[id].plugins), {
                filter: {
                    closeFilterbarButton: true,
                    ruleCount: 3
                },
                exporter: true,
                printer: true,
                pagination: {
                    pageSizes: ['50', '100', '200', 'All'],
                    description: true,
                    sizeSwitch: true,
                    pageStepper: true,
                    gotoButton: true,
                    maxPageStep: 5,
                    position: 'bottom'
                }

            }),
            store: this.layout[id].store,
            queryOptions: (row ? { parentId: row.id } : null)
        });
        this.gridNode.appendChild(this.grid.domNode);

        if(row) {
            this.pathNode.innerHTML = this.layout[id - 1].title + ' (' + row.name + ') / ' + this.layout[id].title;
        }
        else {
            this.pathNode.innerHTML = this.layout[id].title;
        }

        if(this.layout[id].multilevel) {
            var self = this;
            connect.connect(self.grid, 'onRowDblClick', function() {
                var row = self.grid.getItem(self.grid.focus.rowIndex);
                var cell = self.grid.focus.cell;
                console.log(row, cell);
                fx.slideTo({
                    node: self.grid.domNode,
                    duration: 500,
                    left: (domGeom.getMarginBox(self.grid.domNode).l - 2000).toString(),
                    units: 'px',
                    onEnd: function() {
                        self.gridNode.innerHTML = '';
                        self._buildGrid(id + 1, row);
                        self.grid.startup();
                    }
                }).play();
            });
        }
    },

    onRefreshClick: function() {

        alert('refresh');
    },

    onAddClick: function() {

        alert('add');
    },

    onRemoveClick: function() {

        alert('remove');
    },

    onSaveClick: function() {

        alert('save');
    },

    onPrintClick: function() {

        alert('print');
    },

    onExportCsvClick: function() {

        alert('export-csv');
    },

    onBackClick: function() {

        alert('backward');
    },

    onForwardClick: function() {

        alert('forward');
    }

});

});
