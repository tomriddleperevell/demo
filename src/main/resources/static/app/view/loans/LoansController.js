Ext.define('DE.view.loans.LoansController', {
	extend: 'Ext.app.ViewController',

	resetSearchForm: function () {
		var me = this;
		var form = me.lookup('loanSearchForm');
		form.reset();
	},

	searchLoans: function () {
		var me = this;
		var form = me.lookup('loanSearchForm');
		me.getStore('loansStore').load({
			params: form.getForm().getValues()
		});
	}

});