import Nav from "../components/nav";
import { Table, Tag, Space, Button } from "antd";

const columns = [
  {
    title: "名称",
    dataIndex: "name",
    key: "name",
    render: (text) => <a>{text}</a>,
  },
  {
    title: "币对",
    dataIndex: "symbol",
    key: "symbol",
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
    render: () => <Button size="small">查看</Button>,
  },
  {
    title: "状态",
    dataIndex: "status",
    key: "status",
    render: (status) => (
      <Tag color={status == "RUNNING" ? "green" : null}>{status}</Tag>
    ),
  },
  {
    title: "动作",
    key: "action",
    render: (text, record) => (
      <Space size="middle">
        <a>启动</a>
        <a>暂停</a>
        <a>销毁</a>
      </Space>
    ),
  },
];

const data = [
  {
    key: "1",
    id: "1",
    name: "BICO-01",
    age: "32天",
    symbol: "BICO_USDT",
    createdAt: "2021年12月31日 12:00:00",
    invest: 120,
    lowPrice: 3.52,
    highPrice: 5.52,
    grids: 12,
    status: "RUNNING",
  },
];

export default function Home() {
  return (
    <div className="container container-md mx-auto px-4 py-10">
      <Nav />
      <div className="mt-0">
        <Table columns={columns} dataSource={data} />
      </div>
      <div className="mt-10 text-center">
        <span>binance-bot🤖 @2021</span>
      </div>
    </div>
  );
}
