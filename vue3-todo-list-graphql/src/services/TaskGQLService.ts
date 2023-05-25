import graphqlClient from "./GraphQLClient";
export default {
    createTask(todoId:number, name: string) {
        const query = `
        mutation create_task{
            createTodoTask(
                todoId: ${todoId},
                task:{
                    name : "${name}"
                }
            ) {
                title
                description
                progress
                tasks{
                    id
                    name
                    isDone
                }
            }
        }`;
        return graphqlClient({ query: query });
    },
    updateTask(taskId: number, name: string) {
        const query = `
        mutation update_task_by_id {
            updateTodoTask(
                id:${taskId}},
                task:{
                    name:"${name}"
                }
            ){
                title
                description
                progress
                createdDate
                lastModifiedDate
                tasks{
                    name
                    isDone
                }
            }
        }`;
        return graphqlClient({ query: query });
    },
    updateTaskStatus(taskId: number) {
        const query = `
        mutation update_task_status {
            updateTodoTaskStatus(id:${taskId}){
                title
                progress
                createdDate
                lastModifiedDate
            }
        }`;
        return graphqlClient({ query: query });
    },
    deleteTask(taskId: number) {
        const query = `
            mutation delete_todo_task {
                deleteTodoTask(id:${taskId}){
                    title
                    progress
                    description
                    createdDate
                    lastModifiedDate
                    tasks{
                        isDone
                        name
                    }
                }
            }`;
        return graphqlClient({ query: query });
    }
    
}