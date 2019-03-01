Ext.define('DE.model.users.User', {
	extend: 'Ext.data.Model',

	fields: ['id', 'userName','password'],
	proxy: {
		type: 'rest',
		url: 'customers',
		writer: {
			writeRecordId: false,
			writeAllFields: true
		}
	}
});