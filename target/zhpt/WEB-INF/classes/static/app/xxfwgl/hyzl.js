var hyzl=(function ($) {
		var vehilist;
		var onbusy= 0;
		var timeAgain;
		var yycls=0;//营运车辆数
		var zpjksl=0;
		$(function () {

			timeAgain=setInterval(function(){
				zpjksl=0;
				initAll();
				initThree();
				initTwo();
				initOne();
				focusMonitor();
				faultMonitor();
			},300000);
			
			initAll();
			initThree();
			initTwo();
			initOne();
			focusMonitor();
			faultMonitor();

			/* 抓拍监控 -> START */
			$('#captureMonitorDialog').on('hidden.bs.modal', function (e) {
				console.info('do something...')
			});
			$('#captureMonitorDialog').on('shown.bs.modal', function (e) {
				$('#focusMonitor .tw-list').on('click', function (dd) {
					var vehicle=dd.currentTarget.getElementsByTagName("span")[0].innerHTML;
					menuBarSkip({sTitle: '设备维修', mTitle:'抓拍监控'}, function (item) {
//						console.log("111111111111111=",item)
						parent.$('#' + item.name).attr('src', encodeURI(item.href + '?type=1&vehicle='+vehicle));
					})
				});
				$('#malfunctionMonitor .tw-list').on('click', function (dd) {
					var vehicle=dd.currentTarget.getElementsByTagName("span")[0].innerHTML;
					menuBarSkip({sTitle: '设备维修', mTitle:'抓拍监控'}, function (item) {
						parent.$('#' + item.name).attr('src', item.href + '?type=2&vehicle='+vehicle);
					})
				});
			});
			$('#captureMonitorTab')
				.find('li').on('click', function () {
				$(this).addClass('active').siblings('.active').removeClass('active');
				$('#captureMonitorTabPanel').find('.panel-body[data-title=' + $(this).attr('data-skip') + ']').addClass('active').siblings('.active').removeClass('active');
			}).end();
//			$('<p class="tw-list"><span>浙AT2343</span><span>2019-02-27 16:30:24</span><span>杭州东站</span></p>').data('vehicle', '浙A12345').appendTo('#focusMonitor');

			/**
			 * 菜单栏跳转
			 * @param skip
			 * @param cb 
			 */
			function menuBarSkip(skip, cb) {
				var serverMenuItems = parent.$('#serverMenu .ip-menuItem');
				for (var i = 0; i < serverMenuItems.length; i++) {
					if ($(serverMenuItems[i]).text().trim() === skip.sTitle) {
						parent.$(serverMenuItems[i]).find('.ip-menuTitle').click();
						var menuItems = parent.$('#menuListBar .ip-menuItem');
						for (var j = 0; j < menuItems.length; j++) {
							if ($(menuItems[j]).text().trim() === skip.mTitle) {
								var item = parent.$(menuItems[j]).data('database');
								parent.$(menuItems[j]).find('.ip-menuTitle').click();
								cb(item);
							}
						}
					}
				}
			}
			/* 抓拍监控 -> END */


			/*运营总览 跳转 综合查询菜单栏 */
			$('.ip-highlight-red').on('click',function(){
				var id = $(this).attr("id");
				var _parent = parent.$(window.parent.document);
				console.log(id)
//				if(id == "gzsbcx"){
//					_parent.find('.ip-menuItem [data-skip="czgzyfx"]').trigger("click");
//				}else{
					_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
					setTimeout(function(){
						console.log("进了没有")
							_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
							_parent.find('.ip-menuItem [data-skip="'+id+'"]').trigger("click");
						
					},100);
//				}
			});
			$('.ip-highlight-green').on('click',function(){
				var id = $(this).attr("id");
				var _parent = parent.$(window.parent.document);
				_parent.find('.ip-menuItem [data-skip="czgzyfx"]').trigger("click");
			});
			
			
			/*黄色跳转*/
			$('.ip-highlight-orange').on('click',function(){
				var id = $(this).attr("id");
				var _parent = parent.$(window.parent.document);
				console.log(id)
				if(id == "hyyxqk_clzxs" || id == "hyyxqk_clzks" ){
					_parent.find('.ip-menuItem [data-skip="clfb"]').trigger("click");
				}
			});
			
			//网上服务
			$('.ip-highlight').on('click',function(){
				var id = $(this).attr("id");
				var _parent = parent.$(window.parent.document);
				console.log(id)
				if(id == "zlc" || id =="zsc" || id =="zdd" || id =="zje"){
					_parent.find('.ip-menuItem [data-skip="wsfw"]').trigger("click");
					setTimeout(function(){
						_parent.find('#menuListBar .ip-menuItem:nth-of-type(1) .ip-menuTitle').trigger("click");
						_parent.find('.ip-menuItem [data-skip="yybg"]').trigger("click");
					},100);
				}
			});
			
			//投诉
			$('.ip-count').on('click',function(){
				var id = $(this).attr("id");
				var _parent = parent.$(window.parent.document);
				console.log(id)
				if(id == "tszs" ||id =="tsyjj"){
					console.log("coming")
					_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
					setTimeout(function(){
						_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
						_parent.find('.ip-menuItem [data-skip="tscx"]').trigger("click");
					},100);
				}
			});
			

			$('.scrollbar-macosx').scrollbar();
			$('#hyzl-gstype .ip-toolItem').on('click', function () {
				var name = $(this).attr('name');
				var box = $(this).parents('.panel-heading').next();
				$(this).hide().siblings().show();
				box.find('[name='+name+']').show().siblings('[name='+$(this).siblings().attr('name')+']').hide();
			});
			
			$('#hyzl-jsytype .ip-toolItem').on('click', function () {
				var name = $(this).attr('name');
				var box = $(this).parents('.panel-heading').next();
				$(this).hide().siblings().show();
				box.find('[name='+name+']').show().siblings('[name='+$(this).siblings().attr('name')+']').hide();
			});
		});

		function initAll(){
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/qyjk",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data)
	 				if(data.vehilist == undefined){
//	 					layer.msg("数据异常");
	 				}else{
	 					console.log(data.vehilist);
	 					vehilist = data.vehilist;

	 					//行业运行情况
	 					initHyyxqk();
	 					
	 					//行业总览 最上面一栏
	 					var ons=0,offs=0,busys=0,emptys=0;
	 					for (var i = 0; i < vehilist.length; i++) {
	 						var ve  = vehilist[i];
	 						if(ve.longi < 0){continue;}
	 						if (ve.lati == null || "" == ve.lati
	 								|| 0==ve.lati
	 								|| ""==ve.longi
	 								|| 0 ==ve.longi 
	 						) {
	 							offs++;
	 						} else {
	 							if (ve.onoffstate=="1") {
	 								if (ve.carStatus=="0") {
	 									emptys++;
	 								} else {
	 									busys++;
	 								}
	 								ons++;
	 							} else if (ve.onoffstate=="0") {
	 								offs++;
	 							}
	 						}
	 					}
	 					$('.clzs').html(vehilist.length);
	 					$('.zaix').html(ons);
	 					
	 					$('.szl').html((new Number(busys/ons)*100).toFixed(2)+"%");
	 					$('#hyyxqk_clzkl').html((new Number(busys/ons)*100).toFixed(2)+"%");
	 					
	 					onbusy=busys;
	 					init();
	 					setTimeout(function(){
	 						initFive();
	 					},200);
	 				}
	 			}
			});
		}
		
		
		
		function initFive(){
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/five",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data)
	 				if(data == null){
	 				}else{
	 					//行业运营总览
	 					var all = data.all[0];
	 					console.log(all)
	 					$("#zlc").html(new Number(all.lc/yycls).toFixed(2));
	 					$("#zsc").html(new Number(all.sc/yycls).toFixed(2));
	 					$("#zdd").html(all.count);
	 					$("#zje").html(new Number(all.je).toFixed(2));
	 					
	 					$(".zyycs").html(all.count);

	 					initYyzk(data);
	 				}
	 			}
			});
		}
		

		//其他总览
		function initThree(){
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/three",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data);
	 				if(data == null){
//	 					layer.msg("数据异常");
	 				}else{
	 					//企业
	 					var comp = data.compzl;
	 					var textcomp = "";
	 					for(var i=0;i<comp.length;i++){
	 						if(i>4) break;
	 						textcomp +='<tr><td>'+comp[i].name+'</td><td>'+comp[i].VEHICLE_SUM+'</td></tr>'
	 					}
	 					$("#compzl tbody").html(textcomp);
	 					$("#qycx").html(comp.length);

	 					//车辆
	 					var cl = data.clzl;
	 					var textcl = "";
	 					var clzs = 0;
	 					for(var i=0;i<cl.length;i++){
	 						if(i>4) break;
	 						textcl +='<tr><td>'+cl[i].BRAND+'</td><td>'+cl[i].MODEL+'</td><td>'+cl[i].count+'</td></tr>'
	 					}
	 					for(var i=0;i<cl.length;i++){
	 						clzs += cl[i].count;
	 					}

	 					$("#vehizl tbody").html(textcl);
	 					$("#clcx").html(clzs);

	 					var textclcount = "";
	 					var clcount = data.clzlcount;
	 					for(var i=0;i<clcount.length;i++){
	 						textclcount +='<tr><td>'+clcount[i].ti+'</td><td>'+clcount[i].count+'</td></tr>'
	 						clzs += cl[i].count;
	 					}
	 					$("#vehizl1 tbody").html(textclcount);

	 					//驾驶员
	 					var per = data.perzl;
	 					var textper = "";
	 					var perzs = 0;
	 					for(var i=0;i<per.length;i++){
	 						textper +='<tr><td>'+per[i].ti+'</td><td>'+per[i].count+'</td></tr>';
	 						perzs += per[i].count;
	 					}
	 					$("#perzl tbody").html(textper);
	 					$("#jsycx").html(perzs);
	 					
	 					//驾驶员年龄
	 					var per1 = data.perzl1;
	 					var textper1 = '<tr><td>50岁以下</td><td>'+per1[0].l1+'</td></tr>'+
	 					'<tr><td>50(含)至65岁</td><td>'+per1[0].l2+'</td></tr>'+
	 					'<tr><td>65岁(含)以上</td><td>'+per1[0].l3+'</td></tr>';
	 					$("#perzl1 tbody").html(textper1);
	 					
	 					//故障状况
	 					var gzzl_count = 0;
	 					var gz = data.isu;
	 					var textgz = "";
	 					for(var i=0;i<gz.length;i++){
	 						gzzl_count += gz[i].count;
	 					}
	 					for(var i=0;i<gz.length;i++){
	 						if(i>4) break;
	 						var x = i;
	 						x++;
	 						textgz +='<tr><th class="ip-ranking">'+x+'</th><td>'+gz[i].BA_NAME+'</td><td width="90">'+gz[i].count+'</td></tr>';
	 					}

	 					$("#gzsbcx").html(gzzl_count);
	 					$("#gzzl tbody").html(textgz);
	 					
	 					
	 					/* 业户 车辆  驾驶员 车载设备 故障设备  总览跳转*/
	 					
	 					/* 业户 */
	 					$('#compzl tbody tr').on('click',function(){
	 						var _this = this;
	 						var _parent = parent.$(window.parent.document);
	 						_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
	 						setTimeout(function(){
	 							var name = $($(_this).children()[0]).html();
	 							_parent.find('#zl_comp').val(name);
	 							console.log(name)
	 							_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
	 							_parent.find('.ip-menuItem [data-skip="qycx"]').trigger("click");
 								_parent.find('iframe#qycx').attr('src','app/zhcx/qycx.html');
	 						},100);
	 					});
	 					
	 					/* 车载设备*/
	 					$('#czzl tbody tr').on('click',function(){
	 						var _this = this;
	 						var _parent = parent.$(window.parent.document);
	 						_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
	 						setTimeout(function(){
	 							var name = $($(_this).children()[1]).html();
	 							_parent.find('#zl_mac').val(name);
	 							console.log(name)
	 							_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
	 							_parent.find('.ip-menuItem [data-skip="czsbcx"]').trigger("click");
	 							_parent.find('iframe#czsbcx').attr('src','app/zhcx/czsbcx.html');
	 						},100);
	 					});
	 					
	 					/* 故障设备*/
	 					$('#gzzl tbody tr').on('click',function(){
	 						var _this = this;
	 						var _parent = parent.$(window.parent.document);
	 						_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
	 						setTimeout(function(){
	 							var name = $($(_this).children()[1]).html();
	 							_parent.find('#zl_error').val(name);
//	 							console.log(name)
	 							_parent.find('#menuListBar .ip-menuItem:nth-of-type(5) .ip-menuTitle').trigger("click");
	 							_parent.find('.ip-menuItem [data-skip="czsbgzcx"]').trigger("click");
	 							_parent.find('iframe#czsbgzcx').attr('src','app/czsbgzfx/czsbgzcx.html');
	 						},100);
	 					});
	 					

	 					/* 驾驶员*/
	 					$('#perzl tbody tr').on('click',function(){
	 						var _this = this;
	 						var _parent = parent.$(window.parent.document);
	 						_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
	 						setTimeout(function(){
	 							var name = $($(_this).children()[0]).html();
	 							_parent.find('#zl_driver').val(name);
//	 							console.log(name)
	 							_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
	 							_parent.find('.ip-menuItem [data-skip="jsycx"]').trigger("click");
	 							_parent.find('iframe#jsycx').attr('src','app/zhcx/jsycx.html');
	 						},100);
	 					});
	 					
//	 					/* 车辆 */
	 					$('#vehizl tbody tr').on('click',function(){
	 						var _this = this;
	 						var _parent = parent.$(window.parent.document);
	 						_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
	 						setTimeout(function(){
	 							var name = $($(_this).children()[0]).html();
	 							_parent.find('#zl_vehi_type').val(name);
	 							console.log(name)
	 							_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
	 							_parent.find('.ip-menuItem [data-skip="clcx"]').trigger("click");
	 							_parent.find('iframe#clcx').attr('src','app/zhcx/clcx.html');
	 						},100);
	 					});
	 					
	 					/* 车辆1*/
	 					$('#vehizl1 tbody tr').on('click',function(){
	 						var _this = this;
	 						var _parent = parent.$(window.parent.document);
	 						_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
	 						setTimeout(function(){
	 							var name = $($(_this).children()[0]).html();
	 							_parent.find('#zl_vehi_age').val(name);
	 							console.log(name)
	 							_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
	 							_parent.find('.ip-menuItem [data-skip="clcx"]').trigger("click");
	 							_parent.find('iframe#clcx').attr('src','app/zhcx/clcx.html');
	 						},100);
	 					});
	 					

	 				}
	 			}
			});
		}
		
		function init(){
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/zero",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data)
 					var yycl = data.yycl[0].count;
	 				yycls = yycl;
 					$('.yycl').html(yycl);
	 			}
			});
		}
		
		function initOne(){
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/one",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data)
 					//车载设备状况
 					var czzl_count = 0;
 					var cz = data.mac;
 					var textcz = "";
 					for(var i=0;i<cz.length;i++){
 						czzl_count += cz[i].count;
 					}
 					for(var i=0;i<cz.length;i++){
 						if(i>4) break;
 						var x = i;
 						x++;
 						textcz +='<tr><th class="ip-ranking">'+x+'</th><td>'+cz[i].MDT_SUB_TYPE+'</td><td width="90">'+cz[i].count+'</td></tr>';
 					}

 					$("#czsbcx").html(czzl_count);
 					$("#czzl tbody").html(textcz);
	 			}
			});
		}
		
		function initTwo(){
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/two",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data)
	 				if(data == null){
	 				}else{
	 					var tsdata = [],tstitle= [];
	 					var zbdata = [],zbtitle= [];
	 					
	 					var ts = data.ts;
	 					var tj = data.tj;
	 					var tsAll = 0;
	 					for(var i=0;i< ts.length;i++){
	 						tstitle.push(ts[i].tt);
	 						var t = {};
	 						t.value = ts[i].count;
	 						t.name = ts[i].tt
	 						tsdata.push(t);
	 						tsAll+=ts[i].count;
	 					}
	 					
	 					var yjj=0,wjj=0;
	 					for(var i=0;i< tj.length;i++){
	 						zbtitle.push(tj[i].tt);
	 						if(tj[i].tt === "已归档" ){
	 							yjj+=tj[i].count;
	 						}else{
	 							wjj+=tj[i].count;
	 						}
	 						zbdata.push({
	 							name: tj[i].tt,
	 							type: 'bar',
	 							barWidth: '20',
	 							barGap: '1px',
	 							label: {normal: {position: 'right', show: true}},
	 							data: [tj[i].count]
	 						});
	 					}
//	 					console.log(yjj+"@"+wjj)
	 					var zbAll = new Number(yjj/(yjj+wjj)*100).toFixed(2);
	 					$("#tszs").html(tsAll+"次");
	 					$("#tsyjj").html(zbAll+"%");
	 					
	 					var hyzlEcharts_tsxx = echarts.init(document.getElementById('hyzl-echarts_tsxxpm'));
	 					hyzlEcharts_tsxx.setOption({
	 						color: ['#1391fd', '#4575b4', '#74add1', '#d48265', '#6e7074', '#749f83', '#c4ccd3', '#fdae61', '#f46d43', '#d73027', '#a50026'],
	 						legend: {
	 							orient: 'horizontal',
	 							y: '75%',
	 							x: 'center',
	 							data: tstitle
//	 								['拒载', '绕路', '不整洁', '态度差', '无车可用']
	 						},
	 						 tooltip : {
	 					        trigger: 'item',
	 					        formatter: "{b} : {c} ({d}%)"
	 					    },
	 						series: [
	 							{
	 								name: '访问来源',
	 								type: 'pie',
	 								radius: '70%',
	 								center: ['50%', '35%'],
	 								data: tsdata
//	 									[
//	 									{value: 24, name: '拒载'},
//	 									{value: 24, name: '绕路'},
//	 									{value: 24, name: '不整洁'},
//	 									{value: 24, name: '态度差'},
//	 									{value: 76, name: '无车可用'}
//	 								]
	 									,
//	 								itemStyle: {
//	 									emphasis: {
//	 										shadowBlur: 10,
//	 										shadowOffsetX: 0,
//	 										shadowColor: 'rgba(0, 0, 0, 0.5)'
//	 									}
//	 								},
//	 								labelLine: {
//	 									show: false
//	 								},
//	 								label: {
//	 									formatter: '{b}：{d}%',
//	 									position: 'inside'
//	 								}
	 									label: {
	 						                normal: {
	 						                    show: false
	 						                },
	 						                emphasis: {
	 						                    show: false
	 						                }
	 						            },
	 						            lableLine: {
	 						                normal: {
	 						                    show: false
	 						                },
	 						                emphasis: {
	 						                    show: false
	 						                }
	 						            }
	 							}
	 						]
	 					});
	 					var hyzlEcharts_yjjwtzb = echarts.init(document.getElementById('hyzl-echarts_yjjwtzb'));
	 					hyzlEcharts_yjjwtzb.setOption({
	 						color: ['#1391fd', '#4575b4', '#74add1', '#d48265', '#6e7074', '#749f83', '#c4ccd3', '#fdae61', '#f46d43', '#d73027', '#a50026'],
	 						tooltip: {
	 							trigger: 'axis',
	 							axisPointer: {
	 								type: 'shadow'
	 							}
	 						},
	 						legend: {
	 							data: zbtitle
//	 								['已解决', '未解决']
	 						},
	 						grid: {
	 							left: '10px',
	 							right: '30px',
	 							bottom: '20px'
	 						},
	 						xAxis: {
	 							type: 'value',
	 							boundaryGap: [0, 0.01]
	 						},
	 						yAxis: {
	 							type: 'category',
	 							data: 
//	 								zbtitle
	 							['']
	 						},
	 						series: zbdata
//	 							[
//	 							{
//	 								name: '已解决',
//	 								type: 'bar',
//	 								barWidth: '30',
//	 								barGap: '1px',
//	 								label: {normal: {position: 'right', show: true}},
//	 								data: [182]
//	 							},
//	 							{
//	 								name: '未解决',
//	 								type: 'bar',
//	 								barWidth: '30',
//	 								label: {normal: {position: 'right', show: true}},
//	 								data: [193]
//	 							}
//	 						]
	 					});
	 				}
	 			}
			});
//			$(window).resize(function () {
//				hyzlEcharts_tsxx.resize();
//				hyzlEcharts_yjjwtzb.resize();
//			});
		}
		
		//重点抓拍车辆
		function focusMonitor(){
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/focusMonitor",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data);
	 				if(data == null){
//	 					layer.msg("数据异常");
	 				}else{
	 					//重点抓拍车辆
	 					var focus = data.zdzp;
	 					var text = "";
	 					for(var i=0;i<focus.length;i++){
	 						text +='<p class="tw-list"><span>'+focus[i].VEHICLE_NO+'</span><span>'+(focus[i].GET_TIME==null?"":(new Date(focus[i].GET_TIME)).Format("yyyy-MM-dd hh:mm:ss"))+'</span><span>'+focus[i].GET_LOCATION+'</span></p>'
	 					}
	 					$("#focusMonitor").html(text);
	 					zpjksl += focus.length;
	 					$('.zpjk').html(zpjksl+"辆");
	 					/* 重点抓拍车辆跳转*/
//	 					$('#perzl tbody tr').on('click',function(){
//	 						var _this = this;
//	 						var _parent = parent.$(window.parent.document);
//	 						_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
//	 						setTimeout(function(){
//	 							var name = $($(_this).children()[0]).html();
//	 							_parent.find('#zl_driver').val(name);
//	 							_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
//	 							_parent.find('.ip-menuItem [data-skip="jsycx"]').trigger("click");
//	 							_parent.find('iframe#jsycx').attr('src','app/zhcx/jsycx.html');
//	 						},100);
//	 					});
	 					
	 				}
	 			}
			});
		}
		
		//故障车辆抓拍
		function faultMonitor(){
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/faultMonitor",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data);
	 				if(data == null){
//	 					layer.msg("数据异常");
	 				}else{
	 					//重点抓拍车辆
	 					var fault = data.gzzp;
	 					var text = "";
	 					for(var i=0;i<fault.length;i++){
	 						text +='<p class="tw-list"><span>'+fault[i].VEHI_NO+'</span><span>'+(fault[i].SUBSCRIBE_TIME==null?"":(new Date(fault[i].SUBSCRIBE_TIME)).Format("yyyy-MM-dd hh:mm:ss"))+'</span><span>'+fault[i].SUBSCRIBE_PLACE+'</span></p>'
	 					}
	 					$("#malfunctionMonitor").html(text);
	 					zpjksl += fault.length;
	 					$('.zpjk').html(zpjksl+"辆");
	 					/* 重点抓拍车辆跳转*/
//	 					$('#perzl tbody tr').on('click',function(){
//	 						var _this = this;
//	 						var _parent = parent.$(window.parent.document);
//	 						_parent.find('.ip-menuItem [data-skip="zhtjyfx"]').trigger("click");
//	 						setTimeout(function(){
//	 							var name = $($(_this).children()[0]).html();
//	 							_parent.find('#zl_driver').val(name);
//	 							_parent.find('#menuListBar .ip-menuItem:nth-of-type(4) .ip-menuTitle').trigger("click");
//	 							_parent.find('.ip-menuItem [data-skip="jsycx"]').trigger("click");
//	 							_parent.find('iframe#jsycx').attr('src','app/zhcx/jsycx.html');
//	 						},100);
//	 					});
	 					
	 				}
	 			}
			});
		}
		
		function initYyzk(obj){
			var ddcomp = [],jecomp = [];
			var dddata = [],jedata = [];
			for(var i = 0 ;i < obj.order.length; i++){
				ddcomp.push(obj.order[i].ti);
				jecomp.push(obj.sum[i].ti);

				dddata.push(obj.order[i].count);
				jedata.push(obj.sum[i].je);
			}


			var hyzlEcharts_ddzl = echarts.init(document.getElementById('hyzl-echarts_ddzl'));
			hyzlEcharts_ddzl.setOption({
				color: ['#1391fd', '#4575b4', '#74add1', '#d48265', '#6e7074', '#749f83', '#c4ccd3', '#fdae61', '#f46d43', '#d73027', '#a50026'],
				tooltip: {
					trigger: 'axis',
					axisPointer: {
						type: 'shadow'
					}
				},
				grid: {
					top: '70px',
					left: '50px',
					right: '40px',
					bottom: '20px'
				},
				xAxis: [
					{
						name: '小时',
						type: 'category',
						data: ddcomp,
						axisTick: {
							alignWithLabel: true
						}
					}
				],
				yAxis: [
					{	name: '订单',
						axisLabel: {
				            formatter: function (value, index) {
				                return (value/1000)+"千";
				            }
				        },
//						type: 'value'
					}
				],
				series: [
					{
						name: '订单',
						type: 'bar',
						barWidth: '60%',
						markPoint: {
							data: [
								{type: 'max', name: '最大值'}
							]
						},
						data: dddata
					}
				]
			});
			var hyzlEcharts_jyzl = echarts.init(document.getElementById('hyzl-echarts_jyzl'));
			hyzlEcharts_jyzl.setOption({
				color: ['#1391fd', '#4575b4', '#74add1', '#d48265', '#6e7074', '#749f83', '#c4ccd3', '#fdae61', '#f46d43', '#d73027', '#a50026'],
				tooltip: {
					trigger: 'axis',
					axisPointer: {
						type: 'shadow'
					}
				},
				grid: {
					top: '70px',
					left: '50px',
					right: '40px',
					bottom: '20px'
				},
				xAxis: [
					{
						name: '小时',
						type: 'category',
						data: jecomp,
						axisTick: {
							alignWithLabel: true
						}
					}
				],
				yAxis: [
					{	name: '金额',
						axisLabel: {
				            formatter: function (value, index) {
				                return (value/10000)+"万";
				            }
				        },
//						type: 'value'
					}
				],
				series: [
					{
						name: '金额',
						type: 'bar',
						barWidth: '60%',
						markPoint: {
							data: [
								{type: 'max', name: '最大值'}
							]
						},
						data: jedata
					}
				]
			});
			$(window).resize(function () {
				hyzlEcharts_ddzl.resize();
				hyzlEcharts_jyzl.resize();
			});
		}

		function initHyyxqk(){
			//行业运行状况 - 车辆在线总览
			var online = 0,offline = 0;

			//行业运行状况 - 车辆载客总览
			var busycar = 0,emptycar = 0;

			for (var i = 0; i < vehilist.length; i++) {
				var ve  = vehilist[i];
				if(ve.longi < 0){continue;}
				if (ve.lati == null || "" == ve.lati
						|| 0==ve.lati
						|| ""==ve.longi
						|| 0 ==ve.longi 
				) {
					offline++;
				} else {
					if (ve.onoffstate=="1") {
						if (ve.carStatus=="0") {
							emptycar++;
						} else {
							busycar++;
						}
						online++;
					} else if (ve.onoffstate=="0") {
						offline++;
					}
				}
			}

			$('#hyyxqk_clzxs').html(online);
			$('#hyyxqk_clzxl').html((online/(online+offline)*100).toFixed(2));
			$('#hyyxqk_clzks').html(busycar);
//			$('#hyyxqk_clzkl').html((busycar/(busycar+emptycar)*100).toFixed(2));

			var hyzlEcharts_clzx = echarts.init(document.getElementById('hyzl-echarts_clzxzl'));
			hyzlEcharts_clzx.setOption({
				color: ['#1391fd', '#4575b4', '#74add1', '#d48265', '#6e7074', '#749f83', '#c4ccd3', '#fdae61', '#f46d43', '#d73027', '#a50026'],
				legend: {
					orient: 'horizontal',
					y: 'bottom',
					x: 'center',
					data: ['车辆离线', '车辆在线']
				},
				series: [
					{
						name: '访问来源',
						type: 'pie',
						radius: '80%',
						center: ['50%', '40%'],
						data: [
							{value: offline, name: '车辆离线'},
							{value: online, name: '车辆在线'}
						],
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						},
						labelLine: {
							show: false
						},
						label: {
							formatter: '{b}：{d}%',
							position: 'inside'
						}
					}
				]
			});
			var hyzlEcharts_clzk = echarts.init(document.getElementById('hyzl-echarts_clzkzl'));
			hyzlEcharts_clzk.setOption({
				color: ['#1391fd', '#4575b4', '#74add1', '#d48265', '#6e7074', '#749f83', '#c4ccd3', '#fdae61', '#f46d43', '#d73027', '#a50026'],
				legend: {
					orient: 'horizontal',
					y: 'bottom',
					x: 'center',
					data: ['车辆载客', '车辆未载客']
				},
				series: [
					{
						name: '访问来源',
						type: 'pie',
						radius: '80%',
						center: ['50%', '40%'],
						data: [
							{value: busycar, name: '车辆载客'},
							{value: emptycar, name: '车辆未载客'}
						],
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						},
						labelLine: {
							show: false
						},
						label: {
							formatter: '{b}：{d}%',
							position: 'inside'
						}
					}
				]
			});
			$(window).resize(function () {
				hyzlEcharts_clzx.resize();
				hyzlEcharts_clzk.resize();
			});
		}
	})(jQuery);