import apiClient from "./ApiClient";
export default {
    getTaskList(taskId: number){
        return apiClient.get(`/task/${taskId}`);
    },
    createTask(todoId: number, name: string){
        return apiClient.post(`/task/?_todo_id=${todoId}`, {
            name: name
        });
    },
    updateTask(taskId: number, name: string){
        return apiClient.put(`/task/${taskId}`, {
            name: name
        })
    },
    updateTaskStatus(taskId: number){
        return apiClient.patch(`/task/${taskId}`);
    },
    deleteTask(taskId: number){
        return apiClient.delete(`/task/${taskId}`);
    }
};