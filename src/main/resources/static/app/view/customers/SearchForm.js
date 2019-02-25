Ext.define('DE.view.customers.SearchForm', {
	extend: 'Ext.form.Panel',
	layout: 'hbox',
	fieldDefaults: {
		labelAlign: 'top',
		margin: 5
	},

	items: [{
		xtype: 'textfield',
		name: 'personalNo',
		fieldLabel: 'პირადი N'
	}, {
		xtype: 'textfield',
		name: 'firstName',
		fieldLabel: 'სახელი'
	}, {
		xtype: 'textfield',
		name: 'lastName',
		fieldLabel: 'გვარი'
	}, {
		xtype: 'numberfield',
		name: 'fromAge',
		fieldLabel: 'ასაკიდან'
	}, {
		xtype: 'numberfield',
		name: 'toAge',
		fieldLabel: 'ასაკამდე'
	}],
	buttons: [{
		xtype: 'button',
		text: 'დამატება',
		handler: 'showAddCustomerWindow'
	},  '->', {
		xtype: 'button',
		text: 'ძებნა',
		handler: 'searchCustomers'
	}, {
		xtype: 'button',
		text: 'გასუფთავება',
		handler: 'resetSearchForm'
	}]
});