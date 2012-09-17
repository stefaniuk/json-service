<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>json-service-example4</title>
    <style type="text/css">
        @import "dojotoolkit/nhs/themes/nhs/nhs.css";
    </style>
    <script type="text/javascript" src="dojotoolkit/dojo/dojo.js" data-dojo-config="async: 1, isDebug: 1, parseOnLoad: true, locale: 'en'"></script>
    <script type="text/javascript">
        require([
            // main
            'dojo/ready',
            'nhs/main',
            // data
            'nhs/grid/NhsGridMultilevel',
            // html
            'nhs/BorderContainer',
            'dijit/layout/ContentPane'
        ], function(ready, nhs, NhsGridMultilevel) {
            var grid;
            ready(function() {
                // grid structure
                var structure1 = [{
                    cells: [
                        { field: 'name', name: 'Name', datatype: 'string', width: 50, editable: false }
                    ]
                }];
                var structure2 = [{
                    cells: [
                        { field: 'title', name: 'Title', datatype: 'string', width: 50, editable: false }
                    ]
                }];
                var structure3 = [{
                    cells: [
                        { field: 'name', name: 'Name', datatype: 'string', width: 50, editable: false }
                    ]
                }];
                // grid plugins
                var plugins = {
                    indirectSelection: true,
                };
                // grid
                grid = new NhsGridMultilevel({
                    id: 'grid',
                    layout: [
                        {
                            multilevel: true,
                            structure: structure1,
                            plugins : plugins,
                            store: 'chinook/service/artist',
                            title: 'Artist'
                        },
                        {
                            multilevel: true,
                            structure: structure2,
                            plugins : plugins,
                            store: 'chinook/service/album',
                            title: 'Album'
                        },
                        {
                            multilevel: false,
                            structure: structure3,
                            plugins : plugins,
                            store: 'chinook/service/track',
                            title: 'Track'
                        }
                    ]
                });
                dojo.byId('gridNode').appendChild(grid.domNode);
            });
            nhs.ready(function() {
                grid.startup();
            }, 1000);
        });
    </script>
</head>
<body>
    <div id="container" class="container" data-dojo-type="nhs/BorderContainer" data-dojo-props="design:'headline',gutters:true,liveSplitters:false,persist:false">
        <div id="pane_top" class="pane-top" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top'">
        </div>
        <div id="pane_center" class="pane-center" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'center'">
            <div id="gridNode"></div>
        </div>
        <div id="pane_bottom" class="pane-bottom" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'bottom'">
        </div>
    </div>
</body>
</html>
