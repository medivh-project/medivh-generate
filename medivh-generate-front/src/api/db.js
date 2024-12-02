import axios from '../utils/request';

export const getTables = () => axios.get(`/generate/tables`);

const dbApi = {
    testConnection: (strategy, data) => axios.post(`/generate/test_connection/${strategy}`, data),
    getTables,
};

export default dbApi;
