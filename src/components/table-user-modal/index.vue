<template>
  <n-modal
    v-model:show="store.modalStore.open"
    preset="dialog"
    :icon="renderIcon(modelProps.icon)"
    :title="modelProps.title"
    :content="modelProps.content"
    :type="(modelProps.type as any)"
    :positive-text="modelProps.positiveText"
    :negative-text="modelProps.negativeText"
    @mask-click="modelProps.modelButtonCancelClick"
    @positive-click="modelProps.modelButtonOKClick"
    @negative-click="modelProps.modelButtonCancelClick"
    @close="modelProps.modelButtonCancelClick"
  >
    <div v-if="store.modalStore.type === 'edit'" class="edit-modal-wrapper">
      <div>
        <span>用户名</span>
        <n-input
          :status="(modelProps.type as any)"
          type="text"
          :value="userInfo.userName"
          @input="(value:any) => userInfo.userName = value"
        />
      </div>
      <div>
        <span>密码</span>
        <n-input
          :status="(modelProps.type as any)"
          type="text"
          :value="userInfo.userPassword"
          @input="(value:any) => userInfo.userPassword = value"
        />
      </div>
      <div>
        <span>邮箱</span>
        <n-input
          :status="(modelProps.type as any)"
          type="text"
          :value="userInfo.userEmail"
          @input="(value:any) => userInfo.userEmail = value"
        />
      </div>
      <div>
        <span>用户权限</span>
        <n-select
          :status="(modelProps.type as any)"
          :value="userInfo.userRole"
          @update:value="(value:string) => userInfo.userRole = value"
          :options="optionUserRole"
        />
      </div>
      <div>
        <span>用户状态</span>
        <n-select
          :status="(modelProps.type as any)"
          :value="userInfo.userStatus == '0' ? '启用' : '禁用'"
          @update:value="(value:string) => userInfo.userStatus = value"
          :options="optionUserStatus"
        />
      </div>
      <div>
        <span>用户余额</span>
        <n-input-number
          :status="(modelProps.type as any)"
          :value="userInfo.balance"
          @update:value="(value:any) => userInfo.balance = value"
        />
      </div>
    </div>
    <div v-else-if="store.modalStore.type === 'add'" class="add-modal-wrapper">
      <div>
        <span>用户名{{ userInfo.userName }}</span>
        <n-input
          :status="(modelProps.type as any)"
          type="text"
          :value="userInfo.userName"
          @input="(value:any) => userInfo.userName = value"
        />
      </div>
      <div>
        <span>密码</span>
        <n-input
          :status="(modelProps.type as any)"
          type="text"
          :value="userInfo.userPassword"
          @input="(value:any) => userInfo.userPassword = value"
        />
      </div>
      <div>
        <span>邮箱</span>
        <n-input
          :status="(modelProps.type as any)"
          type="text"
          :value="userInfo.userEmail"
          @input="(value:any) => userInfo.userEmail = value"
        />
      </div>
      <div>
        <span>用户权限</span>
        <n-select
          :status="(modelProps.type as any)"
          :value="userInfo.userRole"
          @update:value="(value:string) => userInfo.userRole = value"
          :options="optionUserRole"
        />
      </div>
      <div>
        <span>用户状态</span>
        <n-select
          :status="(modelProps.type as any)"
          :value="userInfo.userStatus == '0' ? '启用' : '禁用'"
          @update:value="(value:string) => userInfo.userStatus = value"
          :options="optionUserStatus"
        />
      </div>
    </div>
  </n-modal>
</template>
<script setup lang="ts">
import { useTableOperateModel } from "@/stores/index";
import { useMessage, NModal, NInput, NSelect, NInputNumber } from "naive-ui";
import { optionUserRole, optionUserStatus } from "@/mock/common";
import { renderIcon } from "@/utils";
import { computed, reactive, ref } from "vue";
import { deleteUser, addUser, changeUserInfo } from "@/serve/api";
import {
  CloudUploadOutline,
  TrashBinOutline,
  PersonAddOutline,
} from "@vicons/ionicons5";

const store = useTableOperateModel();
const message = useMessage();

const userInfo = reactive({
  id: store.modalStore.data.id,
  userName: ref(store.modalStore.data.userName),
  userPassword: ref(store.modalStore.data.userPassword),
  userEmail: ref(store.modalStore.data.userEmail),
  userRole: ref(store.modalStore.data.userRole),
  userStatus: ref(store.modalStore.data.userStatus),
  balance: ref(store.modalStore.data.balance),
});

const modelProps = computed(() => {
  switch (store.modalStore.type) {
    case "edit":
      return editProps;
    case "add":
      return addProps;
    case "delete":
      return deleteProps;
  }
});

const deleteProps = {
  icon: TrashBinOutline,
  title: "确认",
  content: "你确认是否删除吗?",
  type: "error",
  positiveText: "确认",
  negativeText: "取消",
  modelButtonOKClick: async () => {
    // 删除用户
    let resp = await deleteUser(userInfo.userName);

    if (resp.data && resp.data.code === 1102) message.success("删除成功");
    else message.warning("删除失败");
  },
  modelButtonCancelClick: () => {
    message.success("取消操作");
  },
};
const editProps = {
  icon: CloudUploadOutline,
  title: "请编辑如下个人信息",
  content: "",
  type: "warning",
  positiveText: "提交",
  negativeText: "取消",
  modelButtonOKClick: async () => {
    console.log(userInfo);

    const resp = await changeUserInfo(userInfo);
    if (resp.data && resp.data.code === 1102) message.success("修改成功");
    else message.warning("修改失败");
  },
  modelButtonCancelClick: () => {
    message.success("Cancel");
  },
};
const addProps = {
  icon: PersonAddOutline,
  title: "添加新用户",
  content: "",
  type: "success",
  positiveText: "提交",
  negativeText: "取消",
  modelButtonOKClick: async () => {
    const resp = await addUser(userInfo);
    if (resp.data && resp.data.code === 1102) message.success("添加成功");
    else message.warning("添加失败");
  },
  modelButtonCancelClick: () => {
    message.success("取消操作");
  },
};
</script>

<style scoped>
.edit-modal-wrapper,
.add-modal-wrapper {
  margin-top: 32px;
  display: flex;
  flex-direction: column;
  gap: 32px 0;
}
</style>
