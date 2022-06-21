import React, { useEffect, useState } from "react";
import { Layout, Table, Tag, Space } from "antd";
import { getLatestInfo } from "../server";

const { Content } = Layout;

const columns = [
  {
    title: "Title",
    dataIndex: "title",
    key: "title",
  },
  {
    title: "Description",
    dataIndex: "description",
    key: "description",
  },
  {
    title: "Link",
    dataIndex: "link",
    key: "link",
  },

  {
    title: "GUID",
    dataIndex: "guid",
    key: "guid",
  },
];

const Dashboard = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    const res = await getLatestInfo();
    setData(res?.data);
  };

  return (
    <Content
      style={{ paddingLeft: "50px", paddingRight: "50px", marginTop: "50px" }}
    >
      <Table columns={columns} dataSource={data} />;
    </Content>
  );
};

export default Dashboard;
