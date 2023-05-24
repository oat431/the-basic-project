<script setup lang="ts">
import TaskService from '../services/TaskService';
import TodoListService from '../services/TodoListService';
import { useRouter } from 'vue-router';
import nProgress from 'nprogress';
import { ref } from 'vue';
import { useTodoListStore } from '../store/todolist';

const todoList = useTodoListStore();
const router = useRouter();
const editMark = ref(false);
const props = defineProps({
    id: {
        type: Number,
        required: true
    },
    todoId: {
        type: Number,
        required: true
    },
    name : {
        type: String,
        required: true,
        default: 'Todo list'
    },
    isDone : {
        type: Boolean,
        required: true,
        default: 'lorem ipsum dolor sit amet'
    },
})

const showName = ref(props.name);

const updateMode = () => {
    editMark.value = !editMark.value;
}

const deleteTask = async () => {
    nProgress.start();
    await TaskService.deleteTask(props.id);
    const response = await TodoListService.getTodoListById(props.todoId);
    todoList.data = response.data;
    nProgress.done();
}

const updateStatus = async () => {
    nProgress.start();
    await TaskService.updateTaskStatus(props.id);
    const response = await TodoListService.getTodoListById(props.todoId);
    todoList.data = response.data;
    nProgress.done();
}

const updateTask = async () => {
    nProgress.start();
    await TaskService.updateTask(props.id, showName.value);
    const response = await TodoListService.getTodoListById(props.todoId);
    todoList.data = response.data;
    editMark.value = !editMark.value;
    nProgress.done();
}
</script>
<template>
    <div v-if="isDone" class="alert shadow-lg bg-base-200 mt-6">
        <div>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                class="stroke-info flex-shrink-0 w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            <div v-if="!editMark">
                <h3 class="font-bold">{{ id }} . {{ showName }}</h3>
            </div>
            <div v-else>
                <input type="text" class="input input-bordered w-full max-w-x" v-model="showName" />
            </div>
        </div>
        <div v-if="editMark" class="flex-none">
            <button @click="updateMode" class="btn btn-sm btn-outline btn-success">undo</button>
            <button @click="updateTask" class="btn btn-sm hover:btn-success">update</button>
        </div>
        <div v-else class="flex-none">
            <button @click="updateStatus" class="btn btn-sm hover:btn-error">not done</button>
            <button @click="updateMode" class="btn btn-sm hover:btn-info">edit</button>
            <button @click="deleteTask" class="btn btn-sm hover:btn-warning">delete</button>
        </div>
    </div>
    <div v-else class="alert shadow-lg bg-base-100 mt-6">
        <div>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                class="stroke-info flex-shrink-0 w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            <div v-if="!editMark">
                <h3 class="font-bold">{{ id }} . {{ showName }}</h3>
            </div>
            <div v-else>
                <input type="text" class="input input-bordered w-full max-w-x" v-model="showName" />
            </div>
        </div>
        <div v-if="editMark" class="flex-none">
            <button @click="updateMode" class="btn btn-sm btn-outline btn-success">undo</button>
            <button @click="updateTask" class="btn btn-sm hover:btn-success">update</button>
        </div>
        <div v-else class="flex-none">
            <button @click="updateStatus" class="btn btn-sm hover:btn-error">done</button>
            <button @click="updateMode" class="btn btn-sm hover:btn-info">edit</button>
            <button @click="deleteTask" class="btn btn-sm hover:btn-warning">delete</button>
        </div>
    </div>
</template>
