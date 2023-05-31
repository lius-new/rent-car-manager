import { ref } from "vue";
import { defineStore } from "pinia";

export const uesDataTableStore = defineStore("ues-data-table-store ", () => {
  const filterValue = ref<string>("");
  const data = ref<any>();

  const loadData = (d: any) => (data.value = d);

  return {
    data,
    filterValue,
    loadData,
  };
});
