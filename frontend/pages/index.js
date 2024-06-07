import Nav from "../components/nav";
import { Table, Tag, Button, Spin } from "antd";
import useBot from "../hooks/bot";

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
    return (
      <Button type="primary" size="small">
        查看
      </Button>
    );
  }
  return (
    <Button disabled size="small">
      空仓
    </Button>
  );
};

const DeleteBotButton = (props) => {
  return (
    <Button type="link" danger size="small" {...props}>
      销毁
    </Button>
  );
};

const BotActions = ({ bot }) => {
  const { del, start, stop } = useBot();

  const handleDelete = async () => {
    if (confirm(`是否删除 ${bot.name}`)) {
      await del(bot.name);
    }
  };

  const handleStart = async () => {
    if (confirm(`是否启动 ${bot.name}`)) {
      await start(bot.name);
    }
  };

  const handleStop = async () => {
    if (confirm(`是否停止 ${bot.name}`)) {
      await stop(bot.name);
    }
  };

  if (bot.status == "RUNNING") {
    return (
      <>
        <Button type="link" size="small" onClick={handleStop}>
          停止
        </Button>
        <DeleteBotButton onClick={handleDelete} />
      </>
    );
  }

  if (bot.status == "STOP") {
    return (
      <>
        <Button type="link" size="small" onClick={handleStart}>
          运行
        </Button>
        <DeleteBotButton onClick={handleDelete} />
      </>
    );
  }
};

const columns = [
  {
    title: "名称",
    dataIndex: "name",
    key: "name",
    render: (text) => (
      <>
        <span className="mr-2">🤖</span>
        <span className="font-medium">{text}</span>
      </>
    ),
  },
  {
    title: "币对",
    dataIndex: "symbol",
    key: "symbol",
    render: (text) => <span>{text}</span>,
  },
  {
    title: "状态",
    dataIndex: "status",
    key: "status",
    render: (status) => <BotStatusTag status={status} />,
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
    title: "运行时长",
    dataIndex: "age",
    key: "age",
    ellipsis: true,
  },
  {
    title: "创建日期",
    dataIndex: "createdAt",
    key: "createdAt",
    ellipsis: true,
  },
  {
    title: "动作",
    key: "action",
    render: (bot) => <BotActions bot={bot} />,
  },
];

export default function Home() {
  const { bots, isLoadingBots } = useBot();

  return (
    <div className="container container-md mx-auto px-4 py-10">
      <Nav />
      <div className="mt-4">
        {isLoadingBots ? (
          <div className="grid justify-items-center  my-4">
            <Spin />
          </div>
        ) : (
          <Table columns={columns} dataSource={bots} />
        )}
      </div>
      <div className="mt-10 text-center">
        <span>binance-bot🤖 @2021</span>
      </div>
    </div>
  );
}
