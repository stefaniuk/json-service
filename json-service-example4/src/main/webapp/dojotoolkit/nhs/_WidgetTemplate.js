define([
    'dojo/_base/declare',
    'nhs/_Widget',
    'dijit/_TemplatedMixin',
    'dijit/_WidgetsInTemplateMixin'
], function(declare, _Widget, _TemplatedMixin, _WidgetsInTemplateMixin) {

return declare('nhs._WidgetTemplate', [ _Widget, _TemplatedMixin, _WidgetsInTemplateMixin ], {

    // module:
    //        nhs/_WidgetTemplate
    // summary:
    //        Base class for all templated widgets.
    // features:
    //        widget template
    //        widgets in template
    //        name, tabindex

    widgetsInTemplate: true,

    name: '',

    tabindex: 0

});

});
