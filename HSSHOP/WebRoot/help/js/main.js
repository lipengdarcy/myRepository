// JavaScript Document
$(function(){
	$(".nav_right li").click(function(){
			$(this).addClass("checked");
			$(this).find("a").addClass("achecked");
			$(this).siblings().removeClass("checked");	
			$(this).siblings().find("a").removeClass("achecked");
			var li_class=$(this).attr('id');
			var mainshow_class=li_class+"_detail";
			$(".main_show").hide();
		    $("."+mainshow_class).show();
			$(".son_nav").hide();
				
		})
	$(".nav_li>a").click(function(){		
		/*左侧菜单导航控制*/
		$(this).parent().css("background","#bfbdbe")
		$(this).parent().siblings().css("background","#e5e5e5");
		$(this).parent().find(".son_nav").toggle();
		if($(this).parent().find("div").hasClass("arrow-down")){
		$(this).parent().find(".arrow-down").removeClass("arrow-down").addClass("arrow-up");
		}else{
			$(this).parent().find(".arrow-up").removeClass("arrow-up").addClass("arrow-down");
			}
		$(this).parent().siblings().find(".son_nav").hide();
		$(this).parent().siblings().find("div").attr("class","arrow-down")
	})

		/*左侧子菜单链接控制*/
		$(".son_nav li").click(function(){
			var li_class=$(this).attr('class');
			var mainshow_class=li_class+"_detail";
			$("."+mainshow_class).siblings().hide();
			$("."+mainshow_class).show();
			});	
			/*底部导航链接控制*/
		$(".footerBox li").click(function(){
			var li_class=$(this).attr('class');
			var mainshow_class=li_class+"_detail";
			$("."+mainshow_class).siblings().hide();
			$("."+mainshow_class).show();
			})
			$(".footerBox h3").click(function(){
			var li_class=$(this).attr('class');
			var mainshow_class=li_class+"_detail";
			$(".main_show").hide();
			$("."+mainshow_class).show();
			})
	})
