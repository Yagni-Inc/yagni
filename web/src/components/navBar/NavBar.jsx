import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import logo from './yagni-inc-light.png';
import './NavBar.css';
import Counter from '../Counter';

class NavBar extends Component {
	state = {
		cartCount: 0,
	};

	render() {
		return (
			<div>
				<ul
					className='nav justify-content-evenly align-items-end text-light pb-2'
					id='nav'
				>
					<li className='nav-item'>
						<Link className='nav-link text-light' to='/shop'>
							Shop
						</Link>
					</li>
					<li className='nav-item'>
						<HashLink className='nav-link text-light' to='/#contact'>
							Contact
						</HashLink>
					</li>
					<li className='nav-item'>
						<Link className='navbar-brand' to='/'>
							<img src={logo} alt='Yagni Inc.' height='100' />
						</Link>
					</li>
					<li className='nav-item'>
						<Link className='nav-link text-light' to='/orders'>
							My Orders
						</Link>
					</li>
					<li className='nav-item'>
						<Link className='nav-link position-relative text-light' to='/cart'>
							Cart
							<span className='position-absolute top-50 start-100 translate-middle p-2 ms-2 bg-callout badge fs-6 text-dark'>
								<span class='visually-hidden'> Cart Items</span>
								{this.state.cartCount}
							</span>
						</Link>
					</li>
				</ul>
			</div>
		);
	}
}

export default NavBar;
