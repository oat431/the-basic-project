import { createRouter, createWebHistory } from "vue-router";
import Home from "../pages/Home.vue";
import TodoListPage from "../pages/TodoListPage.vue";
import Nprogress from "nprogress";

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/todo/:id",
        name: "TodoDetail",
        component: TodoListPage,
        props: true,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach(() => {
    Nprogress.start();
});

router.afterEach(() => {
    Nprogress.done();
});


export default router;