var jtwf = (function($) {
	var all = 0,re;
	$(function () {
		var jtwFields = [
            {name: 'gridId', title: '序号', width: 60, align: 'center'},
		     			{name: 'CPH', title: '车牌号', width: 120},
		     			{name: 'XM', title: '姓名', width: 120},
		     			{name: 'WZSJ', title: '违章时间', width: 150},
		     			{name: 'WZDD', title: '违章地点', width: 150},
		     			{name: 'WZNR', title: '违章内容', width: 250},
		     			{name: 'CLJG', title: '处理结果', width: 250},
		     			{name: 'KF', title: '扣分', width: 120},
		     			{name: 'ZFJGFK', title: '执法机关罚款', width: 250},
		     			{name: 'GSFK', title: '公司罚款', width: 250},
		     			{name: 'caozuo', title: '操作',
		     				itemTemplate: function (_,item) {
		     					var style = {marginRight: '4px'};
		     					return [
		     						$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
		     							$('#bljl-dialog').modal('show');
		     							$('#bljl-dialog .modal-title').text('详情');
		     							$('#bljl-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
		     							$('#bljl-dialog-save').hide();
		     							
		     							$("#jtwf_cph").text(item.CPH);
		     							$("#jtwf_xm").text(item.XM);
		     							$("#jtwf_wzsj").text(item.WZSJ);
		     							$("#jtwf_wzdd").text(item.WZDD);
		     							$("#jtwf_wznr").text(item.WZNR);
		     							$("#jtwf_cljg").text(item.CLJG);
		     							$("#jtwf_kf").text(item.KF);
		     							$("#jtwf_zfjgfk").text(item.ZFJGFK);
		     							$("#jtwf_gsfk").text(item.GSFK);
		     						
		     						}),
		     						$('<a>').addClass('btn btn-primary btn-xs').text('修改').css(style).on('click', function () {
		     							$('#bljl-dialog').modal('show');
		     							$('#bljl-dialog .modal-title').text('修改');
		     							$('#bljl-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		     							$('#bljl-dialog-save').show();
		     							
		     							$("#jtwf_cph1").val(item.CPH);
		     							$("#jtwf_xm1").val(item.XM);
		     							$("#jtwf_wzsj1").val(item.WZSJ);
		     							$("#jtwf_wzdd1").val(item.WZDD);
		     							$("#jtwf_wznr1").val(item.WZNR);
		     							$("#jtwf_cljg1").val(item.CLJG);
		     							$("#jtwf_kf1").val(item.KF);
		     							$("#jtwf_zfjgfk1").val(item.ZFJGFK);
		     							$("#jtwf_gsfk1").val(item.GSFK);
		     							
		     							
		     							$("#bljl-dialog-save").off('click').on('click',function(){
		     								jqxhr=$.ajax({
		     									url : basePath + "getFindAlljtwf",
		     									type :'post',
		     									dataType :'json',
		     									timeout :180000,
		     									data:{
		     										"bid" : item.BID,
		     										"cph" : $("#jtwf_cph1").val(),
		     										"xm" : $("#jtwf_xm1").val(),
		     										"wzsj" : $("#jtwf_wzsj1").val(),
		     										"wzdd" : $("#jtwf_wzdd1").val(),
		     										"wznr" : $("#jtwf_wznr1").val(),
		     										"cljg" : $("#jtwf_cljg1").val(),
		     										"kf" : $("#jtwf_kf1").val(),
		     										"zfjgfk" : $("#jtwf_zfjgfk1").val(),
		     										"gsfk" : $("#jtwf_gsfk1").val(),
		     										
		     									},
		     									success:function(data){
		     										//console.info(data);
		     										if(data>0){
		     											$('#bljl-dialog').modal('hide');
		     											layer.msg('修改成功',{icon: 1});
		     											hxx();
		     										}else{
		     											alert('修改失败');
		     										}
		     									}
		     								});
		     							});
		     							
		     							
		     							$('#bljl-dialog-pdrq input.form-control').datetimepicker(dateDefaultOption);
		     						}),
		     						$('<a>').addClass('btn btn-danger btn-xs').text('删除').on('click', function () {
		     							
		     							layer.confirm('亲 ！确认删除吗 ？',{btn:['删除','取消']
		     							},function(){
		     								jqxhr=$.ajax({
			     								url : basePath + "getDeletejtwf",
			     								type :'post',
			     								dataType :'json',
			     								timeout :180000,
			     								data :{"bid" :item.BID},
			     								success :function(data){
			     									//console.info(data);
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
		     		var bljlData = [
		     			{cphm: '车牌号1', pdrq: '姓名1', wzsj: '违章时间1', wzdd: '违章地点1', wznr: '违章内容1', cljg: '处理结果1', kouf: '扣分1', zffk: '执法机关罚款1', gsfk: '公司罚款1'},
		     			{cphm: '车牌号2', pdrq: '姓名2', wzsj: '违章时间2', wzdd: '违章地点2', wznr: '违章内容2', cljg: '处理结果2', kouf: '扣分2', zffk: '执法机关罚款2', gsfk: '公司罚款2'},
		     			{cphm: '车牌号3', pdrq: '姓名3', wzsj: '违章时间3', wzdd: '违章地点3', wznr: '违章内容3', cljg: '处理结果3', kouf: '扣分3', zffk: '执法机关罚款3', gsfk: '公司罚款3'},
		     			{cphm: '车牌号4', pdrq: '姓名4', wzsj: '违章时间4', wzdd: '违章地点4', wznr: '违章内容4', cljg: '处理结果4', kouf: '扣分4', zffk: '执法机关罚款4', gsfk: '公司罚款4'},
		     			{cphm: '车牌号5', pdrq: '姓名5', wzsj: '违章时间5', wzdd: '违章地点5', wznr: '违章内容5', cljg: '处理结果5', kouf: '扣分5', zffk: '执法机关罚款5', gsfk: '公司罚款5'},
		     		];
		     		var resetQueryConditions = function (e) {
		     			$('#jtfw-ximi').val('');
		     			$('#jtfw-cphm').select2('val','0');
		     		};
		     		var addbljl = function (e) {
		     			$('#bljl-dialog').modal('show');
		     			$('#bljl-dialog .modal-title').text('添加');
		     			$('#bljl-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		     			$('#bljl-dialog-save').show();
		     			$('#bljl-dialog-pdrq input.form-control').datetimepicker(dateDefaultOption);
		     			$('#bljl-dialog-nife input.form-control').datetimepicker(dateYearDefaultOption);
		     			
		     			$("#bljl-dialog-save").off('click').on('click',function(){
		     				
		     				jqxhr=$.ajax({
		     					url:basePath +"getInsertjtwf",
		     					type:'post',
		     					dataType:'json',
		     					timeout:180000,
		     					data:{
		     						"cph" : $("#jtwf_cph1").val(),
		     						"xm" : $("#jtwf_xm1").val(),
		     						"wzsj" : $("#jtwf_wzsj1").val(),
		     						"wzdd" : $("#jtwf_wzdd1").val(),
		     						"wznr" : $("#jtwf_wznr1").val(),
		     						"cljg" : $("#jtwf_cljg1").val(),
		     						"kf" : $("#jtwf_kf1").val(),
		     						"zfjgfk" : $("#jtwf_zfjgfk1").val(),
		     						"gsfk" : $("#jtwf_gsfk1").val(),
		     					},
		     					success:function(data){
		     						if(data>0){
		     						layer.msg('添加成功');
                                        $('#bljl-dialog').modal('hide');
                                        hxx();

		     						}else{
		     							layer.msg('添加失败',{icon:2})
		     						}
		     					}
		     				});
		     			});
		     			
		     			
		     			
		     			
		     		};
		     	/*	function hxx(){
		     			var index =layer.msg('小妹正在努力加载',{
		     				icon: 16
		     				,shade: 0.01,
		     				time: 6000
		     			});
		     			 var url= basePath;
		     			 var data = {};
		     			 var cph1 = $("#bljl-cphm").val();
		     			 var xm1= $("#bljl-ximi").val();
		     			
		     			if(!cph1 && !xm1){
		     				url += "getSelectJtwf";
		     			}else{
		     				url += "getSelectJtwfName";
		     				data.cph=cph1;
		     				data.xm=xm1;
		     			}
		     			
		     			jqxhr=$.ajax({
		     				url:url,
		     				type:'post',
		     				dataType:'json',
		     				data:data,
		     				success:function(data){
		     					datas=data.data;
		     					for(var i= 0; i<datas.length;i++){
		     						var item =datas[i];
		     						item.gridId=i+1;
		     					}
		     					$('#jtwfTable').jsGrid({
		     						width: 'calc(100% - 2px)',
		     						height: 'calc(100% - 2px)',
		     						editing: true,
		     						sorting: true,
		     						paging: false,
		     						autoload: true,
		     						data: datas,
		     						fields: jtwFields
		     					});
		     				},
		     			error:function(data){
		     			}
		     			
		     			});
		     			
		     			
		     		};*/
		     		
		     		
		     		function hxx(){
		    			$('#jtwfTable').jsGrid({
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
		         	        url:"../../getSelectJtwf",
		         	        data:{
		         	        	"cph" : $("#jtfw-cphm").val(),
		         				"xm" : $("#jtfw-ximi").val(),
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
//		            			alert("数据异常");
		                });
		    		}
		     		
		     		
		     		
		     		$("#jtfw_select").off('click').on('click',function(){
		     			hxx();
		     		});
		     		
		     		
		     		$("#bljl-Export").off('click').on('click',function(){
		     			layer.confirm('确认导出吗',{btn:['确认','取消']
		     			},function () {
                            layer.msg('导出成功',{icon:1})
                            window.open(basePath + "getFindExport?cph="+$("#jtfw-cphm").val()+"&xm="+$("#jtfw-ximi").val())

                        },function () {
                            layer.msg('已取消导出',{icon:2})
                        })
		     		})




		     		
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
                            url:basePath+ "getSelectJtwfName",
                            data:{"cph":$("#jtfw-cphm").val()},
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
                                $('#jtfw-cphm').select2({
                                    language:'zh-CN',
                                    width: '160',
                                    minimumInputLength: 3,
                                    allowClear:true,
                                    data:data
                                });

                            }

                        })

		     		/*	$('#jtfw-cphm').select2({
		     				language: 'zh-CN',
		     				width: '160',
                            ajax: {
                                url: basePath + "getSelectJtwfName",
                                data: function (params) {
                                    var query = {cph: params.term};
                                    return query;
                                },
                                processResults: function (res) {
                                	var data =_.map(res.data,function (item) {
                                		dats={id:item.CPH, text:item.CPH}
                                        return dats
                                    });
                                    return {
                                        results: data
                                    }
                                }
                            }


		     			});*/
		     			$('#bljl-dialog-wzsj input.form-control').datetimepicker(dateDefaultOption);
		     			$('#bljl-reset').on('click', resetQueryConditions);
		     			$('#bljl-add').on('click', addbljl);
		     			$('#bljl-dialog').on('hidden.bs.modal', function (e) {
		     				$(this).find('input[type=text].form-control, textarea.form-control').val('');
		     				$(this).find('select.form-control').val('').trigger('change');
		     				$(this).find('div.form-control').text('');
		     			});
		     			$('#jtwfTable').jsGrid({
		     				width: 'calc(100% - 2px)',
		     				height: 'calc(100% - 2px)',
		     				editing: true,
		     				sorting: true,
		     				paging: false,
		     				autoload: true,
		     				data: [],
		     				fields: jtwFields
		     			});
		     			$('.scrollbar-macosx').scrollbar();
		     			hxx();
		     		})
	
	});
})(jQuery);
