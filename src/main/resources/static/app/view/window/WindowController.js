Ext.define('DE.view.window.WindowController', {
	extend: 'Ext.app.ViewController',

	onAfterRender: function () {
		var me = this;
		var customerWin = me.getView();
		var customerRec = customerWin.getCustomerRecord();
		me.lookup('addCustomerForm').loadRecord(customerRec);
	},

	saveCustomer: function () {
		var me = this;
		var form = me.lookup('addCustomerForm');
		form.updateRecord();
		var rec = form.getRecord();
		me.getView().setLoading();
		rec.save({
			callback: function () {
				me.getView().setLoading(false);
			},
			success: function () {
				me.getView().close();
			}
		});
	}
});