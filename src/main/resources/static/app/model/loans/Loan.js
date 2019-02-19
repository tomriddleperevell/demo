Ext.define('DE.model.loans.Loan', {
	extend: 'Ext.data.Model',

	fields: ['id', 'totalAmount', 'leftAmount', 'serviceName', 'customerId'],

	proxy: {
		type: 'rest',
		url: 'loans'
	}
});