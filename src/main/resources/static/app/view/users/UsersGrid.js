Ext.define('DE.view.users.UsersGrid',{
	extend : 'Ext.grid.Panel',
	title : 'მომხმარებლები',
	columns :[{
		text: 'ID',
		dataIndex: 'id'
	}, {
		text : 'მომხმარებლის სახელი',
		dataIndex : 'userName'
	}


	]

});