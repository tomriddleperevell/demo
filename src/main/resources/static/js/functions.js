function log() {
	console.log.apply(console, arguments);
}

function toUrl(params) {
	return Object.keys(params).map(function (key) {
		return key + '=' + params[key];
	}).join('&');
}

function request(options) {
	var me = this;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState === 4) {
			var resp;
			try {
				resp = JSON.parse(this.responseText);
			} catch (e) {
				console.error("failed parse response JSON");
				resp = this.responseText;
			}

			if (options.callback) {
				options.callback(resp);
			}
			if (this.status === 200) {
				if (options.success) {
					options.success(resp);
				}
			} else {
				if (options.failure) {
					options.failure(resp);
				}
			}
		}
	};
	if (!options.url) {
		throw "Url is required";
	}
	xhttp.open(options.method || "GET", options.url + '?' + toUrl(options.params), true);
	xhttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
	xhttp.send(JSON.stringify(options.data));
}