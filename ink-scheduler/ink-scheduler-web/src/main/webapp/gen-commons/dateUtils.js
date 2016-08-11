var DateFormat = function(date) {
	// implementation
	var format = function(str) {
		str = str.replace(/yyyy/g, date.getFullYear());
		str = str.replace(/yy/g, date.getFullYear().toString().slice(2));
		str = str.replace(/mm/g, addZero(date.getMonth() + 1));
		str = str.replace(/dd/g, addZero(date.getDate()));
		str = str.replace(/wk/g, date.getDay());
		str = str.replace(/hh/g, addZero(date.getHours()));
		str = str.replace(/mi/g, date.getMinutes());
		str = str.replace(/ss/g, addZero(date.getSeconds()));
		str = str.replace(/ms/g, date.getMilliseconds());
		return str;
	}
	var valueOf = function() {
	}
	var toString = function() {
		return date.toLocaleString();
	}
	// constructor
	date = new Date(date);
	if (!date || date == "NaN")
		date = new Date();
	// inteface
	this.format = format;
	this.valueOf = valueOf;
	this.toString = toString;
	
	var addZero = function addZero(tmp) {
		if(tmp<10) {
			return "0"+tmp;
		}
		return tmp;
	}
}

//返回年月日及星期()
function getCurDateStr(){
	var d = new Date();
	var week;
	switch (d.getDay()){
		case 1: week="星期一"; break;
		case 2: week="星期二"; break;
		case 3: week="星期三"; break;
		case 4: week="星期四"; break;
		case 5: week="星期五"; break;
		case 6: week="星期六"; break;
		default: week="星期天";
	}
	//var years = d.getYear();
	var years = d.getFullYear();
	var month = add_zero(d.getMonth()+1);
	var days = add_zero(d.getDate());
	//var hours = add_zero(d.getHours());
	//var minutes = add_zero(d.getMinutes());
	//var seconds=add_zero(d.getSeconds());
	var ndate = years+"年"+month+"月"+days+"日 "+" "+week;
	return ndate;
}

function add_zero(temp){
	if(temp<10) return "0"+temp;
	else return temp;
}

//返回时间区间(上午/下午/晚上)
function getCurHourZone(){
	var date = new Date(); 
	var h = date.getHours(); 
	var timeStr = (h <= 12 && h > 6) ? '上午' : ( (h < 18 && h > 12) ? '下午' : '晚上'); 
	return timeStr; 
}