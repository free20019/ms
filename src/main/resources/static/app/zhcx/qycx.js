var qycx = (function($) {
	$(function () {
		$(".select2").select2({  
		  	language: "zh-CN",  //设置 提示语言
	        tags:true,  
	        createTag:function (decorated, params) {  
	            return null;  
	        },  
	    });
		
		$('#qycx_area').select2({
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
		
		$('#qycx_style').select2({
			language: 'zh-CN',
			width: '150',
			minimumResultsForSearch: -1,
			data: [
			       {id: '1', text: '全部'},
			       {id: '2', text: '业户'},
			       {id: '3', text: '个体'}
			       ]
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
				$('#qycx_name').select2({
					data: data,
					allowClear: true
				});			
				//跳转赋值  ---- step 2
				if($($($(window.parent.document)).find('#zl_comp')).val() != ""){
					$("#qycx_name").val($($($(window.parent.document)).find('#zl_comp')).val()).trigger("change");
					$($($(window.parent.document)).find('#zl_comp')).val("");
					init();
				}
			}
		});
		//跳转赋值  ---- step 1
		if($($($(window.parent.document)).find('#zl_comp')).val() == ""){
			init();
		}
		
		//图表
		function init(){
			var all = 0;
			$('#qycxTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 80px)',
				autoload: true,
				paging: true,
				pageLoading: true,
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
                fields: [
                         	{name: 'ID', title: '序号', width: 60, align: 'center'},
		        			{name: 'NAME', title: '业户名称', width: 200, align: 'center'},
		        			{name: 'VEHICLE_SUM', title: '规模(车辆数)', width: 80, align: 'center'},
		        			{name: 'AREA_NAME', title: '区域', width: 100, align: 'center'},
		        			{name: 'ECONOMIC_NAME', title: '性质', width: 120, align: 'center'},
		        			{name: 'LEGAL_PERSON_NAME', title: '负责人', width: 60, align: 'center'},
		        			{name: 'LICENSE_NUMBER', title: '经营许可证号', width: 160, align: 'center'},
		        			{name: 'RESPONSIBLE_PERSON_PHONE', title: '联系方式', width: 100, align: 'center'},
		        			{name: 'LICENSE_VALID_PERIOD_FROM', title: '许可证起', width: 120, align: 'center'},
		        			{name: 'LICENSE_VALID_PERIOD_END', title: '许可证止', width: 120, align: 'center'},
		        			{name: 'LICENSE_ISSUING_DATE', title: '发放日期', width: 120, align: 'center'},
		        			{name: 'BUSINESS_SCOPE_NAME', title: '经营范围', width: 120, align: 'center'},
		        			{name: 'BUSINESS_STATUS_NAME', title: '经营状态', width: 60, align: 'center'}
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
		
		function re(filter, callback){
//			console.log(filter)
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            console.log(222)
            jqxhr=$.ajax({
     	        url:"../../tjfx/qycx",
     	        data:{
     				"name" : $("#qycx_name").val(),
     				"area" : $("#qycx_area option:selected").html(),
     				"style" : $("#qycx_style option:selected").html(),
     				"min" :  $("#qycx_min").val(),
     				"max" :  $("#qycx_max").val(),
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            		var qycxData = [];
     				console.log(json)
            		all = json.data[0].count;
     				console.log(all)
         			if(json.code == 0){
         				for(var i = 0; i< json.data[0].datas.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.NAME =  json.data[0].datas[i].NAME;
         					rs.VEHICLE_SUM =  json.data[0].datas[i].VEHICLE_SUM;
           					rs.AREA_NAME =  json.data[0].datas[i].AREA_NAME;
         					rs.ECONOMIC_NAME =  json.data[0].datas[i].ECONOMIC_NAME;
         					rs.LEGAL_PERSON_NAME =  json.data[0].datas[i].LEGAL_PERSON_NAME;
         					rs.LICENSE_NUMBER =  json.data[0].datas[i].LICENSE_NUMBER;
         					rs.RESPONSIBLE_PERSON_PHONE =  json.data[0].datas[i].RESPONSIBLE_PERSON_PHONE;
         					rs.LICENSE_VALID_PERIOD_FROM =  formatYYYYMMDD(json.data[0].datas[i].LICENSE_VALID_PERIOD_FROM);
         					rs.LICENSE_VALID_PERIOD_END =  formatYYYYMMDD(json.data[0].datas[i].LICENSE_VALID_PERIOD_END);
         					rs.LICENSE_ISSUING_DATE =  formatYYYYMMDD(json.data[0].datas[i].LICENSE_ISSUING_DATE);
         					rs.BUSINESS_SCOPE_NAME =  json.data[0].datas[i].BUSINESS_SCOPE_NAME;
         					rs.BUSINESS_STATUS_NAME =  json.data[0].datas[i].BUSINESS_STATUS_NAME;
         					qycxData.push(rs);
         				}
         				return callback({
                            data: qycxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}
		
		//查询
		$("#qycx_cx").on('click',function(){
			init();
		});
		
		//导出
		$("#qycx_dc").on('click',function(){
			var data = {
					"name" : $("#qycx_name option:selected").html()||"全部",
     				"area" : $("#qycx_area option:selected").html(),
     				"style" : $("#qycx_style option:selected").html(),
     				"min" :  $("#qycx_min").val(),
     				"max" :  $("#qycx_max").val(),
				};
				url = "../../tjfx/qycxxlsx?data=" + JSON.stringify(data) , window.open(url)
		});
	});
})(jQuery);