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


	showCustomerEdit: function (gridView, rec, row) {
		var me = this;
		var customerWin = Ext.create('DE.view.window.CustomerWindow', {
			customerRecord: rec,
			animateTarget: row,
			modal: true
		});

		customerWin.show();
	},

	deleteCustomer: function () {
		var me = this;
		var grid = me.lookup('customersGrid');
		var rec = grid.getSelectionModel().getSelection()[0];
		if (!rec) return;

		Ext.Msg.confirm('გაფრხილება!', 'დაადასტურეთ წაშლა', function (ans) {
			if (ans === 'yes') {
				me.getView().setLoading();
				rec.erase({
					callback: function () {
						me.getView().setLoading(false);
					},
					failure: function () {
						me.getStore('customersStore').reload();
					}
				});
			}
		});

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