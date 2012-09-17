define([
    'dojo',
    'nhs/main',
    'dojo/_base/declare',
    'dojox/data/ClientFilter',
    'dojox/data/JsonRestStore',
    'nhs/data/JsonRpc'
], function(dojo, nhs, declare, ClientFilter, JsonRestStore, JsonRpc) {

var JsonRpcStore = declare('nhs.data.JsonRpcStore', null, {

    // module:
    //        nhs/data/JsonRpcStore
    // summary:
    //        TODO

    _jsonrpc: null,

    _noTrailingSlash: true,

    constructor: function(args) {

        var smd = {
            SMDVersion: '2.0',
            additionalParameters: 'false',
            contentType: 'application/json',
            envelope: 'JSON-RPC-2.0',
            transport: 'POST',
            services: {
                select: {
                    parameters: [{ type: 'object' }],
                    returns: { type: 'any' }
                },
                update: {
                    parameters: [{ type: 'string' }, { type: 'object' }],
                    returns: { type: 'object' }
                },
                insert: {
                    parameters: [{ type: 'object' }],
                    returns: { type: 'object' }
                },
                'delete': {
                    parameters: [{ type: 'string' }],
                    returns: { type: 'object' }
                }
            }
        };

        if(dojo.isString(args)) {
            var pos = args.lastIndexOf('/');
            var obj = {
                url: args.substring(0, pos),
                target: args.substring(pos + 1)
            }
            args = obj;
        }

        if(args.smd) {
            dojo.mixin(args.smd, smd);
        }
        else {
            args.smd = smd;
        }

        if(typeof(args.noTrailingSlash) != 'undefined') {
            this._noTrailingSlash = args.noTrailingSlash;
        }

        this._jsonrpc = new JsonRpc({
            url: args.url + '/' + args.target,
            smd: args.smd,
            noTrailingSlash: this._noTrailingSlash
        });

        var self = this;
        var service = function(query, data) {
            for(var name in data) {
                // amend undefined to null
                if(typeof(data[name]) == 'undefined') {
                    data[name] = null;
                }
            }
            var handle = self._jsonrpc.select(data);
            handle.then(function(result) {
                self.onEvent('select', result);
            });
            return handle;
        }
        service['put'] = function(key, data) {
            var handle = self._jsonrpc.update(key, dojo.fromJson(data));
            handle.then(function(result) {
                self.onEvent('update', result);
            });
            return handle;
        }
        service['post'] = function(key, data) {
            var handle = self._jsonrpc.insert(dojo.fromJson(data));
            handle.then(function(result) {
                self.onEvent('insert', result);
            });
            return handle;
        }
        service['delete'] = function(key) {
            var handle = self._jsonrpc['delete'](key);
            handle.then(function(result) {
                self.onEvent('delete', result);
            });
            return handle;
        }

        dojo.mixin(this, new JsonRestStore({
            target: args.target,
            service: service
        }));
    },

    onEvent: function(event, result) {
    }

});

/* create a registry of stores over JSON-RPC protocol */
JsonRpcStore.prototype.registry = {

    map: {},

    add: function(args) {
        if(dojo.isString(args)) {
            this.map[args] = new JsonRpcStore(args);
        }
        else {
            this.map[args.url + '/' + args.target] = new JsonRpcStore({
                url: args.url,
                target: args.target,
                smd: args.smd,
                noTrailingSlash: typeof(args.noTrailingSlash) != 'undefined' ? args.noTrailingSlash : true
            });
        }
    },

    get: function(args) {
        var url = dojo.isObject(args) ? args.url + '/' + args.target : args;
        if(!this.map[url]) {
            this.add(args);
        }
        return this.map[url];
    }

};
nhs.set('jsonrpcstore.registry', JsonRpcStore.prototype.registry);

return JsonRpcStore;

});
