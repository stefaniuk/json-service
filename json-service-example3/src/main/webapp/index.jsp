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

                // create JSON-RPC services
                var artistService = new Service('chinook/service/artist');
                var albumService = new Service('chinook/service/album');
                var trackService = new Service('chinook/service/track');

                var count = 1;
                var artistId = 87; // Guns N' Roses
                artistService.list().then(function(artists) {
                    albumService.list(artists[artistId].id).then(function(albums) {
                        for(var i in albums) {
                            var album = albums[i];
                            (function(album) { // this closure is requried to save a reference to the current album
                                trackService.list(album.id).then(function(tracks) {
                                    for(var j in tracks) {
                                        var track = tracks[j];
                                        console.log(
                                            count++, ' - ',
                                            artists[artistId].name, ' - ',
                                            album.title, ' - ',
                                            track.name
                                        );
                                    }
                                });
                            })(album); // end: closure
                        }
                    });
                });

            });

        });
    </script>
</head>
<body>
    Open the Console tab in Firebug to see results!
</body>
</html>
