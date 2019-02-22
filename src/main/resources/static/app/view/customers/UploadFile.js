Ext.define('DE.view.customers.UploadFile', {
	extend: "Ext.form.Panel",
	fileUpload: true,
	width: 300,
	autoHeight: true,
	bodyStyle: 'padding: 10px 10px 10px 10px;',
	labelWidth: 50,
	defaults: {
		//anchor: '95%',

		allowBlank: false,

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