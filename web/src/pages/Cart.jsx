import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const Cart = ({ cart, setCart, handleChange }) => {
	const [price, setPrice] = useState(0);

	const handleRemove = (id) => {
		const arr = cart.filter((item) => item.productId !== id);
		setCart(arr);
		handlePrice();
	};

	const handlePrice = () => {
		let ans = 0;
		cart.map((item) => (ans += item.amount * item.salePrice));
		setPrice(ans);
	};

	useEffect(() => {
		handlePrice();
	});

	return (
		<React.Fragment>
			<h1 className='m-5'> Shopping Cart </h1>
			<div className='container m-5'>
				<table className='table table-striped'>
					<thead>
						<tr>
							<th>Item Ordered</th>
							<th>Unit Price </th>
							<th className='d-flex justify-content-center'>Cart Actions </th>
						</tr>
					</thead>
					<tbody>
						{cart.map((item) => (
							<tr>
								<td key={item.productId}>{item.productId}</td>
								<td> $ {item.salePrice.toFixed(2)}</td>
								<td className='d-flex justify-content-center align-items-center'>
									<button
										className='btn btn-dark btn-sm'
										onClick={() => handleChange(item, 1)}
									>
										+
									</button>
									<span className='badge bg-callout text-dark ms-3 me-3'>
										{item.amount}
									</span>
									<button
										className='btn btn-dark btn-sm'
										onClick={() => handleChange(item, -1)}
									>
										-
									</button>
									<button
										className='btn btn-danger btn-sm ms-3'
										onClick={() => handleRemove(item.productId)}
									>
										Remove
									</button>
								</td>
							</tr>
						))}

						<tr>
							<td className='fs-3'>Cart Total</td>
							<td></td>
							<td className='fs-3 d-flex justify-content-center align-items-center'>
								$ {price.toFixed(2)}
								<Link
									className='btn bg-callout text-dark btn-lg m-3'
									type='button'
									to='/yagni/checkout'
								>
									Checkout
								</Link>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</React.Fragment>
	);
};

export default Cart;
