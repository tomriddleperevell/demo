Ext.define('DE.view.MainView', {
	extend: 'Ext.container.Viewport',
	layout: 'fit',
	items: [{
		xtype: 'tabpanel',
		items: [{
			xclass: 'DE.view.main.Main'
		}, {
			xclass: 'DE.view.customers.Main',
			//title : 'კლიენტდსაები'
		}, {
			xtype: 'panel',
			title: 'კიდე სხვა ტაბი'
		}, {
			xclass: 'DE.view.loans.Main'
		},{

			xclass:'DE.view.users.Main',

		}]
	}]
});