<template>
  <main class="login-container-wrapper">
    <n-tabs
      :on-before-leave="onBeforeLeaveHandle"
      default-value="signin"
      size="large"
      animated
      justify-content="space-evenly"
      pane-style="padding-bottom:24px; box-sizing: border-box;"
    >
      <n-tab-pane name="signin" tab="登录">
        <n-form>
          <n-form-item-row label="用户名">
            <n-input
              placeholder="username"
              :on-change="(value:string) => {onChangeInputHandle(value,'username')}"
            />
          </n-form-item-row>
          <n-form-item-row label="密码">
            <n-input
              type="password"
              placeholder="password"
              :on-change="(value:string) => {onChangeInputHandle(value,'password')}"
            />
          </n-form-item-row>
        </n-form>
        <n-button type="primary" @click="onSubmitHandle" block secondary strong>
          登录
        </n-button>
      </n-tab-pane>
      <n-tab-pane name="signup" tab="注册">
        <n-form>
          <n-form-item-row label="用户名">
            <n-input
              placeholder="username"
              :on-change="(value:string) => {onChangeInputHandle(value,'username')}"
            />
          </n-form-item-row>
          <n-form-item-row label="密码">
            <n-input
              type="password"
              placeholder="password"
              :on-change="(value:string) => {onChangeInputHandle(value,'password')}"
            />
          </n-form-item-row>
          <n-form-item-row label="重复密码">
            <n-input
              type="password"
              placeholder="password"
              :on-change="(value:string) => {onChangeInputHandle(value,'password_')}"
            />
          </n-form-item-row>
        </n-form>
        <n-button type="primary" @click="onSubmitHandle" block secondary strong>
          注册
        </n-button>
      </n-tab-pane>
    </n-tabs>
  </main>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import { login, register } from "@/serve/api";
import {
  NInput,
  NButton,
  NForm,
  NTabs,
  NFormItemRow,
  NTabPane,
  useMessage,
} from "naive-ui";
import { useRouter } from "vue-router";

const msgProvider = useMessage();
const router = useRouter();

const operateMethod = ref<"signin" | "signup">("signin");
const userInfo = reactive({
  userName: ref(""),
  userPassword: ref(""),
  userPassword_: ref(""),
});

const onBeforeLeaveHandle = (name: "signin" | "signup") => {
  operateMethod.value = name;
  return true;
};

const onChangeInputHandle = (value: string, key: any) => {
  if (key === "username") {
    userInfo.userName = value;
  } else if (key === "password") {
    userInfo.userPassword = value;
  } else if (key === "password_") {
    userInfo.userPassword_ = value;
  }
};

const onSubmitHandle = () => {
  if (operateMethod.value === "signin") {
    login({
      userName: userInfo.userName,
      userPassword: userInfo.userPassword,
    }).then(({ data }) => {
      if (data.code === 1001) {
        msgProvider.success(data.msg);
        localStorage.setItem("token", data.data.token);
        localStorage.setItem("userName", userInfo.userName);
        localStorage.setItem("userRole", data.data.role);
        router.push("/");
      } else {
        msgProvider.warning(data.msg);
      }
    });
  } else {
    register({
      userName: userInfo.userName,
      userPassword: userInfo.userPassword,
    }).then(({ data }) => {
      if (data.code === 1003) {
        msgProvider.success(data.msg);
        localStorage.setItem("token", data.data.token);
        localStorage.setItem("userRole", data.data.role);
        localStorage.setItem("userName", userInfo.userName);
        router.push("/");
      } else {
        if (data.code === 1101) msgProvider.warning(`用户名已存在`);
        else msgProvider.warning(data.msg);
      }
    });
  }
};
</script>

<style scoped>
.login-container-wrapper {
  position: absolute;
  left: 50%;
  top: 16%;
  transform: translateX(-50%);
  border-radius: 12px;
  background-color: white;
  box-shadow: rgba(139, 128, 128, 0.02) 0px 1px 3px 0px,
    rgba(149, 169, 189, 0.15) 0px 0px 0px 1px;
}
.card-tabs .n-tabs-nav--bar-type {
  padding-left: 4px;
}
@media screen and (max-width: 756px) {
  .login-container-wrapper {
    width: 88vw;
    padding: 12px;
  }
}
@media screen and (min-width: 756px) {
  .login-container-wrapper {
    padding: 12px 40px;
    width: 64vw;
  }
}
@media screen and (min-width: 1024px) {
  .login-container-wrapper {
    padding: 12px 40px;
    width: 32vw;
  }
}
</style>
