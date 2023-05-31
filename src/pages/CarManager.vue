<template>
  <Layout>
    <div class="page-content">
      <Table :func="tableFunc" />
    </div>
  </Layout>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { getAllCar, getCarByUser } from "@/serve/api";
import Layout from "@/components/layout/index.vue";
import Table from "@/components/table-car/index.vue";

// 通过判断userRole 来决定请求的连接

const tableFunc = computed(() => {
  let role = localStorage.getItem("userRole");
  if (role === "ADMIN") return getAllCar;
  else return getCarByUser;
});
</script>

<style scoped>
.page-content {
  padding: 20px;
}
</style>
