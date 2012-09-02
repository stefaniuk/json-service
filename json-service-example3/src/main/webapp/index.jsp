<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>json-service-example3</title>
    <script type="text/javascript" src="dojotoolkit/dojo/dojo.js" data-dojo-config="async: 1, isDebug: 1, locale: 'en'"></script>
    <script type="text/javascript">
        require([
            'dojo/ready',
            'dojox/rpc/Service',
            'dojox/rpc/JsonRPC' // JsonRPC has to be loaded too
        ], function(ready, Service) {
            ready(function() {

                // create JSON-RPC service
                service = new Service('controller/jsonrpc/');

                // call JSON-RPC methods
                service.add(12345, 54321).then(function(result) {
                    console.log('(add)', result);
                });
                service.subtract(12345, 54321).then(function(result) {
                    console.log('(subtract)', result);
                });
                service.multiple(12345, 54321).then(function(result) {
                    console.log('(multiple)', result);
                });
                service.divide(12345, 54321).then(function(result) {
                    console.log('(divide)', result);
                });

            });

        });
    </script>
</head>
<body>
    Open the Console tab in Firebug to see results!
</body>
</html>
