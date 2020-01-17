var wzcx = (function($) {
	var all = 0,re;
	$(function () {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 24;

		$('#wzcx_datetimeStart').datetimepicker(dateDefaultOption);
		$('#wzcx_datetimeEnd').datetimepicker(dateDefaultOption);
		$('#wzcx_datetimeStart').val(new Date(today - oneday).Format('yyyy-MM-dd'));
		$('#wzcx_datetimeEnd').val(new Date().Format('yyyy-MM-dd'));

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
				$('#wzcx_cph').select2({
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
			$('#wzcxTable').jsGrid({
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
        			{name: 'PARTY_NAME', title: '当事人姓名', width: 60, align: 'center'},
        			{name: 'AUTO_NUM', title: '车号', width: 60, align: 'center'},
        			{name: 'INTEGRAL', title: '扣分', width: 40, align: 'center'},
        			{name: 'ILLEGAL_TIME', title: '违章时间', width: 100, align: 'center'},
        			{name: 'ILLEGAL_TIME', title: '执法时间', width: 100, align: 'center'},
        			{name: 'CASE_REASON', title: '案件原因', width: 200, align: 'center'},
        			{name: 'ORGAN', title: '执法部门', width: 80, align: 'center'},
        			{name: 'AREA', title: '执法区域', width: 60, align: 'center'},
        			{name: 'LIENCE_NUM', title: '经营许可证号', width: 100, align: 'center'},
        			{
        				title: '详情',
                        itemTemplate: function(_, item) {
                        	var style = {};
                        	return [
								$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
									$('#wzcx-dialog').modal('show');
									$('#wzcx-dialog .modal-title').text('详情');
									$('#wzcx-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
									$('#wzcx-dialog-save').hide();
									
									for(var i = 0; i< re.length ;i++){
			         					if(re[i].AUTO_NUM.replace(".","") == item.AUTO_NUM){
			         						var s = re[i];
			         						$('#wzcx-dialog-ximi div').html("").html(s.PARTY_NAME);
			         						$('#wzcx-dialog-zjhm div').html("").html(s.PARTY_NUMBER.replace(".",""));
			         						$('#wzcx-dialog-jyfw div').html("").html(formatYYYYMMDD(s.PARTY_BIRTHDAY));
			         						$('#wzcx-dialog-lxdh div').html("").html(s.PARTY_PHONE);
			         						$('#wzcx-dialog-txdz div').html("").html(s.PARTY_ADDR);
			         						$('#wzcx-dialog-yhmc div').html("").html(s.COM_NAME);
			         						$('#wzcx-dialog-yhdz div').html("").html(s.COM_ADDR);
			         						$('#wzcx-dialog-yyxk div').html("").html(s.LIENCE_NUM);
			         						$('#wzcx-dialog-cyzg div').html("").html(s.CERTI_NUM);
			         						$('#wzcx-dialog-icka div').html("").html(s.IC_SCORE);
			         						
			         						$('#wzcx-dialog-zfjg div').html("").html(s.ORGAN);
			         						$('#wzcx-dialog-zfbm div').html("").html(s.AREA);
			         						$('#wzcx-dialog-zfre div').html("").html(s.MARSHALS_NAME);
			         						$('#wzcx-dialog-ajlb div').html("").html(s.CASE_CATEGORY);
			         						$('#wzcx-dialog-ajzt div').html("").html(s.CASE_STATUS);
			         						$('#wzcx-dialog-ajyy div').html("").html(s.CASE_REASON);
			         						$('#wzcx-dialog-kouf div').html("").html(s.INTEGRAL);
			         						$('#wzcx-dialog-fkje div').html("").html(s.FINE);
			         						$('#wzcx-dialog-zfsj div').html("").html(formatYYYYMMDD(s.LEGAL_TIME));
			         						$('#wzcx-dialog-wzsj div').html("").html(formatYYYYMMDD(s.ILLEGAL_TIME));
			         						$('#wzcx-dialog-clys div').html("").html(formatYYYYMMDD(s.UPD_DATE));
			         						$('#wzcx-dialog-clda div').html("").html(s.UPD_PERSON);
			         					}
			         				}
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
			$('#wzcx-add').on('click', function (e) {
				$('#wzcx-dialog').modal('show');
				$('#wzcx-dialog .modal-title').text('添加');
				$('#wzcx-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
				$('#wzcx-dialog-save').show();
			});
		}

		function res(filter, callback){
//			console.log(filter)
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
     	        url:"../../tjfx/wzcx",
     	        data:{
     	        	"stratTime" : $("#wzcx_datetimeStart").val(),
     	        	"endTime" : $("#wzcx_datetimeEnd").val(),
     	        	"xm" : $("#wzcx_xm").val(),
     				"cph" : $("#wzcx_cph option:selected").html()||"全部",
     				"yszh" : $("#wzcx_cyzgz").val(),
     				"jyxkzh" : $("#wzcx_jyxkzh").val(),
     				"area" : $("#wzcx_area").val(),
     				"part" : $("#wzcx_part").val(),
     				"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
     	        },
     	        dataType: 'json'
            }).done(function(json) {
            		var wzcxData = [];
     				console.log(json)
            		all = json.data[0].count;
     				re  = json.data[0].datas;
     				console.log(all)
         			if(json.code == 0){
         				for(var i = 0; i< re.length ;i++){
         					var rs={};
         					rs.ID = startIndex+i+1;
         					rs.PARTY_NAME =  re[i].PARTY_NAME;
           					rs.AUTO_NUM =  re[i].AUTO_NUM.replace(".","");
         					rs.INTEGRAL =  re[i].INTEGRAL;
         					rs.ILLEGAL_TIME =  formatYYYYMMDD(re[i].ILLEGAL_TIME);
         					rs.LEGAL_TIME =  formatYYYYMMDD(re[i].LEGAL_TIME);
         					rs.CASE_REASON =  re[i].CASE_REASON;
         					rs.ORGAN =  re[i].ORGAN;
         					rs.AREA =  re[i].AREA;
         					rs.LIENCE_NUM =  re[i].LIENCE_NUM;
         					wzcxData.push(rs);
         				}
         				return callback({
                            data: wzcxData,
                            itemsCount: all
                        });
         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}

		//查询
		$("#wzcx_cx").on('click',function(){
			init();
		});

		//导出
		$("#wzcx_dc").on('click',function(){
			var data = {
					"stratTime" : $("#wzcx_datetimeStart").val(),
     	        	"endTime" : $("#wzcx_datetimeEnd").val(),
     	        	"xm" : $("#wzcx_xm").val(),
     				"cph" : $("#wzcx_cph option:selected").html()||"全部",
     				"yszh" : $("#wzcx_cyzgz").val(),
     				"jyxkzh" : $("#wzcx_jyxkzh").val(),
     				"area" : $("#wzcx_area").val(),
     				"part" : $("#wzcx_part").val(),
				};
				url = "../../tjfx/wzcxxlsx?data=" + JSON.stringify(data) , window.open(url)
		});
	});
})(jQuery);
