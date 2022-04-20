//判断菜单里面是否包含权限值，如果包含，就显示对应的菜单
export function isHasPermission(permissionValue){
  const menuList =  JSON.parse(sessionStorage.getItem('menuList'));
  const hasPermission = menuList.some(menu => {
    return permissionValue.includes(menu.permission)
  })
}
