import axios from "axios";
import createAuthRefreshInterceptor from "axios-auth-refresh";

const client_id = "cricket-client";
const client_secret = "cricket-secret";

const instance = axios.create({
  baseURL: "http://localhost:8080",
});

const refreshAuthLogic = (failedRequest) =>
  instance
    .post("/oauth/token", null, {
      params: {
        grant_type: "refresh_token",
        client_id,
        client_secret,
        refresh_token: sessionStorage.getItem("refresh_token"),
      },
    })
    .then((tokenRefreshResponse) => {
      failedRequest.response.config.params["access_token"] =
        tokenRefreshResponse.data.access_token;
      localStorage.setItem(
        "access_token",
        tokenRefreshResponse.data.access_token
      );
      localStorage.setItem(
        "refresh_token",
        tokenRefreshResponse.data.refresh_token
      );

      return Promise.resolve();
    })
    .catch(() => {
      return Promise.reject();
    });

createAuthRefreshInterceptor(instance, refreshAuthLogic);

export const login = async (data) => {
  const fd = new FormData();
  fd.append("grant_type", "password");
  fd.append("password", data.password);
  fd.append("username", data.username);
  fd.append("client_id", client_id);
  fd.append("client_secret", client_secret);
  return await instance.post(`/oauth/token`, fd);
};

export const searchCricInfo = async (stringValue, abortSignal) => {
  const url = new URL("http://localhost:8080/api/v1/scores/search");

  const params = {
    keyword: stringValue,
    page: 0,
    size: 5,
    access_token: localStorage.getItem("access_token"),
    client_id,
    client_secret,
  };

  url.search = new URLSearchParams(params).toString();

  const result = await fetch(url, {
    method: "get",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    signal: abortSignal,
  });
  const json = await result.json();
  console.log("searching", json.data);
  return json.data.content;
};

export const getLatestInfo = async () => {
  try {
    const res = await instance.get("/api/v1/scores/latest", {
      params: {
        access_token: localStorage.getItem("access_token"),
        client_id,
        client_secret,
      },
    });
    return res?.data;
  } catch (error) {
    console.error(error);
  }
};
