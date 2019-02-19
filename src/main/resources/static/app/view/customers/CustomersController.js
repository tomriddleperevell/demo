Ext.define('DE.view.customers.CustomersController', {
	extend: 'Ext.app.ViewController',

	resetSearchForm: function () {
		var me = this;
		me.lookup('searchForm').reset();
	},

	searchCustomers: function () {
		var me = this;
		var form = me.lookup('searchForm');

		me.getStore('customersStore').load({
			params: form.getForm().getValues()
		});
	}
});