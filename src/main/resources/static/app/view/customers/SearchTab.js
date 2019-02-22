Ext.define('DE.view.customers.SearchTab', {
	extend : 'Ext.container.Container',
	title: 'ძებნის გვერდი',
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
})