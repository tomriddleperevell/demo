Ext.define('DE.view.loans.Main', {
	extend: 'Ext.container.Container',
	title: 'სესხები',
	controller: {
		xclass: 'DE.view.loans.LoansController'
	},
	/*viewModel: {
		stores: {
			loansStore: {
				xclass: 'DE.store.loans.Loans'
			}
		}
	},*/
	//html: '<h1 align="center" > "loan search page" </h1>',
	layout: {
		type: 'vbox',
		align: 'stretch'
	},
	items: [{

		xclass: 'DE.view.loans.SearchForm',
		reference: 'loanSearchForm'
	}, {
		// xtype: 'panel',
		// html: 'dsadasdsada',
		xclass: 'DE.view.loans.LoansGrid',
		flex: 1
	}]
});