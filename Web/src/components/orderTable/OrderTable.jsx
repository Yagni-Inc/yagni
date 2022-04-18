import React, { Component } from 'react';
import { getOrders } from '../../assets/testOrdersService';

class OrderTable extends Component {
	state = { orders: getOrders() };
	render() {
		const { length: count } = this.state.orders;
		const orders = this.state.orders;
		const email = this.state.orders[1].custEmail; //will need to change this to the email entered by the customer

		if (count === 0)
			return (
				<h2 className='text-center mb-5'>There are no orders to display!</h2>
			);
		return (
			<React.Fragment>
				<h2 className='text-center mb-5'>
					Showing {count} orders for {email}
				</h2>
				{orders.map((order) => (
					<div className='accordion accordion-flush'>
						<div className='accordion-item'>
							<h2 className='accordion-header' id='headingOne'>
								<button
									className='accordion-button collapsed'
									type='button'
									data-bs-toggle='collapse'
									data-bs-target='#collapseOne'
									aria-expanded='false'
									aria-controls='collapseOne'
								>
									<span className='me-3'>ORDER: {order.orderId} </span>
									<span className='me-3'>DATE: {order.orderDate}</span>
								</button>
							</h2>
							<div
								id='collapseOne'
								className='accordion-collapse collapse'
								aria-labelledby='headingOne'
								data-bs-parent='#accordionExample'
							>
								<div className='accordion-body'>
									<table className='table'>
										<thead>
											<tr>
												<th>Product ID</th>
												<th>Supplier ID</th>
												<th>Quantity Ordered</th>
												<th>Product Total</th>
											</tr>
										</thead>
										<tbody>
											{order.products.map((product) => (
												<tr>
													<td>{product.productId}</td>
													<td>{product.supplierId}</td>
													<td>{product.quantity}</td>
													<td className='text-end'>
														${' '}
														{(product.quantity * product.unitPrice).toFixed(2)}
													</td>
												</tr>
											))}
										</tbody>
									</table>
									<p className='text-end mt-2'>
										Order Total: ${order.orderTotal.toFixed(2)}
									</p>
								</div>
							</div>
						</div>
					</div>
				))}
			</React.Fragment>
		);
	}
}

export default OrderTable;
