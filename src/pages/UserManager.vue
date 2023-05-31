<template>
  <Layout>
    <div class="page-content">
      <div class="operate-menu">
        <n-button type="primary" @click="addUserClickHandle">
          添加用户
        </n-button>
        <n-input
          v-model:value="storeDataTable.filterValue"
          type="text"
          placeholder="请输入用户名"
        />
      </div>
      <Table :func="getAllUser" />
    </div>
  </Layout>
</template>

<script setup lang="ts">
import { NButton, NInput } from "naive-ui";
import Layout from "@/components/layout/index.vue";
import Table from "@/components/table-user/index.vue";
import { uesDataTableStore, useTableOperateModel } from "@/stores/index";
import { getAllUser } from "@/serve/api";

const storeTableModal = useTableOperateModel();
const storeDataTable = uesDataTableStore();

const addUserClickHandle = () => {
  storeTableModal.openAddModal({
    userName: "",
    userPassword: "",
    userEmail: "",
    userRole: "USER",
    userStatus: 0,
  });
};
</script>

<style scoped>
.page-content {
  padding: 20px;
}

.operate-menu {
  display: flex;
  padding-top: 20px;
  padding-bottom: 20px;
  gap: 24px;
}
</style>
