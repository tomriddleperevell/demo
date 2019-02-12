request({
	method: 'GET',
	url: 'customers/search',
	params: {
		fromAge: 20,
		toAge: 50
	},
	// data: {
	// 	firstName: 'ვიღაცა',
	// 	lastName: 'ვიღაციანი',
	// 	age: 15
	// },
	callback: function (responseText) {
		log('callback', responseText);
	},
	success: function (response) {
		log('success', response);
	},
	failure: function (responseText) {
		log('failure', responseText);
	}
});