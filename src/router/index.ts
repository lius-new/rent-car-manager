import { VNode } from "vue";
import {
  Home,
  Login,
  BackHome,
  CarManager,
  UserManager,
  UserAccount,
  CarPublish,
} from "@/pages/index";

import { isLogin, toLoginIsLogin } from "@/utils";
import { createRouter, createWebHistory, Router } from "vue-router";

interface routesItemInterface {
  path: string;
  name: string;
  component: any | VNode;
  [key: string]: any;
}

const routes: Array<routesItemInterface> = [
  {
    path: "/",
    component: Home,
    name: "root",
    meta: {
      isNeedLogin: false, //是否需要登录
    },
  },
  {
    path: "/backend-home",
    component: BackHome,
    name: "backend-home",
    meta: {
      isNeedLogin: true, //是否需要登录
    },
  },
  {
    path: "/user-manager",
    component: UserManager,
    name: "user-manager",
    meta: {
      isNeedLogin: true, //是否需要登录
    },
  },
  {
    path: "/car-manager",
    component: CarManager,
    name: "car-manager",
    meta: {
      isNeedLogin: true, //是否需要登录
    },
  },
  {
    path: "/car-publish",
    component: CarPublish,
    name: "car-publish",
    meta: {
      isNeedLogin: true, //是否需要登录
    },
  },
  {
    path: "/user-account",
    component: UserAccount,
    name: "user-account",
    meta: {
      isNeedLogin: true, //是否需要登录
    },
  },
  { path: "/login", name: "login", component: Login },
];

const router: Router = createRouter({
  history: createWebHistory(),
  routes,
});
//  from, next
router.beforeEach(async (to, _, next) => {
  if (to.meta.isNeedLogin) {
    isLogin();
    next();
  } else if (to.path === "/login") {
    toLoginIsLogin();
    next();
  } else {
    next();
  }
});

export default router;
