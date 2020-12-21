import request from '@/utils/request'
export function search(page, size, data) {
  return request({
    url: '/SYS-API/SpringLoginLog/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}
