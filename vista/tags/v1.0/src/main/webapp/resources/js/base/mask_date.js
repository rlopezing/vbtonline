/**
 * Created with IntelliJ IDEA.
 * User: Training
 * Date: 19/09/13
 * Time: 02:50 PM
 * To change this template use File | Settings | File Templates.
 */
// jQuery Mask Plugin v1.3.0
// github.com/igorescobar/jQuery-Mask-Plugin
(function(d){var w=function(a,g,c){var e=this;a=d(a);var l;g="function"==typeof g?g(a.val(),c):g;e.init=function(){c=c||{};e.byPassKeys=[8,9,37,38,39,40,46];e.translation={0:{pattern:/\d/},9:{pattern:/\d/,optional:!0},"#":{pattern:/\d/,recursive:!0},A:{pattern:/[a-zA-Z0-9]/},S:{pattern:/[a-zA-Z]/}};e.translation=d.extend({},e.translation,c.translation);e=d.extend(!0,{},e,c);a.each(function(){!1!==c.maxlength&&a.attr("maxlength",g.length);a.attr("autocomplete","off");f.destroyEvents();f.events();f.val(f.getMasked())})};
    var f={events:function(){a.on("keydown.mask",function(){l=f.val()});a.on("keyup.mask",f.behaviour);a.on("paste.mask",function(){setTimeout(function(){a.keydown().keyup()},100)})},destroyEvents:function(){a.off("keydown.mask").off("keyup.mask").off("paste.mask")},val:function(v){var c="input"===a.get(0).tagName.toLowerCase();return 0<arguments.length?c?a.val(v):a.text(v):c?a.val():a.text()},behaviour:function(a){a=a||window.event;if(-1===d.inArray(a.keyCode||a.which,e.byPassKeys))return f.val(f.getMasked()),
        f.callbacks(a)},getMasked:function(){var a=[],d=f.val(),b=0,q=g.length,h=0,l=d.length,k=1,r="push",m=-1,n,s;c.reverse?(r="unshift",k=-1,n=0,b=q-1,h=l-1,s=function(){return-1<b&&-1<h}):(n=q-1,s=function(){return b<q&&h<l});for(;s();){var t=g.charAt(b),u=d.charAt(h),p=e.translation[t];p?(u.match(p.pattern)?(a[r](u),p.recursive&&(-1==m?m=b:b==n&&(b=m-k),n==m&&(b-=k)),b+=k):p.optional&&(b+=k,h-=k),h+=k):(a[r](t),u==t&&(h+=k),b+=k)}return a.join("")},callbacks:function(d){var e=f.val(),b=f.val()!==l;if(!0===
        b&&"function"==typeof c.onChange)c.onChange(e,d,a,c);if(!0===b&&"function"==typeof c.onKeyPress)c.onKeyPress(e,d,a,c);if("function"===typeof c.onComplete&&e.length===g.length)c.onComplete(e,d,a,c)}};e.remove=function(){f.destroyEvents();f.val(e.getCleanVal()).removeAttr("maxlength")};e.getCleanVal=function(){for(var a=[],c=f.val(),b=0,d=g.length;b<d;b++)e.translation[g.charAt(b)]&&a.push(c.charAt(b));return a.join("")};e.init()};d.fn.mask=function(a,g){return this.each(function(){d(this).data("mask",
    new w(this,a,g))})};d.fn.unmask=function(){return this.each(function(){d(this).data("mask").remove()})};d("input[data-mask]").each(function(){d(this).mask(d(this).attr("data-mask"))})})(window.jQuery||window.Zepto);