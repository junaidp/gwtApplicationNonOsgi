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
$.widget( "metro.charm" , {

    version: "3.0.0",

    options: {
        position: "right",
        opacity: 1,
        outside: false,
        timeout: 0,
        duration: 400
    },

    _create: function () {
        var that = this, element = this.element, o = this.options;

        $.each(element.data(), function(key, value){
            if (key in o) {
                try {
                    o[key] = $.parseJSON(value);
                } catch (e) {
                    o[key] = value;
                }
            }
        });


        this._createCharm();

        element.data('charm', this);
    },

    _createCharm: function(){
        var that = this, element = this.element, o = this.options;

        element.addClass("charm").addClass(o.position+"-side").css({opacity: o.opacity}).hide();

        var closer = $("<span/>").addClass("charm-closer").appendTo(element);
        closer.on('click', function(){
            that.close();
        });

        if (o.outside === true) {
            element.on('mouseleave', function(e){
                that.close();
            });
        }
    },

    _showCharm: function(){
        var that = this, element = this.element, o = this.options;
        var size;

        if (o.position === "left" || o.position === "right") {
            size = element.outerWidth();
            if (o.position === "left") {
                element.css({
                    left: -size
                }).show().animate({
                    left: 0
                }, o.duration);
            } else {
                element.css({
                    right: -size
                }).show().animate({
                    right: 0
                }, o.duration);
            }
        } else {
            size = element.outerHeight();
            if (o.position === "top") {
                element.css({
                    top: -size
                }).show().animate({
                    top: 0
                }, o.duration);
            } else {
                element.css({
                    bottom: -size
                }).show().animate({
                    bottom: 0
                }, o.duration);
            }
        }

        if (o.timeout > 0) {
            this._timeout_interval = setInterval(function(){
                if (!element.is(":hover")) {
                    that.close();
                    clearInterval(this._timeout_interval);
                }
            }, o.timeout);
        }
    },

    _hideCharm: function(){
        var that = this, element = this.element, o = this.options;
        var size;

        if (o.position === "left" || o.position === "right") {
            size = element.outerWidth();
            if (o.position === "left") {
                element.animate({
                    left: -size
                }, o.duration, function(){
                    element.hide();
                });
            } else {
                element.animate({
                    right: -size
                }, o.duration, function(){
                    element.hide();
                });
            }
        } else {
            size = element.outerHeight();
            if (o.position === "top") {
                element.animate({
                    top: -size
                }, o.duration, function(){
                    element.hide();
                });
            } else {
                element.animate({
                    bottom: -size
                }, o.duration, function(){
                    element.hide();
                });
            }
        }

        clearInterval(this._timeout_interval);
    },

    open: function(){
        var that = this, element = this.element, o = this.options;

        if (element.data("opened") === true) {
            return;
        }

        element.data("opened", true);
        this._showCharm();
    },

    close: function(){
        var that = this, element = this.element, o = this.options;

        if (element.data("opened") === false) {
            return;
        }

        element.data("opened", false);

        this._hideCharm();
    },

    _destroy: function () {
    },

    _setOption: function ( key, value ) {
        this._super('_setOption', key, value);
    }
});
