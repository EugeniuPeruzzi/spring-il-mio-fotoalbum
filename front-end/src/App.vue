<script setup>
// IMPORT LIBS
import { onMounted, ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PhotoIndex from './components/PhotoIndex.vue';
import ContactForm from './components/ContactForm.vue';

// DATA
const photos = ref(null);

// FUNCTIONS
const getPhotos = async () => {
  const data = await axios.get("http://localhost:8080/api/photos");
  photos.value = data.data;
};

const mostraIndex = ref(true);

const cambiaVista = () => {
  mostraIndex.value = false;
};

const backToPhoto = () => {
  mostraIndex.value = true;
};

// HOOKS
onMounted(getPhotos);
</script>

<template>
  <PhotoIndex v-if="mostraIndex" :photos="photos" @mostraFormContatto="cambiaVista" />
  <ContactForm v-else @BackToPhotos="backToPhoto"/>
</template>




<style lang="scss" >
  @use './styles/generals.scss' as *;

  *{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
</style>