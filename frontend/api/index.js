import axios from "axios";

const instance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_BASE_API,
  timeout: 10000,
});

const createBot = async (data) => {
  return await instance.put("/bots", data);
};

const getBots = async () => {
  return await instance.get("/bots");
};

export { createBot, getBots };
