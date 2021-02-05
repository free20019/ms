var realTimeMonitorAPI = (function (_, axios, { baseURL }) {
  function getVehicleInfo(vhic) {
    return axios
      .get('claq/getStructure', { baseURL, params: { vhic: vhic } })
      .then((res) => {
        const list = res.data.data
        if (list.length == 0) return []

        const listItem = list[0]
        let data = []

        if (listItem.structure) {
          data = _.map(listItem.structure, (item) => {
            const data = resetStructure(item)
            const code = item.code
            const children = _.filter(
              listItem.videovehicle,
              (item) => item.code == code
            )
            if (children.length > 0) {
              data.children = _.map(children, resetVehicle)
              const onlineChildren = _.filter(
                data.children,
                (item) => item.state
              )
              // if (onlineChildren.length > 0)
              data.onlineChildren = onlineChildren
            }
            return data
          })
        } else data = _.map(listItem.videovehicle, resetVehicle)

        return data
      })
  }

  function resetStructure(item) {
    const data = {}
    data.id = item.id
    data.title = item.structure_name
    data.higherUps = item.higher_ups
    return data
  }
  function resetVehicle(item) {
    const dateNow = Date.now() - 60 * 3 * 1000
    const data = {}
    data.id = item.id
    data.title = item.vehi_no
    data.type = item.VEHICLE_TYPE
    data.speedTime = item.SPEED_TIME
    data.deviceId = item.deviceid
    data.state = dateNow < data.speedTime

    const vedioAddress = item.vedio_address
    // && data.state
    if (vedioAddress && data.state)
      data.children = _.map(vedioAddress.split(';'), resetVedioInfo)

    return data
  }
  function resetVedioInfo(item, index) {
    return { title: `通道${index + 1}`, src: item }
  }
  return { getVehicleInfo }
})(_, axios, { baseURL })
