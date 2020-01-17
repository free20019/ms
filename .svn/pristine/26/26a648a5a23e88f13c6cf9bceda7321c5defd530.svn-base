var hrhs = (function($) {
	var all = 0,re;
	$(function () {
		
	
		var hrhsFields = [
                        {name: 'gridId', title: '序号', width: 120, align: 'center'},
		      			{name: 'CPH', title: '车牌号', width: 120},
		      			{name: 'XM', title: '姓名', width: 120},
		      			{name: 'SJ', title: '时间', width: 150},
		      			{name: 'NR', title: '内容', width: 250},
		      			{name: 'JLLB', title: '奖励类别', width: 120},
		      			{name: 'JLJE', title: '奖励金额', width: 120},
		      			{name: 'JFQK', title: '加分情况', width: 120},
		      			{name: 'JLLX', title: '奖励类型', width: 120},
		      			{name: 'caozuo', title: '操作',
		      				itemTemplate: function (_,item) {
		      					var style = {marginRight: '4px'};
		      					return [
		      						$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
		      							$('#hrhs-dialog').modal('show');
		      							$('#hrhs-dialog .modal-title').text('详情');
		      							$('#hrhs-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
		      							$('#hrhs-dialog-save').hide();
		      							/*详情  */
		      							$('#hrhs_cph').text(item.CPH);
		      							$('#hrhs_xm').text(item.XM);
		      							$('#hrhs_sj').text(item.SJ);
		      							$('#hrhs_nr').text(item.NR);
		      							$('#hrhs_jfqk').text(item.JFQK);
		      							$('#hrhs_jllb').text(item.JLLB);
		      							$('#hrhs_jlje').text(item.JLJE);
		      							$('#hrhs_jllx').text(item.JLLX);
		      							
		      						}),
		      						$('<a>').addClass('btn btn-primary btn-xs').text('修改').css(style).on('click', function () {
		      							$('#hrhs-dialog').modal('show');
		      							$('#hrhs-dialog .modal-title').text('修改');
		      							$('#hrhs-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      							$('#hrhs-dialog-save').show();
		      							///*修改
		      							$('#hrhs_cph1').val(item.CPH);
		      							$('#hrhs_xm1').val(item.XM);
		      							$('#hrhs_sj1').val(item.SJ);
		      							$('#hrhs_nr1').val(item.NR);
		      							$('#hrhs_jfqk1').val(item.JFQK);
		      							$('#hrhs_jllb1').val(item.JLLB).trigger('change');
		      							$('#hrhs_jlje1').val(item.JLJE);
		      							$('#hrhs_jllx1').val(item.JLLX).trigger('change');
		      							

		      						 	$('#hrhs-dialog-shij input.form-control').datetimepicker(dateDefaultOption);
		       							$('#hrhs-dialog-jllb select.form-control').select2({
		      								language: 'zh-CN',
		       								width: '168',
		       								minimumResultsForSearch: -1,
		       								data: [
		       									{id: '好人好事', text: '好人好事'},
		       									{id: '吃苦赖劳', text: '吃苦赖劳'},
		       									{id: '艰苦奋斗', text: '艰苦奋斗'},
		       								]
		       							});
		      							 $('#hrhs-dialog-jllx select.form-control').select2({
		      								language: 'zh-CN',
		      								width: '168',
		      								minimumResultsForSearch: -1,
		      								data: [
		      									{id: '见义勇为行', text: '见义勇为行'},
		      									{id: '喜欢学习', text: '喜欢学习'}
		      								]
		      							});
		      									
		      						 	 $("#hrhs-dialog-save").off('click').on('click',function(){
		      									jqxhr=$.ajax({
		      										url:basePath +"getUpdateKhrhs",
		      										type:'post',
		      										dataType:'json',
		      										timeout:180000,
		      										data:{
		      											"bid":item.BID,
		      											"cph":$('#hrhs_cph1').val(),
		      											"xm":$('#hrhs_xm1').val(),
		      											"sj":$('#hrhs_sj1').val(),
		      											"nr":$('#hrhs_nr1').val(),
		      											"jllb":$('#hrhs_jllb1 option:selected').html(),
		      											"jlje":$('#hrhs_jlje1').val(),
		      											"jfqk":$('#hrhs_jfqk1').val(),
		      											"jllx":$('#hrhs_jllx1 option:selected').text(),
		      											
		      										},
		      										success:function(data){
		      											if(data>0){
		      												$('#hrhs-dialog').modal('hide');
		      												layer.msg('修改成功',{icon:1});
		      												hxx();
		      											}
		      										}
		      										
		      									});
		      									
		      								});
		      								
		      						
		      							
		      						}),
		      						$('<a>').addClass('btn btn-danger btn-xs').text('删除').on('click', function () {
		      							
		      							layer.confirm('亲 确认取消吗 ？',{btn :['确认','取消']
		      							},function(){
		      								jqxhr=$.ajax({
			      								url:basePath + "getdeletehrhs",
			      								type:'post',
			      								dataType:'json',
			      								data:{"bid":item.BID},
			      								success:function(data){
			      									if(data>0){
			      										layer.msg('删除成功',{icon:1});
			      										hxx();
			      									}else{
			      										layer.msg('已取消删除',{icon:2});
			      									}
			      								}
			      								
			      							});
		      								
		      								
		      							},function(){
		      								layer.msg('已取消删除',{icon:2});
		      								
		      							})
		      							
		      							/**/
		      							
		      							
		      						})
		      					];
		      				}, width: 135}
		      		];

//		      		var resetQueryConditions = function (e) {
//		      			$('#hrhs-siji').val('');
//		      			$("#hrhs-cphm").select2('val','0');
//		      		};
		      		var addhrhs = function (e) {
		      			$('#hrhs-dialog').modal('show');
		      			$('#hrhs-dialog .modal-title').text('添加');
		      			$('#hrhs-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      			$('#hrhs-dialog-save').show();
		      			$('#1').datetimepicker(dateDefaultOption);
		      			
		      			
		      			//添加
		      			$("#hrhs-dialog-save").off('click').on('click',function(){
		      				
		      			jqxhr=$.ajax({
		      				url : basePath + "getInserthrhs",
		      				type:'post',
		      				dataType:'json',
		      				timeout:180000,
		      				data:{
		      					"cph":$('#hrhs_cph1').val(),
		      					"xm":$('#hrhs_xm1').val(),
		      					"sj":$('#hrhs_sj1').val(),
		      					"nr":$('#hrhs_nr1').val(),
		      					"jllb":$('#hrhs_jllb1 option:selected').html(),//option:selected').html(),
		      					"jlje":$('#hrhs_jlje1').val(),
		      					"jfqk":$('#hrhs_jfqk1').val(),
		      					"jllx":$('#hrhs_jllx1 option:selected').html(),
		      				},
		      				success:function(data){
		      					if(data>0){		      						
		      						layer.msg('添加成功',{icon:1});
		      						$('#hrhs-dialog').modal('hide');
		      						hxx();
		      					}else{
                                    layer.msg('添加失败',{icon:2})
								}
		      				}
		      			});
		      				
		      			});
		      			
		      			
		       			$('#hrhs-dialog-jllb select.form-control').select2({
		      				language: 'zh-CN',
		       				width: '168',
		       				minimumResultsForSearch: -1,
		       				data: [
		                              {id: '1', text: '好人好事'},
		      	                    {id: '2', text: '吃苦赖劳'},
		                          	{id: '3', text: '艰苦奋斗'}
		       				]
		       			});
		      			 $('#hrhs-dialog-jllx select.form-control').select2({
		      				language: 'zh-CN',
		      				width: '168',
		      				minimumResultsForSearch: -1,
		      				data: [
		      					{id: '1', text: '见义勇为行' , selected:'true'},
		      					{id: '2', text: '喜欢学习'}
		      				]
		      			});
		      		};
		      		$('#hrhs-Query').on('click', function(){
		      			hxx();
		      		})
		      		/*function hxx(){
		      			var index =layer.msg('正在努力加载',{
		      				icon: 16
		      				,shade: 0.01
		      			});
		      			
		      			var url=basePath;
		      			var data={};
		      			var cphm=$("#hrhs-cphm").val();
		      			var siji=$("#hrhs-siji").val();
		      			if(!cphm && !siji){
		      				url+="gethrhs";
		      			}else{
		      				url+="getSelectNamehrhs";
		      				data.cph=cphm;
		      				data.sj=siji;
		      			}
		      			
		      		    jqxhr=$.ajax({
		      				url : url,
		      				type : 'post',
		      				data: data,
		      				dataType: 'json',
		      				timeout : 180000,
		      				success:function(data){
		      					var datas = data.data;
		      					console.log(datas)
		      					for(var i=0; i<datas.length; i++){
		      						var item = datas[i];
		      						item.gridId=i+1;
		      					}
		      					$('#hrhsTable').jsGrid({
		      						width: 'calc(100% - 2px)',
		      						height: 'calc(100% - 2px)',
		      						editing: true,
		      						sorting: true,
		      						paging: false,
		      						autoload: true,
		      						data: datas,
		      						fields: hrhsFields
		      					});
		      				},
		      				error:function(XMLHttpRequest, textStatus, errorThrown){
		      					console.log(XMLHttpRequest,textStatus,errorThrown)
		      				}
		      			});
		      		};
		      		*/
		      		
		      		
		      		
		      		function hxx(){
		    			$('#hrhsTable').jsGrid({
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
		         	        url:"../../gethrhs",
		         	        data:{
		         	        	"cph" : $("#hrhs-cphm").val(),
		         				"xm" : $("#hrhs-siji").val(),
		         				"pageIndex":filter.pageIndex,
		         				"pageSize":filter.pageSize
		         	        },
		         	        dataType: 'json'
		                }).done(function(json) {
		                		var xxfbData = [];
		         				console.log(json)
		                		all = json.data[0].count;
		         				re = json.data[0].datas;

                            for (var i = 0; i < re.length; i++) {

                                var item = re[i];
                                item.gridId = i + 1;
							}



		         				console.log(all)
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
		      		
		      		
		      		
		    		$("#hrhs-Export").off('click').on('click',function(){
		      			layer.confirm('需要导出吗？',{btn:['确认','取消']
		      			},function () {
							layer.msg('导出成功',{icon:1});
                            window.open(basePath + "gethrhsdc?cph="+$("#hrhs-cphm").val()+"&xm="+$("#hrhs-siji").val())
                        },function () {
							layer.msg('已取消导出',{icon:2})
                        })

		      		})
		      		
		      		$("#hrhs-Query").click(function(){
		      			hxx();
		      		});
		      		
		      		
		      		$(function () {
		      			$(".select2").select2({  
	        			  	language: "zh-CN",  //设置 提示语言
	        		        tags:true,  
	        		        createTag:function (decorated, params) {  
	        		            return null;  
	        		        },  
	        		    });
		      			$('#hrhs-datetimeStart').datetimepicker(dateDefaultOption);
		      			$('#hrhs-datetimeEnd').datetimepicker(dateDefaultOption);
		      			$('.addTimePeriod, .period').on('click', function () {
		      				if ($(this).hasClass('addTimePeriod')) $(this).addClass('period').removeClass('addTimePeriod');
		      				else if ($(this).hasClass('period')) $(this).addClass('addTimePeriod').removeClass('period');
		      			});
		      			$('#hrhs-wzxz').select2({
		      				language: 'zh-CN',
		      				width: '160',
		      				minimumResultsForSearch: -1,
		      				data: [
		      					{id: '1', text: '违章性质1'},
		      					{id: '2', text: '违章性质2'}
		      				]
		      			});


		      			jqxhr=$.ajax({
                            type:'post',
							url:basePath+ "getInserthrhsNames",
							data:{"cph":$("#hrhs-cphm").val()},
							dataType:'json',
							timeout:3600000,
							success:function (json) {
                                var data=json.data;
                            	for(var i=0;i<data.length;i++){
                            		data[i].id=data[i].CPH;
                            		data[i].text=data[i].CPH;

								}
								var fh={};
                            	fh.id="filter";
                            	fh.text="全部";
                            	data.unshift(fh);
                                $('#hrhs-cphm').select2({
									language:'zh-CN',
									width: '160',
									minimumInputLength: 3,
									allowClear:true,
									data:data
								});

                            }

						})



                       /* $('#hrhs-cphm').select2({
                            language : 'zh-CN',
                            width : 160,
                            // minimumResultsForSearch : -1,
                            ajax: {
                                url: basePath + "getInserthrhsNames",
                                dataType: 'json',
                                data: function (params) {
                                    return {
                                        cph: params.term
                                    };
                                },
                                processResults: function(res) {
                                    var data = _.map(res.data, function(item){
                                        return {id: item.CPH, text: item.CPH}
                                    });
                                    return {
                                        results: data
                                    };
                                }
                            }
                        });*/

//		      			$('#hrhs-reset').on('click', resetQueryConditions);
		      			$('#hrhs-add').on('click', addhrhs);
		      			$('#hrhs-dialog').on('hidden.bs.modal', function (e) {
		      				$(this).find('input[type=text].form-control, textarea.form-control').val('');
		      				$(this).find('select.form-control').val('').trigger('change');
		      				$(this).find('div.form-control').text('');
		      			});
		      			$('#hrhsTable').jsGrid({
		      				width: 'calc(100% - 2px)',
		      				height: 'calc(100% - 2px)',
		      				editing: true,
		      				sorting: true,
		      				paging: false,
		      				autoload: true,
		      				data: [],
		      				fields: hrhsFields
		      			});
		      			$('.scrollbar-macosx').scrollbar();
		      			hxx();
		      			
		      		});
	});
})(jQuery);
