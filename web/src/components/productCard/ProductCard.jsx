import React, { Component } from 'react';
import './ProductCard.css';
import prodImg from './product.png';
import Counter from '../Counter';

class ProductCard extends Component {
	state = {
		imgURL: 'product.png',
	};

	render() {
		return (
			<div className='card card-test'>
				<img className='card-img-top' src={prodImg} alt='' />
				<div className='card-body'>
					<h4 className='card-title'>Product ID: </h4>
					<h5 className='card-subtitle mb-3'> Sale Price: </h5>
					<Counter />
				</div>
			</div>
		);
	}
}

export default ProductCard;
