import req from '@/common/util/request'

export function getMenuList(data) {
  return req({
    url: '/menu/findAllMenu',
    method: 'get',
    params: data
  })
}


//updateMenu
export function updateMenu(data) {
  return req({
    url: '/menu/updateMenu',
    method: 'put',
    data
  })
}

/**
 * 保存菜单
 * @param {菜单} data
 */
export function saveMenu(data) {
  return req({
    url: '/menu/saveMenu',
    method: 'post',
    data
  })
}

/**
 *  删除菜单
 * @param {id} id
 */
export function delMenu(id) {
  return req({
    url: '/menu/delMenu/' + id,
    method: 'delete'
  })
}
