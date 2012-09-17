define([
    'dojo',
    './kernel',
    'dojox/collections/ArrayList'
], function(dojo, nhs, ArrayList) {

	// module:
	//		nhs/_base/ready
	// summary:
	//		This module provides an enhancement to the dojo.ready feature.

	// set topic to be published when toolkit is loaded
	nhs.topic.ready = 'ready';

	// tasks to run on ready
	var tasks = (function() {

        var t = {}, queue = [];

		t.add = function(func, order) {
			queue.push({
				func: func,
				order: order || 0
			});
		};

		t.run = function() {
			var al = new ArrayList(queue);
			al.sort(function(a, b) { return a.order - b.order; });
			al.forEach(function(task) {
				task.func();
			});
		};

		return t;

	})();

	// final initialisation
	dojo.ready(function() {
		// try to call initialisation code as late as possible
		dojo.ready(function() {
			// add initialisation code that has to be run last
			tasks.add(function() {
				// use claro as a base theme
				if(!dojo.hasClass(dojo.body(), 'claro')) {
					dojo.addClass(dojo.body(), 'claro');
				}
				// display invisable nodes
				dojo.query('.display-onready').removeClass('display-onready');
				// publishe an event
				dojo.publish(nhs.topic.ready);
			}, 999);
			tasks.run();
		});
	});

	// enhanced dojo.ready
	nhs.ready = function(func, order) {
		tasks.add(func, order);
	};

    return nhs;
});
