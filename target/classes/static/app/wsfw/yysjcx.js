var fwts = (function($) {
	$(function () {
		var yysjcxFields = [
            {name: 'gridId', title: '序号', width: 120, align: 'center'},
			{name: 'CPH', title: '车号', width: 120,align:'center'},
			{name: 'YINGYUN', title: '服务资格证号', width: 150,align:'center'},
			{name: 'SCSJ', title: '上车时间', width: 165,align:'center'},
			{name: 'XCSJ', title: '下车时间', width: 165,align:'center'},
			{name: 'YYSJ', title: '营运时间/分钟', width: 130,align:'center'},
			{name: 'ZKLC', title: '载客里程/公里', width: 120,align:'center'},
			{name: 'KCLC', title: '空车里程/公里', width: 120,align:'center'},
			{name: 'DHSJ', title: '等候时间/秒', width: 150,align:'center'},
			{name: 'JIAOYITYPE', title: '交易类型', width: 120,align:'center'},
			{name: 'YYJE', title: '营运金额/元', width: 120,align:'center'}
		];
		$('#yysjcx-datetimeStart').datetimepicker(datetimeDefaultOption);
		$('#yysjcx-datetimeEnd').datetimepicker(datetimeDefaultOption);
		$('#yysjcx-datetimeStart').val(new Date().Format('yyyy-MM-dd 00:00:00'));
		$('#yysjcx-datetimeEnd').val(new Date().Format('yyyy-MM-dd 23:59:59'));
		$('.addTimePeriod, .period').on('click', function () {
			if ($(this).hasClass('addTimePeriod')) $(this).addClass('period').removeClass('addTimePeriod');
			else if ($(this).hasClass('period')) $(this).addClass('addTimePeriod').removeClass('period');
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
		        		
    		function hxx(){
    			var str1=$('#yysjcx-datetimeStart').val();
      			var str2=$('#yysjcx-datetimeEnd').val();
      			if(issh(str1,str2)=='1'){
      				layer.msg('不支持跨越查询',{icon:2});
      				return false;
      			}
    			$('#yusjcxTable').jsGrid({
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
         	        url:"../../getFindAllyusjcx",
         	        data:{
         	        	"start" : $("#yysjcx-datetimeStart").val(),
         				"stop" : $("#yysjcx-datetimeEnd").val(),
         				"cph" : $("#yysjcx-carNumber").val(),
         				"gsm" : $("#yysjcx-enterprise").val(),
         				"pageIndex":filter.pageIndex,
         				"pageSize":filter.pageSize
         	        },
         	        dataType: 'json'
                }).done(function(json) {
                	if(json.code==500100){
                		layer.msg('数据不存在',{icon:2});
                		return callback();
                	}
                		var xxfbData = [];
         				console.log(json)
                		all = json.data[0].count;
         				re = json.data[0].datas;

					for(var i=0;i<re.length;i++){
						var iterm=re[i];
						iterm.gridId=startIndex+i+1;


					}


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
//            			alert("数据异常");
                });
    		}
    		



		
        		$(function () {
        			$(".select2").select2({  
        			  	language: "zh-CN",  //设置 提示语言
        		        tags:true,  
        		        createTag:function (decorated, params) {  
        		            return null;  
        		        },  
        		    });
//        			jqxhr=$.ajax({
//                        type:'post',
//                        url:basePath+ "getFindAllyusjcxName",
//                        data:{"cph":$("#yysjcx-carNumber").val()},
//                        dataType:'json',
//                        timeout:3600000,
//                        success:function (json) {
//                            var data=json.data;
//                           /* if (data.length > 200) data.length = 200;*/
//                            for(var i=0;i<data.length;i++){
//                                data[i].id='浙'+data[i].CP;
//                                data[i].text='浙'+data[i].CP;
//                            }
//                            var fh={};
//                            fh.id="guolv";
//                            fh.text="全部";
//                            data.unshift(fh);
//                            $('#yysjcx-carNumber').select2({
//                                language:'zh-CN',
//                                width: '160',
//                                minimumInputLength: 3,
//                                allowClear:true,
//                                data:data
//                            });
//
//                        }
//
//                    })
//                      jqxhr=$.ajax({
//                        type:'post',
//                        url:basePath+ "getFindAllgsm",
//                        data:{},
//                        dataType:'json',
//                        timeout:3600000,
//                        success:function (json) {
//                            var data=json.data;
//                            for(var i=0;i<data.length;i++){
//                                data[i].id=data[i].GSM;
//                                data[i].text=data[i].GSM;
//                            }
//                            var fh={};
//                            fh.id="null";
//                            fh.text="全部";
//                            data.unshift(fh);
//                            $('#yysjcx-enterprise').select2({
//                                language:'zh-CN',
//                                allowClear:true,
//                                data:data
//                            });
//
//                        }
//
//                    })
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
        					qb.id='null';
        					qb.text='全部';
        					data.unshift(qb);
        					$('#yysjcx-carNumber').select2({
        						data: data,
        						language:'zh-CN',
        						minimumInputLength: 3,
        						allowClear: true
        					});
        				}
        			});
                    jqxhr=$.ajax({
						type: "POST",
						url:"../../wsfw/qycomp",
						data:{},
						dataType: 'json',
						timeout : 3600000,
						success:function(json){
							var data= json.datacomp;
							for (var i = 0; i < data.length; i++) {
								data[i].id=data[i].FGS;
								data[i].text=data[i].FGS;
							}
							var qb={};
							qb.id='null';
							qb.text='全部';
							data.unshift(qb);
							$('#yysjcx-enterprise').select2({
								data: data,
								allowClear: true
								});
						}
					});
        			$('#yusjcxTable').jsGrid({
        				width: 'calc(100% - 2px)',
        				height: 'calc(100% - 2px)',
        				editing: true,
        				sorting: true,
        				paging: false,
        				autoload: true,
        				data: [],
        				fields: yysjcxFields
      								
        			});
        			hxx();
            		//查询
            		$("#select_yyscx").off('click').on('click',function(){
            			hxx();
            		})
            		$("#Export_yysjcx").off('click').on('click',function () {
        				var str1=$('#yysjcx-datetimeStart').val();
              			var str2=$('#yysjcx-datetimeEnd').val();
              			if(issh(str1,str2)=='1'){
              				layer.msg('不支持跨越查询',{icon:2});
              				return false;
              			}
                        layer.confirm('需要导出吗？',{btn:['确认','取消']
                            },function () {
                                layer.msg('导出成功',{icon:1});
                            window.open(basePath+"getExportFindAll?start="+$("#yysjcx-datetimeStart").val()+"&stop="+$("#yysjcx-datetimeEnd").val()+"&cph="+$("#yysjcx-carNumber").val()+"&gsm="+$("#yysjcx-enterprise").val())

                            },function () {
                                layer.msg('已取消导出',{icon:2});
                            }
                        )

                    })
        		        		
        		});
	});
})(jQuery);
