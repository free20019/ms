var khxx = (function($) {
	var all = 0,re;
	$(function () {
		var khxxFields = [
            {name: 'gridId', title: '序号', width: 60, align: 'center'},
		      			{name: 'NAME', title: '司机姓名', width: 100},
		      			{name: 'EVENTNAME', title: '事件名称', width: 120},
		      			{name: 'BREAKTIME', title: '违章时间', width: 120},
		      			{name: 'DEDUCTFEN', title: '扣分', width: 120},
		      			{name: 'CARHAO', title: '车牌号', width: 120},
		      			{name: 'ZHIFA', title: '执法单位', width: 150},
		      			{name: 'FWZH', title: '服务证号', width: 150},
		      		];
		      		/* var khxxData = [
		      			{gridId: '1', sjxm: '司机姓名1', sjmc: '事件名称1', wzsj: '违章时间1', kouf: '扣分1', cphm: '车牌号1', zfdw: '执法单位1'},
		      			{gridId: '2', sjxm: '司机姓名2', sjmc: '事件名称2', wzsj: '违章时间2', kouf: '扣分2', cphm: '车牌号2', zfdw: '执法单位2'},
		      			{gridId: '3', sjxm: '司机姓名3', sjmc: '事件名称3', wzsj: '违章时间3', kouf: '扣分3', cphm: '车牌号3', zfdw: '执法单位3'},
		      			{gridId: '4', sjxm: '司机姓名4', sjmc: '事件名称4', wzsj: '违章时间4', kouf: '扣分4', cphm: '车牌号4', zfdw: '执法单位4'},
		      			{gridId: '5', sjxm: '司机姓名5', sjmc: '事件名称5', wzsj: '违章时间5', kouf: '扣分5', cphm: '车牌号5', zfdw: '执法单位5'}
		      		]; */
		      	/*	function hxx(){
		      			var index =layer.msg('小妹正在努力加载',{
		      				icon: 16
		      				,shade: 0.01,
		      				time: 6000
		      			});
		      			
		      			var url=basePath;
		      	    	var data={};
		      	    	var kssj=$("#khxx-datetimeStart").val();
		      	    	var ffuzh=$("#hrhs-fwzh").val();
		      	    	
		      	    	if(!kssj && !ffuzh){
		      	    	url+="getFindAll";
		      	    	}else{
		      	    		url+="getFindAllName";
		      	    		data.breaktime=kssj;
		      	    		data.fwzh=ffuzh;
		      	    	}
		      	    	
		      		    jqxhr=$.ajax({
		      		    	
		      				url :url,
		      				type : 'post',
		      				data:data,
		      				dataType: 'json',
		      				timeout : 180000,
		      				success:function(data){
		      					var datas = data.data;
		      					for(var i=0; i<datas.length; i++){
		      						var item = datas[i];
		      						item.gridId=i+1;
		      					}
//		       					$('#yhglTable').jsGrid({
//		       						width: '100%',
//		       						height: 'calc(100% - 45px)',
//		       						editing: true,
//		       						sorting: true,
//		       						paging: true,
//		       						autoload: true,
//		       						pageIndex: 1,
//		       					    pageSize: 20,
//		       					    pageButtonCount: 15,
//		       					    pagerFormat: "{first} {prev} {pages} {next} {last}    {pageIndex} / {pageCount}",
//		       					    pagePrevText: "上一页",
//		       					    pageNextText: "下一页",
//		       					    pageFirstText: "首页",
//		       					    pageLastText: "尾页",
//		       					    data: datas,
//		       						fields: yhglFields
//		       					});
		      					$('#khxxTable').jsGrid({
		      						width: 'calc(100% - 2px)',
		      						height: 'calc(100% - 2px)',
		      						editing: true,
		      						sorting: true,
		      						paging: false,
		      						autoload: true,
		      						data: datas,
		      						fields: khxxFields
		      					});
		      				},
		      				error:function(data){
		      				}
		      			});
		      		};*/
		
		
		
		
		
		
		
		
		function hxx(){
			$('#khxxTable').jsGrid({
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
     	        url:"../../getkhxxfinds",
     	        data:{
     	        	"starttime" : $("#khxx-datetimeStart").val(),
     				"storptime" : $("#khxx-datetimeEnd").val(),
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            		var xxfbData = [];
     				console.log(json)
            		all = json.data[0].count;
     				re = json.data[0].datas;
     				//console.log(all)
                for (var i = 0; i < re.length; i++) {
                    var iterm = re[i];
                    iterm.gridId=i+1;}
         			if(json.code == 0){
         				/*for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.title =  re[i].BT;
         					rs.content =  re[i].NR;
           					rs.datetime =  re[i].FBRQ;
         					rs.type =  re[i].LB;
         					xxfbData.push(rs);
         				}*/
         				return callback({
                            data: re,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}
		      		
		      		$("#select_khxx").off('click').on('click',function(){
		      			hxx();
		      		});


      //导出
        $("#Export_khxx").off('click').on('click',function(){
        	layer.confirm('确认导出吗',{btn:['确认','取消']
 			},function () {
                layer.msg('导出成功',{icon:1})
                window.open(basePath +"findswczexcles?starttime="+$("#khxx-datetimeStart").val()+"&storptime="+$("#khxx-datetimeEnd").val())
            },function () {
                layer.msg('已取消导出',{icon:2})
            })
        });
		      		
		      		$(function () {
		      			$('#khxx-datetimeStart').datetimepicker(dateDefaultOption);
		      			$('#khxx-datetimeEnd').datetimepicker(dateDefaultOption);
		      			$('.addTimePeriod, .period').on('click', function () {
		      				if ($(this).hasClass('addTimePeriod')) $(this).addClass('period').removeClass('addTimePeriod');
		      				else if ($(this).hasClass('period')) $(this).addClass('addTimePeriod').removeClass('period');
		      			});
		      			$('#khxxTable').jsGrid({
		      				width: 'calc(100% - 2px)',
		      				height: 'calc(100% - 2px)',
		      				editing: true,
		      				sorting: true,
		      				paging: false,
		      				autoload: true,
		      				data: [],
		      				fields: khxxFields
		      			});
		      			$('.scrollbar-macosx').scrollbar();
		      			hxx();
		      		})
	
	});
})(jQuery);
