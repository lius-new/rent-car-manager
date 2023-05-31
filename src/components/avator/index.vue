<template>
  <n-button style="height: 48px" text :render-icon="renderIcon(PersonIcon)">
    {{ userName }}
  </n-button>
</template>
<script setup lang="ts">
import { NButton } from "naive-ui";
import { renderIcon } from "@/utils";
import { PersonOutline as PersonIcon } from "@vicons/ionicons5";
const userName = localStorage.getItem("userName");
</script>
