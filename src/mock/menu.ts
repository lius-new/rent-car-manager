import { VNode, Component, h } from "vue";
import { NIcon } from "naive-ui";

import {
  AccessibilityOutline,
  CarSportOutline,
  ArrowUp,
  HandRightOutline,
  LogInOutline,
  HomeOutline,
  CartSharp,
  Diamond,
} from "@vicons/ionicons5";

interface MenuItemInterface {
  label: string;
  key: string;
  icon: () => VNode<{ [key: string]: any }>;
  [key: string]: any;
}

interface renderIconFunc {
  (icon: Component): () => VNode<{ [key: string]: any }>;
}
const renderIcon: renderIconFunc = (icon: Component) => {
  return () => h(NIcon, null, { default: () => h(icon) });
};
const menuOptions: MenuItemInterface[] = [
  {
    label: "首页",
    key: "backend-home",
    icon: renderIcon(HomeOutline),
  },
  {
    label: "用户管理",
    key: "user-manager",
    icon: renderIcon(AccessibilityOutline),
  },
  { label: "车辆管理", key: "car-manager", icon: renderIcon(CarSportOutline) },
  { label: "车辆发布", key: "car-publish", icon: renderIcon(ArrowUp) },
  {
    label: "个人信息设置",
    key: "user-account",
    icon: renderIcon(HandRightOutline),
  },
];
const userOptions: MenuItemInterface[] = [
  {
    label: "用户信息",
    key: "user-account",
    icon: renderIcon(HandRightOutline),
  },
  {
    label: "注销",
    key: "cancellation",
    icon: renderIcon(LogInOutline),
  },
  {
    label: "租车",
    key: "rent-car-home",
    icon: renderIcon(CartSharp),
  },
];
const toVipOptions: MenuItemInterface = {
  label: "升级VIP",
  key: "to-vip",
  icon: renderIcon(Diamond),
};

export default { menuOptions, userOptions, toVipOptions };
