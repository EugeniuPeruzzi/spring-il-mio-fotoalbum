<script setup>
// IMPORT LIBS
import { onMounted, ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PhotoIndex from './components/PhotoIndex.vue';

// DATA
const photos = ref(null);

// FUNCTIONS
const getPhotos = async () => {
  const data = await axios.get("http://localhost:8080/api/photos");
  photos.value = data.data;
};

// HOOKS
onMounted(getPhotos);
</script>

<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-12">
        <photo-index :photos="photos" />
      </div>
    </div>
  </div>
</template>

<style lang="scss" >
  @use './styles/generals.scss' as *;
</style>