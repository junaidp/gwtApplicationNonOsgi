/*******************************************************************************
 * Copyright (c) 2017  * Copyright (c) 2015 Sapient Arrow Technologies.
 * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Affero GNU Public License
 * which accompanies this distribution, and is available at
 * https://en.wikipedia.org/wiki/Affero_General_Public_License
 *  
 * Copyright:
 *    Sapient Arrow Technologies llc
 *  
 *  This file is part of the Business Suite software of Sapient Arrowpro.net.
 *  Copyright (C) 2012-2020 Sapient Arrowpro.net
 *
 *  The primary contact email is support@Sapient Arrowpro.net
 *
 *  Version: AGPL
 *
 *  Sapient Arrow Technologies, Sapient Arrow Information Systems, Sapient Arrow along with their domain names,  
 *  etc and the names Acuity, Ingenuity, Derivo, Colander etc are copyright of
 *  Sapient Arrow llc and usage of these without prior permission of the owner is strictly prohibited
 *   
 * The contents of this file may be used under the terms of
 *  the Affero GNU General Public License Version (the "AGPL"),
 *  A copy of the AGPL v2.1 can be obtained from https://en.wikipedia.org/wiki/Affero_General_Public_License
 *  
 *  AGPL, in essence, means that this software requires a commercial license for use in or as a commercial application
 *******************************************************************************/
var _notify_container = false;
var _notifies = [];

var Notify = {

	_container: null,
	_notify: null,
	_timer: null,

	version: "3.0.0",

	options: {
		icon: '', // to be implemented
		caption: '',
		content: '',
		shadow: true,
		width: 'auto',
		height: 'auto',
		style: false, // {background: '', color: ''}
		position: 'right', //right, left
		timeout: 3000,
		keepOpen: false,
		type: 'default' //default, success, alert, info, warning
	},

	init: function(options) {
		this.options = $.extend({}, this.options, options);
		this._build();
		return this;
	},

	_build: function() {
		var that = this, o = this.options;

		this._container = _notify_container || $("<div/>").addClass("notify-container").appendTo('body');
		_notify_container = this._container;

		if (o.content === '' || o.content === undefined) {return false;}

		this._notify = $("<div/>").addClass("notify");

		if (o.type !== 'default') {
			this._notify.addClass(o.type);
		}

		if (o.shadow) {this._notify.addClass("shadow");}
		if (o.style && o.style.background !== undefined) {this._notify.css("background-color", o.style.background);}
		if (o.style && o.style.color !== undefined) {this._notify.css("color", o.style.color);}

		// add Icon
		if (o.icon !== '') {
			var icon = $(o.icon).addClass('notify-icon').appendTo(this._notify);
		}

		// add title
		if (o.caption !== '' && o.caption !== undefined) {
			$("<div/>").addClass("notify-title").html(o.caption).appendTo(this._notify);
		}
		// add content
		if (o.content !== '' && o.content !== undefined) {
			$("<div/>").addClass("notify-text").html(o.content).appendTo(this._notify);
		}

		// add closer
		var closer = $("<span/>").addClass("notify-closer").appendTo(this._notify);
		closer.on('click', function(){
			that.close(0);
		});

		if (o.width !== 'auto') {this._notify.css('min-width', o.width);}
		if (o.height !== 'auto') {this._notify.css('min-height', o.height);}

		this._notify.hide().appendTo(this._container).fadeIn('slow');
		_notifies.push(this._notify);

		if (!o.keepOpen) {
			this.close(o.timeout);
		}

	},

	close: function(timeout) {
		var self = this;

		if(timeout === undefined) {
			return this._hide();
		}

		setTimeout(function() {
			self._hide();
		}, timeout);

		return this;
	},

	_hide: function() {
		var that = this;

		if(this._notify !== undefined) {
			this._notify.fadeOut('slow', function() {
				$(this).remove();
				_notifies.splice(_notifies.indexOf(that._notify), 1);
			});
			return this;
		} else {
			return false;
		}
	},

	closeAll: function() {
		_notifies.forEach(function(notEntry) {
			notEntry.hide('slow', function() {
				notEntry.remove();
				_notifies.splice(_notifies.indexOf(notEntry), 1);
			});
		});
		return this;
	}
};

$.Notify = function(options) {
	return Object.create(Notify).init(options);
};

$.Notify.show = function(message, title, icon) {
	return $.Notify({
		content: message,
		caption: title,
		icon: icon
	});
};

