;(function (Vue, _, $, api) {
  var vm = new Vue({
    el: '#root',
    data() {
      return {
        tree: {
          loading: false,
          query: '',
          data: [],
          defaultProps: {
            children: 'children',
            label: 'title',
          },
          selected: null,
        },
        video: {
          data: [
            {
              id: 'video1',
              src: '',
            },
          ],
          num: 1,
        },
        treeClick: {
          count: 0,
          label: '',
        },
        staticVideoData: [
          {
            id: 'video1',
            src: '',
          },
          {
            id: 'video2',
            src: '',
          },
          {
            id: 'video3',
            src: '',
          },
          {
            id: 'video4',
            src: '',
          },
          {
            id: 'video5',
            src: '',
          },
          {
            id: 'video6',
            src: '',
          },
          {
            id: 'video7',
            src: '',
          },
          {
            id: 'video8',
            src: '',
          },
          {
            id: 'video9',
            src: '',
          },
          {
            id: 'video10',
            src: '',
          },
          {
            id: 'video11',
            src: '',
          },
          {
            id: 'video12',
            src: '',
          },
          {
            id: 'video13',
            src: '',
          },
          {
            id: 'video14',
            src: '',
          },
          {
            id: 'video15',
            src: '',
          },
          {
            id: 'video16',
            src: '',
          },
        ],
      }
    },
    computed: {
      videoClass() {
        let num = this.video.data.length
        return num == 1 ? 'one' : num == 4 ? 'four' : num == 6 ? 'six' : num == 16 ? 'sixteen' : ''
      },
      hasShowSelectedVideo() {
        return this.video.data.length > 1 && this.tree.selected != null
      },
    },
    mounted() {
      this.getVehicleInfo()
    },
    methods: {
      getVehicleInfo() {
        this.tree.loading = true
        api.getVehicleInfo(this.tree.query).then((data) => {
          this.tree.data = data
          this.tree.loading = false
        })
      },
      handleTreeQueryKeyup() {
        if (this.tree.query.length > 2 || this.tree.query.length == 0) this.getVehicleInfo()
      },
      handleSelectedVideoItemClick(item, index) {
        const selected = this.tree.selected || item
        const videoId = `video${index + 1}`
        const videoItem = this.video.data[index]
        if (selected.src == videoItem.src) return
        if (videoItem.src) this.closeAisleIcon(videoItem)
        let player = videojs(videoId, { autoplay: true })
        if (videoItem.src) {
          player.dispose()
          const videoList = this.$refs[videoId]
          var videoTag = this.addVideoTag(videoId)
          if (videoList.length > 0) videoList[0].appendChild(videoTag)
        }
        player = videojs(videoId, { autoplay: true })
        this.$set(this.video.data, index, { ...videoItem, ...selected })
        player.src({ type: 'rtmp/flv', src: selected.src })
        this.tree.selected = null
      },
      handleVideoCloseClick(item, index) {
        const videoId = item.id
        const player = videojs(videoId, { autoplay: true })
        player.dispose()
        const videoList = this.$refs[videoId]
        var videoTag = this.addVideoTag(videoId)
        this.closeAisleIcon(this.video.data[index])
        if (videoList.length > 0) videoList[0].appendChild(videoTag)
      },
      handleAllVideoCloseClick() {
        _.each(this.video.data, (item, index) => {
          this.handleVideoCloseClick(item, index)
        })
      },
      // 切换视频数目
      handleVideoNum(num) {
        this.tree.selected = null
        // 1.先判断是push 还是splice
        // (*暂无*)2.所有staticVideoData 重新排序保证src 是连续的（不能是 1 2 3 5 有src）
        if (num > this.video.data.length) {
          // push
          for (let i = this.video.data.length; i < num; i++) {
            this.video.data.push(this.staticVideoData[i])
          }
        } else if (num < this.video.data.length) {
          // splice
          _.each(this.video.data, (item, index) => {
            if (!item) return
            if (index < num) return
            const player = videojs(`video${index + 1}`, { autoplay: true })
            player.dispose()
          })
          this.video.data.splice(num, this.video.data.length - num)
        }
        // this.video.data = []
        // for (let i = 0; i < num; i++) {
        //   this.video.data.push(this.staticVideoData[i])
        // }
      },
      //节点双击事件
      dbClickNode(node, data) {
        this.tree.selected = null
        // 是子节点
        if (!node.isLeaf || !data.src) return
        this.$set(data, 'active', true)
        if (this.video.data.length > 1) this.tree.selected = data
        //this.video.data[0] = { ...this.video.data[0], ...data }
        else this.handleSelectedVideoItemClick(data, 0)
        //this.$set(this.video.data, 0, { ...this.video.data[0], ...data })
        // _.some(this.video.data, (item) => {
        //   if (count == 0 && !item.src) {
        //   }
        // })
      },
      addVideoTag(videoId) {
        var videoTag = document.createElement('video')
        videoTag.setAttribute('id', videoId)
        videoTag.setAttribute('class', 'video-js vjs-default-skin vjs-big-play-centered')
        videoTag.setAttribute('controls', true)
        videoTag.setAttribute('preload', 'auto')
        return videoTag
      },
      showStructureNumber(node, data) {
        if (node.label && /[队]$/.test(node.label)) {
          if (!data.children) return '0 / 0'
          const onlineChildrenLength = data.onlineChildren.length || 0
          return `${data.children.length} / ${onlineChildrenLength}`
        }
        if (!data.children) return ''

        // const onlineChildrenLength = data.onlineChildren.length || 0
        // return `${data.children.length} / ${onlineChildrenLength}`
      },
      closeAisleIcon(videoItem) {
        this.eachTreeItem(this.tree.data, videoItem)
        this.$set(videoItem, 'src', '')
      },
      eachTreeItem(tree, vItem) {
        _.each(tree, (item) => {
          if (item.children) this.eachTreeItem(item.children, vItem)
          if (vItem.src == item.src) this.$set(item, 'active', false)
        })
      },
    },
  })
  $(function () {
    $('.scrollbar-macosx').scrollbar()
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
    if (fls.f) {
      document.getElementsByClassName('openFlash')[0].style.display = 'none'
      document.getElementsByClassName('openFlashTips')[0].style.display = 'none'
      //        document.write("您安装了flash,当前flash版本为: " + fls.v + ".x");
    } else {
      document.getElementsByClassName('openFlash')[0].style.display = 'block'
      document.getElementsByClassName('openFlashTips')[0].style.display = 'block'
      //        document.write("您没有安装flash");
    }
  })
})(Vue, _, jQuery, realTimeMonitorAPI)
