import { useEffect, useState } from "react";
import Nav from "../components/nav";
import { Table, Tag, Space, Button } from "antd";
import { getBots } from "../api";

const BotStatusTag = ({ status }) => {
  let color = "green";
  if (status === "RUNNING") {
    color = "blue";
  } else if (status === "STOP") {
    color = "red";
  }
  return <Tag color={color}>{status}</Tag>;
};

const BotPositionButton = ({ trades }) => {
  if (trades && trades.length > 0) {
    return <Button type="primary" size="small">查看</Button>;
  }
  return <Button disabled size="small">空仓</Button>;
};

const BotActions = ({bot}) => {

}

const columns = [
  {
    title: "名称",
    dataIndex: "name",
    key: "name",
    render: (text) => <span>{text}</span>,
  },
  {
    title: "币对",
    dataIndex: "symbol",
    key: "symbol",
    render: (text) => <span>{text}</span>,
  },
  {
    title: "运行时长",
    dataIndex: "age",
    key: "age",
  },
  {
    title: "创建日期",
    dataIndex: "createdAt",
    key: "createdAt",
  },
  {
    title: "投资金额",
    dataIndex: "invest",
    key: "invest",
  },
  {
    title: "底部价格",
    dataIndex: "lowPrice",
    key: "lowPrice",
  },
  {
    title: "顶部价格",
    dataIndex: "highPrice",
    key: "highPrice",
  },
  {
    title: "网格数",
    dataIndex: "grids",
    key: "grids",
  },
  {
    title: "持仓详情",
    dataIndex: "trades",
    key: "trades",
    render: (trades) => <BotPositionButton trades={trades} />,
  },
  {
    title: "状态",
    dataIndex: "status",
    key: "status",
    render: (status) => <BotStatusTag status={status} />,
  },
  {
    title: "动作",
    key: "action",
    render: (all) => (
      <Space size="middle">
        <a>启动</a>
        <a>暂停</a>
        <a>销毁</a>
      </Space>
    ),
  },
];

export default function Home() {
  const [bots, setBots] = useState([]);

  useEffect(async () => {
    const { data } = await getBots();
    const tableBots = data.map((bot) => {
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
    setBots(tableBots);
  }, []);

  return (
    <div className="container container-md mx-auto px-4 py-10">
      <Nav />
      <div className="mt-4">
        <Table columns={columns} dataSource={bots} />
      </div>
      <div className="mt-10 text-center">
        <span>binance-bot🤖 @2021</span>
      </div>
    </div>
  );
}
