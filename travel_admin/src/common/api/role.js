import req from '@/common/util/request'


export function getAllRole(data) {
  return req({
    url: '/role/list',
    method: 'get',
    params: data
  })
}



export function getRoleList(data) {
  return req({
    url: '/role/page',
    method: 'get',
    params: data
  })
}


export function updateRole(data) {
  return req({
    url: '/role/updateRole',
    method: 'put',
    data
  })
}

export function saveRole(data) {
  return req({
    url: '/role/saveRole',
    method: 'post',
    data
  })
}


export function delRole(id) {
  return req({
    url: '/role/deleteRole/' + id,
    method: 'delete'
  })
}

