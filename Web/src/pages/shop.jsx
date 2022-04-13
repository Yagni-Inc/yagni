import React, { Component } from 'react';
import ProductCard from '../components/productCard/productCard';
import productData from '../assets/products.json';

class Shop extends Component {
	state = {
		products: productData.products,
	};
	render() {
		return (
			<div>
				<header>
					<h1 className='display-2 fw-bold m-3 text-center'>Shop</h1>
				</header>
				<div className='container mt-5'>
					<div className='row'>
						{productData.products.map((product) => (
							<ProductCard
								key={product.productId}
								productId={product.productId}
								quantity={product.quantity}
								salePrice={product.salePrice}
								supplierId={product.supplierId}
							/>
						))}
					</div>
				</div>
			</div>
		);
	}
}

export default Shop;
