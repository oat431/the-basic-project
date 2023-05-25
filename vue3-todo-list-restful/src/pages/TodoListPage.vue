<script setup lang="ts">
import DefaultLayout from '../layout/DefaultLayout.vue';
import TodoListService from '../services/TodoListService';
import TaskService from '../services/TaskService';
import TaskCard from '../components/TaskCard.vue';
import Nprogress from 'nprogress';
import { onMounted, ref } from 'vue';
import dayjs from 'dayjs';
import 'dayjs/locale/en'
import { Form, Field, ErrorMessage } from "vee-validate";
import { useRouter } from 'vue-router';
import { useTodoListStore } from '../store/todolist';
import * as yup from "yup";

const router = useRouter();
const todoList = useTodoListStore();
const editMark = ref(false);
const schema = yup.object({
    name: yup.string().required("Task name is required"),
});
const schema2 = yup.object({
    title: yup.string().required("Title is required"),
    description: yup.string().required("Description is required"),
});

const props = defineProps({
    id: {
        type: Number,
        required: true
    }
})

const formatDate = (date: string) => {
    return dayjs(date).locale('en').format('dddd, MMMM D, YYYY | h:mm A');
}

const createTask = async (value: { [x: string]: string }) => {
    Nprogress.start();
    await TaskService.createTask(props.id, value.name);
    const response = await TodoListService.getTodoListById(props.id);
    todoList.data = response.data;
    Nprogress.done();
}

const deleteTodoList = async () => {
    Nprogress.start();
    await TodoListService.deleteTodoList(props.id);
    Nprogress.done();
    router.push({ name: 'Home' });
}

const updateMode = () => {
    editMark.value = !editMark.value;
}

const updateTodoList = async (value: {[x: string]: string}) => {
    Nprogress.start();
    await TodoListService.updateTodoList(props.id, value.title, value.description);
    const response = await TodoListService.getTodoListById(props.id);
    todoList.data = response.data;
    editMark.value = !editMark.value;
    Nprogress.done();
}

onMounted(async () => {
    Nprogress.start();
    const response = await TodoListService.getTodoListById(props.id);
    todoList.data = response.data;
    Nprogress.done();
})
</script>
<template>
    <DefaultLayout v-if="todoList.data">
        <div v-if="editMark">
            <Form class="p-3" :validation-schema="schema2" @submit="updateTodoList">
                <div class="mt-2">
                    <p>
                        <Field name="title" type="text" placeholder="Title" :value="todoList.data.title" class="input input-bordered w-full max-w-x" />
                    </p>
                    <ErrorMessage class="text-error text-md" name="title" />
                </div>
                <div class="mt-2">
                    <p>
                        <Field name="description" type="text" placeholder="Description" :value="todoList.data.description"
                            class="input input-bordered input-lg w-full max-w-x" />
                    </p>
                    <p></p>
                    <ErrorMessage class="text-error text-md" name="description" />
                </div>
                <button type="submit" class="btn btn-success text-base-100 m-3">Update</button>
                <button @click="updateMode" class="btn btn-outline btn-success m-3">undo</button>
            </Form>
        </div>
        <div v-else>
            <h1 class="text-3xl font-bold mt-3">{{ todoList.data.title }}</h1>
            <p class="text-lg mt-2">{{ todoList.data.description }}</p>
        </div>
        <progress class="progress progress-primary w-full" :value="todoList.data.progress" max="100"></progress>
        <p class="text-md font-bold">{{ parseFloat(todoList.data.progress).toFixed(2) }} %</p>
        <p class="text-sm">create date: {{ formatDate(todoList.data.createdDate) }}</p>
        <p class="text-sm">update date: {{ formatDate(todoList.data.lastModifiedDate) }}</p>
        <TaskCard v-for="item in todoList.data.tasks" :id="item.id" :name="item.name" :isDone="item.isDone" :todoId="id" />
        <div v-if="!editMark">
            <button @click="updateMode" class="btn btn-warning text-base-100 m-4">edit</button>
            <button @click="deleteTodoList" class="btn btn-error text-base-100 m-4">delete</button>
        </div>
    </DefaultLayout>
    <DefaultLayout>
        <div class="text-center">
            <h1 class="text-2xl font-bold">Create Task</h1>
        </div>
        <Form class="p-3" :validation-schema="schema" @submit="createTask">
            <div class="mt-2">
                <p>
                    <Field name="name" type="text" placeholder="task name" class="input input-bordered w-full max-w-x" />
                </p>
                <ErrorMessage class="text-error text-md" name="name" />
            </div>
            <button type="submit" class="btn mt-3">Create</button>
        </Form>
    </DefaultLayout>
    <DefaultLayout>
        <RouterLink :to="{ name: 'Home' }" class="btn btn-primary text-base-100">Back</RouterLink>
    </DefaultLayout>
</template>