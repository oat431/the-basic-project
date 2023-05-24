import axios from "axios";

const graphqlInstant = axios.create({
    baseURL: "YOUR_API_ENDPOINT",
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
    }
});

const graphqlClient = (query: {[x:string]:string}) => {
    return graphqlInstant.post("/graphql", query);
}

export default graphqlClient;