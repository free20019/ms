;(function (Vue, _, $) {
  var vm = new Vue({
    el: '#root',
    data() {
      return {
        query: {
          room_name: '',
        },
        table: {
          loading: false,
          data: [],
        },
        dialog: {
          display: false,
          transfer: [],
          transferData: [],
          room_name: '',
          wait_time: '',
        },
        isShow: false,
        userId: null,
      }
    },
    mounted() {
      this.getPower()
      this.getVideoRoom()
      this.getUser()
      this.getUserId()
    },
    methods: {
      // 用户点入视频房间
      handleJoinClick(id) {
        this.$confirm('确认加入会话, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }).then(() => {
          axios
            .get('jkzx/clickVideoRoom', { baseURL, params: { room_id: id } })
            .then((res) => {
              if (res.data.count == 1) {
                window.open(res.data.url)
              } else{
                  this.$message.error('加入会话失败')
                  this.getVideoRoom()
              }
            })
        })
      },
      // 删除
      handleDelClick(id) {
        this.$confirm('此操作将删除该会议, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }).then(() => {
          axios
            .get('jkzx/deleteVideoRoom', { baseURL, params: { room_id: id } })
            .then((res) => {
              if (res.data > 0) {
                this.$message.success('删除成功')
                this.getVideoRoom()
              } else this.$message.error('删除失败')
            })
        })
      },
      // 取消按钮
      handleDialogCancel() {
        this.dialog.display = false
      },
      // 清空dialog
      handleDialogClose() {
        this.dialog.transfer = []
        this.dialog.room_name = ''
        this.dialog.wait_time = ''
      },
      // dialog提交
      submitDialog() {
        const { room_name, wait_time, transfer } = this.dialog
        if (!room_name) return this.$message.error('请填写房间名')
        if (!wait_time) return this.$message.error('请填写等待时间')
        let reg = /^[0-9]*$/
        if (!reg.test(wait_time))
          return this.$message.error('等待时间只能是数字')
        if (_.filter(transfer, (item) => item == this.userId).length == 0)
          return this.$message.error('被邀请的用户需要包含自己')
        axios
          .get('jkzx/addVideoRoom', {
            baseURL,
            params: {
              room_name,
              wait_time,
              invite_users: _.pluck(
                this.$refs.transfer.targetData,
                'label'
              ).join(),
              invite_user_ids: _.pluck(
                this.$refs.transfer.targetData,
                'key'
              ).join(),
            },
          })
          .then((res) => {
            if (res.data > 0) {
              this.$message.success('添加成功')
              this.getVideoRoom()
              this.dialog.display = false
            }else if (res.data == -1) {
              this.$message.error('该用户无权限"创建会议"')
              this.dialog.display = false
            }
          })
      },
      // 获取用户ID
      getUserId() {
        axios
          .get('jkzx/getUserId', {
            baseURL,
          })
          .then((res) => {
            this.userId = res.data
          })
      },
      // 获取用户ID
      getPower() {
        axios
          .get('common/power', {
            baseURL,
          })
          .then((res) => {
            // 权限"创建会议"
            if(res.data.data[0].menu.split(',').indexOf('sphy_add')>-1){
                this.isShow = true
            }
          })
      },
      handleQueryClick() {
        this.getVideoRoom()
      },
      // 查询视频房间
      getVideoRoom() {
        const { room_name } = this.query
        this.table.loading = true
        axios
          .get('jkzx/getVideoRoom', {
            baseURL,
            params: {
              room_name,
            },
          })
          .then((res) => {
            this.table.data = res.data
            this.table.loading = false
          })
      },
      // 格式化表格时间
      formatterTableTime(a, b, val) {
        return (val && moment(val).format('YYYY-MM-DD HH:mm:ss')) || ''
      },
      // 格式化是否可以进入房间（0：不可进入，1：可以进入）
      formatterTableInState(a, b, val) {
        return val == 0 ? '连接中' : val == 1 ? '已断开' : ''
      },
      // 添加视频房间dialog
      handleAddVideoRoomDialog() {
        this.dialog.display = true
      },
      // 用户下拉框
      getUser() {
        axios
          .get('jkzx/getUser', {
            baseURL,
          })
          .then((res) => {
            this.dialog.transferData = _.map(res.data, (item) => {
              return {
                key: item.id,
                label: item.username,
              }
            })
          })
      },
    },
  })
})(Vue, _, jQuery)
