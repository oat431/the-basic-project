<script setup lang="ts">
import { ref } from "vue";

defineProps<{ msg: string }>();
const msg = ref(import.meta.env.TODOLIST_V3_API);

const count = ref(0);
</script>

<template>
  <div class="card mt-3">
    <button class="btn btn-primary" type="button" @click="count++">
      count is {{ count }}
    </button>
    <div class="mockup-code mt-3">
      <pre data-prefix=">" class="text-success">
        <code>Edit components/HelloWorld.vue</code> to test HMR
      </pre>
      <pre data-prefix=">" class="text-warning">
        <code>Check out <a class="link link-warning" href="https://vuejs.org/guide/quick-start.html#local" target="_blank">create-vue</a>, the official Vue + Vite starter</code>
      </pre>
      <pre data-prefix=">" class="text-secondary">
        <code>Intall <a class="link link-secondary" href="https://github.com/vuejs/language-tools" target="_blank">Volar</a> in your IDE for a better DX</code>
      </pre>
      <pre data-prefix=">" class="text-error">
        <code>{{ msg }}</code>
      </pre>
    </div>
  </div>
</template>
