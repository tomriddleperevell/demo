Ext.Ajax.setupUrl = function (opts, url) {
	var pathParams;
	if (opts.operation && opts.operation.pathParams) {
		pathParams = opts.operation.pathParams;
	} else {
		pathParams = opts.pathParams;
	}

	if (pathParams) {
		return buildURL(url, pathParams);
	}
	return url;
};

Ext.application({
	name: 'DE',
	appFolder: 'app',
	views:['MainView'],
	mainView: 'DE.view.MainView'
});