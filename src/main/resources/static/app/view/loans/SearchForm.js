Ext.define( 'DE.view.loans.SearchForm',{
	extend : 'Ext.form.Panel',
	layout: 'hbox',
	fieldDefaults: {
		labelAlign: 'top',
		margin: 5
	},
	items : [
		{
			xtype : 'textfield',
			name : 'serviceName',
			fieldLabel : 'სერვისი'
		}, {
			xtype : 'numberfield',
			name : 'totalAmount',
			fieldLabel : 'სრული თანხა'
		} , {
			xtype : 'numberfield',
			name : 'leftAmount',
			fieldLabel : 'დარჩენილი თანხა'
		} , {
			xtype : 'numberfield',
			name : 'customerId',
			fieldLabel : 'მომხმარებლის აიდი'
		}
	],
	buttons : [
		{
			xtype : 'button',
			text : 'ძებნა',
			handler : 'searchLoans'
		} , {
			xtype : 'button',
			text : 'წაშლა',
			handler : 'resetSearchForm'
		}
	]

});