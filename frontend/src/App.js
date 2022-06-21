import "antd/dist/antd.css";
import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import PrivateRoute from "./components/routing/privateRoute";

function App() {
  return (
    <Router>
      <Switch>
        <Route
          exact
          path={process.env.PUBLIC_URL + "/login"}
          component={Login}
        />
        <PrivateRoute
          exact
          path={process.env.PUBLIC_URL + "/"}
          component={Home}
        />
      </Switch>
    </Router>
  );
}

export default App;
