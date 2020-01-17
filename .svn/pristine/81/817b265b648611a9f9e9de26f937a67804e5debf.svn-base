var swsj = (function(_, $) {
	var all = 0,re;
	$(function () {
		var swsjFields = [
            {name: 'gridId', title: '序号', width: 60, align: 'center'},
		      			{name: 'CPH', title: '车牌号', width: 120, align: 'center'},
		      			{name: 'SJXM', title: '司机姓名', width: 120, align: 'center'},
		      			{name: 'LXFS', title: '联系方式', width: 120, align: 'center'},
		      			{name: 'CLYS', title: '车辆颜色', width: 120, align: 'center'},
		      			{name: 'SCSJ', title: '上车时间', width: 120, align: 'center'},
		      			{name: 'XCSJ', title: '下车时间', width: 120, align: 'center'},
		      			{name: 'WPMS', title: '物品描述', width: 120, align: 'center'},
		      			{name: 'BZ', title: '备注', width:120, align: 'center'},
		      	
		      			{name: 'caozuo', title: '操作',
		      				itemTemplate: function (_,item) {
		      					var style = {marginRight: '4px'};
		      					return [
		      						$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
		      							$('#swsj-dialog').modal('show');
		      							$('#swsj-dialog .modal-title').text('详情');
		      							$('#swsj-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
		      							$('#swsj-dialog-save').hide();
		      							
		      							$("#swsj_cph").text(item.CPH);
		      							$("#swsj_sjxm").text(item.SJXM);
		      							$("#swsj_lxfs").text(item.LXFS);
		      							$("#swsj_clys").text(item.CLYS);
		      							$("#swsj_scsj").text(item.SCSJ);
		      							$("#swsj_xcsj").text(item.XCSJ);
		      							$("#swsj_wpms").text(item.WPMS);
		      							$("#swsj_bz").text(item.BZ);
		      							
		      							
		      						}),
		      						$('<a>').addClass('btn btn-primary btn-xs').text('修改').css(style).on('click', function () {
		      							$('#swsj-dialog').modal('show');
		      							$('#swsj-dialog .modal-title').text('修改');
		      							$('#swsj-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      							$('#swsj-dialog-save').show();
		      							
		      							
		      							$("#swsj_cph1").val(item.CPH);
		      							$("#swsj_sjxm1").val(item.SJXM);
		      							$("#swsj_lxfs1").val(item.LXFS);
		      							$("#swsj_clys1").val(item.CLYS);
		      							$("#swsj_scsj1").val(item.SCSJ);
		      							$("#swsj_xcsj1").val(item.XCSJ);
		      							$("#swsj_wpms1").val(item.WPMS);
		      							$("#swsj_bz1").val(item.BZ);
		      							
		      							$("#swsj-dialog-save").off('click').on('click',function(){
		      								jqxhr=$.ajax({
		      									url:basePath + "getUpdateSwsj",
		      									type:'post',
		      									dataType :'json',
		      									data:{
		      									"bid":item.BID,
		      									"cph":$("#swsj_cph1").val(),
		      									"sjxm":$("#swsj_sjxm1").val(),
		      									"lxfs":$("#swsj_lxfs1").val(),
		      									"clys":$("#swsj_clys1").val(),
		      									"scsj":$("#swsj_scsj1").val(),
		      									"xcsj":$("#swsj_xcsj1").val(),
		      									"wpms":$("#swsj_wpms1").val(),
		      									"bz":$("#swsj_bz1").val(),
		      									},
		      									timeout :180000,
		      									success :function(data){
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
		      							
		      							
		      							//$('#swsj-dialog-clys input.form-control').datetimepicker(dateDefaultOption);
		      							//$('#swsj-dialog-fsrq input.form-control').datetimepicker(dateDefaultOption);
		      							//$('#swsj-dialog-barq input.form-control').datetimepicker(dateYearDefaultOption);
		      						}),
		      						$('<a>').addClass('btn btn-danger btn-xs').text('删除').on('click', function () {
		      							
		      							
		      							layer.confirm('确定删除吗？',{btn:['确认','取消']},
		      									function(){
		      								jqxhr=$.ajax({
			      								url : basePath + "getDeleteSwsj",
			      								type :'post',
			      								dataType :'json',
			      								timeout :180000,
			      								data :{"bid" :item.BID},
			      								success :function(data){
			      									if(data>0){
			      										layer.msg('已删除',{icon:1});
			      									hxx();
			      									}else{
			      										layer.msg('删除失败',{icon:2});
			      									}
			      								}
			      							});
		      								
		      									},function(){
		      									layer.msg('删除已取消',{
		      									});
		      									});

		      						})
		      					];
		      				}, width: 135}
		      		];
//		      		var swsjData = [
//		      			{cphm: '车牌号1', sjmc: '姓名1', sjsj: '违章时间1', sjhw: '违章地点1', wpzt: '违章内容1', lqxm: '处理结果1', ghsj: '扣分1', szly: '执法机关罚款1'},
//		      			{cphm: '车牌号2', sjmc: '姓名2', sjsj: '违章时间2', sjhw: '违章地点2', wpzt: '违章内容2', lqxm: '处理结果2', ghsj: '扣分2', szly: '执法机关罚款2'},
//		      			{cphm: '车牌号3', sjmc: '姓名3', sjsj: '违章时间3', sjhw: '违章地点3', wpzt: '违章内容3', lqxm: '处理结果3', ghsj: '扣分3', szly: '执法机关罚款3'},
//		      			{cphm: '车牌号4', sjmc: '姓名4', sjsj: '违章时间4', sjhw: '违章地点4', wpzt: '违章内容4', lqxm: '处理结果4', ghsj: '扣分4', szly: '执法机关罚款4'},
//		      			{cphm: '车牌号5', sjmc: '姓名5', sjsj: '违章时间5', sjhw: '违章地点5', wpzt: '违章内容5', lqxm: '处理结果5', ghsj: '扣分5', szly: '执法机关罚款5'},
//		      		];


//		      		var resetQueryConditions = function (e) {
//		      			//$('.panel-queryBar .form-control').val('');
//                        $('#swsj-ximi').val('');
//                        $('#swsj-cphm').select2('val','0');
//		      		};


		      		var addSwsj = function (e) {
		      			$('#swsj-dialog').modal('show');
		      			$('#swsj-dialog .modal-title').text('添加');
		      			$('#swsj-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      			$('#swsj-dialog-save').show();
		      			
		      			
		      			//$('#swsj-dialog-fsrq input.form-control').datetimepicker(dateDefaultOption);
		      			//$('#swsj-dialog-barq input.form-control').datetimepicker(dateYearDefaultOption);
		      			$('#dialog-clys input.form-control').datetimepicker(dateYearDefaultOption);
		      			
		      			$("#swsj-dialog-save").off('click').on('click',function(){
		      				
		      				jqxhr=$.ajax({
		      					url:basePath+"getInsertSwsj",
		      					type:'post',
		      					dataType:'json',
		      					data:{
		      						"cph":$("#swsj_cph1").val(),
		      						"sjxm":$("#swsj_sjxm1").val(),
		      						"lxfs":$("#swsj_lxfs1").val(),
		      						"clys":$("#swsj_clys1").val(),
		      						"scsj":$("#swsj_scsj1").val(),
		      						"xcsj":$("#swsj_xcsj1").val(),
		      						"wpms":$("#swsj_wpms1").val(),
		      						"bz":$("#swsj_bz1").val(),
		      					},
		      					timeout:180000,
		      					success:function(data){
		      						if(data>0){
		      						layer.msg('添加成功',{icon:1});
                                        $('#swsj-dialog').modal('hide');
                                        hxx();

		      						}else{
                                        layer.msg('添加失败',{icon:1});
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
		      			var cphm=$("#swsj-cphm").val();
		      			var xmm=$("#swsj-ximi").val();
		      			
		      			if(!cphm && !xmm){
		      				url+="getJswsjActi";
		      				
		      			}else{
		      				url+="getJswsjName";
		      				data.cph=cphm;
		      				data.xm=xmm;
		      			}
		      			jqxhr=$.ajax({
		      				url:url,
		      				type:'post',
		      				dataType:'json',
		      				data:data,
		      				timeout:1800000,
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
		      		}
		      		*/
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
		         	        url:"../../getJswsjFindAll",
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
		      		
		      		
		      		
		      		$("#select_swsj").off('click').on('click',function(){
		      			hxx();
		      		});
		      		
		      		$("#swsj-Export").off('click').on('click',function(){
		      			layer.confirm('需要导出吗 ?',{btn:['确认','取消']
		      			},function () {
							layer.msg('导出成功',{icon:1})
                            window.open(basePath + "getswsjExport?cph="+$("#swsj-cphm").val()+"&xm="+$("#swsj-ximi").val())
                        },function () {
						layer.msg('已取消导出',{icon:2})
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
		      			$('#swsj_scsj1').datetimepicker(dateDefaultOption);
		      			$('#swsj_xcsj1').datetimepicker(dateDefaultOption);
		      			$('.addTimePeriod, .period').on('click', function () {
		      				if ($(this).hasClass('addTimePeriod')) $(this).addClass('period').removeClass('addTimePeriod');
		      				else if ($(this).hasClass('period')) $(this).addClass('addTimePeriod').removeClass('period');
		      			});




                        jqxhr=$.ajax({
                            type:'post',
                            url:basePath+ "getJswsjName",
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





                        /* $('#swsj-cphm').select2({
                             language : 'zh-CN',
                             width : 160,
                             // minimumResultsForSearch : -1,
                             ajax: {
                                 url: basePath + "getJswsjName",
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
		      			$('#swsj-wzxz').select2({
		      				language: 'zh-CN',
		      				width: '160',
		      				minimumResultsForSearch: -1,
		      				data: [
		      					{id: '1', text: '违章性质1'},
		      					{id: '2', text: '违章性质2'}
		      				]
		      			});
		      			$('#swsj-dialog-sglx select.form-control').select2({
		      				language: 'zh-CN',
		      				width: '168',
		      				minimumResultsForSearch: -1,
		      				data: [
		      					{id: '1', text: '事故类别1'},
		      					{id: '2', text: '事故类别2'}
		      				]
		      			});
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
		      				data: [],
		      				fields: swsjFields
		      			});
		      			$('.scrollbar-macosx').scrollbar();
		      			hxx();
		      		})
	
	});
})(_, jQuery);
