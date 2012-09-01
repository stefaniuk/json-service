var dojoConfig = {

    packages:[
        {
            name: 'dojo',
            location: './dojo'
        },
        {
            name: 'dijit',
            location: './dijit'
        },
        {
            name: 'dojox',
            location: './dojox'
        },
        {
            name: 'stefaniuk',
            location: './stefaniuk'
        }
    ],

    build : {

        basePath: '../',
        releaseDir: '.',
        releaseName: 'build',

        layers : {
            'stefaniuk/stefaniuk': {
                include: [
                    'dojox/rpc/Service',
                    'dojox/rpc/JsonRPC',
                    'dojox/data/ClientFilter',
                    'dojox/data/JsonRestStore'
                ]
            }
        },

        //optimize: 'closure', // with this option it takes ages to build
        cssOptimize: 'comments',
        layerOptimize: 'closure'
    }

};
