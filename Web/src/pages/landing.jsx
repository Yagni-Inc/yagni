import React, { Component } from "react";
import NavBar from "../components/navBar/navBar";
import logo from "../logo.png";

// TODO: Make a landing page!

class Landing extends Component {
  state = {};
  render() {
    return (
      <div>
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h1 class="display-5 fw-bold m-3">Hello Yagni Inc!</h1>
            <p class="col-md-8 fs-4">Let's build a sick web page.</p>
            <button class="btn btn-dark btn-lg" type="button" onClick="">
              Click me!
            </button>
            <a href="index2.html"> click Me to go to link 2!</a>
          </header>
        </div>
      </div>
    );
  }
}

export default Landing;
