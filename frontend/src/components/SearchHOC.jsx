import React, { useEffect } from "react";
import { Layout, Select, AutoComplete, Input } from "antd";
import useSearchService from "../services/searchService";
import { Link } from "react-router-dom";

const { Header } = Layout;

const { Option } = Select;

const withSearch = (Component) => {
  return function NewComponent() {
    const { inputText, setInputText, search } = useSearchService();

    useEffect(() => {
      console.log("che", search);
    }, [search]);

    return (
      <Layout style={{ minHeight: "100vh" }}>
        <Header style={{ textAlign: "center" }}>
          {/* <Select
            showSearch
            placeholder="input search text"
            allowClear
            defaultActiveFirstOption={false}
            showArrow={false}
            filterOption={false}
            // value={inputText}
            onSearch={(e) => setInputText(e)}
            onChange={(e) => setInputText(e)}
            style={{ marginTop: "1em", width: "50em" }}
            notFoundContent={<div>Nothing found</div>}
          >
            {search?.result?.Search?.map((d) => (
              <Option key={d.imdbID}>{d.Title}</Option>
            ))}
          </Select> */}
          <AutoComplete
            allowClear
            dropdownMatchSelectWidth={252}
            style={{
              width: "50em",
            }}
            options={search?.result?.map((d) => {
              return {
                value: d.title,
                label: (
                  <div
                    style={{
                      display: "flex",
                      justifyContent: "space-between",
                    }}
                  >
                    {d.title}
                  </div>
                ),
              };
            })}
            onSearch={(e) => setInputText(e)}
          >
            <Input.Search size="large" placeholder="input here" enterButton />
          </AutoComplete>
        </Header>
        {<Component />}
      </Layout>
    );
  };
};

export default withSearch;
