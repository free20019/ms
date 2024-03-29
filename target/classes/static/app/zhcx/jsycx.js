var jsycx = (function($) {
	var all = 0,re;
	$(function () {
		
		$(".select2").select2({  
		  	language: "zh-CN",  //设置 提示语言
	        tags:true,  
	        createTag:function (decorated, params) {  
	            return null;  
	        },  
	    });

		$('#jsycx_city').select2({
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
		
		$('#jsycx_age').select2({
			language: 'zh-CN',
			width: '150',
			minimumResultsForSearch: -1,
			allowClear: true,
			data: [
			{id: '3(含)至5年', text: '3(含)至5年'},
			{id: '5(含)至10年', text: '5(含)至10年'},
			{id: '10(含)至15年', text: '10(含)至15年'},
			{id: '15(含)至20年', text: '15(含)至20年'},
			{id: '20年(含)以上', text: '20年(含)以上'}
			]
		});
		
		
		
		
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
				$('#jsycx_cph').select2({
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
				$('#jsycx_gsm').select2({
					data: data,
					allowClear: true
					});
			}
		});
		//初始化
		init();

		//图表
		function init(){
			$('#jsycxTable').jsGrid({
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
                fields:  [
                    {name: 'ID', title: '序号', width: 60, align: 'center'},
        			{name: 'sfzh', title: '身份证号', width: 160, align: 'center'},
        			{name: 'xm', title: '姓名', width: 60, align: 'center'},
        			{name: 'gs', title: '业户名称', width: 200, align: 'center'},
        			{name: 'jyxkzh', title: '经营许可证号', width: 100, align: 'center'},
        			{name: 'fwzh', title: '服务证号', width: 160, align: 'center'},
        			{name: 'ch', title: '车牌号', width: 80, align: 'center'},
        			{name: 'zgzyxqz', title: '资格证有效期止', width: 120, align: 'center'},
        			{name: 'fz', title: '分值', width: 60, align: 'center'},
        			{name: 'zzzt', title: '证照状态', width: 60, align: 'center'},
        			{
        				title: '详情',
                        itemTemplate: function(_, item) {
                        	var style = {};
                            return [
								$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
									$('#jsycx-dialog').modal('show');
									$('#jsycx-dialog .modal-title').text('详情');
									$('#jsycx-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
									$('#jsycx-dialog-save').hide();
									
									for(var i = 0; i< re.length ;i++){
			         					if(re[i].id_number == item.sfzh){
			         						var s = re[i];
			         						$('#jsycx-dialog-ximi div').html("").html(s.name);
			         						$('#jsycx-dialog-sfzh div').html("").html(s.id_number);
			         						$('#jsycx-dialog-jial div').html("").html(s.driver_age);
			         						$('#jsycx-dialog-szds div').html("").html(s.area_name);
			         						$('#jsycx-dialog-ickh div').html("").html(s.vehicle_id);
			         						$('#jsycx-dialog-sgqy div').html("").html(s.company_name);
			         						
			         						$('#jsycx-dialog-cphm div').html("").html(s.hasOwnProperty('plate_number')?s.plate_number.replace(".",""):"");
			         						$('#jsycx-dialog-zgzh div').html("").html(s.ID);
			         						$('#jsycx-dialog-ajzt div').html("").html(s.hasOwnProperty('valid_period_start')?formatYYYYMMDD(s.valid_period_start):"");
			         						$('#jsycx-dialog-yxqz div').html("").html(s.hasOwnProperty('valid_period_end')?formatYYYYMMDD(s.valid_period_end):"");
			         						$('#jsycx-dialog-zjbh div').html("").html(s.serial_number);
			         						$('#jsycx-dialog-hzcs div').html("").html(s.replacement_number);
			         						$('#jsycx-dialog-xkzh div').html("").html(s.company_license_number);
			         						$('#jsycx-dialog-sgyxqq div').html("").html(formatYYYYMMDD(s.ic_vd_s));
			         						$('#jsycx-dialog-sgyxqz div').html("").html(formatYYYYMMDD(s.ic_vd_e));
			         						$('#jsycx-dialog-fwjdkh div').html("").html(s.server_card_num);
			         						$('#jsycx-dialog-fwjdkyxqq div').html("").html(formatYYYYMMDD(s.server_date_begin));
			         						$('#jsycx-dialog-fwjdkyxqz div').html("").html(formatYYYYMMDD(s.server_date_end));
			         						$('#jsycx-dialog-pxcs div').html("").html(s.train_number);
			         					}
			         				}
								})
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
		}

		function res(filter, callback){
			//跳转赋值  ---- step 1
			var name =  $($($(window.parent.document)).find('#zl_driver')).val();
			console.log("name:"+name)
	        if(name == ""){
	         	name = $("#jsycx_age option:selected").html()||"全部"
	        }else{
	        	//跳转赋值  ---- step 2
				$("#jsycx_age").val(name).trigger("change");
	         	$($($(window.parent.document)).find('#zl_driver')).val("");
	        }
			
//			console.log(filter)
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
     	        url:"../../tjfx/jsycx",
     	        data:{
     				"sfzh" : $("#jsycx_sfzh").val(),
     				"cph" : $("#jsycx_cph option:selected").html()||"全部",
     				"xm" : $("#jsycx_xm").val(),
     				"gsm" : $("#jsycx_gsm option:selected").html()||"全部",
     				"age" : $("#jsycx_age option:selected").html()||"全部",
     				"fwzh" : $("#jsycx_fwzh").val(),
     				"jyxkz" : $("#jsycx_jyxkz").val(),
//     				"status" : $("#jsycx_status option:selected").html(),
     				"city" : $("#jsycx_city option:selected").html(),
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            		var jsycxData = [];
     				console.log(json)
            		all = json.data[0].count;
     				re = json.data[0].datas;
     				console.log(all)
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.sfzh =  re[i].id_number;
         					rs.xm =  re[i].name;
           					rs.gs =  re[i].company_name;
         					rs.jyxkzh =  re[i].company_license_number;
         					rs.fwzh =  re[i].server_card_num;
         					rs.ch =  re[i].hasOwnProperty('plate_number')?re[i].plate_number.replace(".",""):"";
         					rs.zgzyxqz =  formatYYYYMMDD(re[i].valid_period_end);
         					rs.fz =  re[i].assess_score;
         					rs.zzzt =  re[i].status_name;
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

		//查询
		$("#jsycx_cx").on('click',function(){
			init();
		});

		//导出
		$("#jsycx_dc").on('click',function(){
			//跳转赋值  ---- step 1
			var name =  $($($(window.parent.document)).find('#zl_driver')).val();
			console.log("name:"+name)
	        if(name == ""){
	         	name = $("#jsycx_age option:selected").html()||"全部"
	        }else{
	        	//跳转赋值  ---- step 2
				$("#jsycx_age").val(name).trigger("change");
	         	$($($(window.parent.document)).find('#zl_driver')).val("");
	        }
			var data = {
					"sfzh" : $("#jsycx_sfzh").val(),
     				"cph" : $("#jsycx_cph option:selected").html()||"全部",
     				"xm" : $("#jsycx_xm").val(),
     				"gsm" : $("#jsycx_gsm option:selected").html()||"全部",
     				"age" : $("#jsycx_age option:selected").html()||"全部",
     				"fwzh" : $("#jsycx_fwzh").val(),
     				"jyxkz" : $("#jsycx_jyxkz").val(),
//     				"status" : $("#jsycx_status option:selected").html(),
     				"city" : $("#jsycx_city option:selected").html(),
			};
			url = "../../tjfx/jsycxxlsx?data=" + JSON.stringify(data) , window.open(url)
			
		});
	});
})(jQuery);
