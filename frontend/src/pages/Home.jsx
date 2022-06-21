import React from "react";
import { Layout } from "antd";
import withSearch from "../components/SearchHOC";
import Dashboard from "./Dashboard";

const { Content } = Layout;

const Home = () => {
  return (
    <Content
      style={{ paddingLeft: "50px", paddingRight: "50px", marginTop: "50px" }}
    >
      <main>Welcome to Cricret live score!</main>

      <Dashboard />
    </Content>
  );
};

export default withSearch(Home);
