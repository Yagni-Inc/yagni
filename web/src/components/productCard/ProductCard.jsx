import React from 'react';
import './ProductCard.css';
import prodImg from './product.png';

const ProductCard = ({ item, handleClick }) => {
	const { productId, salePrice, supplierId } = item;
	return (
		<div className='col-sm-12 col-md-4 mb-5'>
			<div className='card align-items-center mb-5'>
				<img className='card-img-top pt-3 w-50 h-50' src={prodImg} alt='' />
				<div className='card-body align-items-start'>
					<h4 className='card-title'>
						Product ID: <span> {productId}</span>
					</h4>
					<h5 className='card-subtitle mb-3'>
						Sale Price: <span> ${salePrice.toFixed(2)}</span>
					</h5>
					<h5 className='card-subtitle mb-3'>
						Supplier: <span> {supplierId}</span>
					</h5>
					<button
						className='btn btn-dark btn-lg me-3'
						onClick={() => handleClick(item)}
					>
						Add to Cart
					</button>
				</div>
			</div>
		</div>
	);
};

export default ProductCard;
