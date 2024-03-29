var sjzp = (function($) {
	var all = 0, re;
	$(function() {
		var sjzpFields = [
				{
					name : 'gridId',
					title : '序号',
					width : 60,
					align : 'center'
				},
				// {name: 'BID', title: '编号', width: 120},
				{
					name : 'CPH',
					title : '车牌号',
					width : 120,
					align : 'center'
				},
				{
					name : 'CLXYNX',
					title : '车辆营运年限',
					width : 120,
					align : 'center'
				},
				{
					name : 'SJNL',
					title : '司机年龄',
					width : 120,
					align : 'center'
				},
				{
					name : 'XB',
					title : '性别',
					width : 120,
					align : 'center'
				},
				{
					name : 'JL',
					title : '驾龄',
					width : 120,
					align : 'center'
				},
				{
					name : 'BC',
					title : '班次',
					width : 120,
					align : 'center'
				},
				{
					name : 'JBDD',
					title : '交班地点',
					width : 250,
					align : 'center'
				},
				{
					name : 'LXR',
					title : '联系人',
					width : 120,
					align : 'center'
				},
				{
					name : 'DJRQ',
					title : '登记日期',
					width : 150,
					align : 'center'
				},
				{
					name : 'caozuo',
					title : '操作',
					itemTemplate : function(_, item) {
						var style = {
							marginRight : '4px'
						};
						return [
								$('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click',function() {
									$('#sjzp-dialog').modal('show');
									$('#sjzp-dialog .modal-title').text('详情');
									$('#sjzp-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
									$('#sjzp-dialog-save').hide();
									for (var i = 0; i < re.length; i++) {
										if (re[i].CPH == item.CPH) {
											var s = re[i];
											$("#sjzp_cx").text(s.CPH);
											$("#sjzp_clyynx").text(s.CLXYNX);
											$("#sjzp_sjnl").text(s.SJNL);
											$("#sjzp_xb").text(s.XB);
											$("#sjzp_hj").text(s.HJ);
											$("#sjzp_cynx").text(s.CYNX);
											$("#sjzp_jl").text(s.JL);
											$("#sjzp_bc").text(s.BC);
											$("#sjzp_xj").text(s.XJ);
											$("#sjzp_jbdd").text(s.JBDD);
											$("#sjzp_sjyye").text(s.SJXYE);
											$("#sjzp_lxdh").text(s.LXDH);
											$("#sjzp_yx").text(s.YX);
											$("#sjzp_lxr").text(s.LXR);
											$("#sjzp_djrq").text(s.DJRQ);
											$("#sjzp_bz").text(s.BZ);
										}
									}

								}),
								$('<a>').addClass('btn btn-primary btn-xs').text('修改').css(style).on('click',function() {
													$('#sjzp-dialog').modal('show');
													$('#sjzp-dialog .modal-title').text('修改');
													$('#sjzp-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
													$('#sjzp-dialog-save').show();
													$('#sjzp-dialog-djrq input.form-control').datetimepicker(dateDefaultOption);
													for (var i = 0; i < re.length; i++) {
														if (re[i].CPH == item.CPH) {
															var s = re[i];
															$("#bianId1").val(s.BID);
															$("#sjzp_cx1").val(s.CPH);
															$("#sjzp_clyynx1").val(s.CLXYNX);
															$("#sjzp_sjnl1").val(s.SJNL);
															$("#sjzp_xb1").val(s.XB);
															$("#sjzp_hj1").val(s.HJ);
															$("#sjzp_cynx1").val(s.CYNX);
															$("#sjzp_jl1").val(s.JL);
															$("#sjzp_bc1").val(s.BC);
															$("#sjzp_xj1").val(s.XJ);
															$("#sjzp_jbdd1").val(s.JBDD);
															$("#sjzp_sjyye1").val(s.SJXYE);
															$("#sjzp_lxdh1").val(s.LXDH);
															$("#sjzp_yx1").val(s.YX);
															$("#sjzp_lxr1").val(s.LXR);
															$("#sjzp_djrq1").val(s.DJRQ);
															$("#sjzp_bz1").val(s.BZ);
														}

													}

													$("#sjzp-dialog-save").off('click').on('click',function() {
																		jqxhr = $.ajax({
																					url : basePath+ "getUpSjzp",
																					type : 'post',
																					dataType : 'json',
																					data : {
																						"bid" : $(
																								"#bianId1")
																								.val(),
																						"cph" : $(
																								"#sjzp_cx1")
																								.val(),
																						"clxynx" : $(
																								"#sjzp_clyynx1")
																								.val(),
																						"sjnl" : $(
																								"#sjzp_sjnl1")
																								.val(),
																						"xb" : $(
																								"#sjzp_xb1")
																								.val(),
																						"gl" : $(
																								"#sjzp_jl1")
																								.val(),
																						"bc" : $(
																								"#sjzp_bc1")
																								.val(),
																						"jbdd" : $(
																								"#sjzp_jbdd1")
																								.val(),
																						"lxr" : $(
																								"#sjzp_lxr1")
																								.val(),
																						"djrq" : $(
																								"#sjzp_djrq1")
																								.val(),
																						"hj" : $(
																								"#sjzp_hj1")
																								.val(),
																						"cynx" : $(
																								"#sjzp_cynx1")
																								.val(),
																						"xj" : $(
																								"#sjzp_xj1")
																								.val(),
																						"sjxye" : $(
																								"#sjzp_sjyye1")
																								.val(),
																						"lxdh" : $(
																								"#sjzp_lxdh1")
																								.val(),
																						"yx" : $(
																								"#sjzp_yx1")
																								.val(),
																						"bz" : $(
																								"#sjzp_bz1")
																								.val(),
																					},
																					timeout : 180000,
																					success : function(
																							data) {
																						if (data > 0) {
																							$('#sjzp-dialog').modal('hide');
																							layer.msg('修改成功',{icon:1});
																							hxx();
																						} else {
																							layer.msg('修改失败',{icon:2});
																						}
																					}

																				});
																	});

													$('#sjzp-dialog-fsrq input.form-control').datetimepicker(dateDefaultOption);
													$('#sjzp-dialog-barq input.form-control').datetimepicker(dateYearDefaultOption);
												}),
								$('<a>').addClass('btn btn-danger btn-xs').text('删除').on('click',function() {

													layer.confirm(
																	'确认删除吗？',
																	{
																		btn : [
																				'确认',
																				'取消' ]
																	// 按钮
																	},
																	function() {
																		jqxhr = $.ajax({
																					url : basePath+ "getDeleteSjzp",
																					type : 'post',
																					dataType : 'json',
																					data : {
																						"bid" : item.BID
																					},
																					success : function(
																							data) {
																						if (data > 0) {
																							hxx();
																							layer.msg('删除成功',{icon : 1});
																						} else {
																							layer.msg('删除失败',{icon : 1});
																						}
																					}
																				});

																	},
																	function() {
																		layer.msg('已取消',{icon : 1});
																	});

													/*
													 * jqxhr=$.ajax({ url
													 * :basePath
													 * +"getDeleteSjzp", type
													 * :'post', dataType
													 * :'json',
													 * data:{"bid":item.BID},
													 * success:function(data){
													 * hxx(); } });
													 */

												}) ];
					},
					width : 135
				} ];
		// var sjzpData = [
		// {cphm: '车牌号1', sjmc: '姓名1', szmc: '违章时间1', yswp: '违章地点1', ccsj:
		// '违章内容1', qsdd: '处理结果1', weiz: '扣分1', xzjg: '执法机关罚款1'},
		// {cphm: '车牌号2', sjmc: '姓名2', szmc: '违章时间2', yswp: '违章地点2', ccsj:
		// '违章内容2', qsdd: '处理结果2', weiz: '扣分2', xzjg: '执法机关罚款2'},
		// {cphm: '车牌号3', sjmc: '姓名3', szmc: '违章时间3', yswp: '违章地点3', ccsj:
		// '违章内容3', qsdd: '处理结果3', weiz: '扣分3', xzjg: '执法机关罚款3'},
		// {cphm: '车牌号4', sjmc: '姓名4', szmc: '违章时间4', yswp: '违章地点4', ccsj:
		// '违章内容4', qsdd: '处理结果4', weiz: '扣分4', xzjg: '执法机关罚款4'},
		// {cphm: '车牌号5', sjmc: '姓名5', szmc: '违章时间5', yswp: '违章地点5', ccsj:
		// '违章内容5', qsdd: '处理结果5', weiz: '扣分5', xzjg: '执法机关罚款5'},
		// ];
		// var resetQueryConditions = function (e) {
		// $('.panel-queryBar .form-control').val('');
		// };
		var addSjzp = function(e) {
			$('#sjzp-dialog').modal('show');
			$('#sjzp-dialog .modal-title').text('添加');
			$('#sjzp-dialog-form').addClass('ip-type-input').removeClass(
					'ip-type-text');
			$('#sjzp-dialog-save').show();
			$('#sjzp-dialog-djrq input.form-control').datetimepicker(dateDefaultOption);
			$("#sjzp-dialog-save").off('click').on('click', function() {
				jqxhr = $.ajax({
					url : basePath + "getInsertSjzp",
					type : 'post',
					dataType : 'json',
					timeout : 180000,
					data : {

						"cph" : $("#sjzp_cx1").val(),
						"clxynx" : $("#sjzp_clyynx1").val(),
						"sjnl" : $("#sjzp_sjnl1").val(),
						"xb" : $("#sjzp_xb1").val(),
						"hj" : $("#sjzp_hj1").val(),
						"cynx" : $("#sjzp_cynx1").val(),
						"jl" : $("#sjzp_jl1").val(),
						"bc" : $("#sjzp_bc1").val(),
						"xj" : $("#sjzp_xj1").val(),
						"jbdd" : $("#sjzp_jbdd1").val(),
						"sjxye" : $("#sjzp_sjyye1").val(),
						"lxdh" : $("#sjzp_lxdh1").val(),
						"yx" : $("#sjzp_yx1").val(),
						"lxr" : $("#sjzp_lxr1").val(),
						"djrq" : $("#sjzp_djrq1").val(),
						"bz" : $("#sjzp_bz1").val(),
					},
					success : function(data) {
						if (data > 0) {
							layer.msg('添加成功', {
								icon : 1
							});
							$('#sjzp-dialog').modal('hide');
							hxx();
						} else {
							layer.msg('添加失败', {
								icon : 2
							});
						}
					}
				});
			});

		};

		// 图表
		function hxx() {
			$('#sjzpTable').jsGrid({
				width : '100%',
				height : 'calc(100% - 55px)',
				autoload : true,
				paging : true,
				pageLoading : true,
				pageSize : 15,
				pageIndex : 1,
				controller : {
					loadData : function(filter) {
						var d = $.Deferred();
						var a = res(filter, function(item) {
							d.resolve(item);
						})
						return d.promise();
					}
				},

				pagerContainer : null,
				pageButtonCount : 5,
				pagerFormat : "{first} {prev} {pages} {next} {last} {pageIndex} of {pageCount}",
				pagePrevText : "上一页",
				pageNextText : "下一页",
				pageFirstText : "第一页",
				pageLastText : "末页",
				pageNavigatorNextText : ">",
				pageNavigatorPrevText : "<"
			});
		}

		function res(filter, callback) {

			var startIndex = (filter.pageIndex - 1) * filter.pageSize;
			jqxhr = $.ajax({
				url : "../../getFinaAllSjzp",
				data : {
					"xm" : $("#sjzp-ximi").val(),
					"pageIndex" : filter.pageIndex,
					"pageSize" : filter.pageSize
				},
				dataType : 'json'
			}).done(function(json) {
				var xxfbData = [];

				all = json.data[0].count;
				re = json.data[0].datas;

				// for (var i = 0; i < re.length; i++) {
				// var iterm = re[i];
				// re[i].gridId=i+1;
				// }

				if (json.code == 0) {
					for (var i = 0; i < re.length; i++) {
						var rs = {};
						rs.gridId = startIndex + i + 1;
						rs.BID = re[i].BID;
						rs.CPH = re[i].CPH;
						rs.CLXYNX = re[i].CLXYNX;
						rs.SJNL = re[i].SJNL;
						rs.XB = re[i].XB;
						rs.JL = re[i].JL;
						rs.BC = re[i].BC;
						rs.JBDD = re[i].JBDD;
						rs.LXR = re[i].LXR;
						rs.DJRQ = re[i].DJRQ;

						xxfbData.push(rs);
					}
					return callback({
						data : xxfbData,
						itemsCount : all
					});
				} else {
				}
			}).fail(function() {
//				alert("数据异常");
			});
		}

		$(function() {
			$(".select2").select2({  
			  	language: "zh-CN",  //设置 提示语言
		        tags:true,  
		        createTag:function (decorated, params) {  
		            return null;  
		        },  
		    });
			// 初始化
			$('.addTimePeriod, .period').on('click', function() {
				if ($(this).hasClass('addTimePeriod'))
					$(this).addClass('period').removeClass('addTimePeriod');
				else if ($(this).hasClass('period'))
					$(this).addClass('addTimePeriod').removeClass('period');
			});
			$('#sjzp-wzxz').select2({
				language : 'zh-CN',
				width : '160',
				minimumResultsForSearch : -1,
				data : [ {
					id : '1',
					text : '违章性质1'
				}, {
					id : '2',
					text : '违章性质2'
				} ]
			});
			$('#sjzp-dialog-sglx select.form-control').select2({
				language : 'zh-CN',
				width : '168',
				minimumResultsForSearch : -1,
				data : [ {
					id : '1',
					text : '事故类别1'
				}, {
					id : '2',
					text : '事故类别2'
				} ]
			});
			$('#sjzp-dialog-wpzt input.form-control').datetimepicker(
					dateDefaultOption);
			// $('#sjzp-reset').on('click', resetQueryConditions);
			$('#sjzp-add').on('click', addSjzp);
			$('#sjzp-dialog').on('hidden.bs.modal',function(e) {
								$(this).find('input[type=text].form-control, textarea.form-control').val('');
								$(this).find('select.form-control').val('').trigger('change');
								$(this).find('div.form-control').text('');
							});
			$('#sjzpTable').jsGrid({
				width : 'calc(100% - 2px)',
				height : 'calc(100% - 2px)',
				editing : true,
				sorting : true,
				paging : false,
				autoload : true,
				data : [],
				fields : sjzpFields
			});
			$('.scrollbar-macosx').scrollbar();
			hxx();
		})

		// 查询
		$("#select_sjzp").on('click', function() {
			hxx();
		});

		// Export
		$("#sjzp-Export").off('click').on(
				'click',
				function() {
					layer.confirm('需要导出吗？', {
						btn : [ '确认', '取消' ]
					}, function() {
						layer.msg('导出成功', {
							icon : 1
						})
						window.open(basePath + "getsjzpExport?xm="+ $("#sjzp-ximi").val())
					}, function() {
						layer.msg('导出成功', {
							icon : 2
						})
					})

				})

	});
})(jQuery);
