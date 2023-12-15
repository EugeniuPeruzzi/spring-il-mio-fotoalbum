<script setup>
import { ref, computed } from 'vue';
import { defineProps, defineEmits } from 'vue';

// EMITS
const emits = defineEmits(["openPizza"]);

// Props
const props = defineProps({
    photos: {
        type: Array,
        required: true
    }
});

// Modello per l'input di ricerca
const search = ref('');
// Proprietà per filtrare le pizze in base all'input di ricerca
const filteredPhotos = computed(() => {
    if (!search.value) return props.photos;
    return props.photos.filter(photo => photo.name.toLowerCase().includes(search.value.toLowerCase()));
});

</script>

<template>
  <div class="container">
      <div class="row">
          <h1 class="text-center mt-4">Il tuo album</h1>
          <!-- Lista delle foto -->
          <div class="col-12">
              <div class="d-flex flex-wrap justify-content-center">
                  <!-- Loop attraverso tutte le foto -->
                  <div v-for="photo in filteredPhotos" :key="photo.id" class="m-3">
                      <!-- Card per ogni photo -->
                      <div class="card p-2" style="width: 18rem;">
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
