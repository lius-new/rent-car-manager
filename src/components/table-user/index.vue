<template>
  <n-data-table
    key="(row:any) => row.key"
    :columns="columns"
    :data="data"
    :pagination="{ pageSize }"
    striped
  />
  <TableModal v-if="storeTableModal.modalStore.open" />
</template>
<script setup lang="ts">
import { cloneDeep } from "lodash";
import { NDataTable, NButton, NButtonGroup } from "naive-ui";
import { ref, h, onBeforeMount } from "vue";
import TableModal from "@/components/table-user-modal/index.vue";
import { uesDataTableStore, useTableOperateModel } from "@/stores/index";
import { mapUserKey, encapsulatedDataCol, encapsulatedUserData } from "@/utils";

interface PropsInterface {
  func: Function;
  [key: string]: any;
}

const props = withDefaults(defineProps<PropsInterface>(), {});

const columns = ref<any>([]); // 表中的字段名
const data = ref<any>([]); // 表中数据
const storeTableModal = useTableOperateModel(); // 数据表格操作中的模态框的store
const storeDataTable = uesDataTableStore(); // 数据表格的store

// 数据
storeDataTable.$subscribe((_, state) => {
  if (state.filterValue.length !== 0) {
    let filter = state.data.filter((item: any) => {
      return (item.userName as string).includes(state.filterValue);
    });
    data.value = filter;
  } else {
    data.value = state.data;
  }
});

// 加载数据
const loadData = async () => {
  // 获取数据
  const resp = await props.func();

  if (
    resp.data.code &&
    resp?.data?.code === 1102 &&
    resp.data.data.length !== 0
  ) {
    storeDataTable.loadData(cloneDeep(resp.data.data)); // 将数据同步到store,保存备份
    data.value = encapsulatedUserData(await resp.data.data);
    columns.value = encapsulatedDataCol(mapUserKey, data.value[0]);

    // 添加操作按钮
    columns.value.push({
      title: "操作",
      key: "action",
      render(row: any) {
        return h(
          NButtonGroup,
          {
            style:
              "display: flex; flex-flow: row wrap; justify-content: flex-start; gap: 8px 12px;",
          },
          [
            h(
              NButton,
              {
                round: true,
                ghost: true,
                type: "warning",
                onClick: () => {
                  const current = storeDataTable.data.filter(
                    (item: any) =>
                      item.id === row.id && item.userName === row.userName
                  )[0];
                  storeTableModal.openEditModal(cloneDeep(current));
                },
              },
              { default: () => "编辑" }
            ),
            h(
              NButton,
              {
                round: true,
                ghost: true,
                type: "error",
                onClick: () => {
                  storeTableModal.openDeleteModal(row);
                },
              },
              { default: () => "删除" }
            ),
          ]
        );
      },
    });
  }
};

onBeforeMount(() => loadData());
</script>
