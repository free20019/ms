
var aqgg = (function($) {
    var all = 0,re;
    $(function () {
    	
    	$("#aqgg-datetimeStart").datetimepicker(dateDefaultOption);
        $("#aqgg-datetimeEnd").datetimepicker(dateDefaultOption);
        $("#xxfb_fbrq").datetimepicker(dateDefaultOption);
        var xxfbFields = [
            {name: 'gridId', title: '序号', width: 60, align: 'center'},
            {name: 'BT', title: '标题',  width: 140,align: 'center' },
            {name: 'NR', title: '内容', width: 280,align: 'center' },
            {name: 'FBRQ', title: '发布日期', width: 160 ,align: 'center'},
            {name: 'LB', title: '类别', width: 120,align: 'center'},
            {name: 'SFBD', title: '是否必读', itemTemplate:formatMalfunction, width: 120,align: 'center'},
            {name: 'FBBM', title: '发布部门', width: 120,align: 'center'},
            {name: 'fj', title: '附件',itemTemplate: function (_,item) {
                var style = {marginRight: '4px'};
                if(item.WJM!=""){               	
                	return [               	        
                	        $('<a title="下载">').text(item.WJM).css(style).on('click', function () {                        
                	        	url = "../../jxxfb/download?address="+item.FJ+"&filename="+item.WJM, window.open(url)
                	        }),
//                	        $('<font>').text(item.WJM)
                	  ];
                }
            }, width: 200,align: 'center' },
            {name: 'gs', title: '查看公司',itemTemplate: function (_,item) {
                var style = {marginRight: '4px'};
            	return [               	        
					$('<a>').addClass('btn btn-primary btn-xs').text('公司详情').css(style).on('click', function () {
						$('#qd-revenuetbody').html("");
						jqxhr=$.ajax({
							url:"../../jxxfb/xxfbgs",
							type:'post',
							dataType:'json',
							data:{"id":item.BID},
							timeout:180000,
							success:function (data) {
								$('#gs-dialog').modal('show');	
								for (var i = 0; i < data.data.length; i++) {
									$('<tr><td>' + (i + 1) + '</td>' +
											'<td>' + data.data[i].COMPANY_NAME + '</td>' +
											'<td>已查看</td>' +
											'</tr>')
									.appendTo('#qd-revenuetbody');
								}
								
							}
						});
					 }),   
            	  ];
            }, width: 120,align: 'center'},
            {name: 'caozuo', title: '操作',
                itemTemplate: function (_,item) {
                    var style = {marginRight: '4px'};
                    return [
                        $('<a>').addClass('btn btn-primary btn-xs').text('详情').css(style).on('click', function () {
                        	$(".a-upload").hide();
                            $('#aqgg-dialog').modal('show');
                            $('#aqgg-dialog .modal-title').text('详情');
                            $('#aqgg-dialog-form').addClass('ip-type-text').removeClass('ip-type-input');
                            $('#tijiao').hide();
                            $('#xxfb_bt').val(item.BT);
                            $('#xxfb_nr').val(item.NR);
                            $('#xxfb_fbrq').val(item.FBRQ);
                            $('#xxfb_lb').val(item.LB).trigger('change');
                            $('#xxfb_bd').val(item.SFBD).trigger('change');
                            $("#xxfb_fjm").html(item.WJM);
                            $("#xxfb_fbbm").val(item.FBBM);
                        }),
                        $('<a>').addClass('btn btn-primary btn-xs').text('修改').css(style).on('click', function () {
                        	file_info=null;
                        	$(".a-upload").show();
                        	$('#tijiao').show();
                            $('#aqgg-dialog').modal('show');
                            $('#aqgg-dialog .modal-title').text('修改');
                            $('#aqgg-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
                            $('#xxfb_bt').val(item.BT);
                            $('#xxfb_nr').val(item.NR);
                            $('#xxfb_fbrq').val(item.FBRQ);
                            $("#xxfb_fjm").html(item.WJM);
                            $("#xxfb_fbbm").val(item.FBBM);
                            $('#xxfb_lb').val(item.LB).trigger('change');
                            $('#xxfb_bd').val(item.SFBD).trigger('change');
                            
                            $("#tijiao").off('click').on('click',function () {
                                jqxhr=$.ajax({
                                    url:"../../jxxfb/getUpdatexxfb",
                                    type:'post',
                                    dataType:'json',
                                    data:{"id":item.BID,
                                        "bt":$('#xxfb_bt').val(),
                                        "nr":nr=$('#xxfb_nr').val(),
                                        "fbrq":fbrq=$('#xxfb_fbrq').val(),
                                        "lb":$('#xxfb_lb').val(),
                                        "bd":$('#xxfb_bd').val(),
                                        'fbbm':$('#xxfb_fbbm').val(),
                                        'lx':1,
                                    	'fj':$("#xxfb_fjm").html()==""?"":(file_info==null?item.FJ:file_info.file_address),
                                        'wjm':$("#xxfb_fjm").html()==""?"":(file_info==null?$("#xxfb_fjm").html():file_info.file_name)
                                    },
                                    timeout:180000,
                                    success:function (data) {
                                        if(data>0){
                                            $('#aqgg-dialog').modal('hide');
                                            hxx();
                                           layer.msg('修改成功',{icon:1})
                                        }else{
                                            layer.msg('修改失败',{icon:2})
                                        }
                                    }
                                });
                            });
                            $('#aqgg-dialog-pdrq input.form-control').datetimepicker(dateDefaultOption);
                        }),
                        $('<a>').addClass('btn btn-danger btn-xs').text('删除').on('click', function () {
                            layer.confirm('确认删除吗？',
                                {
                                    btn: ['确认','取消'] //按钮
                                }, function(){
                                    jqxhr=$.ajax({
                                        url :"../../jxxfb/getDeletexxfb",
                                        type :'post',
                                        dataType :'json',
                                        data:{"id":item.BID},
                                        success:function(data){
                                            if(data>0){
                                                hxx();
                                                layer.msg('删除成功', {icon: 1});
                                            }else{
                                                layer.msg('删除失败', {icon: 2});
                                            }
                                        }
                                    });

                                }, function(){
                                    layer.msg('已取消', {icon: 1});
                                });



                        })
                    ];
                }, width: 135}
        ];
        var resetQueryConditions = function (e) {
            $('#aqgg-siji').val('');
            $("#aqgg-cphm").select2('val','0')

        };
        var addxxfb = function (e) {
        	file_info=null;
        	$(".a-upload").show();
        	$('#tijiao').show();
            $('#aqgg-dialog').modal('show');
            $('#aqgg-dialog .modal-title').text('添加');
            $('#aqgg-dialog-form').addClass('ip-type-input').removeClass('ip-type-text');
            $('#aqgg-dialog-save').show();
            $('#xxfb_fbrq1 input.form-control').datetimepicker(dateDefaultOption);
            $('#aqgg-dialog-shij input.form-control').datetimepicker(dateYearDefaultOption);
            $("#xxfb_bt").val("");
            $("#xxfb_nr").val("");
            $("#xxfb_fbrq").val("");
            $("#xxfb_lb").val("");
            $("#xxfb_bd").val("");
            $("#xxfb_fj").val("");
            $("#xxfb_fbbm").val("");
            $("#xxfb_fjm").html("");
            //添加
            $("#tijiao").off('click').on('click',function () {
                var bt = $("#xxfb_bt").val();
                var nr = $("#xxfb_nr").val();
                var fbrq = $("#xxfb_fbrq").val();
                var lb = $("#xxfb_lb").val();
                var bd = $("#xxfb_bd").val();
                var fbbm = $("#xxfb_fbbm").val();
                jqxhr=$.ajax({
                    url:"../../jxxfb/getInsertxxfb",
                    type:'post',
                    dataType:'json',
                    data:{'bt':bt,'nr':nr,'fbrq':fbrq,'lb':lb,'bd':bd,'fbbm':fbbm,'lx':1,
                    	'fj':$("#xxfb_fjm").html()==""?"":(file_info==null?"":file_info.file_address),
                    	'wjm':$("#xxfb_fjm").html()==""?"":(file_info==null?"":file_info.file_name)},
                    Timeout:180000,
                    success:function (data) {
                        if(data>0){
                            layer.msg('添加成功',{icon:1});
                            $('#aqgg-dialog').modal('hide');
                            hxx();
                        }else{
                            layer.msg('添加失败',{icon:2});
                        }
                    }
                });
            })

        };





        function hxx(){
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
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
                url:"../../jxxfb/find",
                data:{
                	"lx" : 1,
                	"title" : $("#aqgg-title").val(),
                    "datetimeStart" : $("#aqgg-datetimeStart").val(),
                    "datetimeEnd" : $("#aqgg-datetimeEnd").val(),
                    "type" : $("#aqgg-type option:selected").html(),
                    "pageIndex" : filter.pageIndex,
                    "pageSize" : filter.pageSize
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
//                alert("数据异常");
            });
        }




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

            $('#aqgg-reset').on('click', resetQueryConditions);
            $('#aqgg-add').on('click', addxxfb);
            $('#aqgg-dialog').on('hidden.bs.modal', function (e) {
                $(this).find('input[type=text].form-control, textarea.form-control').val('');
                $(this).find('select.form-control').val('').trigger('change');
                $(this).find('div.form-control').text('');
            });

            $('#aqgg-type').select2({
                language : 'zh-CN',
                width : 160,
                minimumResultsForSearch : -1,
                allowClear: true,
                data: [{ id: '1', text: '提醒单' },
                    { id: '2', text: '预警信息' },
                    { id: '3', text: '会议' },
                    { id: '4', text: '通知' },
                    { id: '5', text: '公告' },
                    { id: '6', text: '公示' },
                    { id: '7', text: '文件' },
                    { id: '8', text: '政策法规'},
                    { id: '9', text: '其他'},
                ]
            });

            $('#xxfb_lb').select2({
                language : 'zh-CN',
                width : '100%',
                minimumResultsForSearch : -1,
                allowClear: true,
                data: [
                    {id: '提醒单', text: '提醒单'},
                    {id: '预警信息', text: '预警信息'},
                    {id: '会议', text: '会议'},
                    {id: '通知', text: '通知'},
                    {id: '公告', text: '公告'},
                    {id: '公示', text: '公示'},
                    {id: '文件', text: '文件'},
                    {id: '政策法规', text: '政策法规'},
                    {id: '其他', text: '其他'}
                ]
            });
            
            $('#xxfb_bd').select2({
                language : 'zh-CN',
                width : '100%',
                minimumResultsForSearch : -1,
                allowClear: true,
                data: [
                    {id: '1', text: '必读'},
                    {id: '2', text: '选读'},
                ]
            });
            $('#cxycfxTable').jsGrid({
                width: 'calc(100% - 2px)',
                height: 'calc(100% - 2px)',
                editing: true,
                sorting: true,
                paging: false,
                autoload: true,
                data: [],
                fields: xxfbFields
            });
            $('.scrollbar-macosx').scrollbar();

            //查询
            hxx();
            $("#aqgg-select").on('click', function() {
                hxx();
            });

            //导出
            $("#xxfb_Export").on('click',function() {

                layer.confirm('确认导出吗?',{btn:['确认','取消']
                },function () {
                    layer.msg('导出成功',{icon:1});
                    window.open("../../jxxfb/getExportFindhxx?lx=1&title=" + $("#aqgg-title").val()+"&datetimeStart=" + $("#aqgg-datetimeStart").val() + "&datetimeEnd=" + $("#aqgg-datetimeEnd").val() + "&type=" + $("#aqgg-type option:selected").html())
                },function () {
                    layer.msg('已取消导出',{icon:2});
                })

            });
        });
        var file_info=null;
        var $input =  $("#xxfb_fj");
        // ①为input设定change事件
        $input.change(function () {
        //②如果value不为空，调用文件加载方法
            if($(this).val() != ""){
                fileLoad(this);
            }else{
            	 $("#xxfb_fjm").html("");
            }
        })
        //③创建fileLoad方法用来上传文件
		function fileLoad(ele){
		    //④创建一个formData对象
		    var formData = new FormData();
		    //⑤获取传入元素的val
		    var name = $(ele).val();
		    //⑥获取files
		    var files = $(ele)[0].files[0];
		    //⑦将name 和 files 添加到formData中，键值对形式
		    formData.append("file", files);
		    formData.append("name", name);
		    $.ajax({
		        url: "../../jxxfb/importfile",
		        type: 'POST',
		        data: formData,
		        processData: false,// ⑧告诉jQuery不要去处理发送的数据
		        contentType: false, // ⑨告诉jQuery不要去设置Content-Type请求头
		        dataType: 'json',
		        beforeSend: function () {
		           //⑩发送之前的动作
		        },
		        success: function (responseStr) {
		           //11成功后的动作
		        	file_info=responseStr;
		            $("#xxfb_fjm").html(file_info.file_name);
		        }
		        ,
		        error : function (responseStr) {
		            //12出错后的动作
		        }
		    });
		}
        function formatMalfunction (val) {
			if (val === '1') return "必读";
			else if (val === '2') return "选读";
			else return '';
		}
    });
})(jQuery);

