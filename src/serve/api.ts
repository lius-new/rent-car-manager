import { httpGet, httpPost, httpDelete, httpPut } from "./http";

export const login = async (userInfo: any) =>
  await httpPost({
    url: "/user/login",
    data: JSON.stringify(userInfo),
  });

export const register = async (userInfo: any) =>
  await httpPost({
    url: "/user/register",
    data: JSON.stringify(userInfo),
  });

export const authLogin = async () => {
  let userRole = localStorage.getItem("userRole");
  return await httpGet({
    url: "/user/authLogin",
    params: { userRole: `${userRole}` },
  });
};

export const getAllUser = async () => {
  return await httpGet({ url: "/user" });
};

export const getUserByName = async (userName: string) => {
  return await httpGet({ url: `/user/${userName}` });
};

export const deleteUser = async (userName: string) => {
  return await httpDelete({ url: `/user/${userName}` });
};

export const addUser = async (user: any) => {
  return await httpPost({ url: "/user", data: JSON.stringify(user) });
};

export const changeUserInfo = async (user: any) => {
  return await httpPut({
    url: "/user",
    data: JSON.parse(JSON.stringify(user)),
  });
};
export const toVip = async (user: any) => {
  return await httpPut({
    url: "/user/to-vip",
    data: JSON.parse(JSON.stringify(user)),
  });
};


export const getAllCar = async () => {
  return await httpGet({ url: "/car" });
};

export const getAllCarAndUserAndRent = async () => {
  return await httpGet({ url: "/car/car-user-rent" });
};

export const getCarByUser = async () => {
  return await httpGet({
    url: `/car/user/${localStorage.getItem("userName")}`,
  });
};
export const deleteCar = async (user: {
  userName: string;
  cId: string | number;
}) => {
  return await httpDelete({ url: `/car`, params: { ...user } });
};
export const changeCar = async (info: any) => {
  return await httpPut({
    url: "/car",
    data: JSON.parse(JSON.stringify(info)),
  });
};
export const addCar = async (info: any) => {
  return await httpPost({
    url: "/car",
    data: JSON.parse(JSON.stringify(info)),
  });
};

export const rentCar = async (info: any) => {
  return await httpPost({ url: "/rent", data: JSON.stringify(info) });
};
