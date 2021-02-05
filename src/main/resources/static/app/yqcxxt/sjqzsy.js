var sjqzsycx=(function ($) {
	$(function () {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 24;
		$('#sjqzsy-stateTime').datetimepicker(datetimeDefaultOption);
		$('#sjqzsy-endTime').datetimepicker(datetimeDefaultOption);
		// $('#sjqzsy-stateTime').val(new Date(today-oneday).Format('yyyy-MM-dd hh:mm:ss'));
		// $('#sjqzsy-endTime').val(new Date(today).Format('yyyy-MM-dd hh:mm:ss'));
		$('#sjqzsy-stateTime').val(new Date().Format('yyyy-MM-dd 00:00:00'));
		$('#sjqzsy-endTime').val(new Date(today).Format('yyyy-MM-dd hh:mm:ss'));
		$(document).ready(function() {
			$(".select2").select2({
				language: "zh-CN",  //设置 提示语言
				tags:true,
				allowClear: true,
				createTag:function (decorated, params) {
					return null;
				},
			});
		});
		// jqxhr=$.ajax({
		// 	type: "POST",
		// 	url: "../../ycyy/xll",
		// 	data:{"field":"CPHM","table":"TB_TAXI_SMDJ"},
		// 	timeout : 180000,
		// 	dataType: 'json',
		// 	success:function(data){
		// 		$("#sjqzsy-cphm").select2({
		// 			language: "zh-CN",  //设置 提示语言
		// 			minimumInputLength: 3,
		// 			tags:true,
		// 			allowClear: true,
		// 			closeOnSelect: true,
		// 			createTag:function (decorated, params) {
		// 				return null;
		// 			},
		// 		});
		// 		$("#sjqzsy-cphm").empty();
		// 		$("#sjqzsy-cphm").append('<option value=""></option>');
		// 		$("#sjqzsy-cphm").append('<option value="null">全部车牌</option>');
		// 		for(var i=0; i<data.length;i++){
		// 			$("#sjqzsy-cphm").append('<option value="'+data[i].CPHM+'">'+data[i].CPHM+'</option>');
		// 		}
		// 	},
		// 	error: function(){
		// 	}
		// });

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
                $('#sjqzsy-cphm').select2({
                    data: data,
                    language:'zh-CN',
                    minimumInputLength: 3,
                    allowClear: true
                });
            }
        });

		var bljlFields1 = [
			{name: 'id', title: '乘客明细', width: 60, align: 'center'},
			{name: 'ckdh', title: '乘客手机号', width: 120, align: 'center'},
			{name: 'ckxm', title: '乘客姓名', width: 120, align: 'center'},
			{name: 'djsj', title: '登记时间', width: 120, align: 'center'},
			{name: 'ckrs', title: '填写类型', width: 120, align: 'center'},
		];
		var bljlFields2 = [
			{name: 'id', title: '司机明细', width: 60, align: 'center'},
			{name: 'cphm', title: '车牌号', width: 120, align: 'center'},
			{name: 'ssgs', title: '所属公司', width: 120, align: 'center'},
			{name: 'djsj', title: '登记时间', width: 120, align: 'center'},
			{name: 'sjxm', title: '司机姓名', width: 120, align: 'center'},
			{name: 'sjdh', title: '司机电话', width: 120, align: 'center'},
			{name: 'ckrs', title: '填写类型', width: 120, align: 'center'},
		];
		$('#sjqzsy-select').on('click', function () {
			findsjqzsy();
		});
		var list1=[];
		var list2=[];
		var stime='';
		var etime='';
		var vehicle='';
		var phone='';
		var type = '';
		function findsjqzsy(){
			if($("#sjqzsy-stateTime").val()==''||$("#sjqzsy-endTime").val()==''){
				return layer.msg("请输入时间!!");
			}
			if($("#sjqzsy-cphm").val()==''&&$("#sjqzsy-phone").val()==''){
				return layer.msg("请输入车牌号或手机号码!!");
			}
			stime=$("#sjqzsy-stateTime").val();
			etime=$("#sjqzsy-endTime").val();
			vehicle=$("#sjqzsy-cphm").val();
			phone=$("#sjqzsy-phone").val();
			type = '';
			list1=[];
			list2=[];
			$('#sjqzsyDown').html("");
			var load=layer.load(1);
			jqxhr=$.ajax({
				type: "POST",
				url: "../../yqcx/findsjqzsy",
				data:{"stime":$("#sjqzsy-stateTime").val(),
					"etime":$("#sjqzsy-endTime").val(),
					"vehicle":$("#sjqzsy-cphm").val(),
					"phone": $("#sjqzsy-phone").val(),
				},
				timeout : 180000,
				dataType: 'json',
			}).done(function(data) {
				list1=data[0].list1;
				list2=data[0].list2;
				// var num=0;
				// if(list1.length>0){
				// 	for (var i = 0; i < list1.length; i++) {
				// 		num +=list1[i].CKRS;
				// 	}
				// }
				// $("#number1").html(num);
				$("#number1").html(list1.length);
				$("#number2").html(list2.length);
				layer.close(load);
			}).fail(function() {
				layer.close(load);
			});
		}

		$('#sjqzsy-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});


		$("#number1").on('click',function () {
			type='1';
			if(list1.length==0){
				return ;
			}
			findsjqzsy1();
		})

		function findsjqzsy1(){
			var all = 0;
			$('#sjqzsyDown').jsGrid({
				width: '100%',
				height: 'calc(100% - 125px)',
				autoload: true,
				pageLoading: true,
				editing: true,
				sorting: true,
				controller: {
					loadData: function(filter) {
						var d = $.Deferred();
						var a = re1(filter, function(item){
							d.resolve(item);
						})
						return d.promise();
					}
				},
				fields: bljlFields1,
			});

		}
		function re1(filter, callback) {
			var jsycxData = [];
			if (list1.length > 0) {
				for (var i = 0; i < list1.length; i++) {
					var rs = {};
					rs.id =  i+1;
					rs.ckdh =  list1[i].CKDH;
					rs.ckxm =  list1[i].CKXM;
					rs.djsj =  (list1[i].DJSJ==null?"":(new Date(list1[i].DJSJ)).Format("yyyy-MM-dd hh:mm:ss"));
					rs.ckrs =  list1[i].CKRS;
					jsycxData.push(rs);
				}
				return callback({
					data: jsycxData,
					itemsCount: jsycxData.length
				});

			}
		}

		$("#number2").on('click',function () {
			type='2';
			if(list2.length==0){
				return ;
			}
			findsjqzsy2();
		})

		function findsjqzsy2(){
			var all = 0;
			$('#sjqzsyDown').jsGrid({
				width: '100%',
				height: 'calc(100% - 125px)',
				autoload: true,
				pageLoading: true,
				controller: {
					loadData: function(filter) {
						var d = $.Deferred();
						var a = re2(filter, function(item){
							d.resolve(item);
						})
						return d.promise();
					}
				},
				fields: bljlFields2,
			});

		}
		function re2(filter, callback) {
			var jsycxData = [];
			if (list2.length > 0) {
				for (var i = 0; i < list2.length; i++) {
					var rs = {};
					rs.id =  i+1;
					rs.cphm =  list2[i].CPHM;
					rs.ssgs =  list2[i].COMPANY_NAME;
					rs.sjxm =  list2[i].SJXM;
					rs.sjdh =  list2[i].SJDH;
					rs.djsj =  (list2[i].DJSJ==null?"":(new Date(list2[i].DJSJ)).Format("yyyy-MM-dd hh:mm:ss"));
					rs.ckrs =  list2[i].CKRS;
					jsycxData.push(rs);
				}
				return callback({
					data: jsycxData,
					itemsCount: jsycxData.length
				});

			}
		}
		$('#sjqzsy-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});
		$('#sjqzsy-dc').on('click', function () {
			if(type==''){
				return layer.msg("请选择导出内容!!");
			}
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"yqcx/findsjqzsydc?stime="+stime
					+"&etime="+etime
					+"&vehicle="+vehicle
					+"&phone="+phone
					+"&type="+type
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})

})(jQuery)

