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
    }
};
