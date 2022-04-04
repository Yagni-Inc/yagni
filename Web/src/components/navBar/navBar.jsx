import React, { Component } from "react";
import { Link } from "react-router-dom";
import logo from "./yagni-inc-light.png";
import "./navBar.css";

class NavBar extends Component {
  state = {};
  render() {
    return (
      <div>
        <ul
          className="nav justify-content-evenly align-items-end text-light"
          id="nav"
        >
          <li className="nav-item">
            <Link className="nav-link text-light" to="/shop">
              Shop
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link text-light" to="#contact">
              Contact
            </Link>
          </li>
          <li className="nav-item">
            <Link className="navbar-brand" to="/">
              <img src={logo} alt="Yagni Inc." height="100" />
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link text-light" to="/orders">
              My Orders
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link text-light" to="/cart">
              Cart
            </Link>
          </li>
        </ul>
      </div>
    );
  }
}

export default NavBar;
