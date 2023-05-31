<template>
  <Layout>
    <div class="car-publish-wrapper">
      <div>
        <span>用户名</span>
        <n-select
          type="text"
          :value="carPublishInfo.userName"
          :options="userNameList"
          @update:value="(value:string)=>(carPublishInfo.userName = value)"
        />
      </div>
      <div>
        <span>车辆名称</span>
        <n-input
          type="text"
          :value="carPublishInfo.carName"
          @input="(value:any) => carPublishInfo.carName =value"
        />
      </div>
      <div>
        <span>车辆描述</span>
        <n-input
          type="text"
          :value="carPublishInfo.carDesc"
          @input="(value:any) => carPublishInfo.carDesc =value"
        />
      </div>
      <div>
        <span>车辆详情</span>
        <n-input
          type="text"
          :value="carPublishInfo.carContent"
          @input="(value:any) => carPublishInfo.carContent =value"
        />
      </div>
      <div>
        <n-button
          @click="submitCarInfoHandle"
          type="info"
          :disabled="!(carPublishInfo.userName && carPublishInfo.carName)"
          >上传</n-button
        >
      </div>
    </div>
  </Layout>
</template>

<script setup lang="ts">
import { ref, reactive, onBeforeMount } from "vue";
import { NInput, NButton, NSelect, useMessage } from "naive-ui";
import { getAllUser, addCar } from "@/serve/api";
import Layout from "@/components/layout/index.vue";

const message = useMessage();
const userNameList = ref();

onBeforeMount(async () => {
  let resp = await getAllUser();

  if (
    resp.data.code &&
    resp?.data?.code === 1102 &&
    resp.data.data.length !== 0
  ) {
    userNameList.value = await resp.data.data.map((item: any) => ({
      label: item.userName,
      value: item.userName,
    }));
  }
});

const carPublishInfo = reactive({
  userName: ref(""),
  carName: ref(),
  carDesc: ref(),
  carContent: ref(),
  carStatus: 2,
  rentId: null,
});

const submitCarInfoHandle = async () => {
  const resp = await addCar({
    ...carPublishInfo,
    user: {
      userName: carPublishInfo.userName,
    },
  });
  if (resp.data && resp.data.code == 1102) message.success("添加成功,待审核中");
  else message.warning("添加成功,待审核中");
};
</script>

<style scoped>
.car-publish-wrapper {
  margin: 20px auto;
  padding: 24px;
  width: 64vw;
  border-radius: 12px;
  background-color: white;
  box-shadow: rgba(139, 128, 128, 0.02) 0px 1px 3px 0px,
    rgba(149, 169, 189, 0.15) 0px 0px 0px 1px;
  display: flex;
  flex-direction: column;
  gap: 24px 0;
}
</style>
