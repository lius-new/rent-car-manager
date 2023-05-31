<template>
  <div class="home-wrapper">
    <nav class="menu-wrapper">
      <ul class="menu-list flex justify-between">
        <li class="menu-item home-title">
          <h3>LiusNew的租车平台</h3>
        </li>
        <li class="menu-item">
          <n-button
            style="padding: 0 20px"
            type="tertiary"
            @click="menuBtn.handleClick"
            >{{ menuBtn.title }}</n-button
          >
        </li>
      </ul>
    </nav>
    <div class="content-wrapper">
      <section class="car-views">
        <div
          :class="{
            'car-views-item': true,
            'view-rent': showRentItem.showRent && showRentItem.key === index,
          }"
          @click="() => carRentItemHandleClick(index)"
          v-for="(value, index) in data"
          :key="index"
        >
          <div class="front">
            <div class="car-image"></div>
            <div class="car-name-user">
              <div>
                <div>所有人:{{ value.user.userName }}</div>
                <div>车辆名:{{ value.carName }}</div>
              </div>
              <div class="car-rent-info">
                {{ value.rentId === null ? "未租借" : "已租借" }}
              </div>
            </div>
            <div class="car-msg">
              <div class="car-desc">{{ value.carDesc }}</div>
            </div>
          </div>
          <div class="backend">
            <div class="backend-rent" v-if="value.rentId !== null">
              <div class="rent-text">已租借</div>
              <div class="rent-time">
                <span>
                  开始时间:
                  {{ getDataTime(new Date(value.rent.rentStartDate)) }}</span
                >
                <span>
                  结束时间:
                  {{ getDataTime(new Date(value.rent.rentEndDate)) }}</span
                >
              </div>
            </div>
            <div v-else class="rent-operate">
              <div style="display: flex; align-items: center">
                <span style="margin-right: 8px">开始时间: </span>
                <n-date-picker
                  v-model:value="startEndDate.start"
                  type="datetime"
                  clearable
                />
              </div>
              <div style="width: 32px">
                <ArrowDownSharp />
              </div>
              <div style="display: flex; align-items: center">
                <span style="margin-right: 8px">结束时间: </span>
                <n-date-picker
                  v-model:value="startEndDate.end"
                  type="datetime"
                  clearable
                />
              </div>
              <n-button
                style="padding: 0 20px"
                type="tertiary"
                @click="() => rentBtnClickHandle(value)"
                >租借</n-button
              >
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed, reactive, ref, onBeforeMount } from "vue";
import { getDataTime, isLogin } from "@/utils";
import { NButton, NDatePicker, useMessage } from "naive-ui";
import { useRouter } from "vue-router";
import { getAllCarAndUserAndRent, rentCar } from "@/serve/api";
import { ArrowDownSharp } from "@vicons/ionicons5";

(window as any).$message = useMessage();

const router = useRouter();
let data = ref<any>(null);

const loadData = async () => {
  const resp = await getAllCarAndUserAndRent();
  data.value = await resp.data.data;
};

const showRentItem = reactive({ key: ref(0), showRent: ref(false) });
const startEndDate = reactive({
  start: new Date().getTime(),
  end: new Date().getTime() + 86400000,
});

const carRentItemHandleClick = (key: number) => {
  showRentItem.key = key;

  if (showRentItem.showRent === false) {
    showRentItem.showRent = true;
  }

  if (showRentItem.showRent === true) {
    showRentItem.showRent = false;
    showRentItem.showRent = true;
  }
};

const menuBtn = computed(() => {
  const userName = localStorage.getItem("userName");
  const token = localStorage.getItem("token");

  if (userName === null || token === null) {
    return {
      title: "登录",
      handleClick: () => {
        // 检查是否登录
        router.push("/login");
      },
    };
  } else {
    return {
      title: "前往后台页面",
      handleClick: () => router.push("/backend-home"),
    };
  }
});

const rentBtnClickHandle = async (value: any) => {
  let loginStatus = await isLogin();
  if (loginStatus) {
    // 获取用户名，和车辆信息,不可以自己租借自己

    const { id } = value;

    const resp = await rentCar({
      carId: id,
      userName: localStorage.getItem("userName"),
      startTime: startEndDate.start,
      endTime: startEndDate.end,
    });

    const { code } = resp.data;
    if (code !== 1011) {
      (window as any).$message.warning("租借失败");
      return;
    }
    (window as any).$message.warning("租借成功");

    await loadData();
  }
};

onBeforeMount(async () => await loadData());
</script>

<style lang="scss" scoped>
.home-wrapper {
  width: 72vw;
  margin: auto;
}
.home-wrapper .menu-wrapper {
  padding: 20px;
  border-bottom: 1px solid rgba(149, 169, 189, 0.15);

  & .menu-item.home-title {
    font-size: 1.25rem;
    font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
      "Lucida Sans", Arial, sans-serif;
    background: linear-gradient(-45deg, #60efff 0%, #00ff87 100%);
    background-clip: text;
    -webkit-background-clip: text;
    color: transparent;
  }
}

.content-wrapper {
  margin: 36px 0;

  & .car-views {
    display: grid;
    gap: 64px 36px;
    grid-template-columns: repeat(4, 1fr);
  }

  & .car-views-item.view-rent {
    transform: rotateY(180deg);
  }
  & .car-views .car-views-item {
    height: 48vh;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    background-color: #fff;
    border: 1px solid #e5e5e5;
    transform-style: preserve-3d; /* 开启3d效果 */
    transition: transform 1s ease-in-out;
    border-radius: 24px;

    & .front,
    .backend {
      width: 100%;
      height: 100%;
      position: absolute;
      border-radius: 24px;
      backface-visibility: hidden;
      background-position: center center;
      background-repeat: no-repeat;
      background-size: 100% 100%;
    }

    & .front {
      overflow: hidden;
      display: flex;
      flex-direction: column;
    }

    & .backend {
      transform: rotateY(180deg);
      display: flex;
      align-items: center;

      & .rent-operate {
        height: 50%;
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-around;
      }
    }

    & .front .car-image {
      background-repeat: no-repeat;
      background-image: url("@/assets/car-1.svg");
      background-color: white;
      background-size: cover;
      width: 100%;
      height: 64%;
      border-bottom: 1px solid #e5e5e5;
      box-shadow: rgba(139, 128, 128, 0.02) 0px 1px 3px 0px,
        rgba(149, 169, 189, 0.15) 0px 0px 0px 1px;
    }

    & .front .car-name-user,
    & .front .car-msg {
      flex: auto;
      padding: 0 12px;
      background-color: #fff;
      display: flex;
      align-items: center;
      justify-content: space-between;
      border-bottom: 1px solid #e5e5e5;

      & .car-rent-info {
        border: 1px solid #e5e5e5;
        font-size: 0.675rem;
        padding: 2px 6px;
        border-radius: 12px;
        color: #4c5c61;
      }

      & .car-desc {
        color: #4c5c61;
      }

      & .car-price {
        display: flex;
        align-items: center;
        font-size: 1.725rem;
        color: rgb(111, 193, 196);

        & svg {
          color: black;
          width: 16px;
        }
      }
    }

    & .backend .backend-rent {
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      gap: 12px 0;
    }
    & .backend .backend-rent .rent-text {
      font-size: 1.325rem;
      writing-mode: tb-rl;
    }
    & .backend .backend-rent .rent-time {
      margin-top: 20px;
      display: flex;
      gap: 8px 0;
      flex-direction: column;
    }
  }
}
</style>
