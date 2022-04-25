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
			<div className='col-sm-12 col-md-4 mb-5'>
				<div className='card align-items-center mb-5'>
					<img className='card-img-top pt-3 w-50 h-50' src={prodImg} alt='' />
					<div className='card-body align-items-start'>
						<h4 className='card-title'>
							Product ID: <span> {this.props.productId}</span>
						</h4>
						<h5 className='card-subtitle mb-3'>
							Sale Price: <span> ${this.props.salePrice.toFixed(2)}</span>
						</h5>
						<h5 className='card-subtitle mb-3'>
							Supplier: <span> {this.props.supplierId}</span>
						</h5>
						<Counter />
					</div>
				</div>
			</div>
		);
	}
}

export default ProductCard;
