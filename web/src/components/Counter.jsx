import React, { Component } from 'react';

class Counter extends Component {
	state = {
		value: 0,
	};

	// Event binding option
	// constructor() {
	//   super();
	//   this.handleIncrement = this.handleIncrement.bind(this);
	// }

	handleIncrement = () => {
		this.setState({ value: this.state.value + 1 });
	};

	render() {
		return (
			<div>
				<button
					onClick={this.handleIncrement}
					className='btn btn-dark btn-lg me-3'
				>
					Add to Cart
				</button>
				<i className='bi bi-plus'></i>
				<span className={this.getBadgeClasses()}>{this.formatCount()}</span>
				<i className='bi bi-dash' />
			</div>
		);
	}

	formatCount() {
		const { value } = this.state;
		return value === 0 ? 'Zero' : value;
	}

	getBadgeClasses() {
		let classes = 'badge m-2 text-dark bg-';
		classes += this.state.value === 0 ? 'warning' : 'info';
		return classes;
	}
}

export default Counter;
