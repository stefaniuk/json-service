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
            name: 'nhs',
            location: './nhs'
        }
    ],

    build : {

        basePath: '../',
        releaseDir: '.',
        releaseName: 'build',

        layers : {
            'nhs/nhs': {
                include: [
                    // TODO
                ]
            }
        },

        //optimize: 'closure', // with this option it takes ages to build
        cssOptimize: 'comments',
        layerOptimize: 'closure'
    }

};
