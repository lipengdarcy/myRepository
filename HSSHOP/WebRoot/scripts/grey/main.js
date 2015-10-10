// JavaScript Document
$(function() {

	$(".categorys").on({
		mouseenter : function() {
			$(".nav-info").show();
		},
		mouseleave : function() {
			$(".nav-info").hide();
			$(".info>li").removeClass('active');
			$(".nav-detail>ul").hide();
		}
	})
	$('.categorys .title').click(function() {
		$(".nav-info").toggle();
		$(".info>li").removeClass('active');
		$(".nav-detail>ul").hide();
	})

	/* 子菜单点击事件 */
	$(".info>li").on({
		mouseenter : function() {
			var li_index = $(this).index();
			$(this).addClass('active');
			$(this).siblings().removeClass('active');
			$(".son-info" + li_index).show();
			$(".son-info" + li_index).siblings().hide();
		}
	})

	/* 右侧菜单点击事件 */
	$(".left>li").on({
		mouseenter : function() {
			$(this).find(".son-info").show();
			$(this).siblings().find(".son-info").hide();
		},
		mouseleave : function() {
			$(this).find(".son-info").hide();
		}
	})

	/* 底部图片轮播效果 */
	$(".next").click(function() {
		var i = 6;
		var ulwidth = $(".link-ul").width() / i;
		var len = $(".link-ul").find("li").length;
		var page_count = Math.ceil(len / i);

		if (!$(".link-ul>ul").is(":animated")) {
			var ulleft = $(".link-ul>ul").position().left;
			if (ulleft == 0) {
				$(".link-ul>ul").animate({
					left : '-=' + ulwidth * (len - i)
				});
			} else {
				$(".link-ul>ul").animate({
					left : '+=' + ulwidth
				}, "slow");
			}
		}
	});

	$(".prev").click(function() {
		var i = 6;
		var ulwidth = $(".link-ul").width() / i;
		var len = $(".link-ul").find("li").length;
		var ulleft = $(".link-ul>ul").position().left;

		if (!$(".link-ul>ul").is(":animated")) {
			if ((ulleft - 1) < -ulwidth * (len - i)) {
				$(".link-ul>ul").animate({
					left : '0px'
				});

			} else {
				$(".link-ul>ul").animate({
					left : '-=' + ulwidth
				}, "slow");
			}
		}
	})

	$(".fixed-close").click(function() {
		$(".fixed-menu").hide();
	})

	/* 加减商品数量 */
	$(".del").click(function() {
		var proAmount = parseInt($(".product-amount").val())
		proAmount--;
		if (proAmount >= 0) {
			$(".product-amount").val(proAmount);
		}
	})
	$(".add").click(function() {
		var proAmount = parseInt($(".product-amount").val())
		proAmount++;
		$(".product-amount").val(proAmount);
	})

	// 商品详情tab切换
	$(".detail-tab li").on({
		click : function() {
			var li_index = $(this).index();
			$(this).addClass("active");
			$(this).siblings().removeClass("active");
			$(".detail" + li_index).show();
			$(".detail" + li_index).siblings("div").hide();
		}
	})

	// 经销商门户左侧导航展开
	$(".use-tab-nav .second>i").click(function() {
		$(this).closest('li').find('ul').toggle();

		if ($(this).closest('li').find('ul').is(':hidden')) {
			$(this).css('background-position', '0 6px');
			$(this).closest('li').removeClass("active");
		} else {
			$(this).css('background-position', '0 -23px');
			$(".use-tab-nav").find('li').removeClass('active');
			$(this).closest('li').addClass("active");
			$(".no-childul>i").css('background-position', '0 6px');
		}
	})
	$(".use-tab-nav .no-childul>i").click(function() {
		if ($(this).css("background-position") == "0px 6px") {
			$(".no-childul>i").css('background-position', '0 6px');
			$(this).css('background-position', '0 -23px');
			$(".use-tab-nav").find('li').removeClass('active');
			$(this).closest('li').addClass("active");
		} else {
			$(this).closest('li').removeClass("active");
			$(this).css('background-position', '0 6px');
		}
	})

	$(".use-nav-sec li").click(function() {
		$(".use-tab-nav").find('li').removeClass('active');
		$(this).addClass("active");
	})
	// $(".user-content-tab>li").click(function() {
	// $(this).addClass("active");
	// $(this).siblings().removeClass("active");
	// })
})
