var gzsbcx = (function($) {
	var all = 0,re;
	$(function () {
		
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
				$('#gzsbcx_cph').select2({
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
				$('#gzsbcx_yh').select2({
					data: data,
					allowClear: true
					});
			}
		});
		
		//初始化
		init();

		//图表
		function init(){
			$('#gzsbcxTable').jsGrid({
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
                    {name: 'VEHI_NO', title: '车牌号', width: 100, align: 'center'},
                    {name: 'COMP_NAME', title: '业户名称', width: 200, align: 'center'},
        			{name: 'MT_NAME', title: '终端名称', width: 100, align: 'center'},
        			{name: 'MDT_SUB_TYPE', title: '终端类型', width: 100, align: 'center'},
        			{name: 'MDT_NO', title: '终端编号', width: 140, align: 'center'},
        			{name: 'RM_ID', title: '故障类型', width: 100, align: 'center'},
        			{name: 'RR_TIME', title: '故障时间', width: 160, align: 'center'}
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
     	        url:"../../tjfx/gzsbcx",
     	        data:{
     	       	"cph" : $("#gzsbcx_cph option:selected").html()||"全部",
 				"yh" : $("#gzsbcx_yh option:selected").html()||"全部",
     				"zdlx" : $("#gzsbcx_zdlx").val(),
     				"zdbh" : $("#gzsbcx_zdbh").val(),
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            		var gzsbcxData = [];
            		all = json.data[0].count;
     				re = json.data[0].datas;
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.MT_NAME =  re[i].mt_name;
           					rs.MDT_NO =  re[i].mdt_no;
         					rs.MDT_SUB_TYPE =  re[i].mdt_sub_type;
         					rs.VEHI_NO =  re[i].vehi_no;
         					rs.COMP_NAME =  re[i].comp_name;
         					rs.RM_ID =  re[i].rm_id;
         					rs.RR_TIME =  formatYYYYMMDD(re[i].rr_time);
         					gzsbcxData.push(rs);
         				}
         				return callback({
                            data: gzsbcxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}

		//查询
		$("#gzsbcx_cx").on('click',function(){
			init();
		});

		//导出
		$("#gzsbcx_dc").on('click',function(){
			var data = {
					"cph" : $("#gzsbcx_cph option:selected").html()||"全部",
     				"yh" : $("#gzsbcx_yh option:selected").html()||"全部",
     				"zdlx" : $("#gzsbcx_zdlx").val(),
     				"zdbh" : $("#gzsbcx_zdbh").val()
				};
				url = "../../tjfx/gzsbcxxlsx?data=" + JSON.stringify(data) , window.open(url)
		});
	});
})(jQuery);
