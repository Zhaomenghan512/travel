import request from '@/common/util/request'


export function getCommentsList(data) {
  return request({
    url: '/comments/page',
    method: 'post',
    params: data
  })
}


//删除评论
export function delComments(row) {
  return request({
    url: '/comments/delComments/'+row.id,
    method: 'get'
  })
}




