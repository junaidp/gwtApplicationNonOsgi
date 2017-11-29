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
$.widget("metro.accordion", {

    version: "3.0.0",

    options: {
        closeAny: false,
        speed: 'fast',
        onFrameOpen: function(frame){return true;},
        onFrameOpened: function(frame){},
        onFrameClose: function(frame){return true;},
        onFrameClosed: function(frame){}
    },

    init: function(){
        var that = this, element = this.element;

        element.on('click', '.heading', function(e){
            var frame = $(this).parent();

            if (frame.hasClass('disabled')) {return false;}

            if  (!frame.hasClass('active')) {
                that._openFrame(frame);
            } else {
                that._closeFrame(frame);
            }

            e.preventDefault();
            e.stopPropagation();
        });
    },

    _closeAllFrames: function(){
        var that = this;
        var frames = this.element.children('.frame.active');
        $.each(frames, function(){
            that._closeFrame($(this));
        });
    },

    _openFrame: function(frame){
        var o = this.options;
        var content = frame.children('.content');
        var result;

        if (typeof o.onFrameOpen === 'function') {
            if (!o.onFrameOpen(frame)) {return false;}
        } else {
            if (typeof window[o.onFrameOpen] === 'function') {
                if (!window[o.onFrameOpen](frame)) {return false;}
            } else {
                result = eval("(function(){"+o.onFrameOpen+"})");
                if (!result.call(frame)) {return false;}
            }
        }

        if (o.closeAny) {this._closeAllFrames();}

        content.slideDown(o.speed);
        frame.addClass('active');

        if (typeof o.onFrameOpened === 'function') {
            o.onFrameOpened(frame);
        } else {
            if (typeof window[o.onFrameOpened] === 'function') {
                window[o.onFrameOpened](frame);
            } else {
                result = eval("(function(){"+o.onFrameOpened+"})");
                result.call(frame);
            }
        }
    },

    _closeFrame: function(frame){
        var o = this.options;
        var content = frame.children('.content');
        var result;

        if (typeof o.onFrameClose === 'function') {
            if (!o.onFrameClose(frame)) {return false;}
        } else {
            if (typeof window[o.onFrameClose] === 'function') {
                if (!window[o.onFrameClose](frame)) {return false;}
            } else {
                result = eval("(function(){"+o.onFrameClose+"})");
                if (!result.call(frame)) {return false;}
            }
        }

        content.slideUp(o.speed,function(){
            frame.removeClass("active");
        });

        if (typeof o.onFrameClosed === 'function') {
            o.onFrameClosed(frame);
        } else {
            if (typeof window[o.onFrameClosed] === 'function') {
                window[o.onFrameClosed](frame);
            } else {
                result = eval("(function(){"+o.onFrameClosed+"})");
                result.call(frame);
            }
        }
    },

    _create: function(){
        var that = this, o = this.options, element = this.element;

        $.each(this.element.data(), function(key, value){
            if (key in o) {
                try {
                    o[key] = $.parseJSON(value);
                } catch (e) {
                    o[key] = value;
                }
            }
        });

        that.init();
        element.data('accordion', this);

    },

    _destroy: function(){
    },

    _setOption: function(key, value){
        this._super('_setOption', key, value);
    }
});
