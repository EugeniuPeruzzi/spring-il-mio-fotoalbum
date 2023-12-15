<script setup>
import { ref } from 'vue';
import axios from 'axios';

const contatto = ref({
  nome: '',
  email: '',
  messaggio: ''
});

const inviaMessaggio = async () => {
    const response = await axios.post('http://localhost:8080/api/contatto', contatto.value);
    console.log(response.data);
};

</script>

<template>
    <div>
        <form @submit.prevent="inviaMessaggio">
        <label for="nome">Nome:</label>
        <input type="text" v-model="contatto.nome" required>

        <label for="email">Email:</label>
        <input type="email" v-model="contatto.email" required>

        <label for="messaggio">Messaggio:</label>
        <textarea v-model="contatto.messaggio" required></textarea>

        <button type="submit">Invia Messaggio</button>
        </form>
    </div>
</template>

  

<style scoped>

</style>
