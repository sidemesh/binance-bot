import { useState, useEffect, useRef } from "react";
import {
  Button,
  Drawer,
  Space,
  Form,
  Col,
  Row,
  Input,
  InputNumber,
  Select,
} from "antd";
import axios from "axios";

const { Option } = Select;

// https://api.binance.com/api/v3/klines?symbol=BICOUSDT&interval=1h
const CreateBootDrawer = (props) => {
  const [symbols, setSymbols] = useState([]);

  const chartRef = useRef();

  const onSymbolChange = async (symbol) => {
    if (symbol) {
      const { data } = await axios.get(
        `https://api.binance.com/api/v3/klines?symbol=${symbol}&interval=1h`
      );

      const converted = data.map((it) => {
        return {
          close: it[4],
          high: it[2],
          low: it[3],
          open: it[1],
          timestamp: it[0],
          volume: it[5],
        };
      });

      console.log(converted);
    }
  };

  useEffect(async () => {
    const res = await axios.get(
      "https://www.binance.com/bapi/asset/v1/public/asset-service/product/get-exchange-info"
    );
    const data = res.data.data;
    const symbols = data.map((item) => {
      return item.baseAsset + item.quoteAsset;
    });
    setSymbols(symbols);
  }, []);

  return (
    <Drawer
      title="创建机器人🤖"
      placement="right"
      onClose={props.onClose}
      visible={props.visible}
      extra={
        <Space>
          <Button onClick={props.onClose}>取消</Button>
          <Button onClick={props.onClose} type="primary">
            创建
          </Button>
        </Space>
      }
    >
      <Form layout="vertical">
        <Row>
          <Col span={24}>
            <Form.Item
              name="name"
              label="名称"
              rules={[{ required: true, message: "请输入机器人名称" }]}
            >
              <Input placeholder="请输入机器人名称" />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <Form.Item
              name="symbol"
              label="交易对"
              rules={[{ required: true, message: "请输入交易对名称" }]}
            >
              <Select
                showSearch
                style={{ width: "100%" }}
                placeholder="选择交易对"
                optionFilterProp="children"
                onChange={onSymbolChange}
              >
                {symbols.map((item) => {
                  return (
                    <Option value={item} key={item}>
                      {item}
                    </Option>
                  );
                })}
              </Select>
            </Form.Item>
          </Col>
        </Row>
        <Row gutter="16">
          <Col span={12}>
            <Form.Item
              name="lowPrice"
              label="区间下限"
              rules={[{ required: true, message: "请输入区间下限" }]}
            >
              <InputNumber addonAfter="$" placeholder="lowPrice" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="highPrice"
              label="区间上限"
              rules={[{ required: true, message: "请输入区间上限" }]}
            >
              <InputNumber addonAfter="$" placeholder="highPrice" />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter="16">
          <Col span={12}>
            <Form.Item
              name="invest"
              label="投资金额"
              rules={[{ required: true, message: "请输入投资金额" }]}
            >
              <InputNumber addonAfter="$" placeholder="invest" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="grids"
              label="网格数量"
              rules={[{ required: true, message: "请输入网格数量" }]}
            >
              <InputNumber addonAfter="G" placeholder="grids" />
            </Form.Item>
          </Col>
        </Row>
      </Form>
      <div className="mt-4" id="chart-root" ref={chartRef}></div>
    </Drawer>
  );
};

const Nav = () => {
  const [isShowCreateBotDrawer, setIsShowCreateBotDrawer] = useState(false);

  const showCreateBotDrawer = () => {
    setIsShowCreateBotDrawer(true);
  };

  const onCreateBotDrawerClose = () => {
    setIsShowCreateBotDrawer(false);
  };

  return (
    <div className="w-full flex justify-between">
      <h1 className="font-medium text-2xl">Binance-bot🤖</h1>

      <Button type="primary" onClick={showCreateBotDrawer}>
        创建机器人
      </Button>
      <CreateBootDrawer
        onClose={onCreateBotDrawerClose}
        visible={isShowCreateBotDrawer}
      />
    </div>
  );
};

export default Nav;
