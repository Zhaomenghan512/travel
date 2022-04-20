import request from '@/common/util/request'


//新增保存景区
export function saveSpots(data) {
  return request({
    url: '/spots/saveSpots',
    method: 'post',
    params:data
  })
}
//修改保存景区
export function updateSaveSpots(data) {
  return request({
    url: '/spots/updateSaveSpots',
    method: 'post',
    params:data
  })
}
//删除景区
export function deleteSpots(id) {
  return request({
    url: '/spots/deleteSpots/' + id,
    method: 'delete'
  })
}

//查询所有景区 getSpotsList
export function getSpotsList(data) {
  return request({
    url: '/spots/page',
    method: 'post',
    params: data
  })
}



