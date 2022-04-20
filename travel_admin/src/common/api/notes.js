import request from '@/common/util/request'

export function getNotesList(data) {

  return request({
    url: '/notes/page',
    method: 'post',
    params: data
  })
}



