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
$.widget( "metro.fluentmenu" , {

    version: "3.0.0",

    options: {
        onSpecialClick: function(a, li){},
        onTabClick: function(a, li){},
        onTabChange: function(a, li){}
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

        this._createMenu();

        element.data('fluentmenu', this);

    },

    _createMenu: function(){
        var that = this, element = this.element, o = this.options;
        var active_tab = $(element.find(".tabs-holder > li.active")[0]);

        this.openTab(active_tab);

        element.on("click", ".tabs-holder > li > a", function(e){
            var a = $(this);
            var li = a.parent('li');
            var result;

            if (li.hasClass('special')) {
                if (typeof o.onSpecialClick === 'function') {
                    o.onSpecialClick(a, li);
                } else {
                    if (typeof window[o.onSpecialClick] === 'function') {
                        window[o.onSpecialClick](a, li);
                    } else {
                        result = eval("(function(){"+o.onSpecialClick+"})");
                        result.call(a, li);
                    }
                }
            } else {
                var panel = $(a.attr('href'));
                that._hidePanels();
                that._showPanel(panel);
                element.find('.tabs-holder > li').removeClass('active');
                a.parent('li').addClass('active');

                if (typeof o.onTabClick === 'function') {
                    o.onTabClick(a, li);
                } else {
                    if (typeof window[o.onTabClick] === 'function') {
                        window[o.onTabClick](a, li);
                    } else {
                        result = eval("(function(){"+o.onTabClick+"})");
                        result.call(a, li);
                    }
                }

                if (typeof o.onTabChange === 'function') {
                    o.onTabChange(a, li);
                } else {
                    if (typeof window[o.onTabChange] === 'function') {
                        window[o.onTabChange](a, li);
                    } else {
                        result = eval("(function(){"+o.onTabChange+"})");
                        result.call(a, li);
                    }
                }
            }
            e.preventDefault();
        });
    },

    _hidePanels: function(){
        this.element.find('.tab-panel').hide();
    },

    _showPanel: function(panel){
        if (panel == undefined) {
            panel = this.element.find('.tabs-holder li.active a').attr('href');
        }
        $(panel).show();
    },

    openTab: function(tab){
        var that = this, element = this.element, o = this.options;
        var target_panel = $(tab.children('a').attr('href'));
        if (target_panel.length === 0) {
            return false;
        }
        this._hidePanels();
        this._showPanel(target_panel);
        element.find('.tabs-holder > li').removeClass('active');
        tab.addClass('active');
    },

    _destroy: function () {
    },

    _setOption: function ( key, value ) {
        this._super('_setOption', key, value);
    }
});
