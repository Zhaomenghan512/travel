import request from '@/common/util/request'


export function getAllProduct(data) {
  return request({
    url: '/product/list',
    method: 'get',
    params: data
  })
}

//getCateGoryList分类列表


export function getCateGoryList(data) {
  return request({
    url: '/product/getCateGoryList',
    method: 'post',
    params: data
  })
}

export function getProductList(data) {
  return request({
    url: '/product/page',
    method: 'post',
    params: data
  })
}

export function upSelf(row) {
  return request({
    url: '/product/upSelf/'+row.id,
    method: 'get'
  })
}

export function downSelf(row) {
  return request({
    url: '/product/downSelf/'+row.id,
    method: 'get'
  })
}


export function updateProduct(data) {
  return request({
    url: '/product/updateProduct',
    method: 'put',
    data
  })
}

export function saveProduct(data) {
   return request({
    url: '/product/saveProduct',
    method: 'post',
    data
  })

}


export function delProduct(id) {
  return request({
    url: '/product/deleteProduct/' + id,
    method: 'delete'
  })
}

