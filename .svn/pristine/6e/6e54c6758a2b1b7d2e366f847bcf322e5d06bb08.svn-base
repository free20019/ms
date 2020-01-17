var tscx = (function($) {
	var all = 0,re;
	$(function () {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 24;

		$('#tscx_datetimeStart').datetimepicker(dateDefaultOption);
		$('#tscx_datetimeEnd').datetimepicker(dateDefaultOption);
		$('#tscx_datetimeStart').val(new Date(today - oneday).Format('yyyy-MM-dd'));
		$('#tscx_datetimeEnd').val(new Date().Format('yyyy-MM-dd'));

		$('#tscx_tslx').select2({
			language: 'zh-CN',
			width: '150',
			allowClear: true,
			minimumResultsForSearch: -1,
			data: [
			       {id: '0', text: '全部'},
			       {id: '1', text: '绕道'},
			       {id: '2', text: '中途抛客'},
			       {id: '3', text: '扰乱场站秩序'},
			       {id: '4', text: '服务不规范'},
			       {id: '5', text: '服务质量'},
			       {id: '6', text: '拒载'},
			       {id: '7', text: '拼载'},
			       {id: '8', text: '表扬'},
			       {id: '9', text: '其他'}
			       ]
		});
		
		//初始化
		init();
		//图表
		function init(){
			$('#tscxTable').jsGrid({
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
        			{name: 'CALL_NAME', title: '投诉人', width: 100, align: 'center'},
        			{name: 'BUSINESS_ITEMTYPE_NAME', title: '投诉类型', width: 100, align: 'center'},
        			{name: 'CALLER_ID', title: '联系方式', width: 100, align: 'center'},
        			{name: 'VEHICLE_PLATE_NUMBER', title: '投诉车辆', width: 80, align: 'center'},
        			{name: 'BUSINESS_STATUS_NAME', title: '处理详情', width: 120, align: 'center'},
        			{name: 'HAPPEN_TIME', title: '投诉时间', width: 120, align: 'center'},
        			{name: 'ACCEPT_TIME', title: '处理时间', width: 120, align: 'center'}
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
            jqxhr=$.ajax({
     	        url:"../../tjfx/tscx",
     	        data:{
     	        	"stime" : $("#tscx_datetimeStart").val(),
     	        	"etime" : $("#tscx_datetimeEnd").val(),
     				"lx" : $("#tscx_tslx option:selected").html()||"全部",
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            		var tscxData = [];
     				console.log(json)
            		all = json.data[0].count;
     				re  = json.data[0].datas;
     				console.log(all)
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.CALL_NAME =  re[i].CALL_NAME;
           					rs.VEHICLE_PLATE_NUMBER =  re[i].VEHICLE_PLATE_NUMBER.replace(".","");
         					rs.BUSINESS_ITEMTYPE_NAME =  re[i].BUSINESS_ITEMTYPE_NAME;
         					rs.HAPPEN_TIME =  formatYYYYMMDD(re[i].HAPPEN_TIME);
         					rs.ACCEPT_TIME =  formatYYYYMMDD(re[i].ACCEPT_TIME);
         					rs.CALLER_ID =  re[i].CALLER_ID;
         					rs.BUSINESS_STATUS_NAME =  re[i].BUSINESS_STATUS_NAME;
         					tscxData.push(rs);
         				}
         				return callback({
                            data: tscxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}

		//查询
		$("#tscx_cx").on('click',function(){
			init();
		});

		//导出
		$("#tscx_dc").on('click',function(){
			var data = {
					"stime" : $("#tscx_datetimeStart").val(),
     	        	"etime" : $("#tscx_datetimeEnd").val(),
     				"lx" : $("#tscx_tslx option:selected").html()||"全部"
				};
				url = "../../tjfx/tscxxlsx?data=" + JSON.stringify(data) , window.open(url)
		});
	});
})(jQuery);
