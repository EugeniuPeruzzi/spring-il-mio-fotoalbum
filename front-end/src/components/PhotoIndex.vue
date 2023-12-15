<script setup>
import { ref, computed, defineProps, defineEmits } from 'vue';


// EMITS
const emits = defineEmits(["mostraFormContatto"]);

// Props
const props = defineProps({
    photos: {
        type: Array,
        required: true
    }
});

// Modello per l'input di ricerca
const search = ref('');
// Proprietà per filtrare le le foto in base all'input di ricerca
const filteredPhotos = computed(() => {
    if (!search.value) return props.photos;
    return props.photos.filter(photo => photo.name.toLowerCase().includes(search.value.toLowerCase()));
});

// FUNCTIONS
const contattaci = () => {
  emits('mostraFormContatto');
};

</script>

<template>
<div class="container">
    <div class="row">
        <h1 class="text-center mb-4">Il tuo album FRONT END</h1>
        <!-- Input di ricerca -->
        <div class=" col-12">
            <div class="input-group input-group-sm my-3 p-2 d-flex justify-content-center align-items-center" >
                <div class="d-flex">
                    <button class="btn btn-primary me-5" @click="contattaci">Contattaci</button>
                    <div class="">
                        <input v-model="search" style="width: 250px;" type="text" class="form-control"
                        aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
                    </div>
                    <h3 class="d-inline-block me-3">Cerca</h3>
                </div>
            </div>
        </div>
        <!-- Lista delle foto -->
        <div class="col-12">
            <div class="d-flex flex-wrap justify-content-center">
                <!-- Loop attraverso tutte le foto -->
                <div v-for="photo in filteredPhotos" :key="photo.id" class="m-3">
                    <!-- Card per ogni photo -->
                    <div class="card p-2" style="width: 25rem;">
                        <!-- Immagine della photo -->
                        <img :src="photo.url" class="card-img-top" alt="foto" style="height: 250px;">

                        <!-- Nome della photo -->
                        <h5 class="text-center mt-1 text-capitalize">{{ photo.name }}</h5>
                        <!-- Descrizione della photo -->
                        <p class="card-text overflow-auto p-3" style="height: 10rem;">{{ photo.description }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
  </template>
  

<style scoped>

</style>
