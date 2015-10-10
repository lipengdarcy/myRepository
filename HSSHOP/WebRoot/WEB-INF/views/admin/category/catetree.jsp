<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.tree-expand-icon{
		cursor:pointer;
		font-size:20px;
		margin:8px;
	}
	.tree-text{
		font-size:15px;
	}
	.tree-out-content{
		 padding:20px;
	}
	.tree-out-content ul{
		margin-left:16px;
	}
	.tree-out-content ul li{
		margin-top:3px;
		margin-bottom:3px;
	}
	
	.sel-node-btn{
		float:right;
		 font-size:16px;
		 color:blue;
		 margin-right:20px;
		 cursor:pointer;
	}
	.tree-text.last-node{
		margin-left:18px;
	}
</style>
</head>
<body>
	<div class="tree-out-content">
		<ul>
			<li>
				<span class="tree-expand-icon" par-id="0">+</span><span class="tree-text">分类节点</span>
			</li>
		</ul>
	</div>
</body>
</html>