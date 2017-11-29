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
package com.sapientarrow.gwtapp.client.dashboard.MyDashboard;
//package com.helloworld.client.view.MyDashboard;
//
//import java.util.ArrayList;
//
//import org.moxieapps.gwt.highcharts.client.Chart;
//import org.moxieapps.gwt.highcharts.client.Credits;
//import org.moxieapps.gwt.highcharts.client.Point;
//import org.moxieapps.gwt.highcharts.client.ToolTip;
//import org.moxieapps.gwt.highcharts.client.ToolTipData;
//import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
//import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
//import org.moxieapps.gwt.highcharts.client.labels.DataLabelsFormatter;
//import org.moxieapps.gwt.highcharts.client.labels.PieDataLabels;
//import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
//import org.moxieapps.gwt.highcharts.client.Series;
//
//
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.helloworld.shared.DummyDTO;
//
//public class PieChartDashboard extends VerticalPanel{
//	
//	public PieChartDashboard(){
//		
//		DummyDTO dummyDTO1 = new DummyDTO();
//		dummyDTO1.setX(10);
//		dummyDTO1.setY(20);
//		dummyDTO1.setZ(30);
//		DummyDTO dummyDTO2 = new DummyDTO();
//		dummyDTO2.setX(40);
//		dummyDTO2.setY(50);
//		dummyDTO2.setZ(60);
//		DummyDTO dummyDTO3 = new DummyDTO();
//		dummyDTO3.setX(70);
//		dummyDTO3.setY(80);
//		dummyDTO3.setZ(90);
//		ArrayList<DummyDTO> dummyList = new ArrayList<DummyDTO>();
//		dummyList.add(dummyDTO1);
//		dummyList.add(dummyDTO2);
//		dummyList.add(dummyDTO3);
//		setData(dummyList);
//
//	}
//	
//	public void setData(ArrayList<DummyDTO> dummyList){
//		add(createDummyChart(dummyList));
//		setSpacing(5);
//		setStyleName("grayBackground");
//	}
//
//		
//		public Chart createDummyChart(ArrayList<DummyDTO> strategicList) {  
//			  
//			
//	        
//	        final Chart chart = new Chart().setWidth(350).setHeight(350)
//	                .setType(Series.Type.PIE)  
//	                .setChartTitleText("Dummy Analysis") 
//	                .setPlotBackgroundColor((String) null)  
//	                .setPlotBorderWidth(null);
//		              chart.setCredits(
//		            		     new Credits()
//		            		       .setText("")
//		            		      
//		            		   )  
//	                .setPlotShadow(false)  
//			                .setPiePlotOptions(new PiePlotOptions()  
//			                    .setAllowPointSelect(true)  
//			                    .setPieDataLabels(new PieDataLabels()  
//			                    
//			                     .setConnectorColor("#000000")  
//			                    .setEnabled(true)  
//			                    .setColor("#000000")  
//			                    .setFormatter(new DataLabelsFormatter() {  
//			                        public String format(DataLabelsData dataLabelsData) {  
//			                            return  dataLabelsData.getPointName() + " " + dataLabelsData.getYAsDouble() + " %";  
//			                        }  
//			                    })  
//			                )  
//			            )
//
//	                .setToolTip(new ToolTip()
//	                    .setFormatter(new ToolTipFormatter() {  
//	                        public String format(ToolTipData toolTipData) {  
//	                            return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";  
//	                        }  
//	                    })  
//	                );  
//	      
//		        chart.addSeries(chart.createSeries()  
//	            .setName("Dummy")  
//	            .setPoints(new Point[]{ 
//	            		
//	                new Point("dummy1", 10),  
//	                new Point("dummy2", 20),  
//	                new Point("dummy3", 30),
//	                new Point("dummy4", 40),
//	            })  
//	        );
//	       chart.setSize(370, 170);
//	        return chart;  
//	    }
//		
//
//}
//
