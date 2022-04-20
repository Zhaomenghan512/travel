import request from '@/common/util/request'

export function getAllCity() {
  return request({
    url: '/common/getAllCities',
    method: 'post'
  })
}

export function getAllLevel() {
  return request({
    url: '/common/getAllLevel',
    method: 'post'
  })
}

export function getAllTheme() {
  return request({
    url: '/common/getAllTheme',
    method: 'post'
  })
}



