function log() {
	console.log.apply(console, arguments);
}

function buildURL(url, params) {
	for (var i in params) {
		url = url.replace(new RegExp('{' + i + '}', 'g'), params[i]);
	}
	return url.replace('//', '/');
}

function request(options) {
	if (!options.url)
		return;
	options.method = options.method || 'POST';
	emptyString2Null(options.jsonData);
	// if (options.pathParams) {
	// 	options.url = buildURL(options.url, options.pathParams);
	// }
	var successFn = options.success;
	var callbackFn = options.callback;
	var failureFn = options.failure;
	options.callback = function (opts, success, response) {
		processResponse(response, callbackFn, success);
	};
	options.success = function (response) {
		processResponse(response, successFn, true);
	};
	options.failure = function (response) {
		processResponse(response, failureFn, false);
	};
	return Ext.Ajax.request(options);
}
