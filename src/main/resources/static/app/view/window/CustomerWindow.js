Ext.define('DE.view.window.CustomerWindow', {
	extend: 'Ext.window.Window',
	controller: {
		xclass: 'DE.view.window.WindowController'
	},
	config: {
		customerRecord: null
	},
	title: 'Hello',

	items: [{
		xtype: 'form',
		reference: 'addCustomerForm',
		fieldDefaults: {
			labelAlign: 'top',
			margin: 5,
			width: 300
		},
		items: [{
			xtype: 'textfield',
			name: 'personalNo',
			fieldLabel: 'პირადი N',
			flex: 1
		}, {
			xtype: 'textfield',
			name: 'firstName',
			fieldLabel: 'სახელი',
			flex: 1
		}, {
			xtype: 'textfield',
			name: 'lastName',
			fieldLabel: 'გვარი',
			flex: 1
		}, {
			xtype: 'numberfield',
			name: 'age',
			fieldLabel: 'ასაკი',
			flex: 1
		}],
		buttons: [{
			xtype: 'button',
			text: 'შენახვა',
			handler: 'saveCustomer'
		}]
	}],
	listeners: {
		afterRender: 'onAfterRender'
	}
});