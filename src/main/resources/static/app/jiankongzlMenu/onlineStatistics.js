;(function (Vue, _, $) {
  var vm = new Vue({
    el: '#root',
    data() {
      return {
        query: {
          dw: '',
          dwOptions: [],
          cphm: '',
          cphmOptions: [],
          stime: new Date(new Date().getTime() - 24 * 60 * 60 * 1000),
          etime: new Date(new Date().getTime() - 24 * 60 * 60 * 1000),
          defaultProps: {
            children: 'children',
            label: 'label',
          },
          treeData: [],
        },
        table: {
          loading: false,
          data: [],
          pageSize: 20,
          currentPage: 1,
          total: 0,
          type: 0, // 0普通 1详情
          selectRow: {},
        },
        total: {
          time1: '',
          time2: '',
        },
      }
    },
    computed: {
      filterTableList() {
        return this.filterTablePage(this.table)
      },
    },
    mounted() {
      const elements = this.$refs['queryBar'].$el
      this.getTreeData()
      this.getZfcOnlineTimeStatistics()
      // this.getAllZfc()
      this.$nextTick(() => {
        this.$refs[
          'queryPanel'
        ].style.height = `calc(100% - ${elements.offsetHeight}px)`
        window.addEventListener('resize', () => {
          this.$refs[
            'queryPanel'
          ].style.height = `calc(100% - ${elements.offsetHeight}px)`
        })
      })
    },
    methods: {
      // 表格详情点击
      handleTableDetailClick(row) {
        this.table.type = 1
        this.table.selectRow = row
        this.getZfcOnlineTime()
      },
      getZfcOnlineTime() {
        this.table.loading = true
        const { stime, etime } = this.query
        axios
          .get('jkzx/getZfcOnlineTime', {
            baseURL,
            params: {
              structure_name: this.table.selectRow.structure_name,
              vehi_no: this.table.selectRow.vehi_no,
              stime: (stime && moment(stime).format('YYYY-MM-DD')) || '',
              etime: (etime && moment(etime).format('YYYY-MM-DD')) || '',
            },
          })
          .then((res) => {
            this.table.data = res.data
            this.table.currentPage = 1
            this.table.total = res.data.length
            this.table.loading = false
          })
      },
      getTreeData() {
        axios
          .all([
            axios.get('jkzx/getStructure', { baseURL }),
            axios.get('jkzx/getAllZfc', { baseURL }),
          ])
          .then(
            axios.spread((res1, res2) => {
              let data = [
                {
                  id: -1,
                  label: '杭州局',
                  children: [],
                },
              ]
              let data1 = _.map(res1.data, (item) => {
                return {
                  id: item.code,
                  label: item.structure_name,
                }
              })
              let data2 = _.map(res2.data, (item) => {
                return {
                  code: item.code,
                  id: item.deviceid,
                  label: item.vehi_no,
                }
              })
              _.each(data1, (item) => {
                item.children = _.filter(data2, (i) => i.code == item.id)
              })
              data[0].children.push(...data1)
              this.query.treeData = data
            })
          )
      },
      handleQueryClick() {
        if (this.$refs.tree.getCheckedNodes().length == 0)
          return this.$message.error('请选择单位')
        this.getZfcOnlineTimeStatistics()
      },
      // 执法车上线时长统计 查询
      getZfcOnlineTimeStatistics() {
        if (!this.$refs.tree) return
        if (this.$refs.tree.getCheckedNodes().length == 0) return
        this.table.type = 0 //切换为全部查询
        this.table.loading = true
        const { stime, etime } = this.query
        let structure_name = '' //单位
        let vehi_no = '' //车牌号
        console.info()
        structure_name = _.pluck(
          _.filter(
            this.$refs.tree.getCheckedNodes(false, true),
            (item) => item.children && item.label !== '杭州局'
          ),
          'label'
        ).join()
        vehi_no = _.pluck(
          _.filter(this.$refs.tree.getCheckedNodes(), (item) => !item.children),
          'label'
        ).join()
        if (structure_name.indexOf('杭州局') > -1) {
          structure_name = ''
          vehi_no = ''
        }
        axios
          .get('jkzx/getZfcOnlineTimeStatistics', {
            baseURL,
            params: {
              structure_name,
              vehi_no,
              stime: (stime && moment(stime).format('YYYY-MM-DD')) || '',
              etime: (etime && moment(etime).format('YYYY-MM-DD')) || '',
            },
          })
          .then((res) => {
            this.table.data = res.data
            this.table.currentPage = 1
            this.table.total = res.data.length
            let time1 = 0
            let time2 = 0
            _.each(res.data, (item) => {
              time1 += item.time1
              time2 += item.time2
            })
            this.total.time1 =
              parseInt(time1 / 60) + '小时' + (time1 % 60) + '分钟'
            this.total.time2 =
              parseInt(time2 / 60) + '小时' + (time2 % 60) + '分钟'
            this.table.loading = false
          })
      },
      // 导出
      handleExcelClick() {
        this.$confirm('是否需要导出?', '提示', {
          confirmButtonText: '是',
          cancelButtonText: '否',
          cancelButtonClass: 'el-button--danger',
          closeOnClickModal: false,
          type: 'info',
          center: true,
        })
          .then(() => {
            const { stime, etime } = this.query
            let structure_name = '' //单位
            let vehi_no = '' //车牌号
            if (this.table.type == 0) {
              structure_name = _.pluck(
                _.filter(
                  this.$refs.tree.getCheckedNodes(false, true),
                  (item) => item.children && item.label !== '杭州局'
                ),
                'label'
              ).join()
              vehi_no = _.pluck(
                _.filter(
                  this.$refs.tree.getCheckedNodes(),
                  (item) => !item.children
                ),
                'label'
              ).join()
              if (structure_name.indexOf('杭州局') > -1) {
                structure_name = ''
                vehi_no = ''
              }
              window.open(
                `${baseURL}jkzx/getZfcOnlineTimeStatisticsExcel?stime=${
                  (stime && moment(stime).format('YYYY-MM-DD')) || ''
                }&etime=${
                  (etime && moment(etime).format('YYYY-MM-DD')) || ''
                }&vehi_no=${vehi_no}&structure_name=${structure_name}`
              )
            } else {
              structure_name = this.table.selectRow.structure_name
              vehi_no = this.table.selectRow.vehi_no
              window.open(
                `${baseURL}jkzx/getZfcOnlineTimeExcel?stime=${
                  (stime && moment(stime).format('YYYY-MM-DD')) || ''
                }&etime=${
                  (etime && moment(etime).format('YYYY-MM-DD')) || ''
                }&vehi_no=${vehi_no}&structure_name=${structure_name}`
              )
            }
          })
          .catch((e) => {
            // console.error("error" + e)
          })
      },
      handleTablePageCurrentChange(index) {
        this.table.currentPage = index
      },
      // 前端分页
      filterTablePage({ data, pageSize, currentPage }) {
        const pageIndex = currentPage - 1
        return _.filter(data, (item, index) => {
          return index >= pageIndex * pageSize && index < currentPage * pageSize
        })
      },
    },
  })
})(Vue, _, jQuery)
