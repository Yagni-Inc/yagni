import React, { useState } from 'react';
import styled from 'styled-components';

const Styles = styled.div`
	// background: lavender;
	padding: 20px;

	form {
		background: white;
		border: 1px solid #dedede;
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		margin: 0 auto;
		max-width: 800px;
		padding: 30px 50px;
	}

	input {
		border: 1px solid #d9d9d9;
		border-radius: 4px;
		box-sizing: border-box;
		padding: 10px;
		width: 100%;
	}

	label {
		color: #3f3f3f;
		display: block;
		font-family: sans-serif;
		font-size: 16px;
		font-weight: 500;
		margin-bottom: 5px;
	}

	.submitButton {
		background-color: #6976d9;
		color: white;
		font-family: sans-serif;
		font-size: 14px;
		margin: 20px 0px;
		height: 40px;
	}

	.textBox {
		width: 700px;
	}
`;

const Contact = () => {
	const [status, setStatus] = useState('Submit');

	const handleSubmit = async e => {
		e.preventDefault();
		setStatus('Sending...');
		const { name, email, message } = e.target.elements;
		let details = {
			name: name.value,
			email: email.value,
			message: message.value,
		};
		let response = await fetch('http://localhost:5000/contact', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json;charset=utf-8',
			},
			body: JSON.stringify(details),
		});
		setStatus('Submit');
		let result = await response.json();
		alert(result.status);
	};

	return (
		<form onSubmit={handleSubmit} className='text-start'>
			<div>
				<label htmlFor='name'>Name:</label>
				<input type='text' id='name' required />
			</div>
			<div>
				<label htmlFor='email'>Email:</label>
				<input type='email' id='email' required />
			</div>
			<div>
				<label htmlFor='message'>Message:</label>
				<textarea id='message' className='textBox' required />
			</div>
			<button type='submit' className='submitButton'>
				{status}
			</button>
		</form>
	);
};

export default function ContactForm() {
	return (
		<Styles>
			<Contact />
		</Styles>
	);
}
