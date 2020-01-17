var clyysjtj = (function($) {
	var all = 0,re;
	$(function () {
	
		var clyysjtjFields = [
                            {name: 'gridId', title: '序号', width: 60, align: 'center'},
                            {name: 'TIME', title: '日期', width: 120, align:'center'},
		          			{name: 'CCPHM', title: '车辆数/(辆)', width: 120, align:'center'},
		          			{name: 'TTJCS', title: '营运次数/(次)', width: 120, align:'center'},
		          			{name: 'RYSJE', title: '营运金额/(元)', width: 120, align:'center'},
		          			{name: 'RZLC', title: '营运里程/(公里)', width: 120, align:'center'},
		          			{name: 'RKSLC', title: '空驶里程/(公里)', width: 120, align:'center'},
		          			{name: 'RZKLC', title: '载客时间/(分钟)', width: 150, align:'center'},
		          			{name: 'DHSJ', title: '载客等候时间/(分钟)', width: 150, align:'center'}
		          		];
		          		var clyysjtjData = [
		          			{clsh: '车辆数1', yycs: '营运次数1', yyje: '营运金额1', yylc: '营运里程1', kslc: '空驶里程1', zksj: '载客时间1', zkdhsj: '载客等候时间1'},
		          			{clsh: '车辆数2', yycs: '营运次数2', yyje: '营运金额2', yylc: '营运里程2', kslc: '空驶里程2', zksj: '载客时间2', zkdhsj: '载客等候时间2'},
		          			{clsh: '车辆数3', yycs: '营运次数3', yyje: '营运金额3', yylc: '营运里程3', kslc: '空驶里程3', zksj: '载客时间3', zkdhsj: '载客等候时间3'},
		          			{clsh: '车辆数4', yycs: '营运次数4', yyje: '营运金额4', yylc: '营运里程4', kslc: '空驶里程4', zksj: '载客时间4', zkdhsj: '载客等候时间4'},
		          			{clsh: '车辆数5', yycs: '营运次数5', yyje: '营运金额5', yylc: '营运里程5', kslc: '空驶里程5', zksj: '载客时间5', zkdhsj: '载客等候时间5'}
		          		];

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
        	var str1=$('#clyysjtj-datetimeStart').val();
  			var str2=$('#clyysjtj-datetimeEnd').val();
  			if(issh(str1,str2)=='1'){
  				layer.msg('不支持跨越查询',{icon:2});
  				return false;
  			}
            $('#cxycfxTable').jsGrid({
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
                url:basePath + "getYclyusjtjFind",
                data:{
                    "cph" : $("#clyysjtj-car").val(),
                    "gs" : $("#clyysjtj-yhmc").val(),
                    "kssj" : $("#clyysjtj-datetimeStart").val(),
                    "jssj" : $("#clyysjtj-datetimeEnd").val(),
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

                all = json.data[0].count;
                re = json.data[0].list;

                for (var i = 0; i < re.length; i++) {
                    var iterm = re[i];
                    iterm.gridId = i+1;
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
//                alert("数据异常");
            });
        }


		$("#select_clyysjtj").off('click').on('click',function(){

            hxx();
		})


        $("#clyysj_Export").off('click').on('click',function () {
        	var str1=$('#clyysjtj-datetimeStart').val();
  			var str2=$('#clyysjtj-datetimeEnd').val();
  			if(issh(str1,str2)=='1'){
  				layer.msg('不支持跨越查询',{icon:2});
  				return false;
  			}
            window.open(basePath + "getYclyusjtjExport?cph="+ $("#clyysjtj-car").val()+"&gs="+$("#clyysjtj-yhmc").val()+"&kssj="+$("#clyysjtj-datetimeStart").val()+"&jssj="+$("#clyysjtj-datetimeEnd").val())
        })




		          		$(function () {
		          			$(".select2").select2({  
		        			  	language: "zh-CN",  //设置 提示语言
		        		        tags:true,  
		        		        createTag:function (decorated, params) {  
		        		            return null;  
		        		        },  
		        		    });
		          		   
		          			$('#clyysjtj-datetimeStart').datetimepicker(dateDefaultOption);
		          			$('#clyysjtj-datetimeEnd').datetimepicker(dateDefaultOption);
		          			$('#clyysjtj-datetimeStart').val(new Date().Format('yyyy-MM-dd'));
		          			$('#clyysjtj-datetimeEnd').val(new Date().Format('yyyy-MM-dd'));
		          			$('.addTimePeriod, .period').on('click', function () {
		          				if ($(this).hasClass('addTimePeriod')) $(this).addClass('period').removeClass('addTimePeriod');
		          				else if ($(this).hasClass('period')) $(this).addClass('addTimePeriod').removeClass('period');
		          			});


//                            jqxhr=$.ajax({
//                                type:'post',
//                                url:basePath+ "getYclyusjtjFindName",
//                                data:{"cph":$("#clyysjtj-car").val()},
//                                dataType:'json',
//                                timeout:3600000,
//                                success:function (json) {
//                                    var data=json.data;
//                                    for(var i=0;i<data.length;i++){
//                                        data[i].id="浙"+data[i].CP;
//                                        data[i].text="浙"+data[i].CP;
//                                    }
//                                    var fh={};
//                                    fh.id="filter";
//                                    fh.text="全部";
//                                    data.unshift(fh);
//                                    $('#clyysjtj-car').select2({
//                                        language: 'zh-CN',
//                                        width: 160,
//                                        minimumInputLength: 3,
//                                        allowClear: true,
//                                        data: data
//                                    });
//                                }
//                            })
//
//
//                            //2
//                            jqxhr=$.ajax({
//                                type:'post',
//                                url:basePath+ "getYclyusjtjFindGS",
//                                data:{"gs": $("#clyysjtj-yhmc").val()},
//                                dataType:'json',
//                                timeout:3600000,
//                                success:function (json) {
//                                    var data=json.data;
//                                    for(var i=0;i<data.length;i++){
//                                        data[i].id=data[i].ZGS;
//                                        data[i].text=data[i].ZGS;
//
//                                    }
//                                    var fh={};
//                                    fh.id="filter";
//                                    fh.text="全部";
//                                    data.unshift(fh);
//
//                                    $('#clyysjtj-yhmc').select2({
//                                        language: 'zh-CN',
//                                        width: 160,
//                                        allowClear: true,
//                                        data: data
//                                    });
//                                }
//                            })
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
		        					$('#clyysjtj-car').select2({
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
									$('#clyysjtj-yhmc').select2({
										data: data,
										allowClear: true
										});
								}
							});


                           /* $('#clyysjtj-car').select2({
                                language: 'zh-CN',
                                width: 160,
                                allowClear: true,
                                data: [],

                            });*/


		          			/*$('#clyysjtj-car').select2({
                                language: 'zh-CN',
                                width: '160',
                                ajax: {
                                    url: basePath + "getYclyusjtjFindName",
                                    data: function (params) {
                                        console.info('ajax:')
                                        var query = {cph: params.term};
                                        return query;
                                    },
                                    processResults: function (res) {
                                        var data = _.map(res.data, function (item) {
                                            return {id: item.CPHM, text: item.CPHM}
                                        });
                                        return {
                                            results: data
                                        }
                                    }
                                }


                            });*/



/*                            $('#clyysjtj-yhmc').select2({
                                language: 'zh-CN',
                                width: '160',
                                ajax: {
                                    url: basePath + "getYclyusjtjFindName",
                                    data: function (params) {
                                        var query = {gs: params.term};
                                        return query;
                                    },
                                    processResults: function (res) {
                                        var data =_.map(res.data,function (item) {
                                            dats={id:item.ZGS, text:item.ZGS}
                                            return dats
                                        });
                                        return {
                                            results: data
                                        }
                                    }
                                }


                            });
*/

		          			$('#cxycfxTable').jsGrid({
		          				width: 'calc(100% - 2px)',
		          				height: 'calc(100% - 2px)',
		          				editing: true,
		          				sorting: true,
		          				paging: false,
		          				autoload: true,
		          				data: [],
		          				fields: clyysjtjFields
		          			});
                            hxx();
		          		});
	});
})(jQuery);
