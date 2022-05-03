import React from 'react';
import { render } from 'react-dom';
import './Index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';

const rootElement = document.getElementById('root');
render(
	<React.StrictMode>
		<App />
	</React.StrictMode>,
	rootElement
);
