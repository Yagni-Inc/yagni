import React, { Component } from "react";
import { Link } from "react-router-dom";
import logo from "./yagni-inc-light.png";
import "./navBar.css";

class NavBar extends Component {
  state = {};
  render() {
    return (
      <nav class="navbar bg-light" id="ournav">
        <div class="container">
          <div className="nav">
            <a className="navbar-brand" href="/">
              <img src={logo} alt="Yagni Inc." height="100" />
            </a>
            <a className="navLink" href="/shop">
              Shop
            </a>
            <a className="navLink" href="#contact">
              Contact
            </a>
            <a className="navLink" href="/orders">
              My Orders
            </a>
            <a className="navLink" href="/cart">
              Cart
            </a>
          </div>
        </div>
      </nav>
    );
  }
}

export default NavBar;
