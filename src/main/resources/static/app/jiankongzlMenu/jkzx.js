;(function (Vue, _, $) {
  var vm = new Vue({
    el: '#root',
    data() {
      return {
        firstVideoFlag: true, // 第一次看视频
        map: null,
        mapInfoWindow: null,
        SimpleMarker: null,
        geocoder: null,
        mapAddress: '1',
        mapMarker: {
          //点聚合
          cluster: [],
          //大队
          Notmoving: [],
          //监控类型车辆打点
          point: [],
          //搜索车辆打点
          search: [],
          //监控详情车辆打点
          details: [],
          //监控详情车辆打点
          detailsOne: [],
          //场站摄像头打点
          stationCamera: [],
        },
        lastZoom: 1,
        selectedStationInfo: {},//选中场站信息
        limitStationZoom: 17,//视频打点的地图级别限制（>=有效）
        czVideo: '', //场站视频
        czVideoOptions: [],
        changeZoom: 1,
        mapLoading: false,
        mapScreen: {},
        checkType: [],
        carType: [],
        lklhType: [],
        lklhIndeterminate: false,
        lklhChecked: false,
        zfcType: [],
        zfcIndeterminate: false,
        zfcChecked: false,
        // 悬浮框展开
        panelFlag: {
          first: false,
          second: false,
        },
        //车辆类型
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
              // {
              //   id: 'gcc',
              //   clusterId: '4',
              //   type: '2.2',
              //   value: '普货',
              //   pic: '',
              // }, //普货
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
              '../../resources/images/jkzx/执法车3.png',
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
              '../../resources/images/jkzx/对讲机3.png',
            ],
            color: '#FFFFFF',
          }, //对讲机
          {
            id: '执法大队',
            type: '6',
            type_name: '执法大队',
            pic: '../../resources/images/jkzx/房子.png',
          }, //执法大队
          {
            id: '场站视频',
            type: '7',
            type_name: '场站视频',
            pic: '../../resources/images/jkzx/摄像头.png',
          }, //场站视频
        ],
        chooseVehicleType: '',
        //直接显示定位的车辆类型
        showLocationVehicleType: ['3', '4', '5'],
        online: {
          xyc: 0,
          wyc: 0,
          lklh: 0,
          zfc: 0,
          zfzd: 0,
          djj: 0,
        },
        // 地图上边搜索框
        mapQuery: {
          type: '',
          input: '',
        },
        mapToolType: 'jklx', //地图工具栏 类型
        mapToolRadio: '3', //地图工具栏 单选框
        table: {
          data: [{}],
        },
        mapLeft: {
          loading: false,
          activeLi: '',
          list: [],
          table: [],
          zx: 0,
          total: [],
          totalZx: 0,
          selectionChangeFlag: true, // 手动改变为true 定时器改变为false
          selectData: [],
          selectLength: 1,
        },
        mapDialog: {
          form: {},
          icon: '',
          type: '',
        },
        // 视频弹出框
        videoDialog: {
          loading: false,
          display: false,
          form: {
            arr: [],
            active: '通道1',
            index: 0,
          },
        },
        videoTimeOut: null,
        player: null,
        init: false,
        overTime: [
          10 * 60 * 1000, //巡游车
          10 * 60 * 1000, //网约
          10 * 60 * 1000, //两客一危
          3 * 60 * 1000, //执法车
          10 * 60 * 1000, //执法终端
          10 * 60 * 1000, //对讲机
        ],
      }
    },
    computed: {},
    mounted() {
      this.init = true
      this.initMap()
      AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
        this.SimpleMarker = SimpleMarker
      })
      //地图事件
      this.handleMapMouseZoom()
      //执法大队定位
      this.getBrigadeLocation()
      //场站
      this.getAllStation()
      //点聚合接口查询
      this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
      //十分钟内在线
      this.getVehicleOnline(this.overTime.toString())
      setInterval(() => {
        // 定时器改变了表格勾选框 设置为false
        this.mapLeft.selectionChangeFlag = false
        if (this.mapToolType == 'jklx') {
          this.getVehicleOnline(this.overTime.toString())
          this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
        } else if (this.mapToolType == 'jkxq') {
          this.mapLeft.loading = true
          this.queryTable()
        }
      }, 0.5 * 60 * 1000)
    },
    methods: {
      handleFullScreen() {
        var _parent = parent.$(window.parent.document)
        console.info(_parent.find('.ip-layout'))
        _parent.find('.ip-layout')[0].className += ' full-screen'
      },
      //场站选中
      handleStationSelectClick(station_name){
        // this.map.indoorMap.showIndoorMap('B000A856LJ',5);
        // this.map.indoorMap.on('click',function(result){
        //   console.log(result);
        // });
        this.getStationLocation(station_name)
      },
      //定位点清除
      clearMapMarkers(list) {
        if (list.length !== 0) {
          this.map.remove(list)
        }
      },
      /* 返回地理位置 */
      regeoCode(lnglat) {
        let _this = this
        _this.mapAddress = ''
        _this.geocoder.getAddress(lnglat, function(status, result) {
            if (status === 'complete'&&result.regeocode) {
              // document.getElementById('address').value = result.regeocode.formattedAddress
              _this.mapAddress = result.regeocode.formattedAddress
            }
        });
      },
      //执法大队定位
      getBrigadeLocation() {
        axios
          .get('jkzx/getBrigadeLocation', {
            baseURL,
          })
          .then((res) => {
            if (res.data !== null && res.data.length > 0) {
              _.each(res.data, (item) => {
                this.addMapMarker(this.mapMarker.Notmoving, item, 6, false)
              })
            }
          })
      },
      //场站
      getAllStation() {
        axios
          .get('jkzx/getAllStation', {
            baseURL,
          })
          .then((res) => {
              this.czVideoOptions = _.map(res.data, item =>{
                  return  { value: item.station_name, label: item.station_name }
              })
          })
      },
      //选中场站信息
      getStationLocation(station_name) {
        axios
          .get('jkzx/getStationLocation', {
            baseURL,
            params: {
              station_name,
            },
          })
          .then((res) => {
            this.selectedStationInfo = res.data[0]||{}
            if(Object.keys(this.selectedStationInfo).length === 0){
              this.$message.error('无场站信息！')
            }
            if(this.map.getZoom()<this.limitStationZoom){
              this.map.setZoom(this.limitStationZoom)
            }
            this.map.setCenter([this.selectedStationInfo.station_px, this.selectedStationInfo.station_py])
            setTimeout(() =>{
              this.map.indoorMap.showFloor(this.selectedStationInfo.default_floor,true)
              this.getStationCamera(this.selectedStationInfo.id, 2)
            },1000)

          })
      },
      //场站摄像头
      getStationCamera(station_id, floor) {
        axios
          .get('jkzx/getStationCamera', {
            baseURL,
            params: {
              station_id,
              floor,
            },
          })
          .then((res) => {
              this.clearMapMarkers(this.mapMarker.stationCamera)
              this.mapMarker.stationCamera = []
              if (res.data !== null && res.data.length > 0) {
                if(this.map.getZoom()<this.limitStationZoom){
                  this.map.setZoom(this.limitStationZoom)
                }
                  _.each(res.data, (item) => {
                      this.addMapMarker(this.mapMarker.stationCamera, item, 7, false)
                  })
              } else{
                this.$message.error('无摄像头！')
              }
          })
      },
      // 切换视频通道
      changeVideoWay(index) {
        this.firstVideoFlag = false
        const _self = this
        this.videoDialog.loading = true
        this.videoDialog.form.index = index
        try {
          this.player = videojs(
            'video',
            {
              controls: true,
            },
            function () {
              this.src(
                _self.videoDialog.form.arr[_self.videoDialog.form.index].src
              )
              this.on('error', function () {
                // _self.player.errorDisplay.close()
                console.log('error')
                _self.videoDialog.loading = false
              })
              if (!this.hasStarted_) {
              }
              console.info('hasStarted_', this.hasStarted_)
              // this.play()
              setTimeout(() => {
                this.load()
                this.play()
              })
              this.one('playing', function () {
                console.info('playing')
                _self.videoDialog.loading = false
              })
            }
          ) //my-player为页面video元素的id
          setTimeout(() => {
            _self.videoDialog.loading = false
          }, 4000)
        } catch (error) {
          console.info('error', error)
          _self.videoDialog.loading = false
        }
        console.info('end')
      },
      handleDialogClose() {
        if (this.player) this.player.dispose()
        this.videoDialog.display = false
        this.videoDialog.form.index = 0
      },
      // 视频弹出框点击
      handleVideoDialogClick() {
        const params = new URLSearchParams()
        params.append('vhic', this.mapDialog.form.vehicle_num)
        axios
          .post('claq/getStructure', params, {
            baseURL,
          })
          .then((res) => {
            let data = res.data.data[0].videovehicle[0] || {}
            this.videoDialog.form.arr = []
            if (parseInt(data.passageway) == 1) {
              this.videoDialog.form.arr.push({
                src: data.vedio_address,
                name: '通道1',
              })
            } else if (parseInt(data.passageway) > 1) {
              for (let i = 0; i < parseInt(data.passageway); i++) {
                this.videoDialog.form.arr.push({
                  src: data.vedio_address.split(';')[i],
                  // src: 'rtmp://58.200.131.2:1935/livetv/hunantv',
                  name: '通道' + (i + 1),
                })
              }
            }
            this.videoDialog.display = true
            if (this.firstVideoFlag) {
              this.changeVideoWay(0)
              this.handleDialogClose()
              this.videoDialog.display = true
            }
            setTimeout(() => {
              this.changeVideoWay(0)
            }, 600)
          })
      },
      // 摄像头视频弹出框点击
      handleVideoDialogClick2(src) {
        this.videoDialog.form.arr = []
        this.videoDialog.form.arr.push({
          src: src,
          name: '通道1',
        })
        this.videoDialog.display = true
        if (this.firstVideoFlag) {
          this.changeVideoWay(0)
          this.handleDialogClose()
          this.videoDialog.display = true
        }
        setTimeout(() => {
          this.changeVideoWay(0)
        }, 600)
      },
      //车辆轨迹跳转
      handleTrajectoryDialogClick(veh) {
        var _parent = parent.$(window.parent.document)
        if (_parent.find('.ip-tabBarItem[data-name="gjhf"]').length > 0) {
          _parent.find('.ip-tabBarItem[data-name="gjhf"]').trigger('click')
          _parent.find('#gjhf').get(0).contentWindow.location.reload(true)
        }
        _parent.find('.ip-menuItem [data-skip="gjhf"]').trigger('click')
        document.cookie = `vehtype=${this.mapDialog.type}`
        document.cookie = `vehno=${escape(veh)}`
      },
      // 地图上半部分类型切换
      handleMapToolChange(type) {
        this.mapToolType = type
        //信息框清除
        if (this.mapInfoWindow) {
          this.mapInfoWindow.setMap(null)
        }
        //jkxq:清除地图  jklx:重新查询
        this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
        if (type == 'jkxq') {
          this.getMonitorDetailsMenu()
          this.getMonitorDetails()
        } else if (type == 'jklx') {
          //在线车辆统计
          this.getVehicleOnline(this.overTime.toString())
        }
      },
      // 地图勾选事件
      handleLeftTableSelectionChange(val) {
        let data = JSON.parse(JSON.stringify(val))
        if (this.mapLeft.selectionChangeFlag) {
          this.mapLeft.selectData = val
          this.mapLeft.selectLength--
        } else {
          this.mapLeft.selectionChangeFlag = true
          this.mapLeft.selectLength = this.mapLeft.selectData.length
          let list = []
          for (let i = 0; i < this.mapLeft.selectData.length; i++) {
            list.push(
              _.filter(this.mapLeft.table, (item) => {
                return item.id == this.mapLeft.selectData[i].id
              })[0]
            )
          }
          setTimeout(() => {
            this.toggleSelection(list)
          }, 100)
        }
        if (this.mapLeft.selectLength == 0) {
          //车辆定位点清除
          this.clearMapMarkers(this.mapMarker.details)
          this.mapMarker.details = []
          //监控详情车辆定位点清除——one
          this.clearMapMarkers(this.mapMarker.detailsOne)
          this.mapMarker.detailsOne = []
          if (data !== null && data.length > 0) {
            _.each(data, (item) => {
              if (
                item.hasOwnProperty('SPEED_TIME') ||
                item.hasOwnProperty('speed_time')
              ) {
                item = this.upperJSONKey(item)
                item.speed_time = formatYYYYMMDDHHMISS(item.speed_time)
                this.addMapMarker(
                  this.mapMarker.details,
                  item,
                  this.mapToolRadio,
                  false
                )
              }
            })
          }
          this.mapLeft.selectLength = 1
        }
      },
      // 手动选中勾选框
      toggleSelection(rows) {
        if (rows) {
          rows.forEach((row) => {
            this.$refs.leftTable.toggleRowSelection(row)
          })
        } else {
          this.$refs.leftTable.clearSelection()
        }
      },
      //map转换
      upperJSONKey(jsonObj) {
        let reg = /^[0-9A-Z_]+$/
        for (var key in jsonObj) {
          if (reg.test(key)) {
            jsonObj[key.toLowerCase()] = jsonObj[key]
            // delete jsonObj[key]
          }
        }
        return jsonObj
      },
      // 查询全部数据
      getMonitorDetails() {
        const params = new URLSearchParams()
        params.append('type', this.mapToolRadio)
        axios
          .post('jkzx/getMonitorDetails', params, {
            baseURL,
          })
          .then((res) => {
            this.mapLeft.table = _.map(res.data, (item) => {
              item.isonline =
                new Date(item.SPEED_TIME) >=
                new Date(new Date() - this.overTime[this.mapToolRadio])
              return item
            })
            this.mapLeft.zx = _.filter(this.mapLeft.table, (item) => {
              return (
                new Date(item.SPEED_TIME) >=
                new Date(new Date() - this.overTime[this.mapToolRadio])
              )
            }).length
            setTimeout(() => {
              this.mapLeft.loading = false
            }, 500)
          })
      },
      getMonitorDetailsMenu() {
        const params = new URLSearchParams()
        params.append('type', this.mapToolRadio)
        axios
          .post('jkzx/getMonitorDetailsMenu', params, {
            baseURL,
          })
          .then((res) => {
            if (this.mapToolRadio == 3)
              this.mapLeft.list = [{ structure_name: '全部' }]
            else if (this.mapToolRadio == 5)
              this.mapLeft.list = [{ company: '全部' }]
            _.each(res.data, (item) => {
              this.mapLeft.list.push(item)
            })
            this.mapLeft.activeLi = '全部'
            this.queryTable()
          })
      },
      // 地图单选框切换
      handleRadioChange(e) {
        this.getMonitorDetailsMenu()
        this.getMonitorDetails()
      },
      // 地图左边
      handleMapLeftMenuClick(name) {
        // 点击左边菜单 大队 切换 需要把selectionChangeFlag 设置为true
        this.mapLeft.selectionChangeFlag = true
        this.mapLeft.loading = true
        this.mapLeft.activeLi = name
        this.queryTable()
      },
      // 查询表格
      queryTable() {
        if (this.mapLeft.activeLi == '全部') {
          this.getMonitorDetails()
        } else {
          const params = new URLSearchParams()
          params.append('type', this.mapToolRadio)
          params.append('name', this.mapLeft.activeLi)
          axios
            .post('jkzx/getMonitorDetailsDetails', params, {
              baseURL,
            })
            .then((res) => {
              this.mapLeft.table = _.map(res.data, (item) => {
                item.isonline =
                  new Date(item.SPEED_TIME) >=
                  new Date(new Date() - this.overTime[this.mapToolRadio])
                return item
              })
              this.mapLeft.zx = _.filter(this.mapLeft.table, (item) => {
                return (
                  new Date(item.SPEED_TIME) >=
                  new Date(new Date() - this.overTime[this.mapToolRadio])
                )
              }).length
              setTimeout(() => {
                this.mapLeft.loading = false
              }, 500)
            })
        }
      },
      // 地图左边设备定位
      handleMapLeftLocationClick(row) {
        // infoWindow信息窗体清除
        if (this.mapInfoWindow) {
          this.mapInfoWindow.setMap(null)
        }
        //监控详情车辆定位点清除——one
        this.clearMapMarkers(this.mapMarker.detailsOne)
        this.mapMarker.detailsOne = []
        let vehicle = ''
        if (this.mapToolRadio === '3') {
          vehicle = row.vehi_no
        } else if (this.mapToolRadio === '5') {
          vehicle = row.phone
        }

        item = this.upperJSONKey(row)
        //没有区域限制，没有zoom限制，点坐标
        if (
          item.hasOwnProperty('SPEED_TIME') ||
          item.hasOwnProperty('speed_time')
        ) {
          item.speed_time = formatYYYYMMDDHHMISS(item.speed_time)
          let message = this.findMessageByType(item, this.mapToolRadio, false)
          this.map.setCenter(message.position)
          setTimeout(() => {
            this.addMapMarker(
              this.mapMarker.detailsOne,
              item,
              this.mapToolRadio,
              false
            )
          }, 0.5 * 1000)
        } else {
          this.$message.error('无定位！')
        }
        // if (vehicle !== null && vehicle !== '') {
        //   axios
        //     .get('jkzx/getLocationByVehicle', {
        //       baseURL,
        //       params: {
        //         type: this.mapToolRadio,
        //         vehicle,
        //       },
        //     })
        //     .then((res) => {
        //       //没有区域限制，没有zoom限制，点坐标
        //       if (res.data !== null && res.data.length > 0) {
        //         let message = this.findMessageByType(
        //           res.data[0],
        //           this.mapToolRadio,
        //           false
        //         )
        //         this.map.setCenter(message.position)
        //         setTimeout(() => {
        //           //地图与左侧栏在线情况显示一致
        //           res.data[0].isonline = row.isonline
        //           this.addMapMarker(
        //             this.mapMarker.detailsOne,
        //             res.data[0],
        //             this.mapToolRadio,
        //             false
        //           )
        //         }, 0.5 * 1000)
        //       } else {
        //         this.$message.error('无定位！')
        //       }
        //     })
        // }
      },

      // 地图上半部分搜索框
      handleMapInputClick() {
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
            if (res.data !== null && res.data.length > 0) {
              let message = this.findMessageByType(res.data[0], type, false)
              this.map.setCenter(message.position)
              setTimeout(() => {
                this.addMapMarker(
                  this.mapMarker.search,
                  res.data[0],
                  type,
                  false,
                  true
                )
              }, 0.5 * 1000)
            } else {
              this.$message.error('无定位！')
            }
          })
      },

      // 地图上半部分搜索框清除事件
      handleMapInputClearClick() {
        //信息框清除
        if (this.mapInfoWindow) {
          this.mapInfoWindow.setMap(null)
        }
        this.clearMapMarkers(this.mapMarker.search)
        this.mapMarker.search = []
      },
      //在线车辆统计
      getVehicleOnline(bytime) {
        axios
          .get('jkzx/getVehicleOnline', {
            baseURL,
            params: {
              bytime,
            },
          })
          .then((res) => {
            if (res.data.length == 0) {
              // this.$message.error('无数据！')
            } else {
              this.online = res.data
            }
          })
      },
      //点聚合接口查询
      getMapCluster(zoom, veh_type) {
        // this.map.clearMap()
        //点聚合清除
        this.clearMapMarkers(this.mapMarker.cluster)
        this.mapMarker.cluster = []
        //车辆定位点清除
        this.clearMapMarkers(this.mapMarker.point)
        this.mapMarker.point = []
        //监控详情
        if (this.mapToolType == 'jkxq') {
          return
        } else if (this.mapToolType == 'jklx') {
          //监控详情车辆定位点清除
          this.clearMapMarkers(this.mapMarker.details)
          this.mapMarker.details = []
          //监控详情车辆定位点清除——one
          this.clearMapMarkers(this.mapMarker.detailsOne)
          this.mapMarker.detailsOne = []
        }
        if (veh_type === '' || veh_type === null) return
        //直接显示定位的车辆类型，查询车辆定位
        _.each(veh_type.split(','), (item) => {
          if (this.showLocationVehicleType.indexOf(item) > -1) {
            this.getVehicleLocation(item)
          }
        })
        //地图层级大于一定值，直接查询车辆定位
        if (zoom >= 16) {
          _.each(veh_type.split(','), (item) => {
            this.getVehicleLocation(item)
          })
          return
        } else {
          // this.mapLoading = true
          let lev = this.zoomToLev(zoom)
          //两客一危大类型去除
          let arr = veh_type.split(',')
          let index_arr = arr.indexOf('2')
          if (index_arr > -1) {
            arr.splice(index_arr, 1)
          }
          //执法车等大类型去除
          _.each(this.showLocationVehicleType, (item_type) => {
            index_arr = arr.indexOf(item_type)
            if (index_arr > -1) {
              arr.splice(index_arr, 1)
            }
          })

          if (veh_type.split(',').indexOf('2') > -1) {
            _.each(this.lklhType, (item1) => {
              _.each(this.vehicleType[2].sonType, (item2) => {
                //前端是否点击子类型
                if (item2.id === item1) {
                  // 该打点车辆类型
                  arr.push(item2.clusterId)
                }
              })
            })
          }
          if (arr.length === 0) {
            return
          }
          axios
            .get('jkzx/getMapCluster', {
              baseURL,
              params: {
                lev,
                veh_type: arr.toString(),
              },
            })
            .then((res) => {
              if (res.data.length == 0) {
                // this.$message.error('无数据！')
              } else {
                _.each(res.data, (item) => {
                  if (item.veh_count > 0) this.getMarkerClusterer(item)
                })
              }
              // this.mapLoading = false
            })
        }
      },
      //地图zoom层级转化为lev（数据库表识别）
      zoomToLev(zoom) {
        if (zoom < 10) {
          return 0
        } else if (zoom >= 10 && zoom <= 11) {
          return 1
        } else if (zoom >= 12 && zoom <= 13) {
          return 2
        } else if (zoom >= 14 && zoom <= 15) {
          return 3
        } else if (zoom >= 16 && zoom <= 17) {
          return 4
        }
      },
      //点聚合打点
      getMarkerClusterer(item) {
        let _this = this
        // AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
        let simpleMarker = new SimpleMarker({
          //设置节点属性
          iconLabel: {
            //普通文本
            innerHTML: item.veh_count,
            //设置样式
            style: {
              color: '#fff',
              fontSize: '100%',
              // marginTop: '10px',
              lineHeight: _this.setFontStyleByCount(item.veh_count),
              width: _this.setFontStyleByCount(item.veh_count),
            },
          },
          //自定义图标地址
          iconStyle: {
            src: _this.findPicByCount(item.veh_count),
            style: {
              width: _this.setFontStyleByCount(item.veh_count),
              height: _this.setFontStyleByCount(item.veh_count),
            },
          },
          //设置基点偏移
          // offset: new AMap.Pixel(-19, -60),
          map: _this.map,
          showPositionPoint: true,
          position: [item.center_longi, item.center_lati],
          zIndex: 100,
        })
        //点聚合存放
        _this.mapMarker.cluster.push(simpleMarker)
        // simpleMarker.on('click', () => this.clickMarkerClusterer(item))
        // })
      },
      //点聚合打点-->根据点的车辆数据返回相应的字体样式
      setFontStyleByCount(count) {
        if (count >= 10000) {
          return '80px'
        } else if (count >= 1000 && count < 10000) {
          return '70px'
        } else {
          return '50px'
        }
      },
      //点聚合打点-->根据点的车辆数据返回相应的图标
      findPicByCount(count) {
        if (count >= 10000) {
          return 'https://a.amap.com/jsapi_demos/static/images/red.png'
        } else if (count >= 1000 && count < 10000) {
          return 'https://a.amap.com/jsapi_demos/static/images/blue.png'
        } else {
          return 'https://a.amap.com/jsapi_demos/static/images/green.png'
        }
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
              // this.$message.error('无数据！')
            } else {
              _.each(res.data, (item, index) => {
                this.addMapMarker(this.mapMarker.point, item, type, true)
              })
            }
          })
      },

      //车辆定位地图打点
      // item:打点车, type：车辆类型, limit：是否有限制, isExist:是否存在信息框
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
              src: message.pic[message.index2],
              // style: {
              //     width: '110%',
              //     height: '110%'
              // }
            },
            //设置基点偏移
            offset: new AMap.Pixel(-30, -50),
            // map: _this.map,
            showPositionPoint: true,
            position: message.position,
            zIndex: 100,
          })
          simpleMarker.on('click', () =>
            _this.clickMapMarker(item, type, limit)
          )
          //车辆定位点存放
          mapMarkerlist.push(simpleMarker)
          simpleMarker.setMap(_this.map)
          // })

          marker = new AMap.Marker({
            position: message.position,
            icon: message.pic[message.index1],
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
              src: message.pic[message.index2],
              // style: {
              //     width: '110%',
              //     height: '110%'
              // }
            },
            //设置基点偏移
            offset: new AMap.Pixel(-28, -60),
            // map: _this.map,
            showPositionPoint: true,
            position: message.position,
            zIndex: 100,
          })
          simpleMarker.on('click', () =>
            _this.clickMapMarker(item, type, limit)
          )
          //车辆定位点存放
          mapMarkerlist.push(simpleMarker)
          simpleMarker.setMap(_this.map)
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
            icon: message.pic[message.index1],
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
        mapMarkerlist.push(marker)
      },

      //点击地图弹框
      clickMapMarker(item, type, limit) {
        if (this.mapInfoWindow) {
          this.mapInfoWindow.setMap(null)
        }
        let message = this.findMessageByType(item, type, limit)
        this.mapDialog.type = type
        if (type != 6) {
          //获取地址位置
          this.regeoCode(message.position)
          this.mapDialog.form = item
          this.mapInfoWindow = new AMap.InfoWindow({
            autoMove: false,
            offset: new AMap.Pixel(0, 0),
            content: this.$refs['map-dialog'],
          })
          setTimeout(() =>{
            this.mapInfoWindow.open(this.map, message.position)
          },150)
        } else {
          let info = []
          info.push(message.text)
          // this.map.setCenter(message.position)
          this.mapInfoWindow = new AMap.InfoWindow({
            autoMove: false,
            offset: new AMap.Pixel(0, 0),
            content: info.join('</table>'),
          })
          this.mapInfoWindow.open(this.map, message.position)
        }
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

      //根据车辆类型找到地图打点的相应信息
      findMessageByType(item, type, limit) {
        type = type.toString()
        let map = {}
        let maxlat = this.mapScreen.maxlat
        let maxlng = this.mapScreen.maxlng
        let minlat = this.mapScreen.minlat
        let minlng = this.mapScreen.minlng
        if (type === '0') {
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
            new Date(item.stime) < new Date(new Date() - this.overTime[type])
          ) {
            map.isMarker = false
            return map
          }
          //出租
          map.icon = '../../resources/images/jkzx/巡游车.png'
          map.position = [item.longi, item.lati]

          let text =
            '<table>' +
            '<tr><td>' +
            '<b style="color:#3399FF">巡游车-' +
            item.vehi_no +
            '</b>' +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[所属公司]</b>：' +
            item.comp_name +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[速度]</b>：' +
            item.speed +
            'KM/H' +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[SIM卡号]</b>：' +
            item.sim_num +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[终端号]</b>：' +
            item.mdt_no +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[车辆所属人]</b>：' +
            this.isNull(item.own_name) +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[联系电话]</b>：' +
            this.isNull(item.own_tel) +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[经度]</b>：' +
            item.longi +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[纬度]</b>：' +
            item.lati +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[方向]</b>：' +
            fxzh(item.angle) +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[GPS时间]</b>：' +
            item.stime +
            '</td></tr>' +
            ''
          map.text = text
        } else if (type === '1') {
          //网约
          if (limit === true) {
            if (
              item.latitude == null ||
              '' == item.latitude ||
              item.longitude == null ||
              '' == item.longitude
            ) {
              map.isMarker = false
              return map
            }
            if (
              item.latitude <= maxlat &&
              item.latitude >= minlat &&
              item.longitude <= maxlng &&
              item.longitude >= minlng
            ) {
              map.isMarker = true
            } else {
              map.isMarker = false
              return map
            }
          }
          if (
            new Date(item.positiontime) <
            new Date(new Date() - this.overTime[type])
          ) {
            map.isMarker = false
            return map
          }
          map.icon = '../../resources/images/jkzx/网约车.png'
          map.position = [item.longitude, item.latitude]
          let text =
            '<table>' +
            '<tr><td>' +
            '<b style="color:#3399FF">网约车-' +
            item.vehicleno +
            '</b>' +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[速度]</b>：' +
            item.speed +
            'KM/H' +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[APP 公司]</b>：' +
            item.abb_name +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[所属公司]</b>：' +
            item.name +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[营运状态]</b>：' +
            this.formatterType(item.bizstatus) +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[GPS时间]</b>：' +
            item.positiontime +
            '</td></tr>' +
            ''
          map.text = text
        } else if (type === '2') {
          //两客一危
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
            new Date(item.stime) < new Date(new Date() - this.overTime[type])
          ) {
            map.isMarker = false
            return map
          }
          //两客一危车辆类型的子类型显示效果
          map.isMarker = false
          if (limit === true) {
            _.each(this.lklhType, (item1) => {
              _.each(this.vehicleType[2].sonType, (item2) => {
                //前端是否点击子类型
                if (item2.id === item1) {
                  // 该打点车辆类型
                  if (item2.value === item.owner_name.toString()) {
                    // map.icon=item2.pic
                    map.isMarker = true
                    return false
                  }
                }
              })
            })
          } else {
            _.each(this.vehicleType[2].sonType, (item2) => {
              // 该打点车辆类型
              if (item2.value === item.owner_name.toString()) {
                // map.icon=item2.pic
                map.isMarker = true
                return false
              }
            })
          }

          map.icon = '../../resources/images/jkzx/两客两货.png'
          map.position = [item.longi, item.lati]
          let text =
            '<table>' +
            '<tr><td>' +
            '<b style="color:#3399FF">两客一危-' +
            item.vehi_num +
            '</b>' +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[品牌型号]</b>：' +
            item.brand_name +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[车辆类型]</b>：' +
            this.isNull(item.owner_name) +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[所属公司]</b>：' +
            item.comp_name +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[速度]</b>：' +
            item.speed +
            'KM/H' +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[方向]</b>：' +
            fxzh(item.direction) +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[经度]</b>：' +
            item.longi +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[纬度]</b>：' +
            item.lati +
            '</td></tr>' +
            '<tr><td>' +
            '<b>[GPS时间]</b>：' +
            item.stime +
            '</td></tr>' +
            ''
          map.text = text
        } else if (type === '3') {
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
            if (
              new Date(item.speed_time) <
              new Date(new Date() - this.overTime[type])
            ) {
              map.isMarker = false
              return map
            }
            map.index1 = 0
            map.index2 = 1
          } else {
            map.index1 = 2
            map.index2 = 1
            if (
              new Date(item.speed_time) <
              new Date(new Date() - this.overTime[type])
            ) {
              map.index1 = 2
            } else {
              map.index1 = 0
            }
            if (item.hasOwnProperty('isonline') && item.isonline === true) {
              map.index1 = 0
            }
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
            '<tr><td class="aaa">' +
            '视频' +
            '</td></tr>' +
            ''
          map.text = text
        } else if (type === '4') {
          //执法终端
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
            new Date(item.speed_time) <
            new Date(new Date() - this.overTime[type])
          ) {
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
            if (
              new Date(item.speed_time) <
              new Date(new Date() - this.overTime[type])
            ) {
              map.isMarker = false
              return map
            }
            map.index1 = 0
            map.index2 = 1
          } else {
            //图片上线下线下标
            map.index1 = 2
            map.index2 = 1
            if (
              new Date(item.speed_time) <
              new Date(new Date() - this.overTime[type])
            ) {
              map.index1 = 2
            } else {
              map.index1 = 0
            }
            if (item.hasOwnProperty('isonline') && item.isonline === true) {
              map.index1 = 0
            }
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
        } else if (type === '7') {
          //场站视频
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
            item.position_name +
            '</b>' +
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
      //鼠标缩放地图事件
      handleMapMouseZoom() {
        //室内地图事件
        let floor = 1
        this.map.indoorMap.on('floor_complete',(result) =>{
          if(Object.keys(this.selectedStationInfo).length > 0){
            floor = result.building.floor
            this.getStationCamera(this.selectedStationInfo.id, result.building.floor)
          }
        });
        //缩放
        this.map.on('zoomend', () => {
          //点聚合
          this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
          //场站视频
          if(this.map.getZoom()<this.limitStationZoom){
            this.clearMapMarkers(this.mapMarker.stationCamera)
            this.mapMarker.stationCamera = []
          }else{
            if(this.map.getZoom() >= this.limitStationZoom&&this.lastZoom >= this.limitStationZoom){
            }else if(this.map.getZoom() >= this.limitStationZoom && this.lastZoom < this.limitStationZoom){
              //当前zoom>=limitStationZoom,且上次zoom<limitStationZoom,场站视频重新打点
              if(Object.keys(this.selectedStationInfo).length > 0){
                this.getStationCamera(this.selectedStationInfo.id, floor)
              }
            }
          }
          this.lastZoom = this.map.getZoom()
          // let currentZoom = this.map.getZoom()
          // let residualValue =this.map.getZoom()%2
          // if(residualValue!=0&&currentZoom<this.changeZoom){
          //     this.map.setZoom(this.map.getZoom()-residualValue)
          // }else if(residualValue!=0&&currentZoom>this.changeZoom){
          //     this.map.setZoom(this.map.getZoom()+residualValue)
          // }
          // this.changeZoom = currentZoom
        })

        //第一次地图不动
        let bounds = this.map.getBounds()
        this.mapScreen.maxlat = parseFloat(bounds.northeast.lat)
        this.mapScreen.maxlng = parseFloat(bounds.northeast.lng)
        this.mapScreen.minlat = parseFloat(bounds.southwest.lat)
        this.mapScreen.minlng = parseFloat(bounds.southwest.lng)
        //移动
        this.map.on('moveend', () => {
          bounds = this.map.getBounds()
          this.mapScreen.maxlat = parseFloat(bounds.northeast.lat)
          this.mapScreen.maxlng = parseFloat(bounds.northeast.lng)
          this.mapScreen.minlat = parseFloat(bounds.southwest.lat)
          this.mapScreen.minlng = parseFloat(bounds.southwest.lng)
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
          zooms: [9, 18],
          zoom: 12,
          center: [120.209561, 30.245278],
          resizeEnable: true,
          expandZoomRange: false,
        })
        this.geocoder = new AMap.Geocoder({
          city: "010", //城市设为北京，默认：“全国”
          radius: 1000 //范围，默认：500
        });
        this.changeZoom = this.map.getZoom()
      },
      //监控类型
      handleCheckBoxChange(e) {
        this.checkType = e
        let type = []
        _.each(e, (item1) => {
          _.each(this.vehicleType, (item2) => {
            if (item1 === item2.id) type.push(item2.type)
          })
        })
        this.chooseVehicleType = type.toString()
        this.getMapCluster(this.map.getZoom(), this.chooseVehicleType)
      },
      //lklh监控类型
      handleCheckBoxlklhChange(e) {
        this.lklhType = e
        let index = this.checkType.indexOf('lklh')
        if (this.lklhType.length === 0) {
          if (index > -1) {
            this.checkType.splice(index, 1)
          }
        } else {
          if (index === -1) {
            this.checkType.push('lklh')
          }
        }
        this.handleCheckBoxChange(this.checkType)
      },
      //zfc监控类型
      handleCheckBoxzfcChange(e) {
        this.zfcType = e
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

      handleCheckBox(type) {},
      // 两客一危多选框改变事件
      lklhChange(e) {
        this.lklhType = []
        if (e) {
          this.lklhType.push('pthy')
          this.lklhType.push('wxpc')
          // this.lklhType.push('gcc')
          this.lklhType.push('lykc')
        } else {
          this.lklhType = []
        }
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
      lklhType: {
        handler(v, o) {
          if (v.length == 3) {
            this.lklhIndeterminate = false
            this.lklhChecked = true
            if (_.filter(this.carType, (item) => item == 'lklh').length == 0)
              this.carType.push('lklh')
          } else {
            if (v.length > 0 && v.length < 3) this.lklhIndeterminate = true
            else this.lklhIndeterminate = false
            this.lklhChecked = false
            this.carType = _.filter(this.carType, (item) => item != 'lklh')
          }
        },
      },
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
  $(function () {
    videojs.options.flash.swf = 'video-js.swf'

    function flashChecker() {
      var hasFlash = 0 //是否安装了flash
      var flashVersion = 0 //flash版本
      var isIE = /*@cc_on!@*/ 0 //是否IE浏览器

      if (isIE) {
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
        if (swf) {
          hasFlash = 1
          VSwf = swf.GetVariable('$version')
          flashVersion = parseInt(VSwf.split(' ')[1].split(',')[0])
        }
      } else {
        if (navigator.plugins && navigator.plugins.length > 0) {
          var swf = navigator.plugins['Shockwave Flash']
          if (swf) {
            hasFlash = 1
            var words = swf.description.split(' ')
            for (var i = 0; i < words.length; ++i) {
              if (isNaN(parseInt(words[i]))) continue
              flashVersion = parseInt(words[i])
            }
          }
        }
      }
      return { f: hasFlash, v: flashVersion }
    }

    var fls = flashChecker()
    var s = ''
    // if (fls.f) {
    //   document.getElementsByClassName('openFlash')[0].style.display = 'none'
    //   document.getElementsByClassName('openFlashTips')[0].style.display = 'none'
    //   //        document.write("您安装了flash,当前flash版本为: " + fls.v + ".x");
    // } else {
    //   document.getElementsByClassName('openFlash')[0].style.display = 'block'
    //   document.getElementsByClassName('openFlashTips')[0].style.display =
    //     'block'
    //   //        document.write("您没有安装flash");
    // }
  })
})(Vue, _, jQuery)
