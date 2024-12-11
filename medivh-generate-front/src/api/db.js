import axios from '../utils/request';

export const getTables = () => axios.get(`/generate/tables`);

export const generateCode = (pluginName, table) => 
    axios.post(`/generate/${pluginName}`, table, { responseType: 'blob' });

export const generateAllCode = (pluginName, tables) => 
    axios.post(`/generate/generate_all/${pluginName}`, tables, { responseType: 'blob' });

const dbApi = {
    testConnection: (strategy, data) => axios.post(`/generate/test_connection/${strategy}`, data),
    getTables,
    generateCode,
    generateAllCode,
};

export default dbApi;
