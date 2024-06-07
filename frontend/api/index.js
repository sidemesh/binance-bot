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

const deleteBot = async (id) => {
  return await instance.delete("/bots/" + id);
};

const startBot = async (id) => {
  return await instance.post("/bots/" + id + "/start");
};

const stopBot = async (id) => {
  return await instance.post("/bots/" + id + "/stop");
};

export { createBot, getBots, deleteBot, startBot, stopBot};
