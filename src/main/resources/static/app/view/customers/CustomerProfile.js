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
			xtype : 'tabpanel',

			padding : 5,
			items : [
				{
					title : 'grid',
					xtype: 'grid',
					store: {

						xclass: 'DE.store.files.File'
					},
					reference: 'customerFilesGrid',
					columns: [{
						text: 'ფაილის ხახელი',
						dataIndex: 'fileName',
						flex: 1
					}]
				},
				{
					title : 'meore',
					xtype: 'grid',
					store: {

						xclass: 'DE.store.files.File'
					},

					columns: [{
						text: 'ფაილის ხახელი',
						dataIndex: 'fileName',
						flex: 1
					}]
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


			log(customerId);

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

			/*
		var rec = me.getViewModel().get('customer');
		me.getView().setLoading();
		rec.save({
			callback: function () {
				log("in update");
				me.getView().setLoading(false);
			},
			success: function () {
				me.getView().close();
			}
		});

		*/


		},
	}

});