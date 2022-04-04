import React, { Component } from "react";
import logo from "../assets/img/logos/YagniLogoOnly.png";
import "./css/landing.css";
import { Link } from "react-router-dom";

// TODO: Make a landing page!

class Landing extends Component {
  state = {};
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <div className="container">
            <div className="row">
              <div className="col-sm-12 col-md-7 align-items-center">
                <img src={logo} className="App-logo" alt="logo" />
                <h1 className="display-2 fw-bold m-3">
                  Products by Yagni Inc.
                </h1>
                <p className="display-5">We got what you need</p>
                <Link
                  className="btn btn-callout btn-lg text-uppercase"
                  type="button"
                  to="/shop"
                >
                  Shop now
                </Link>
              </div>
            </div>
          </div>
        </header>
      </div>
    );
  }
}

export default Landing;
