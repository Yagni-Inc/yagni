import React, { Component } from "react";

class Counter extends Component {
  state = {
    count: 1,
  };

  render() {
    return (
      <div>
        <button className="btn btn-dark btn-lg">Add to Cart </button>
        <span className={this.getBadgeClasses()}>{this.formatCount()}</span>
      </div>
    );
  }

  formatCount() {
    const { count } = this.state;
    return count === 0 ? "Zero" : count;
  }

  getBadgeClasses() {
    let classes = "badge m-2 text-dark bg-";
    classes += this.state.count === 0 ? "warning" : "info";
    console.log(classes);
    return classes;
  }
}

export default Counter;
