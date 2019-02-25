Ext.define('DE.view.customers.CustomersController', {
	extend: 'Ext.app.ViewController',

	resetSearchForm: function () {
		var me = this;
		me.lookup('searchForm').reset();
	},

	showAddCustomerWindow: function (btn) {
		var me = this;
		var customerWin = Ext.create('DE.view.window.CustomerWindow', {
			customerRecord: Ext.create('DE.model.customers.Customer'),
			animateTarget: btn,
			modal: true
		});

		customerWin.show();
	},

	showCustomerEdit: function (gridView, rec) {
		var me = this;
		var tab = me.lookup("searchTab");
		var newTab = Ext.create('DE.view.customers.CustomerProfile', {
			customerId: rec.getId()
		});
		tab.add(newTab);
		tab.setActiveTab(newTab);

		/*var customerWin = Ext.create('DE.view.window.CustomerWindow', {
			customerRecord: rec,
			animateTarget: row,
			modal: true
		});

		customerWin.show();*/

	},

	searchCustomers: function () {
		var me = this;
		var form = me.lookup('searchForm');

		me.getView().setLoading();
		me.getStore('customersStore').load({
			params: form.getForm().getValues(),
			callback: function () {
				me.getView().setLoading(false);
			}
		});
	}
});