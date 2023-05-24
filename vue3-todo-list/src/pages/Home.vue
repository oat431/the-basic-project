<script setup lang="ts">
import DefaultLayout from '../layout/DefaultLayout.vue';
import TodoListService from '../services/TodoListService';
import TodoCard from '../components/TodoCard.vue';
import Nprogress from 'nprogress';
import { onMounted, ref } from 'vue';
import { Form, Field, ErrorMessage } from "vee-validate";
import { useRouter } from 'vue-router';
import * as yup from "yup";

const router = useRouter();
const data = ref();
const page = ref(0);
const limit = ref(0);
const schema = yup.object({
    title: yup.string().required(),
    description: yup.string().required(),
});

onMounted(async () => {
    const res = await TodoListService.getTodoList(0, 5);
    data.value = res.data.content;
    limit.value = res.data.totalPage;
});

const nextPage = async () => {
    Nprogress.start();
    if(page.value + 1 > limit.value-1) {
        Nprogress.done();
        return; 
    } else {
        page.value++;
    }
    const res = await TodoListService.getTodoList(page.value, 5);
    data.value = res.data.content;
    Nprogress.done();
}

const prevPage = async () => {
    Nprogress.start();
    if(page.value - 1 < 0) {
        Nprogress.done();
        return; 
    } else {
        page.value--;
    }
    const res = await TodoListService.getTodoList(page.value, 5);
    data.value = res.data.content;
    Nprogress.done();
}

const createTodoList = async (value : {[x:string]:string}) => {
    Nprogress.start();
    await TodoListService.createTodoList(value.title, value.description);
    Nprogress.done();
    router.go(0);
}

</script>

<template>
    <DefaultLayout>
        <div class="text-center">
            <h1 class="text-2xl font-bold">Sahachan TodoList Version 3</h1>
        </div>
        <TodoCard v-for="item in data" :id="item.id" :title="item.title" :description="item.description" :tasks="item.tasks.length" />
        <div class="btn-group mt-6">
            <button class="btn" @click="prevPage" >«</button>
            <button class="btn">Page {{ page+1 }} / {{ limit }}</button>
            <button class="btn" @click="nextPage" >»</button>
        </div>
    </DefaultLayout>
    <DefaultLayout>
        <div class="text-center">
            <h1 class="text-2xl font-bold">Create Todo List</h1>
        </div>
        <Form class="p-3" :validation-schema="schema" @submit="createTodoList">
            <div class="mt-2">
                <p>
                    <Field name="title" type="text" placeholder="Title" class="input input-bordered w-full max-w-x" />
                </p>
                <ErrorMessage class="text-error text-md" name="title" />
            </div>
            <div class="mt-2">
                <p>
                    <Field name="description" type="text" placeholder="Description" class="input input-bordered input-lg w-full max-w-x" />
                </p>
                <p></p>
                <ErrorMessage class="text-error text-md" name="description" />
            </div>
            <button type="submit" class="btn mt-3">Create</button>
        </Form>
    </DefaultLayout>
</template>
