import request from '@/common/util/request'

export function getCustormList(data) {
  return request({
    url: '/custorm/page',
    method: 'post',
    params: data
  })
}



