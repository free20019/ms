var czrz=(function ($) {
    var echart = null;
    $(function () {
        $('.scrollbar-macosx').scrollbar();
        $('#czrz-stateTime').datetimepicker(dateDefaultOption);
        $('#czrz-stateTime').val(new Date().Format('yyyy-MM-dd'));
        $('#czrz-endTime').datetimepicker(dateDefaultOption);
        $('#czrz-endTime').val(new Date().Format('yyyy-MM-dd'));
        var bljlFields = [
            {name: 'ID', title: '序号', width: 60, align: 'center'},
            {name: 'ACTIONDES', title: '操作描述', width: 100, align: 'center'},
            {name: 'ACTIONIP', title: '操作ip', width: 100, align: 'center'},
            {name: 'USERNAME', title: '用户', width: 100, align: 'center'},
            {name: 'ACTIONTIME', title: '时间', width: 100, align: 'center'},
            {name: 'METHODNAME', title: '方法名', width: 200, align: 'center'},
            // {name: 'METHODPARAMS', title: '方法参数',width: 200, align: 'center'},
            // {name: 'ACTIONSQL', title: 'sql', width: 100, align: 'center'},
            // {name: 'RESULT', title: '结果', width: 100, align: 'center'},
            {name: 'caozuo', title: '操作',
                itemTemplate:
                    function(value, item) {
                        var style = {marginRight: '4px'};
                        return [
                            $('<a>').addClass('btn btn-primary btn-sm').text('结果').on('click', function () {
                                $('#czrz-dialog').modal('show');
                                // $('#czrzResult').html( JSON.stringify(JSON.parse(item.RESULT),null,2));
                                $('#czrzResult').html(item.RESULT);


                            })
                        ]
                    }, width: 100, align: 'center'
            }

        ];
        $('#czrz-select').on('click', function () {
            findczrz();
        });
        function findczrz(){
            var all = 0;
            $('#czrzTable').jsGrid({
                width: '100%',
                height: 'calc(100% - 50px)',
                autoload: true,
                paging: true,
                pageLoading: true,
                editing: true,
                sorting: true,
                pageSize: 15,
                pageIndex: 1,
                controller: {
                    loadData: function(filter) {
                        var d = $.Deferred();
                        var a = re(filter, function(item){
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
        function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
                type: "POST",
                url: "../../yqcx/findczrz",
                data:{"stime":$("#czrz-stateTime").val(),
                    "etime":$("#czrz-endTime").val(),
                    "value":$("#czrz-value").val(),
                    "pageIndex":filter.pageIndex,
                    "pageSize":filter.pageSize
                },
                timeout : 180000,
                dataType: 'json',
            }).done(function(data) {
                if(data.code==500100){
                    layer.msg('数据不存在',{icon:2});
                    return callback();
                }
                all = data[0].count;
                if(data.length>0){
                    for(var i = 0; i< data[0].datas.length ;i++){
                        data[0].datas[i].ID=startIndex+i+1;
                    }
                    return callback({
                        data: data[0].datas,
                        itemsCount: all
                    });

                }else{
                }
            }).fail(function() {
            });
        }
        findczrz();
        $('#czrz-dc').on('click', function () {
            layer.confirm('你确定要导出数据', {
                btn: ['确定', '取消'],
                offset: '100px'
            }, function (layerIndex) {
                window.open(basePath+"yqcx/findczrzdc?stime="+$("#czrz-stateTime").val()
                    +"&etime="+$("#czrz-endTime").val()
                    +"&value="+$("#czrz-value").val()
                );
                layer.close(layerIndex);
            }, function (layerIndex) {
                layer.close(layerIndex);
            });
        });
    })

})(jQuery)