import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import logo from './yagni-inc-light.png';
import './NavBar.css';

class NavBar extends Component {
	state = {};
	render() {
		return (
			<div>
				<ul
					className='nav justify-content-evenly align-items-end text-light pb-2'
					id='nav'
				>
					<li className='nav-item'>
						<Link className='nav-link text-light' to='/yagni/shop'>
							Shop
						</Link>
					</li>
					<li className='nav-item'>
						<Link className='nav-link text-light' to='/yagni#contact'>
							Contact
						</Link>
					</li>
					<li className='nav-item'>
						<Link className='navbar-brand' to='/yagni'>
							<img src={logo} alt='Yagni Inc.' height='100' />
						</Link>
					</li>
					<li className='nav-item'>
						<Link className='nav-link text-light' to='/yagni/orders'>
							My Orders
						</Link>
					</li>
					<li className='nav-item'>
						<Link className='nav-link text-light' to='/yagni/cart'>
							Cart
						</Link>
					</li>
				</ul>
			</div>
		);
	}
}

export default NavBar;
