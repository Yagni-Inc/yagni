import React from 'react';
import { useForm } from 'react-hook-form';
import styled from 'styled-components';

import saveData from './SaveData';
import emailValidator from './EmailValidator';

const Styles = styled.div`
	// background: lavender;
	padding: 20px;

	h1 {
		border-bottom: 1px solid white;
		color: #3d3d3d;

		font-size: 20px;
		font-weight: 600;
		line-height: 24px;
		padding: 10px;
		text-align: center;
	}

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
		color: #3d3d3d;
		display: block;
		font-family: sans-serif;
		font-size: 14px;
		font-weight: 500;
		margin-bottom: 5px;
	}

	.submitButton {
		background-color: #6976d9;
		color: white;
		font-family: sans-serif;
		font-size: 14px;
		margin: 20px 0px;
	}
`;

const Contact = () => {
	const { contact, handleSubmit } = useForm();

	return (
		<form
			onSubmit={handleSubmit((data) => saveData(data))}
			className='text-start'
		>
			<h1>Contact Us</h1>
			<label>First name:</label>
			<input name='First Name ' ref={contact} />

			<label className='mt-3'>Last name:</label>
			<input name='Last Name' ref={contact} />

			<label className='mt-3'>Email:</label>
			<input name='Email' ref={contact} />

			<label className='mt-3'>Phone number:</label>
			<input name='phone' ref={contact} />

			<label className='mt-3'>Reason for contact:</label>
			<input name='contactReason' ref={contact} />

			<input type='submit' className='submitButton mt-5' />
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
