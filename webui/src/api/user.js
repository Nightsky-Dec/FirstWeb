import axios from '@/libs/api.request'

export const login = (params) => {
  console.log(params)
  return axios.request({
    url: 'user/login',
    data: params,
    method: 'post'
  })
}

// 暂时不用
export const getUserInfo = (name) => {
  return axios.request({
    url: 'user/user',
    params: {
      name
    },
    method: 'get'
  })
}

export const logout = (token) => {
  return axios.request({
    url: 'user/logout',
    method: 'post'
  })
}

export const addUser = (data) => {
  return axios.request({
    url: 'user/add',
    method: 'post',
    data: data
  })
}

export const updataUser = (data) => {
  return axios.request({
    url: 'user/updata',
    method: 'post',
    data: data
  })
}

export const getUnreadCount = () => {
  return axios.request({
    url: 'message/count',
    method: 'get'
  })
}

export const getMessage = () => {
  return axios.request({
    url: 'message/init',
    method: 'get'
  })
}

export const getContentByMsgId = msg_id => {
  return axios.request({
    url: 'message/content',
    method: 'get',
    params: {
      msg_id
    }
  })
}

export const hasRead = msg_id => {
  return axios.request({
    url: 'message/has_read',
    method: 'post',
    data: {
      msg_id
    }
  })
}

export const removeReaded = msg_id => {
  return axios.request({
    url: 'message/remove_readed',
    method: 'post',
    data: {
      msg_id
    }
  })
}

export const restoreTrash = msg_id => {
  return axios.request({
    url: 'message/restore',
    method: 'post',
    data: {
      msg_id
    }
  })
}
