<template>
  <n-layout style="height: 100vh" class="root-container">
    <n-layout-header
      :inverted="inverted"
      bordered
      style="
        display: flex;
        align-items: center;
        justify-content: end;
        padding-right: 4vw;
        gap: 0 42px;
      "
    >
      <n-button type="tertiary" @click="router.push('/')"> 租车 </n-button>
      <n-dropdown
        trigger="hover"
        :options="menuOptionList.userOptions"
        @select="userMenuHandleClick"
      >
        <Avatar />
      </n-dropdown>
    </n-layout-header>
    <n-layout has-sider>
      <n-layout-sider
        bordered
        show-trigger
        collapse-mode="width"
        :collapsed-width="64"
        :width="240"
        :native-scrollbar="false"
        :inverted="inverted"
      >
        <n-menu
          :value="currentMenu"
          :inverted="inverted"
          :collapsed-width="64"
          :collapsed-icon-size="22"
          :options="menuOptions"
          :on-update:value="menuHandleClick"
        />
      </n-layout-sider>
      <n-layout><slot></slot> </n-layout>
    </n-layout>
  </n-layout>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import Avatar from "@/components/avator/index.vue";
import {
  NButton,
  NMenu,
  NLayout,
  NLayoutHeader,
  NLayoutSider,
  NDropdown,
  useMessage,
} from "naive-ui";
import menuOptionList from "@/mock/menu";
import { useRoute, useRouter } from "vue-router";
import { computed } from "vue";
import { getRoleMenuList } from "@/utils";

const inverted = ref(false);

const router = useRouter();
const route = useRoute();

(window as any).$message = useMessage();

const menuOptions = computed(getRoleMenuList);
const currentMenu = computed(() => route.path.substring(1));

const userMenuHandleClick = (key: string) => {
  if (key === "cancellation") {
    localStorage.clear();
    router.push("/login");
  } else if (key === "user-account") {
    router.push("/user-account");
  } else if (key === "rent-car-home") {
    router.push("/");
  }
};

const menuHandleClick = (key: string) => {
  switch (key) {
    case "user-manager":
      router.push("/user-manager");
      break;
    case "car-manager":
      router.push("/car-manager");
      break;
    case "car-publish":
      router.push("/car-publish");
      break;
    case "user-account":
      router.push("/user-account");
      break;
    default:
      router.push("/");
      break;
  }
};
</script>

<style>
div.root-container.n-layout .n-layout-scroll-container {
  display: flex;
  flex-direction: column;
}
</style>
