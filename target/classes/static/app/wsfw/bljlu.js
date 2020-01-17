var bljlu = (function($) {
	var all = 0,re;
	$(function () {
		var bljlFields = [
            {name: 'gridId', title: '序号', width: 60, align: 'center'},
		      			//{name: 'BID', title: '编号', width: 120},
		      			{name: 'CPH', title: '车牌号', width: 120},
		      			{name: 'XM', title: '姓名', width: 120},
		      			{name: 'WFNR', title: '违反内容', width: 150},
		      			{name: 'CLJG', title: '结果', width: 250},
		      			{name: 'caozuo', title: '操作',
		      				itemTemplate: function (_,item) {
		      					var style = {marginRight: '4px'};
		      					return [
		      						$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
		      							$('#bljl-dialog').modal('show');
		      							$('#bljl-dialog .modal-title').text('详情');
		      							$('#bljl-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
		      							$('#bljl-dialog-save').hide();
		      							$("#bljl_CPH").text(item.CPH);
		      							$("#bljl_XM").text(item.XM);
		      							$("#bljl_WFNR").text(item.WFNR);
		      							$("#bljl_CLJG").text(item.CLJG);
		      						}),
		      						$('<a>').addClass('btn btn-primary btn-xs').text('修改').css(style).on('click', function () {
		      							$('#bljl-dialog').modal('show');
		      							$('#bljl-dialog .modal-title').text('修改');
		      							$('#bljl-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		       							$('#bljl-dialog-save').show();

		      							$('#bljl-dialog-pdrq input.form-control').datetimepicker(dateDefaultOption);
		      							/* 修改显示的*/
		      							$("#bljl_CPH1").val(item.CPH);
		      							$("#bljl_XM1").val(item.XM);
		      							$("#bljl_WFNR1").val(item.WFNR);
		      							$("#bljl_CLJG1").val(item.CLJG);
		      							
		      							$("#bljl-dialog-save").off('click').on('click' ,function(){
		      								jqxhr=$.ajax({
		      									 url :basePath + "getUpdate",
		      										type : 'post',
		      										dataType: 'json',
		      										data:{"CPH":$("#bljl_CPH1").val(),"XM":$("#bljl_XM1").val(),"BID":item.BID,
		      											"WFNR":$("#bljl_WFNR1").val(),"CLJG":$("#bljl_CLJG1").val()},
		      										timeout : 180000,
		      										success:function(data){
		      											if(data > 0){
		      												$('#bljl-dialog').modal('hide');
		      												layer.msg('修改成功',{icon: 1});
		      												hxx();
		      											}
		      										}
		      									 
		      								 });
		      							})
		      							
		      						}),
		      						$('<a>').addClass('btn btn-danger btn-xs').text('删除').on('click', function () {
                                        layer.confirm('确认删除吗 ？',{btn: ['确认','取消']
                                        },function(){
                                            jqxhr=$.ajax({
                                                url :basePath + "getdelete",
                                                type : 'post',
                                                dataType: 'json',
                                                data:{"bid":item.BID},
                                                timeout : 180000,
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
                                        })

		      						})
		      					];
		      				}, width: 135}
		      		];
		      		/* var bljlData = [
		      			{cphm: '车牌号1', pdrq: '姓名1', nife: '违反内容1', rymc: '结果1'},
		      			{cphm: '车牌号2', pdrq: '姓名2', nife: '违反内容2', rymc: '结果2'},
		      			{cphm: '车牌号3', pdrq: '姓名3', nife: '违反内容3', rymc: '结果3'},
		      			{cphm: '车牌号4', pdrq: '姓名4', nife: '违反内容4', rymc: '结果4'},
		      			{cphm: '车牌号5', pdrq: '姓名5', nife: '违反内容5', rymc: '结果5'},
		      		]; */
		      		var resetQueryConditions = function (e) {
		      			$('#bljl-siji').val('');
		      			$("#bljl-cphm").select2('val','0')

		      		};
		      		var addbljl = function (e) {
		      			$('#bljl-dialog').modal('show');
		      			$('#bljl-dialog .modal-title').text('添加');
		      			$('#bljl-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
		      			$('#bljl-dialog-save').show();
		      			$('#bljl-dialog-pdrq input.form-control').datetimepicker(dateDefaultOption);
		      			$('#bljl-dialog-nife input.form-control').datetimepicker(dateYearDefaultOption);
		      			
		      			//添加
		      			$("#bljl-dialog-save").off('click').on('click',function(){
		      				var CPH=$("#bljl_CPH1").val();
		      				var XM=$("#bljl_XM1").val();
		      				var WFNR=$("#bljl_WFNR1").val();
		      				var CLJG=$("#bljl_CLJG1").val();
		      			//	var BID='25';
		      				
		      				jqxhr=$.ajax({
		      					url :basePath + "getinsert",
		      					type : 'post',
		      					dataType: 'json',
		      					data:{"CPH":CPH,"XM":XM,"WFNR":WFNR,"CLJG":CLJG},
		      					timeout : 180000,
		      					success:function(data){
		      						if(data>0){
		      							layer.msg('添加成功',{icon:1});
                                        $('#bljl-dialog').modal('hide');
                                        hxx();
		      						}else{
                                        layer.msg('添加失败',{icon:1});
									}
		      					}
		      				});
		      			});
		      		};
		      		
		      		/*function hxx(){
		      			 var url = basePath;
		      			    var data = {};
		      			    var cphm = $('#bljl-cphm').val();
		      			    alert(cphm);
		      			    
		      			    var siji = $('#bljl-siji').val();
		      			    if (!cphm && !siji) {
		      			        url += 'getblj';
		      	            } else {
		      			        url += 'getcpAndxm';
		      			        data.xm=siji;
		      			        data.cph=cphm;
		      	            }
		      		    jqxhr=$.ajax({
		      				url : url,
		      				type : 'post',
		      				data : data,
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
		      					$('#bljlTable').jsGrid({
		      						width: 'calc(100% - 2px)',
		      						height: 'calc(100% - 2px)',
		      						editing: true,
		      						sorting: true,
		      						paging: false,
		      						autoload: true,
		      						data: datas,
		      						fields: bljlFields
		      					});
		      				},
		      				error:function(data){
		      				}
		      			});
		      		};*/
		      		
		      		
		      		
		      		
		      		function hxx(){
		    			$('#bljlTable').jsGrid({
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
		         	        url:"../../getblj",
		         	        data:{
		         				"cph" : $("#bljl-cphm").val(),
		         				"xm" : $("#bljl-siji").val(),
		         				"pageIndex":filter.pageIndex,
		         				"pageSize":filter.pageSize
		         	        },
		         	        dataType: 'json'
		                }).done(function(json) {
		                	console.log(json)
		                		var ycyyfxData = [];
		                		all = json.data[0].count;
		         				re = json.data[0].list;
                            for (var i = 0; i < re.length; i++) {
                                var iterm = re[i];
                                iterm.gridId=i+1;
                            }

		             			if(json.code == 0){
		             				/*for(var i = 0; i< re.length ;i++){
		             					var rs={};
		             					rs.CPH =  re[i].CPH;
		               				
		             					ycyyfxData.push(rs);
		             				}*/
		             				console.info(re);
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
		      		
		      		$("#bljl_select").click(function(){
		      			hxx();
		      	
		      		});
		      		
		      		
		      		$("#bljl-Export").off('click').on('click',function(){
		      			layer.confirm('需要导出吗？',{btn:['确认','取消']
		      			},function () {
							layer.msg('导出成功',{icon:1});
                            window.open(basePath + "getFindAllExport?cph="+$("#bljl-cphm").val()+"&xm="+$("#bljl-siji").val())
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
                            url:basePath+ "getcpAndxmName",
                            data:{"cph":$("#bljl-cphm").val()},
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
                                fh.id="filter";
                                fh.text="全部";
                                data.unshift(fh);
                                $('#bljl-cphm').select2({
                                    language:'zh-CN',
                                    width: '160',
                                    minimumInputLength: 3,
                                    allowClear:true,
                                    data:data
                                });

                            }

                        })



		      			/*$('#bljl-cphm').select2({
                            language: 'zh-CN',
                            width : 160,
                            ajax: {
                                url: basePath + "getcpAndxmName",
                                dataType: 'json',
                                data: function (params) {
                                    var query = {cph: params.term};
                                    return query;
                                },
                                processResults: function (res) {
                                    var data = _.map(res.data, function (item) {
                                        return {id: item.CPH, text: item.CPH}
                                    });
                                    return {
                                        results: data
                                    };
                                }
                            }


		      			});*/
		      			$('#bljl-reset').on('click', resetQueryConditions);
		      			$('#bljl-add').on('click', addbljl);
		      			$('#bljl-dialog').on('hidden.bs.modal', function (e) {
		      				$(this).find('input[type=text].form-control, textarea.form-control').val('');
		      				$(this).find('select.form-control').val('').trigger('change');
		      				$(this).find('div.form-control').text('');
		      			});
		      			$('#bljlTable').jsGrid({
		      				width: 'calc(100% - 2px)',
		      				height: 'calc(100% - 2px)',
		      				editing: true,
		      				sorting: true,
		      				paging: false,
		      				autoload: true,
		      				data: [],
		      				fields: bljlFields
		      			});
		      			$('.scrollbar-macosx').scrollbar();
		      			hxx();
		      		});
	});
})(jQuery);
