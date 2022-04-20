export default {
  // Global page headers (https://go.nuxtjs.dev/config-head)
  head: {
    title: '凤凰旅游网',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  // Global CSS (https://go.nuxtjs.dev/config-css)
  css: [
    {src:'element-ui/lib/theme-chalk/index.css'},
    {src:'assets/main.css'},
    {src:'quill/dist/quill.snow.css'},
    {src:'quill/dist/quill.bubble.css'},
    {src:'quill/dist/quill.core.css'},
  ],

  // Plugins to run before rendering page (https://go.nuxtjs.dev/config-plugins)
  plugins: [
    {src:'@/plugins/element-ui'},
    {src:'@/plugins/vue-quill-editor',ssr: false},
    {src:'@/plugins/localStorage',ssr: false }
  ],

  // Auto import components (https://go.nuxtjs.dev/config-components)
  components: true,

  // Modules for dev and build (recommended) (https://go.nuxtjs.dev/config-modules)
  buildModules: [
  ],

  // Modules (https://go.nuxtjs.dev/config-modules)
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
    '@nuxtjs/style-resources'
  ],

  styleResources:{
    less:[
      './assets/common.less'
    ]
  },

  //设置后台的地址
  axios: {
    baseURL: 'http://localhost:80'
  },

  // Build Configuration (https://go.nuxtjs.dev/config-build)
  build: {
    transpile: [/^element-ui/],
  }
}
