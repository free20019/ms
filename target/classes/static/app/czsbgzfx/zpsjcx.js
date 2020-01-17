var zpsjcx = (function (Vue, _, $) {
	var clfb_map;

	var vm = new Vue({
		el: '#root',
		data: function () {
			return {
				yehu: [],
				yehuOptions: [],
				changzhan: [],
				changzhanOptions: []
			}
		},
		mounted: function () {
			var _this = this;
			this.$nextTick(function () {
//				_this.getxll();
				_this.getczxll();
			});
		},
		methods: {
			getxll :function(){
				var _this = this;
				 $.ajax({
					type: "POST",
					url: "../../zpsj/xll",
					data: {"field": "ZGS", "table": "jjq_company"},
					timeout: 180000,
					dataType: 'json',
					success: function (data) {
						console.log("123="+data)
						_this.yehuOptions = _.map(data, function(item) {
							return item.ZGS && {id: item.ZGS, label: item.ZGS};
						})
					},
					error: function () {
					}
				});
			},
			getczxll :function(){
				var _this = this;
				$.ajax({
					type: "POST",
					url: "../../zpsj/xll",
					data: {"field": "ADDRESS", "table": "tb_vehicle_hk"},
					timeout: 180000,
					dataType: 'json',
					success: function (data) {
						_this.changzhanOptions = _.map(data, function(item) {
							return item.ADDRESS && {id: item.ADDRESS, label: item.ADDRESS};
						})
					},
					error: function () {
					}
				});
			}
		},
		components: {
			'tree-select': VueTreeselect.Treeselect
		}
	});
	$(function () {
		$('#zpsjcx-stateTime').datetimepicker(datetimeDefaultOption);
		$('#zpsjcx-endTime').datetimepicker(datetimeDefaultOption);
		$('#zpsjcx-stateTime').val(new Date().Format('yyyy-MM-dd 00:00:00'));
		$('#zpsjcx-endTime').val(new Date().Format('yyyy-MM-dd 23:59:59'));
		$(".select2").select2({
			language: "zh-CN",  //设置 提示语言
			tags: true,
			allowClear: true,
			createTag: function (decorated, params) {
				return null;
			},
		});
		$('#zpsjcx-option').select2({
			language: 'zh-CN',
			allowClear: false,
			minimumResultsForSearch: -1,
			data: [
				{id: '1', text: '时间日期'},
				{id: 'null', text: '全天'},
				{id: '21:00:00-02:00:00', text: '21:00:00-02:00:00'}
			]
		});
		$('#zpsjcx-option').on('change', function () {
			if ($('#zpsjcx-option').val() == '1') {
				$('#zpsjcx-stateTime').datetimepicker('remove');
				$('#zpsjcx-endTime').datetimepicker('remove');
				$('#zpsjcx-stateTime').datetimepicker(datetimeDefaultOption);
				$('#zpsjcx-endTime').datetimepicker(datetimeDefaultOption);
				$('#zpsjcx-stateTime').val(new Date().Format('yyyy-MM-dd 00:00:00'));
				$('#zpsjcx-endTime').val(new Date().Format('yyyy-MM-dd 23:59:59'));
			} else {
				$('#zpsjcx-stateTime').datetimepicker('remove');
				$('#zpsjcx-endTime').datetimepicker('remove');
				$('#zpsjcx-stateTime').datetimepicker(dateDefaultOption);
				$('#zpsjcx-endTime').datetimepicker(dateDefaultOption);
				if ($('#zpsjcx-option').val() == 'null') {
					$('#zpsjcx-stateTime').val(new Date().Format('yyyy-MM-dd'));
					$('#zpsjcx-endTime').val(new Date().Format('yyyy-MM-dd'));
				} else {
					$('#zpsjcx-stateTime').val(new Date(new Date() - 1000 * 60 * 60 * 24).Format('yyyy-MM-dd'));
					$('#zpsjcx-endTime').val(new Date(new Date() - 1000 * 60 * 60 * 24).Format('yyyy-MM-dd'));
				}
			}
		});
//		jqxhr = $.ajax({
//			type: "POST",
//			url: "../../zpsj/xll",
//			data: {"field": "VEHICLE_NO", "table": "tb_vehicle_hk"},
//			timeout: 180000,
//			dataType: 'json',
//			success: function (data) {
//				for (var i = 0; i < data.length; i++) {
//					if (null != data[i].VEHICLE_NO) {
//						data[i].id = data[i].VEHICLE_NO;
//						data[i].text = data[i].VEHICLE_NO;
//					}
//				}
//				var qb = {};
//				qb.id = 'null';
//				qb.text = '全部';
//				data.unshift(qb);
//				$("#zpsjcx-cphm").select2({
//					language: "zh-CN",  //设置 提示语言
//					minimumInputLength: 3,
//					allowClear: true,
//					data: data
//				});
//			},
//			error: function () {
//			}
//		});
		jqxhr = $.ajax({
			type: "POST",
			url: "../../zpsj/xll",
			data: {"field": "ADDRESS", "table": "tb_vehicle_hk"},
			timeout: 180000,
			dataType: 'json',
			success: function (data) {
				for (var i = 0; i < data.length; i++) {
					if (null != data[i].ADDRESS) {
						data[i].id = data[i].ADDRESS;
						data[i].text = data[i].ADDRESS;
					}
				}
				var qb = {};
				qb.id = 'null';
				qb.text = '全部';
				data.unshift(qb);
				$("#zpsjcx-address").select2({
					language: "zh-CN",  //设置 提示语言
					multiple: true,
					allowClear: true,
					data: data
				});
			},
			error: function () {
			}
		});

//		jqxhr=$.ajax({
//			type: "POST",
//			url:"../../claq/qycomp",
//			data:{},
//			dataType: 'json',
//			timeout : 3600000,
//			success:function(json){
//				console.log(json);
//				var data= json.datacomp;
//				for (var i = 0; i < data.length; i++) {
//					data[i].id=data[i].NAME;
//					data[i].text=data[i].NAME;
//				}
//				var qb= {};var map1= {};var map2= {};
//				qb.id = 'null';qb.text = '全部';
//				map1.id='公司为空';map1.text='公司为空';
//				map2.id='公司不为空';map2.text='公司不为空';
//				data.unshift(map1);data.unshift(map2);data.unshift(qb);
//				$('#zpsjcx-gs').select2({
//					data: data,
//					allowClear: true
//				});
//			}
//		});
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
				$('#zpsjcx-cphm').select2({
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
		 		var qb= {};var map1= {};var map2= {};
				qb.id = 'null';qb.text = '全部';
				map1.id='公司为空';map1.text='公司为空';
				map2.id='公司不为空';map2.text='公司不为空';
				data.unshift(map1);data.unshift(map2);data.unshift(qb);
		 		$('#zpsjcx-gs').select2({
		 			data: data,
		 			allowClear: true
		 			});
		 	}
		 });
		var bljlFields = [
			{name: 'id', title: '序号', width: 60, align: 'center'},
			{name: 'gs', title: '业户', width: 120, align: 'center'},
			{name: 'cphm', title: '车牌号', width: 120, align: 'center'},
			{name: 'cjdd', title: '场站', width: 120, align: 'center'},
			{name: 'cjsj', title: '时间', width: 120, align: 'center'},
			{
				name: 'caozuo', title: '操作',
				itemTemplate:
					function (value, item) {
						var style = {marginRight: '4px'};
						return [
							$('<a>').addClass('btn btn-primary btn-sm').text('详情').on('click', function () {
								var key = "pic_" + item.cphm + "_" + item.cjdd + "_" + item.cjsj.replace("-", "").replace("-", "").replace(" ", "").replace(":", "").replace(":", "") + ".jpg";
								document.getElementById('zpsjbig').src = "../../common/pic?key=" + key;
								imagebigscale("zpsjbigImage", "zpsjbig", "zpsjimagetool", "zpsjclose", "zpsjleft-rote", "zpsjright-rote", "zpsjbigger", "zpsjsmaller");
								document.getElementById('zpsjbigImage').style.display = 'block';
								document.getElementById('zpsjbigImage').style.top = '0px';
								document.getElementById('zpsjbigImage').style.left = '30px';
							})
						]
					}, width: 40, align: 'center'
			}
		];
		$('#zpsjcx-select').on('click', function () {
			findzpsjcx();
		});
		function findzpsjcx() {
			var all = 0;
			$('#zpsjcxTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 55px)',
				autoload: true,
				paging: true,
				pageLoading: true,
				editing: true,
				sorting: true,
				pageSize: 15,
				pageIndex: 1,
				controller: {
					loadData: function (filter) {
						var d = $.Deferred();
						var a = re(filter, function (item) {
							d.resolve(item);
						})
						return d.promise();
					}
				},
				fields: bljlFields,
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

		function re(filter, callback) {
			// var check = '';
			// if ($("#check").is(':checked')) {
			// 	check = '1';
			// }
//			var options = document.getElementById("zpsjcx-address");
//			var address = "";
//			for (i = 0; i < options.length; i++) {
//				if (options.options[i].selected) {
//					address += "'" + options.options[i].value + "',";
//				}
//			}
//			address = address.substr(0, (address.length - 1));
			var address = "";
			for (i = 0; i < vm.changzhan.length; i++) {
				address += "'" + vm.changzhan[i] + "',";
			}
			address = address.substr(0, (address.length - 1));
			var startIndex = (filter.pageIndex - 1) * filter.pageSize;
			jqxhr = $.ajax({
				type: "POST",
				url: "../../zpsj/findzpsjcx",
				data: {
					"stime": $("#zpsjcx-stateTime").val(),
					"etime": $("#zpsjcx-endTime").val(),
					"vehicle": $("#zpsjcx-cphm").val(),
					"address": address,
					"company": $("#zpsjcx-gs").val(),
					"type": $('#zpsjcx-option').val(),
					"check": $('#check').val(),
					"pageIndex": filter.pageIndex,
					"pageSize": filter.pageSize
				},
				timeout: 180000,
				dataType: 'json',
			}).done(function (data) {
				var jsycxData = [];
				all = data[0].count;
				if (data.length > 0) {
					for (var i = 0; i < data[0].datas.length; i++) {
						var rs = {};
						var vehicle = data[0].datas[i];
						rs.id = startIndex + i + 1;
						rs.gs = data[0].datas[i].COMPANY_NAME;
						rs.cphm = data[0].datas[i].VEHICLE_NO;
						rs.cjdd = data[0].datas[i].ADDRESS;
						rs.cjsj = (data[0].datas[i].DBTIME == null ? "" : (new Date(data[0].datas[i].DBTIME)).Format("yyyy-MM-dd hh:mm:ss"));
						jsycxData.push(rs);
					}
					return callback({
						data: jsycxData,
						itemsCount: all
					});

				} else {
				}
			}).fail(function () {
			});
		}

		findzpsjcx();
		$('#zpsjcx-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});
		$('#zpsjcx-dc').on('click', function () {
			// var check = '';
			// if ($("#check").is(':checked')) {
			// 	check = '1';
			// }
			var address = "";
			for (i = 0; i < vm.changzhan.length; i++) {
				address += "'" + vm.changzhan[i] + "',";
			}
			address = address.substr(0, (address.length - 1));
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath + "zpsj/findzpsjcxdc?stime=" + $("#zpsjcx-stateTime").val()
					+ "&etime=" + $("#zpsjcx-endTime").val()
					+ "&vehicle=" + $("#zpsjcx-cphm").val()
					+ "&address=" + address
					+ "&company=" + $("#zpsjcx-gs").val()
					+ "&type=" + $('#zpsjcx-option').val()
					+ "&check=" + $('#check').val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
})(Vue, _, jQuery);

