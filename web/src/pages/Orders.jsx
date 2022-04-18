import React, { Component } from 'react';
import OrderTable from '../components/orderTable/OrderTable';

class Orders extends Component {
	// state = { orders: getOrders() };
	render() {
		return (
			<React.Fragment>
				<h1 className='m-5'>Order History</h1>
				<div className='container m-5'>
					<OrderTable />
				</div>
			</React.Fragment>
		);
	}
}

export default Orders;
