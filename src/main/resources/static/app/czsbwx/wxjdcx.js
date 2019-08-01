var wxjdcx=(function ($) {
	var clfb_map;
	$(function () {
		$('#wxjd-stateTime').datetimepicker(datetimeDefaultOption);
//		$('#wxjd-stateTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		$('#wxjd-endTime').datetimepicker(datetimeDefaultOption);
//		$('#wxjd-endTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));

//		findvehicle('');
		$(document).ready(function() {
			  $(".select2").select2({  
					language: "zh-CN",  //设置 提示语言
			        tags:true,  
			        allowClear: true,
			        createTag:function (decorated, params) {  
			            return null;  
			        },  
			    });
			});
//		function findvehicle(obj){
//			jqxhr=$.ajax({
//				type: "POST",
//				url: "../../sbwx/clxll",
//				data:{"table":"tb_vehicle"},
//				timeout : 180000,
//				dataType: 'json',
//				success:function(data){
//					$("#wxjd-cphm").select2({ 
//						language: "zh-CN",  //设置 提示语言
//				        minimumInputLength: 3,
//				        tags:true,  
//				        allowClear: true,
//				        closeOnSelect: true,
//				        createTag:function (decorated, params) {  
//				            return null;  
//				        },  
//				    });
//					$("#wxjd-cphm").empty();
//					$("#wxjd-cphm").append('<option value="" selected></option>');
//					$("#wxjd-cphm").append('<option value="null">全部车辆</option>');
//					for(var i=0; i<data.length;i++){
//						if(obj.length>0){
//							if(obj == data[i].VEHI_NO){
//								$("#wxjd-cphm").append('<option value="'+data[i].VEHI_NO+'" selected>'+data[i].VEHI_NO+'</option>');
//							}else{
//								$("#wxjd-cphm").append('<option value="'+data[i].VEHI_NO+'">'+data[i].VEHI_NO+'</option>');
//							}
//						}else{
//							$("#wxjd-cphm").append('<option value="'+data[i].VEHI_NO+'">'+data[i].VEHI_NO+'</option>');
//						}
//					}
//				},
//				error: function(){
//				}         
//			});
//		}
//		
//		jqxhr=$.ajax({
//			type: "POST",
//			url: "../../sbwx/xll",
//			data:{"table":"tb_company"},
//			timeout : 180000,
//			dataType: 'json',
//			success:function(data){
//				$("#wxjd-company").empty();
//				$("#wxjd-company").append('<option value="" disabled selected>业户</option>');
//				$("#wxjd-company").append('<option value="null">全部业户</option>');
//				for(var i=0; i<data.length;i++){
//					$("#wxjd-company").append('<option value="'+data[i].COMP_ID+'">'+data[i].COMP_NAME+'</option>');				
//				}
//			},
//			error: function(){
//			}         
//		});
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
				qb.id='null';
				qb.text='全部';
				data.unshift(qb);
				$('#wxjd-cphm').select2({
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
				qb.id='null';
				qb.text='全部';
				data.unshift(qb);
				$('#wxjd-company').select2({
					data: data,
					allowClear: true
				});
			}
		});
//        jqxhr=$.ajax({
//			type: "POST",
//			url:"../../sbwx/qycomp",
//			data:{},
//			dataType: 'json',
//			timeout : 3600000,
//			success:function(json){
//				var data= json.datacomp;
//				for (var i = 0; i < data.length; i++) {
//					data[i].id=data[i].COMP_NAME;
//					data[i].text=data[i].COMP_NAME;
//				}
//				var qb={};
//				qb.id='null';
//				qb.text='全部';
//				data.unshift(qb);
//				$('#wxjd-company').select2({
//					data: data,
//					allowClear: true
//					});
//			}
//		});	
		
		var bljlFields = [
		              		{name: 'id', title: '序号', width: 60, align: 'center'},
		              		{name: 'cphm', title: '车牌号', width: 80, align: 'center'},
			    			{name: 'gs', title: '业户', width: 120, align: 'center'},
			    			{name: 'simk', title: 'SIM卡', width: 120, align: 'center'},
			    			{name: 'cz', title: '车主', width: 80, align: 'center'},
			    			{name: 'sjhm', title: '手机号码', width: 120, align: 'center'},
			    			{name: 'gzyy', title: '上次故障原因', width: 240, align: 'center'},		
				  		    {name: 'sxsj', title: '送修时间', width: 120, align: 'center'},
				  		    {name: 'wxsj', title: '完修时间', width: 120, align: 'center'},
			    			{name: 'jdxx', title: '维修进度', width: 120, align: 'center'},
			    			{name: 'wxjg', title: '维修情况', width: 120, align: 'center'},
			    			
			    			{name: 'caozuo', title: '操作', width: 60},
			    			
			    		];
		$('#wxjd-select').on('click', function () {
			findwxjd();
		});
		function findwxjd(){
			var all = 0;
			$('#wxjdTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 55px)',
				autoload: true,
				paging: true,
				pageLoading: true,
				editing: true,

				pageSize: 15,
				pageIndex: 1,
				controller: {
                    loadData: function(filter) {
                    	var d = $.Deferred();
                    	var a = re(filter, function(item){
                    		d.resolve(item);
                    	})
                    	return d.promise();
                    }
                },
                fields: bljlFields,
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
		function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
            	type: "POST",
				url: "../../sbwx/findwxjd",
				data:{"stime":$("#wxjd-stateTime").val(),
					"etime":$("#wxjd-endTime").val(),
					"vehicle":$("#wxjd-cphm").val(),
					"company":$("#wxjd-company").val(),
					"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
					},
				timeout : 180000,
				dataType: 'json',
            }).done(function(data) {
            		var jsycxData = [];
     				all = data[0].count;
         			if(data.length>0){
         				for(var i = 0; i< data[0].datas.length ;i++){
         					var rs={};
         					var vehicle=data[0].datas[i];
         					rs.id =  startIndex+i+1;
         					rs.gs =  vehicle.COMP_NAME;
         					rs.cphm =  vehicle.VEHI_NO;
         					rs.gzyy = vehicle.RM_MALFUNCTION;
         					rs.simk =  vehicle.SIM_NUM;
         					rs.cz =  vehicle.OWN_NAME;
         					rs.sjhm =  vehicle.OWN_TEL;
//         					if(vehicle.DB_TIME<=vehicle.RR_TIME_END){
         						rs.sxsj = (vehicle.RR_TIME==null?"":(new Date(vehicle.RR_TIME)).Format("yyyy-MM-dd hh:mm:ss"));
         						rs.wxsj = (vehicle.RR_TIME_END==null?"":(new Date(vehicle.RR_TIME_END)).Format("yyyy-MM-dd hh:mm:ss"));
         						var date=new Date();
         						if(date.getTime()>vehicle.RR_TIME_END){
         							rs.jdxx ='维修完成';
         							rs.wxjg =vehicle.RC_CONTENT;	
         						}else{
         							rs.jdxx ='正在维修';
         							rs.wxjg =vehicle.RC_CONTENT;	
         						}
//         					}else{
//         						rs.sxsj = '';
//         						rs.wxsj = '';
//         						rs.jdxx = '';
//             					rs.wxjg = '';
//         					}
         					rs.caozuo = "<a href='javascript:void(0);' onclick='findclzz(\""+vehicle.VEHICLE_NO+"\");'>跟踪</a>";
         					jsycxData.push(rs);
         				}
         				return callback({
                            data: jsycxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}
		findwxjd();
		$('#wxjd-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
			$('#wxjd-terminal').html('<option value="" disabled selected>具体异常状态</option>');
		});
		$('#wxjd-dc').on('click', function () {
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
					window.open(basePath+"sbwx/findwxjddc?stime="+$("#wxjd-stateTime").val()
					+"&etime="+$("#wxjd-endTime").val()
					+"&vehicle="+$("#wxjd-cphm").val()
					+"&company="+$("#wxjd-company").val()
				);
				layer.close(layerIndex);
		}, function (layerIndex) {
			layer.close(layerIndex);
		});
		});
	})
})(jQuery)
function findclzz(vehicle){
	var _parent = parent.$(window.parent.document);
	if (_parent.find('.ip-tabBarItem[data-name="wxcltj"]').length > 0) {
		_parent.find('.ip-tabBarItem[data-name="wxcltj"]').trigger('click');
		_parent.find('#wxcltj').get(0).contentWindow.location.reload(true);
	}
	_parent.find('.ip-menuItem [data-skip="wxcltj"]').trigger("click");
	_parent.find('#defaultVehicle').val(vehicle);
}
function findgz(){
	var gz=$('#wxjd-type').val();
	var text='';
	if(gz=='0'){
		text +='<option value="">全部</option>';
	}
	if(gz=='1'){
		text +='<option value="NO_GPS">无定位数据</option>';
//		text +='<option value="LOW_VOLTAGE">终端主电源欠压</option>'
//			  +'<option value="NO_POWER">主电源掉电</option>'
//			  +'<option value="NO_GPS">无定位数据</option>'
//			  +'<option value="NO_UPLOAD">无数据上传</option>';
	}
	if(gz=='2'){
		text +='<option value="MOD_FAULT">定位模块故障</option>'
			  +'<option value="ANT_FAULT">天线短路</option>'
			  +'<option value="INEXACT">非精确定位</option>';
	}
	if(gz=='3'){
		text +='<option value="COMM_FAULT">通讯故障</option>';
	}
	if(gz=='4'){
		text +='<option value="GPS_OVER_BACK">定位回传过密</option>'
			  +'<option value="GPS_NO_BACK">回传数据丢失</option>';
	}
	if(gz=='5'){
		text +='<option value="CAM_OCCLUSION">摄像头遮挡</option>'
			  +'<option value="CAM_NOSIGN">摄像头信号丢失</option>';
	}
	
	if(gz=='6'){
		text +='<option value="HD_FAULT">硬盘故障</option>'
			  +'<option value="SD_FAULT">SD卡故障</option>'
			  +'<option value="VD_FAULT">视频主机故障</option>'
			  +'<option value="VD_EX_FAULT">视频扩展故障</option>';
	}
	if(gz=='7'){
		text +='<option value="METER_DISCONN">计价器连接断开</option>';
	}
	if(gz=='8'){
		text +='<option value="NAV_DISCONN">导航屏断开</option>';
	}
	if(gz=='9'){
		text +='<option value="ST_NO_CHG">空重车不变化</option>'
			  +'<option value="ST_OVER_CHG">空重车切换频繁</option>';
	}
	$('#wxjd-terminal').html(text);
}
