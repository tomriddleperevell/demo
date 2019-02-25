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
		log(form);
		form.updateRecord();
		var rec = form.getRecord();
		me.getView().setLoading();
		rec.save({
			callback: function () {
			},
			success: function () {
				me.getView().setLoading(false);
				me.getView().close();
			}
		});
	},

	uploadFileHandler: function () {
		var me = this;
		var uploadForm = me.lookup("uploadForm");
		var form = me.lookup('addCustomerForm');
		form.updateRecord();
		var rec = form.getRecord();
		if (uploadForm.getForm().isValid()) {
			log("here");
			form_action = 1;
			uploadForm.getForm().submit({
				url: 'customers/upload',
				waitMsg: 'Uploading file...',
				params: {
					id: rec.getId()
				},
				success: function (uploadForm, action) {
					log("success");
				},
				failure: function () {
					log("failure bro");
				}
			});
		}

	}
});