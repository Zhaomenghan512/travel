
export default {
  //inserted在dom树绘制后调用
  inserted(el, binding, vnode) {
    const { value } = binding
    const menuList =  JSON.parse(sessionStorage.getItem('menuList'));
    const hasPermission = menuList.some(menu => {
      return value.includes(menu.permission)
    })
    if (!hasPermission) {
      el.parentNode && el.parentNode.removeChild(el)
    }
  }
}


