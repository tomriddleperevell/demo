Ext.define('DE.view.users.Main',{
	extend:"Ext.container.Container",
	title : "მომხმარებლები",

	items:[{
		reference : 'searchTab',
		xclass:'DE.view.users.SearchTab'
	},{
		reference:'grid',
		xclass:'DE.view.users.UsersGrid'
	}


	]


});