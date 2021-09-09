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
<title>Bootstrap 组件</title>

<!-- Bootstrap -->
<link href="${ctx}/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<h1>组件学习Demo</h1>

	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${ctx}/jquery/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

	<b>http://localhost:9011/pages/componentDemo.jsp</b>
	<h2>1.Glyphicons 字体图标</h2>
	包括250多个来自 Glyphicon Halflings 的字体图标。Glyphicons Halflings
	一般是收费的，但是他们的作者允许 Bootstrap 免费使用。为了表示感谢，希望你在使用时尽量为 Glyphicons 添加一个友情链接。
	<br /> 图标的可访问性
	<br /> 现代的辅助技术能够识别并朗读由 CSS 生成的内容和特定的 Unicode 字符。为了避免
	屏幕识读设备抓取非故意的和可能产生混淆的输出内容（尤其是当图标纯粹作为装饰用途时），我们为这些图标设置了 aria-hidden="true"
	属性。
	<br /> 如果你使用图标是为了表达某些含义（不仅仅是为了装饰用），请确保你所要表达的意思能够通过被辅助设备识别，例如，包含额外的内容并通过
	.sr-only 类让其在视觉上表现出隐藏的效果。
	<br /> 如果你所创建的组件不包含任何文本内容（例如，
	&lt;button&gt;内只包含了一个图标），你应当提供其他的内容来表示这个控件的意图，这样就能让使用辅助设备的用户知道其作用了。这种情况下，你可以为控件添加
	aria-label 属性。
	<br /> 可以把它们应用到按钮、工具条中的按钮组、导航或输入框等地方。
	<br />
	<button type="button" class="btn btn-default" aria-label="Left Align">
		<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
	</button>

	<button type="button" class="btn btn-default btn-lg">
		<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Star
	</button>
	alert 组件中所包含的图标是用来表示这是一条错误消息的，通过添加额外的 .sr-only
	文本就可以让辅助设备知道这条提示所要表达的意思了。
	<br />
	<div class="alert alert-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		<span class="sr-only">Error:</span> Enter a valid email address
	</div>

	<h2>2.下拉菜单</h2>
	用于显示链接列表的可切换、有上下文的菜单。下拉菜单的 JavaScript 插件让它具有了交互性。
	<h3>2.1实例</h3>
	将下拉菜单触发器和下拉菜单都包裹在 .dropdown 里，或者另一个声明了 position: relative;
	的元素。然后加入组成菜单的 HTML 代码。
	<br />
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">
			Dropdown <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>
	通过为下拉菜单的父元素设置 .dropup 类，可以让菜单向上弹出（默认是向下弹出的）。
	<br />
	<div class="dropup">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false">
			Dropup <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h3>2.2对齐</h3>
	默认情况下，下拉菜单自动沿着父元素的上沿和左侧被定位为 100% 宽度。 为 .dropdown-menu 添加
	.dropdown-menu-right 类可以让菜单右对齐。
	<br /> 可能需要额外的定位May require additional positioning
	<br /> 在正常的文档流中，通过 CSS 为下拉菜单进行定位。这就意味着下拉菜单可能会由于设置了 overflow
	属性的父元素而被部分遮挡或超出视口（viewport）的显示范围。如果出现这种问题，请自行解决。
	<br /> 不建议使用 .pull-right
	<br /> 从 v3.1.0 版本开始，我们不再建议对下拉菜单使用 .pull-right 类。如需将菜单右对齐，请使用
	.dropdown-menu-right 类。导航条中如需添加右对齐的导航（nav）组件，请使用 .pull-right 的 mixin
	版本，可以自动对齐菜单。如需左对齐，请使用 .dropdown-menu-left
	<br /> 类。
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dLabel" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">
			Dropdown <span class="caret"></span>
		</button>
		<ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dLabel">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h3>2.3标题</h3>
	在任何下拉菜单中均可通过添加标题来标明一组动作。
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">
			Dropdown <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
			<!-- class="dropdown-header" -->
			<li class="dropdown-header">Dropdown header</li>
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h3>2.4分割线</h3>
	为下拉菜单添加一条分割线，用于将多个链接分组。
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">
			Dropdown <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<!-- role="separator" class="divider" -->
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h3>2.5禁用的菜单项</h3>
	为下拉菜单中的 &lt;li &gt; 元素添加 .disabled 类，从而禁用相应的菜单项。
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu5" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">
			Dropdown <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu5">
			<li><a href="#">Action</a></li>
			<!-- class="disabled" -->
			<li class="disabled"><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<!-- role="separator" class="divider" -->
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h2>3.按钮组</h2>
	通过按钮组容器把一组按钮放在同一行里。通过与按钮插件联合使用，可以设置为单选框或多选框的样式和行为。
	<br /> 按钮组中的工具提示和弹出框需要特别的设置
	<br /> 当为 .btn-group 中的元素应用工具提示或弹出框时，必须指定 container: 'body'
	选项，这样可以避免不必要的副作用（例如工具提示或弹出框触发时，会让页面元素变宽和/或失去圆角）。
	<br /> 确保设置正确的 role 属性并提供一个 label 标签
	<br /> 为了向使用辅助技术 - 如屏幕阅读器 - 的用户正确传达一正确的按钮分组，需要提供一个合适的 role
	属性。对于按钮组合，应该是 role="group"，对于toolbar（工具栏）应该是 role="toolbar"。
	<br />
	一个例外是按钮组合只包含一个单一的控制元素或一个下拉菜单（比如实际情况，&lt;button&gt;元素组成的两端对齐排列的按钮组
	）或下拉菜单。
	<br /> 此外，按钮组和工具栏应给定一个明确的label标签，尽管设置了正确的 role
	属性，但是大多数辅助技术将不会正确的识读他们。在这里提供的实例中，我们使用 aria-label，但是， aria-labelledby
	也可以使用。
	<br />

	<h3>3.1基本实例</h3>
	Wrap a series of buttons with .btn in .btn-group.
	<div class="btn-group" role="group" aria-label="...">
		<button type="button" class="btn btn-default">Left</button>
		<button type="button" class="btn btn-default">Middle</button>
		<button type="button" class="btn btn-default">Right</button>
	</div>

	<h3>3.2按钮工具栏</h3>
	把一组 &lt;div class="btn-group"&gt; 组合进一个 &lt;div
	class="btn-toolbar"&gt;中就可以做成更复杂的组件。
	<div class="btn-toolbar" role="toolbar" aria-label="...">
		<div class="btn-group" role="group" aria-label="...">
			<button type="button" class="btn btn-default">1</button>
			<button type="button" class="btn btn-default">2</button>
			<button type="button" class="btn btn-default">3</button>
			<button type="button" class="btn btn-default">4</button>
		</div>
		<div class="btn-group" role="group" aria-label="...">
			<button type="button" class="btn btn-default">1</button>
			<button type="button" class="btn btn-default">2</button>
			<button type="button" class="btn btn-default">3</button>
			<button type="button" class="btn btn-default">4</button>
		</div>
		<div class="btn-group" role="group" aria-label="...">...</div>
	</div>

	<h3>3.3尺寸</h3>
	只要给 .btn-group 加上 .btn-group-* 类，就省去为按钮组中的每个按钮都赋予尺寸类了，如果包含了多个按钮组时也适用。
	<br />
	<div class="btn-group btn-group-lg" role="group" aria-label="...">
		<button type="button" class="btn btn-default">Left</button>
		<button type="button" class="btn btn-default">Middle</button>
		<button type="button" class="btn btn-default">Right</button>
	</div>
	<div class="btn-group" role="group" aria-label="...">
		<button type="button" class="btn btn-default">Left</button>
		<button type="button" class="btn btn-default">Middle</button>
		<button type="button" class="btn btn-default">Right</button>
	</div>
	<div class="btn-group btn-group-sm" role="group" aria-label="...">
		<button type="button" class="btn btn-default">Left</button>
		<button type="button" class="btn btn-default">Middle</button>
		<button type="button" class="btn btn-default">Right</button>
	</div>
	<div class="btn-group btn-group-xs" role="group" aria-label="...">
		<button type="button" class="btn btn-default">Left</button>
		<button type="button" class="btn btn-default">Middle</button>
		<button type="button" class="btn btn-default">Right</button>
	</div>

	<h3>3.4嵌套</h3>
	想要把下拉菜单混合到一系列按钮中，只须把 .btn-group 放入另一个 .btn-group 中。
	<br />
	<div class="btn-group" role="group" aria-label="...">
		<button type="button" class="btn btn-default">1</button>
		<button type="button" class="btn btn-default">2</button>

		<div class="btn-group" role="group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Dropdown <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a href="#">Dropdown link</a></li>
				<li><a href="#">Dropdown link</a></li>
			</ul>
		</div>
	</div>

	<h3>3.5垂直排列</h3>
	让一组按钮垂直堆叠排列显示而不是水平排列。分列式按钮下拉菜单不支持这种方式。
	<br />
	<!-- class="btn-group-vertical" -->
	<div class="btn-group-vertical" role="group" aria-label="...">
		<button type="button" class="btn btn-default">1</button>
		<button type="button" class="btn btn-default">2</button>

		<div class="btn-group" role="group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Dropdown <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a href="#">Dropdown link</a></li>
				<li><a href="#">Dropdown link</a></li>
			</ul>
		</div>
	</div>

	<h3>3.6两端对齐排列的按钮组</h3>
	让一组按钮拉长为相同的尺寸，填满父元素的宽度。对于按钮组中的按钮式下拉菜单也同样适用。
	<br /> 关于边框的处理
	<br /> 由于对两端对齐的按钮组使用了特定的 HTML 和 CSS （即 display:
	table-cell），两个按钮之间的边框叠加在了一起。在普通的按钮组中，margin-left: -1px
	用于将边框重叠，而没有删除任何一个按钮的边框。 然而，margin 属性不支持 display: table-cell。因此，根据你对
	Bootstrap 的定制，你可以删除或重新为按钮的边框设置颜色。
	<br /> IE8 和边框
	<br /> Internet Explorer 8 不支持在两端对齐的按钮组中绘制边框，无论是
	&lt;a&gt;或&lt;button&gt; 元素。为了照顾 IE8，把每个按钮放入另一个 .btn-group 中即可。
	<br />

	<h4>关于&lt;a&gt;元素</h4>
	只须将一系列 .btn 元素包裹到 .btn-group.btn-group-justified 中即可。
	<div class="btn-group btn-group-justified" role="group"
		aria-label="...">
		<a href="#" role="button" class="btn btn-default">1</a> <a href="#"
			role="button" class="btn btn-default">2</a>
	</div>

	<h4>关于&lt;button&gt;元素</h4>
	为了将 &lt;button&gt;元素用于两端对齐的按钮组中，必须将每个按钮包裹进一个按钮组中you must wrap each
	button in a button group。大部分的浏览器不能将我们的 CSS 应用到对齐的
	&lt;button&gt;元素上，但是，由于我们支持按钮式下拉菜单，我们可以解决这个问题。
	<div class="btn-group btn-group-justified" role="group"
		aria-label="...">
		<div class="btn-group" role="group">
			<button type="button" class="btn btn-default">Left</button>
		</div>
		<div class="btn-group" role="group">
			<button type="button" class="btn btn-default">Middle</button>
		</div>
		<div class="btn-group" role="group">
			<button type="button" class="btn btn-default">Right</button>
		</div>
	</div>

	<h2>4.按钮式下拉菜单</h2>
	把任意一个按钮放入 .btn-group 中，然后加入适当的菜单标签，就可以让按钮作为菜单的触发器了。
	<h3>4.1单按钮下拉菜单</h3>
	只要改变一些基本的标记，就能把按钮变成下拉菜单的开关。
	<!-- Single button -->
	<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			Action <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h3>4.2分裂式按钮下拉菜单</h3>
	相似地，分裂式按钮下拉菜单也需要同样的改变一些标记，但只是多一个分开的按钮。
	<!-- Split button -->
	<div class="btn-group">
		<button type="button" class="btn btn-danger">Action</button>
		<button type="button" class="btn btn-danger dropdown-toggle"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<span class="caret"></span> <span class="sr-only">Toggle
				Dropdown</span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h3>4.3尺寸</h3>
	按钮式下拉菜单适用所有尺寸的按钮。
	<div class="btn-group">
		<!-- 大尺寸class="btn-lg" -->
		<button class="btn btn-default btn-lg dropdown-toggle" type="button"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			Large button <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<div class="btn-group">
		<!-- 小尺寸class="btn-sm" -->
		<button class="btn btn-default btn-sm dropdown-toggle" type="button"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			Large button <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<div class="btn-group">
		<!-- 超小尺寸class="btn-sm" -->
		<button class="btn btn-default btn-xs dropdown-toggle" type="button"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			Large button <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h3>4.4向上弹出式菜单</h3>
	给父元素添加 .dropup 类就能使触发的下拉菜单朝上方打开。
	<div class="btn-group dropup">
		<button type="button" class="btn btn-default">Dropup</button>
		<button type="button" class="btn btn-default dropdown-toggle"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<span class="caret"></span> <span class="sr-only">Toggle
				Dropdown</span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>

	<h2>5.输入框组</h2>
	通过在文本输入框 &lt;input&gt; 前面、后面或是两边加上文字或按钮，可以实现对表单控件的扩展。为 .input-group 赋予
	.input-group-addon 或 .input-group-btn 类，可以给 .form-control
	的前面或后面添加额外的元素。
	<br /> 只支持文本输入框 &lt;input&gt;
	<br /> 这里请避免使用 &lt;select&gt; 元素，因为 WebKit 浏览器不能完全绘制它的样式。
	<br /> 避免使用 &lt;textarea&gt;元素，由于它们的 rows 属性在某些情况下不被支持。
	<br /> 输入框组中的工具提示和弹出框需要特别的设置
	<br /> 为 .input-group 中所包含的元素应用工具提示（tooltip）或popover（弹出框）时，必须设置
	container: 'body'
	参数，为的是避免意外的副作用（例如，工具提示或弹出框被激活后，可能会让当前元素变得更宽或/和变得失去其圆角）。
	<br /> 不要和其他组件混用
	<br /> 不要将表单组或栅格列（column）类直接和输入框组混合使用。而是将输入框组嵌套到表单组或栅格相关元素的内部。
	<br />
	<h3>5.1基本实例</h3>
	在输入框的任意一侧添加额外元素或按钮。你还可以在输入框的两侧同时添加额外元素。
	<br /> 我们不支持在输入框的单独一侧添加多个额外元素（.input-group-addon 或 .input-group-btn）。
	<br /> 我们不支持在单个输入框组中添加多个表单控件。
	<br />
	<div class="input-group">
		<span class="input-group-addon" id="basic-addon1">@</span> <input
			type="text" class="form-control" placeholder="Username"
			aria-describedby="basic-addon1">
	</div>

	<div class="input-group">
		<input type="text" class="form-control"
			placeholder="Recipient's username" aria-describedby="basic-addon2">
		<span class="input-group-addon" id="basic-addon2">@example.com</span>
	</div>

	<div class="input-group">
		<span class="input-group-addon">$</span> <input type="text"
			class="form-control" aria-label="Amount (to the nearest dollar)">
		<span class="input-group-addon">.00</span>
	</div>

	<label for="basic-url">Your vanity URL</label>
	<div class="input-group">
		<span class="input-group-addon" id="basic-addon3">https://example.com/users/</span>
		<input type="text" class="form-control" id="basic-url"
			aria-describedby="basic-addon3">
	</div>

	<h3>5.2尺寸</h3>
	为 .input-group 添加相应的尺寸类，其内部包含的元素将自动调整自身的尺寸。不需要为输入框组中的每个元素重复地添加控制尺寸的类。
	<div class="input-group input-group-lg">
		<span class="input-group-addon" id="sizing-addon1">@</span> <input
			type="text" class="form-control" placeholder="Username"
			aria-describedby="sizing-addon1">
	</div>

	<div class="input-group">
		<span class="input-group-addon" id="sizing-addon2">@</span> <input
			type="text" class="form-control" placeholder="Username"
			aria-describedby="sizing-addon2">
	</div>

	<div class="input-group input-group-sm">
		<span class="input-group-addon" id="sizing-addon3">@</span> <input
			type="text" class="form-control" placeholder="Username"
			aria-describedby="sizing-addon3">
	</div>

	<h3>5.3作为额外元素的多选框和单选框</h3>
	可以将多选框或单选框作为额外元素添加到输入框组中。
	<div class="row">
		<div class="col-lg-6">
			<div class="input-group">
				<span class="input-group-addon"> <input type="checkbox"
					aria-label="...">
				</span> <input type="text" class="form-control" aria-label="...">
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
		<div class="col-lg-6">
			<div class="input-group">
				<span class="input-group-addon"> <input type="radio"
					aria-label="...">
				</span> <input type="text" class="form-control" aria-label="...">
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->

	<h3>5.4作为额外元素的按钮</h3>
	为输入框组添加按钮需要额外添加一层嵌套，不是 .input-group-addon，而是添加 .input-group-btn
	来包裹按钮元素。由于不同浏览器的默认样式无法被统一的重新赋值，所以才需要这样做。
	<div class="row">
		<div class="col-lg-6">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span> <input type="text" class="form-control" placeholder="Search for...">
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
		<div class="col-lg-6">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span>
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->

	<h3>5.5作为额外元素的按钮式下拉菜单</h3>
	<div class="row">
		<div class="col-lg-6">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Action <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul>
				</div>
				<!-- /btn-group -->
				<input type="text" class="form-control" aria-label="...">
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
		<div class="col-lg-6">
			<div class="input-group">
				<input type="text" class="form-control" aria-label="...">
				<div class="input-group-btn">
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Action <span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-menu-right">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul>
				</div>
				<!-- /btn-group -->
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->

	<h3>5.6作为额外元素的分裂式按钮下拉菜单</h3>
	<div class="input-group">
		<div class="input-group-btn">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Action <span class="caret"></span>
			</button>
			<ul class="dropdown-menu dropdown-menu-right">
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
				<li><a href="#">Something else here</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>
		</div>
		<input type="text" class="form-control" aria-label="...">
	</div>

	<div class="input-group">
		<input type="text" class="form-control" aria-label="...">
		<div class="input-group-btn">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Action <span class="caret"></span>
			</button>
			<ul class="dropdown-menu dropdown-menu-right">
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
				<li><a href="#">Something else here</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>
		</div>
	</div>

	<h3>5.7多个按钮</h3>
	While you can only have one add-on per side, you can have multiple
	buttons inside a single .input-group-btn.
	<div class="input-group">
		<div class="input-group-btn">
			<button class="btn btn-default" type="button">Go!</button>
			<button class="btn btn-default" type="button">B</button>
		</div>
		<input type="text" class="form-control" aria-label="...">
	</div>

	<div class="input-group">
		<input type="text" class="form-control" aria-label="...">
		<div class="input-group-btn">
			<button class="btn btn-default" type="button">Go!</button>
			<button class="btn btn-default" type="button">B</button>
		</div>
	</div>


	<h2>6.导航</h2>
	Bootstrap 中的导航组件都依赖同一个 .nav 类，状态类也是共用的。改变修饰类可以改变样式。
	<br /> 在标签页上使用导航需要依赖 JavaScript 标签页插件
	<br /> 由于标签页需要控制内容区的展示，因此，你必须使用 标签页组件的 JavaScript 插件。另外还要添加 role 和 ARIA
	属性 – 详细信息请参考该插件的 实例。
	<br /> 确保导航组件的可访问性
	<br /> 如果你在使用导航组件实现导航条功能，务必在 &lt;ul&gt; 的最外侧的逻辑父元素上添加 role="navigation"
	属性，或者用一个 &lt;nav&gt;元素包裹整个导航组件。不要将 role 属性添加到 &lt;ul&gt;上，
	<br /> 因为这样可以被辅助设备（残疾人用的）上被识别为一个真正的列表。
	<br />

	<h3>6.1标签页</h3>
	注意 .nav-tabs 类依赖 .nav 基类。
	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#">Home</a></li>
		<li role="presentation"><a href="#">Profile</a></li>
		<li role="presentation"><a href="#">Messages</a></li>
	</ul>

	<h3>6.2胶囊式标签页</h3>
	HTML 标记相同，但使用 .nav-pills 类：
	<ul class="nav nav-pills">
		<li role="presentation" class="active"><a href="#">Home</a></li>
		<li role="presentation"><a href="#">Profile</a></li>
		<li role="presentation"><a href="#">Messages</a></li>
	</ul>

	胶囊是标签页也是可以垂直方向堆叠排列的。只需添加 .nav-stacked 类。
	<br />
	<ul class="nav nav-pills nav-stacked">
		<li role="presentation" class="active"><a href="#">Home</a></li>
		<li role="presentation"><a href="#">Profile</a></li>
		<li role="presentation"><a href="#">Messages</a></li>
	</ul>

	<h3>6.3两端对齐的标签页</h3>
	在大于 768px 的屏幕上，通过 .nav-justified
	类可以很容易的让标签页或胶囊式标签呈现出同等宽度。在小屏幕上，导航链接呈现堆叠样式。
	<br /> 两端对齐的导航条导航链接已经被弃用了。
	<br />

	<h3>6.4禁用的链接</h3>
	对任何导航组件（标签页、胶囊式标签页），都可以添加 .disabled 类，从而实现链接为灰色且没有鼠标悬停效果。
	<br /> 链接功能不受到影响
	<br /> 这个类只改变 &lt;a&gt; 的外观，不改变功能。可以自己写 JavaScript 禁用这里的链接。
	<br />
	<ul class="nav nav-pills">
		<li role="presentation"><a href="#">Clickable link</a></li>
		<li role="presentation" class="disabled"><a href="#">Disabled
				link</a></li>
		<li role="presentation"><a href="#">Clickable link</a></li>
	</ul>

	<h3>6.5添加下拉菜单</h3>
	用一点点额外 HTML 代码并加入下拉菜单插件的 JavaScript 插件即可。
	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#">Home</a></li>
		<li role="presentation"><a href="#">Help</a></li>
		<li role="presentation" class="dropdown"><a
			class="dropdown-toggle" data-toggle="dropdown" href="#" role="button"
			aria-haspopup="true" aria-expanded="false"> Dropdown <span
				class="caret"></span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
				<li><a href="#">Something else here</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul></li>
	</ul>

	<h3>6.6带下拉菜单的胶囊式标签页</h3>
	<ul class="nav nav-pills">
		<li role="presentation" class="active"><a href="#">Home</a></li>
		<li role="presentation"><a href="#">Help</a></li>
		<li role="presentation" class="dropdown"><a
			class="dropdown-toggle" data-toggle="dropdown" href="#" role="button"
			aria-haspopup="true" aria-expanded="false"> Dropdown <span
				class="caret"></span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
				<li><a href="#">Something else here</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul></li>
	</ul>

	<h2>7.导航条</h2>
	导航条是在您的应用或网站中作为导航页头的响应式基础组件。它们在移动设备上可以折叠（并且可开可关），且在视口（viewport）宽度增加时逐渐变为水平展开模式。
	<br /> 两端对齐的导航条导航链接已经被弃用了。
	<br /> I.导航条内所包含元素溢出
	<br /> 由于 Bootstrap
	并不知道你在导航条内放置的元素需要占据多宽的空间，你可能会遇到导航条中的内容折行的情况（也就是导航条占据两行）。解决办法如下：
	<br /> 减少导航条内所有元素所占据的宽度。
	<br /> 在某些尺寸的屏幕上（利用 响应式工具类）隐藏导航条内的一些元素。
	<br /> 修改导航条在水平排列和折叠排列互相转化时，触发这个转化的最小屏幕宽度值。可以通过修改
	@grid-float-breakpoint 变量实现，或者自己重写相关的媒体查询代码，覆盖 Bootstrap 的默认值。
	<br /> II.依赖 JavaScript 插件
	<br /> 如果 JavaScript
	被禁用，并且视口（viewport）足够窄，致使导航条折叠起来，导航条将不能被打开，.navbar-collapse
	内所包含的内容也将不可见。
	<br /> 响应式导航条依赖 collapse 插件，定制 Bootstrap 的话时候必将其包含。
	<br /> III.修改视口的阈值，从而影响导航条的排列模式
	<br /> 当浏览器视口（viewport）的宽度小于 @grid-float-breakpoint
	值时，导航条内部的元素变为折叠排列，也就是变现为移动设备展现模式；当浏览器视口（viewport）的宽度大于
	@grid-float-breakpoint
	值时，导航条内部的元素变为水平排列，也就是变现为非移动设备展现模式。通过调整源码中的这个值，就可以控制导航条何时堆叠排列，何时水平排列。默认值是
	768px （小屏幕 -- 或者说是平板 --的最小值，或者说是平板）。
	<br /> IV.导航条的可访问性
	<br /> 务必使用 &lt;nav &gt; 元素，或者，如果使用的是通用的 &lt;div &gt; 元素的话，务必为导航条设置
	role="navigation" 属性，这样能够让使用辅助设备的用户明确知道这是一个导航区域。
	<br />

	<h3>7.1实例</h3>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Brand</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
					<li><a href="#">Link</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Link</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<h3>7.2品牌图标</h3>
	将导航条内放置品牌标志的地方替换为 &lt;img&gt;元素即可展示自己的品牌图标。由于 .navbar-brand
	已经被设置了内补（padding）和高度（height），你需要根据自己的情况添加一些 CSS 代码从而覆盖默认设置。
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <img alt="Brand"
					src="${ctx}/image/Tulips.jpg" width="30px" height="30px" />
				</a>
			</div>
		</div>
	</nav>

	<h3>7.3表单</h3>
	将表单放置于 .navbar-form 之内可以呈现很好的垂直对齐，并在较窄的视口（viewport）中呈现折叠状态。
	使用对齐选项可以规定其在导航条上出现的位置。 注意，.navbar-form 和 .form-inline 的大部分代码都一样，内部实现使用了
	mixin。 某些表单组件，例如输入框组，可能需要设置一个固定宽度，从而在导航条内有合适的展现。
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <img alt="Brand"
					src="${ctx}/image/Tulips.jpg" width="30px" height="30px" />
				</a>
			</div>

			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</nav>
	移动设备上的注意事项
	<br /> 在移动设备上，对于在 fixed 定位的元素内使用表单控件的情况有一些注意事项。请参考我们提供的浏览器支持情况相关的文档 。
	<br /> 为输入框添加 label 标签
	<br /> 如果你没有为输入框添加 label 标签，屏幕阅读器将会遇到问题。对于导航条内的表单，可以通过添加 .sr-only 类隐藏
	label 标签。
	<br />

	<h3>7.4按钮</h3>
	对于不包含在 &lt;form&gt;中的 &lt;button&gt;元素，加上 .navbar-btn
	后，可以让它在导航条里垂直居中。有一些对于为辅助设备提供可识别标签的方法，例如， aria-label、aria-labelledby 或者
	title 属性。如果这些方法都没有，屏幕阅读器将使用 placeholder 属性（如果这个属性存在的话），但是请注意，使用
	placeholder 代替其他识别标签的方式是不推荐的。
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <img alt="Brand"
					src="${ctx}/image/Tulips.jpg" width="30px" height="30px" />
				</a>
			</div>

			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>

			<button type="button" class="btn btn-default navbar-btn">Sign
				in</button>
		</div>
	</nav>
	基于情境的用法
	<br /> 就像标准的 按钮类 一样，.navbar-btn 可以被用在 &lt;a&gt; 和 &lt;input&gt;
	元素上。然而，在 .navbar-nav 内，.navbar-btn 和标准的按钮类都不应该被用在 &lt;a&gt; 元素上。
	<br />

	<h3>7.5文本</h3>
	把文本包裹在 .navbar-text中时，为了有正确的行距和颜色，通常使用 &lt;p&gt;标签。
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <img alt="Brand"
					src="${ctx}/image/Tulips.jpg" width="30px" height="30px" />
				</a>
			</div>

			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<!-- 文本  .navbar-text-->
			<p class="navbar-text">Signed in as Mark Otto</p>
			<button type="button" class="btn btn-default navbar-btn">Sign
				in</button>
		</div>
	</nav>

	<h3>7.6非导航的链接</h3>
	或许你希望在标准的导航组件之外添加标准链接，那么，使用 .navbar-link 类可以让链接有正确的默认颜色和反色设置。
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <img alt="Brand"
					src="${ctx}/image/Tulips.jpg" width="30px" height="30px" />
				</a>
			</div>

			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<p class="navbar-text">Signed in as Mark Otto</p>
			<!-- 非导航的链接  .navbar-link-->
			<p class="navbar-text navbar-right">
				Signed in as <a href="#" class="navbar-link">Mark Otto</a>
			</p>
			<button type="button" class="btn btn-default navbar-btn">Sign
				in</button>
		</div>
	</nav>

	<h3>7.7组件排列</h3>
	通过添加 .navbar-left 和 .navbar-right 工具类让导航链接、表单、按钮或文本对齐。两个类都会通过 CSS
	设置特定方向的浮动样式。例如，要对齐导航链接，就要把它们放在个分开的、应用了工具类的&lt;ul&gt;标签里。 这些类是
	.pull-left 和 .pull-right 的 mixin 版本，但是他们被限定在了媒体查询（media
	query）中，这样可以更容易的在各种尺寸的屏幕上处理导航条组件。
	<br /> 向右侧对齐多个组件
	<br /> 导航条目前不支持多个 .navbar-right 类。为了让内容之间有合适的空隙，我们为最后一个 .navbar-right
	元素使用负边距（margin）。如果有多个元素使用这个类，它们的边距（margin）将不能按照你的预期正常展现。
	<br /> 我们将在 v4 版本中重写这个组件时重新审视这个功能。
	<br />

	<h3>7.7.1固定在顶部</h3>
	添加 .navbar-fixed-top 类可以让导航条固定在顶部，还可包含一个 .container 或 .container-fluid
	容器，从而让导航条居中，并在两侧添加内补（padding）。
	<%-- <nav class="navbar navbar-default navbar-fixed-top">
	  <div class="container">
	    <nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">
		        <img alt="Brand" src="${ctx}/image/Tulips.jpg" width="30px" height="30px"/>
		      </a>
		    </div>
		    
		    <form class="navbar-form navbar-left" role="search">
			  <div class="form-group">
			    <input type="text" class="form-control" placeholder="Search">
			  </div>
			  <button type="submit" class="btn btn-default">Submit</button>
			</form>
			<p class="navbar-text">Signed in as Mark Otto</p>
			<!-- 非导航的链接  .navbar-link-->
			<p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>
			<button type="button" class="btn btn-default navbar-btn">Sign in</button>
		  </div>
		</nav>
	  </div>
	</nav> --%>

	需要为 body 元素设置内补（padding）
	<br /> 这个固定的导航条会遮住页面上的其它内容，除非你给 &lt;body&gt;元素底部设置了
	padding。用你自己的值，或用下面给出的代码都可以。提示：导航条的默认高度是 50px。
	<br /> body { padding-top: 70px; }
	<br /> Make sure to include this after the core Bootstrap CSS.
	<br />

	<h3>7.7.2固定在底部</h3>
	添加 .navbar-fixed-bottom 类可以让导航条固定在底部，并且还可以包含一个 .container 或
	.container-fluid 容器，从而让导航条居中，并在两侧添加内补（padding）。
	<%-- <nav class="navbar navbar-default navbar-fixed-bottom">
	  <div class="container">
	    <nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">
		        <img alt="Brand" src="${ctx}/image/Tulips.jpg" width="30px" height="30px"/>
		      </a>
		    </div>
		    
		    <form class="navbar-form navbar-left" role="search">
			  <div class="form-group">
			    <input type="text" class="form-control" placeholder="Search">
			  </div>
			  <button type="submit" class="btn btn-default">Submit</button>
			</form>
			<p class="navbar-text">Signed in as Mark Otto</p>
			<!-- 非导航的链接  .navbar-link-->
			<p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>
			<button type="button" class="btn btn-default navbar-btn">Sign in</button>
		  </div>
		</nav>
	  </div>
	</nav> --%>

	<h3>7.7.3静止在顶部</h3>
	通过添加 .navbar-static-top 类即可创建一个与页面等宽度的导航条，它会随着页面向下滚动而消失。还可以包含一个
	.container 或 .container-fluid 容器，用于将导航条居中对齐并在两侧添加内补（padding）。 与
	.navbar-fixed-* 类不同的是，你不用给 body 添加任何内补（padding）。
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#"> <img alt="Brand"
							src="${ctx}/image/Tulips.jpg" width="30px" height="30px" />
						</a>
					</div>

					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
					<p class="navbar-text">Signed in as Mark Otto</p>
					<!-- 非导航的链接  .navbar-link-->
					<p class="navbar-text navbar-right">
						Signed in as <a href="#" class="navbar-link">Mark Otto</a>
					</p>
					<button type="button" class="btn btn-default navbar-btn">Sign
						in</button>
				</div>
			</nav>
		</div>
	</nav>

	<h3>7.7.4反色的导航条</h3>
	通过添加 .navbar-inverse 类可以改变导航条的外观。
	<nav class="navbar navbar-inverse">
		<div class="container">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#"> <img alt="Brand"
							src="${ctx}/image/Tulips.jpg" width="30px" height="30px" />
						</a>
					</div>

					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
					<p class="navbar-text">Signed in as Mark Otto</p>
					<!-- 非导航的链接  .navbar-link-->
					<p class="navbar-text navbar-right">
						Signed in as <a href="#" class="navbar-link">Mark Otto</a>
					</p>
					<button type="button" class="btn btn-default navbar-btn">Sign
						in</button>
				</div>
			</nav>
		</div>
	</nav>

	<h2>8.路径导航</h2>
	在一个带有层次的导航结构中标明当前页面的位置。
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li><a href="#">Library</a></li>
		<li class="active">Data</li>
	</ol>

	<h2>9.分页</h2>
	为您的网站或应用提供带有展示页码的分页组件，或者可以使用简单的翻页组件
	<h3>9.1默认分页</h3>
	受 Rdio 的启发，我们提供了这个简单的分页组件，用在应用或搜索结果中超级棒。组件中的每个部分都很大，优点是容易点击、易缩放、点击区域大。
	<nav aria-label="Page navigation">
		<ul class="pagination">
			<li><a href="#" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>

	<h3>9.2禁用和激活状态</h3>
	链接在不同情况下可以定制。你可以给不能点击的链接添加 .disabled 类、给当前页添加 .active 类。
	<nav aria-label="...">
		<ul class="pagination">
			<li class="disabled"><a href="#" aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
			<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>
	我们建议将 active 或 disabled 状态的链接（即 &lt;a&gt; 标签）替换为 &lt;span&gt;
	标签，或者在向前/向后的箭头处省略&lt;a&gt; 标签，这样就可以让其保持需要的样式而不能被点击。
	<nav aria-label="...">
		<ul class="pagination">
			<li class="disabled"><span> <span aria-hidden="true">&laquo;</span>
			</span></li>
			<li class="active"><span>1 <span class="sr-only">(current)</span></span>
			</li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><span> <span aria-hidden="true">&raquo;</span>
			</span></li>
		</ul>
	</nav>

	<h3>9.3尺寸</h3>
	想要更小或更大的分页？.pagination-lg 或 .pagination-sm 类提供了额外可供选择的尺寸。
	<nav aria-label="...">
		<ul class="pagination pagination-lg">
			<!-- pagination 默认大小        pagination pagination-sm 小 -->
			<li class="disabled"><span> <span aria-hidden="true">&laquo;</span>
			</span></li>
			<li class="active"><span>1 <span class="sr-only">(current)</span></span>
			</li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><span> <span aria-hidden="true">&raquo;</span>
			</span></li>
		</ul>
	</nav>

	<h3>9.4翻页</h3>
	用简单的标记和样式，就能做个上一页和下一页的简单翻页。用在像博客和杂志这样的简单站点上棒极了。
	<h4>9.41默认实例</h4>
	在默认的翻页中，链接居中对齐。
	<nav aria-label="...">
		<ul class="pager">
			<li><a href="#">Previous</a></li>
			<li><a href="#">Next</a></li>
		</ul>
	</nav>

	<h4>9.42对齐链接</h4>
	你还可以把链接向两端对齐：
	<nav aria-label="...">
		<ul class="pager">
			<li class="previous"><a href="#"><span aria-hidden="true">&larr;</span>
					Older</a></li>
			<li class="next"><a href="#">Newer <span aria-hidden="true">&rarr;</span></a></li>
		</ul>
	</nav>

	<h4>9.43可选的禁用状态</h4>
	.disabled 类也可用于翻页中的链接
	<nav aria-label="...">
		<ul class="pager">
			<li class="previous disabled"><a href="#"><span
					aria-hidden="true">&larr;</span> Older</a></li>
			<li class="next"><a href="#">Newer <span aria-hidden="true">&rarr;</span></a></li>
		</ul>
	</nav>

	<h2>10.标签</h2>
	<h3>10.1实例</h3>
	<h3>
		Example heading <span class="label label-default">New</span>
	</h3>

	<h3>10.2可用的变体</h3>
	用下面的任何一个类即可改变标签的外观。
	<span class="label label-default">Default</span>
	<span class="label label-primary">Primary</span>
	<span class="label label-success">Success</span>
	<span class="label label-info">Info</span>
	<span class="label label-warning">Warning</span>
	<span class="label label-danger">Danger</span> 如果标签数量很多怎么办？
	<br /> 如果你有大量的设置为 inline
	属性的标签全部放在一个较窄的容器元素内，在页面上展示这些标签就会出现问题，每个标签就会有自己的一个 inline-block
	元素（就像图标一样）。解决的办法是为每个标签都设置为 display: inline-block; 属性。关于这个问题以及实例，请参考
	#13219 。
	<br />

	<h2>11.徽章</h2>
	给链接、导航等元素嵌套 &lt;span class="badge"&gt;元素，可以很醒目的展示新的或未读的信息条目。
	<a href="#">Inbox <span class="badge">42</span></a>

	<button class="btn btn-primary" type="button">
		Messages <span class="badge">4</span>
	</button>

	Self collapsing
	<br /> 如果没有新的或未读的信息条目，也就是说不包含任何内容，徽章组件能够自动隐藏（通过CSS的 :empty 选择符实现) 。
	<br /> 跨浏览器兼容性
	<br /> 徽章组件在 Internet Explorer 8 浏览器中不会自动消失，因为 IE8 不支持 :empty 选择符。
	<br />

	<h3>11.1适配导航元素的激活状态</h3>
	Bootstrap 提供了内置的样式，让胶囊式导航内处于激活状态的元素所包含的徽章展示相匹配的样式。
	<ul class="nav nav-pills" role="tablist">
		<li role="presentation" class="active"><a href="#">Home <span
				class="badge">42</span></a></li>
		<li role="presentation"><a href="#">Profile</a></li>
		<li role="presentation"><a href="#">Messages <span
				class="badge">3</span></a></li>
	</ul>


	<h2>12.巨幕</h2>
	这是一个轻量、灵活的组件，它能延伸至整个浏览器视口来展示网站上的关键内容。
	<div class="jumbotron">
		<h1>Hello, world!</h1>
		<p>This is a simple hero unit, a simple jumbotron-style component
			for calling extra attention to featured content or information.</p>
		<p>
			<a class="btn btn-primary btn-lg" href="#" role="button">Learn
				more</a>
		</p>
	</div>

	如果需要让巨幕组件的宽度与浏览器宽度一致并且没有圆角，请把此组件放在所有 .container 元素的外面，并在组件内部添加一个
	.container 元素。
	<div class="jumbotron">
		<div class="container">
			<h1>Hello, world!</h1>
			<p>This is a simple hero unit, a simple jumbotron-style component
				for calling extra attention to featured content or information.</p>
			<p>
				<a class="btn btn-primary btn-lg" href="#" role="button">Learn
					more</a>
			</p>
		</div>
	</div>

	<h2>13.页头</h2>
	页头组件能够为 h1 标签增加适当的空间，并且与页面的其他部分形成一定的分隔。它支持 h1 标签内内嵌 small
	元素的默认效果，还支持大部分其他组件（需要增加一些额外的样式）。
	<div class="page-header">
		<h1>
			Example page header <small>Subtext for header</small>
		</h1>
	</div>

	<h2>14.缩略图</h2>
	<h3>14.1默认样式的实例</h3>
	Boostrap 缩略图的默认设计仅需最少的标签就能展示带链接的图片。
	<div class="row">
		<div class="col-xs-6 col-md-3">
			<a href="#" class="thumbnail"> <img src="${ctx}/image/Tulips.jpg"
				alt="郁金香花花">
			</a>
		</div>
	</div>

	<h3>14.2自定义内容</h3>
	添加一点点额外的标签，就可以把任何类型的 HTML 内容，例如标题、段落或按钮，加入缩略图组件内。

	<div class="row">
		<div class="col-sm-6 col-md-4">
			<div class="thumbnail">
				<img src="${ctx}/image/Tulips.jpg" alt="郁金香花花">
				<div class="caption">
					<h3>Tulips.jpg</h3>
					<p>郁金香花花</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
	</div>

	<h2>15.警告框</h2>
	警告框组件通过提供一些灵活的预定义消息，为常见的用户动作提供反馈消息。
	<h3>15.1实例</h3>
	将任意文本和一个可选的关闭按钮组合在一起就能组成一个警告框，.alert
	类是必须要设置的，另外我们还提供了有特殊意义的4个类（例如，.alert-success），代表不同的警告信息。
	<br /> 没有默认类
	<br />
	警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。
	<br />
	<div class="alert alert-success" role="alert">Well done! You
		successfully read this important alert message.</div>
	<div class="alert alert-info" role="alert">Heads up! This alert
		needs your attention, but it's not super important.</div>
	<div class="alert alert-warning" role="alert">Warning! Better
		check yourself, you're not looking too good.</div>
	<div class="alert alert-danger" role="alert">Oh snap! Change a
		few things up and try submitting again.</div>

	<h3>15.2可关闭的警告框</h3>
	为警告框添加一个可选的 .alert-dismissible 类和一个关闭按钮。
	<br /> 依赖警告框 JavaScript 插件
	<br /> 如果需要为警告框组件提供关闭功能，请使用 jQuery 警告框插件。
	<br />
	<div class="alert alert-warning alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>Warning!</strong> Better check yourself, you're not looking
		too good.
	</div>

	确保在所有设备上的正确行为
	<br /> 务必给 &lt;button&gt;元素添加 data-dismiss="alert" 属性。
	<br />

	<h3>15.3警告框中的链接</h3>
	用 .alert-link 工具类，可以为链接设置与当前警告框相符的颜色。
	<div class="alert alert-success" role="alert">
		You successfully read this <a href="#" class="alert-link">important
			alert message.</a>
	</div>
	<div class="alert alert-info" role="alert">
		This <a href="#" class="alert-link">alert needs your attention</a> but
		it's not super important.
	</div>
	<div class="alert alert-warning" role="alert">
		Better check yourself, you're <a href="#" class="alert-link"> not
			looking too good.</a>
	</div>
	<div class="alert alert-danger" role="alert">
		<a href="#" class="alert-link">Change a few things up</a> and try
		submitting again.
	</div>

	<h2>16.进度条</h2>
	通过这些简单、灵活的进度条，为当前工作流程或动作提供实时反馈。
	<br /> 跨浏览器兼容性
	<br /> 进度条组件使用了 CSS3 的 transition 和 animation 属性来完成一些特效。这些特性在 Internet
	Explorer 9 或以下版本中、Firefox 的老版本中没有被支持。Opera 12 不支持 animation 属性。
	<br />

	<h3>16.1基本实例</h3>
	默认样式的进度条
	<div class="progress">
		<div class="progress-bar" role="progressbar" aria-valuenow="60"
			aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
			<span class="sr-only">60% Complete</span>
		</div>
	</div>

	<h3>16.2带有提示标签的进度条</h3>
	将设置了 .sr-only 类的 &lt;span&gt;标签从进度条组件中移除 类，从而让当前进度显示出来。
	<div class="progress">
		<div class="progress-bar" role="progressbar" aria-valuenow="60"
			aria-valuemin="0" aria-valuemax="100" style="width: 60%;">60%</div>
	</div>

	展示很低的百分比时，如果需要让文本提示能够清晰可见，可以为进度条设置 min-width 属性。
	<div class="progress">
		<div class="progress-bar" role="progressbar" aria-valuenow="0"
			aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;">
			0%</div>
	</div>
	<div class="progress">
		<div class="progress-bar" role="progressbar" aria-valuenow="2"
			aria-valuemin="0" aria-valuemax="100"
			style="min-width: 2em; width: 2%;">2%</div>
	</div>

	<h3>16.3根据情境变化效果</h3>
	进度条组件使用与按钮和警告框相同的类，根据不同情境展现相应的效果。
	<div class="progress">
		<div class="progress-bar progress-bar-success" role="progressbar"
			aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
			style="width: 40%">
			<span class="sr-only">40% Complete (success)</span>
		</div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-info" role="progressbar"
			aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
			style="width: 20%">
			<span class="sr-only">20% Complete</span>
		</div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-warning" role="progressbar"
			aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
			style="width: 60%">
			<span class="sr-only">60% Complete (warning)</span>
		</div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-danger" role="progressbar"
			aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
			style="width: 80%">
			<span class="sr-only">80% Complete (danger)</span>
		</div>
	</div>

	<h3>16.4条纹效果</h3>
	通过渐变可以为进度条创建条纹效果，IE9 及更低版本不支持。
	<div class="progress">
		<div class="progress-bar progress-bar-success progress-bar-striped"
			role="progressbar" aria-valuenow="40" aria-valuemin="0"
			aria-valuemax="100" style="width: 40%">
			<span class="sr-only">40% Complete (success)</span>
		</div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-info progress-bar-striped"
			role="progressbar" aria-valuenow="20" aria-valuemin="0"
			aria-valuemax="100" style="width: 20%">
			<span class="sr-only">20% Complete</span>
		</div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-warning progress-bar-striped"
			role="progressbar" aria-valuenow="60" aria-valuemin="0"
			aria-valuemax="100" style="width: 60%">
			<span class="sr-only">60% Complete (warning)</span>
		</div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-danger progress-bar-striped"
			role="progressbar" aria-valuenow="80" aria-valuemin="0"
			aria-valuemax="100" style="width: 80%">
			<span class="sr-only">80% Complete (danger)</span>
		</div>
	</div>

	<h3>16.5动画效果</h3>
	为 .progress-bar-striped 添加 .active 类，使其呈现出由右向左运动的动画效果。IE9 及更低版本的浏览器不支持。
	<div class="progress">
		<div class="progress-bar progress-bar-striped active"
			role="progressbar" aria-valuenow="45" aria-valuemin="0"
			aria-valuemax="100" style="width: 45%">
			<span class="sr-only">45% Complete</span>
		</div>
	</div>

	<h3>16.6堆叠效果</h3>
	把多个进度条放入同一个 .progress 中，使它们呈现堆叠的效果。
	<div class="progress">
		<div class="progress-bar progress-bar-success" style="width: 35%">
			<span class="sr-only">35% Complete (success)</span>
		</div>
		<div class="progress-bar progress-bar-warning progress-bar-striped"
			style="width: 20%">
			<span class="sr-only">20% Complete (warning)</span>
		</div>
		<div class="progress-bar progress-bar-danger" style="width: 10%">
			<span class="sr-only">10% Complete (danger)</span>
		</div>
	</div>

	<h2>17.媒体对象</h2>
	这是一个抽象的样式，用以构建不同类型的组件，这些组件都具有在文本内容的左或右侧对齐的图片（就像博客评论或 Twitter 消息等）。
	<h3>17.1默认样式</h3>
	默认样式的媒体对象组件允许在一个内容块的左边或右边展示一个多媒体内容（图像、视频、音频）。
	<div class="media">
		<div class="media-left">
			<a href="#"> <img class="media-object"
				src="${ctx}/image/Tulips.jpg" alt="郁金香花花" width="100px;"
				height="100px;">
			</a>
		</div>
		<div class="media-body">
			<h4 class="media-heading">郁金香</h4>
			郁金香花花超级好闻，今天爱上了它！！！
		</div>
	</div>
	.pull-left 和 .pull-right 这两个类以前也曾经被用在了媒体组件上，但是，从 v3.3.0
	版本开始，他们就不再被建议使用了。.media-left 和 .media-right 替代了他们，不同之处是，在 html 结构中，
	.media-right 应当放在 .media-body 的后面。

	<h3>17.2默对齐</h3>
	图片或其他媒体类型可以顶部、中部或底部对齐。默认是顶部对齐。
	<div class="media">
		<div class="media-left media-middle">
			<a href="#"> <img class="media-object"
				src="${ctx}/image/Tulips.jpg" alt="郁金香花花" width="64px;"
				height="64px;">
			</a>
		</div>
		<div class="media-body">
			<h4 class="media-heading">郁金香</h4>
			郁金香花花超级好闻，今天爱上了它！！！
		</div>
	</div>

	<h3>17.3媒体对象列表</h3>
	用一点点额外的标记，就能在列表内使用媒体对象组件（对评论或文章列表很有用）。
	<ul class="media-list">
		<li class="media">
			<div class="media-left">
				<a href="#"> <img class="media-object"
					src="${ctx}/image/Tulips.jpg" alt="郁金香花花" width="64px;"
					height="64px;">
				</a>
			</div>
			<div class="media-body">
				<h4 class="media-heading">郁金香</h4>
				郁金香花花超级好闻，今天爱上了它！！！
			</div>
		</li>
	</ul>

</body>
</html>