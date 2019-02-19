Ext.define('DE.view.customers.CustomersGrid', {
	extend: 'Ext.grid.Panel',
	bind: {
		store: '{customersStore}'
	},
	columns: [{
		text: 'ID',
		dataIndex: 'id'
	}, {
		text: 'პირადი N',
		dataIndex: 'personalNo',
		width: 120
	}, {
		text: 'სახელი',
		dataIndex: 'firstName',
		flex: 1
	}, {
		text: 'გვარი',
		dataIndex: 'lastName',
		flex: 1
	}, {
		text: 'ასაკი',
		dataIndex: 'age',
		flex: 1
	}]
});