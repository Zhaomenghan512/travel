import authorize from './authorize'

const install = function(Vue) {
  Vue.directive('authorize', authorize)
}

authorize.install = install
export default authorize
