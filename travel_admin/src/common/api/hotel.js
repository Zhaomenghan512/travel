import request from '@/common/util/request'

export function getHotelList(data) {

  return request({
    url: '/hotel/page',
    method: 'post',
    params: data
  })
}
//新增保存酒店
export function addSaveHotel(data) {
  return request({
    url: '/hotel/addSaveHotel',
    method: 'post',
    params:data
  })
}
//编辑保存
export function editSaveHotel(data) {
  return request({
    url: '/hotel/editSaveHotel',
    method: 'post',
    params:data
  })
}

//删除酒店
export function delHotel(row) {
  return request({
    url: '/hotel/delHotel/'+row.id,
    method: 'get'
  })
}


