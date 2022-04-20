import request from '@/common/util/request'

export function getOrderList(data) {
  return request({
    url: '/order/page',
    method: 'post',
    params: data
  })
}

