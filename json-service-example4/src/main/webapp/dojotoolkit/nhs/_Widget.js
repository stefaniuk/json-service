define([
    'dojo',
    'nhs/main',
    'dojo/_base/declare',
    'dijit/_WidgetBase',
    'dijit/_CssStateMixin'
], function(dojo, nhs, declare, _WidgetBase, _CssStateMixin) {

return declare('nhs._Widget', [ _WidgetBase, _CssStateMixin ], {

    // module:
    //        nhs/_Widget
    // summary:
    //        Base class for all widgets.
    // features:
    //        CSS state
    //        onReady function
    //        disabled

    disabled: false,

    postCreate: function() {

        this.inherited(arguments);

        // subscribe on ready topic
        dojo.subscribe(nhs.topic.ready, this, 'onReady');
    },

    _setDisabledAttr: function(/*boolean*/value) {

        this.disabled = value;
    },

    _getDisabledAttr: function() {

        return this.disabled;
    },

    onReady: function() {

    }

});

});
