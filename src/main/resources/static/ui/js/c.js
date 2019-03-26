//需要抓取的节点要有类似的类名和属性，例：<div class="fetch-XXX" data-fetch-type="ad" data-fetch-id="333">ad</div>
//类名fetch为必须的前缀，防止类名带样式
var #alasin#Counter = {
	api: {
		log: function (entity_type, entity_id, action, output_chanel, other, title, medium) {
			var _postData = {
				'entity_type': entity_type,
				'entity_id': entity_id,
				'action': action,
				"entity_title": title,
				"medium": medium || ''
			};
			if (output_chanel) {
				_postData["output_channel"] = output_chanel;
			}
			#alasin#Counter.otherData(other, _postData);
			#alasin#Counter.fetchData(_postData);
		}
	},
	common: {
		getCookie: function (cname) {
			if (cname && escape(cname).indexOf("%u") > 0) {
				cname = encodeURIComponent(cname);
			}
			var name = cname + "=";
			var ca = document.cookie.split(';');
			//var valueList = [];
			for (var i = 0; i < ca.length; i++) {
				var c = ca[i].trim();
				if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
			}
			return "";
		},
		setCookie: function (cname, cvalue, span, mainDomain) {
			if (cname && /[\u4e00-\u9fa5]/.test(cname)) {
				cname = encodeURIComponent(cname);
			}
			var d = new Date();
			d.setTime(d.getTime() + span);
			var expires = "expires=" + d.toGMTString();
			if (!/(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)/.test(location.host) && mainDomain) {
				var domain = "; domain=." + location.host.split(".").slice(1).join(".") + ";path=/";
				document.cookie = cname + "=" + cvalue + "; " + expires + domain;
			} else {
				document.cookie = cname + "=" + cvalue + "; " + expires;
			}
		},
		getDevice: function () {
			var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/MicroMessenger/i)) {
				return #alasin#Counter.config.KEY.WX;//微信浏览器
			}
			else if (ua.match(/#alasin#app/i)) {
				return #alasin#Counter.config.KEY.APP;//App
			}
			else if (ua.match(/(iphone|ipod|android|ios|symbianos)/i)) {
				return #alasin#Counter.config.KEY.M;//手机浏览器
			}
			else {
				return #alasin#Counter.config.KEY.PC; //PC浏览器
			}
		},
		getuuid: function () {
			if (typeof crypto != 'undefined') {
				return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, function (c) {
					return (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16);
				})
			} else {
				return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
					var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
					return v.toString(16);
				});
			}
		},
		require: function (url, func) {
			if (url) {
				if (url.indexOf(".js") > 0) {
					var isExist = false;
					if (typeof func !== "function") {
						func = function () { };
					}
					for (var i = 0; i < document.scripts.length; i++) {
						var src = document.scripts[i].src.toLowerCase();
						if (src.indexOf(url.toLowerCase()) > -1) {
							isExist = true;
						}
					}
					if (!isExist) {
						var head = document.getElementsByTagName('head')[0],
							js = document.createElement('script');
						js.type = "text/javascript";
						js.src = url + "?v=" + #alasin#Counter.common.getVersion();
						head.appendChild(js);
						js.onload = function () {
							func();
						}
					} else {
						func();
					}
				}
			}
		},
		getVersion: function () {
			var Version = 1.0;
			for (var i = 0; i < document.scripts.length; i++) {
				var src = document.scripts[i].src.toLowerCase();
				if (src.indexOf("/c.js") > -1) {
					var ps = src.split('/c.js?');
					if (ps.length > 1) {
						ps = ps[1].split('&')
						for (var j = 0; j < ps.length; j++) {
							var pps = ps[j];
							if (pps.indexOf('v=') > -1) {
								var ppps = pps.split('=');
								if (ppps.length > 1) {
									Version = ppps[1];
								}
							}
						}
					}
				}
			}
			return Version;
		},
		getSource: function () {
			var result = "直接访问";
			if (document.referrer) {
				var aElement = document.createElement("a");
				aElement.href = document.referrer;
				if (aElement.host == "mp.weixin.qq.com") {
					result = "公众号";
				} else {
					var hostChar = aElement.host.split('.');
					if (hostChar.length > 2) {
						result = hostChar[hostChar.length - 2];
					} else if (hostChar.length > 1) {
						result = hostChar[hostChar.length - 1];
					}
					if (result == "#alasin#") {
						var name = #alasin#Counter.common.getFirstPathName(aElement.pathname);
						result = #alasin#Counter.common.getSystem(name, "站内来源");
					} else if (result == "ccee") {
						result = "CCEE_Web";
					} else {
						result = aElement.host;
					}
				}
			}
			return result;
		},
		getFirstPathName: function (pathname) {
			if (!pathname) pathname = location.pathname;
			var list = pathname.split('/');
			var name = "";
			if (list.length > 1) {
				name = list[1].toLowerCase();
			}
			return name;
		},
		getSystem: function (name, defaultResult) {
			if (!defaultResult) defaultResult = "首页";
			var result = defaultResult;
			if (!name) name = #alasin#Counter.common.getFirstPathName();
			if (name) {
				if (#alasin#Counter.config.system[name]) {
					result = #alasin#Counter.config.system[name];
				} else result = name;
			}
			return result;
		},
		isNumeric: function (n) {
			return !isNaN(parseFloat(n)) && isFinite(n);
		}
	},
	fetchData: function (_postData) {//立即发送
		_postData = $.extend(#alasin#Counter.data(), _postData);
		if (!_postData.entity_id) {
			#alasin#Counter.debugMsg('id为空不发送');
			return false;
		}
		//Cookie未过期则无法再发送相同日志请求
		var key = _postData.entity_type + "_" + _postData.entity_id + "_" + _postData.action;
		if (#alasin#Counter.common.getCookie(key) == "") {
			#alasin#Counter._core._sendData(_postData);
			#alasin#Counter.debugMsg("api交互");
			#alasin#Counter.common.setCookie(key, 1, #alasin#Counter.config.cookieTimeOut);
		} else {
			#alasin#Counter.debugMsg(#alasin#Counter.config.cookieTimeOut + "毫秒内无法发送相同请求" + key);
		}
	},
	pushData: function (_postData) {//打包发送
		//Cookie未过期则无法再发送相同日志请求
		_postData = $.extend(#alasin#Counter.data(), _postData);
		if (!_postData.entity_id) {
			#alasin#Counter.debugMsg('id为空打包');
			return false;
		}
		var key = _postData.entity_type + "_" + _postData.entity_id + "_" + _postData.action;
		if (#alasin#Counter.common.getCookie(key) == "") {
			#alasin#Counter._core._cache.push(JSON.stringify(_postData));
			#alasin#Counter.debugMsg("api交互");
			#alasin#Counter.common.setCookie(key, 1, #alasin#Counter.config.cookieTimeOut);
		} else {
			#alasin#Counter.debugMsg(#alasin#Counter.config.cookieTimeOut + "毫秒内无法发送相同请求" + key);
		}
	},
	_core: {
		_cache: [],
		_popCache: function () {
			var tmp = [];
			var c = #alasin#Counter._core._cache;
			while (c.length > 0) {
				tmp.push(c.pop());
			}//另存数据 避免并发
			if (tmp.length > 0) {
				#alasin#Counter._core._cors(tmp);
			}
		},
		_sendData: function (data) {
			var datas = [];
			datas.push(JSON.stringify(data));
			#alasin#Counter._core._cors(datas);
		},
		_cors: function (datas, success) {//默认有Cors
			try {
				var data = { 'txt': datas, 'uid': #alasin#Counter.uid };
				if (#alasin#Counter.gid && !#alasin#Counter.common.isNumeric(#alasin#Counter.gid)) {
					data.gid = #alasin#Counter.gid;
				}
				$.ajax({
					type: "POST",
					url: #alasin#Counter.config.apiUrl,
					data: data,
					success: function (json) {
						#alasin#Counter.debugMsg('success');
						if (success && typeof success == "function") success();
					},
					error: function () {
						#alasin#Counter._core._josnp(datas, success);
						#alasin#Counter.debugMsg('fail');
					}
				});
			}
			catch (ex) {
				#alasin#Counter.debugMsg(ex.message);
				#alasin#Counter._core._josnp(datas, success);
				#alasin#Counter.errLog(ex, 3);
			}
		},
		_josnp: function (datas, success) {//不支持cors 则使用jsonp  长度有限制数据量大会造成部分 present/view丢失
			try {
				var data = { 'txt': datas, 'uid': #alasin#Counter.uid };
				if (#alasin#Counter.gid && !#alasin#Counter.common.isNumeric(#alasin#Counter.gid)) {
					data.gid = #alasin#Counter.gid;
				}
				$.ajax({
					type: "GET",
					url: #alasin#Counter.config.apiUrl,
					dataType: "JSONP",
					data: data,
					success: function (json) {
						#alasin#Counter.debugMsg('success');
						if (success && typeof success == "function") success();
					},
					error: function () {
						#alasin#Counter.debugMsg('fail');
					}
				});
			}
			catch (ex) {
				#alasin#Counter.debugMsg(ex.message);
				#alasin#Counter._core._josnp(datas);
				#alasin#Counter.errLog(ex, 3);
			}
		}
	},
	bind: function () {
		#alasin#Counter.openSession();
		var config = #alasin#Counter.config;
		if ($(config.viewTag).length != 0) {//view 一个页面一个
			var _postData = {
				entity_type: $(config.viewTag).attr(config.typeAttr),
				entity_id: $(config.viewTag).attr(config.idAttr),
				entity_title: $(config.viewTag).attr(config.titleAttr),
				medium: $(config.viewTag).attr(config.mediumAttr),
				action: $(config.viewTag).attr(config.actionAttr) || 'view',
				create_time: new Date().getTime()
			};
			try {
				#alasin#Counter.otherData($(config.viewTag).attr(config.otherAttr), _postData);
				#alasin#Counter.fetchData(_postData);
				#alasin#Counter.debugMsg(_postData);
			} catch (ex) { #alasin#Counter.errLog(ex, 1, _postData.action); }
		}

		if ($(config.clickTag).length > 0) {
			#alasin#Counter.dynamicevent(config.clickTag, "click");
		}
		if ($(config.presentTag).length > 0) {
			#alasin#Counter.dynamicevent(config.presentTag, "present", "inView", "push");
		}
		if ($(config.readContainer).length > 0) {
			#alasin#Counter.dynamicevent(config.readContainer, "read", "overView", "push");
		}

		$(config.presentTag + ',' + config.readContainer).on('checkView', function () {
			var a = $(this).offset().top;
			if (a >= $(window).scrollTop() && a < ($(window).scrollTop() + $(window).height())) {
				#alasin#Counter.debugMsg("div在可视范围");
				$(this).trigger('inView');
			}
			//文章内容页判断是否读完
			if ($(this).hasClass(config.readContainer.substring(1))) {
				if ($(window).scrollTop() + $(window).height() > $(this).height() + $(this).offset().top) {
					#alasin#Counter.debugMsg('div已经全部显示');
					$(this).trigger('overView');
				}
			}
			#alasin#Counter._core._popCache();//数据全部提交
		});
		var tId;
		$(window).scroll(function () {
			clearTimeout(tId);
			tId = setTimeout(function () {
				$(config.presentTag + ',' + config.readContainer).trigger('checkView');
			}, 500);
		});
		//页面记录
		var pageData = #alasin#Counter.data();
		pageData.entity_type = "page";
		pageData.entity_id = location.href;
		pageData.action = "page_view";
		delete pageData.var_url;
		#alasin#Counter._core._sendData(pageData);
	},
	//动态绑定,js加载DOM元素时使用
	dynamicbind: function ($this, array) {
		array = array || ["click", "inview"];
		var config = #alasin#Counter.config;
		if (typeof array != "undefined" && $.isArray(array)) {
			for (var l = array.length; l > 0; l--) {
				var tag = array[l - 1].toLowerCase();
				switch (tag) {
					case "click":
						#alasin#Counter.dynamicevent($this, "click");
						break;
					case "inview":
						#alasin#Counter.dynamicevent($this, "present", "inView", "push");
						$this.on('checkView', function () {
							var a = $(this).offset().top;
							if (a >= $(window).scrollTop() && a < ($(window).scrollTop() + $(window).height())) {
								#alasin#Counter.debugMsg("dbind div在可视范围");
								$(this).trigger('inView');
							}
							#alasin#Counter._core._popCache();//数据全部提交
						});
						break;
					case "overview":
						#alasin#Counter.dynamicevent($this, "read", "overView", "push");
						$this.on('checkView', function () {
							if ($(this).hasClass(config.readContainer.substring(1))) {
								if ($(window).scrollTop() + $(window).height() > $(this).height() + $(this).offset().top) {
									#alasin#Counter.debugMsg('dbind div已经全部显示');
									$(this).trigger('overView');
								}
							}
							#alasin#Counter._core._popCache();//数据全部提交
						});
						break;
				}
			}
		}
	},
	//动态绑定事件
	//acion,行为
	//event,默认为click
	//type,请求类型，值有fetch和push，默认为fetch
	dynamicevent: function ($this, action, event, type) {
		var config = #alasin#Counter.config;
		type = type || "fetch";
		event = event || "click";
		/****  旧版传的是jquery元素对象 兼容旧版 如果发现 可以改成新版 可直接传元素class   ***/
		if (typeof $this === 'string') {
			$(document).on(event, $this, function () {
				if (action == "present" && $(this).attr(config.typeAttr) == "article") {
					return;
				}
				var str_entity_id = $(this).attr(config.idAttr);
				var _postData = {
					entity_type: $(this).attr(config.typeAttr),
					entity_id: str_entity_id,
					entity_title: $(this).attr(config.titleAttr),
					medium: $(this).attr(config.mediumAttr),
					action: $(this).attr(config.actionAttr) || action
				};
				try {
					#alasin#Counter.otherData($(this).attr(config.otherAttr), _postData);
					switch (type) {
						case "fetch":
							#alasin#Counter.fetchData(_postData);
							break;
						case "push":
							#alasin#Counter.pushData(_postData);
							break;
					}
					#alasin#Counter.debugMsg(_postData);
				} catch (ex) { #alasin#Counter.errLog(ex, 1, _postData.action); }
			});
		}
		else {
			$this.on(event, function () {
				if (action == "present" && $(this).attr(config.typeAttr) == "article") {
					return;
				}
				var str_entity_id = $(this).attr(config.idAttr);
				var _postData = {
					entity_type: $(this).attr(config.typeAttr),
					entity_id: str_entity_id,
					entity_title: $(this).attr(config.titleAttr),
					medium: $(this).attr(config.mediumAttr),
					action: $(this).attr(config.actionAttr) || action
				};
				try {
					#alasin#Counter.otherData($(this).attr(config.otherAttr), _postData);
					switch (type) {
						case "fetch":
							#alasin#Counter.fetchData(_postData);
							break;
						case "push":
							#alasin#Counter.pushData(_postData);
							break;
					}
					#alasin#Counter.debugMsg(_postData);
				} catch (ex) { #alasin#Counter.errLog(ex, 1, _postData.action); }
			});
		}

	},
	//其他数据
	//格式为otherKey1=otherValue1;otherKey2=otherValue2
	otherData: function (otherData, postData) {
		if (otherData) {
			otherData = otherData.split(';');
			for (var i = 0; i < otherData.length; i++) {
				var params = otherData[i].split('=');
				if (params.length > 1) {
					var value = params[1];
					if (value) {
						postData["var_" + params[0]] = value;
					}
				}
			}
		}
	},
	debugMsg: function (msg) {
		if (msg && #alasin#Counter.config.debug) console.log(msg);
	},
	errLog: function (ex, level, action) {
		if (#alasin#Counter.logger) {
			#alasin#Counter.logger.push("data_type", "error");
			if (ex) {
				#alasin#Counter.logger.push("stack", ex.stack.toString());
				#alasin#Counter.logger.push("err_message", ex.message);
			}
			#alasin#Counter.logger.push("err_level", level);
			#alasin#Counter.logger.push("device_type", #alasin#Counter.device_type);
			#alasin#Counter.logger.push("create_time", new Date().getTime());
			if (action) #alasin#Counter.logger.push("err_action", action);
			#alasin#Counter.logger.push("user_agent", navigator.userAgent);
			#alasin#Counter.logger.push("url", location.href);
			#alasin#Counter.logger.push("user_id", (#alasin#Counter.uid || #alasin#Counter.guestId));
			#alasin#Counter.logger.push("page_system", #alasin#Counter.common.getSystem());
			#alasin#Counter.logger.logger();
		}
	},
	infoLog: function (msg) {
		if (#alasin#Counter.logger && msg) {
			#alasin#Counter.logger.push("data_type", "info");
			#alasin#Counter.logger.push("message", msg);
			#alasin#Counter.logger.push("device_type", #alasin#Counter.device_type);
			#alasin#Counter.logger.push("create_time", new Date().getTime());
			#alasin#Counter.logger.push("user_agent", navigator.userAgent);
			#alasin#Counter.logger.push("url", location.href);
			#alasin#Counter.logger.push("user_id", (#alasin#Counter.uid || #alasin#Counter.guestId));
			#alasin#Counter.logger.push("page_system", #alasin#Counter.common.getSystem());
			#alasin#Counter.logger.logger();
		}
	},
	config: {
		presentTag: '.fetch-present',//present
		clickTag: '.fetch-click',//click
		readContainer: '.fetch-read',//read
		viewTag: '.fetch-view',//view
		actionAttr: 'data-fetch-action',//自定义动作 默认 click为click、read为read、view为view、present为present 
		typeAttr: 'data-fetch-type',
		idAttr: 'data-fetch-id',
		titleAttr: 'data-fetch-title',//title
		mediumAttr: 'data-fetch-medium',//位置
		otherAttr: 'data-fetch-other',
		presentAttr: 'data-present',//控制是否收集
		apiUrl: location.protocol + '//api2.#alasin#/tongji/api/fetch/ajax',
		userCookieKey: 'PassPort_Token',
		ssoCookieKey: '#alasin#_Token',
		cookieTimeOut: 2 * 60 * 1000,//2分钟
		guestCookieKey: 'Guest_Token',
		debug: location.hash.indexOf("debug") > 0,
		//浏览器指纹key
		fingerKey: 'Finger_Token',
		//获取cookie接口地址
		cookieUrl: function () {
			var result = location.protocol + '//#alasin#/tools/getcookie';
			if (#alasin#Counter.config.debug) {
				result = location.protocol + '//test.#alasin#/tools/getcookie';
			} else if (#alasin#Counter.common.getDevice() != #alasin#Counter.config.KEY.PC) {
				result = location.protocol + '//m.#alasin#/tools/getcookie';
			}
			return result;
		},
		//游客过期时间
		guestTimeOut: 365 * 24 * 60 * 60 * 1000,
		//首次打开过期时间
		openTimeOut: 24 * 60 * 60 * 1000,
		openKey: "Open_Session",
		KEY: {
			PC: "pc",
			M: "mweb",
			APP: "app",
			WX: "wechat"
		},
		preGuest: "guest_",
		system: {
			"yuke": "雨课",
			"article": "资讯",
			"ask": "问答",
			"theme": "专题",
			"live": "直播",
			"vc": "培训",
			"active": "活动",
			"enroll": "表单",
			"vote": "投票",
			"ccee": "CCEE_Web"
		}
	},
	data: function () {
		var _data = #alasin#Counter.commonData();
		_data.entity_type = "";
		_data.entity_id = "";
		_data.action = "";
		_data.entity_title = "";
		return _data;
	},
	//公共数据
	commonData: function () {
		var _data = {
			data_type: "event",
			var_url: location.href,
			var_referrer: document.referrer,
			device_type: #alasin#Counter.device_type,
			create_time: new Date().getTime(),
			var_finger: #alasin#Counter.finger,
			source: #alasin#Counter.common.getSource(),
			var_ad_blocking: typeof _hmt === "undefined" || _hmt instanceof Array,
			var_user_agent: navigator.userAgent
		};
		if (#alasin#Counter.guestId && #alasin#Counter.uid && #alasin#Counter.uid.indexOf(#alasin#Counter.config.preGuest) == -1) {
			_data.var_guestId = #alasin#Counter.guestId;
		}
		if (#alasin#Counter.gid && #alasin#Counter.common.isNumeric(#alasin#Counter.gid)) {
			_data.gid = #alasin#Counter.gid;
		}
		return _data;
	},
	openSession: function () {
		if (!#alasin#Counter.common.getCookie(#alasin#Counter.config.openKey)) {
			var _data = #alasin#Counter.commonData();
			_data.action = "open_session";
			var json = JSON.stringify(_data);
			#alasin#Counter._core._cors(json, function () {
				var now = new Date();
				var end = new Date(now.getFullYear(), now.getMonth(), now.getDate() + 1);
				#alasin#Counter.common.setCookie(#alasin#Counter.config.openKey, new Date().getTime(), (end.getTime() - now.getTime()), 1);
			});
		}
	},
	device_type: "",
	uid: '',
	gid: '',
	//浏览器指纹
	finger: '',
	//游客id
	guestId: '',
	fingerInit: function () {
		var _finger = localStorage.getItem(#alasin#Counter.config.fingerKey);
		if (_finger) {
			#alasin#Counter.finger = _finger;
		} else {
			#alasin#Counter.common.require("//static.#alasin#/plug/fp.min.js", function () {
				if (typeof Fingerprint2 == "function") {
					new Fingerprint2().get(function (result, components) {
						#alasin#Counter.finger = result;
						localStorage.setItem(#alasin#Counter.config.fingerKey, result);
					});
				}
			});
		}
	},
	//日志记录器
	logger: null,
	//初始化日志记录器
	loggerInit: function () {
		function createHttpRequest() {
			if (window.XMLHttpRequest) { return new XMLHttpRequest(); } else if (window.ActiveXObject) { return new ActiveXObject("Microsoft.XMLHTTP"); }
		}
		function AliLogTracker(host, project, logstore) {
			this.uri_ = location.protocol + '//' + project + '.' + host + '/logstores/' + logstore + '/track?APIVersion=0.6.0';
			this.params_ = new Array();
			this.httpRequest_ = createHttpRequest();
		}
		AliLogTracker.prototype = {
			push: function (key, value) {
				if (!key || !value) {
					return;
				}
				this.params_.push(key);
				this.params_.push(value);
			},
			logger: function () {
				var url = this.uri_;
				var k = 0;
				while (this.params_.length > 0) {
					if (k % 2 == 0) {
						url += '&' + encodeURIComponent(this.params_.shift());
					} else {
						url += '=' + encodeURIComponent(this.params_.shift());
					}
					++k;
				}
				try {
					this.httpRequest_.open("GET", url, true);
					this.httpRequest_.send(null);
				} catch (ex) {
					if (window && window.console && typeof window.console.log === 'function') {
						console.log("Failed to log to ali log import because of this exception:\n" + ex);
						console.log("Failed log data:", url);
					}
				}

			}
		};
		#alasin#Counter.logger = new AliLogTracker("cn-hangzhou.log.aliyuncs.com", "#alasin#", "web_fe");
	},
	setGuestUser: function () {
		var _guestFunc = function (visitor) {
			var _visitor = null;
			if (visitor) {
				_visitor = visitor;
			} else {
				_visitor = #alasin#Counter.common.getuuid();
			}
			if (_visitor.indexOf(#alasin#Counter.config.preGuest) != 0) {
				_visitor = #alasin#Counter.config.preGuest + _visitor;
			}
			#alasin#Counter.common.setCookie(#alasin#Counter.config.guestCookieKey, _visitor, #alasin#Counter.config.guestTimeOut);
			#alasin#Counter.uid = _visitor;
			#alasin#Counter.guestId = _visitor;
			#alasin#Counter.bind();
		};
		var _fetchGuest = function () {
			try {
				$.ajax({
					type: "GET",
					url: #alasin#Counter.config.cookieUrl(),
					dataType: "JSONP",
					data: { key: #alasin#Counter.config.guestCookieKey, set: true, expire: #alasin#Counter.config.guestTimeOut },
					success: function (json) {
						if (json.result_code == 1 && json.data) {
							_guestFunc(json.data);
						} else {
							_guestFunc();
						}
					},
					error: function () {
						_guestFunc();
					}
				});
			}
			catch (ex) {
				_guestFunc();
				#alasin#Counter.errLog(ex, 3);
			}
		}
		if (location.origin.indexOf("ccee.com") > 0) {
			try {
				$.ajax({
					type: "GET",
					url: #alasin#Counter.config.cookieUrl(),
					dataType: "JSONP",
					data: { key: #alasin#Counter.config.userCookieKey },
					success: function (json) {
						if (json.result_code == 1 && json.data) {
							if (json.data.length < 32) {
								#alasin#Counter.common.setCookie(#alasin#Counter.config.userCookieKey, json.data, #alasin#Counter.config.guestTimeOut);
								#alasin#Counter.uid = json.data;
								#alasin#Counter.bind();
							} else {
								_fetchGuest();
							}
						} else {
							_fetchGuest();
						}
					},
					error: function () {
						_fetchGuest();
					}
				});
			} catch (ex) {
				_fetchGuest();
				#alasin#Counter.errLog(ex, 3);
			}
		} else {
			_fetchGuest();
		}
	},
	init: function (config) {
		#alasin#Counter.fingerInit();
		if (config) {
			#alasin#Counter.config = $.extend(#alasin#Counter.config, config);
		}
		#alasin#Counter.device_type = #alasin#Counter.common.getDevice();
		#alasin#Counter.gid = #alasin#Counter.common.getCookie(#alasin#Counter.config.ssoCookieKey);
		var uid = #alasin#Counter.common.getCookie(#alasin#Counter.config.userCookieKey);
		if (uid) {
			#alasin#Counter.uid = uid;
			#alasin#Counter.guestId = #alasin#Counter.common.getCookie(#alasin#Counter.config.guestCookieKey);
			#alasin#Counter.bind();
		}
		else {
			if (#alasin#Counter.common.getDevice() == #alasin#Counter.config.KEY.APP) {
				#alasin#Counter.common.require("//static.#alasin#/common/js/app.js", function () {
					try {
						app.addReady(function () {
							#alasin#App.Config(JSON.stringify({
								Oauth: [
									"getUnionId",
									"getVipId",
									"getAppUserId"
								]
							}));
						});
						app.addComplete(function () {
							var uid = "";
							if (typeof #alasin#App.getAppUserId == "function") {
								uid = #alasin#App.getAppUserId();
							}
							if (uid) {
								#alasin#Counter.uid = "app_" + uid;
							} else {
								#alasin#Counter.uid = #alasin#App.getUnionId();
							}
							#alasin#Counter.device_type = "app";
							if (typeof #alasin#Counter.getVipId == "function") #alasin#Counter.gid = #alasin#App.getVipId();
							#alasin#Counter.bind();
						});
					} catch (ex) {
						#alasin#Counter.debugMsg(ex.message);
						#alasin#Counter.errLog(ex, 3);
					}
				});
			} else {//添加游客记录
				var _visitor = #alasin#Counter.common.getCookie(#alasin#Counter.config.guestCookieKey);
				if (!_visitor) {
					#alasin#Counter.setGuestUser();
				} else {
					if (_visitor.indexOf(#alasin#Counter.config.preGuest) != 0) {
						_visitor = #alasin#Counter.config.preGuest + _visitor;
						#alasin#Counter.common.setCookie(#alasin#Counter.config.guestCookieKey, _visitor, #alasin#Counter.config.guestTimeOut);
					}
					#alasin#Counter.uid = _visitor;
					#alasin#Counter.guestId = _visitor;
					#alasin#Counter.bind();
				}
			}
		}
	},
	changeConfig: function (config) {
		if (config) { #alasin#Counter.config = $.extend(#alasin#Counter.config, config); }
	}
}
$(document).ready(function () {
	#alasin#Counter.init();
});
#alasin#Counter.loggerInit();
window.onerror = function (msg, url, lineNo, columnNo, error) {
	if (!error) {
		error = new Error();
		error.message = msg;
		error.stack = url + ":" + lineNo;
	}
	#alasin#Counter.errLog(error, 2);
	return false;
};
window.onunload = function () {
	#alasin#Counter._core._popCache();//数据全部提交
}