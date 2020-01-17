var ysrlfx = (function($) {
	var all = 0,re;
	$(function () {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 2;
		
		$('#ysrlfx_stime').datetimepicker(datetimeDefaultOption);
		$('#ysrlfx_etime').datetimepicker(datetimeDefaultOption);
		$('#ysrlfx_stime').val(new Date(today - oneday).Format('yyyy-MM-dd hh:mm:ss'));
		$('#ysrlfx_etime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		
		$(".select2").select2({  
		  	language: "zh-CN",  //设置 提示语言
	        tags:true,  
	        createTag:function (decorated, params) {  
	            return null;  
	        },  
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
				$('#ysrlfx_cphm').select2({
					data: data,
					language:'zh-CN',
                    minimumInputLength: 3,
					allowClear: true
					});
			}
		});
		
		//初始化
		init();

		//图表
		function init(){
			$('#ysrlfxTable').jsGrid({
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
        			{name: 'VHIC', title: '车牌号码', width: 80, align: 'center'},
        			{name: 'S_TIME', title: '开始时间', width: 100, align: 'center'},
        			{name: 'E_TIME', title: '结束时间', width: 100, align: 'center'},
        			{name: 'S_MILE', title: 'GPS里程', width: 100, align: 'center'},
        			{name: 'R_MILE_2', title: '最短距离里程', width: 80, align: 'center'},
        			{name: 'R_MILE', title: '最短时间里程', width: 80, align: 'center'},
        			{name: 'RDONE', title: 'GPS里程/最短距离里程', width: 80, align: 'center'},
        			{name: 'RDTWO', title: 'GPS里程/最短时间里程', width: 80, align: 'center'},
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
			console.log(filter)
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
     	        url:"../../tjfx/ysrlfx",
     	        data:{
     				"stime" : $("#ysrlfx_stime").val(),
     				"etime" : $("#ysrlfx_etime").val(),
     				"cphm" :  $("#ysrlfx_cphm option:selected").html().trim()||"全部",
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            	console.log(json)
            		var ysrlfxData = [];
            		all = json.data[0].count;
     				re = json.data[0].datas;
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.VHIC =  re[i].VHIC.indexOf("浙")>-1?re[i].VHIC:("浙"+re[i].VHIC);
           					rs.S_TIME =  formatYYYYMMDDHHMISS(re[i].S_TIME);
         					rs.E_TIME =  formatYYYYMMDDHHMISS(re[i].E_TIME);
         					rs.S_MILE =  re[i].S_MILE;
         					rs.R_MILE_2 =  re[i].R_MILE_2;
         					rs.R_MILE =  re[i].R_MILE;
         					rs.RDONE =  re[i].RDONE;
         					rs.RDTWO =  re[i].RDTWO;
         					ysrlfxData.push(rs);
         				}
         				return callback({
                            data: ysrlfxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}

		//查询
		$("#ysrlfx_cx").on('click',function(){
			init();
		});
	});
})(jQuery);
