import axios from 'axios';

const instance = axios.create({
    baseURL: '/api',
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json',
    },
});

instance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `${token}`;
        }
        config.headers['Content-Type'] = config.headers['Content-Type'] || 'application/json';

        return config;
    },
    (error) => {
        console.error('请求错误: ', error);
        return Promise.reject(error);
    }
);

instance.interceptors.response.use(
    (response) => {
        const {data} = response;
        if (data.code === 200) {
            return data.data;
        } else {
            ElMessage.error(data.message || '请求失败');
            return Promise.reject(new Error(data.message || '请求失败'));
        }
    },
    (error) => {
        if (error.response) {
            const {status, data} = error.response;
            let message
            switch (status) {
                case 401:
                    message = '未授权，请重新登录';
                    localStorage.removeItem('token');
                    window.location.href = '/login';
                    break;
                case 403:
                    message = '拒绝访问';
                    break;
                case 404:
                    message = '请求地址不存在';
                    break;
                case 500:
                    message = '服务器错误';
                    break;
                default:
                    message = data.message || `错误: ${status}`;
            }
            ElMessage.error(message);
        } else if (error.message.includes('timeout')) {
            ElMessage.error('请求超时');
        } else {
            ElMessage.error('网络错误，请稍后重试');
        }
        return Promise.reject(error);
    }
);

const request = {
    get(url, params, config = {}) {
        return instance.get(url, {params, ...config});
    },
    post(url, data, config = {}) {
        return instance.post(url, data, {...config});
    },
    put(url, data, config = {}) {
        return instance.put(url, data, {...config});
    },
    delete(url, params, config = {}) {
        return instance.delete(url, {params, ...config});
    },
    custom(config) {
        return instance.request(config);
    },
};

export default request;
