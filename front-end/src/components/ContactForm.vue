<script setup>
import { ref } from 'vue';
import axios from 'axios';

// EMITS
const emits = defineEmits(["BackToPhotos"]);

const contatto = ref({
  nome: '',
  email: '',
  messaggio: ''
});

const inviaMessaggio = async () => {
    const response = await axios.post('http://localhost:8080/api/contatto', contatto.value);
    console.log(response.data);
    emits('BackToPhotos');
};

// FUNCTIONS
const backToPhoto = () => {
  emits('BackToPhotos');
};


</script>

<template>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <button class="btn btn-primary mb-5" @click="backToPhoto">Back</button>
                
                <form @submit.prevent="inviaMessaggio" >
                    <div class="mb-3 input-group">
                        <span class="input-group-text" id="basic-addon2">Nome</span>
                        <input type="text" id="nome" class="form-control" v-model="contatto.nome" required>
                    </div>

                    <div class="mb-3 input-group">
                        <span class="input-group-text" id="basic-addon2">Email:</span>
                        <input type="email" id="nome" class="form-control" v-model="contatto.email" required>
                    </div>

                    <div class="mb-3 input-group">
                        <span class="input-group-text" id="basic-addon2">Testo</span>
                        <textarea type="text" id="nome" class="form-control" v-model="contatto.messaggio" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-success">Invia Messaggio</button>
                </form>
                
            </div>
        </div>
    </div>
</template>

  

<style scoped>

</style>
