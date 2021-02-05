;(function (Vue, _, $) {
    var vm = new Vue({
        el: '#root',
        data() {
            return {
                radioCarType: '',
                map: null,
                mapInfoWindow: null,
                SimpleMarker: null,
                mapMarker: {
                    //大队
                    Notmoving: [],
                    //监控类型车辆打点
                    point: [],
                    //搜索车辆打点
                    search: [],
                },
                pathSimplifierIns: null,
                mapScreen: {},
                checkType: [],
                carType: [],
                zfcType: [],
                zfcIndeterminate: false,
                zfcChecked: false,
                // 悬浮框展开
                panelFlag: {
                    first: false,
                    second: false,
                },
                forkList: [],
                forkTrajectoryList: [],
                vehicleSelectInfoList: [],
                query: {
                    cphm: '',
                    radioCarType: 0,
                },
                vehicleType: [
                    { id: 'xyc', type: '0', type_name: '巡游车' }, //出租
                    { id: 'wyc', type: '1', type_name: '网约车' }, //网约
                    {
                        id: 'lklh',
                        type: '2',
                        type_name: '两客一危',
                        sonType: [
                            // { id: 'pthy', type: '2.0', value: '普通货运', pic: '' }, //普通货运
                            // { id: 'wxpc', type: '2.1', value: '危险品', pic: '' }, //危险品
                            // { id: 'gcc', type: '2.2', value: '工程车', pic: '' }, //工程车
                            // { id: 'lykc', type: '2.3', value: '客车旅游', pic: '' }, //旅游客车
                            {
                                id: 'pthy',
                                clusterId: '2',
                                type: '2.0',
                                value: '班车客运',
                                pic: '',
                            }, //班车客运
                            {
                                id: 'wxpc',
                                clusterId: '3',
                                type: '2.1',
                                value: '包车客运',
                                pic: '',
                            }, //包车客运
                            {
                                id: 'gcc',
                                clusterId: '4',
                                type: '2.2',
                                value: '普货',
                                pic: '',
                            }, //普货
                            {
                                id: 'lykc',
                                clusterId: '5',
                                type: '2.3',
                                value: '危货',
                                pic: '',
                            }, //危货
                        ],
                    }, //两客一危
                    {
                        id: 'zfc',
                        type: '3',
                        type_name: '执法车',
                        pic: [
                            '../../resources/images/jkzx/执法车1.png',
                            '../../resources/images/jkzx/执法车2.png',
                        ],
                        color: '#FFFFFF',
                        sonType: [
                            {
                                id: 'ygzfc',
                                name: '运管执法车',
                                type: '3.0',
                                value: '3',
                                color: '#3399FF',
                                pic: '../../resources/images/jkzx/执法车.png',
                            }, //运管执法车
                            {
                                id: 'glzfc',
                                name: '公路执法车',
                                type: '3.1',
                                value: '2',
                                color: '#FFB679',
                                pic: '../../resources/images/jkzx/公路执法车2.png',
                            }, //公路执法车
                            {
                                id: 'gwc',
                                name: '公务车',
                                type: '3.2',
                                value: '1',
                                color: '#0B0C52',
                                pic:
                                    '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
                            }, //公务车
                        ],
                    }, //执法车
                    { id: 'zfzd', type: '4', type_name: '执法终端' }, //执法终端
                    {
                        id: 'djj',
                        type: '5',
                        type_name: '对讲机',
                        pic: [
                            '../../resources/images/jkzx/对讲机1.png',
                            '../../resources/images/jkzx/对讲机2.png',
                        ],
                        color: '#FFFFFF',
                    }, //对讲机
                    {
                        id: '执法大队',
                        type: '6',
                        type_name: '执法大队',
                        pic: '../../resources/images/jkzx/房子.png',
                    }, //执法大队
                ],
                chooseVehicleType: '',
                //直接显示定位的车辆类型
                showLocationVehicleType: ['3', '4', '5'],
                // 地图上边搜索框
                mapQuery: {
                    type: '',
                    input: '',
                },
                overTime:[
                    10 * 60 * 1000,//巡游车
                    10 * 60 * 1000,//网约
                    10 * 60 * 1000,//两客一危
                    3 * 60 * 1000,//执法车
                    10 * 60 * 1000,//执法终端
                    10 * 60 * 1000,//对讲机
                ]
            }
        },
        computed: {},
        mounted() {
            this.initMap()
            AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
                this.SimpleMarker = SimpleMarker
            })
            this.handleMapMouseZoom()
            this.getMonitorVehicle(this.query.radioCarType, "")
            setInterval(() => {
                this.getMapTrajectoryAllTwo()
            }, 2 * 60 * 1000)
        },
        methods: {
            // 地图上半部分搜索框清除事件
            handleMapInputClearClick() {
                //信息框清除
                if (this.mapInfoWindow) {
                    this.mapInfoWindow.setMap(null)
                }
                this.clearMapMarkers(this.mapMarker.search)
                this.mapMarker.search = []
            },

            // 地图上半部分搜索框
            handleMapInputClick() {
                for (let i = 3; i < 6; i++) {
                    this.mapQuery.type = i.toString()
                    const { type, input } = this.mapQuery
                    if (!type) return this.$message.error('请选择监控类型')
                    if (!input) return this.$message.error('请输入车牌号码')
                    axios
                        .get('jkzx/getLocationByVehicle', {
                            baseURL,
                            params: {
                                type,
                                vehicle: input,
                            },
                        })
                        .then((res) => {
                            //车辆定位点清除
                            this.clearMapMarkers(this.mapMarker.search)
                            this.mapMarker.search = []
                            //信息框清除
                            if (this.mapInfoWindow) {
                                this.mapInfoWindow.setMap(null)
                            }
                            //没有区域限制，没有zoom限制，点坐标
                            if(res.data!==null && res.data.length>0){
                                console.info("res.datares.data=",res.data)
                                let message = this.findMessageByType(res.data[0], type, false)
                                this.map.setCenter(message.position)
                                setTimeout(()=>{
                                    this.addMapMarker(this.mapMarker.search, res.data[0], type, false, true)
                                },0.5*1000)
                            }
                        })
                }
            },
            //定位点清除
            clearMapMarkers(list) {
                if (list.length !== 0) {
                    this.map.remove(list)
                    // _.each(list, (item) => {
                    //     if (item) {
                    //         item.setMap(null)
                    //     }
                    // })
                }
            },
            //车辆轨迹前置操作(页面刷新第一次查询轨迹)
            async getMapTrajectoryAllOne(forkList) {
                this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
                if (this.pathSimplifierIns) {
                    this.pathSimplifierIns.setData(null);
                }
                if (forkList === null || forkList.length === 0) return;
                // let stime = new Date(new Date() - 1000 * 60 * 15).Format('yyyy-MM-dd hh:mm:ss')
                let stime = new Date(new Date() - 1000 * 60 * 15).Format('yyyy-MM-dd hh:mm:ss')
                let etime = new Date().Format('yyyy-MM-dd hh:mm:ss')
                this.vehicleSelectInfoList = []
                _.each(forkList, (item) => {
                    if (item.vehicle_no !== '' && item.vehicle_type !== '') {
                        let map = {}
                        map.stime = stime
                        map.vehicle_type = item.vehicle_type
                        map.vehicle_no = item.vehicle_no
                        this.vehicleSelectInfoList.push(map)
                    }
                })
                this.forkTrajectoryList = []
                for (let i = 0; i < forkList.length; i++) {
                    let item = forkList[i]
                    if (item.vehicle_no !== '' && item.vehicle_type !== '') {
                     await this.getTrajectoryByVehicle(item.vehicle_type, item.vehicle_no, stime, etime)
                    }
                }
                this.doPathSimplifierIns(this.forkTrajectoryList)
                this.map.setFitView()
            },
            //车辆轨迹前置操作(定时刷新轨迹)
            async getMapTrajectoryAllTwo() {
                if (this.vehicleSelectInfoList !== null && this.vehicleSelectInfoList.length !== 0) {
                    let etime = new Date().Format('yyyy-MM-dd hh:mm:ss')
                    console.info("定时刷新=", this.vehicleSelectInfoList)
                    this.forkTrajectoryList = []
                    for (let i = 0; i <this.vehicleSelectInfoList.length; i++) {
                        let item = this.vehicleSelectInfoList[i]
                        await this.getTrajectoryByVehicle(item.vehicle_type, item.vehicle_no, item.stime, etime)
                    }
                    this.doPathSimplifierIns(this.forkTrajectoryList)
                }
            },
            //车辆轨迹查询
            getTrajectoryByVehicle(type, vehicle, stime, etime) {
                return axios.get('jkzx/getTrajectoryByVehicle', {
                    baseURL,
                    params: {
                        type,
                        vehicle,
                        stime,
                        etime
                    }
                }).then((res) => {
                    let map = {}
                    map.vehicle = vehicle
                    map.type = type
                    map.list = res.data
                    this.forkTrajectoryList.push(map)
                })
            },
            //车辆轨迹绘制
            doPathSimplifierIns(lists) {
                if (this.pathSimplifierIns) {
                    this.pathSimplifierIns.setData(null);
                }
                if (lists !== null) {
                    let message = this.findMessageByType(lists, this.query.radioCarType ,false)
                    console.info("message=", message)
                    if(message.d.length === 0){
                        // this.$message.error('无轨迹！')
                        return ;
                    }
                    let _this = this
                    //地图轨迹
                    AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function (PathSimplifier, $) {
                        if (!PathSimplifier.supportCanvas) {
                            alert('当前环境不支持 Canvas！');
                            return;
                        }
                        let colors = ["#3366cc", "#dc3912"]
                        _this.pathSimplifierIns = new PathSimplifier({
                            zIndex: 100,
                            map: _this.map, //所属的地图实例
                            getPath: function (pathData, pathIndex) {
                                return pathData.path;
                            },
                            getHoverTitle: function(pathData, pathIndex, pointIndex) {
                                //返回鼠标悬停时显示的信息
                                if (pointIndex >= 0) {
                                    //鼠标悬停在某个轨迹节点上
                                    // return pathData.name + '，点:' + pointIndex + '/' + pathData.path.length;
                                    // return pathData.text[pointIndex];
                                }
                                //鼠标悬停在节点之间的连线上
                                // return pathData.name + '，点数量' + pathData.path.length;
                            },
                            renderOptions: {
                                pathLineStyle: {
                                    dirArrowStyle: true
                                },
                                getPathStyle: function (pathItem, zoom) {
                                    let color = colors[pathItem.pathIndex],
                                        lineWidth = Math.round(2 * Math.pow(1.1, zoom - 3));
                                    return {
                                        pathLineStyle: {
                                            strokeStyle: color,
                                            lineWidth: lineWidth
                                        },
                                        pathLineSelectedStyle: {
                                            lineWidth: lineWidth + 2
                                        },
                                        pathNavigatorStyle: {
                                            fillStyle: color
                                        }
                                    }
                                }
                            }
                        });

                        function onload() {
                            _this.pathSimplifierIns.renderLater();
                        }

                        function onerror(e) {
                            alert('图片加载失败！');
                        }

                        function getNavg() {
                            //创建一个轨迹巡航器
                            for (let i = 0; i < message.d.length; i++) {
                                var navg = _this.pathSimplifierIns.createPathNavigator(i, {
                                    // loop: true,
                                    speed: 5000,
                                    pathNavigatorStyle: {
                                        width: 50,
                                        height: 50,
                                        content: PathSimplifier.Render.Canvas.getImageContent('../../resources/images/car/z1.png', onload, onerror),
                                        zIndex: 102,
                                        strokeStyle: null,
                                        fillStyle: null
                                    }
                                });
                                navg.start()
                            }
                        }

                        function initRoutesContainer() {
                            let navg = getNavg();
                        }

                        window.pathSimplifierIns = _this.pathSimplifierIns;
                        $('<div id="loadingTip">加载数据，请稍候...</div>').appendTo(document.body);
                        $('#loadingTip').remove();
                        let flyRoutes = [];
                        message.d.push.apply(message.d, flyRoutes);
                        _this.pathSimplifierIns.setData(message.d);
                        initRoutesContainer();

                        _this.pathSimplifierIns.on('pointMouseover', function(e, info) {
                            // let position =[]
                            // position.push(e.originalEvent.lnglat.getLng())
                            // position.push(e.originalEvent.lnglat.getLat())
                            if(info.pathData.text[info.pointIndex]!==null&&info.pathData.path[info.pointIndex]!==null)
                            _this.clickMapMarker2(info.pathData.text[info.pointIndex], info.pathData.path[info.pointIndex])
                        });
                    });
                }
            },

            //点击地图弹框
            clickMapMarker2(text, position) {
                if (this.mapInfoWindow) {
                    this.mapInfoWindow.setMap(null)
                }
                let info = []
                info.push(text)
                // this.map.setCenter(message.position)
                this.mapInfoWindow = new AMap.InfoWindow({
                    // autoMove: false,
                    offset: new AMap.Pixel(0, 0),
                    content: info.join(''),
                })
                this.mapInfoWindow.open(this.map, position)
            },

            //根据车辆类型返回车辆信息
            findMessageByType(allLists, type, limit) {
                //执法力量使用
                let maxlat = this.mapScreen.maxlat
                let maxlng = this.mapScreen.maxlng
                let minlat = this.mapScreen.minlat
                let minlng = this.mapScreen.minlng

                type = type.toString()
                let map = {}
                if (type === '0') {
                    //出租
                    let d = []
                    _.each(allLists, (datas, index) => {
                        let list = datas.list
                        if(list !== null && list.length > 0){
                            let a = {}
                            a.path = []
                            a.name = index
                            a.text = []
                            //里程
                            let lc = 0
                            _.each(list, (item, i) => {
                                if (i > 0) {
                                    let l1 = new AMap.LngLat(item.PX, item.PY);
                                    let l2 = new AMap.LngLat(list[i - 1].PX, list[i - 1].PY);
                                    lc += l1.distance(l2);
                                    item.LC = parseFloat((lc / 1000).toFixed(2));
                                } else {
                                    item.LC = 0;
                                }
                                let dcjkData = {};
                                dcjkData.vehicle = item.vehicle_num
                                dcjkData.date = formatYYYYMMDDHHMISS(item.SPEED_TIME);
                                dcjkData.speed = item.SPEED;
                                dcjkData.direction = fxzh(item.DIRECTION);
                                dcjkData.position = item.PX + "," + item.PY;
                                dcjkData.mileage = item.LC;
                                dcjkData.state = (item.STATE == "0" ? "空车" : (item.STATE == "1" ? "重车" : ""));
                                a.path.push([item.PX,item.PY]);
                                a.dcjkData=dcjkData
                                let text =
                                    '<table>' +
                                    '<tr><td>' +
                                    '<b style="color:#3399FF">巡游车-' + dcjkData.vehicle + '</b>' +
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[速度]：' + dcjkData.speed + 'KM/H' + '</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[方向]：' + dcjkData.direction + '</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[空重车]：' + dcjkData.state + '</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[经纬度]：' + dcjkData.position +'</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' + '<b>[GPS时间]：' + dcjkData.date +'</b>'+
                                    '</td></tr>' +
                                    '</table>'
                                a.text.push(text)
                            })
                            d.push(a)
                        }
                    })
                    map.d = d
                    // map.dcjkData =dcjkData
                } else if (type === '1') {
                    //网约
                    let d = []
                    _.each(allLists, (datas, index) => {
                        let list = datas.list
                        if(list !== null && list.length > 0){
                            let a = {}
                            a.path = []
                            a.name = index
                            a.text = []
                            //里程
                            let lc = 0
                            _.each(list, (item, i) => {
                                if (i > 0) {
                                    let l1 = new AMap.LngLat(item.Longitude, item.Latitude);
                                    let l2 = new AMap.LngLat(list[i - 1].Longitude, list[i - 1].Latitude);
                                    lc += l1.distance(l2);
                                    item.LC = parseFloat((lc / 1000).toFixed(2));
                                } else {
                                    item.LC = 0;
                                }
                                let dcjkData = {};
                                dcjkData.vehicle = item.VehicleNo
                                dcjkData.date = formatYYYYMMDDHHMISS(item.PositionTime);
                                dcjkData.speed = item.Speed;
                                dcjkData.direction = fxzh(item.Direction);
                                dcjkData.position = item.Longitude + "," + item.Latitude;
                                dcjkData.mileage = item.LC;
                                dcjkData.abb_name = item.abb_name;
                                dcjkData.name = item.name
                                dcjkData.bizstatus =this.formatterType(item.BizStatus)
                                a.path.push([item.Longitude,item.Latitude]);
                                a.dcjkData=dcjkData
                                let text =
                                    '<table>' +
                                    '<tr><td>' +
                                    '<b style="color:#3399FF">网约车-' + dcjkData.vehicle + '</b>' +
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[速度]：' + dcjkData.speed + 'KM/H' + '</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[APP 公司]：' + dcjkData.abb_name + '</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[所属公司]：' + dcjkData.name + '</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[营运状态]：' + dcjkData.bizstatus + '</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[经纬度]：' + dcjkData.position +'</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' + '<b>[GPS时间]：' + dcjkData.date +'</b>'+
                                    '</td></tr>' +
                                    '</table>'
                                a.text.push(text)
                            })
                            d.push(a)
                        }
                    })
                    map.d = d
                } else if (type === '2') {
                    //两客一危
                    let d = []
                    _.each(allLists, (datas, index) => {
                        let list = datas.list
                        if(list !== null && list.length > 0){
                            let a = {}
                            a.path = []
                            a.name = index
                            a.text = []
                            let lc = 0
                            _.each(list, (item, i) => {
                                if (i > 0) {
                                    let l1 = new AMap.LngLat(item.LONGI, item.LATI);
                                    let l2 = new AMap.LngLat(list[i - 1].LONGI, list[i - 1].LATI);
                                    lc += l1.distance(l2);
                                    item.LC = parseFloat((lc / 1000).toFixed(2));
                                } else {
                                    item.LC = 0;
                                }
                                let dcjkData = {};
                                dcjkData.vehicle = item.VEHI_NUM;
                                dcjkData.speed = item.SPEED;
                                dcjkData.date = formatYYYYMMDDHHMISS(item.STIME);
                                dcjkData.position = item.LONGI + "," + item.LATI;
                                dcjkData.mileage = item.LC;
                                dcjkData.brand_name = this.isNull(item.BRAND) +' '+this.isNull(item.MODEL)
                                dcjkData.owner_name = this.formatIndustry(item.INDUSTRY)
                                dcjkData.comp_name = this.isNull(item.COMPANY_NAME)
                                a.path.push([item.LONGI,item.LATI]);
                                let text =
                                    '<table>' +
                                    '<tr><td>' +
                                    '<b style="color:#3399FF">两客一危-' + dcjkData.vehicle + '</b>' +
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[速度]：' + dcjkData.speed + 'KM/H' + '</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[品牌型号]：' + dcjkData.brand_name +'</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[车辆类型]：' + dcjkData.owner_name +'</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[所属公司]：' + dcjkData.comp_name +'</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' +
                                    '<b>[经纬度]：' + dcjkData.position +'</b>'+
                                    '</td></tr>' +
                                    '<tr><td>' + '<b>[GPS时间]：' + dcjkData.date +'</b>'+
                                    '</td></tr>' +
                                    '</table>'
                                a.text.push(text)
                                a.dcjkData=dcjkData
                            })
                            d.push(a)
                        }
                    })
                    map.d = d
                } else if (type === '3') {
                    let item = allLists
                    //执法车
                    if (limit === true) {
                        if (
                            item.lati == null ||
                            '' == item.lati ||
                            item.longi == null ||
                            '' == item.longi
                        ) {
                            map.isMarker = false
                            return map
                        }
                        if (
                            item.lati <= maxlat &&
                            item.lati >= minlat &&
                            item.longi <= maxlng &&
                            item.longi >= minlng
                        ) {
                            map.isMarker = true
                        } else {
                            map.isMarker = false
                            return map
                        }
                    }
                    if (
                        new Date(item.speed_time) < new Date(new Date() - this.overTime[type])
                    ) {
                        map.isMarker = false
                        return map
                    }
                    //执法车车辆类型的子类型显示效果
                    map.isMarker = false
                    if (limit === true) {
                        _.each(this.zfcType, (item1) => {
                            _.each(this.vehicleType[3].sonType, (item2) => {
                                //前端是否点击子类型
                                if (item2.id === item1) {
                                    //该打点车辆类型
                                    if (item2.value === item.vehicle_type.toString()) {
                                        // map.icon = item2.pic
                                        // map.color = item2.color
                                        map.vehicleType = item2.name
                                        map.isMarker = true
                                        return false
                                    }
                                }
                            })
                        })
                    } else {
                        _.each(this.vehicleType[3].sonType, (item2) => {
                            //该打点车辆类型
                            if (item2.value === item.vehicle_type.toString()) {
                                // map.icon = item2.pic
                                // map.color = item2.color
                                map.vehicleType = item2.name
                                map.isMarker = true
                                return false
                            }
                        })
                    }
                    map.pic = this.vehicleType[type].pic
                    map.color = this.vehicleType[type].color
                    map.vehicle = this.isNull(item.vehicle_num).replace('浙A', '')
                    map.position = [item.longi, item.lati]
                    let text =
                        '<table>' +
                        '<tr><td>' +
                        '<b style="color:#3399FF">' +
                        map.vehicleType +
                        '-' +
                        item.vehicle_num +
                        '</b>' +
                        '</tr></td>' +
                        '<tr><td>' +
                        '<b>[单位名称]</b>：' +
                        item.structure_name +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<b>[GPS时间]</b>：' +
                        item.speed_time +
                        '</td></tr>' +
                        ''
                    map.text = text
                } else if (type === '4') {
                    let item = allLists
                    //执法终端
                    if(limit === true){
                        if (
                            item.lati == null ||
                            '' == item.lati ||
                            item.longi == null ||
                            '' == item.longi
                        ) {
                            map.isMarker = false
                            return map
                        }
                        if (
                            item.lati <= maxlat &&
                            item.lati >= minlat &&
                            item.longi <= maxlng &&
                            item.longi >= minlng
                        ) {
                            map.isMarker = true
                        } else {
                            map.isMarker = false
                            return map
                        }
                    }

                    if(new Date(item.speed_time) < new Date(new Date()-this.overTime[type])){
                        map.isMarker = false
                        return map
                    }
                    map.icon = '../../resources/images/jkzx/执法车.png'
                    map.position = [item.longi, item.lati]
                    let text =
                        '<table>' +
                        '<tr><td>' +
                        '<b style="color:#3399FF">执法终端-' +
                        item.vehicle_num +
                        '</b>' +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<b>[GPS时间]</b>：' +
                        item.speed_time +
                        '</td></tr>' +
                        ''
                    map.text = text
                } else if (type === '5') {
                    let item = allLists
                    //对讲机
                    if (limit === true) {
                        if (
                            item.lati == null ||
                            '' == item.lati ||
                            item.longi == null ||
                            '' == item.longi
                        ) {
                            map.isMarker = false
                            return map
                        }
                        if (
                            item.lati <= maxlat &&
                            item.lati >= minlat &&
                            item.longi <= maxlng &&
                            item.longi >= minlng
                        ) {
                            map.isMarker = true
                        } else {
                            map.isMarker = false
                            return map
                        }
                    }

                    if (
                        new Date(item.speed_time) < new Date(new Date() - this.overTime[type])
                    ) {
                        map.isMarker = false
                        return map
                    }
                    map.pic = this.vehicleType[type].pic
                    map.color = this.vehicleType[type].color
                    map.vehicle = this.isNull(item.name)
                    map.position = [item.longi, item.lati]
                    let text =
                        '<table>' +
                        '<tr><td>' +
                        '<b style="color:#3399FF">对讲机-' +
                        item.vehicle_num +
                        '</b>' +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<b>[姓名]</b>：' +
                        this.isNull(item.name) +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<tr><td>' +
                        '<b>[单位名称]</b>：' +
                        this.isNull(item.company) +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<tr><td>' +
                        '<b>[GPS时间]</b>：' +
                        item.speed_time +
                        '</td></tr>' +
                        ''
                    map.text = text
                } else if (type === '6') {
                    //执法大队
                    if (
                        item.latitude == null ||
                        '' == item.latitude ||
                        item.longitude == null ||
                        '' == item.longitude
                    ) {
                        map.isMarker = false
                        return map
                    }

                    map.icon = this.vehicleType[type].pic
                    map.position = [item.longitude, item.latitude]
                    let text =
                        '<table>' +
                        '<tr><td>' +
                        '<b style="color:#3399FF">' +
                        item.company +
                        '</b>' +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<b>[地址]</b>：' +
                        item.address +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<b>[电话]</b>：' +
                        item.telphone +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<b>[经度]</b>：' +
                        item.longitude +
                        '</td></tr>' +
                        '<tr><td>' +
                        '<b>[纬度]</b>：' +
                        item.latitude +
                        '</td></tr>' +
                        ''
                    map.text = text
                }
                return map
            },

            isNull(item) {
                if (item == null || item == '') {
                    return ''
                } else {
                    return item
                }
            },

            formatterType(type) {
                type = parseInt(type)
                if (type === 1) return '载客'
                else if (type === 2) return '接单'
                else if (type === 3) return '空驶'
                else if (type === 4) return '停运'
            },

            //两客一危行业类别
            formatIndustry(item){
                let message = ''
                if (item === '011') {
                    message = '班车客运'
                } else if (item === '012') {
                    message = '包车客运'
                }else if (item === '020') {
                    message = '普货'
                }else if (item === '030') {
                    message = '危货'
                }
                return message
            },

            //根据车辆类型找到经纬度相应信息
            findPxPy(type) {
                let map = {}
                if (type === '0') {
                    //出租
                    map.px = 'longi'
                    map.py = 'lati'
                } else if (type === '1') {
                    //网约
                    map.px = 'longitude'
                    map.py = 'latitude'
                } else if (type === '2') {
                    //两客一危
                    map.px = 'longi'
                    map.py = 'lati'
                } else if (type === '3') {
                    //执法车
                    map.px = 'longi'
                    map.py = 'lati'
                } else if (type === '4') {
                    //执法终端
                    map.px = 'longi'
                    map.py = 'lati'
                } else if (type === '5') {
                    //对讲机
                    map.px = 'longi'
                    map.py = 'lati'
                }
                return map
            },
            //关注车辆查询
            getMonitorVehicle(vehicle_type, vehicle_no) {
                axios.get('jkzx/getMonitorVehicle', {
                    baseURL,
                    params: {
                        vehicle_type,
                        vehicle_no
                    }
                }).then((res) => {
                    this.forkList = _.map(res.data, (item) => {
                        return {
                            vehicle_no: item.vehicle_no,
                            vehicle_type: item.vehicle_type,
                            type_name: this.vehicleType[parseInt(item.vehicle_type)].type_name,
                        }
                    })
                    this.getMapTrajectoryAllOne(this.forkList)
                })
            },
            //关注车辆删除
            deleteMonitorVehicle(vehicle_type, vehicle_no) {
                axios.get('jkzx/deleteMonitorVehicle', {
                    baseURL,
                    params: {
                        vehicle_type,
                        vehicle_no
                    }
                }).then((res) => {
                    if (res.data === 0) {
                        this.$message.error('删除失败');
                    } else if (res.data >= 1) {
                        this.$message.success('删除成功');
                        this.getMonitorVehicle(this.query.radioCarType)
                    }
                })
            },
            // 模糊搜索 调用接口
            handleInputSearch(queryString, cb) {
                if (queryString.length < 3){
                    cb([])
                    return;
                }
                const {radioCarType} = this.query
                const params = new URLSearchParams()
                params.append('type', radioCarType)
                params.append('is_screen', true)
                params.append('screen_value', queryString)
                axios.get('jkzx/getVehicle', {baseURL, params}).then((res) => {
                    let list = _.map(res.data, (item) => {
                        return {
                            id: item.vehicle,
                            value: item.vehicle,
                        }
                    })
                    cb(list)
                })
            },
            // 模糊搜索下拉框选择
            handleInputSelect(item) {
                console.info("将要关注车辆=", item.value)
                this.$confirm(`确认关注车辆 ${item.value} ？`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                    .then(() => {
                        axios.get('jkzx/addMonitorVehicle', {
                            baseURL,
                            params: {
                                vehicle_type: this.query.radioCarType,
                                vehicle_no: item.value
                            }
                        }).then((res) => {
                            if (res.data === -1) {
                                this.$message.error('该车辆已添加');
                            } else if (res.data === 0) {
                                this.$message.error('添加失败');
                            } else if (res.data >= 1) {
                                this.$message.success('添加成功');
                                this.getMonitorVehicle(this.query.radioCarType)
                            }
                        })
                    })
                    .catch(() => {
                    })
            },
            //-----------------------------------------------------执法力量-------------------------------------------------------
            //点击执法力量首先执行
            getMapCluster(zoom, veh_type) {
                console.info('监控类型=', veh_type)
                var _this = this
                //车辆定位点清除
                this.clearMapMarkers(this.mapMarker.point)
                this.mapMarker.point = []

                if (veh_type === '' || veh_type === null) return
                //直接显示定位的车辆类型，查询车辆定位
                _.each(veh_type.split(','), (item) => {
                    if (this.showLocationVehicleType.indexOf(item) > -1) {
                        this.getVehicleLocation(item)
                    }
                })
            },

            //根据车辆类型查询车辆定位信息
            getVehicleLocation(type) {
                let px = this.findPxPy(type).px
                let py = this.findPxPy(type).py
                let maxlat = this.mapScreen.maxlat
                let maxlng = this.mapScreen.maxlng
                let minlat = this.mapScreen.minlat
                let minlng = this.mapScreen.minlng
                axios
                    .get('jkzx/getVehicleByCoordinate', {
                        baseURL,
                        params: {
                            px,
                            py,
                            maxlat,
                            maxlng,
                            minlat,
                            minlng,
                            type,
                        },
                    })
                    .then((res) => {
                        if (res.data.length == 0) {
                        } else {
                            _.each(res.data, (item) => {
                                this.addMapMarker(this.mapMarker.point, item, type, true)
                            })
                        }
                    })
            },

            //车辆定位地图打点
            addMapMarker(mapMarkerlist, item, type, limit, isExist) {
                isExist = isExist === null ? false : isExist
                let message = this.findMessageByType(item, type, limit)
                if (message.isMarker === false) {
                    return
                }
                let marker
                //执法车打点
                if (type === '3') {
                    let _this = this
                    // AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
                        let simpleMarker = new SimpleMarker({
                            //设置节点属性
                            iconLabel: {
                                //普通文本
                                innerHTML: message.vehicle,
                                //设置样式
                                style: {
                                    color: message.color,
                                    fontSize: '100%',
                                    marginTop: '6px',
                                },
                            },
                            //自定义图标节点(img)的属性
                            iconStyle: {
                                src: message.pic[1],
                                // style: {
                                //     width: '110%',
                                //     height: '110%'
                                // }
                            },
                            //设置基点偏移
                            offset: new AMap.Pixel(-30, -50),
                            map: _this.map,
                            showPositionPoint: true,
                            position: message.position,
                            zIndex: 100,
                        })
                        simpleMarker.on('click', () =>
                            _this.clickMapMarker(item, type, limit)
                        )
                        //车辆定位点存放
                        mapMarkerlist.push(simpleMarker)
                    // })

                    marker = new AMap.Marker({
                        position: message.position,
                        icon: message.pic[0],
                        // icon: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
                        offset: new AMap.Pixel(-13, -15),
                    })
                    marker.on('click', () => this.clickMapMarker(item, type))
                    marker.setMap(this.map)
                }
                //对讲机
                else if (type === '5') {
                    let _this = this
                    // AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
                        let simpleMarker = new SimpleMarker({
                            //设置节点属性
                            iconLabel: {
                                //普通文本
                                innerHTML: message.vehicle,
                                //设置样式
                                style: {
                                    color: message.color,
                                    fontSize: '100%',
                                    marginTop: '6px',
                                },
                            },
                            //自定义图标节点(img)的属性
                            iconStyle: {
                                src: message.pic[1],
                                // style: {
                                //     width: '110%',
                                //     height: '110%'
                                // }
                            },
                            //设置基点偏移
                            offset: new AMap.Pixel(-28, -60),
                            map: _this.map,
                            showPositionPoint: true,
                            position: message.position,
                            zIndex: 100,
                        })
                        simpleMarker.on('click', () =>
                            _this.clickMapMarker(item, type, limit)
                        )
                        //车辆定位点存放
                        mapMarkerlist.push(simpleMarker)
                    // })

                    // // 创建 AMap.Icon 实例：
                    // let icon = new AMap.Icon({
                    //   size: new AMap.Size(30, 40), // 图标尺寸
                    //   image: message.pic[1], // Icon的图像
                    //   // imageOffset: new AMap.Pixel(0, -60),  // 图像相对展示区域的偏移量，适于雪碧图等
                    //   imageSize: new AMap.Size(30, 40), // 根据所设置的大小拉伸或压缩图片
                    // })

                    marker = new AMap.Marker({
                        position: message.position,
                        icon: message.pic[0],
                        // icon: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
                        offset: new AMap.Pixel(-15, -20),
                    })
                    marker.on('click', () => this.clickMapMarker(item, type))
                    marker.setMap(this.map)
                } else {
                    marker = new AMap.Marker({
                        position: message.position,
                        icon: message.icon,
                        // icon: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
                        offset: new AMap.Pixel(-15, -15),
                    })
                    marker.on('click', () => this.clickMapMarker(item, type))
                    marker.setMap(this.map)
                }
                if (limit === false && isExist === true) {
                    this.clickMapMarker(item, type)
                }
                //车辆定位点存放
                mapMarkerlist.push(marker)            },

            //点击地图弹框
            clickMapMarker(item, type, limit) {
                if (this.mapInfoWindow) {
                    this.mapInfoWindow.setMap(null)
                }
                let message = this.findMessageByType(item, type, limit)
                let info = []
                info.push(message.text)
                // this.map.setCenter(message.position)
                this.mapInfoWindow = new AMap.InfoWindow({
                    autoMove: false,
                    offset: new AMap.Pixel(0, 0),
                    content: info.join('</table>'),
                })
                this.mapInfoWindow.open(this.map, message.position)
            },

            //鼠标缩放地图事件
            handleMapMouseZoom() {
                //缩放
                this.map.on('zoomend', () => {
                    this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
                    console.info('地图zoom=', this.map.getZoom())
                })
                //移动
                this.map.on('moveend', () => {
                    let bounds = this.map.getBounds()
                    this.mapScreen.maxlat = parseFloat(bounds.northeast.lat)
                    this.mapScreen.maxlng = parseFloat(bounds.northeast.lng)
                    this.mapScreen.minlat = parseFloat(bounds.southwest.lat)
                    this.mapScreen.minlng = parseFloat(bounds.southwest.lng)
                    console.info('监控范围=', this.mapScreen)
                    this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
                })
            },


            // 悬浮框收缩事件
            handlePanelTitleClickFirst() {
                if (this.panelFlag.first) {
                    this.panelFlag.first = false
                    this.$refs['firstPanel'].style.width = '0'
                } else {
                    this.panelFlag.first = true
                    this.$refs['firstPanel'].style.width = '370px'
                }
            },
            handlePanelTitleClickSecond() {
                if (this.panelFlag.second) {
                    this.panelFlag.second = false
                    this.$refs['secondPanel'].style.width = '0'
                } else {
                    this.panelFlag.second = true
                    this.$refs['secondPanel'].style.width = '370px'
                }
            },
            initMap() {
                this.map = new AMap.Map('jkzxMap', {
                    level: 15,
                    center: [120.209561,30.245278],
                    resizeEnable: true,
                })
            },
            handleRadioChange(e) {
                console.info("选择的关注车辆类型=", e)
                this.query.cphm = ''
                this.getMonitorVehicle(e, "")
            },
            handleDeleteForkVehicle() {
                this.$confirm(`确认删除该类型的关注车辆吗 ？`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                    .then(() => {
                        this.deleteMonitorVehicle(this.query.radioCarType, '')
                    })
                    .catch(() => {
                    })
            },
            //监控类型
            handleCheckBoxChange(e) {
                this.checkType = e
                console.info('all_checkBox=' + e)
                let type = []
                _.each(e, (item1) => {
                    _.each(this.vehicleType, (item2) => {
                        if (item1 === item2.id) type.push(item2.type)
                    })
                })
                this.chooseVehicleType = type.toString()
                this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
            },
            handleCheckBox(type) {
                console.info(type)
            },
            //zfc监控类型
            handleCheckBoxzfcChange(e) {
                this.zfcType = e
                console.info('zfc_checkBox=' + this.zfcType)
                let index = this.checkType.indexOf('zfc')
                if (this.zfcType.length === 0) {
                    if (index > -1) {
                        this.checkType.splice(index, 1)
                    }
                } else {
                    if (index === -1) {
                        this.checkType.push('zfc')
                    }
                }
                this.handleCheckBoxChange(this.checkType)
            },
            zfcChange(e) {
                this.zfcType = []
                if (e) {
                    this.zfcType.push('ygzfc')
                    this.zfcType.push('glzfc')
                    this.zfcType.push('gwc')
                } else {
                    this.zfcType = []
                }
            },
        },
        watch: {
            zfcType: {
                handler(v, o) {
                    if (v.length == 3) {
                        this.zfcIndeterminate = false
                        this.zfcChecked = true
                        if (_.filter(this.carType, (item) => item == 'zfc').length == 0)
                            this.carType.push('zfc')
                    } else {
                        if (v.length > 0 && v.length < 3) this.zfcIndeterminate = true
                        else this.zfcIndeterminate = false
                        this.zfcChecked = false
                        this.carType = _.filter(this.carType, (item) => item != 'zfc')
                    }
                },
            },
        },
    })
})(Vue, _, jQuery)
