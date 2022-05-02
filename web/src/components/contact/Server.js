/* 
Creates and runs server for contact form emailer.

Dependencies:
	express - handles the route used by the POST request
	cors - allows for cross origin resource sharing between the frontend and the server
	nodemailer - simplifies sending emails with Node.js using SMTP
*/

// setting variables for using dependencies
const express = require('express');
const router = express.Router();
const cors = require('cors');
const nodemailer = require('nodemailer');

// starts server on localhost port 5000
const app = express();
app.use(cors());
app.use(express.json());
app.use('/', router);
app.listen(5000, () => console.log('Server Running'));

// email authentication for nodemailer. NOTE: Google ending support for less secure apps on May 30th, 2022
const contactEmail = nodemailer.createTransport({
	service: 'gmail',
	auth: {
		user: 'yagni.inc@gmail.com',
		pass: 'y4gn1nc22',
	},
});

// error handling for email
contactEmail.verify(error => {
	if (error) {
		console.log(error);
	} else {
		console.log('Ready to Send');
	}
});

// develops and sends email
router.post('/contact', (req, res) => {
	const name = req.body.name;
	const email = req.body.email;
	const message = req.body.message;
	const mail = {
		from: name,
		to: 'yagni.inc@gmail.com',
		subject: 'Contact Form Submission',
		html: `<p>Name: ${name}</p>
			   <p>Email: ${email}</p>
			   <p>Message: ${message}</p>`,
	};
	contactEmail.sendMail(mail, error => {
		if (error) {
			res.json({ status: 'ERROR' });
		} else {
			res.json({ status: 'Message Sent' });
		}
	});
});
