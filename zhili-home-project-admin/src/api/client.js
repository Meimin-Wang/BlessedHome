import { clientAuth } from '@/api/constants'
import request from '@/utils/request'

export function getClients() {
  return request({
    url: '/api/clients',
    method: 'GET',
    headers: {
      'Authorization': clientAuth
    }
  })
}
