import { useState, useEffect } from "react";
import { createModel } from "hox";
import { createBot, deleteBot, getBots, startBot, stopBot } from "../api";

const useBot = () => {
  const [bots, setBots] = useState([]);
  const [isLoadingBots, setIsLoadingBots] = useState(false);

  const updateBots = async () => {
    setIsLoadingBots(true);
    const { data } = await getBots();
    const converted = data.map((bot) => {
      return {
        key: bot.name,
        id: bot.name,
        name: bot.name,
        age: "TODO",
        symbol: bot.symbol,
        createdAt: "TODO",
        invest: bot.invest,
        lowPrice: bot.low,
        highPrice: bot.high,
        grids: bot.grids,
        status: bot.status,
        trades: bot.buyGrids,
        action: bot,
      };
    });
    setBots(converted);
    setIsLoadingBots(false);
  };

  const del = async (id) => {
    const res = await deleteBot(id);
    if (res.status === 200) {
      await updateBots();
    }
  };

  const start = async (id) => {
    const res = await startBot(id);
    if (res.status === 200) {
      await updateBots();
    }
  };

  const stop = async (id) => {
    const res = await stopBot(id);
    if (res.status === 200) {
      await updateBots();
    }
  };

  const create = async (data) => {
    const res = await createBot(data);
    if (res.status === 200) {
      await updateBots();
    }
    return res;
  };

  const load = async (data) => {
    await updateBots();
  };

  useEffect(async () => {
    await updateBots();
  }, []);

  return {
    bots,
    isLoadingBots,
    del,
    start,
    stop,
    create,
    load,
  };
};

export default createModel(useBot);
