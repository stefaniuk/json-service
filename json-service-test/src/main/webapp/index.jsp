<!doctype html>
<html>
<head>
	<title>code4ge-json-service-test</title>
	<script type="text/javascript" src="dojo.js" data-dojo-config="parseOnLoad:false,locale:'en'"></script>
	<script type="text/javascript" src="code4ge-jsf.js"></script>
	<script type="text/javascript">
		code4ge.ready(function() {
			var registry = code4ge.get('jsonrpc.registry');
			registry.add({ url: 'service' });
			var service = registry.get('service');
			service.sync = true;
			service.echo('This is just a test.').addCallback(function(result) {
				console.log('Response: ', result);
			});
		});
	</script>
</head>
<body>
</body>
</html>
