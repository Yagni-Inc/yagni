import React, { Component } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NavBar from './components/navBar/NavBar';
import Landing from './pages/Landing';
import Shop from './pages/Shop';

import './App.css';

class App extends Component {
	state = {};
	render() {
		return (
			<div>
				<BrowserRouter>
					<NavBar />
					<Routes>
						<Route path='/yagni' element={<Landing />} />
						<Route path='/yagni/shop' element={<Shop />} />
					</Routes>
				</BrowserRouter>
			</div>
		);
	}
}

export default App;
