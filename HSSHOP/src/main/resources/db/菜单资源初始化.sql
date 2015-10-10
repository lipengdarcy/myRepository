
delete from SYS_RESOURCE;

-- 顶级菜单 
INSERT INTO `SYS_RESOURCE`
(`id`,`pId`,`name`,`resourceStr`,`disOrder`,`memo`) VALUES 
(1, null,'所有资源','system setting', 0, '所有资源'),
(2, 1,'系统设置','system setting', 1, '系统设置'),
(3, 1,'产品管理','system setting', 2, '产品管理'),
(4, 1,'文章管理','system setting', 3, '文章管理'),
(5, 1,'用户管理','system setting', 4, '用户管理'),
(6, 1,'区域管理','system setting', 5, '区域管理'),
(7, 1,'订单管理','system setting', 6, '订单管理');

-- 二级菜单
INSERT INTO `SYS_RESOURCE`
(`pId`,`name`,`resourceStr`,`disOrder`,`memo`) VALUES 

(2,'管理员信息管理','admin/adminuser.do', 1, 'page5'),
(2,'网站配置','admin/webinfor.do?method=toUpdate', 2, 'page6'),
(2,'上传配置','admin/webinfor.do?method=toUpdateUpload', 3, 'page13'),
(2,'邮箱配置','admin/mailconfig.do?method=toUpdate', 4, 'page7'),
(2,'计量单位','admin/adminunit.do', 5, 'page14'),
(2,'短信查看','admin/message.do?method=toMessageList', 6, 'page14'),
(2,'行政区域管理','admin/areamanager.do', 7, 'areamanager'),
		
(3,'产品分类','admin/category.do', 1, 'page8'),
(3,'添加SKU','admin/adminproduct.do?method=toAddSKU', 2, 'page10'),
(3,'在售商品','admin/adminproduct.do?method=skugroupList', 3, 'page11'),
(3,'下架商品','admin/adminproduct.do?method=downList', 4, 'page12'),

(4,'帮助管理','admin/helps/helpslist.do', 1, 'page10'),
(4,'帮助类型管理','admin/helps/helpstypelist.do', 2, 'page45'),
(4,'新闻管理','admin/newsMng/news.do', 3, 'page11'),
(4,'新闻类型管理','admin/newsMng/newstype.do', 4, 'page12'),

(5,'用户列表','admin/user/userlist.do', 8, 'userpage1'),
(5,'申请列表','admin/user/applylist.do', 8, 'page45'),

(6,'区域运费','regionprice/shipList.do', 1, 'page41'),
(6,'区域装卸费','regionprice/handList.do', 2, 'page42'),
(6,'区域价格','regionprice/priceList.do', 3, 'page43'),
(6,'工厂管理','admin/area/factoryaddress.do', 4, 'page49'),
(6,'工厂区域管理','admin/area/factoryregions.do', 5, 'page50'),
(6,'门店管理','admin/area/storeaddress.do', 6, 'page51'),
(6,'门店区域管理','admin/area/storeregions.do', 7, 'page52'),
(6,'区域品牌','regionbrand/brandList.do', 8, 'page45'),
(6,'品牌产地','regionfactory/factoryList.do', 9, 'page46'),

(7,'订单列表','admin/order/index.do', 1, 'page44'),
(7,'评价列表','admin/comment/commentlist.do', 2, 'page40');





			
	

