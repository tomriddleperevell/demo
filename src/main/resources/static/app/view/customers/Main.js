Ext.define('DE.view.customers.Main', {
	extend: 'Ext.container.Container',
	controller: {
		xclass: 'DE.view.customers.CustomersController'
	},

	viewModel: {
		stores: {
			customersStore: {
				xclass: 'DE.store.customers.Customers'
			},


		}
	},
	title: 'კლიენტები',
	items: [{
		reference:"searchTab",
		xtype: 'tabpanel',
		items: [{
			xclass: 'DE.view.customers.SearchTab',
			reference: 'searchTabRef'

		}]
	}

	]
});