// 通用js
var code, codeurl;
function Fcommon(ctx) {
	$(document).on('click', '.city', function() {
		$('.i-city-target[code=' + code + ']').addClass('active');
		$('.mask').show();
		$('.i-city').show();
	});
	$(document).on('click', '.i-city-close', function() {
		$('.mask').hide();
		$('.i-city').hide();
	});
	$(document).on({
		mouseover : function() {
			$(this).addClass('active');
		},
		mouseout : function() {
			$(this).removeClass('active');
			$('.i-city-target[code=' + code + ']').addClass('active');
		},
		click : function() {
			code = $(this).attr('code');
			var hrefarr = window.location.href.split('/');
			if (code == 0 && hrefarr.length == 5) {
				hrefarr.splice(4, 1);
			} else {
				hrefarr[4] = code;
			}
			if(hrefarr.length > 5){
				if (hrefarr[6].substring(0,4) == 'lctj') {
					var lctjarr = hrefarr[6].split('&');
					lctjarr[0]='plctj?code='+code;
					hrefarr[6]=lctjarr.join('&');
				}}
			window.location.href = hrefarr.join('/');
		}
	}, '.i-city-target');

	$(function() {
		getcode();
		$('.mask')
				.before(
						'<div class="i-city"><div class="i-city-h"><span class="i-city-t">切换城市</span> <i class="i-city-close"></i></div><div class="i-city-c"><a class="i-city-target" name="" code="0">总公司</a><a class="i-city-target" name="北京市分公司" code="1100">北京</a><a class="i-city-target" name="天津市分公司" code="1200">天津</a><a class="i-city-target" name="河北省分公司" code="1300">河北</a><a class="i-city-target" name="山西省分公司" code="1400">山西</a><a class="i-city-target" name="内蒙古分公司" code="1500">内蒙古</a><a class="i-city-target"name="辽宁省分公司" code="2100">辽宁</a><a class="i-city-target" name="吉林省分公司" code="2200">吉林</a><a class="i-city-target" name="黑龙江省分公司" code="2300">黑龙江</a><a class="i-city-target" name="上海分公司" code="3100">上海</a><a class="i-city-target" name="江苏省分公司" code="3200">江苏</a><a class="i-city-target" name="浙江分公司" code="3300">浙江</a><a class="i-city-target" name="安徽省分公司" code="3400">安徽</a><a class="i-city-target" name="福建省分公司" code="3500">福建</a><a class="i-city-target" name="江西省分公司" code="3600">江西</a><a class="i-city-target" name="山东省分公司" code="3700">山东</a><a class="i-city-target" name="河南省分公司" code="4100">河南</a><a class="i-city-target" name="湖北省分公司" code="4200">湖北</a><a class="i-city-target" name="湖南省分公司" code="4300">湖南</a><a class="i-city-target" name="广东省分公司" code="4400">广东</a><a class="i-city-target" name="广西分公司" code="4500">广西</a><a class="i-city-target" name="海南省分公司" code="4600">海南</a><a class="i-city-target" name="重庆市分公司" code="5000">重庆</a><a class="i-city-target" name="四川省分公司" code="5100">四川</a><a class="i-city-target" name="贵州市分公司" code="5200">贵州</a><a class="i-city-target" name="云南省分公司" code="5300">云南</a><a class="i-city-target" name="西藏分公司" code="5400">西藏</a><a class="i-city-target" name="陕西省分公司" code="6100">陕西</a><a class="i-city-target" name="甘肃省分公司" code="6200">甘肃</a><a class="i-city-target" name="青海省分公司" code="6300">青海</a><a class="i-city-target" name="宁夏省分公司" code="6400">宁夏</a><a class="i-city-target" name="新疆省分公司" code="6500">新疆</a><a class="i-city-target" name="大连市分公司" code="2102">大连</a><a class="i-city-target" name="青岛市分公司" code="3702">青岛</a><a class="i-city-target" name="厦门市分公司" code="3502">厦门</a><a class="i-city-target" name="宁波市分公司" code="3312">宁波</a><a class="i-city-target" name="深圳市分公司" code="4403">深圳</a><a class="i-city-target" name="杭州市分公司" code="3301">杭州</a><a class="i-city-target" name="广州市分公司" code="4401">广州</a></div></div>');

		$('.header>span>span').html(
				$('.i-city-target[code=' + code + ']').html());
		$('.header p').html(
				'中共中国人民财产保险股份有限公司'
						+ $('.i-city-target[code=' + code + ']').attr('name')
						+ '委员会');
		$('.head-logo').parent().attr('href', ctx + codeurl);
		$('.c-title a').eq(0).attr('href', ctx + codeurl);
	});
}
// cookies的js
function getcode() {
	var codearr = window.location.href.split('/');
	if (codearr[4] == 'page' || codearr[4] == 'article'
			|| codearr[4] == 'notice' || codearr.length == 4) {
		code = 0;
	} else {
		code = codearr[4];
	}
	if (code == 0) {
		codeurl = '/12/0';
	} else {
		codeurl = '/12/' + code;
	}
}
// 正则表达式获取URL中的参数
function GetQueryString(name) {
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	var r = window.location.search.substr(1).match(reg); // 获取url中'?'符后的字符串并正则匹配
	var context = '';
	if (r != null)
		context = r[2];
	reg = null;
	r = null;
	return context == null || context == '' || context == 'undefined' ? ''
			: context;
}
function GetbrandName() {
	return window.location.pathname.split('/')[4];
}
// index页面的js
function Findex(ctx) {
	var index = 0, a = new Array(0, 0, 0, 0, 0);
	function t() {
		if (index < 4) {
			$('.i-news-list li').eq(index).addClass('active').siblings()
					.removeClass('active');
			$('.i-news-imagebox>a').eq(index).show().siblings().hide();
			index++;
		}
		if (index >= 4) {
			index = 0;
		}
	}
	function timer() {
		sh = setInterval(t, 5000);
	}

	t();
	timer();
	$(document).on({
		mouseover : function() {
			var num = $(this).index();
			clearInterval(sh);
			$(this).addClass('active').siblings().removeClass('active');
			$('.i-news-image').eq(num).show().siblings().hide();
		},
		mouseout : function() {
			index = $(this).index() + 1;
			timer();
		}
	}, '.i-news-list li');
	$(document).on({
		mouseover : function() {
			clearInterval(sh);
		},
		mouseout : function() {
			timer();
		}
	}, '.i-news-image');
	$(document).on('click', '#link option', function() {
		window.open($(this).attr('value'));
	});
	$(document).on('click', '#i-link-index', function() {
		$.cookie('cookie_code', 0, {
			expires : 7,
			path : ctx + '/'
		});
	});
	$(document).on(
			'click',
			'.i-a-tab',
			function() {
				var thise = $(this);
				$(this).addClass('on').siblings().removeClass('on');
				$('.i-a-group').eq($(this).index() - 1).show().siblings()
						.hide();
				if (a[thise.index() - 1] == 0) {
					a[thise.index() - 1] = 1;
					$.get(ctx + '/activities/count/' + thise.index()
							+ '?code=' + code, function(data) {
						Getactivetable(data,thise);
					});
				}
			});
	$(document).on({
		mouseover : function() {
			$(this).children().css('color', '#ff6600');
		},
		mouseout : function() {
			$(this).children().css('color', '#333');
		}
	}, '.i-a-cbox span');
	function Getactivetable(data,thistab) {
		var thisgroup;
		if (code == 0) {
			for (var i = 0; i < data.length; i++) {
				if (data[i].value == 0 || i == 0) {
					$('.i-a-group').eq(thistab.index() - 1).append(
							'<div class="i-a-cbox"><span class="i-a-city" title="'
									+ data[i].name + '">' + data[i].name
									+ '</span><span class="i-a-count">'
									+ data[i].value + '</span></div>');
				} else {
					thisgroup = $('.i-a-group').eq(thistab.index() - 1);
					$('.i-a-group')
							.eq(thistab.index() - 1)
							.append(
									'<div class="i-a-cbox"><span class="i-a-city" title="'
											+ data[i].name
											+ '"><a target="_blank" href="'
											+ ctx
											+ codeurl
											+ '/page/lctj?id='
											+ data[i].id
											+ '&brandName='
											+ thisgroup.attr('data-brand')
											+ '&type='
											+ thisgroup.attr('data-type')
											+ '&uptype='
											+ thisgroup.attr('data-uptype')
											+ '">'
											+ data[i].name
											+ '</a></span><span class="i-a-count"><a target="_blank" href="'
											+ ctx + codeurl + '/page/lctj?id='
											+ data[i].id + '&brandName='
											+ thisgroup.attr('data-brand')
											+ '&type='
											+ thisgroup.attr('data-type')
											+ '&uptype='
											+ thisgroup.attr('data-uptype')
											+ '">' + data[i].value
											+ '</a></span></div>');
				}
			}
		} else {
			for (var i = 0; i < data.length; i++) {
				if (data[i].value == 0) {
					$('.i-a-group').eq(thistab.index() - 1).append(
							'<div class="i-a-cbox"><span class="i-a-city" title="'
									+ data[i].name + '">' + data[i].name
									+ '</span><span class="i-a-count">'
									+ data[i].value + '</span></div>');
				} else {
					thisgroup = $('.i-a-group').eq(thistab.index() - 1);
					$('.i-a-group')
							.eq(thistab.index() - 1)
							.append(
									'<div class="i-a-cbox"><span class="i-a-city" title="'
											+ data[i].name
											+ '"><a target="_blank" href="'
											+ ctx
											+ codeurl
											+ '/page/lctj?id='
											+ data[i].id
											+ '&brandName='
											+ thisgroup.attr('data-brand')
											+ '&type='
											+ thisgroup.attr('data-type')
											+ '&uptype='
											+ thisgroup.attr('data-uptype')
											+ '">'
											+ data[i].name
											+ '</a></span><span class="i-a-count"><a target="_blank" href="'
											+ ctx + codeurl + '/page/lctj?id='
											+ data[i].id + '&brandName='
											+ thisgroup.attr('data-brand')
											+ '&type='
											+ thisgroup.attr('data-type')
											+ '&uptype='
											+ thisgroup.attr('data-uptype')
											+ '">' + data[i].value
											+ '</a></span></div>');
				}

			}
			if (data.length < 8) {
				$('.i-a-cbox').addClass('i-a-cboxc1');
			}
			if (data.length > 7 && data.length < 22) {
				$('.i-a-cbox').addClass('i-a-cboxc2');
			}
			if (data.length > 21) {
				$('.i-a-cbox').addClass('i-a-cboxc3');
			}
		}
	}
	$(function() {
		$('#news-more')
				.attr(
						'href',
						ctx
								+ codeurl
								+ '/page/djyw?brandId=a081b418588af22801588afb2020000f');
		$('#notice-more').attr('href', ctx + codeurl + '/page/tzgg');

		$('.i-linkbox a').eq(0).attr('href', ctx + '/12/0');
		$('.i-linkbox a').eq(1).attr('href', ctx + codeurl + '/page/document');
		$('.i-linkbox').eq(2).find('a').attr('href',
				ctx + codeurl + '/page/lhdzz');
		$('.i-linkbox').eq(3).find('a').attr('href',
				ctx + codeurl + '/page/lhdzb');
		$('.i-linkbox').eq(4).find('a').attr('href',
				ctx + codeurl + '/page/djlxd');
		if (code == 0) {
			$('.i-actdegree .i-title').html('<i></i>&nbsp各省级单位活跃度');
		} else {
			$('.i-actdegree .i-title').html('<i></i>&nbsp各地市单位活跃度');
		}
		$
				.get(
						ctx + '/index/brands/' + code,
						function(data) {
							for (var i = 0; i < data.length; i++) {
								$('.i-icongroup')
										.append(
												'<div class="iconbox"><a href="'
														+ ctx
														+ codeurl
														+ data[i].url
														+ '?brandId='
														+ data[i].id
														+ '" target="_blank"><div class="icon"></div></a><a>'
														+ data[i].name
														+ '</a></div>')
							}
							$('.icon').each(
									function(i) {
										$(this).css(
												'background',
												'url(' + ctx
														+ '/resources/images/'
														+ data[i].icon + ')');
									});
						});
		$.get(ctx + '/index/api?code=' + code, function(data) {
			$('.i-org-box .i-module-count').eq(0).html(data.dwhCount);
			$('.i-org-box .i-module-count').eq(1).html(data.dzbCount);
			$('.i-org-box .i-module-count').eq(2).html(data.dyCount);
			$('.i-org-box .i-module-count').eq(3).html(data.ybdyCount);
			$('.i-org-box .i-module-count').eq(4).html(data.jjfzCount);
			for (var i = 0; i < data.news.length; i++) {
				$('.i-news-list').append(
						'<li><a href="' + ctx + codeurl + '/article/'
								+ data.news[i].id + '" target="_blank">'
								+ data.news[i].title + '</a></li>');

				var url = '/file/download?id=' + data.news[i].thumbnail;
				if (data.news[i].thumbnail.length == 0) {
					url = '/resources/images/picnotfound.png';
				}
				$('.i-news-imagebox').append(
						'<a class="i-news-image" href="' + ctx + codeurl
								+ '/article/' + data.news[i].id
								+ '" target="_blank">\
						<img src=' + url
								+ '></a>');
			}
			for (var i = 0; i < data.notice.length; i++) {
				var title = data.notice[i].title;
				$('.i-notice').append(
						'<div class="i-notice-info"><a href="' + ctx + codeurl
								+ '/notice/' + data.notice[i].id
								+ '" target="_blank">' + title
								+ '</a><span class="i-notice-date">'
								+ data.notice[i].createTime + '</span></div>');
			}
			$('.i-linkbox .f-r a').eq(0).html(data.lhdzzCount);
			$('.i-linkbox .f-r a').eq(1).html(data.lhdzbCount);
			$('.i-linkbox .f-r a').eq(2).html(data.djlxdCount);
		});

		$.get(ctx + '/inactive/list?code=' + code,
				function(data) {
					for (var i = 0; i < data.length; i++) {
						$('.i-actdegree-box .i-module-name').eq(i).html(
								data[i].name);
						$('.i-actdegree-box .i-module-count').eq(i).html(
								data[i].value);
					}
				});

		$('.i-a-tab').eq(0).click();
	});
}

// brand页面的js
function Fbrand(ctx) {
	var timer, brandId;
	$(document).on({
		mouseover : function() {
			i = $(this);
			function way() {
				$('.news-tabitem').removeClass('active');
				$('.c-tab').hide();
				i.addClass('active');
				$('#' + i.attr('tab')).show();
			}

			timer = setTimeout(way, 150);
		},
		mouseout : function() {
			i = $(this);
			clearTimeout(timer);
		}
	}, '.news-tabitem');
	$(document).on({
		mouseover : function() {
			$(this).css('color', '#ff6600');
		},
		mouseout : function() {
			$(this).css('color', '#333');
		}
	}, '.clearfix a');

	function pagination(i, subId, te) {
		var pageIndex = 0;
		var pageSize = 10;
		$(function() {
			// 分页，PageCount是总条目数，这是必选参数，其它参数都是可选
			$('#Pagination' + i).pagination(te, {
				callback : PageCallback, // PageCallback() 为翻页调用次函数。
				prev_text : '上一页',
				next_text : '下一页 ',
				items_per_page : pageSize,
				num_edge_entries : 1, // 两侧首尾分页条目数
				num_display_entries : 4, // 连续分页主体部分分页条目数
				current_page : pageIndex
			});
			// 翻页调用
			function PageCallback(index, jq) {
				InitPage(index);
			}
			// 请求数据
			function InitPage(pageIndex) {
				$
						.get(
								ctx + '/tabs/article/list?code=' + code
										+ '&subId=' + subId + '&pageNum='
										+ (pageIndex + 1),
								function(data) {
									$('#ul' + i).empty();
									for (var j = 0; j < data[0].data.length; j++) {
										$('#ul' + i)
												.append(
														'<li class="clearfix"><span class="toubiao"></span><a href="'
																+ ctx
																+ codeurl
																+ '/article/'
																+ data[0].data[j].id
																+ '" target="_blank">【'
																+ data[0].data[j].orgName
																+ '】'
																+ data[0].data[j].title
																+ '</a><span class="news-date">'
																+ data[0].data[j].time
																+ '</span></li>');
									}

									$('#Pagination' + i).prepend(
											'<span>共' + te
													+ '条记录,每页显示10条</span>');

								});
				$('.pagination a').removeAttr('href');
			}
		});

	}
	$(function() {
		brandId = GetQueryString('brandId');
		var hrefarr = window.location.href.split('/');
		$.get(ctx + '/tabs/' + code + '/' + brandId, function(data) {
			for (var i = 0; i < data.length; i++) {
				$('.news-tab').append(
						'<span class="news-tabitem" tab="tab' + i + '">'
								+ data[i].name + '</span>');
				$('.news-list').append(
						'<div class="c-tab" id="tab' + i
								+ '"><div class="c-list"><ul id="ul' + i
								+ '"></ul></div></div>')

				if (data[i].totalElements > 0) {
					$('#tab' + i).append(
							'<div id="Pagination' + i
									+ '" class="pagination"></div>');
					pagination(i, data[i].id, data[i].totalElements);
				}
			}
			$('.news-tabitem').eq(0).mouseover();
			if (hrefarr[6].substring(0,4) == 'dygl') {
				Fdygl();
			}
		});
	});
}
// 知识库页面js
function Fdocument(ctx) {
	var brandId, key, subId, listData;
	// list-menu
	$(".list-menu").delegate(
			".title",
			"click",
			function() {
				if (!$(this).hasClass("active")) {
					$(this).parent().siblings().each(
							function() {
								var $sec_list = $(this).find(".second-list");
								if ($sec_list.prev().hasClass("active")) {
									$sec_list.prev().removeClass("active")
											.end().slideUp("fast");
									$(this).find(".fa").removeClass(
											"fa-angle-down").addClass(
											"fa-angle-right");
								} else {
									$(this).children().removeClass("active");
								}
							});
					$(this).next().slideDown("fast").end().find(".fa")
							.removeClass("fa-angle-right").addClass(
									"fa-angle-down").end().addClass("active");
				} else {
					$(this).next().slideUp("fast").end().find(".fa")
							.removeClass("fa-angle-down").addClass(
									"fa-angle-right").end().removeClass(
									"active");
				}
			});
	$(".list-menu").delegate(".second-list li>a", "click", function() {
		$(this).addClass("active");
		$(".second-list li>a").not(this).removeClass("active");// not()过滤
	})
	$(document).on(
			{
				click : function() {
					key = '';
					$(".c-list-content").html("<ul></ul>");
					brandId = $(this).parent().parent().parent().find(
							".item-title").attr("data-id");
					subId = $(this).attr("data-id");
					getDocData();
				}
			}, '.second-list a');
	$(document).on({
		click : function() {
			key = '';
			$(".c-list-content").html("<ul></ul>");
			brandId = $(this).find(".item-title").attr("data-id");
			subId = "";
			getDocData();
		}
	}, ".title");
	function getDocData() {
		$.get(ctx + '/tabs/knowledge/list?code=' + code + '&brandId=' + brandId
				+ '&subId=' + subId + '&key=' + key, function(data) {
			if (data[0].totalElements > 0) {
				$('.c-list-content').append(
						'<div id="Pagination" class="pagination"></div>');
				pagination(data[0].totalElements, key);
			} else {
				$('.c-list-content ul').html('<li>请添加数据!</li>').css({
					"text-align" : "center"
				});
			}
		});
	}
	function searchDoc() {
		$('.c-list-content').html('<ul></ul>');
		key = $('#searchInput').val();
		getDocData();
	}
	$(document).on('click', '#searchBtn', function() {
		searchDoc();
	});
	$("#searchInput").on("keyup", function(ev) {
		if (ev.keyCode == 13) {
			searchDoc();
		}
	})
	function pagination(te) {
		var pageIndex = 0;
		var pageSize = 15;
		$(function() {
			// 分页，PageCount是总条目数，这是必选参数，其它参数都是可选
			$('#Pagination').pagination(te, {
				callback : PageCallback, // PageCallback() 为翻页调用次函数。
				prev_text : '上一页',
				next_text : '下一页 ',
				items_per_page : pageSize,
				num_edge_entries : 1, // 两侧首尾分页条目数
				num_display_entries : 4, // 连续分页主体部分分页条目数
				current_page : pageIndex
			});
			// 翻页调用
			function PageCallback(index) {
				$(".c-list-content ul").html("");
				InitPage(index);
			}
			// 请求数据
			function InitPage(pageIndex) {
				$
						.get(
								ctx + '/tabs/knowledge/list?code=' + code
										+ '&brandId=' + brandId + '&subId='
										+ subId + '&key=' + key,
								+subId + '&key=' + key + '&pageNum='
										+ (pageIndex + 1),
								function(data) {
									for (var i = 0; i < data[0].data.length; i++) {
										$('.c-list-content ul')
												.append(
														'<li><a href="'
																+ ctx
																+ codeurl
																+ '/article/'
																+ data[0].data[i].id
																+ '" target="_blank">'
																+ "【"
																+ data[0].data[i].orgName
																+ "】"
																+ data[0].data[i].title
																+ '</a><span class="news-date">'
																+ data[0].data[i].time
																+ '</span></li>');
									}

									$('#Pagination').prepend(
											'<span>共' + te
													+ '条记录,每页显示15条</span>');

								})
			}
		});

	}
	$(function() {

		$
				.get(
						'/knowledge/info?code=' + code,
						function(data) {
							for (var i = 0; i < data.length; i++) {
								listData = data;
								if (data[i].subBrand.length > 0) {
									var html = "";
									for (j = 0; j < data[i].subBrand.length; j++) {
										html += '<li><a data-id="'
												+ data[i].subBrand[j].id + '">'
												+ data[i].subBrand[j].name
												+ '</a></li>';
									}
									var xhtml = '</span><i class="fa fa-angle-right"></i></a>';
								} else {
									var html = "";
									var xhtml = "";
								}
								$('.list-menu')
										.append(
												'<li>\
											<a class="title">\
											<span class="item-title" data-id="'
														+ data[i].id
														+ '">'
														+ data[i].name
														+ xhtml
														+ '<ul class="second-list">'
														+ html
														+ '</ul>\
										</li>');
							}

							$('.title').eq(0).trigger("click");
							$('.list-menu>li:last').css('border', 'none');
						});
	})
}
// 组织结构页面的js
function Forganizations(ctx) {

	$(document).on(
			'click',
			'.org-btn',
			function() {
				var _self = $(this);
				var pid = _self.attr('party_id');
				var obj = _self.parent().siblings(".org-panel");
				$.get(ctx + '/org/sub/' + pid, function(data) {
					var html = '';
					for (var i = 0; i < data.length - 1; i++) {
						html += '<li><a href="' + ctx + codeurl
								+ '/page/org_detail?orgId=' + data[i].id
								+ '" target="_blank">' + data[i].name
								+ '</a></li>';
					}
					;
					obj.find('.org-party').empty().html(html);
					obj.find('.org-result span').empty().html(
							data[data.length - 1].count);
				});
				obj.toggle(function() {
					if ($(this).css('display') === 'block') {
						_self.text('收起');
					} else {
						_self.text('展开');
					}
				});
			});
	$(function() {
		$.get(ctx + '/orgCount/' + code, function(data) {
			for (var i = 0; i < 5; i++) {
				$('tbody td').eq(i).html(
						data[$('tbody td').eq(i).attr('name') + 'Count']);
			}
		});
		$
				.get(
						ctx + '/org/' + code,
						function(data) {
							for (var i = 0; i < data.length; i++) {
								$('.c-org>ul')
										.append(
												'<li><div class="org-item"><i></i><span><a href="'
														+ ctx
														+ codeurl
														+ '/page/org_detail?orgId='
														+ data[i].id
														+ '" target="_blank">中共中国人民财产保险股份有限公司'
														+ data[i].name
														+ '</a></span><a party_id="'
														+ data[i].id
														+ '" href="javascript:;" class="org-btn">展开</a></div><div class="org-panel"><ul class="clearfix org-party"></ul><div class="org-result"><span></span></div></div></li>');
							}
						});
	})

}
function Forg(ctx, typeName, typeNum) {
	var orgName = new Array("联合党支部", "联合党总支", "党建联系点"), thiscode;
	function Porg() {
		$
				.get(
						ctx + '/org/party/list?code=' + thiscode,
						function(data) {
							$('.c-table')
									.append(
											'<table><thead><tr><th>组织机构</th><th>'
													+ orgName[typeNum - 1]
													+ '数量</th></tr></thead><tbody></tbody></table>');
							for (var i = 1; i < data.length; i++) {
								if (data[i][typeName + 'Count'] == 0) {
									$(".c-table tbody")
											.append(
													'<tr><td>'
															+ data[i].dwName
															+ '</td><td><span>0</span></td></tr>');
								} else {
									$(".c-table tbody")
											.append(
													'<tr><td><span>'
															+ data[i].dwName
															+ '</span></td><td><a 	class="num" data-id="'
															+ data[i].id
															+ '" data-type="'
															+ data[i].orgType
															+ '" data-code="'
															+ data[i].orgCode
															+ '">'
															+ data[i][typeName
																	+ 'Count']
															+ '</a></td></tr>');
								}
							}
							$('tbody').eq(t).prepend(
									'<tr><td><a class="back" href="javascript:window.location.reload();">'
											+ data[0].dwName + '</a></td><td>'
											+ data[0][typeName + 'Count']
											+ '</td></tr>');
						});
	}
	$(document)
			.on(
					'click',
					'a.num',
					function() {
						thiscode = $(this).attr('data-code');
						$(".c-table").empty();
						if ($(this).attr('data-type') == 0) {
							Porg();
						} else {
							$('.c-table').append(
									'<table><tbody></tbody></table>');
							$
									.get(
											ctx + '/org/committee/list?orgId='
													+ $(this).attr('data-id')
													+ '&type=' + typeNum,
											function(data) {
												$(".c-table tbody")
														.append(
																'<td><a class="back org3">'
																		+ data[0].dwName
																		+ '</a>［联合］</td>');
												for (var i = 0; i < data.length; i++) {
													$(".c-table tbody")
															.append(
																	'<tr><td><a href="'
																			+ ctx
																			+ codeurl
																			+ '/page/org_detail?orgId='
																			+ data[i].id
																			+ '" target = "_blank">'
																			+ data[i][typeName
																					+ 'Name']
																			+ '</a></td></tr>');
												}
											});
						}
					});
	$(document).on('click', '.org3', function() {
		$(".c-table").empty();
		Porg();
	})
	$(function() {
		$
				.get(
						ctx + '/org/party/list?code=' + code,
						function(data) {
							$('.c-table')
									.append(
											'<table><thead><tr><th>组织机构</th><th>'
													+ orgName[typeNum - 1]
													+ '数量</th></tr></thead><tbody></tbody></table>');
							for (var i = 0; i < data.length; i++) {
								if (data[i][typeName + 'Count'] == 0) {
									$(".c-table tbody")
											.append(
													'<tr><td>'
															+ data[i].dwName
															+ '</td><td><span>0</span></td></tr>');
								} else {
									$(".c-table tbody")
											.append(
													'<tr><td><span>'
															+ data[i].dwName
															+ '</span></td><td><a 	class="num" data-id="'
															+ data[i].id
															+ '" data-type="'
															+ data[i].orgType
															+ '" data-code="'
															+ data[i].orgCode
															+ '">'
															+ data[i][typeName
																	+ 'Count']
															+ '</a></td></tr>');
								}
							}
						});
	})
}
function ForgDetail(ctx) {
	var infotxt = '';
	var orgId = GetQueryString("orgId");
	function memberPrint(job, data) {
		for (var i = 0; i < data[job].length; i++) {
			$('#' + job).append(
					'<span><a href="' + ctx + codeurl + '/page/member_info?id='
							+ data[job][i].id + '" target="_blank">'
							+ data[job][i].name + '</a></span>');
		}
	}
	$(function() {
		$('.c-title a')
				.eq(1)
				.attr(
						'href',
						ctx
								+ codeurl
								+ '/page/organizations?brandId=a081b418588af22801588af549b70002');
		$
				.get(
						ctx + '/org/detail/list?orgId=' + orgId,
						function(data) {
							$('.c-detail h2 span').html(
									data.cityName + '［' + data.orgName + '］');
							if (data.remark) {
								$('.c-mod').eq(0).children('p').html(
										data.remark);
							}
							if (data.leaderName != null) {
								$('.c-mod-leader span').html(data.leaderName);
								$('.c-mod-leader').show();
							}
							if (data.isContactParty == true) {
								$('.c-detail h2').append('<i class="i2"></i>');
							}
							if (data.isUnionParty == true) {
								$('.c-detail h2').append('<i class="i3"></i>');
							}
							if (data.isUnionMasterParty == true) {
								$('.c-detail h2').append('<i class="i4"></i>');
							}
							$('#buildDate').html(data.buildDate);
							$('#changeInfo').html(data.changeInfo);

							if (data.partyType == 0) {

								$('.c-mod')
										.eq(0)
										.children('.mod-content')
										.append(
												'<div class="clearfix"><p class="position">成立时间</p><p class="personnel" id="buildDate"></p></div><div class="clearfix"><p class="position">换届信息</p><p class="personnel" id="changeInfo"></p></div>');
								$('.c-mod')
										.eq(1)
										.children('.mod-content')
										.append(
												'<div class="clearfix"><p class="position">党委书记</p><p class="personnel" id="dwsj"></p></div><div class="clearfix"><p class="position">党委副书记</p><p class="personnel" id="dwfsj"></p></div><div class="clearfix"><p class="position">纪委书记</p><p class="personnel" id="jwsj"></p></div><div class="clearfix"><p class="position">党委委员</p><p class="personnel" id="dwwy"></p></div>');
								$('.orgD-count').eq(0).hide();
								for (var i = 1; i < 5; i++) {
									if (data[$('.orgD-count .count').eq(i)
											.attr('name')] == 0
											|| data.orgCode == 0) {
										$('.orgD-count .count').eq(i).html(
												data[$('.orgD-count .count')
														.eq(i).attr('name')]);
									} else {
										$('.orgD-count .count')
												.eq(i)
												.html(
														'<a target="_blank" href="'
																+ ctx
																+ codeurl
																+ '/page/lctj?id='
																+ data.orgId
																+ '&brandName='
																+ $(
																		'.orgD-count .count')
																		.eq(i)
																		.attr(
																				'data-brand')
																+ '&type='
																+ $(
																		'.orgD-count .count')
																		.eq(i)
																		.attr(
																				'data-type')
																+ '&uptype='
																+ $(
																		'.orgD-count .count')
																		.eq(i)
																		.attr(
																				'name')
																+ '">'
																+ data[$(
																		'.orgD-count .count')
																		.eq(i)
																		.attr(
																				'name')]
																+ '</a>');
									}
								}
								if (data.dwsj != '') {
									infotxt += '党委书记' + data.dwsj.length + '名 ';
									memberPrint('dwsj', data);
								}
								if (data.dwfsj != '') {
									infotxt += '党委副书记' + data.dwfsj.length
											+ '名 ';
									memberPrint('dwfsj', data);
								}
								if (data.jwsj != '') {
									infotxt += '纪委书记' + data.jwsj.length + '名 ';
									memberPrint('jwsj', data);
								}
								if (data.dwwy != '') {
									infotxt += '党委委员' + data.dwwy.length + '名 ';
									memberPrint('dwwy', data);
								}
								for (var i = 0; i < data.children.length; i++) {
									$('.c-mod')
											.eq(3)
											.children('.mod-content')
											.children('ul')
											.append(
													'<li><a href="'
															+ ctx
															+ codeurl
															+ '/page/org_detail?orgId='
															+ data.children[i].id
															+ '" target="_blank">'
															+ data.children[i].name
															+ '</a></li>');
								}
							} else {
								$('.c-mod')
										.eq(0)
										.children('.mod-content')
										.append(
												'<div class="clearfix"><p class="position">成立时间</p><p class="personnel" id="buildDate"></p></div><div class="clearfix"><p class="position">换届信息</p><p class="personnel" id="changeInfo"></p></div>');
								$('.c-mod')
										.eq(1)
										.children('.mod-content')
										.append(
												'<div class="clearfix"><p class="position">支部书记</p><p class="personnel" id="zbsj"></p></div><div class="clearfix"><p class="position">支部副书记</p><p class="personnel" id="zbfsj"></p></div><div class="clearfix"><p class="position">组织委员</p><p class="personnel" id="zzwy"></p></div><div class="clearfix"><p class="position">宣传委员</p><p class="personnel" id="xcwy"></p></div><div class="clearfix"><p class="position">纪检委员</p><p class="personnel" id="jjwy"></p></div><div class="clearfix"><p class="position">党员</p><p class="personnel" id="dy"></p></div><div class="clearfix"><p class="position">预备党员</p><p class="personnel" id="ybdy"></p></div><div class="clearfix"><p class="position">发展对象</p><p class="personnel" id="fzdx"></p></div><div class="clearfix"><p class="position">积极分子</p><p class="personnel" id="jjfz"></p></div>');
								$('.orgD-count').eq(4).hide();
								for (var i = 0; i < 4; i++) {
									if (data[$('.orgD-count .count').eq(i)
											.attr('name')] == 0) {
										$('.orgD-count .count').eq(i).html(
												data[$('.orgD-count .count')
														.eq(i).attr('name')]);
									} else {
										$('.orgD-count .count')
												.eq(i)
												.html(
														'<a target="_blank" href="'
																+ ctx
																+ codeurl
																+ '/page/lctj?id='
																+ data.orgId
																+ '&brandName='
																+ $(
																		'.orgD-count .count')
																		.eq(i)
																		.attr(
																				'data-brand')
																+ '&type='
																+ $(
																		'.orgD-count .count')
																		.eq(i)
																		.attr(
																				'data-type')
																+ '&uptype='
																+ $(
																		'.orgD-count .count')
																		.eq(i)
																		.attr(
																				'name')
																+ '">'
																+ data[$(
																		'.orgD-count .count')
																		.eq(i)
																		.attr(
																				'name')]
																+ '</a>');
									}
								}
								if (data.zbsj != '') {
									infotxt += '支部书记' + data.zbsj.length + '名 ';
									memberPrint('zbsj', data);
								}
								if (data.zbfsj != '') {
									infotxt += '支部副书记' + data.zbfsj.length
											+ '名 ';
									memberPrint('zbfsj', data);
								}
								if (data.zzwy != '') {
									infotxt += '组织委员' + data.zzwy.length + '名 ';
									memberPrint('zzwy', data);
								}
								if (data.xcwy != '') {
									infotxt += '宣传委员' + data.xcwy.length + '名 ';
									memberPrint('xcwy', data);
								}
								if (data.jjwy != '') {
									infotxt += '纪检委员' + data.jjwy.length + '名 ';
									memberPrint('jjwy', data);
								}
								if (data.dy != '') {
									infotxt += '党员' + data.dy.length + '名 ';
									memberPrint('dy', data);
								}
								if (data.ybdy != '') {
									infotxt += '预备党员' + data.ybdy.length + '名 ';
									memberPrint('ybdy', data);
								}
								if (data.fzdx != '') {
									infotxt += '发展对象' + data.fzdx.length + '名 ';
									memberPrint('fzdx', data);
								}
								if (data.jjfz != '') {
									infotxt += '积极分子' + data.jjfz.length + '名 ';
									memberPrint('jjfz', data);
								}
								$('.c-mod').eq(3).hide();
							}
							$('.info').html(infotxt);
						})
	})
}
function FmemberInfo(ctx) {
	var id;
	id = GetQueryString("id");
	$(function() {
		$.get(ctx + '/org/member/info?id=' + id, function(data) {
			$('.c-member h2 span').html(data[0].name);
			$('.member-info p').eq(1).html(data[0].jobName);
			$('.member-info p').eq(2).html(data[0].gender);
			$('.member-info p').eq(4).html(data[0].joinPartyDate);
			$('.member-info p').eq(6).html(data[0].email);
			$('.member-info p').eq(7).html(data[0].tel);
		})
	})
}

function Fnotice(ctx) {
	function pagination(te) {
		var pageIndex = 0;
		var pageSize = 10;
		$(function() {
			// 分页，PageCount是总条目数，这是必选参数，其它参数都是可选
			$('#Pagination').pagination(te, {
				callback : PageCallback, // PageCallback() 为翻页调用次函数。
				prev_text : '上一页',
				next_text : '下一页 ',
				items_per_page : pageSize,
				num_edge_entries : 1, // 两侧首尾分页条目数
				num_display_entries : 4, // 连续分页主体部分分页条目数
				current_page : pageIndex
			});
			// 翻页调用
			function PageCallback(index) {
				InitPage(index);
			}
			// 请求数据
			function InitPage(pageIndex) {
				$.get(ctx + '/tabs/Notice/list?code=' + code, function(data) {
					for (var i = 0; i < data.rows.length; i++) {
						$('.c-list ul').append(
								'<li class="clearfix"><a href="' + ctx
										+ codeurl + '/notice/'
										+ data.rows[i].id
										+ '" target="_blank">'
										+ data.rows[i].title
										+ '</a><span class="news-date">'
										+ data.rows[i].time + '</span></li>');
					}

					$('#Pagination').prepend(
							'<span>共' + te + '条记录,每页显示10条</span>');

				})
			}
		});

	}
	$(function() {
		$.get(ctx + '/tabs/Notice/list?code=' + code, function(data) {
			$('.c-list').html('<ul></ul>');
			if (data.total > 0) {
				$('.c-list').append(
						'<div id="Pagination" class="pagination"></div>');
				pagination(data.total);
			}
		});
	});
}
function Fdygltable(ctx) {
	var brandId, thisid, orgInfo, orgName;
	function setTable(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].orgType == 2) {
				orgName = data[i].name;
			} else {
				orgName = '<a class="orgname" href="' + ctx + codeurl
						+ '/page/org_detail?orgId=' + data[i].id
						+ '" target="_blank">' + data[i].name + '</a>';
			}
			$('.c-table tbody').append(
					'<tr data-id="' + data[i].id + '"><td>' + orgName
							+ '</td><td><a class="num org' + data[i].orgType
							+ '">' + data[i].dyCount
							+ '</a></td><td><a class="num org'
							+ data[i].orgType + '">' + data[i].ybdyCount
							+ '</a></td><td><a class="num org'
							+ data[i].orgType + '">' + data[i].fzdxCount
							+ '</a></td><td><a class="num org'
							+ data[i].orgType + '">' + data[i].jjfzCount
							+ '</a></td></tr>');
		}
		$('tbody tr').eq(0).html(
				'<td><a class="orgname" href="' + ctx + codeurl
						+ '/page/org_detail?orgId=' + data[0].id
						+ '" target="_blank">' + data[0].name + '</a></td><td>'
						+ data[0].dyCount + '</td><td>' + data[0].ybdyCount
						+ '</td><td>' + data[0].fzdxCount + '</td><td>'
						+ data[0].jjfzCount + '</td>');
		$('html').animate({
			scrollTop : $(".header").offset().top
		}, 150);
		$('.c-table').unmask();
	}
	$(document)
			.on(
					'click',
					'.org0',
					function() {
						if ($(this).parents('tr').attr('data-id')) {
							thisid = $(this).parents('tr').attr('data-id');
						}
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id='
												+ thisid + '&brandId='
												+ brandId,
										function(data) {
											setTable(data);
											$('th')
													.eq(0)
													.html(
															'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
										});
					})
	$(document)
			.on(
					'click',
					'.org1',
					function() {
						thisid = $(this).parents('tr').attr('data-id');
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											if (code == 0) {
												$('th')
														.eq(0)
														.html(
																'<a class="back org0"><i class="backicon"></i></a>');
												$('tr').eq(0).attr('data-id',
														data[0].pid);
											} else {
												$('th')
														.eq(0)
														.html(
																'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
											}
										});
					});
	$(document).on('click','.org2',function(){
		window.open(ctx+codeurl+'/page/org_detail?orgId='+$(this).parents('tr').attr('data-id'));
	});
	$(function() {
		brandId = GetQueryString('brandId');
		$('.c-table').mask('正在加载...请稍后');
		$.get(ctx + '/flow/count?code=' + code + '&id=' + brandId, function(
				data) {
			if (code == 0) {
				data[0].name = '合计';
			}
			setTable(data);
		});
	});
}
function Fmbgltable(ctx) {
	var brandId, thiscode, thisid, ndgzjhCount, zxgzjhCount, gzjhFlowCount, gzjhNewsCount, gzzjFlowCount, gzzjNewsCount, orgName, brandName;
	function setTable(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].orgType == 2) {
				orgName = data[i].name;
			} else {
				orgName = '<a class="orgname org' + data[i].orgType + '">'
						+ data[i].name + '</a>';
			}
			if (data[i].ndgzjhCount == 0) {
				ndgzjhCount = 0;
			} else {
				ndgzjhCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=gzjh1&uptype=gzjh" target="_blank">'
						+ data[i].ndgzjhCount + '</a>';
			}
			if (data[i].zxgzjhCount == 0) {
				zxgzjhCount = 0;
			} else {
				zxgzjhCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=gzjh2&uptype=gzjh" target="_blank">'
						+ data[i].zxgzjhCount + '</a>';
			}
			if (data[i].gzjhFlowCount == 0) {
				gzjhFlowCount = 0;
			} else {
				gzjhFlowCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=gzjh3&uptype=gzjh" target="_blank">'
						+ data[i].gzjhFlowCount + '</a>';
			}
			if (data[i].gzjhNewsCount == 0) {
				gzjhNewsCount = 0;
			} else {
				gzjhNewsCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/xwtj?id=' + data[i].id + '&brandName='
						+ brandName + '&type=gzjh" target="_blank">'
						+ data[i].gzjhNewsCount + '</a>';
			}
			if (data[i].gzzjFlowCount == 0) {
				gzzjFlowCount = 0;
			} else {
				gzzjFlowCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=gzzj&uptype=gzzj" target="_blank">'
						+ data[i].gzzjFlowCount + '</a>';
			}
			if (data[i].gzzjNewsCount == 0) {
				gzzjNewsCount = 0;
			} else {
				gzzjNewsCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/xwtj?id=' + data[i].id + '&brandName='
						+ brandName + '&type=gzzj" target="_blank">'
						+ data[i].gzzjNewsCount + '</a>';
			}
			$('.c-table tbody').eq(0).append(
					'<tr data-id="' + data[i].id + '"><td>' + orgName
							+ '</td><td>' + ndgzjhCount + '</td><td>'
							+ zxgzjhCount + '</td><td>' + gzjhFlowCount
							+ '</td><td>' + gzjhNewsCount + '</td></tr>');
			$('.c-table tbody').eq(1).append(
					'<tr data-id="' + data[i].id + '"><td>' + orgName
							+ '</td><td>' + gzzjFlowCount + '</td><td>'
							+ gzzjNewsCount + '</td></tr>');
		}
		$('tbody').eq(0).find('td').eq(0).html(data[0].name);
		$('tbody').eq(1).find('td').eq(0).html(data[0].name);
		$('html').animate({
			scrollTop : $(".header").offset().top
		}, 150);
		$('.c-table').unmask();
	}
	$(document)
			.on(
					'click',
					'.org0',
					function() {
						if ($(this).parents('tr').attr('data-id')) {
							thisid = $(this).parents('tr').attr('data-id');
						}
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$.get(ctx + '/subFlow/count?id=' + thisid + '&brandId='
								+ brandId, function(data) {
							setTable(data);
						});
						$('.c-table')
								.eq(0)
								.find('th')
								.eq(0)
								.html(
										'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
						$('.c-table')
								.eq(1)
								.find('th')
								.eq(0)
								.html(
										'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
					})
	$(document)
			.on(
					'click',
					'.org1',
					function() {
						thisid = $(this).parents('tr').attr('data-id');
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											if (code == 0) {
												$('.c-table')
														.eq(0)
														.find('th')
														.eq(0)
														.html(
																'<a class="back org0"><i class="backicon"></i></a>');
												$('.c-table')
														.eq(1)
														.find('th')
														.eq(0)
														.html(
																'<a class="back org0"><i class="backicon"></i></a>');
												$('.c-table').eq(0).find('tr')
														.eq(0).attr('data-id',
																data[0].pid);
												$('.c-table').eq(1).find('tr')
														.eq(0).attr('data-id',
																data[0].pid);
											} else {
												$('.c-table')
														.eq(0)
														.find('th')
														.eq(0)
														.html(
																'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
												$('.c-table')
														.eq(1)
														.find('th')
														.eq(0)
														.html(
																'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
											}
										});
					});
	$(function() {
		brandId = GetQueryString('brandId');
		brandName = GetbrandName();
		$('.c-table').mask('正在加载...请稍后');
		$.get(ctx + '/flow/count?code=' + code + '&id=' + brandId, function(
				data) {
			if (code == 0) {
				data[0].name = '合计';
			}
			setTable(data);
			if (code == 0) {
				$('tbody').eq(0).find('tr').eq(0).html(
						'<td>' + data[0].name + '</td><td>'
								+ data[0].ndgzjhCount + '</td><td>'
								+ data[0].zxgzjhCount + '</td><td>'
								+ data[0].gzjhFlowCount + '</td><td>'
								+ data[0].gzjhNewsCount + '</td>');
				$('tbody').eq(1).find('tr').eq(0).html(
						'<td>' + data[0].name + '</td><td>'
								+ data[0].gzzjFlowCount + '</td><td>'
								+ data[0].gzzjNewsCount + '</td>');
			}
		});
	});
}
function Fzzshtable(ctx) {
	var brandId, thiscode, thisid, zbwyhCount, zbdydhCount, dxzhCount, dkCount, orgName, brandName;
	function setTable(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].orgType == 2) {
				orgName = data[i].name;
			} else {
				orgName = '<a class="orgname org' + data[i].orgType + '">'
						+ data[i].name + '</a>';
			}
			if (data[i].zbwyhCount == 0) {
				zbwyhCount = 0;
			} else {
				zbwyhCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=zbwyh&uptype=shyk" target="_blank">'
						+ data[i].zbwyhCount + '</a>';
			}
			if (data[i].zbdydhCount == 0) {
				zbdydhCount = 0;
			} else {
				zbdydhCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=zbdydh&uptype=shyk" target="_blank">'
						+ data[i].zbdydhCount + '</a>';
			}
			if (data[i].dxzhCount == 0) {
				dxzhCount = 0;
			} else {
				dxzhCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=dxzh&uptype=shyk" target="_blank">'
						+ data[i].dxzhCount + '</a>';
			}
			if (data[i].dkCount == 0) {
				dkCount = 0;
			} else {
				dkCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName + '&type=dk&uptype=shyk" target="_blank">'
						+ data[i].dkCount + '</a>';
			}
			$('.c-table tbody').append(
					'<tr data-id="' + data[i].id + '"><td>' + orgName
							+ '</td><td>' + zbwyhCount + '</td><td>'
							+ zbdydhCount + '</td><td>' + dxzhCount
							+ '</td><td>' + dkCount + '</td></tr>');
		}
		$('tbody td').eq(0).html(data[0].name);
		$('html').animate({
			scrollTop : $(".header").offset().top
		}, 150);
		$('.c-table').unmask();
	}
	$(document)
			.on(
					'click',
					'.org0',
					function() {
						if ($(this).parents('tr').attr('data-id')) {
							thisid = $(this).parents('tr').attr('data-id');
						}
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											$('th')
													.eq(0)
													.html(
															'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
										});
					})
	$(document)
			.on(
					'click',
					'.org1',
					function() {
						thisid = $(this).parents('tr').attr('data-id');
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											if (code == 0) {
												$('th')
														.eq(0)
														.html(
																'<a class="back org0"><i class="backicon"></i></a>');
												$('tr').eq(0).attr('data-id',
														data[0].pid);
											} else {
												$('th')
														.eq(0)
														.html(
																'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
											}
										});
					});
	$(function() {
		brandId = GetQueryString('brandId');
		brandName = GetbrandName();
		$('.c-table').mask('正在加载...请稍后');
		$.get(ctx + '/flow/count?code=' + code + '&id=' + brandId, function(
				data) {
			if (code == 0) {
				data[0].name = '合计';
			}
			setTable(data);
			if (code == 0) {
				$('tbody').find('tr').eq(0).html(
						'<td>' + data[0].name + '</td><td>'
								+ data[0].zbwyhCount + '</td><td>'
								+ data[0].zbdydhCount + '</td><td>'
								+ data[0].dxzhCount + '</td><td>'
								+ data[0].dkCount + '</td>')
			}
		});
	});
}
function Fsjhdtable(ctx) {
	var brandId, thiscode, thisid, flowCount, newsCount, orgName, brandName;
	function setTable(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].orgType == 2) {
				orgName = data[i].name;
			} else {
				orgName = '<a class="orgname org' + data[i].orgType + '">'
						+ data[i].name + '</a>';
			}
			if (data[i].flowCount == 0) {
				flowCount = 0;
			} else {
				flowCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=zbwhy&uptype=sjhd" target="_blank">'
						+ data[i].flowCount + '</a>';
			}
			if (data[i].newsCount == 0) {
				newsCount = 0;
			} else {
				newsCount = '<a class="num" href="' + ctx + codeurl
						+ '/page/xwtj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=zbdydh&uptype=sjhd" target="_blank">'
						+ data[i].newsCount + '</a>';
			}
			$('.c-table tbody').append(
					'<tr data-id="' + data[i].id + '"><td>' + orgName
							+ '</td><td>' + flowCount + '</td><td>' + newsCount
							+ '</td></tr>');
		}
		$('tbody td').eq(0).html(data[0].name);
		$('html').animate({
			scrollTop : $(".header").offset().top
		}, 150);
		$('.c-table').unmask();
	}
	$(document)
			.on(
					'click',
					'.org0',
					function() {
						if ($(this).parents('tr').attr('data-id')) {
							thisid = $(this).parents('tr').attr('data-id');
						}
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											$('th')
													.eq(0)
													.html(
															'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
										});
					})
	$(document)
			.on(
					'click',
					'.org1',
					function() {
						thisid = $(this).parents('tr').attr('data-id');
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											if (code == 0) {
												$('th')
														.eq(0)
														.html(
																'<a class="back org0"><i class="backicon"></i></a>');
												$('tr').eq(0).attr('data-id',
														data[0].pid);
											} else {
												$('th')
														.eq(0)
														.html(
																'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
											}
										});
					});
	$(function() {
		brandId = GetQueryString('brandId');
		brandName = GetbrandName();
		$('.c-table').mask('正在加载...请稍后');
		$.get(ctx + '/flow/count?code=' + code + '&id=' + brandId, function(
				data) {
			if (code == 0) {
				data[0].name = '合计';
			}
			setTable(data);
			if (code == 0) {
				$('tbody').find('tr').eq(0).html(
						'<td>' + data[0].name + '</td><td>' + data[0].flowCount
								+ '</td><td>' + data[0].newsCount + '</td>')
			}
		});
	});
}
function Fsimpletable(ctx) {
	var brandId, thiscode, thisid, count, orgName, brandName;
	function setTable(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].orgType == 2) {
				orgName = data[i].name;
			} else {
				orgName = '<a class="orgname org' + data[i].orgType + '">'
						+ data[i].name + '</a>';
			}
			if (data[i].count == 0) {
				count = 0;
			} else {
				count = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName + '" target="_blank">' + data[i].count
						+ '</a>';
			}
			$('.c-table tbody').append(
					'<tr data-id="' + data[i].id + '"><td>' + orgName
							+ '</td><td>' + count + '</td></tr>');
		}
		$('tbody td').eq(0).html(data[0].name);
		$('html').animate({
			scrollTop : $(".header").offset().top
		}, 150);
		$('.c-table').unmask();
	}
	$(document)
			.on(
					'click',
					'.org0',
					function() {
						if ($(this).parents('tr').attr('data-id')) {
							thisid = $(this).parents('tr').attr('data-id');
						}
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											$('th')
													.eq(0)
													.html(
															'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
										});
					})
	$(document)
			.on(
					'click',
					'.org1',
					function() {
						thisid = $(this).parents('tr').attr('data-id');
						$(".c-table tbody").empty();
						$('.c-table').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											if (code == 0) {
												$('th')
														.eq(0)
														.html(
																'<a class="back org0"><i class="backicon"></i></a>');
												$('tr').eq(0).attr('data-id',
														data[0].pid);
											} else {
												$('th')
														.eq(0)
														.html(
																'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
											}
										});
					});
	$(function() {
		brandId = GetQueryString('brandId');
		brandName = GetbrandName();
		$('.c-table').mask('正在加载...请稍后');
		$.get(ctx + '/flow/count?code=' + code + '&id=' + brandId, function(
				data) {
			if (code == 0) {
				data[0].name = '合计';
			}
			setTable(data);
			if (code == 0) {
				$('tbody').find('tr').eq(0).html(
						'<td>' + data[0].name + '</td><td>' + data[0].count
								+ '</td>')
			}
		});
	});
}
function Ftabletab() {
	$(document).on({
		mouseover : function() {
			i = $(this);
			$('.c-table-tabitem').removeClass('active');
			$('.c-table').hide();
			i.addClass('active');
			$('#' + i.attr('tab')).show();
		},
		mouseout : function() {
			i = $(this);
		}
	}, '.c-table-tabitem');
	$(function() {
		$('.c-table-tabitem').eq(0).mouseover();
	})
}
function Flctj(ctx) {
	var id, brandName, type, uptype, href;
	function pagination(te) {
		var pageIndex = 0;
		var pageSize = 20;
		$(function() {
			// 分页，PageCount是总条目数，这是必选参数，其它参数都是可选
			$('#Pagination').pagination(te, {
				callback : PageCallback, // PageCallback() 为翻页调用次函数。
				prev_text : '上一页',
				next_text : '下一页 ',
				items_per_page : pageSize,
				num_edge_entries : 1, // 两侧首尾分页条目数
				num_display_entries : 4, // 连续分页主体部分分页条目数
				current_page : pageIndex
			});
			// 翻页调用
			function PageCallback(index) {
				InitPage(index);
			}
			// 请求数据
			function InitPage(pageIndex) {
				$
						.get(
								ctx + '/flow/detail/list?id=' + id
										+ '&brandName=' + brandName + '&type='
										+ type + '&pageNum=' + (pageIndex + 1),
								function(data) {
									$('.c-list ul').empty();
									for (var i = 0; i < data[0].data.length; i++) {

										if (uptype == '') {
											href = '/flow/active/detail?id='
													+ data[0].data[i].id;
										} else {
											href = '/flow/detail?id='
													+ data[0].data[i].id
													+ '&type=' + uptype;
										}
										$('.c-list ul')
												.append(
														'<li class="clearfix"><a href="'
																+ ctx
																+ codeurl
																+ href
																+ '" target="_blank">'
																+ data[0].data[i].name
																+ '</a><span class="news-date">'
																+ data[0].data[i].time
																+ '</span></li>');
									}

									$('#Pagination').prepend(
											'<span>共' + te
													+ '条记录,每页显示20条</span>');

								})
			}
		});

	}
	$(function() {
		id = GetQueryString("id");
		brandName = GetQueryString("brandName");
		type = GetQueryString("type");
		uptype = GetQueryString("uptype");
		$.get(ctx + '/flow/detail/list?id=' + id + '&brandName=' + brandName
				+ '&type=' + type, function(data) {
			$('.c-title span:last').html(data[0].name);
			$('.c-list').html('<ul></ul>');
			if (data[0].totalElements > 0) {
				$('.c-list').append(
						'<div id="Pagination" class="pagination"></div>');
				pagination(data[0].totalElements);
			}
		});
	});
}

function Fxwtj(ctx) {
	var id, brandId, type;
	function pagination(te) {
		var pageIndex = 0;
		var pageSize = 20;
		$(function() {
			// 分页，PageCount是总条目数，这是必选参数，其它参数都是可选
			$('#Pagination').pagination(te, {
				callback : PageCallback, // PageCallback() 为翻页调用次函数。
				prev_text : '上一页',
				next_text : '下一页 ',
				items_per_page : pageSize,
				num_edge_entries : 1, // 两侧首尾分页条目数
				num_display_entries : 4, // 连续分页主体部分分页条目数
				current_page : pageIndex
			});
			// 翻页调用
			function PageCallback(index) {
				InitPage(index);
			}
			// 请求数据
			function InitPage(pageIndex) {
				$
						.get(
								ctx + '/article/count?id=' + id + '&brandName='
										+ brandName + '&type=' + type
										+ '&pageNum=' + (pageIndex + 1),
								function(data) {
									$('.c-list ul').empty();
									for (var i = 0; i < data[0].data.length; i++) {
										$('.c-list ul')
												.append(
														'<li class="clearfix"><a href="'
																+ ctx
																+ codeurl
																+ '/article/'
																+ data[0].data[i].id
																+ '" target="_blank">'
																+ data[0].data[i].title
																+ '</a><span class="news-date">'
																+ data[0].data[i].time
																+ '</span></li>');
									}

									$('#Pagination').prepend(
											'<span>共' + te
													+ '条记录,每页显示20条</span>');

								});
			}
		});

	}
	$(function() {
		id = GetQueryString("id");
		type = GetQueryString("type");
		brandName = GetQueryString("brandName");
		$.get(ctx + '/article/count?id=' + id + '&brandName=' + brandName
				+ '&type=' + type, function(data) {
			$('.c-title span:last').html(data[0].name);
			$('.c-list').html('<ul></ul>');
			if (data[0].totalElements > 0) {
				$('.c-list').append(
						'<div id="Pagination" class="pagination"></div>');
				pagination(data[0].totalElements);
			}
		});
	});
}
function Fgzkh(ctx) {
	var thisleft, length, hlength;
	$(function() {
		$('.kpi-header a').eq(0).attr('href',
				ctx + codeurl + '/page/gzkhplan?type=0');
		$('.kpi-header a').eq(1).attr('href',
				ctx + codeurl + '/page/gzkhplan?type=1');
		$
				.get(
						ctx + '/kpi/count/work?code=' + code,
						function(data) {
							$('.left')
									.each(
											function() {
												thisleft = $(this);
												var a = parseInt(eval(data[thisleft
														.attr('data-type')][thisleft
														.attr('name')]
														.split(',')[0]));
												var b = parseInt(eval(data[thisleft
														.attr('data-type')][thisleft
														.attr('name')]
														.split(',')[1]));
												var c = a / (a + b);
												var d = c.toFixed(4);
												var e = d.slice(2, 4) + "%";
												thisleft.css('width', e);
												thisleft
														.html(eval(data[thisleft
																.attr('data-type')][thisleft
																.attr('name')]
																.split(',')[0]));
												thisleft
														.next()
														.html(
																eval(data[thisleft
																		.attr('data-type')][thisleft
																		.attr('name')]
																		.split(',')[1]));
											});
							length = data.cxsj.length;
							if (length <= 14) {
								for (var i = 0; i < length; i++) {
									$('.table-cxsj tr').eq(0).append(
											'<td>' + data.cxsj[i].name
													+ '</td>');
									$('.table-cxsj tr').eq(1).append(
											'<td>' + data.cxsj[i].value
													+ '</td>');
								}
							} else {
								hlength = parseInt(length / 2);
								$('.c-kpi').eq(3).append(
										$('.table-cxsj').clone());
								for (var i = 0; i < hlength; i++) {
									$('.table-cxsj tr').eq(0).append(
											'<td>' + data.cxsj[i].name
													+ '</td>');
									$('.table-cxsj tr').eq(1).append(
											'<td>' + data.cxsj[i].value
													+ '</td>');
								}
								for (var i = hlength; i < length; i++) {
									$('.table-cxsj tr').eq(2).append(
											'<td>' + data.cxsj[i].name
													+ '</td>');
									$('.table-cxsj tr').eq(3).append(
											'<td>' + data.cxsj[i].value
													+ '</td>');
								}
							}
						});
		$.get(ctx + '/kpi/count/shyk?code=' + code,
				function(data) {
			length = data.length;
					if (length <= 14) {
						for (var i = 0; i < length; i++) {
							$('.table-shyk tr').eq(0).append(
									'<td>' + data[i].name + '</td>');
							$('.table-shyk tr').eq(1).append(
									'<td><a target="_blank" href="' + ctx
											+ codeurl
											+ '/page/gzkh_shyk?orgCode='
											+ data[i].code + '&type=0">'
											+ data[i].zbdydh + '</a></td>');
							$('.table-shyk tr').eq(2).append(
									'<td><a target="_blank" href="' + ctx
											+ codeurl
											+ '/page/gzkh_shyk?orgCode='
											+ data[i].code + '&type=1">'
											+ data[i].zbwyh + '</a></td>');
							$('.table-shyk tr').eq(3).append(
									'<td><a target="_blank" href="' + ctx
											+ codeurl
											+ '/page/gzkh_shyk?orgCode='
											+ data[i].code + '&type=2">'
											+ data[i].dxzh + '</a></td>');
							$('.table-shyk tr').eq(4).append(
									'<td><a target="_blank" href="' + ctx
											+ codeurl
											+ '/page/gzkh_shyk?orgCode='
											+ data[i].code + '&type=3">'
											+ data[i].dk + '</a></td>');
						}
					} else {
						hlength = parseInt(length / 2);
						$('.c-kpi').eq(2).append($('.table-shyk').clone());
						for (var i = 0; i < hlength; i++) {
							$('.table-shyk tr').eq(0).append(
									'<td>' + data[i].name + '</td>');
							$('.table-shyk tr').eq(1).append(
									'<td><a target="_blank" href="' + ctx
											+ codeurl
											+ '/page/gzkh_shyk?orgCode='
											+ data[i].code + '&type=0">'
											+ data[i].zbdydh + '</a></td>');
							$('.table-shyk tr').eq(2).append(
									'<td><a target="_blank" href="' + ctx
											+ codeurl
											+ '/page/gzkh_shyk?orgCode='
											+ data[i].code + '&type=1">'
											+ data[i].zbwyh + '</a></td>');
							$('.table-shyk tr').eq(3).append(
									'<td><a target="_blank" href="' + ctx
											+ codeurl
											+ '/page/gzkh_shyk?orgCode='
											+ data[i].code + '&type=2">'
											+ data[i].dxzh + '</a></td>');
							$('.table-shyk tr').eq(4).append(
									'<td><a target="_blank" href="' + ctx
											+ codeurl
											+ '/page/gzkh_shyk?orgCode='
											+ data[i].code + '&type=3">'
											+ data[i].dk + '</a></td>');
						}
						for (var i = hlength; i < length; i++) {
							$('.table-shyk tr').eq(5).append(
									'<td>' + data[i].name + '</td>');
							$('.table-shyk tr').eq(6).append(
									'<td>' + data[i].zbdydh + '</td>');
							$('.table-shyk tr').eq(7).append(
									'<td>' + data[i].zbwyh + '</td>');
							$('.table-shyk tr').eq(8).append(
									'<td>' + data[i].dxzh + '</td>');
							$('.table-shyk tr').eq(9).append(
									'<td>' + data[i].dk + '</td>');
						}
					}
				});
	});
}
function Fgzkhplan(ctx) {
	var thisleft, type;
	$(document).on(
			'click',
			'.kpi-bar span',
			function() {
				window.open(ctx + codeurl + '/page/gzkh_detail?orgCode='
						+ $(this).parents('.kpi-line').attr('data-code')
						+ '&type=' + type);
			})
	$(function() {
		$('.c-title a').eq(1).attr('href', ctx + codeurl + '/page/gzkh');
		type = GetQueryString('type');
		if (type == 0) {
			$('.c-title span:last').html('工作计划');
			$('.kpi-plan span').eq(0).html('年度党建工作计划');
			$('.kpi-plan span').eq(1).html('专项党建活动、主题教育有实施计划方案');
		}
		if (type == 1) {
			$('.c-title span:last').html('工作总结');
			$('.kpi-plan span').eq(0).html('年度党建工作总结');
			$('.kpi-plan span').eq(1).html('专项党建活动、主题教育有总结报告');
		}
		$
				.get(
						ctx + '/kpi/count/workplan?code=' + code + '&type='
								+ type,
						function(data) {
							for (var i = 0; i < data.length; i++) {
								$('.kpi-plan')
										.append(
												'<div class="kpi-line" data-code="'
														+ data[i].code
														+ '"><span class="kpi-title">'
														+ data[i].name
														+ '</span><div class="kpi-bar"><span class="left">'
														+ data[i].niandu
																.split(',')[0]
														+ '</span><span class="right">'
														+ data[i].niandu
																.split(',')[1]
														+ '</span></div><div class="kpi-bar"><span class="left">'
														+ data[i].zhuanxiang
																.split(',')[0]
														+ '</span><span class="right">'
														+ data[i].zhuanxiang
																.split(',')[1]
														+ '</span></div></div>');
							}
							$('.left').each(function() {
								thisleft = $(this);
								var a = parseInt(thisleft.html());
								var b = parseInt(thisleft.next().html());
								var c = a / (a + b);
								var d = c.toFixed(4);
								var e = d.slice(2, 4) + "%";
								thisleft.css("width", e);
							});
						});
	});
}
function Fgzkhshyk(ctx) {
	$(function() {
		$('.c-title a').eq(1).attr('href', ctx + codeurl + '/page/gzkh');
		$('.c-title a').eq(2).attr('href', ctx + codeurl + '/page/gzkh');
		$('.c-title a').eq(3).attr('href', ctx + codeurl + '/page/gzkh');
		var orgCode = GetQueryString('orgCode');
		var type = GetQueryString('type');
		$
				.get(
						ctx + '/kpi/shyk/' + type + '/' + orgCode,
						function(data) {
							$('.c-title span a:last').html(data.partyName);
							$('.c-title span:last').html(data.typeName);
							if (type == 0 || type == 2) {
								$('.kpi-shyk').addClass('kpi-jidu');
								for (var i = 0; i < data.list.length; i++) {
									$('.kpi-shyk')
											.append(
													'<div class="kpi-line"><span class="kpi-title">'
															+ data.list[i].name
															+ '</span><div class="kpi-shykbar"><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span style="border-right: 1px solid rgb(206, 201, 101);">&nbsp;</span></div></div>');
									if (data.list[i].month != '') {
										var arr = data.list[i].month.split(',');
										for (x in arr) {
											var y = arr[x];
											if (y == 0 || y == 1 || y == 2) {
												$('.kpi-shykbar').eq(i).find(
														'span').eq(0).addClass(
														"active");
											}
											if (y == 4 || y == 5 || y == 3) {
												$('.kpi-shykbar').eq(i).find(
														'span').eq(1).addClass(
														"active");
											}
											if (y == 8 || y == 7 || y == 6) {
												$('.kpi-shykbar').eq(i).find(
														'span').eq(2).addClass(
														"active");
											}
											if (y == 9 || y == 10 || y == 11) {
												$('.kpi-shykbar').eq(i).find(
														'span').eq(3).addClass(
														"active");
											}
										}
									}
								}
							} else {
								for (var i = 0; i < data.list.length; i++) {
									$('.kpi-shyk')
											.append(
													'<div class="kpi-line"><span class="kpi-title">'
															+ data.list[i].name
															+ '</span><div class="kpi-shykbar"><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span style="border-right: 1px solid rgb(206, 201, 101);">&nbsp;</span></div></div>');
									if (data.list[i].month != '') {
										var arr = data.list[i].month.split(',');
										for (x in arr) {
											var y = arr[x];
											$('.kpi-shykbar').eq(i)
													.find('span').eq(y)
													.addClass("active");
										}
									}
								}
							}
						});
	});
}
function Fgzkhdetail(ctx) {
	$(function() {
		$('.c-title a').eq(1).attr('href', ctx + codeurl + '/page/gzkh');
		var orgCode = GetQueryString('orgCode');
		var type = GetQueryString('type');
		if (type == 0) {
			$('.c-title span')
					.eq(-2)
					.html(
							'<a href="'
									+ ctx
									+ codeurl
									+ '/page/gzkhplan?type=0">工作计划</a>&nbsp;&gt;&nbsp;');
		}
		if (type == 1) {
			$('.c-title span')
					.eq(-2)
					.html(
							'<a href="'
									+ ctx
									+ codeurl
									+ '/page/gzkhplan?type=1">工作总结</a>&nbsp;&gt;&nbsp;');
			$('.c-kpi span').eq(0).html('年度党建工作总结');
			$('.c-kpi span').eq(1).html('专项党建活动、主题教育与总结报告');
		}
		$
				.get(
						ctx + '/kpi/workplan/detail?code=' + orgCode + '&type='
								+ type,
						function(data) {
							$('.c-title span:last').html(data[0].orgName);
							for (var i = 1; i < data.length; i++) {
								$('.c-kpi')
										.append(
												'<div class="kpi-detailbar"><span class="kpi-Dtitle">'
														+ data[i].name
														+ '</span><span class="kpi-DbtnL">&nbsp;</span><span class="kpi-DbtnR">&nbsp;</span></div>');
								if (data[i].niandu == true) {
									$('.kpi-DbtnL').eq(i - 1)
											.addClass('active');
								}
								if (data[i].zhuanxiang == true) {
									$('.kpi-DbtnR').eq(i - 1)
											.addClass('active');
								}
							}
						});
	});
}
function Fdygl() {
	$('#tab3').empty();
	$('#tab3')
			.append(
					'<div id="div-ksrk"><a href="javscript:void(0)"><button id="btn-ksrk">进入</button><a/></div>');
	// 开发中提示。。
	function wait() {
		var sWidth = $(window).width();
		var sHeight = $(window).height();
		var vHeight = $(document).height();
		var html = '<div class="c-mask"></div>\
				<div class="c-waiting">\
					<div class="wait-title">\
						<span class="dh-pic"></span>\
						<div id="c-close" class="c-close"></div>\
					</div>\
					<div class="wait-content">总公司教育培训接口正在开发中，请耐心等待...</div>\
				</div>';
		$("body").append(html);
		$(".c-mask").css({
			"width" : sWidth + 'px',
			"height" : vHeight + 'px'
		});
		var dHeight = $(".c-waiting").height();
		var dWidth = $(".c-waiting").width();
		var offWidth = (sWidth - dWidth) / 2;
		var offHeight = (sHeight - dHeight) / 2;
		$(".c-waiting").css({
			"left" : offWidth + 'px',
			"top" : offHeight + 'px'
		});
	}
	;
	$(document).on("click", "#btn-ksrk", function() {
		wait();
		return false;
	});
	$(document).on("click", "#c-close", function() {
		$(".c-waiting,.c-mask").remove();
	})
};
function Fdjxttable(ctx) {
	var brandId, thiscode, thisid, count, orgName, brandName;
	function setTable(data) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].orgType == 2) {
				orgName = data[i].name;
			} else {
				orgName = '<a class="orgname org' + data[i].orgType + '">'
						+ data[i].name + '</a>';
			}
			if (data[i].count == 0) {
				count = 0;
			} else {
				count = '<a class="num" href="' + ctx + codeurl
						+ '/page/lctj?id=' + data[i].id + '&brandName='
						+ brandName
						+ '&type=djxt&uptype=djxt" target="_blank">'
						+ data[i].count + '</a>';
			}
			$('#tabb tbody').append(
					'<tr data-id="' + data[i].id + '"><td>' + orgName
							+ '</td><td>' + count + '</td></tr>');
		}
		$('#tabb').find('tbody td').eq(0).html(data[0].name);
		$('html').animate({
			scrollTop : $(".header").offset().top
		}, 150);
		$('#tabb').unmask();
	}
	$(document)
			.on(
					'click',
					'.org0',
					function() {
						if ($(this).parents('tr').attr('data-id')) {
							thisid = $(this).parents('tr').attr('data-id');
						}
						$("#tabb tbody").empty();
						$('#tabb').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											$('#tabb')
													.find('th')
													.eq(0)
													.html(
															'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
										});
					})
	$(document)
			.on(
					'click',
					'.org1',
					function() {
						thisid = $(this).parents('tr').attr('data-id');
						$("#tabb tbody").empty();
						$('#tabb').mask('正在加载...请稍后');
						$
								.get(
										ctx + '/subFlow/count?id=' + thisid
												+ '&brandId=' + brandId,
										function(data) {
											setTable(data);
											if (code == 0) {
												$('#tabb')
														.find('th')
														.eq(0)
														.html(
																'<a class="back org0"><i class="backicon"></i></a>');
												$('tr').eq(0).attr('data-id',
														data[0].pid);
											} else {
												$('#tabb')
														.find('th')
														.eq(0)
														.html(
																'<a class="back" href="javascript:window.location.reload();"><i class="backicon"></i></a>');
											}
										});
					});
	$(function() {
		brandId = GetQueryString('brandId');
		brandName = GetbrandName();
		$('#tabb').mask('正在加载...请稍后');
		$.get(ctx + '/flow/count?code=' + code + '&id=' + brandId, function(
				data) {
			if (code == 0) {
				data[0].name = '合计';
			}
			setTable(data);
			if (code == 0) {
				$('#tabb').find('tbody tr').eq(0).html(
						'<td>' + data[0].name + '</td><td>' + data[0].count
								+ '</td>')
			}
		});
	});
}
function Fdjxttab(ctx) {
	$(document).on({
		mouseover : function() {
			i = $(this);
			$('.lv2-tabitem').removeClass('active');
			$('.t-djxt').hide();
			i.addClass('active');
			$('#' + i.attr('tab')).show();
		},
		mouseout : function() {
			i = $(this);
		}
	}, '.lv2-tabitem');
	$(function() {
		$('.lv2-tabitem').eq(0).mouseover();
		if (code != 3200) {
			$('#taba').empty();
			$('#taba').css({
				'background' : 'url("' + ctx + '/resources/images/djxt.png")',
				'height' : '260px',
				'margin' : ' 5px auto',
				'width' : '900px'
			});
		}
	})
}
function Fplctj(ctx) {
	var id, brandName, type, uptype, href;
	function pagination(te) {
		var pageIndex = 0;
		var pageSize = 20;
		$(function() {
			// 分页，PageCount是总条目数，这是必选参数，其它参数都是可选
			$('#Pagination').pagination(te, {
				callback : PageCallback, // PageCallback() 为翻页调用次函数。
				prev_text : '上一页',
				next_text : '下一页 ',
				items_per_page : pageSize,
				num_edge_entries : 1, // 两侧首尾分页条目数
				num_display_entries : 4, // 连续分页主体部分分页条目数
				current_page : pageIndex
			});
			// 翻页调用
			function PageCallback(index) {
				InitPage(index);
			}
			// 请求数据
			function InitPage(pageIndex) {
				$
						.get(
								ctx + '/flow/detail/list2?code=' + code
										+ '&brandName=' + brandName + '&type='
										+ type + '&pageNum=' + (pageIndex + 1),
								function(data) {
									$('.c-list ul').empty();
									for (var i = 0; i < data[0].data.length; i++) {

										if (uptype == '') {
											href = '/flow/active/detail?id='
													+ data[0].data[i].id;
										} else {
											href = '/flow/detail?id='
													+ data[0].data[i].id
													+ '&type=' + uptype;
										}
										$('.c-list ul')
												.append(
														'<li class="clearfix"><a href="'
																+ ctx
																+ codeurl
																+ href
																+ '" target="_blank">'
																+ data[0].data[i].name
																+ '</a><span class="news-date">'
																+ data[0].data[i].time
																+ '</span></li>');
									}

									$('#Pagination').prepend(
											'<span>共' + te
													+ '条记录,每页显示20条</span>');

								})
			}
		});

	}
	$(function() {
		id = GetQueryString("id");
		brandName = GetQueryString("brandName");
		type = GetQueryString("type");
		uptype = GetQueryString("uptype");
		$.get(ctx + '/flow/detail/list2?code=' + code + '&brandName=' + brandName
				+ '&type=' + type, function(data) {
			$('.c-title span:last').html(data[0].name);
			$('.c-list').html('<ul></ul>');
			if (data[0].totalElements > 0) {
				$('.c-list').append(
						'<div id="Pagination" class="pagination"></div>');
				pagination(data[0].totalElements);
			}
		});
	});
}