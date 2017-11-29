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
$.widget( "metro.group" , {

    version: "3.0.0",

    options: {
        groupType: 'one-state', // 'multi-state'
        buttonStyle: false,
        onChange: function(index, btn){return true;},
        onChanged: function(index, btn){}
    },

    _create: function () {
        var that = this, element = this.element, o = this.options;
        var result;

        $.each(element.data(), function(key, value){
            if (key in o) {
                try {
                    o[key] = $.parseJSON(value);
                } catch (e) {
                    o[key] = value;
                }
            }
        });

        if (!element.hasClass('group-of-buttons')) {element.addClass('group-of-buttons');}

        var buttons = element.find('.button, .toolbar-button');

        for(var i = 0; i < buttons.length; i++) {
            $(buttons[i]).data('index', i);
        }

        if (o.buttonStyle !== false) {
            buttons.addClass(o.buttonStyle);
        }

        element.on('click', '.button, .toolbar-button', function(){

            var button = $(this), index = button.data('index');

            if (typeof o.onChange === 'function') {
                if (!o.onChange(index, button)) {return false;}
            } else {
                if (typeof window[o.onChange] === 'function') {
                    if (!window[o.onChange](index, button)) {return false;}
                } else {
                    result = eval("(function(){"+o.onChange+"})");
                    if (!result.call(index, button)) {return false;}
                }
            }

            if (o.groupType === 'one-state') {
                buttons.removeClass('active');
                $(this).addClass('active');
            } else  {
                $(this).toggleClass('active');
            }

            if (typeof o.onChanged === 'function') {
                o.onChanged(index, button);
            } else {
                if (typeof window[o.onChanged] === 'function') {
                    window[o.onChanged](index, button);
                } else {
                    result = eval("(function(){"+o.onChanged+"})");
                    result.call(index, button);
                }
            }
        });

        element.data('group', this);

    },

    _destroy: function () {
    },

    _setOption: function ( key, value ) {
        this._super('_setOption', key, value);
    }
});
