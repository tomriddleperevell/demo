Ext.define('DE.view.customers.UploadFile', {
	extend: "Ext.form.Panel",
	fileUpload: true,
	width: 300,
	autoHeight: true,
	config:{
		customerId : null,
	},
	reference : 'uploadFile',
	bodyStyle: 'padding: 10px 10px 10px 10px;',
	labelWidth: 50,
	defaults: {
		//anchor: '95%',

		allowBlank: false,

	},
	controller:{
		uploadFileHandler: function () {
			var me = this;
			log(me.getView().getForm().getValues());
			log(me.getView().getCustomerId());
			var customerID = me.getView().getCustomerId();
			if (me.getView().getForm().isValid()) {
				log("here");
				form_action = 1;
				me.getView().submit({
					url: 'customers/'+customerID+'/files',
					waitMsg: 'Uploading file...',

					success: function (uploadForm, action) {
						me.getView().up('window').close();
						log("success");
					},
					failure: function () {
						log("failure bro");
					}
				});
			}



		}


	},
	items: [{
		xtype: 'fileuploadfield',
		name: 'file',
		emptyText: 'Select a document to upload...',
		fieldLabel: 'File',
		buttonText: 'Browse',
		flex: 1
	}],
	buttons: [{
		text: 'Upload',
		handler: "uploadFileHandler",
	}]


});