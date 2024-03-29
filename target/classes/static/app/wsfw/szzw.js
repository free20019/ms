var szzw = (function($) {
	var all = 0,re;
	$(function () {
		var swsjFields = [
            {name: 'gridId', title: '序号', width: 60, align: 'center'},
		      			{name: 'CPH', title: '车牌号', width: 120, align: 'center'},
		      			{name: 'SJXM', title: '司机姓名', width: 120, align: 'center'},
		      			{name: 'SZXM', title: '失主姓名', width: 150, align: 'center'},
		      			{name: 'YSWP', title: '遗失物品', width: 150, align: 'center'},
		      			{name: 'CCSJ', title: '乘车时间', width: 250, align: 'center'},
		      			{name: 'QSDD', title: '起始地点', width: 250, align: 'center'},
		      			{name: 'WZ', title: '位置', width: 120, align: 'center'},
		      			{name: 'XZJG', title: '寻找结果', width: 250, align: 'center'},
		      			{name: 'caozuo', title: '操作',
		      				itemTemplate: function (_,item) {
		      					var style = {marginRight: '4px'};
		      					return [
		      						$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
		      							$('#swsj-dialog').modal('show');
		      							$('#swsj-dialog .modal-title').text('详情');
		      							$('#swsj-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
		      							$('#swsj-dialog-save').hide();
		      							
		      							$("#szzsu_cph").text(item.CPH);
		      							$("#szzsu_sjxm").text(item.SJXM);
		      							$("#szzsu_szxm").text(item.SZXM);
		      							$("#szzsu_yswp").text(item.YSWP);
		      							$("#szzsu_ccsj").text(item.CCSJ);
		      							$("#szzsu_qssj").text(item.QSDD);
		      							$("#szzsu_wz").text(item.WZ);
		      							$("#szzsu_xzjg").text(item.XZJG);
		      							
		      						}),
		      						$('<a>').addClass('btn btn-primary btn-xs').text('修改').css(style).on('click', function () {
		      							$('#swsj-dialog').modal('show');
		      							$('#swsj-dialog .modal-title').text('修改');
		      							$('#swsj-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      							$('#swsj-dialog-save').show();
		      							
		      							$("#szzsu_cph1").val(item.CPH);
		      							$("#szzsu_sjxm1").val(item.SJXM);
		      							$("#szzsu_szxm1").val(item.SZXM);
		      							$("#szzsu_yswp1").val(item.YSWP);
		      							$("#szzsu_ccsj1").val(item.CCSJ);
		      							$("#szzsu_qssj1").val(item.QSDD);
		      							$("#szzsu_wz1").val(item.WZ);
		      							$("#szzsu_xzjg1").val(item.XZJG);
		      							
		      							
		      							$("#swsj-dialog-save").off('click').on('click',function(){
		      								jqxhr=$.ajax({
		      									url:basePath +"getUpdateSzzsw",
		      									type:'post',
		      									dataType:'json',
		      									data:{
		      										"bid":item.BID,
		      										"cph":$("#szzsu_cph1").val(),
		      									    "sjxm":$("#szzsu_sjxm1").val(),
		      									    "szxm":$("#szzsu_szxm1").val(),
		      									    "yswp":$("#szzsu_yswp1").val(),
		      									    "ccsj":$("#szzsu_ccsj1").val(),
		      									    "qssj":$("#szzsu_qssj1").val(),
		      									    "wz":$("#szzsu_wz1").val(),
		      									    "xzjg":$("#szzsu_xzjg1").val(),
		      									},
		      									timeout:180000,
		      									success:function(data){
		      										if(data>0){
		      											$('#swsj-dialog').modal('hide');
		      											layer.msg('修改成功',{icon:1});
		      											hxx();
		      										}else{
		      											layer.msg('修改失败',{icon:2});
		      										}
		      									}
		      								});
		      								
		      							});
		      							
		      							
		      							
		      							
		      							$('#swsj-dialog-fsrq input.form-control').datetimepicker(dateDefaultOption);
		      							$('#swsj-dialog-barq input.form-control').datetimepicker(dateYearDefaultOption);
		      						}),
		      						$('<a>').addClass('btn btn-danger btn-xs').text('删除').on('click', function () {


                                        layer.confirm('确认删除吗？？',{btn:['确认','取消']
                                        },function(){
                                        	
                                        	
                                        	jqxhr=$.ajax({
    		      								url:basePath +"getDeleteSzzsw",
    		      								type:'post',
    		      								dataType:'json',
    		      								data:{"bid":item.BID},
    		      								timeout:180000,
    		      								success:function(data){
    		      									if(data>0){
    		      										layer.msg('删除成功',{icon: 1});
    		      										hxx();
    		      									}else{
    		      										layer.msg('删除失败',{icon: 1});
    		      									}
    		      								}
    		      							});
                                        	
                                        },function(){
                                        	layer.msg('删除已取消',{icon: 1});
                                        }
                                        )

		      							
		      							
		      						})
		      					];
		      				}, width: 135}
		      		];
		      		var swsjData = [
//		      			{cphm: '车牌号1', sjmc: '姓名1', szmc: '违章时间1', yswp: '违章地点1', ccsj: '违章内容1', qsdd: '处理结果1', weiz: '扣分1', xzjg: '执法机关罚款1'},
//		      			{cphm: '车牌号2', sjmc: '姓名2', szmc: '违章时间2', yswp: '违章地点2', ccsj: '违章内容2', qsdd: '处理结果2', weiz: '扣分2', xzjg: '执法机关罚款2'},
//		      			{cphm: '车牌号3', sjmc: '姓名3', szmc: '违章时间3', yswp: '违章地点3', ccsj: '违章内容3', qsdd: '处理结果3', weiz: '扣分3', xzjg: '执法机关罚款3'},
//		      			{cphm: '车牌号4', sjmc: '姓名4', szmc: '违章时间4', yswp: '违章地点4', ccsj: '违章内容4', qsdd: '处理结果4', weiz: '扣分4', xzjg: '执法机关罚款4'},
//		      			{cphm: '车牌号5', sjmc: '姓名5', szmc: '违章时间5', yswp: '违章地点5', ccsj: '违章内容5', qsdd: '处理结果5', weiz: '扣分5', xzjg: '执法机关罚款5'},
		      		];
//		      		var resetQueryConditions = function (e) {
//		      			$('.panel-queryBar .form-control').val('');
//		      			$('#swsj-cphm').select2('val','0');
//		      		};
		      		var addSwsj = function (e) {
		      			$('#swsj-dialog').modal('show');
		      			$('#swsj-dialog .modal-title').text('添加');
		      			$('#swsj-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      			$('#swsj-dialog-save').show();
		      			
		      			$("#swsj-dialog-save").off('click').on('click',function(){
		      				jqxhr=$.ajax({
		      					url :basePath + "getInsertJszzs",
		      					type :'post',
		      					dataType :'json',
		      					data:{
		      						"cph":$("#szzsu_cph1").val(),
		      					    "sjxm":$("#szzsu_sjxm1").val(),
		      					    "szxm":$("#szzsu_szxm1").val(),
		      					    "yswp":$("#szzsu_yswp1").val(),
		      					    "ccsj":$("#szzsu_ccsj1").val(),
		      					    "qssj":$("#szzsu_qssj1").val(),
		      					    "wz":$("#szzsu_wz1").val(),
		      					    "xzjg":$("#szzsu_xzjg1").val(),
		      					},
		      					timeout :180000,	
		      					success:function(data){
		      						if(data>0){
		      							layer.msg('添加成功',{icon:1});
                                        $('#swsj-dialog').modal('hide');
                                        hxx();
		      						}else{
                                        layer.msg('添加失败',{icon:2});
		      						}
		      					}
		      				});
		      			});
		      		};
		      		
		      		
		      		
		      		
		      		//展示页面
		      		/* function hxx(){
		      				var index =layer.msg('小妹正在努力加载',{
		      					icon: 16
		      					,shade: 0.01,
		      					time: 6000
		      				});
		      			var url=basePath;
		      			var data={};
		      			var cphm=$("#swsj-cphm").val();
		      			var ximi=$("#swsj-ximi").val();
		      			
		      			if(!cphm && !ximi){
		      				url+="getSelectFindAllJszzsw";
		      			}else{
		      				url+="getSelectNameJszzs";
		      				data.cph=cphm;
		      				data.sjxm=ximi;
		      			}
		      			
		      			
		      			jqxhr=$.ajax({
		      				url :url,
		      				type :'post',
		      				dataType :'json',
		      				data:data,
		      				tiomout :180000,
		      				success:function(data){
		      					datas=data.data;
		      					for(var i=0;i<datas.length;i++){
		      						var item=datas[i];
		      						
		      					}
		      					$('#swsjTable').jsGrid({
		      						width: 'calc(100% - 2px)',
		      						height: 'calc(100% - 2px)',
		      						editing: true,
		      						sorting: true,
		      						paging: false,
		      						autoload: true,
		      						data: datas,
		      						fields: swsjFields
		      					});
		      				},
		      				error:function(data){
		      				}
		      			});
		      			
		      			
		      		} */
		      		
		      		
		      		function hxx(){
		    			$('#swsjTable').jsGrid({
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
//		    			console.log(filter)
		                var startIndex = (filter.pageIndex - 1) * filter.pageSize;
		                jqxhr=$.ajax({
		         	        url:"../../getSelectFindAllHXX",
		         	        data:{
		         	        	"cph" : $("#swsj-cphm").val(),
		         				"xm" : $("#swsj-ximi").val(),
		         				"pageIndex":filter.pageIndex,
		         				"pageSize":filter.pageSize
		         	        },
		         	        dataType: 'json'
		                }).done(function(json) {
		                		var xxfbData = [];
//		         				console.log(json)
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
//		            			alert("数据异常");
		                });
		    		}
		      		
		      		$("#select_sousuo").off('click').on('click',function(){
		      			hxx();
		      		});



		      		$("#swsj-Export").off('click').on('click',function(){

		      			layer.confirm('需要导出吗 ?',{btn:['确认','取消']
		      			},function () {
							layer.msg('导出成功？',{icon:1});
                            window.open(basePath + "getszzwExport?cph="+$("#swsj-cphm").val()+"&xm="+$("#swsj-ximi").val())
                        },function () {
							layer.msg('已取消导出',{icon:2});
                        })

		      		});





		      		$(function () {
		      			$(".select2").select2({  
	        			  	language: "zh-CN",  //设置 提示语言
	        		        tags:true,  
	        		        createTag:function (decorated, params) {  
	        		            return null;  
	        		        },  
	        		    });
		      			$('.addTimePeriod, .period').on('click', function () {
		      				if ($(this).hasClass('addTimePeriod')) $(this).addClass('period').removeClass('addTimePeriod');
		      				else if ($(this).hasClass('period')) $(this).addClass('addTimePeriod').removeClass('period');
		      			});


                        jqxhr=$.ajax({
                            type:'post',
                            url:basePath+ "SelectFindAllJszzswName",
                            data:{"cph":$("#swsj-cphm").val()},
                            dataType:'json',
                            timeout:3600000,
                            success:function (json) {
                                var data=json.data;
                                for(var i=0;i<data.length;i++){
                                    data[i].id=data[i].CPH;
                                    data[i].text=data[i].CPH;
//                                    console.info("silly",data[i]);
                                }
                                var fh={};
                                fh.id="filter";
                                fh.text="全部";
                                data.unshift(fh);
                                $('#swsj-cphm').select2({
                                    language:'zh-CN',
                                    width: '160',
                                    minimumInputLength: 3,
                                    allowClear:true,
                                    data:data
                                });

                            }

                        })

                        /*	$('#swsj-cphm').select2({
                                language: 'zh-CN',
                              width : 160,
                              ajax: {
                                  url: basePath + "SelectFindAllJszzswName",
                                  dataType: 'json',
                                  data: function (params) {
                                      var query = {cph: params.term };
                                      return query;
                                  },
                                  processResults:function (res) {
                                      var data=_.map(res.data,function (item) {
                                          return {id:item.CPH,text:item.CPH}
                                      });
                                      return{
                                          results:data
                                      };
                                  }
                              },
                            });*/




		      			$('#swsj-dialog-sglx select.form-control').select2({
		      				language: 'zh-CN',
		      				width: '168',
		      				minimumResultsForSearch: -1,
		      				data: [
		      					{id: '1', text: '事故类别1'},
		      					{id: '2', text: '事故类别2'}
		      				]
		      			});
		      			$('#swsj-dialog-wpzt input.form-control').datetimepicker(dateDefaultOption);
//		      			$('#swsj-reset').on('click', resetQueryConditions);
		      			$('#swsj-add').on('click', addSwsj);
		      			$('#swsj-dialog').on('hidden.bs.modal', function (e) {
		      				$(this).find('input[type=text].form-control, textarea.form-control').val('');
		      				$(this).find('select.form-control').val('').trigger('change');
		      				$(this).find('div.form-control').text('');
		      			});
		      			$('#swsjTable').jsGrid({
		      				width: 'calc(100% - 2px)',
		      				height: 'calc(100% - 2px)',
		      				editing: true,
		      				sorting: true,
		      				paging: false,
		      				autoload: true,
		      				data:swsjData,
		      				fields: swsjFields
		      			});
		      			$('.scrollbar-macosx').scrollbar();
		      			hxx();
		      		})
	
	});
})(jQuery);
