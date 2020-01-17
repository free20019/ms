var sbgzcstj = (function($) {
	var all = 0,re;
	$(function () {
		$('#sbgzcstj-datetimeStart').datetimepicker(dateDefaultOption);
		$('#sbgzcstj-datetimeEnd').datetimepicker(dateDefaultOption);
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 2;
		$('#sbgzcstj-datetimeStart').val(new Date(today).Format('yyyy-MM-dd'));
		$('#sbgzcstj-datetimeEnd').val(new Date().Format('yyyy-MM-dd'));
		
		$(".select2").select2({  
		  	language: "zh-CN",  //设置 提示语言
	        tags:true,  
	        createTag:function (decorated, params) {  
	            return null;  
	        },  
	    });
		
		var dd = [
		          {id: '0', text: '有营运无定位',selected:true},
			      {id: '1', text: '有定位无营运'},
			      {id: '2', text: '有抓拍无定位无营运'},
			      {id: '3', text: '7天无定位无营运'},
			      {id: '4', text: '全天空车全天重车'},
		          ];
		
		$('#sbgzcstj_gz').select2({
			language: 'zh-CN',
			width: '150',
			minimumResultsForSearch: -1,
//			allowClear: true,
			data: dd
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
				$('#sbgzcstj_cph').select2({
					language: 'zh-CN',
					data: data,
					minimumInputLength: 3,
					placeholder:"车牌号",
					allowClear: true
					});
			}
		});
		
		//初始化
		init();


		//图表
		function init(){
			$('#sbgzcstjTable').jsGrid({
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
                 	{name: 'ID', title: '序号', width: 40, align: 'center'},
        			{name: 'VEHICLE_NO', title: '车牌号', width: 80, align: 'center'},
        			{name: 'COMP_NAME', title: '业户名称', width: 160, align: 'center'},
        			{name: 'TYPE', title: '故障类型', width: 120, align: 'center'},
        			{name: 'r', title: '故障次数', width: 120, align: 'center'},
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
            if(!validtion($('#sbgzcstj-datetimeStart').val(),$('#sbgzcstj-datetimeEnd').val())){
            	layer.msg("无法跨月查询");
            	return callback({
                    data: [],
                    itemsCount: 0
                });
            }
            jqxhr=$.ajax({
     	        url:"../../tjfx/sbgzcstj",
     	        data:{
     				"cph" : $("#sbgzcstj_cph option:selected").html()||"全部",
     				"gz" : $("#sbgzcstj_gz option:selected").html(),
     				"stime":$('#sbgzcstj-datetimeStart').val(),
     				"etime":$('#sbgzcstj-datetimeEnd').val(),
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            	console.log(json)
            		var sbgzcstjData = [];
            		all = json.data[0].count;
     				re = json.data[0].datas;
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.VEHICLE_NO =  re[i].VEHICLE_NO;
         					rs.COMP_NAME =  re[i].COMP_NAME;
         					rs.TYPE = re[i].TYPE
         					rs.r =  re[i].r;
         					sbgzcstjData.push(rs);
         				}
         				return callback({
                            data: sbgzcstjData,
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
		$("#sbgzcstj_cx").on('click',function(){
			init();
		});

		//导出
		$("#sbgzcstj_dc").on('click',function(){
			if(!validtion($('#sbgzcstj-datetimeStart').val(),$('#sbgzcstj-datetimeEnd').val())){
            	layer.msg("无法跨月导出");
            	return;
            }
			var data = {
					"cph" : $("#sbgzcstj_cph option:selected").html()||"全部",
     				"gz" : $("#sbgzcstj_gz option:selected").html()||"全部",
     				"stime":$('#sbgzcstj-datetimeStart').val(),
     				"etime":$('#sbgzcstj-datetimeEnd').val()
				};
				url = "../../tjfx/sbgzcstjxlsx?data=" + JSON.stringify(data) , window.open(url)
		});
	});
})(jQuery);
