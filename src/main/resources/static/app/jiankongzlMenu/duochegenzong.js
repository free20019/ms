;(function (Vue, _, $) {
  var vm = new Vue({
    el: '#root',
    data() {
      return {
        radioCarType: 0,
        vhicValue: '',
        list: {
          data: [],
        },
        map: {
          /*地图*/
          map1: null,
          map2: null,
          map3: null,
          map4: null,
          /*执法车mar*/
          marZFC1: [],
          marZFC2: [],
          marZFC3: [],
          marZFC4: [],
          /*对讲机mar*/
          marDJJ1: [],
          marDJJ2: [],
          marDJJ3: [],
          marDJJ4: [],
          /*当前地图*/
          checkedMap: 0,
          /*历史轨迹mar*/
          historyMar1: [],
          historyMar2: [],
          historyMar3: [],
          historyMar4: [],
          /*每个地图车牌*/
          vhic1: '',
          vhic2: '',
          vhic3: '',
          vhic4: '',
          /*执法车定时任务*/
          setIntervalZFC1: null,
          setIntervalZFC2: null,
          setIntervalZFC3: null,
          setIntervalZFC4: null,
          /*对讲机定时任务*/
          setIntervalDJJ1: null,
          setIntervalDJJ2: null,
          setIntervalDJJ3: null,
          setIntervalDJJ4: null,
          /*历史轨迹定时任务*/
          setIntervalHistory1: null,
          setIntervalHistory2: null,
          setIntervalHistory3: null,
          setIntervalHistory4: null,
          /*定时刷新时间间隔*/
          timeInterval: 1000*60,
          /*在线时间*/
          timeOnline: 1000*60*10
        },
        carType: [],
        zfcType: [],
        zfcIndeterminate: false,
        /*每个地图上方执法车、对讲机复选框*/
        checkedType: {
          carType1: [],
          carType2: [],
          carType3: [],
          carType4: [],
        },
        /*加载*/
        loading: {
          vhicLoading: false,
          mapLoading1: false,
          mapLoading2: false,
          mapLoading3: false,
          mapLoading4: false,
        }
      }
    },
    mounted() {
      this.map.map1 = this.initMap(this.map.map1,'map1')
      this.map.map2 = this.initMap(this.map.map2,'map2')
      this.map.map3 = this.initMap(this.map.map3,'map3')
      this.map.map4 = this.initMap(this.map.map4,'map4')
    },
    methods: {
      /*地图显示*/
      initMap(map,mapId) {
        return map = new AMap.Map(mapId, {
          level: 15,
          resizeEnable: true,
        })
      },
      /*每个地图复选框选中事件*/
      boxChange(type) {
        if(this.map['marZFC'+type].length>0) {
          this.map['map'+type].remove(this.map['marZFC'+type])
          this.map['marZFC'+type] = []
        }
        if(this.map['marDJJ'+type].length>0) {
          this.map['map'+type].remove(this.map['marDJJ'+type])
          this.map['marDJJ'+type] = []
        }
        if(this.map['setIntervalZFC'+type]) clearInterval(this.map['setIntervalZFC'+type])
        if(this.map['setIntervalDJJ'+type]) clearInterval(this.map['setIntervalDJJ'+type])
        let checkValue = this.checkedType['carType'+type]
        for(var i=0; i<checkValue.length; i++) {
          if(checkValue[i] == 'zfc'){
            this.getMonitor(3,type)
            this.map['setIntervalZFC'+type] = setInterval(()=>{
              if(this.map['marZFC'+type].length>0) {
                this.map['map'+type].remove(this.map['marZFC'+type])
                this.map['marZFC'+type] = []
              }
              this.getMonitor(3,type)
            }, this.map.timeInterval)
          }
          else if(checkValue[i] == 'djj'){
            this.getMonitor(5,type)
            this.map['setIntervalDJJ'+type] = setInterval(()=>{
              if(this.map['marDJJ'+type].length>0) {
                this.map['map'+type].remove(this.map['marDJJ'+type])
                this.map['marDJJ'+type] = []
              }
              this.getMonitor(5,type)
            }, this.map.timeInterval)
          }
        }
      },
      /*查询某一种设备类型实时定位*/
      getMonitor(type,mapType) {
        console.log('jinlail',type,mapType)
        axios.get('jkzx/getVehicleLocation', {
          baseURL,
          params: {
            type:type
          }
        }).then((res) => {
          var now = new Date().getTime();
          for(var i=0; i<res.data.length; i++) {
            if(now - new Date(res.data[i].speed_time).getTime() <= this.map.timeOnline) {
              this.addMar(res.data[i],mapType,type,0);
              this.addMar(res.data[i],mapType,type,1);
            }
          }
          // this.map['map'+mapType].setFitView()
        })
      },
      isNull(item) {
        if (item == null || item == '') {
          return ''
        } else {
          return item
        }
      },
      /*地图打点*/
      addMar(item,mapType,type,lx) {
        let map = this.map['map'+mapType]
        let icon = '',txt = '';
        let offset = new AMap.Pixel(-8, -8);
        if(type == 5){
          if(lx ==0) {
            icon = '../../resources/images/jkzx/对讲机1.png';
            txt =  '<table>' +
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
                '</td></tr>';
          }else {
            icon = '../../resources/images/jkzx/对讲机2.png';
            offset = new AMap.Pixel(-20, -45);
          }
        } else if(type == 3||type == 2||type == 1){//车辆轨迹
          if(lx ==0) {
            icon = '../../resources/images/jkzx/执法车1.png';
            txt = '<table>' +
                '<tr><td>' +
                '<b style="color:#3399FF">执法车' +
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
                '</td></tr>';
          }else {
            icon = '../../resources/images/jkzx/执法车2.png';
            offset = new AMap.Pixel(-32, -47);
          }

        } else if(type == 10){//车辆轨迹
          icon = '../../resources/images/jkzx/history.png';
          txt = '<p>'+item.vehicle_num+'</p><p>定位时间：'+item.SPEED_TIME+'</p>';
        } else if(type == 11){//车辆最后一个图标
          icon = '../../resources/images/car.png';
          txt = '<p>'+item.vehicle_num+'</p><p>定位时间：'+item.SPEED_TIME+'</p>';
        }
        var viaMarker = new AMap.Marker({
          position: new AMap.LngLat(item.longi,item.lati),
          map: map,
          icon: icon,
          angle: item.DIRECTION,
          offset: offset
        });
        if(lx ==1) {
          if(type ==5) {
            viaMarker.setLabel({
              offset: new AMap.Pixel(5, 3),  //设置文本标注偏移量
              content: "<div>"+(item.name?item.name:'无')+"</div>", //设置文本标注内容
              direction: 'right' //设置文本标注方位
            });
          }else {
            viaMarker.setLabel({
              offset: new AMap.Pixel(10, 3),  //设置文本标注偏移量
              content: "<div>"+item.vehicle_num+"</div>", //设置文本标注内容
              direction: 'right' //设置文本标注方位
            });
          }

        }
        viaMarker.on('mouseover', function() {
          var info = [];
          info.push(txt);
          infoWindow = new AMap.InfoWindow({
            content: info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
          });
          infoWindow.open(map, [item.longi, item.lati]);
        });
        viaMarker.on('mouseout', function() {
          infoWindow.close();
        });
        if(type == 10||type ==11) this.map['historyMar'+mapType].push(viaMarker)
        else if(type ==3) this.map['marZFC'+mapType].push(viaMarker)
        else if(type ==5) this.map['marDJJ'+mapType].push(viaMarker)
      },
      /*根据车号查询车辆列表*/
      getVhicList() {
        if(this.vhicValue.length<3) return this.list.data;
        this.loading.vhicLoading = true
        axios.get('jkzx/getVehicle', {
          baseURL,
          params: {
            type:this.radioCarType,
            is_screen: true,
            screen_value: this.vhicValue
          }
        }).then((res) => {
          let vhicType = '';
          if(this.radioCarType ==0) vhicType = '巡游车'
          else if(this.radioCarType ==1) vhicType = '网约车'
          else if(this.radioCarType ==2) vhicType = '两客一危'
          for(var i=0; i<res.data.length; i++) {
            res.data[i].vhicType = vhicType
            res.data[i].radioCarType = this.radioCarType
          }
          this.list.data = res.data;
          this.loading.vhicLoading = false
        })
      },
      /*清楚车辆列表*/
      clearVhicList() {
        this.list.data = []
        this.vhicValue = '';
      },
      /*事件格式化*/
      dateFormat(time) {
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
        var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
        var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
        return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
      },
      /*查询车辆轨迹*/
      getHistoryUtil(vhic,type) {
        var checkedMap = ++this.map.checkedMap;
        if(this.map['setIntervalHistory'+checkedMap]) clearInterval(this.map['setIntervalHistory'+checkedMap])
        this.getHistory(vhic,type,checkedMap);
        this.map['setIntervalHistory'+checkedMap] = setInterval(()=>{
          this.getHistory(vhic,type,checkedMap);
        },this.map.timeInterval)
      },
      getHistory(vhic,type,checked) {
        console.log('histyro',vhic,checked)
        this.loading['mapLoading'+checked] = true;
        axios.get('jkzx/getTrajectoryByVehicle', {
          baseURL,
          params: {
            type,
            vehicle: vhic,
            stime: this.dateFormat(new Date(new Date().getTime() - 1000*60*10)),
            etime: this.dateFormat(new Date())
          }
        }).then((res) => {
          this.loading['mapLoading'+checked] = false;
          let checkedMap = checked ==0?4:checked;
          if(this.map['historyMar'+checkedMap])
            this.map['map'+checkedMap].remove(this.map['historyMar'+checkedMap])
          this.map['historyMar'+checkedMap] = []
          if(res.data.length == 0) {
            this.$message({
              message: '该车无轨迹！',
              type: 'warning'
            });
            this.map.checkedMap--;
            if(this.map['setIntervalHistory'+checkedMap]) clearInterval(this.map['setIntervalHistory'+checkedMap])
            return;
          }
          let lineArr = []
          for(var i=0; i<res.data.length; i++) {
            if(type == 0){
              res.data[i].longi = res.data[i].PX
              res.data[i].lati = res.data[i].PY
            }else if(type == 1){
              res.data[i].PX = res.data[i].Longitude
              res.data[i].PY = res.data[i].Latitude
              res.data[i].longi = res.data[i].Longitude
              res.data[i].lati = res.data[i].Latitude

              res.data[i].SPEED_TIME =  formatYYYYMMDDHHMISS(res.data[i].PositionTime)
              res.data[i].vehicle_num = res.data[i].VehicleNo
            }else if(type == 2){
              res.data[i].PX = res.data[i].LONGI
              res.data[i].PY = res.data[i].LATI
              res.data[i].longi = res.data[i].LONGI
              res.data[i].lati = res.data[i].LATI

              res.data[i].SPEED_TIME =  formatYYYYMMDDHHMISS(res.data[i].STIME)
              res.data[i].vehicle_num = res.data[i].VEHI_NUM
            }
            lineArr.push(new AMap.LngLat(res.data[i].PX, res.data[i].PY))
            if(i == res.data.length-1) this.addMar(res.data[i], checkedMap,11,0)
            else this.addMar(res.data[i], checkedMap,10,0)
          }
          this.map['vhic'+checkedMap] = vhic
          this.addLine(lineArr,checkedMap)
          this.map['map'+checkedMap].setFitView(this.map['historyMar'+checkedMap])
        })
      },
      /*绘制轨迹*/
      addLine(lineArr,type) {
        //绘制轨迹
        var polyline = new AMap.Polyline({
          map: this.map['map'+type],
          path: lineArr,
          showDir: true,
          strokeColor: '#28F', //线颜色
          // strokeOpacity: 1,     //线透明度
          strokeWeight: 3 //线宽
          // strokeStyle: "solid"  //线样式
        })
        this.map['historyMar'+type].push(polyline)
        // this.map['map'+type].setFitView()
      },
      /*清楚地图*/
      clearMap(type) {
        this.map.checkedMap = type
        this.map['map'+type].remove(this.map['marZFC'+type])
        this.map['map'+type].remove(this.map['marDJJ'+type])
        this.map['map'+type].remove(this.map['historyMar'+type])
        this.map['vhic'+type]= ''
        this.map['mar'+type] = []
        this.map['historyMar'+type] = []
        this.checkedType['carType'+type] = []
        if(this.map['setIntervalZFC'+type]) clearInterval(this.map['setIntervalZFC'+type])
        if(this.map['setIntervalDJJ'+type]) clearInterval(this.map['setIntervalDJJ'+type])
        if(this.map['setIntervalHistory'+type]) clearInterval(this.map['setIntervalHistory'+type])
      }
    },
    watch: {
    },
    destroyed: {

    }
  })
})(Vue, _, jQuery)
