var clcx = (function($) {
	var all = 0,re;
	var pn ;
	$(function () {
//		$('#clcx_status').select2({
//			language: 'zh-CN',
//			width: '150',
//			minimumResultsForSearch: -1,
//			data: [
//			       {id: '0', text: '全部'},
//			       {id: '1', text: '营运'},
//			       {id: '2', text: '停运'},
//			       {id: '3', text: '已转出'},
//			       {id: '4', text: '已转籍'},
//			       {id: '5', text: '已报废'},
//			       {id: '6', text: '检测不合格'},
//			       {id: '7', text: '注销'}
//			       ]
//		});

		$('#clcx_city').select2({
			language: 'zh-CN',
			width: '150',
			minimumResultsForSearch: -1,
			data: [
			       {id: '15', text: '全部'},
			       {id: '11', text: '主城区'},
			       {id: '2', text: '富阳区'},
			       {id: '3', text: '淳安县'},
			       {id: '1', text: '临安区'},
			       {id: '9', text: '桐庐县'},
			       {id: '5', text: '萧山区'},
			       {id: '10', text: '建德市'},
			       {id: '4', text: '余杭区'}
			       ]
		});
		
		$('#clcx_age').select2({
			language: 'zh-CN',
			width: '150',
			minimumResultsForSearch: -1,
			allowClear: true,
			data: [
			       {id: '0(含)至1年', text: '0(含)至1年'},
			       {id: '1(含)至2年', text: '1(含)至2年'},
			       {id: '2(含)至3年', text: '2(含)至3年'},
			       {id: '3(含)至4年', text: '3(含)至4年'},
			       {id: '4(含)年以上', text: '4(含)年以上'}
			       ]
		});
		
		$(".select2").select2({  
		  	language: "zh-CN",  //设置 提示语言
	        tags:true,  
	        createTag:function (decorated, params) {  
	            return null;  
	        },  
	    });
		
/*		//跳转赋值  ---- step 1
		var name =  $($($(window.parent.document)).find('#zl_vehi_age')).val();
		console.log("name:"+name)
        if(name == ""){
        	name = $("#clcx_age option:selected").html()||"全部"
         	$($($(window.parent.document)).find('#zl_vehi_age')).val("");
        }else{
        	//跳转赋值  ---- step 2
        	init();
			$("#clcx_age").val(name).trigger("change");
        }
        
        //跳转赋值  ---- step 1
		var name1 =  $($($(window.parent.document)).find('#zl_vehi_type')).val();
		console.log("name1:"+name1)
        if(name1 == ""){
         	name1 = $("#clcx_type").val()||"全部";
         	$($($(window.parent.document)).find('#zl_vehi_type')).val("");
        }else{
        	//跳转赋值  ---- step 2
        	init();
			$("#clcx_type").val(name1)
        }*/
		
		
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
				$('#clcx_cph').select2({
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
					data[i].id=data[i].ID;
					data[i].text=data[i].NAME;
				}
				var qb={};
				qb.id='0';
				qb.text='全部';
				data.unshift(qb);
				$('#clcx_xm').select2({
					data: data,
					allowClear: true
					});
			}
		});
		
		
		//初始化
		init();


		//图表
		function init(){
			$('#clcxTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 55px)',
				autoload: true,
				paging: true,
				pageLoading: true,
				pageSize: 15,
				pageIndex: 1,
				noDataContent:'无数据',
				loadMessage:'数据加载中',
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
        			{name: 'PLATE_NUMBER', title: '车牌号', width: 80, align: 'center'},
        			{name: 'VEHICLE_TYPE_NAME', title: '车辆类型', width: 60, align: 'center'},
        			{name: 'BRAND', title: '车辆品牌', width: 60, align: 'center'},
        			{name: 'COMPANY_NAME', title: '业户名称', width: 200, align: 'center'},
        			{name: 'COMPANY_LICENSE_NUMBER', title: '经营许可证号', width: 100, align: 'center'},
        			{name: 'LICENSE_NUMBER', title: '道路运输证号', width: 100, align: 'center'},
        			{name: 'LICENSE_VALID_PERIOD_FROM', title: '核发日期', width: 80, align: 'center'},
        			{name: 'LICENSE_VALID_PERIOD_END', title: '有效日期', width: 80, align: 'center'},
        			{name: 'BUSINESS_SCOPE_NAME', title: '经营范围', width: 120, align: 'center'},
        			{name: 'STATUS_NAME', title: '状态', width: 60, align: 'center'},
        			{
        				title: '详情',
                        itemTemplate: function(_, item) {
                        	var style = {};
                            return [
								$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
									$('#clcx-dialog').modal('show');
									$('#clcx-dialog .modal-title').text('详情');
									$('#clcx-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
									$('#clcx-dialog-save').hide();
									for(var i = 0; i< re.length ;i++){
			         					if(re[i].plate_number == item.PLATE_NUMBER){
			         						var s = re[i];
			         						$('#clcx-dialog-yhmc div').html("").html(s.company_name);
			         						$('#clcx-dialog-xkzh div').html("").html(s.company_license_number);
			         						$('#clcx-dialog-jyfw div').html("").html(s.business_scope_name);
			         						
			         						$('#clcx-dialog-cphm div').html("").html(s.plate_number);
			         						$('#clcx-dialog-cpys div').html("").html(s.plate_color_name);
			         						$('#clcx-dialog-cplx div').html("").html(s.vehicle_type_name);
			         						$('#clcx-dialog-hdzk div').html("").html(s.fuel_type);
			         						$('#clcx-dialog-cpai div').html("").html(s.brand);
			         						$('#clcx-dialog-xhao div').html("").html(s.model);
			         						$('#clcx-dialog-chec div').html("").html(s.length);
			         						$('#clcx-dialog-chek div').html("").html(s.width);
			         						$('#clcx-dialog-cheg div').html("").html(s.height);
			         						$('#clcx-dialog-clys div').html("").html(s.color);
			         						$('#clcx-dialog-clda div').html("").html(s.file_number);
			         						$('#clcx-dialog-zozl div').html("").html(s.total_mass);
			         						$('#clcx-dialog-clbz div').html("").html(s.remark);
			         						
			         						$('#clcx-dialog-fdjh div').html("").html(s.engine_number);
			         						$('#clcx-dialog-jsdj div').html("").html(s.tech_level_name);
			         						$('#clcx-dialog-yxqz div').html("").html(formatYYYYMMDD(s.tech_rating_valid_period_end));
			         						$('#clcx-dialog-sbdm div').html("").html(s.chassis_number);
			         						$('#clcx-dialog-rllx div').html("").html(s.fuel_name);
			         						
			         						$('#clcx-dialog-yszh div').html("").html(s.license_number);
			         						$('#clcx-dialog-kssj div').html("").html(formatYYYYMMDD(s.license_valid_period_from));
			         						$('#clcx-dialog-jzsj div').html("").html(formatYYYYMMDD(s.license_valid_period_end));
			         						$('#clcx-dialog-nsyxq div').html("").html(formatYYYYMMDD(s.annual_review_valid_period_s));
			         						$('#clcx-dialog-ccfzsj div').html("").html(formatYYYYMMDD(s.initial_receipt_date));
			         						$('#clcx-dialog-yszbz div').html("").html(s.license_memo);
			         					}
			         				}
									
									
									var fields_1 = [
										{name: 'ID', title: '序号', width: 60, align: 'center'},
					        			{name: 'CALL_NAME', title: '投诉人', width: 80, align: 'center'},
					        			{name: 'BUSINESS_TIME', title: '投诉时间', width: 60, align: 'center'},
					        			{name: 'BRAND', title: '投诉类型', width: 60, align: 'center'},
					        			{name: 'BUSINUSS_STATUS_NAME', title: '处理详情', width: 200, align: 'center'}
				        			];
									
									var fields_2 = [
										{name: 'ID', title: '序号', width: 60, align: 'center'},
					        			{name: 'PARTY_NAME', title: '当事人姓名', width: 80, align: 'center'},
					        			{name: 'ILLEGAL_TIME', title: '违章时间', width: 60, align: 'center'},
					        			{name: 'CLOSED_DATE', title: '执法时间', width: 60, align: 'center'},
					        			{name: 'ILLEGAL_FACT', title: '案件原因', width: 200, align: 'center'},
					        			{name: 'AREA_NAME', title: '执法区域', width: 100, align: 'center'}
				        			];
									
									
									//驾驶员信息
									pn = item.PLATE_NUMBER;
									res_0();
									
									$('#clcxTable_1').jsGrid({
										width: '100%',
										height: 'calc(100% - 55px)',
										autoload: true,
										paging: true,
										pageLoading: true,
										noDataContent:'无数据',
										loadMessage:'数据加载中',
										fields:fields_1,
										controller: {
						                    loadData: function(filter) {
						                    	var d = $.Deferred();
						                    	var a = res_1(filter, function(item){
						                    		d.resolve(item);
						                    	})
						                    	return d.promise();
						                    }
						                },
									});
									
									$('#clcxTable_2').jsGrid({
										width: '100%',
										height: 'calc(100% - 55px)',
										autoload: true,
										paging: true,
										pageLoading: true,
										noDataContent:'无数据',
										loadMessage:'数据加载中',
										fields:fields_2,
										controller: {
						                    loadData: function(filter) {
						                    	var d = $.Deferred();
						                    	var a = res_2(filter, function(item){
						                    		d.resolve(item);
						                    	})
						                    	return d.promise();
						                    }
						                },
									});
									
									
									
								}),
							];
                        },
                        align: "center",
                        width: 50
                    }
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
//			$('#clcx-dialog').on('hidden.bs.modal', function (e) {
//				$(this).find('input[type=text].form-control, textarea.form-control').val('');
//				$(this).find('select.form-control').val('').trigger('change');
//				$(this).find('div.form-control').text('');
//			});
//			$('#clcx-add').on('click', function (e) {
//				$('#clcx-dialog').modal('show');
//				$('#clcx-dialog .modal-title').text('添加');
//				$('#clcx-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
//				$('#clcx-dialog-save').show();
//			});
		}
		
		
		function res_0(){
            $.ajax({
     	        url:"../../tjfx/jsyxx",
     	        data:{
     				"cph" : pn,
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            	console.log(json)
            	re = json.data[0].datas;
            	if(json.code == 0){
            		if(re.length != 0){
            			$('#clcx-dialog-jsyxm div').html("").html(re[0].name);
            			$('#clcx-dialog-jsysfzh div').html("").html(re[0].id_number);
            			$('#clcx-dialog-jsylxdh div').html("").html(re[0].mobile_phone);
            			$('#clcx-dialog-jsyfwzgzh div').html("").html(re[0].server_card_end_num);
            			$('#clcx-dialog-jsyzgyxqz div').html("").html(re[0].server_date);
            		}
            	}
            }).fail(function() {
            });
		}
		
		function res_1(filter, callback){
            $.ajax({
     	        url:"../../tjfx/tsrxx",
     	        data:{
     				"cph" : pn,
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            	console.log(json)
            		var clcxData = [];
            		all = json.data[0].datas.length;
     				re = json.data[0].datas;
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.BUSINESS_TIME =  formatYYYYMMDD(re[i].BUSINESS_TIME);
           					rs.CALL_NAME =  re[i].CALL_NAME;
           					rs.BUSINESS_ITEMTYPE_NAME =  re[i].BUSINESS_ITEMTYPE_NAME;
         					rs.BUSINUSS_STATUS_NAME =  re[i].BUSINUSS_STATUS_NAME;
         					clcxData.push(rs);
         				}
         				return callback({
                            data: clcxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
            });
		}
		
		function res_2(filter, callback){
            $.ajax({
     	        url:"../../tjfx/xzcfxx",
     	        data:{
     				"cph" : pn,
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            	console.log(json)
            		var clcxData = [];
            		all = json.data[0].datas.length;
     				re = json.data[0].datas;
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.PARTY_NAME =  re[i].PARTY_NAME;
           					rs.ILLEGAL_TIME =  re[i].ILLEGAL_TIME;
           					rs.CLOSED_DATE =  re[i].CLOSED_DATE;
         					rs.ILLEGAL_FACT =  re[i].ILLEGAL_FACT;
         					rs.AREA_NAME =  re[i].AREA_NAME;
         					clcxData.push(rs);
         				}
         				return callback({
                            data: clcxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
            });
		}
		
		function res(filter, callback){
			//跳转赋值  ---- step 1
			var name =  $($($(window.parent.document)).find('#zl_vehi_age')).val();
			console.log("name:"+name)
	        if(name == ""){
	        	name = $("#clcx_age option:selected").html()||"全部"
	        }else{
	        	//跳转赋值  ---- step 2
				$("#clcx_age").val(name).trigger("change");
	         	$($($(window.parent.document)).find('#zl_vehi_age')).val("");
	        }
	        
	        //跳转赋值  ---- step 1
			var name1 =  $($($(window.parent.document)).find('#zl_vehi_type')).val();
			console.log("name1:"+name1)
	        if(name1 == ""){
	         	name1 = $("#clcx_type").val()||"全部";
	        }else{
	        	//跳转赋值  ---- step 2
				$("#clcx_type").val(name1);
	         	$($($(window.parent.document)).find('#zl_vehi_type')).val("");

	        }
			
//			console.log(filter)
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
//			console.log(startIndex)
            jqxhr=$.ajax({
     	        url:"../../tjfx/clcx",
     	        data:{
     				"cph" : $("#clcx_cph option:selected").html()||"全部",
     				"xm" : $("#clcx_xm option:selected").html()||"全部",
     				"city" : $("#clcx_city option:selected").html(),
     				"yszh" : $("#clcx_yszh").val(),
     				"age" : name,
     				"type" : name1,
     				"jyxkzh" : $("#clcx_jyxkzh").val(),
//     				"status" : $("#clcx_status option:selected").html(),
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            		var clcxData = [];
            		all = json.data[0].count;
     				re = json.data[0].datas;
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.PLATE_NUMBER =  re[i].plate_number;
           					rs.VEHICLE_TYPE_NAME =  re[i].vehicle_type_name;
           					rs.BRAND =  re[i].brand;
         					rs.COMPANY_NAME =  re[i].company_name;
         					rs.COMPANY_LICENSE_NUMBER =  re[i].company_license_number;
         					rs.LICENSE_NUMBER =  re[i].license_number;
         					rs.LICENSE_VALID_PERIOD_FROM =  formatYYYYMMDD(re[i].license_valid_period_from);
         					rs.LICENSE_VALID_PERIOD_END =  formatYYYYMMDD(re[i].license_valid_period_end);
         					rs.BUSINESS_SCOPE_NAME =  re[i].business_scope_name;
         					rs.STATUS_NAME =  re[i].status_name;
         					clcxData.push(rs);
         				}
         				return callback({
                            data: clcxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
            });
		}

		//查询
		$("#clcx_cx").on('click',function(){
			init();
		});

		//导出
		$("#clcx_dc").on('click',function(){
			//跳转赋值  ---- step 1
			var name =  $($($(window.parent.document)).find('#zl_vehi_age')).val();
			console.log("name:"+name)
	        if(name == ""){
	        	name = $("#clcx_age option:selected").html()||"全部"
	        }else{
	        	//跳转赋值  ---- step 2
				$("#clcx_age").val(name).trigger("change");
	         	$($($(window.parent.document)).find('#zl_vehi_age')).val("");
	        }
	        
	        //跳转赋值  ---- step 1
			var name1 =  $($($(window.parent.document)).find('#zl_vehi_type')).val();
			console.log("name1:"+name1)
	        if(name1 == ""){
	         	name1 = $("#clcx_type").val()||"全部";
	        }else{
	        	//跳转赋值  ---- step 2
				$("#clcx_type").val(name1);
	         	$($($(window.parent.document)).find('#zl_vehi_type')).val("");

	        }
			var data = {
					"cph" : $("#clcx_cph option:selected").html()||"全部",
     				"xm" : $("#clcx_xm option:selected").html()||"全部",
     				"city" : $("#clcx_city option:selected").html(),
     				"yszh" : $("#clcx_yszh").val(),
     				"age" : name,
     				"type" : name1,
     				"jyxkzh" : $("#clcx_jyxkzh").val()
				};
				url = "../../tjfx/clcxxlsx?data=" + JSON.stringify(data) , window.open(url)
		});
	});
})(jQuery);
