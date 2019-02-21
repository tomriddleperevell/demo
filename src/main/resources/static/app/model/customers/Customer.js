Ext.define('DE.model.customers.Customer', {
	extend: 'Ext.data.Model',

	fields: ['id', 'personalNo', 'firstName', 'lastName', 'age'],
	proxy: {
		type: 'rest',
		url: 'customers',
		writer: {
			writeRecordId: false,
			writeAllFields: true
		}
	}
});