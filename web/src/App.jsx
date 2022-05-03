import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NavBar from './components/navBar/NavBar';
import Landing from './pages/Landing';
import Shop from './pages/Shop';
import Cart from './pages/Cart';
import Orders from './pages/Orders';
import './App.css';

const App = () => {
	const [cart, setCart] = useState([]);

	const handleClick = (item) => {
		if (cart.indexOf(item) !== -1) return;
		setCart([...cart, item]);
	};

	const handleChange = (item, d) => {
		const ind = cart.indexOf(item);
		const arr = cart;
		arr[ind].amount += d;

		if (arr[ind].amount === 0) arr[ind].amount = 1;
		setCart([...arr]);
	};
	return (
		<div>
			<BrowserRouter>
				<NavBar size={cart.length} />
				<Routes>
					<Route path='/yagni/' element={<Landing />} />
					<Route
						path='/yagni/shop'
						element={<Shop handleClick={handleClick} />}
					/>
					<Route
						path='/yagni/cart'
						element={
							<Cart cart={cart} setCart={setCart} handleChange={handleChange} />
						}
					/>
					<Route path='/yagni/orders' element={<Orders />} />
				</Routes>
			</BrowserRouter>
		</div>
	);
};

export default App;
