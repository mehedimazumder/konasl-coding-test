import React from "react";
import { Route, Redirect } from "react-router-dom";

const PrivateRoute = ({ component: Component, ...rest }) => {
  const isAuthenticated = !!localStorage.getItem("access_token");

  return (
    <Route
      {...rest}
      render={(props) =>
        !isAuthenticated ? (
          <Redirect to={process.env.PUBLIC_URL + "/login"} />
        ) : (
          <Component {...props} />
        )
      }
    />
  );
};

export default PrivateRoute;
