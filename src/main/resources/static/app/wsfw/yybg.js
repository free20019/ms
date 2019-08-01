var yybgcx=(function ($) {
	var clfb_map;
	$(function () {
		var bljlFields = [
		                {name: 'ID', title: '序号', width: 60,align:"center"},
		                {name: 'TIME', title: '日期', width: 60,align:"center"},
		      			{name: 'CPH', title: '总营运车辆数', width: 120,align:"center"},
		      			//{name: 'yycl', title: '参与营运总车辆数', width: 140,align:"center"},
		      			{name: 'ZYYCS', title: '总营运次数', width: 120,align:"center"},
		      			{name: 'ZYYJE', title: '总营收金额(元)', width: 120,align:"center"},
		      			{name: 'CCSC', title: '出车总时长(分钟)', width: 120,align:"center"},
		      			{name: 'ZLIC', title: '营运总里程(公里)', width: 120,align:"center"},
		      			{name: 'SZZLC', title: '实载总里程(公里)', width: 120,align:"center"},
		      			{name: 'KSZLC', title: '空驶总里程(公里)', width: 120,align:"center"}
			    		];
		$('#yybg-stateTime').datetimepicker(dateDefaultOption);
		$('#yybg-endTime').datetimepicker(dateDefaultOption);
		$('#yybg-select').on('click', function () {
			findyybg();
		});
		function issh(str1,str2){
			if(str1==''||str2==''){
				return 0;
			}else{				
				if(str1.substring(5,7)==str2.substring(5,7)){
					return 0;
				}else{
					
					return 1;
				}
			}
		}
		function findyybg(){
			var str1=$('#yybg-stateTime').val();
  			var str2=$('#yybg-endTime').val();
  			if(issh(str1,str2)=='1'){
  				layer.msg('不支持跨越查询',{icon:2});
  				return false;
  			}
			var all = 0;
			$('#yybgTable').jsGrid({
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
            jqxhr=jqxhr=$.ajax({
            	type: "POST",
            	 url:"../../getFindAllYybgs",
                 data:{
                     "n" : $("#yybg-year").val(),
                     "y" : $("#yybg-month").val(),
                     "d" : $("#yybg-day").val(),
                     "stime":$("#yybg-stateTime").val(),
 					 "etime":$("#yybg-endTime").val(),
                     "pageIndex":filter.pageIndex,
                     "pageSize":filter.pageSize
                 },
				timeout : 180000,
				dataType: 'json',
            }).done(function(data) {
            	if(data.code==500100){
            		layer.msg('数据不存在',{icon:2});
            		return callback();
            	}
            		var jsycxData = [];
            		all = data[0].count;
     				arr = data[0].datas;
         			if(data.length>0){
         				for(var i = 0; i< data[0].datas.length ;i++){
         					var rs={};
         					rs.ID =  startIndex+i+1;
         					 rs.TIME =  arr[i].TIME;
                             rs.CPH =  arr[i].CPH;
                             rs.ZYYCS =  arr[i].ZYYCS;
                             rs.ZYYJE =  arr[i].ZYYJE;
                             rs.CCSC =  arr[i].CCSC;
                             rs.ZLIC =  arr[i].ZLIC;
                             rs.SZZLC =  arr[i].SZZLC;
                             rs.KSZLC =  arr[i].KSZLC;
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

		$('#yybg-dc').on('click', function () {
			var str1=$('#yybg-stateTime').val();
  			var str2=$('#yybg-endTime').val();
  			if(issh(str1,str2)=='1'){
  				layer.msg('不支持跨越导出',{icon:2});
  				return false;
  			}
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"getFindAllYybgsExports?n="+$("#yybg-year").val()
						+"&y="+$("#yybg-month").val()
						+"&d="+$("#yybg-day").val()
						+"&stime="+$("#yybg-stateTime").val()
						+"&etime="+$("#yybg-endTime").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});

	$(function () {
		$(".select2").select2({  
		  	language: "zh-CN",  //设置 提示语言
	        tags:true,  
	        minimumResultsForSearch: -1,
	        createTag:function (decorated, params) {  
	            return null;  
	        },  
	    });
		$('#yybg-year').select2({
			language: 'zh-CN',
			minimumResultsForSearch: -1,
			data: (function () {
				var date = new Date();
				var thisYear = date.getFullYear();
				console.info(thisYear);
				var years = [];
				for (var year = thisYear; year >= 2018; year--) {
					if (year === thisYear) years.push({id: year, text: year + '年', selected: true});
					else years.push({id: year, text: year + '年'});
				}
				return years;
			})()
		});
		$('#yybg-month').select2({
			language: 'zh-CN',
			minimumResultsForSearch: -1,
            allowClear:true,
			data: (function () {
				var date = new Date();
				var thisMonth = date.getMonth()+1;
				console.info(thisMonth);
				var months = [];
				 var fh={};
                 fh.id="null";
                 fh.text="月份";
                 months.unshift(fh);
				for (var month = 1; month <= 12; month++) {
					if(month<10){						
						if (month === thisMonth) months.push({id: '0'+month, text: '0'+month + '月', selected: true});
						else months.push({id: '0'+month, text: '0'+month + '月'});
					}else{	
						if (month === thisMonth) months.push({id: '0'+month, text: month + '月', selected: true});
						else months.push({id: month, text: month + '月'});
					}
				}
				return months;
			})()
		});
		findgz();
		findyybg();
		$('#yybg-year').on('change',function(){
			findgz();
		});
		$('#yybg-month').on('change',function(){
			findgz();
		});
		function findgz(){
			$('#yybg-day').html("");
			var year=$("#yybg-year").val();
			var month=$("#yybg-month").val();
			if(month==''){
				return false;
			}
			var date = new Date(year,month, 0);
			var text='';
			text +='<option value="null">日</option>';
			for (var day = 0; day < date.getDate(); day++) {
				if(day<9){			
					text +='<option value="0'+(day+1)+'">0'+(day+1)+'日</option>';
				}else{
					text +='<option value="'+(day+1)+'">'+(day+1)+'日</option>';
				}
			}
			$('#yybg-day').html(text);
		}
	})
	})
})(jQuery)
