import React, { Component } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NavBar from "./components/navBar/navBar";
import Landing from "./pages/landing";

import "./App.css";

class App extends Component {
  state = {};
  render() {
    return (
      <div>
        <BrowserRouter>
          <NavBar />
          <Routes>
            <Route path="/yagni" element={<Landing />} />
          </Routes>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
