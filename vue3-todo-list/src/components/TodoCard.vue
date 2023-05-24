<script setup lang="ts">
import { useRouter } from 'vue-router';
import TodoListService from '../services/TodoListService';
import nProgress from 'nprogress';

const router = useRouter();
const props = defineProps({
    id: {
        type: Number,
        required: true
    },
    title : {
        type: String,
        required: true,
        default: 'Todo list'
    },
    description : {
        type: String,
        required: true,
        default: 'lorem ipsum dolor sit amet'
    },
    tasks : {
        type: Number,
        required: true,
        default: 0
    }
})

const seeTodoListDetails = () => {
    router.push({
        name: 'TodoDetail',
        params: {
            id: props.id
        },
    })
}

const deleteTodoList = async () => {
    nProgress.start();
    await TodoListService.deleteTodoList(props.id);
    nProgress.done();
    router.go(0);
}
</script>
<template>
    <div class="alert shadow-lg bg-base-200 mt-6">
        <div>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                class="stroke-info flex-shrink-0 w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            <div>
                <h3 class="font-bold">{{ id }} . {{ title }}</h3>
                <div class="text-xs">{{ description }} | {{ tasks }} tasks</div>
            </div>
        </div>
        <div class="flex-none">
            <button @click="seeTodoListDetails" class="btn btn-sm hover:btn-info">See</button>
            <button @click="deleteTodoList" class="btn btn-sm hover:btn-warning">Delete</button>
        </div>
    </div>
</template>
