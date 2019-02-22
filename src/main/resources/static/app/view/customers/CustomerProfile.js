Ext.define('DE.view.customers.CustomerProfile', {
	extend: 'Ext.container.Container',
	title: 'პროფილი',
	closable: true,
	html: 'wazaaaaaaaap',
	reference: 'profilePage',

	config: {
		customerId: null
	},
	listeners: {
		afterrender: 'onAfterRender'
	},

	controller: {
		onAfterRender: function () {
			var me = this;
			var customerWin = me.getView();
			var customerId = customerWin.getCustomerId();
			log(customerId);
		},
	}

});