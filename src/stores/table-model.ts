import { reactive } from "vue";
import { defineStore } from "pinia";

export const useTableOperateModel = defineStore("table-opreate-model", () => {
  const modalStore = reactive<{
    open: boolean;
    type: "edit" | "delete" | "add";
    data: any; // 这个是被选中要被操作的数据
  }>({
    open: false,
    type: "edit",
    data: null,
  });

  const openHandle = () => {
    modalStore.open = true;
  };
  const closeHandle = () => {
    modalStore.open = false;
    console.log("hello");
  };
  const openAddModal = (d: any) => {
    openHandle();
    modalStore.type = "add";
    modalStore.data = d;
  };

  const openEditModal = (d: any) => {
    openHandle();
    modalStore.type = "edit";
    modalStore.data = d;
  };
  const openDeleteModal = (d: any) => {
    openHandle();
    modalStore.type = "delete";
    modalStore.data = d;
  };
  const changeModalData = (key: any, value: string) => {
    modalStore.data[key] = value;
  };

  return {
    modalStore,
    openHandle,
    closeHandle,
    openEditModal,
    openAddModal,
    openDeleteModal,
    changeModalData,
  };
});
