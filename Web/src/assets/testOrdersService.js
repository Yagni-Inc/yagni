const orders = [
	{
		orderId: 1,
		orderDate: '2022-04-01',
		custEmail: 'awilliams62090@gmail.com',
		products: [
			{
				productId: 'Eraser',
				quantity: 3,
				unitPrice: 0.75,
				supplierId: 'Erasers R US',
			},
			{
				productId: 'White Out',
				quantity: 5,
				unitPrice: 3.75,
				supplierId: 'Mistakes Make Us Rich',
			},
			{
				productId: 'Bleach',
				quantity: 2,
				unitPrice: 5.5,
				supplierId: 'When in doubt, bleach it out!',
			},
		],
		orderTotal: 32.0,
	},
	{
		orderId: 2,
		orderDate: '2022-03-15',
		custEmail: 'awilliams62090@gmail.com',
		products: [
			{
				productId: 'Eraser',
				quantity: 13,
				unitPrice: 0.75,
				supplierId: 'Erasers R US',
			},
			{
				productId: 'White Out',
				quantity: 9,
				unitPrice: 3.75,
				supplierId: 'Mistakes Make Us Rich',
			},
			{
				productId: 'Bleach',
				quantity: 5,
				unitPrice: 5.5,
				supplierId: 'When in doubt, bleach it out!',
			},
			{
				productId: 'Paint',
				quantity: 2,
				unitPrice: 15.05,
				supplierId: 'Cover the mistakes on your walls',
			},
		],
		orderTotal: 101.1,
	},
	{
		orderId: 3,
		orderDate: '2022-04-15',
		custEmail: 'awilliams62090@gmail.com',
		products: [
			{
				productId: 'Bleach',
				quantity: 1,
				unitPrice: 5.5,
				supplierId: 'When in doubt, bleach it out!',
			},
			{
				productId: 'Paint',
				quantity: 12,
				unitPrice: 15.05,
				supplierId: 'Cover the mistakes on your walls',
			},
			{
				productId: 'Scissors',
				quantity: 30,
				unitPrice: 4.65,
				supplierId: 'Rocks, Paper and Scissors Ltd.',
			},
		],
		orderTotal: 211.0,
	},
];

export function getOrders() {
	return orders;
}

export function getOrder(orderId) {
	return orders.find((o) => o.orderId === orderId);
}
