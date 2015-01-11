
// 澶囩敤jquery
!window.jQuery && document.write('<script src=http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.min.js><\/script>');
// 寮曞叆鐨勬彃浠� 寮€濮�
/*
* jquery pin
*/
(function(e) {
e.fn.pin = function(a) {
var g = 0,
h = [],
n = !1,
k = e(window),
a = a || {},
m = function() {
for (var i = 0, d = h.length; i < d; i++) {
var b = h[i];
if (a.minWidth && k.width() <= a.minWidth) b.parent().is(".pin-wrapper") && b.unwrap(), b.css({
width: "",
left: "",
top: "",
position: ""
}), a.activeClass && b.removeClass(a.activeClass), n = !0;
else {
n = !1;
var c = a.containerSelector ? b.closest(a.containerSelector) : e(document.body),
f = b.offset(),
j = c.offset(),
l = b.offsetParent().offset();
b.parent().is(".pin-wrapper") || b.wrap("<div class='pin-wrapper'>");
var g = e.extend({
top: 0,
bottom: 0
}, a.padding || {});
b.data("pin", {
pad: g,
from: (a.containerSelector ? j.top : f.top) - g.top,
to: j.top + c.height() - b.outerHeight() - g.bottom,
end: j.top + c.height(),
parentTop: l.top
});
b.css({
width: b.outerWidth()
});
b.parent().css("height", b.outerHeight())
}
}
},
o = function() {
if (!n) {
g = k.scrollTop();
for (var i = [], d = 0, b = h.length; d < b; d++) {
var c = e(h[d]),
f = c.data("pin");
if (f) {
i.push(c);
var j = f.from,
l = f.to;
j + c.outerHeight() > f.end ? c.css("position", "") : j < g && l > g ? ("fixed" != c.css("position") && c.css({
left: c.offset().left,
top: f.pad.top
}).css("position", "fixed"), a.activeClass && c.addClass(a.activeClass)) : g >= l ? (c.css({
left: "",
top: l - f.parentTop + f.pad.top
}).css("position", "absolute"), a.activeClass && c.addClass(a.activeClass)) : (c.css({
position: "",
top: "",
left: ""
}), a.activeClass && c.removeClass(a.activeClass))
}
}
h = i
}
},
p = function() {
m();
o()
};
this.each(function() {
var a = e(this),
d = e(this).data("pin") || {};
if (!d || !d.update) h.push(a), e("img", this).one("load", m), d.update = p, e(this).data("pin", d)
});
k.scroll(o);
k.resize(function() {
m()
});
m();
k.load(p);
return this
}
})(jQuery);
/*!
* jquery.scrollLoading.js
* by zhangxinxu http://www.zhangxinxu.com
* 2010-11-19 v1.0
* 2012-01-13 v1.1 鍋忕Щ鍊艰绠椾慨鏀� position 鈫� offset
* 2012-09-25 v1.2 澧炲姞婊氬姩瀹瑰櫒鍙傛暟, 鍥炶皟鍙傛暟
*/
//(function(a){a.fn.scrollLoading=function(b){var c={attr:"data-url",container:a(window),callback:a.noop};var d=a.extend({},c,b||{});d.cache=[];a(this).each(function(){var h=this.nodeName.toLowerCase(),g=a(this).attr(d.attr);var i={obj:a(this),tag:h,url:g};d.cache.push(i)});var f=function(g){if(a.isFunction(d.callback)){d.callback.call(g.get(0))}};var e=function(){var g=d.container.height();if(a(window).get(0)===window){contop=a(window).scrollTop()}else{contop=d.container.offset().top}a.each(d.cache,function(m,n){var p=n.obj,j=n.tag,k=n.url,l,h;if(p){l=p.offset().top-contop,l+p.height();if((l>=0&&l<g)||(h>0&&h<=g)){if(k){if(j==="img"){f(p.attr("src",k))}else{p.load(k,{},function(){f(p)})}}else{f(p)}n.obj=null}}})};e();d.container.bind("scroll",e)}})(jQuery);
(function($) {
$.fn.scrollLoading = function(options) {
if ($(window).width() < 680) {
var defaults = {
attr: "data-url",
container: $(".home-content"),
callback: $.noop
};
} else {
var defaults = {
attr: "data-url",
container: $(window),
callback: $.noop
};
}
var params = $.extend({}, defaults, options || {});
params.cache = [];
$(this).each(function() {
var node = this.nodeName.toLowerCase(),
url = $(this).attr(params["attr"]);
var data = {
obj: $(this),
tag: node,
url: url
};
params.cache.push(data);
});
var callback = function(call) {
if ($.isFunction(params.callback)) {
params.callback.call(call.get(0));
}
};
var loading = function() {
var contHeight = params.container.height();
if ($(window).get(0) === window) {
contop = $(window).scrollTop();
} else {
contop = params.container.offset().top;
}
$.each(params.cache, function(i, data) {
var o = data.obj,
tag = data.tag,
url = data.url,
post, posb;
if (o) {
post = o.offset().top - contop, post + o.height();
if ((post >= 0 && post < contHeight) || (posb > 0 && posb <= contHeight)) {
if (url) {
if (tag === "img") {
callback(o.attr("src", url));
} else {
o.load(url, {}, function() {
callback(o);
});
}
} else {
callback(o);
}
data.obj = null;
}
}
});
};
loading();
params.container.bind("scroll", loading);
};
})(jQuery);
// 寮曞叆鐨勬彃浠� 缁撴潫
//灏忕獥鍙ｇ殑鍙充晶鍒掑嚭鑿滃崟
$(".sm-menu-btn").click(function() {
if ($(".body").hasClass("slideleft")) {
$(".body").removeClass("slideleft");
} else {
$(".body").addClass("slideleft");
}
});
//寤舵椂鍔犺浇鍥剧墖鐨勫欢鏃跺姞杞藉鐞�
$(".scrollLoading").scrollLoading();
//鎼滅储妗嗙殑鑱氱劍澶勭悊
$("#search-input").focus(function() {
$(this).siblings(".search-icon").addClass("onfocus");
});
$("#search-input").blur(function() {
$(this).siblings(".search-icon").removeClass("onfocus");
});
/*
* 绐楀彛澶у皬鏀瑰彉
*/
function resize() {
var t = $("header .container").height();
$("header,header .wd100").css("height", t);
if ($(window).width() < 680) {
$(".home-content").css({
"margin-top": -t,
"padding-top": t
})
}
}
$(window).resize(function() {
resize();
});
resize();
//鍙充晶鐨勫洖鍒伴《閮� 闂嵎璋冩煡

