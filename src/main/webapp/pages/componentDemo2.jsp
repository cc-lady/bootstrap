<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%><!-- 标签：c标签，spring标签等 -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<h1>你好，世界！</h1>

	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${ctx}/jquery/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

	<b>http://localhost:9011/pages/componentDemo2.jsp</b>
	<h2>1.列表组</h2>
	列表组是灵活又强大的组件，不仅能用于显示一组简单的元素，还能用于复杂的定制的内容。
	<h3>1.1基本实例</h3>
	最简单的列表组仅仅是一个带有多个列表条目的无序列表，另外还需要设置适当的类。我们提供了一些预定义的样式，你可以根据自身的需求通过 CSS
	自己定制。
	<ul class="list-group">
		<li class="list-group-item">Cras justo odio</li>
		<li class="list-group-item">Dapibus ac facilisis in</li>
		<li class="list-group-item">Morbi leo risus</li>
		<li class="list-group-item">Porta ac consectetur ac</li>
		<li class="list-group-item">Vestibulum at eros</li>
	</ul>

	<h3>1.2徽章</h3>
	给列表组加入徽章组件，它会自动被放在右边。
	<ul class="list-group">
		<li class="list-group-item"><span class="badge">14</span> Cras
			justo odio</li>
	</ul>

	<h3>1.3链接</h3>
	用 &lt;a&gt;标签代替 &lt;li&gt; 标签可以组成一个全部是链接的列表组（还要注意的是，我们需要将
	&lt;ul&gt;标签替换为 &lt;div&gt;标签）。没必要给列表组中的每个元素都加一个父元素。
	<div class="list-group">
		<a href="#" class="list-group-item active"> Cras justo odio </a> <a
			href="#" class="list-group-item">Dapibus ac facilisis in</a> <a
			href="#" class="list-group-item">Morbi leo risus</a> <a href="#"
			class="list-group-item">Porta ac consectetur ac</a> <a href="#"
			class="list-group-item">Vestibulum at eros</a>
	</div>

	<h3>1.4按钮</h3>
	列表组中的元素也可以直接就是按钮（也同时意味着父元素必须是 &lt;div&gt; 而不能用&lt;ul&gt;
	了），并且无需为每个按钮单独包裹一个父元素。注意不要使用标准的 .btn 类！
	<div class="list-group">
		<button type="button" class="list-group-item">Cras justo odio</button>
		<button type="button" class="list-group-item">Dapibus ac
			facilisis in</button>
		<button type="button" class="list-group-item">Morbi leo risus</button>
		<button type="button" class="list-group-item">Porta ac
			consectetur ac</button>
		<button type="button" class="list-group-item">Vestibulum at
			eros</button>
	</div>

	<h3>1.5被禁用的条目</h3>
	为 .list-group-item 添加 .disabled 类可以让单个条目显示为灰色，表现出被禁用的效果。
	<div class="list-group">
		<a href="#" class="list-group-item disabled"> Cras justo odio </a> <a
			href="#" class="list-group-item">Dapibus ac facilisis in</a> <a
			href="#" class="list-group-item">Morbi leo risus</a> <a href="#"
			class="list-group-item">Porta ac consectetur ac</a> <a href="#"
			class="list-group-item">Vestibulum at eros</a>
	</div>

	<h3>1.6情境类</h3>
	为列表中的条目添加情境类，默认样式或链接列表都可以。还可以为列表中的条目设置 .active 状态。
	<ul class="list-group">
		<li class="list-group-item list-group-item-success">Dapibus ac
			facilisis in</li>
		<li class="list-group-item list-group-item-info">Cras sit amet
			nibh libero</li>
		<li class="list-group-item list-group-item-warning">Porta ac
			consectetur ac</li>
		<li class="list-group-item list-group-item-danger">Vestibulum at
			eros</li>
	</ul>
	<div class="list-group">
		<a href="#" class="list-group-item list-group-item-success">Dapibus
			ac facilisis in</a> <a href="#"
			class="list-group-item list-group-item-info">Cras sit amet nibh
			libero</a> <a href="#" class="list-group-item list-group-item-warning">Porta
			ac consectetur ac</a> <a href="#"
			class="list-group-item list-group-item-danger">Vestibulum at eros</a>
	</div>

	<h3>1.7定制内容</h3>
	列表组中的每个元素都可以是任何 HTML 内容，甚至是像下面的带链接的列表组。
	<div class="list-group">
		<a href="#" class="list-group-item active">
			<h4 class="list-group-item-heading">List group item heading</h4>
			<p class="list-group-item-text">Donect id elit non mi</p>
		</a>
	</div>

	<h2>2.面版</h2>
	虽然不总是必须，但是某些时候你可能需要将某些 DOM 内容放到一个盒子里。对于这种情况，可以试试面板组件。
	<h3>2.1基本实例</h3>
	默认的 .panel 组件所做的只是设置基本的边框（border）和内补（padding）来包含内容。
	<div class="panel panel-default">
		<div class="panel-body">Basic panel example</div>
	</div>

	<h3>2.2带标题的面版</h3>
	通过 .panel-heading 可以很简单地为面板加入一个标题容器。你也可以通过添加设置了 .panel-title 类的
	&lt;h1&gt;-&lt;h6&gt;标签，添加一个预定义样式的标题。不过，&lt;h1&gt;-&lt;h6&gt;标签的字体大小将被
	.panel-heading 的样式所覆盖。 为了给链接设置合适的颜色，务必将链接放到带有 .panel-title 类的标题标签内。
	<div class="panel panel-default">
		<div class="panel-heading">Panel heading without title</div>
		<div class="panel-body">Panel content</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Panel title</h3>
		</div>
		<div class="panel-body">Panel content</div>
	</div>

	<h3>2.3带脚注的面版</h3>
	把按钮或次要的文本放入 .panel-footer 容器内。注意面版的脚注不会从情境效果中继承颜色，因为他们并不是主要内容。
	<div class="panel panel-default">
		<div class="panel-body">Panel content</div>
		<div class="panel-footer">Panel footer</div>
	</div>

	<h3>2.4情境效果</h3>
	像其他组件一样，可以简单地通过加入有情境效果的状态类，给特定的内容使用更针对特定情境的面版。
	<div class="panel panel-primary">
		<div class="panel-body">Panel content</div>
		<div class="panel-footer">Panel footer</div>
	</div>
	<div class="panel panel-success">
		<div class="panel-body">Panel content</div>
		<div class="panel-footer">Panel footer</div>
	</div>
	<div class="panel panel-info">
		<div class="panel-body">Panel content</div>
		<div class="panel-footer">Panel footer</div>
	</div>
	<div class="panel panel-warning">
		<div class="panel-body">Panel content</div>
		<div class="panel-footer">Panel footer</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">Panel content</div>
		<div class="panel-footer">Panel footer</div>
	</div>

	<h3>2.5带表格的面版</h3>
	为面板中不需要边框的表格添加 .table 类，是整个面板看上去更像是一个整体设计。如果是带有 .panel-body
	的面板，我们为表格的上方添加一个边框，看上去有分隔效果。
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Panel heading</div>
		<div class="panel-body">
			<p>Some default panel content here. Nulla vitae elit libero, a
				pharetra augue. Aenean lacinia bibendum nulla sed consectetur.
				Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis
				vestibulum. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
		</div>

		<!-- Table -->
		<table class="table">
			<tbody>
				<tr>
					<td>#</td>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Username</td>
				</tr>
				<tr>
					<td>1</td>
					<td>Mark</td>
					<td>Otto</td>
					<td>@mdo</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Jacob</td>
					<td>Thornton</td>
					<td>@fat</td>
				</tr>
				<tr>
					<td>3</td>
					<td>Larry</td>
					<td>the Bird</td>
					<td>@twitter</td>
				</tr>
			</tbody>
		</table>
	</div>

	<h3>2.6带列表组的面版</h3>
	可以简单地在任何面版中加入具有最大宽度的列表组。
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Panel heading</div>
		<div class="panel-body">
			<p>...</p>
		</div>

		<!-- List group -->
		<ul class="list-group">
			<li class="list-group-item">Cras justo odio</li>
			<li class="list-group-item">Dapibus ac facilisis in</li>
			<li class="list-group-item">Morbi leo risus</li>
			<li class="list-group-item">Porta ac consectetur ac</li>
			<li class="list-group-item">Vestibulum at eros</li>
		</ul>
	</div>


	<h3>2.7具有响应式特性的嵌入内容</h3>
	<!-- 这个好像不能用 -->
	根据被嵌入内容的外部容器的宽度，自动创建一个固定的比例，从而让浏览器自动确定视频或 slideshow 的尺寸，能够在各种设备上缩放。
	<br /> 这些规则被直接应用在 &lt;iframe&gt;、&lt;embed&gt;、&lt;video&gt; 和
	&lt;object&gt; 元素上。如果你希望让最终样式与其他属性相匹配，还可以明确地使用一个派生出来的
	.embed-responsive-item 类。
	<br /> 超级提示： 不需要为 &lt;iframe&gt; 元素设置 frameborder="0" 属性，因为我们已经替你这样做了！
	<br />
	<!-- 16:9 aspect ratio -->
	<div class="embed-responsive embed-responsive-16by9">
		<iframe class="embed-responsive-item" src="${ctx}/image/Tulips.jpg"></iframe>
	</div>

	<!-- 4:3 aspect ratio -->
	<div class="embed-responsive embed-responsive-4by3">
		<iframe class="embed-responsive-item" src="${ctx}/image/Tulips.jpg"></iframe>
	</div>

	<h2>3.Well</h2>
	<h3>3.1默认效果</h3>
	把 Well 用在元素上，就能有嵌入（inset）的简单效果。
	<div class="well">Look, I'm in a well!</div>

	<h3>3.2可选类/样式</h3>
	通过这两种可选修饰类，可以控制此组件的内补（padding）和圆角的设置
	<div class="well well-lg">Look, I'm in a well!</div>
	<div class="well well-sm">Look, I'm in a well!</div>
</body>
</html>