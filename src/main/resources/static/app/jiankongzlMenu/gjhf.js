;(function (Vue, _, $) {
  var vm = new Vue({
    el: '#root',
    data() {
      return {
        radio: '',
        query: {
          stime: '',
          etime: '',
          vehicle: '',
          type: '',
        },
        map: null,
        mapLoading: false,
        pathSimplifierIns: null,
        mapInfoWindow: null,
        tableData: [],
      }
    },
    mounted() {
      this.initMap()
      this.query.stime = new Date(new Date() - 1000 * 60 * 60 * 2).Format(
        'yyyy-MM-dd hh:mm:ss'
      )
      this.query.etime = new Date().Format('yyyy-MM-dd hh:mm:ss')
      //   let url = decodeURI(window.location.search)
      //   if (url.indexOf('?') > -1) {
      //     this.getURL(url)
      //   }
      this.query.vehicle = this.getCookie('vehno')
      this.query.type = parseInt(this.getCookie('vehtype'))
      if (this.query.vehicle && this.query.type >=0) this.handleSelectClick()
      this.clearCookie('vehtype')
      this.clearCookie('vehno')
    },
    methods: {
      getURL(url) {
        let arr = url.split('?')[1].split('&')
        _.each(arr, (item) => {
          if (item.split('=')[0] == 'type') {
            this.query.type = parseInt(item.split('=')[1])
          } else if (item.split('=')[0] == 'veh') {
            this.query.vehicle = item.split('=')[1]
          }
        })
        this.handleSelectClick()
      },
      // 读取cookie
      getCookie(c_name) {
        console.info('123', document.cookie)
        if (document.cookie.length > 0) {
          c_start = document.cookie.indexOf(c_name + '=')
          if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(';', c_start)
            if (c_end == -1) c_end = document.cookie.length
            console.info('11', document.cookie.substring(c_start, c_end))
            return unescape(document.cookie.substring(c_start, c_end))
          }
        }
        return ''
      },
      // 清除cookie
      clearCookie(name) {
        document.cookie = name + '=' + ''
      },
      getTrajectoryByVehicle() {
        const { stime, etime, vehicle, type } = this.query
        if (type === '') {
          return this.$message.error('请选择车辆类型！')
        }
        if (vehicle === '') {
          return this.$message.error('请输入车牌！')
        }
        if (stime === null || stime === '' || etime === null || etime === '') {
          return this.$message.error('时间不能为空！')
        }
        if (
          new Date(stime).Format('yyyyMM') !== new Date(etime).Format('yyyyMM')
        ) {
          return this.$message.error('无法跨月！')
        }
        this.mapLoading = true
        axios
          .get('jkzx/getTrajectoryByVehicle', {
            baseURL,
            params: {
              type,
              vehicle,
              stime: new Date(stime).Format('yyyy-MM-dd hh:mm:ss'),
              etime: new Date(etime).Format('yyyy-MM-dd hh:mm:ss'),
            },
          })
          .then((res) => {
            this.tableData = []
            this.doPathSimplifierIns(res.data)
            this.mapLoading = false
          })
      },

      //车辆轨迹绘制
      doPathSimplifierIns(list) {
        if (this.pathSimplifierIns) {
          this.pathSimplifierIns.setData(null)
        }
        if (this.mapInfoWindow) {
          this.mapInfoWindow.setMap(null)
        }
        if (list !== null && list.length > 0) {
          let message = this.findMessageByType(list, this.query.type, false)
          console.info('message=', message)
          let _this = this
          //地图轨迹
          AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function (
            PathSimplifier,
            $
          ) {
            if (!PathSimplifier.supportCanvas) {
              alert('当前环境不支持 Canvas！')
              return
            }
            let colors = ['#3366cc', '#dc3912']
            _this.pathSimplifierIns = new PathSimplifier({
              zIndex: 100,
              map: _this.map, //所属的地图实例
              getPath: function (pathData, pathIndex) {
                return pathData.path
              },
              getHoverTitle: function (pathData, pathIndex, pointIndex) {
                //返回鼠标悬停时显示的信息
                if (pointIndex >= 0) {
                  //鼠标悬停在某个轨迹节点上
                  // return pathData.name + '，点:' + pointIndex + '/' + pathData.path.length;
                  // return pathData.text[pointIndex]
                }
                //鼠标悬停在节点之间的连线上
                // return pathData.name + '，点数量' + pathData.path.length;
              },
              renderOptions: {
                renderAllPointsIfNumberBelow: 100, //绘制路线节点，如不需要可设置为-1
                pathLineStyle: {
                  dirArrowStyle: true,
                },
                getPathStyle: function (pathItem, zoom) {
                  let color = colors[pathItem.pathIndex],
                    lineWidth = Math.round(2 * Math.pow(1.1, zoom - 3))
                  return {
                    pathLineStyle: {
                      strokeStyle: color,
                      lineWidth: lineWidth,
                    },
                    pathLineSelectedStyle: {
                      lineWidth: lineWidth + 2,
                    },
                    pathNavigatorStyle: {
                      fillStyle: color,
                    },
                  }
                },
              },
            })

            function onload() {
              _this.pathSimplifierIns.renderLater()
            }

            function onerror(e) {
              alert('图片加载失败！')
            }

            function getNavg() {
              //创建一个轨迹巡航器
              let navg = _this.pathSimplifierIns.createPathNavigator(0, {
                // loop: true,
                speed: 5000,
                pathNavigatorStyle: {
                  width: message.width,
                  height: message.height,
                  autoRotate: message.autoRotate,
                  content: PathSimplifier.Render.Canvas.getImageContent(
                    message.pic,
                    onload,
                    onerror
                  ),
                  zIndex: 102,
                  strokeStyle: null,
                  fillStyle: null,
                },
              })
              $('#id-bofang').on('click', function () {
                navg.start(0)
              })

              $('#id-shuaxin').on('click', function () {
                navg.resume()
              })

              $('#id-zanting').on('click', function () {
                navg.pause()
              })
            }

            function initRoutesContainer() {
              let navg = getNavg()
            }

            window.pathSimplifierIns = _this.pathSimplifierIns
            $('<div id="loadingTip">加载数据，请稍候...</div>').appendTo(
              document.body
            )
            $('#loadingTip').remove()
            let flyRoutes = []
            message.d.push.apply(message.d, flyRoutes)
            _this.pathSimplifierIns.setData(message.d)
            initRoutesContainer()

            _this.pathSimplifierIns.on('pointMouseover', function(e, info) {
              // let position =[]
              // position.push(e.originalEvent.lnglat.getLng())
              // position.push(e.originalEvent.lnglat.getLat())
              if(info.pathData.text[info.pointIndex]!==null&&info.pathData.path[info.pointIndex]!==null)
              _this.clickMapMarker2(info.pathData.text[info.pointIndex], info.pathData.path[info.pointIndex])
            });

          })
        } else {
          this.$message.error('无轨迹！')
        }
        this.map.setFitView()
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
      findMessageByType(list, type, limit) {
        type = type.toString()
        let map = {}
        if (type === '0') {
          //出租
          let d = []
          if (list !== null && list.length > 0) {
            let a = {}
            a.path = []
            a.name = '1'
            a.text = []
            //里程
            let lc = 0
            _.each(list, (item, i) => {
              if (i > 0) {
                let l1 = new AMap.LngLat(item.PX, item.PY)
                let l2 = new AMap.LngLat(list[i - 1].PX, list[i - 1].PY)
                lc += l1.distance(l2)
                item.LC = parseFloat((lc / 1000).toFixed(2))
              } else {
                item.LC = 0
              }
              let dcjkData = {}
              dcjkData.vehicle = item.vehicle_num
              dcjkData.date = formatYYYYMMDDHHMISS(item.SPEED_TIME)
              dcjkData.speed = item.SPEED
              dcjkData.direction = fxzh(item.DIRECTION)
              dcjkData.position = item.PX + ',' + item.PY
              dcjkData.mileage = item.LC
              dcjkData.state =
                item.STATE == '0' ? '空车' : item.STATE == '1' ? '重车' : ''
              //table栏显示数据
              this.tableData.push(dcjkData)
              a.path.push([item.PX, item.PY])
              //a.dcjkData = dcjkData

              let text =
                '<table>' +
                '<tr><td>' +
                '<b style="color:#3399FF">巡游车-' +
                dcjkData.vehicle +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[速度]：' +
                dcjkData.speed +
                'KM/H' +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[方向]：' +
                dcjkData.direction +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[空重车]：' +
                dcjkData.state +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[经纬度]：' +
                dcjkData.position +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[GPS时间]：' +
                dcjkData.date +
                '</b>' +
                '</td></tr>' +
                '</table>'
              a.text.push(text)
            })
            d.push(a)
          }
          map.autoRotate = true
          map.width = 50
          map.height = 50
          map.pic = '../../resources/images/car/z1.png'
          map.d = d
        } else if (type === '1') {
          //网约
          let d = []
          if (list !== null && list.length > 0) {
            let a = {}
            a.path = []
            a.name = '1'
            a.text = []
            //里程
            let lc = 0
            _.each(list, (item, i) => {
              if (i > 0) {
                let l1 = new AMap.LngLat(item.Longitude, item.Latitude)
                let l2 = new AMap.LngLat(
                  list[i - 1].Longitude,
                  list[i - 1].Latitude
                )
                lc += l1.distance(l2)
                item.LC = parseFloat((lc / 1000).toFixed(2))
              } else {
                item.LC = 0
              }
              let dcjkData = {}
              dcjkData.vehicle = item.VehicleNo
              dcjkData.date = formatYYYYMMDDHHMISS(item.PositionTime)
              dcjkData.speed = item.Speed
              dcjkData.direction = fxzh(item.Direction)
              dcjkData.position = item.Longitude + ',' + item.Latitude
              dcjkData.mileage = item.LC
              dcjkData.abb_name = item.abb_name
              dcjkData.name = item.name
              dcjkData.bizstatus = this.formatterType(item.BizStatus)
              a.path.push([item.Longitude, item.Latitude])
              //table栏显示数据
              this.tableData.push(dcjkData)
              //a.dcjkData = dcjkData
              let text =
                '<table>' +
                '<tr><td>' +
                '<b style="color:#3399FF">网约车-' +
                dcjkData.vehicle +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[速度]：' +
                dcjkData.speed +
                'KM/H' +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[APP 公司]：' +
                dcjkData.abb_name +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[所属公司]：' +
                dcjkData.name +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[营运状态]：' +
                dcjkData.bizstatus +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[经纬度]：' +
                dcjkData.position +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[GPS时间]：' +
                dcjkData.date +
                '</b>' +
                '</td></tr>' +
                '</table>'
              a.text.push(text)
            })
            d.push(a)
          }
          map.autoRotate = true
          map.width = 50
          map.height = 50
          map.pic = '../../resources/images/car/z1.png'
          map.d = d
        } else if (type === '2') {
          //两客一危
          let d = []
          if (list !== null && list.length > 0) {
            let a = {}
            a.path = []
            a.name = '1'
            a.text = []
            let lc = 0
            _.each(list, (item, i) => {
              if (i > 0) {
                let l1 = new AMap.LngLat(item.LONGI, item.LATI)
                let l2 = new AMap.LngLat(list[i - 1].LONGI, list[i - 1].LATI)
                lc += l1.distance(l2)
                item.LC = parseFloat((lc / 1000).toFixed(2))
              } else {
                item.LC = 0
              }
              let dcjkData = {}
              dcjkData.vehicle = item.VEHI_NUM
              dcjkData.speed = item.SPEED
              dcjkData.date = formatYYYYMMDDHHMISS(item.STIME)
              dcjkData.position = item.LONGI + ',' + item.LATI
              dcjkData.mileage = item.LC
              dcjkData.brand_name =
                this.isNull(item.BRAND) + ' ' + this.isNull(item.MODEL)
              dcjkData.owner_name = this.formatIndustry(item.INDUSTRY)
              dcjkData.comp_name = this.isNull(item.COMPANY_NAME)
              //table栏显示数据
              this.tableData.push(dcjkData)
              a.path.push([item.LONGI, item.LATI])
              let text =
                '<table>' +
                '<tr><td>' +
                '<b style="color:#3399FF">两客一危-' +
                dcjkData.vehicle +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[速度]：' +
                dcjkData.speed +
                'KM/H' +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[品牌型号]：' +
                dcjkData.brand_name +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[车辆类型]：' +
                dcjkData.owner_name +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[所属公司]：' +
                dcjkData.comp_name +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[经纬度]：' +
                dcjkData.position +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[GPS时间]：' +
                dcjkData.date +
                '</b>' +
                '</td></tr>' +
                '</table>'
              a.text.push(text)
              //a.dcjkData = dcjkData
            })
            d.push(a)
          }
          map.autoRotate = true
          map.width = 50
          map.height = 50
          map.pic = '../../resources/images/car/z1.png'
          map.d = d
        } else if (type === '3') {
          //执法车
          let d = []
          if (list !== null && list.length > 0) {
            let a = {}
            a.path = []
            a.name = '1'
            a.text = []
            let lc = 0
            _.each(list, (item, i) => {
              if (i > 0) {
                let l1 = new AMap.LngLat(item.LONGI, item.LATI)
                let l2 = new AMap.LngLat(list[i - 1].LONGI, list[i - 1].LATI)
                lc += l1.distance(l2)
                item.LC = parseFloat((lc / 1000).toFixed(2))
              } else {
                item.LC = 0
              }
              let dcjkData = {}
              dcjkData.vehicle = item.vehi_no
              // dcjkData.speed = item.SPEED;
              dcjkData.date = formatYYYYMMDDHHMISS(item.SPEED_TIME)
              dcjkData.position = item.LONGI + ',' + item.LATI
              dcjkData.mileage = item.LC
              dcjkData.structure_name = item.structure_name
              //table栏显示数据
              this.tableData.push(dcjkData)
              a.path.push([item.LONGI, item.LATI])
              let text =
                '<table>' +
                '<tr><td>' +
                '<b style="color:#3399FF">执法车-' +
                dcjkData.vehicle +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[经纬度]：' +
                dcjkData.position +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[单位名称]</b>：' +
                dcjkData.structure_name +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[GPS时间]：' +
                dcjkData.date +
                '</b>' +
                '</td></tr>' +
                '</table>'
              a.text.push(text)
              //a.dcjkData = dcjkData
            })
            d.push(a)
          }
          map.autoRotate = false
          map.width = 40
          map.height = 40
          map.pic = '../../resources/images/jkzx/执法车1.png'
          map.d = d
        } else if (type === '4') {
          //执法终端
          let d = []
          if (list !== null && list.length > 0) {
            let a = {}
            a.path = []
            a.name = '1'
            a.text = []
            let lc = 0
            _.each(list, (item, i) => {
              if (i > 0) {
                let l1 = new AMap.LngLat(item.LONGI, item.LATI)
                let l2 = new AMap.LngLat(list[i - 1].LONGI, list[i - 1].LATI)
                lc += l1.distance(l2)
                item.LC = parseFloat((lc / 1000).toFixed(2))
              } else {
                item.LC = 0
              }
              let dcjkData = {}
              dcjkData.vehicle = item.VEHICLE_NUM
              // dcjkData.speed = item.SPEED;
              dcjkData.date = formatYYYYMMDDHHMISS(item.SPEED_TIME)
              dcjkData.position = item.LONGI + ',' + item.LATI
              dcjkData.mileage = item.LC
              //table栏显示数据
              this.tableData.push(dcjkData)
              a.path.push([item.LONGI, item.LATI])
              let text =
                '<table>' +
                '<tr><td>' +
                '<b style="color:#3399FF">执法终端-' +
                dcjkData.vehicle +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[经纬度]：' +
                dcjkData.position +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[GPS时间]：' +
                dcjkData.date +
                '</b>' +
                '</td></tr>' +
                '</table>'
              a.text.push(text)
              //a.dcjkData = dcjkData
            })
            d.push(a)
          }
          map.d = d
        } else if (type === '5') {
          //对讲机
          let d = []
          if (list !== null && list.length > 0) {
            let a = {}
            a.path = []
            a.name = '1'
            a.text = []
            let lc = 0
            _.each(list, (item, i) => {
              if (i > 0) {
                let l1 = new AMap.LngLat(item.LONGI, item.LATI)
                let l2 = new AMap.LngLat(list[i - 1].LONGI, list[i - 1].LATI)
                lc += l1.distance(l2)
                item.LC = parseFloat((lc / 1000).toFixed(2))
              } else {
                item.LC = 0
              }
              let dcjkData = {}
              dcjkData.vehicle = item.VEHICLE_NUM
              dcjkData.name = this.isNull(item.name)
              // dcjkData.speed = item.SPEED;
              dcjkData.date = formatYYYYMMDDHHMISS(item.SPEED_TIME)
              dcjkData.position = item.LONGI + ',' + item.LATI
              dcjkData.mileage = item.LC
              dcjkData.company = this.isNull(item.company)
              //table栏显示数据
              this.tableData.push(dcjkData)
              a.path.push([item.LONGI, item.LATI])
              let text =
                '<table>' +
                '<tr><td>' +
                '<b style="color:#3399FF">对讲机-' +
                dcjkData.vehicle +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[姓名]：' +
                dcjkData.name +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[单位名称]</b>：' +
                dcjkData.company +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[经纬度]：' +
                dcjkData.position +
                '</b>' +
                '</td></tr>' +
                '<tr><td>' +
                '<b>[GPS时间]：' +
                dcjkData.date +
                '</b>' +
                '</td></tr>' +
                '</table>'
              a.text.push(text)
              //a.dcjkData = dcjkData
            })
            d.push(a)
          }
          map.autoRotate = false
          map.width = 35
          map.height = 40
          map.d = d
          map.pic = '../../resources/images/jkzx/对讲机1.png'
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
      formatIndustry(item) {
        let message = ''
        if (item === '011') {
          message = '班车客运'
        } else if (item === '012') {
          message = '包车客运'
        } else if (item === '020') {
          message = '普货'
        } else if (item === '030') {
          message = '危货'
        }
        return message
      },

      //初始化地图
      initMap() {
        this.map = new AMap.Map('map', {
          zoom: 15,
          resizeEnable: true,
        })
      },
      //点击查询
      handleSelectClick() {
        this.getTrajectoryByVehicle()
      },
      //车辆类型选择
      handleRadioChange(e) {
        console.info('选择的车辆类型=', e)
        this.query.vehicle = ''
        this.query.type = e
      },
      // 模糊搜索 调用接口
      handleInputSearch(queryString, cb) {
        if (this.query.type === '' || this.query.type === null || isNaN(this.query.type) ) {
          return this.$message.error('请选择车辆类型！')
        }
        if (queryString.length < 3) {
          cb([])
          return
        }
        const { type } = this.query
        const params = new URLSearchParams()
        params.append('type', type)
        params.append('is_screen', true)
        params.append('screen_value', queryString)
        axios.get('jkzx/getVehicle', { baseURL, params }).then((res) => {
          let list = _.map(res.data, (item) => {
            return {
              id: item.vehicle,
              value: item.vehicle,
            }
          })
          cb(list)
        })
      },
    },
  })
})(Vue, _, jQuery)
