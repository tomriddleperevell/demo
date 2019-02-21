Ext.define('DE.view.customers.Main', {
	extend: 'Ext.container.Container',
	controller: {
		xclass: 'DE.view.customers.CustomersController'

	},
	viewModel: {
		stores: {
			customersStore: {
				xclass: 'DE.store.customers.Customers'
			}
		}
	},
	title: 'კლიენტები',
	layout: {
		type: 'vbox',
		align: 'stretch'
	},
	items: [{
		xclass: 'DE.view.customers.SearchForm',
		reference: 'searchForm'
	}, {
		xclass: 'DE.view.customers.CustomersGrid',
		reference: 'customersGrid',
		flex: 1
	}]
});