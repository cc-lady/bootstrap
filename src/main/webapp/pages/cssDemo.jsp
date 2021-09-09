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
<title>Bootstrap css demo</title>

<!-- Bootstrap -->
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style type="text/css">
.col-sm-9 {
	background-color: yellow;
}

.col-xs-8 {
	background-color: red;
}

.col-xs-4 {
	background-color: green;
}
</style>

</head>
<body>
	<h1>css demo study</h1>

	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${ctx}/jquery/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

	<b>请通过访问http://localhost:9011/test/cssDemo跳转到此页面而不是直接http://localhost:9011/pages/cssDemo.jsp</b>
	<h2>1.表格排列</h2>
	<div class="container" id="users">
		<div class="row">
			<div class="col-xs-1 col-md-1">序号</div>
			<div class="col-xs-1 col-md-1">姓名</div>
			<div class="col-xs-1 col-md-1">手机号</div>
			<div class="col-xs-1 col-md-1">地址</div>
			<div class="col-xs-1 col-md-2">email</div>
			<div class="col-xs-1 col-md-1">用户权限</div>
			<div class="col-xs-1 col-md-1">备注</div>
		</div>
		<%-- div>Name: ${list[0].userName}</div> --%>
		<c:forEach items="${requestScope.list}" var="user">
			<div class="row">
				<div class="col-xs-1 col-md-1">${user.id}</div>
				<div class="col-xs-1 col-md-1">${user.userName}</div>
				<div class="col-xs-1 col-md-1">${user.mobilePhone}</div>
				<div class="col-xs-1 col-md-1">${user.address}</div>
				<div class="col-xs-1 col-md-2">${user.email}</div>
				<div class="col-xs-1 col-md-1">${user.role}</div>
				<div class="col-xs-1 col-md-1">${user.note}</div>
			</div>
		</c:forEach>
	</div>
	<h3>1.1嵌套</h3>
	<div class="container">
		<div class="row">
			<div class="col-sm-9">
				Level 1: .col-sm-9
				<div class="row">
					<div class="col-xs-8 col-sm-6">Level 2: .col-xs-8 .col-sm-6</div>
					<div class="col-xs-4 col-sm-6">Level 2: .col-xs-4 .col-sm-6</div>
				</div>
			</div>
		</div>
	</div>

	<h3>1.2排序</h3>
	<div class="container">
		<div class="row">
			<div class="col-md-9 col-md-push-3">.col-md-9 .col-md-push-3</div>
			<div class="col-md-3 col-md-pull-9">.col-md-3 .col-md-pull-9</div>
		</div>
	</div>

	<h2>2.排版</h2>
	<h3>2.1标题</h3>
	<p>HTML 中的所有标题标签，&lt;h1&gt; 到 &lt;h6&gt; 均可使用。另外，还提供了 .h1 到 .h6
		类，为的是给内联（inline）属性的文本赋予标题的样式。 在标题内还可以包含 &lt;small&gt;标签或赋予 .small
		类的元素，可以用来标记副标题。</p>
	<h1>
		h1. Bootstrap heading <small>Secondary text</small>
	</h1>
	<h2>
		h2. Bootstrap heading <small>Secondary text</small>
	</h2>
	<h3>
		h3. Bootstrap heading <small>Secondary text</small>
	</h3>
	<h4>
		h4. Bootstrap heading <small>Secondary text</small>
	</h4>
	<h5>
		h5. Bootstrap heading <small>Secondary text</small>
	</h5>
	<h6>
		h6. Bootstrap heading <small>Secondary text</small>
	</h6>

	<h3>2.2页面主体</h3>
	<p>Bootstrap 将全局 font-size 设置为 14px，line-height 设置为 1.428。这些属性直接赋予
		&lt;body&gt; 元素和所有段落元素。另外，&lt;p&gt; （段落）元素还被设置了等于 1/2 行高（即
		10px）的底部外边距（margin）。 通过添加 .lead 类可以让段落突出显示。</p>
	<p class="lead">lead段落：哈啊哈哈哈哈哈哈</p>
	<p>普通段落：哈啊哈哈哈哈哈哈</p>

	<h3>2.3内联文本元素</h3>
	<p>
		&lt;mark&gt;高亮：You can use the mark tag to
		<mark>highlight</mark>
		text.
	</p>
	<p>
		&lt;del&gt;显示被删除的文本：
		<del>This line of text is meant to be treated as deleted text.</del>
	</p>
	<p>
		&lt;s&gt;没用的文本：<s>This line of text is meant to be treated as no
			longer accurate.</s>
	</p>
	<p>
		&lt;ins&gt;额外插入的文本：
		<ins>This line of text is meant to be treated as an addition to
			the document.</ins>
	</p>
	<p>
		&lt;u&gt;为文本添加下划线：<u>This line of text will render as underlined</u>
	</p>
	<p>
		&lt;small&gt;小号文本：<small>This line of text is meant to be
			treated as fine print.</small>
	</p>
	<p>
		&lt;strong&gt;强调一段文本：<strong>rendered as bold text</strong>
	</p>
	<p>
		&lt;em&gt;斜体强调一段文本：The following snippet of text is <em>rendered
			as italicized text</em>
	</p>
	Alternate elements
	<br /> 在 HTML5 中可以放心使用&lt;b&gt; 和 &lt;i&gt; 标签。&lt;b&gt;
	用于高亮单词或短语，不带有任何着重的意味；而 &lt;i&gt; 标签主要用于发言、技术词汇等。

	<h3>2.4对齐</h3>
	<p class="text-left">Left aligned text.</p>
	<p class="text-center">Center aligned text.</p>
	<p class="text-right">Right aligned text.</p>
	<p class="text-justify">Justified text.</p>
	<p class="text-nowrap">No wrap text.</p>

	<h3>2.5改变大小写</h3>
	<p class="text-lowercase">Lowercased text.</p>
	<p class="text-uppercase">Uppercased text.</p>
	<p class="text-capitalize">Capitalized text.</p>

	<h3>2.6缩略语</h3>
	当鼠标悬停在缩写和缩写词上时就会显示完整内容，Bootstrap 实现了对 HTML 的 &lt;abbr&gt;
	元素的增强样式。缩略语元素带有 title 属性，
	外观表现为带有较浅的虚线框，鼠标移至上面时会变成带有“问号”的指针。如想看完整的内容可把鼠标悬停在缩略语上（对使用辅助技术的用户也可见）,
	但需要包含 title 属性。
	<div>a.基本缩略语</div>
	<abbr title="attribute">attr</abbr>
	<div>b.首字母缩略语</div>
	<abbr title="HyperText Markup Language" class="initialism">HTML</abbr>

	<h3>2.7地址</h3>
	让联系信息以最接近日常使用的格式呈现。在每行结尾添加&lt;br&gt; 可以保留需要的样式。
	<address>
		<strong>Twitter, Inc.</strong><br> 1355 Market Street, Suite 900<br>
		San Francisco, CA 94103<br> <abbr title="Phone">P:</abbr> (123)
		456-7890
	</address>

	<address>
		<strong>Full Name</strong><br> <a
			href="mailto:chen_chen@sinosoft.com.cn">chen_chen@sinosoft.com.cn</a>
	</address>

	<h3>2.8引用</h3>
	在你的文档中引用其他来源的内容。
	<div>a.默认样式的引用</div>
	将任何 HTML 元素包裹在&lt;blockquote&gt;中即可表现为引用样式。对于直接引用，我们建议用&lt;p&gt;标签。
	<blockquote>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Integer posuere erat a ante.</p>
	</blockquote>
	<div>b.命名来源</div>
	添加 &lt;footer&gt; 用于标明引用来源。来源的名称可以包裹进 &lt;cite&gt;标签中。
	<blockquote>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Integer posuere erat a ante.</p>
		<footer>
			Someone famous in <cite title="Source Title">Source Title</cite>
		</footer>
	</blockquote>
	<div>c.另一种展示风格</div>
	通过赋予 .blockquote-reverse 类可以让引用呈现内容右对齐的效果。
	<blockquote class="blockquote-reverse">
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Integer posuere erat a ante.</p>
		<footer>
			Someone famous in <cite title="Source Title">Source Title</cite>
		</footer>
	</blockquote>

	<h3>2.9列表</h3>
	<div>a.无序列表</div>
	排列顺序无关紧要的一列元素。
	<ul>
		<li>Lorem ipsum dolor sit amet</li>
		<li>Consectetur adipiscing elit</li>
		<li>Integer molestie lorem at massa</li>
		<li>Nulla volutpat aliquam velit
			<ul>
				<li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
			</ul>
		</li>
	</ul>

	<div>b.有序列表</div>
	顺序至关重要的一组元素。
	<ol>
		<li>Lorem ipsum dolor sit amet</li>
		<li>Consectetur adipiscing elit</li>
		<li>Integer molestie lorem at massa</li>
		<li>Nulla volutpat aliquam velit
			<ol>
				<li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
			</ol>
		</li>
	</ol>

	<div>c.无样式列表</div>
	移除了默认的 list-style
	样式和左侧外边距的一组元素（只针对直接子元素）。这是针对直接子元素的，也就是说，你需要对所有嵌套的列表都添加这个类才能具有同样的样式。
	<ul class="list-unstyled">
		<li>Lorem ipsum dolor sit amet</li>
		<li>Consectetur adipiscing elit</li>
		<li>Integer molestie lorem at massa</li>
		<li>Nulla volutpat aliquam velit
			<ul>
				<li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
			</ul>
		</li>
	</ul>

	<div>d.内联列表</div>
	通过设置 display: inline-block; 并添加少量的内补（padding），将所有元素放置于同一行。
	<ul class="list-inline">
		<li>Lorem ipsum dolor sit amet</li>
		<li>Consectetur adipiscing elit</li>
		<li>Integer molestie lorem at massa</li>
		<li>Nulla volutpat aliquam velit
			<ul>
				<li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
			</ul>
		</li>
	</ul>

	<div>e.描述</div>
	带有描述的短语列表。
	<dl>
		<dt>Description lists</dt>
		<dd>A description list is perfect for defining terms.</dd>
		<dt>Euismod</dt>
		<dd>Vestibulum id ligula porta felis euismod semper eget lacinia
			odio sem nec elit.</dd>
		<dd>Donec id elit non mi porta gravida at eget metus.</dd>
		<dt>Malesuada porta</dt>
		<dd>Etiam porta sem malesuada magna mollis euismod.</dd>
	</dl>

	<div>f.水平排列的描述</div>
	.dl-horizontal 可以让&lt;dl&gt;内的短语及其描述排在一行。开始是像
	&lt;dl&gt;的默认样式堆叠在一起，随着导航条逐渐展开而排列在一行。
	<dl class="dl-horizontal">
		<dt>Description lists</dt>
		<dd>A description list is perfect for defining terms.</dd>
		<dt>Euismod</dt>
		<dd>Vestibulum id ligula porta felis euismod semper eget lacinia
			odio sem nec elit.</dd>
		<dd>Donec id elit non mi porta gravida at eget metus.</dd>
		<dt>Malesuada porta</dt>
		<dd>Etiam porta sem malesuada magna mollis euismod.</dd>
	</dl>
	自动截断 通过 text-overflow
	属性，水平排列的描述列表将会截断左侧太长的短语。在较窄的视口（viewport）内，列表将变为默认堆叠排列的布局方式

	<h2>3.代码</h2>
	<h3>3.1内联代码</h3>
	通过 &lt;code&gt;标签包裹内联样式的代码片段。 For example,
	<code>&lt;section&gt;</code>
	should be wrapped as inline.

	<h3>3.2用户输入</h3>
	通过 &lt;kbd&gt;标签标记用户通过键盘输入的内容。 To switch directories, type
	<kbd>cd</kbd>
	followed by the name of the directory.
	<br> To edit settings, press
	<kbd>
		<kbd>ctrl</kbd>
		+
		<kbd>,</kbd>
	</kbd>

	<h3>3.3代码块</h3>
	多行代码可以使用&lt;pre&gt;标签。为了正确的展示代码，注意将尖括号做转义处理。
	<pre>&lt;p&gt;Sample text here...&lt;/p&gt;</pre>

	还可以使用 .pre-scrollable 类，其作用是设置 max-height 为 350px ，并在垂直方向展示滚动条。
	<pre class="pre-scrollable">
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	lallaalalalllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	</pre>

	<h3>3.4变量</h3>
	通过 &lt;var&gt;标签标记变量。
	<var>y</var>
	=
	<var>m</var>
	<var>x</var>
	+
	<var>b</var>

	<h3>3.5程序输出</h3>
	通过 &lt;samp&gt; 标签来标记程序输出的内容。
	<samp>This text is meant to be treated as sample output from a
		computer program.</samp>

	<h2>4.表格</h2>
	<h3>4.1基本实例</h3>
	为任意&lt;tablep&gt;标签添加 .table 类可以为其赋予基本的样式 —
	少量的内补（padding）和水平方向的分隔线。这种方式看起来很多余！？
	但是我们觉得，表格元素使用的很广泛，如果我们为其赋予默认样式可能会影响例如日历和日期选择之类的插件，所以我们选择将此样式独立出来。
	<table class="table">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>地址</th>
				<th>email</th>
				<th>用户权限</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.mobilePhone}</td>
					<td>${user.address}</td>
					<td>${user.email}</td>
					<td>${user.role}</td>
					<td>${user.note}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



	<%-- 	<div class="row">
		  <div class="col-xs-1 col-md-1">序号</div>
		  <div class="col-xs-1 col-md-1">姓名</div>
		  <div class="col-xs-1 col-md-1">手机号</div>
		  <div class="col-xs-1 col-md-1">地址</div>
		  <div class="col-xs-1 col-md-2">email</div>
		  <div class="col-xs-1 col-md-1">用户权限</div>
		  <div class="col-xs-1 col-md-1">备注</div>
		</div>
		div>Name: ${list[0].userName}</div>
	    <c:forEach items="${requestScope.list}" var="user">
	  		<div class="row">
			  <div class="col-xs-1 col-md-1">${user.id}</div>
			  <div class="col-xs-1 col-md-1">${user.userName}</div>
			  <div class="col-xs-1 col-md-1">${user.mobilePhone}</div>
			  <div class="col-xs-1 col-md-1">${user.address}</div>
			  <div class="col-xs-1 col-md-2">${user.email}</div>
			  <div class="col-xs-1 col-md-1">${user.role}</div>
			  <div class="col-xs-1 col-md-1">${user.note}</div>
			</div>
	    </c:forEach> --%>

	<h3>4.2条纹状表格</h3>
	通过 .table-striped 类可以给 &lt;tbody&gt;之内的每一行增加斑马条纹样式。
	<br /> 跨浏览器兼容性: 条纹状表格是依赖 :nth-child CSS 选择器实现的，而这一功能不被 Internet
	Explorer 8 支持。
	<br />
	<table class="table table-striped">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>地址</th>
				<th>email</th>
				<th>用户权限</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.mobilePhone}</td>
					<td>${user.address}</td>
					<td>${user.email}</td>
					<td>${user.role}</td>
					<td>${user.note}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h3>4.3带边框的表格</h3>
	添加 .table-bordered 类为表格和其中的每个单元格增加边框。
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>地址</th>
				<th>email</th>
				<th>用户权限</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.mobilePhone}</td>
					<td>${user.address}</td>
					<td>${user.email}</td>
					<td>${user.role}</td>
					<td>${user.note}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<h3>4.4鼠标悬停</h3>
	通过添加 .table-hover 类可以让 &lt;tbody&gt;中的每一行对鼠标悬停状态作出响应。
	<table class="table table-hover">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>地址</th>
				<th>email</th>
				<th>用户权限</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.mobilePhone}</td>
					<td>${user.address}</td>
					<td>${user.email}</td>
					<td>${user.role}</td>
					<td>${user.note}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h3>4.5紧缩表格</h3>
	通过添加 .table-condensed 类可以让表格更加紧凑，单元格中的内补（padding）均会减半。
	<table class="table table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>地址</th>
				<th>email</th>
				<th>用户权限</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.mobilePhone}</td>
					<td>${user.address}</td>
					<td>${user.email}</td>
					<td>${user.role}</td>
					<td>${user.note}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h3>4.6状态类</h3>
	通过这些状态类可以为行或单元格设置颜色。 Class 描述 .active 鼠标悬停在行或单元格上时所设置的颜色 .success
	标识成功或积极的动作 .info 标识普通的提示信息或动作 .warning 标识警告或需要用户注意 .danger
	标识危险或潜在的带来负面影响的动作
	<table class="table table-striped">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>地址</th>
				<th>email</th>
				<th>用户权限</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="user">
				<tr>
					<td class="active">${user.id}</td>
					<td class="success">${user.userName}</td>
					<td class="warning">${user.mobilePhone}</td>
					<td class="danger">${user.address}</td>
					<td class="info">${user.email}</td>
					<td>${user.role}</td>
					<td>${user.note}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	向使用辅助技术的用户传达用意通过为表格中的一行或一个单元格添加颜色而赋予不同的意义只是提供了一种视觉上的表现，并不能为使用辅助技术 --
	例如屏幕阅读器 --
	浏览网页的用户提供更多信息。因此，请确保通过颜色而赋予的不同意义可以通过内容本身来表达（即在相应行或单元格中的可见的文本内容）；
	或者通过包含额外的方式 -- 例如应用了 .sr-only 类而隐藏的文本 -- 来表达出来。

	<h2>5.响应式表格</h2>
	将任何 .table 元素包裹在 .table-responsive
	元素内，即可创建响应式表格，其会在小屏幕设备上（小于768px）水平滚动。当屏幕大于 768px 宽度时，水平滚动条消失。 垂直方向的内容截断
	<br /> 响应式表格使用了 overflow-y: hidden
	属性，这样就能将超出表格底部和顶部的内容截断。特别是，也可以截断下拉菜单和其他第三方组件。
	<br /> Firefox 和 fieldset 元素
	<br /> Firefox 浏览器对 fieldset 元素设置了一些影响 width
	属性的样式，导致响应式表格出现问题。可以使用下面提供的针对 Firefox 的 hack 代码解决，但是以下代码并未集成在 Bootstrap
	中： @-moz-document url-prefix() { fieldset { display: table-cell; } }
	更多信息请参考 this Stack Overflow answer.
	<br />
	<div class="table-responsive">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>手机号</th>
					<th>地址</th>
					<th>email</th>
					<th>用户权限</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.userName}</td>
						<td>${user.mobilePhone}</td>
						<td>${user.address}</td>
						<td>${user.email}</td>
						<td>${user.role}</td>
						<td>${user.note}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<b>剩余的css样式，例如表单等，请看cssDemo2.jsp</b>
</body>
</html>