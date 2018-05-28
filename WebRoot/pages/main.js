function checkpwd(newpassword, flag) {
	//将密码转为大写
	var password = newpassword.toUpperCase();
	// 数字,特殊字符,大小写字母的匹配
	var boolean_num = false;
	var boolean_A = false;
	var boolean_a = false;
	var boolean_specialchar = false;
	for (var i = 0; i < newpassword.length; i++) {
		if (boolean_A == true && boolean_a == true && boolean_num == true
				&& boolean_specialchar == true)
			break;
		if (newpassword[i].charCodeAt() >= 65
				&& newpassword[i].charCodeAt() <= 90) {
			boolean_A = true;
			continue;
		}
		if (newpassword[i].charCodeAt() >= 48
				&& newpassword[i].charCodeAt() <= 57) {
			boolean_num = true;
			continue;
		}
		if (newpassword[i].charCodeAt() >= 97
				&& newpassword[i].charCodeAt() < 122) {
			boolean_a = true;
			continue;
		}
		if ((newpassword[i].charCodeAt() >= 33 && newpassword[i].charCodeAt() <= 47)
				|| (newpassword[i].charCodeAt() >= 58 && newpassword[i]
						.charCodeAt() <= 64)
				|| (newpassword[i].charCodeAt() >= 91
						&& newpassword[i].charCodeAt() && newpassword[i]
						.charCodeAt() <= 96)
				|| (newpassword[i].charCodeAt() >= 123 && newpassword[i]
						.charCodeAt() <= 127)) {
			boolean_specialchar = true;
			continue;
		}
	}
	if (boolean_A == false || boolean_a == false || boolean_num == false
			|| boolean_specialchar == false) {
		if (flag == 0)
			alert("密码应该是数字、大小写字母、特殊字符的组合,请重新输入");
		if (flag == 1)
			alert("确认密码应该是数字、大小写字母、特殊字符的组合,请重新输入");
		return false;
	}
	// admin,root,password的匹配
	if (password.indexOf("ADMIN") >= 0 || newpassword.indexOf("ROOT") >= 0
			|| password.indexOf("PASSWORD") >= 0) {
		if (flag == 0)
			alert("密码中不能包含admin、root、password（不区分大小写）");
		if (flag == 1)
			alert("确认密码中不能包含admin、root、password（不区分大小写）");
		return false;
	}

	// 键盘三个连续字符(包括三个连续数字)的匹配(横向、大小写、shift)
	var s_1_1 = "`1234567890-=";
	var s_1_2 = "qwertyuiop[]\\";
	var s_1_3 = "asdfghjkl;'";
	var s_1_4 = "zxcvbnm,./";
	var s_2_1 = "`1234567890-=";
	var s_2_2 = "QWERTYUIOP[]\\";
	var s_2_3 = "ASDFGHJKL;'";
	var s_2_4 = "ZXCVBNM,./";
	var s_3_1 = "~!@#$%^&*()_+";
	var s_3_2 = "QWERTYUIOP{}|";
	var s_3_3 = "ASDFGHJKL:\"";
	var s_3_4 = "ZXCVBNM<>?";
	var boolean = true;
	var boolean_nums = false;
	for (var n = 0; n < (newpassword.length - 2); n++) {
		var char = newpassword[n] + newpassword[n + 1] + newpassword[n + 2];
		if (char >= 0 && char <= 999) {
			boolean_nums = true;
			break;
		}
		if ((s_1_1.indexOf(char) >= 0) || (s_1_2.indexOf(char) >= 0)
				|| (s_1_3.indexOf(char) >= 0) || (s_1_4.indexOf(char) >= 0)
				|| (s_2_1.indexOf(char) >= 0) || (s_2_2.indexOf(char) >= 0)
				|| (s_2_3.indexOf(char) >= 0) || (s_2_4.indexOf(char) >= 0)
				|| (s_3_1.indexOf(char) >= 0) || (s_3_2.indexOf(char) >= 0)
				|| (s_3_3.indexOf(char) >= 0) || (s_3_4.indexOf(char) >= 0)) {
			boolean = false;
			break;
		}
	}
	if (boolean_nums == true) {
		if (flag == 0)
			alert("密码中连续数字不能超过三个");
		if (flag == 1)
			alert("确认密码中连续数字不能超过三个");
		return false;
	}
	if (boolean == false) {
		if (flag == 0)
			alert("密码中键盘顺序字符不得超过三个,请重新输入");
		if (flag == 1)
			alert("确认密码中键盘顺序字符不得超过三个,请重新输入");
		return false;
	}
	return true;
}

function Map() {// 模拟java中map的各种方法
	this.elements = new Array();

	// 获取Map元素个数
	this.size = function() {
		return this.elements.length;
	},

	// 判断Map是否为空
	this.isEmpty = function() {
		return (this.elements.length < 1);
	},

	// 删除Map所有元素
	this.clear = function() {
		this.elements = new Array();
	},

	// 向Map中增加元素（key, value)
	this.put = function(_key, _value) {
		if (this.containsKey(_key) == true) {
			if (this.containsValue(_value)) {
				if (this.remove(_key) == true) {
					this.elements.push({
						key : _key,
						value : _value
					});
				}
			} else {
				this.elements.push({
					key : _key,
					value : _value
				});
			}
		} else {
			this.elements.push({
				key : _key,
				value : _value
			});
		}
	},

	// 删除指定key的元素，成功返回true，失败返回false
	this.remove = function(_key) {
		var bln = false;
		try {
			for (var i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	},

	// 获取指定key的元素值value，失败返回null
	this.get = function(_key) {
		try {
			for (var i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					return this.elements[i].value;
				}
			}
		} catch (e) {
			return null;
		}
	},

	// 获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null
	this.element = function(_index) {
		if (_index < 0 || _index >= this.elements.length) {
			return null;
		}
		return this.elements[_index];
	},

	// 判断Map中是否含有指定key的元素
	this.containsKey = function(_key) {
		var bln = false;
		try {
			for (var i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	},

	// 判断Map中是否含有指定value的元素
	this.containsValue = function(_value) {
		var bln = false;
		try {
			for (var i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	},

	// 获取Map中所有key的数组（array）
	this.keys = function() {
		var arr = new Array();
		for (var i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].key);
		}
		return arr;
	},

	// 获取Map中所有value的数组（array）
	this.values = function() {
		var arr = new Array();
		for (var i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].value);
		}
		return arr;
	};
}
