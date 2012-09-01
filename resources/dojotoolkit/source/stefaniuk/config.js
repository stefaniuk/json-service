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
            'dojo/dojo': {
                include: [
                    'stefaniuk/main'
                ]
            }
        },

        //optimize: 'closure', // with this option it takes ages to build
        cssOptimize: 'comments',
        layerOptimize: 'closure'
    }

};
