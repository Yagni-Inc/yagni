import React, { Component } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NavBar from './components/navBar/NavBar';
import Landing from './pages/Landing';
import Shop from './pages/Shop';
import Cart from './pages/Cart';
import Orders from './pages/Orders';
import ContactForm from './components/contact/Contact';
import './App.css';

class App extends Component {
	state = {};
	render() {
		return (
			<div>
				<BrowserRouter>
					<NavBar />
					<Routes>
						<Route path='/' element={<Landing />} />
						<Route path='/shop' element={<Shop />} />
						<Route path='/cart' element={<Cart />} />
						<Route path='/orders' element={<Orders />} />
					</Routes>
				</BrowserRouter>
			</div>
		);
	}
}

export default App;
