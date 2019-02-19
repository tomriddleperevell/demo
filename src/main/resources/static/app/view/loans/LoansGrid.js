Ext.define('DE.view.loans.LoansGrid', {
	extend: 'Ext.grid.Panel',
	bind: {
		store: '{loansStore}'
	},
	columns: [{
		text: 'ID',
		dataIndex: 'id',
		flex: 1
	}, {
		text: 'სრული თანხა',
		dataIndex: 'totalAmount',
		flex: 1
	}, {
		text: 'დარჩენილი თანხა',
		dataIndex: 'leftAmount',
		flex: 1
	}, {
		text: 'სერვისი',
		dataIndex: 'serviceName',
		flex: 1
	}, {
		text: 'მომხმარებლის აიდი',
		dataIndex: 'customerId',
		flex: 1
	}]
});