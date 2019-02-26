Ext.define('DE.view.customers.CustomerProfile', {
	extend: 'Ext.form.Panel',
	bodyPadding: 5,
	title: 'პროფილი',
	closable: true,
	reference: 'profilePage',


	viewModel: true,

	config: {
		customerId: null
	},
	listeners: {
		afterrender: 'onAfterRender'
	},


	items: [{
		xtype: 'toolbar',
		items: [
			{
				width: 100,
				xtype: 'button',
				text: 'წაშლა',
				handler: 'deleteCustomer',

			}, {
				width: 100,
				xtype: 'button',
				text: 'რედაქტირება',
				handler: 'updateCustomer',
			}]
	},

		{
			xtype: 'form',
			reference: 'customerProfileForm',
			items: [{
				xtype: 'displayfield',
				fieldLabel: 'ID',//	html: '<p id = "ddd"> wazaaaaaaaap </p>',
				// name: 'id'
				bind: '{customer.id}'
			}, {
				xtype: 'displayfield',
				fieldLabel: 'პირადი N',
				// name: 'personalNo',
				bind: '{customer.personalNo}',
				width: 120
			}, {
				xtype: 'displayfield',
				fieldLabel: 'სახელი',
				// name: 'firstName',
				bind: '{customer.firstName}',
				//flex: 1
			}, {
				xtype: 'displayfield',
				fieldLabel: 'გვარი',
				// name: 'lastName',
				bind: '{customer.lastName}',
				//flex: 1
			}, {
				xtype: 'displayfield',
				fieldLabel: 'ასაკი',
				// name: 'age',
				bind: '{customer.age}',
				//flex: 1
			}

			]
		}, {
			xtype: 'tabpanel',
			padding: 5,
			items: [{

				title: 'ფაილები',
				xtype: 'grid',
				listeners: {
					itemclick: "getSelectedFile",

				},
				tbar: [{

					xtype: 'button',
					text: 'წაშლა',
					handler: "fileDelete",

				}, {
					xtype: 'button',
					text: 'დამატება',
					handler: "fileAdd",

				}],

				layout: {
					type: 'hbox',
					align: 'stretch'
				},
				store: {

					xclass: 'DE.store.files.File'
				},

				reference: 'customerFilesGrid',
				columns: [{
					text: 'ფაილის ხახელი',
					dataIndex: 'fileName',
					flex: 1
				}],

			},
				{
					title: 'სესხები',
					xclass: 'DE.view.loans.LoansGrid',
					reference:"loansGrid",
					bind:{
						store:{
							data:'{customer.loans}'
						}
					}


				},


			]
		},


	],

	controller: {
		onAfterRender: function () {
			var me = this;
			var customerWin = me.getView();
			var customerId = customerWin.getCustomerId();

			var customerRec = Ext.create('DE.model.customers.Customer', {
				id: customerId
			});
			log(customerRec);
			me.getViewModel().set('customer', customerRec);
			me.getView().setLoading();
			customerRec.load({
				callback: function () {
					me.getView().setLoading(false);
					log("sdsdsd");
				},
				success: function (rec) {
					// me.getView().loadRecord(customerRec);
					log("customer record", customerRec);
					// var loans = customerRec.get('loans');
					// me.lookup('loansGrid').getStore().loadData(loans);

					// log(loans);
					log("succ");
				}
			});
			me.lookup('customerFilesGrid').getStore().load({
				pathParams: {
					customerId: customerId
				},
				callback: function () {
					//me.getView().setLoading(false);
				}
			});





		},

		deleteCustomer: function () {
			var me = this;
			var rec = me.getViewModel().get('customer');
			if (!rec) return;
			log(rec);

			Ext.Msg.confirm('გაფრხილება!', 'დაადასტურეთ წაშლა', function (ans) {
				if (ans === 'yes') {
					me.getView().setLoading();
					rec.erase({
						callback: function () {
							me.getView().setLoading(false);
							me.getView().close();
						},
						failure: function () {
							me.getStore('customersStore').reload();
						}
					});
				}
			});

		},
		updateCustomer: function (gridView, rec, row) {
			var me = this;
			var customerWin = Ext.create('DE.view.window.CustomerWindow', {
				customerRecord: me.getViewModel().get('customer'),
				animateTarget: me,
				modal: true
			});
			customerWin.show();
		},

		fileAdd: function () {
			var me = this;
			var customerWin = me.getView();
			var customerId = customerWin.getCustomerId();
			var window = Ext.create('Ext.window.Window', {


				items: [
					{
						xclass: "DE.view.customers.UploadFile",
						customerId: customerId,


					},
				],


			});

			window.show();


		},

		getSelectedFile: function (grid, record, item) {
			var me = this;
			log("in get selected");
			log(me);
			log(record);

			me.getViewModel().set('file', record);
		},

		fileDelete: function () {
			var me = this;
			var rec = me.getViewModel().get('file');
			if (!rec) return;
			log(rec);

			var customerId = me.getView().getCustomerId();

			Ext.Msg.confirm('გაფრხილება!', 'დაადასტურეთ წაშლა', function (ans) {
				if (ans === 'yes') {
					me.getView().setLoading();
					rec.erase({
						pathParams: {
							customerId: customerId
						},
						callback: function () {
							me.getView().setLoading(false);
							me.getView().close();
						},
						failure: function () {
							//me.getStore('customersStore').reload();
						}
					});
				}
			});


		}


	}

});