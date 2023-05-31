import axios from "axios";
import { useRouter } from "vue-router";

if (process.env.NODE_ENV == "development") {
  axios.defaults.baseURL = "http://127.0.0.1:8080";
} else if (process.env.NODE_ENV == "debug") {
  axios.defaults.baseURL = "";
} else if (process.env.NODE_ENV == "production") {
  axios.defaults.baseURL = "";
}

// 设置请求相应时间
axios.defaults.timeout = 10000;
// 请求头信息是为post时content-type请求设置
// axios.defaults.headers.common["Content-Type"] ="application/json";
axios.defaults.headers.post["Content-Type"] = "application/json";
// axios.defaults.headers.get["Content-Type"] = "application/json";

// 请求拦截器,每次发送请求，判断是否有token。如果有就携带token
axios.interceptors.request.use(
  (config) => {
    let token = localStorage.getItem("token");
    token && (config.headers.Authorization = token);
    return config;
  },
  (err) => Promise.reject(err)
);

// 响应拦截器
axios.interceptors.response.use(
  (response): any => {
    // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
    if (response.status === 200) {
      if (response.data.code === 511) {
        // 未授权调取授权接口 TODO:
        return Promise.resolve(response);
      } else if (response.data.code === 510) {
        // 未登录跳转登录页
        return useRouter().push("/user/login");
      } else {
        return Promise.resolve(response);
      }
    } else {
      return Promise.reject(response);
    }
  },
  (error) => {
    if (error.response && error.response.status) {
      return Promise.reject(error.response);
    }
  }
);

interface httpRequestOptionInterface {
  url: string;
  params?: any;
  data?: any;
  [key: string]: any;
}

// get 请求
export async function httpGet({
  url,
  params = {},
}: httpRequestOptionInterface) {
  return await axios.get(url, { params: params });
}

// post 请求
export async function httpPost({ url, data = {} }: httpRequestOptionInterface) {
  return await axios.post(url, data);
}
// put 请求
export async function httpPut({ url, data = {} }: httpRequestOptionInterface) {
  return await axios.put(url, data);
}

// delete
export async function httpDelete({
  url,
  params = {},
}: httpRequestOptionInterface) {
  return await axios.delete(url, { params: params });
}
