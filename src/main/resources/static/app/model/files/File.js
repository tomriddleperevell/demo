Ext.define('DE.model.files.File' ,{
	extend : 'Ext.data.Model',
	fields : ['id', 'customerId', 'fileName', 'fileLocation'],

	proxy : {
		type : 'rest',
		url : 'customers/{customerId}/files'
	}
});