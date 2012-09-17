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
    'dojo/text!./templates/NhsGrid.html',
    'nhs/BorderContainer',
    'dijit/layout/ContentPane',
    'dijit/form/Button',
    'dojox/grid/enhanced/plugins/Filter',
    'dojox/grid/enhanced/plugins/exporter/CSVWriter',
    'dojox/grid/enhanced/plugins/Printer',
    'dojox/grid/enhanced/plugins/Pagination'
], function(dojo, nhs, declare, connect, fx, domGeom, lang, _WidgetTemplate, DataGrid, template) {

return declare('nhs.grid.NhsGrid', [ _WidgetTemplate ], {

    // module:
    //        nhs/grid/NhsGrid
    // summary:
    //        SEE: http://dojotoolkit.org/reference-guide/1.8/dojox/grid/DataGrid.html

    templateString: template,

    structure: null,

    plugins: null,

    store: null,

    grid: null,

    postCreate: function() {

        this.inherited(arguments);

        this._buildGrid();
    },

    startup: function() {

        this.inherited(arguments);

        this.grid.startup();
    },

    _buildGrid: function() {

        this.grid = new DataGrid({
            id: this.id + '_grid',
            structure: this.structure,
            plugins : lang.mixin(lang.clone(this.plugins), {
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
            store: this.store
        });
        this.gridNode.appendChild(this.grid.domNode);
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
    }

});

});
