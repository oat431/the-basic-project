import graphqlClient from "./GraphQLClient";
export default {
    getTodoList(page: number, size: number) {
        const query = `
        query get_todolist_as_pagination {
            getTodoList(page: ${page}, size: ${size}) {
                page
                totalPages
                totalElements
                content {
                    id
                    title
                    description
                    createdDate
                    lastModifiedDate
                    progress
                    tasks {
                        name
                    }
                }
            }
        }`;
        return graphqlClient({ query: query });
    },
    getTodoListById(id: number) {
        const query = `
        query get_todolist_by_id {
            getTodoListById(id: ${id}){
                title
                description
                progress
                createdDate
                lastModifiedDate               
                tasks {
                    id
                    name
                    isDone
                }
            }
        }`;
        return graphqlClient({ query: query });
    },
    createTodoList(title: string, description: string) {
        const query = `
        mutation create_todo_list {
            createTodoList(
                body: {
                    title :"${title}",
                    description: "${description}" 
                }
            ) {
                id
                title
                description
                createdDate
                lastModifiedDate
                progress
            }
        }`;
        return graphqlClient({ query: query });
    },
    updateTodoList(todoId: number, title: string, description: string) {
        const query = `
        mutation update_todo_list {
            updateTodoList(
                id: ${todoId},
                body :{
                    title: "${title}",
                    description: "${description}"
                }
            ) {
                id
                title
                description
                createdDate
                lastModifiedDate
                progress
            }
        }`;
        return graphqlClient({ query: query });
    },
    deleteTodoList(todoId: number) {
        const query = `
        mutation delete_todo_list_by_id {
            deleteTodoList(id: ${todoId}) {
                title
                description
                progress
                createdDate
            }
        }`;
        return graphqlClient({ query: query });
    }
};
