var czsbgzcx = (function($) {
	var all = 0,re;
	$(function () {
		$('#czsbgzcx-datetimeStart').datetimepicker(dateDefaultOption);
		$('#czsbgzcx-datetimeEnd').datetimepicker(dateDefaultOption);
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 24;
		$('#czsbgzcx-datetimeStart').val(new Date(today-oneday).Format('yyyy-MM-dd'));
		$('#czsbgzcx-datetimeEnd').val(new Date(today-oneday).Format('yyyy-MM-dd'));
		$('#updatetime').text("更新时间："+new Date(today-oneday).Format('yyyy年MM月dd日'));
		
		
		$(".select2").select2({  
		  	language: "zh-CN",  //设置 提示语言
	        tags:true,  
	        createTag:function (decorated, params) {  
	            return null;  
	        },  
	    });
		
		var dd = [
		          {id: '0', text: '全部'},
		          {id: '1', text: '有营运无定位'},
			      {id: '2', text: '有定位无营运'},
			      {id: '3', text: '有抓拍无定位无营运'},
			      {id: '4', text: '7天无定位无营运'},
			      {id: '5', text: '全天空车全天重车'},
			      {id: '6', text: '视频黑屏'},
			      {id: '7', text: '视频移位'},
			      {id: '8', text: '视频断线'},
		          ];
		
		$('#czsbgzcx_gz').select2({
			language: 'zh-CN',
			width: '150',
			minimumResultsForSearch: -1,
			allowClear: true,
			data: dd
		});
		
//		var yczt =[
//			       {id: '0', text: '主机故障'},
//			       {id: '1', text: '定位故障'},
//			       {id: '2', text: '通信故障'},
//			       {id: '3', text: '定位回传故障'},
//			       {id: '4', text: '摄像头故障'},
//			       {id: '5', text: '视频主机/存储故障'},
//			       {id: '6', text: '计价器断开故障'},
//			       {id: '7', text: '导航屏断开故障'},
//			       {id: '8', text: '空车灯故障'}
//			       ]
//		
//		var gz =[
//		   [  
//		       {id: '0', text: '终端主电源欠压'},
//		       {id: '1', text: '主电源掉电'},
//		       {id: '2', text: '无定位数据'},
//		       {id: '3', text: '无数据上传'}
//		   ],
//		   [  
//		       {id: '0', text: '定位模块故障'},
//		       {id: '1', text: '天线短路'},
//		       {id: '2', text: '非精确定位'}
//		   ],
//		   [  
//		       {id: '0', text: '通讯故障'}
//		   ],
//		   [  
//		       {id: '0', text: '定位回传过密'},
//		       {id: '1', text: '回传数据丢失'}
//		   ],
//		   [  
//		       {id: '0', text: '摄像头遮挡'},
//		       {id: '1', text: '摄像头信号丢失'}
//		   ],
//		   [  
//		       {id: '0', text: '硬盘故障'},
//		       {id: '1', text: 'SD卡故障'},
//		       {id: '2', text: '视频主机故障'},
//		       {id: '3', text: '视频扩展故障'}
//		   ],
//		   [  
//		       {id: '0', text: '计价器连接断开'}
//		   ],
//		   [  
//		       {id: '0', text: '导航屏断开'}
//		   ],
//		   [  
//		       {id: '0', text: '空重车不变化'},
//		       {id: '1', text: '空重车切换频繁'}
//		   ],
//		]
//		
//		$('#czsbgzcx_yc').select2({
//			language: 'zh-CN',
//			width: '150',
//			minimumResultsForSearch: -1,
//			allowClear: true,
//			data: yczt
//		});
//		
//		$('#czsbgzcx_gz').select2({
//			language: 'zh-CN',
//			width: '150',
//			allowClear: true,
//			minimumResultsForSearch: -1,
//			data: gz[0]
//		});
//		
//		$('#czsbgzcx_yc').on('change',function(){
//			var n = $('#czsbgzcx_yc option:selected').val();
//			if(n===9)return;
//			var s = gz[n];
//			$('#czsbgzcx_gz').html("").select2({
//				data: s
//			});
//		});
//		
		jqxhr=$.ajax({
			type: "POST",
			url:"../../claq/qyveh",
			data:{},
			dataType: 'json',
			timeout : 3600000,
			success:function(json){
				console.log(json);
				var data= json.dataveh;
				for (var i = 0; i < data.length; i++) {
					data[i].id=data[i].PLATE_NUMBER;
					data[i].text=data[i].PLATE_NUMBER;
				}
				var qb={};
				qb.id='0';
				qb.text='全部';
				data.unshift(qb);
				$('#czsbgzcx_cph').select2({
					data: data,
					language:'zh-CN',
                    minimumInputLength: 3,
					allowClear: true
				});
			}
		});
		
		jqxhr=$.ajax({
			type: "POST",
			url:"../../claq/qycomp",
			data:{},
			dataType: 'json',
			timeout : 3600000,
			success:function(json){
				console.log(json);
				var data= json.datacomp;
				for (var i = 0; i < data.length; i++) {
					data[i].id=data[i].NAME;
					data[i].text=data[i].NAME;
				}
				var qb={};
				qb.id='0';
				qb.text='全部';
				data.unshift(qb);
				$('#czsbgzcx_comp').select2({
					data: data,
					allowClear: true
				});
				//跳转赋值  ---- step 2
				if($($($(window.parent.document)).find('#zl_error')).val() != ""){
					$("#czsbgzcx_comp").val($($($(window.parent.document)).find('#zl_error')).val()).trigger("change");
					$($($(window.parent.document)).find('#zl_error')).val("");
					init();
				}

			}
		});
		//跳转赋值  ---- step 1
		if($($($(window.parent.document)).find('#zl_error')).val() == ""){
			init();
		}

		//图表
		function init(){
			$('#czsbgzcxTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 55px)',
				autoload: true,
				paging: true,
				pageLoading: true,
				pageSize: 15,
				pageIndex: 1,
				controller: {
                    loadData: function(filter) {
                    	var d = $.Deferred();
                    	var a = res(filter, function(item){
                    		d.resolve(item);
                    	})
                    	return d.promise();
                    }
                },
                fields: [
                 	{name: 'ID', title: '序号', width: 60, align: 'center'},
        			{name: 'VEHICLE_NO', title: '车牌号', width: 80, align: 'center'},
        			{name: 'COMP_NAME', title: '业户名称', width: 160, align: 'center'},
        			{name: 'TYPE', title: '故障类型', width: 120, align: 'center'},
        			{name: 'DB_TIME', title: '故障时间', width: 120, align: 'center'},
        			{name: 'NO_GPS', title: '有营运无定位', itemTemplate:formatMalfunction, width: 120, align: 'center'},
        			{name: 'NO_JJQ', title: '有定位无营运',  itemTemplate:formatMalfunction,width: 120, align: 'center'},
        			{name: 'NO_GPS_JJQ', title: '有抓拍无定位无营运', itemTemplate:formatMalfunction, width: 120, align: 'center'},
        			{name: 'SEVEN_GPS_JJQ', title: '7天无定位无营运', itemTemplate:formatMalfunction, width: 120, align: 'center'},
        			{name: 'EMPTY_HEAVY', title: '全天空车全天重车', itemTemplate:formatMalfunction, width: 120, align: 'center'},
        			{name: 'SCREEN_BLACK', title: '视频黑屏',  itemTemplate:formatMalfunction,width: 120, align: 'center'},
        			{name: 'MOVE_ON', title: '视频移位', itemTemplate:formatMalfunction, width: 120, align: 'center'},
        			{name: 'BREAK_OFF', title: '视频断线', itemTemplate:formatMalfunction, width: 120, align: 'center'},
        		],
				pagerContainer: null,
			    pageButtonCount: 5,
			    pagerFormat: "{first} {prev} {pages} {next} {last} {pageIndex} of {pageCount}",
			    pagePrevText: "上一页",
			    pageNextText: "下一页",
			    pageFirstText: "第一页",
			    pageLastText: "末页",
			    pageNavigatorNextText: ">",
			    pageNavigatorPrevText: "<"
          	});
		}

		function res(filter, callback){
//			console.log(filter)
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
//			console.log(startIndex)
            if(!validtion($('#czsbgzcx-datetimeStart').val(),$('#czsbgzcx-datetimeEnd').val())){
            	layer.msg("无法跨月查询");
            	return callback({
                    data: [],
                    itemsCount: 0
                });
            }
            jqxhr=$.ajax({
     	        url:"../../tjfx/czsbgzcx",
     	        data:{
     				"cph" : $("#czsbgzcx_cph option:selected").html()||"全部",
     				"xm" : "全部",
     				"comp" : $("#czsbgzcx_comp").val(),
     				"gz" : $("#czsbgzcx_gz option:selected").html()||"全部",
     				"stime":$('#czsbgzcx-datetimeStart').val(),
     				"etime":$('#czsbgzcx-datetimeEnd').val(),
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            	console.log(json)
            		var czsbgzcxData = [];
            		all = json.data[0].count;
     				re = json.data[0].datas;
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.VEHICLE_NO =  re[i].VEHICLE_NO;
           					rs.COMP_NAME =  re[i].COMP_NAME||"";
         					rs.TYPE = re[i].TYPE;
         					rs.DB_TIME =  formatYYYYMMDD(re[i].DB_TIME);
         					rs.NO_GPS = re[i].NO_GPS;
         					rs.NO_JJQ = re[i].NO_JJQ;
         					rs.NO_GPS_JJQ = re[i].NO_GPS_JJQ;
         					rs.SEVEN_GPS_JJQ = re[i].SEVEN_GPS_JJQ;
         					rs.EMPTY_HEAVY = re[i].EMPTY_HEAVY;	
         					rs.SCREEN_BLACK = re[i].SCREEN_BLACK;
         					rs.MOVE_ON = re[i].MOVE_ON;
         					rs.BREAK_OFF = re[i].BREAK_OFF;
         					czsbgzcxData.push(rs);
         				}
         				return callback({
                            data: czsbgzcxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}
		
		//验证跨月
		function validtion(a,b){
			if(a.replace("-","").substring(4,6) != b.replace("-","").substring(4,6)){
				return false;
			}else{
				return true;
			}
		}
		
		//查询
		$("#czsbgzcx_cx").on('click',function(){
			init();
		});

		//导出
		$("#czsbgzcx_dc").on('click',function(){
			if(!validtion($('#czsbgzcx-datetimeStart').val(),$('#czsbgzcx-datetimeEnd').val())){
            	layer.msg("无法跨月导出");
            	return;
            }
			var data = {
					"cph" : $("#czsbgzcx_cph option:selected").html()||"全部",
     				"xm" : "全部",
     				"comp" : $("#czsbgzcx_comp").val(),
     				"yc" : $("#czsbgzcx_yc option:selected").html()||"全部",
     				"gz" : $("#czsbgzcx_gz option:selected").html()||"全部",
     				"stime":$('#czsbgzcx-datetimeStart').val(),
     				"etime":$('#czsbgzcx-datetimeEnd').val()
				};
				url = "../../tjfx/czsbgzcxxlsx?data=" + JSON.stringify(data) , window.open(url)
		});
		function formatMalfunction (val) {
//			if (val === '0') return $('<span class="malfunction-no">');
//			else if (val === '1') return $('<span class="malfunction-yes">');
			if (val === '0') return "无故障 ";
			else if (val === '1') return "故障";
			else return '';
		}
	});
})(jQuery);