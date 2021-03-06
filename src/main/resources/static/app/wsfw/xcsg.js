var xcsg = (function($) {
	var all = 0,re;
	$(function () {
		var xcgsFields = [
            {name: 'gridId', title: '序号', width: 60, align: 'center'},
		      			{name: 'CPH', title: '车牌号', width: 120},
		      			{name: 'XM', title: '姓名', width: 120},
		      			{name: 'FSRQ', title: '发生日期', width: 150},
		      			{name: 'BARQ', title: '报案日期', width: 150},
		      			{name: 'SGDD', title: '事故地点', width: 250},
		      			{name: 'SGLB', title: '事故类别', width: 250},
		      			{name: 'BCCS', title: '本车车损', width: 120},
		      			{name: 'DFSS', title: '对方损失', width: 250},
		      			{name: 'SGZE', title: '事故总额', width: 250},
		      			{name: 'SGZR', title: '事故责任', width: 250},
		      			{name: 'caozuo', title: '操作',
		      				itemTemplate: function (_,item) {
		      					var style = {marginRight: '4px'};
		      					return [
		      						$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
		      							$('#xcgs-dialog').modal('show');
		      							$('#xcgs-dialog .modal-title').text('详情');
		      							$('#xcgs-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
		      							$('#xcgs-dialog-save').hide();
		      							
		      							$("#xcsg_cph").text(item.CPH);
		      							$("#xcsg_xm").text(item.XM);
		      							$("#xcsg_fsrq").text(item.FSRQ);
		      							$("#xcsg_barq").text(item.BARQ);
		      							$("#xcsg_bccs").text(item.BCCS);
		      							$("#xcsg_dfss").text(item.DFSS);
		      							$("#xcsg_sgze").text(item.SGZE);
		      							$("#xcsg_sglb").text(item.SGLB);
		      							
		      							$("#xcsg_sglb1").select2('val',item.SGLB)
		      							
		      							$("#xcsg_sgzr").text(item.SGZR);
		      							$("#xcsg_sgdd").text(item.SGDD);
		      							
		      						}),
		      						$('<a>').addClass('btn btn-primary btn-xs').text('修改').css(style).on('click', function () {
		      							$('#xcgs-dialog').modal('show');
		      							$('#xcgs-dialog .modal-title').text('修改');
		      							$('#xcgs-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      							$('#xcgs-dialog-save').show();
		      							
		      							$("#xcsg_cph1").val(item.CPH);
		      							$("#xcsg_xm1").val(item.XM);
		      							$("#xcsg_fsrq1").val(item.FSRQ);
		      							$("#xcsg_barq1").val(item.BARQ);
		      							$("#xcsg_bccs1").val(item.BCCS);
		      							$("#xcsg_dfss1").val(item.DFSS);
		      							$("#xcsg_sgze1").val(item.SGZE);
		      							$("#xcsg_sglb1").val(item.SGLB);
		      							$("#xcsg_sgzr1").val(item.SGZR);
		      							$("#xcsg_sgdd1").val(item.SGDD);
		      							
		      							

		      							$("#xcgs-dialog-save").off('click').on('click',function(){
		      								jqxhr=$.ajax({
		      									url:basePath + "getUpdatexcsg",
		      									type : 'post',
		      									dataType : 'json',
		      									data:{
		      										"bid":item.BID,
		      										"cph":$("#xcsg_cph1").val(),
		      										"xm":$("#xcsg_xm1").val(),
		      										"fsrq":$("#xcsg_fsrq1").val(),
		      										"barq":$("#xcsg_barq1").val(),
		      										"bccs":$("#xcsg_bccs1").val(),
		      										"dfss":$("#xcsg_dfss1").val(),
		      										"sgze":$("#xcsg_sgze1").val(),
		      										"sglb":$("#xcsg_sglb1").val(),
		      										"sgzr":$("#xcsg_sgzr1").val(),
		      										"sgdd":$("#xcsg_sgdd1").val(),
		      										
		      									},
		      									timeout:180000,
		      									success:function(data){
		      										$('#xcgs-dialog').modal('hide');
		      										layer.msg('修改成功',{icon: 1});
		      										hxx();
		      										
		      									}
		      								});
		      							});
		      							
		      							
		      							$('#xcgs-dialog-fsrq input.form-control').datetimepicker(dateDefaultOption);
		      							$('#xcgs-dialog-barq input.form-control').datetimepicker(dateDefaultOption);
		      						}),
		      						$('<a>').addClass('btn btn-danger btn-xs').text('删除').on('click', function () {
		      							layer.confirm('亲  确认删除吗 ？',{bit:['确认','取消']
		      							},function(){
		      								jqxhr=$.ajax({
			      								url:basePath + "getDeletexcsg",
			      								type:'post',
			      								dataType:'json',
			      								data:{"bid":item.BID},
			      								timeout:180000,
			      								success:function(data){
			      									if(data>0){
			      										layer.msg('删除成功',{icon: 1});
			      										hxx();
			      									}else{
			      										layer.msg('删除失败',{icon: 2});
			      									}
			      								}
			      							});
		      								
		      							
		      							},function(){
		      								layer.msg('已取消删除',{icon: 2});
		      							}
		      							)
		      							


		      						})
		      					];
		      				}, width: 135}
		      		];
		      		var xcgsData = [
		      			{cphm: '车牌号1', pdrq: '姓名1', wzsj: '违章时间1', wzdd: '违章地点1', wznr: '违章内容1', cljg: '处理结果1', kouf: '扣分1', zffk: '执法机关罚款1', gsfk: '公司罚款1'},
		      			{cphm: '车牌号2', pdrq: '姓名2', wzsj: '违章时间2', wzdd: '违章地点2', wznr: '违章内容2', cljg: '处理结果2', kouf: '扣分2', zffk: '执法机关罚款2', gsfk: '公司罚款2'},
		      			{cphm: '车牌号3', pdrq: '姓名3', wzsj: '违章时间3', wzdd: '违章地点3', wznr: '违章内容3', cljg: '处理结果3', kouf: '扣分3', zffk: '执法机关罚款3', gsfk: '公司罚款3'},
		      			{cphm: '车牌号4', pdrq: '姓名4', wzsj: '违章时间4', wzdd: '违章地点4', wznr: '违章内容4', cljg: '处理结果4', kouf: '扣分4', zffk: '执法机关罚款4', gsfk: '公司罚款4'},
		      			{cphm: '车牌号5', pdrq: '姓名5', wzsj: '违章时间5', wzdd: '违章地点5', wznr: '违章内容5', cljg: '处理结果5', kouf: '扣分5', zffk: '执法机关罚款5', gsfk: '公司罚款5'},
		      		];
		      		var resetQueryConditions = function (e) {
		      			$(' #xcgs-ximi').val('');
		      			$("#xcgs-cphm").select2('val','0');
		      		};
		      		var addxcgs = function (e) {
		      			$('#xcgs-dialog').modal('show');
		      			$('#xcgs-dialog .modal-title').text('添加');
		      			$('#xcgs-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      			$('#xcgs-dialog-save').show();
		      			$('#xcgs-dialog-fsrq input.form-control').datetimepicker(dateDefaultOption);
		      			$('#xcgs-dialog-barq input.form-control').datetimepicker(dateDefaultOption);
		      			
		      			//添加
		      			$("#xcgs-dialog-save").off('click').on('click',function(){
		      				console.info(1);
		      				jqxhr=$.ajax({
		      					url:basePath + "getInsertxcsg",
		      					type:'post',
		      					dataType:'json',
		      					data:{
		      						"cph":$("#xcsg_cph1").val(),
		      						"xm":$("#xcsg_xm1").val(),
		      						"fsrq":$("#xcsg_fsrq1").val(),
		      						"barq":$("#xcsg_barq1").val(),
		      						"bccs":$("#xcsg_bccs1").val(),
		      						"dfss":$("#xcsg_dfss1").val(),
		      						"sgze":$("#xcsg_sgze1").val(),
		      						"sglb":$("#xcsg_sglb1").val(),
		      						"sgzr":$("#xcsg_sgzr1").val(),
		      						"sgdd":$("#xcsg_sgdd1").val(),
		      						
		      					},
		      					timeout:180000,
		      					success:function(data){
		      						if(data>0){
		      							console.info(1);
		      							$('#xcgs-dialog').modal('hide');
		      							hxx();
		      						}else{
		      							alert('添加失败')
		      						}
		      					}
		      				});
		      			});
		      		};
		      		
		      		/*function hxx(){
		      			var index =layer.msg('小妹正在努力加载',{
		      				icon: 16
		      				,shade: 0.01,
		      				time: 6000
		      			});
		      			var url=basePath;
		      			var data={};
		      			var cphs=$("#xcgs-cphm").val();   
		      			var xms=$("#xcgs-ximi").val();
		      			
		      			if(!cphs && !xms){
		      				url+="getSelectFindAll";
		      				
		      			}else{
		      				url+="getSelectNamexcsg";
		      				data.cph=cphs;
		      				data.xm=xms;
		      			}
		      			jqxhr=$.ajax({
		      			url:url,
		      			type :'post',
		      			dataType :'json',
		      			data:data,
		      			timeout :180000,
		      			success:function(data){
		      				datas=data.data;
		      				for(var i=0;i<datas.length;i++){
		      					var item=datas[i];
		      					item.gridId=i+1;
		      				}
		      				$('#xcgsTable').jsGrid({
		      					width: 'calc(100% - 2px)',
		      					height: 'calc(100% - 2px)',
		      					editing: true,
		      					sorting: true,
		      					paging: false,
		      					autoload: true,
		      					data: datas,
		      					fields: xcgsFields
		      				});
		      			}
		      			});
		      			
		      		}*/
		      		
		      		
		      		function hxx(){
		    			$('#xcgsTable').jsGrid({
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
		         	        url:"../../getSelectFindAll",
		         	        data:{
		         	        	"cph" : $("#xcgs-cphm").val(),
		         				"xm" : $("#xcgs-ximi").val(),
		         				"pageIndex":filter.pageIndex,
		         				"pageSize":filter.pageSize
		         	        },
		         	        dataType: 'json'
		                }).done(function(json) {
		                		var xxfbData = [];
		         				//console.log(json)
		                		all = json.data[0].count;
		         				re = json.data[0].datas;

                            for (var i = 0; i < re.length; i++) {
                                var iterm = re[i];
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
//		            			alert("数据异常");
		                });
		    		}
		      		
		      		//搜索
		      		$("#select_xcsg").off('click').on('click',function(){
		      			hxx();
		      		});
		      		
		      		$("#xcgs-Export").off('click').on('click',function(){


                        layer.confirm('确认导出吗',{btn:['确认','取消']
                        },function () {
                            layer.msg('导出成功',{icon:1});
                            window.open(basePath + "getxcsgExport?cph="+$("#xcgs-cphm").val()+"&xm="+$("#xcgs-ximi").val())
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
		      			 $('#xcgs-dialog-sglx select.form-control').select2({
		       				language: 'zh-CN',
		       				width: '168',
		       				minimumResultsForSearch: -1,
		       				data: [
		       					{id: '事故类别1', text: '事故类别1'},
		       					{id: '事故类别2', text: '事故类别2'},
		       					{id: '事故类别3', text: '事故类别3'}
		       				]
		       			});
		      			$('.addTimePeriod, .period').on('click', function () {
		      				if ($(this).hasClass('addTimePeriod')) $(this).addClass('period').removeClass('addTimePeriod');
		      				else if ($(this).hasClass('period')) $(this).addClass('addTimePeriod').removeClass('period');
		      			});


                        jqxhr=$.ajax({
                            type:'post',
                            url:basePath+ "getSelectxcsgName",
                            data:{"cph":$("#xcgs-cphm").val()},
                            dataType:'json',
                            timeout:3600000,
                            success:function (json) {
                                var data=json.data;
                                for(var i=0;i<data.length;i++){
                                    data[i].id=data[i].CPH;
                                    data[i].text=data[i].CPH;
                                    console.info("silly",data[i]);
                                }
                                var fh={};
                                fh.id="null";
                                fh.text="全部";
                                data.unshift(fh);
                                $('#xcgs-cphm').select2({
                                    language:'zh-CN',
                                    width: '160',
                                    minimumInputLength: 3,
                                    allowClear:true,
                                    data:data
                                });

                            }

                        })
		      		/*	$('#xcgs-cphm').select2({
		      				language: 'zh-CN',
		      				width: '160',
							ajax:{
		      					url:basePath + "getSelectxcsgName",
								data :function (params) {
                                    var query={ cph:params.term };
                                       return query;
                                },

                                processResults : function (res) {
		      						var data =_.map(res.data,function (item) {
                                        dats={id:item.CPH, text:item.CPH}
										return dats
                                    });
								return{
                                    results:data
							     	}

                                }

							}


		      			});
		      			*/
		      			$('#xcgs-reset').on('click', resetQueryConditions);
		      			$('#xcgs-add').on('click', addxcgs);
		      			$('#xcgs-dialog').on('hidden.bs.modal', function (e) {
		      				$(this).find('input[type=text].form-control, textarea.form-control').val('');
		      				$(this).find('select.form-control').val('').trigger('change');
		      				$(this).find('div.form-control').text('');
		      			});
		      			$('#xcgsTable').jsGrid({
		      				width: 'calc(100% - 2px)',
		      				height: 'calc(100% - 2px)',
		      				editing: true,
		      				sorting: true,
		      				paging: false,
		      				autoload: true,
		      				data: [],
		      				fields: xcgsFields
		      			});
		      			$('.scrollbar-macosx').scrollbar();
		      			hxx();
		      			
		      			jqxhr=$.ajax({
		      				url :basePath + "getSelectFindAllSglb",
		      				type : 'post',
		      				dataType: 'json',
		      				timeout : 180000,
		      				success:function(data){
		      					var data=data.data;
		      					var datas=new Array();
		      					for(var i=0;i<data.length;i++){
		      						datas.push({id:data[i].BID,text:data[i].SGLB})
		      					}
		      					
		      					$('#xcgs-dialog-sglx select.form-control').select2({
		      						language: 'zh-CN',
		      						width: '168',
		      						minimumResultsForSearch: -1,
		      						data:datas 
		      							/* [
		      							{id: '1', text: '违章性质1'},
		      							{id: '2', text: '违章性质2'}
		      							] */
		      					});
		      					
		      				}
		      				
		      			});
		      			
		      		})
	
	});
})(jQuery);
