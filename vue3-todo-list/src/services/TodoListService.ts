import apiClient from "./ApiClient";
export default {
    getTodoList(page: number, size: number) {
        return apiClient.get('/todolist/', {
            params: {
                _page: page,
                _size: size
            }
        });
    },
    getTodoListById(id: number) {
        return apiClient.get(`/todolist/${id}`);
    },
    createTodoList(title: string, description: string) {
        return apiClient.post('/todolist/', {
            title: title,
            description: description
        });
    },
    updateTodoList(id: number, title: string, description: string) {
        return apiClient.put(`/todolist/${id}`, {
            title: title,
            description: description
        });
    },
    deleteTodoList(id: number) {
        return apiClient.delete(`/todolist/${id}`);
    }

}