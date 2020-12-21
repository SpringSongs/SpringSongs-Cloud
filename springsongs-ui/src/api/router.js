import request from '@/utils/request'
import qs from 'qs'
export function getRouters() {
  return request({
    url: '/SYS-API/SpringResource/GetRouters',
    method: 'get'
  })
}
