import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'YOUR_API_ENDPOINT',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        "Content-Type": 'application/json'
    }
});

export default apiClient;
