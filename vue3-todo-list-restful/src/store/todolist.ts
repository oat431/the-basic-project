import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useTodoListStore = defineStore('todolist',() => {
    const data = ref();
    return { data }
})