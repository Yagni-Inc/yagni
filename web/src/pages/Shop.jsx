import React from 'react';
import ProductCard from '../components/productCard/ProductCard';
import productData from '../assets/products.json';

const Shop = ({ handleClick }) => {
	return (
		<div>
			<header>
				<h1 className='display-2 fw-bold m-3 text-center'>Shop</h1>
			</header>
			<div className='container mt-5'>
				<div className='row'>
					{productData.products.map((item) => (
						<ProductCard
							key={item.productId}
							item={item}
							handleClick={handleClick}
						/>
					))}
				</div>
			</div>
		</div>
	);
};

export default Shop;
