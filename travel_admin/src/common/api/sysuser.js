import req from '@/common/util/request'
//*******************用户登录请求*************************************
export function login(data) {
  return req({
    url: '/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return req({
    url: '/user/info',
    method: 'get'
  })
}

export function logout(token) {
  return req({
    url: '/logout',
    method: 'post',
    params: { access_token: token }
  })
}
//*******************用户中心请求*************************************

export function getUserList(data) {
  return req({
    url: '/user/page',
    method: 'get',
    params: data
  })
}

export function showImage(imgUrl) {
  return req({
    url: '/user/showimage/'+imgUrl,
    method: 'get'
  })
}

export function updateUser(data) {
  return req({
    url: '/user/updateUser',
    method: 'put',
    data
  })
}

/**
 * 保存用户
 * @param {用户} data
 */
export function saveUser(data) {
  return req({
    url: '/user/saveUser',
    method: 'post',
    data
  })
}
export function regUser(data) {
  return req({
    url: '/user/regUser',
    method: 'post',
    data
  })
}

/**
 *  删除用户
 * @param {id} id
 */
export function delUser(id) {
  return req({
    url: '/user/deleteUser/' + id,
    method: 'delete'
  })
}

//保存用户角色
export function saveUserRole(data) {
  return req({
    url: '/user/saveUserRole',
    method: 'post',
    data
  })
}
