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

	<b>http://localhost:9011/pages/javascriptDemo.jsp</b>

	<h2>1.概述</h2>
	jQuery 插件为 Bootstrap 的组件赋予了“生命”。可以简单地一次性引入所有插件，或者逐个引入到你的页面中。
	<h3>1.1单个还是全部引入</h3>
	JavaScript 插件可以单个引入（使用 Bootstrap 提供的单个 *.js 文件），或者一次性全部引入（使用
	bootstrap.js 或压缩版的 bootstrap.min.js）。
	<br /> 建议使用压缩版的 JavaScript 文件
	<br /> bootstrap.js 和 bootstrap.min.js 都包含了所有插件，你在使用时，只需选择一个引入页面就可以了。
	<br /> 插件之间的依赖关系
	<br /> 某些插件和 CSS 组件依赖于其它插件。如果你是单个引入每个插件的，请确保在文档中检查插件之间的依赖关系。注意，所有插件都依赖
	jQuery （也就是说，jQuery必须在所有插件之前引入页面）。 bower.json 文件中列出了 Bootstrap 所支持的
	jQuery 版本。
	<br />

	<h3>1.2data 属性</h3>
	你可以仅仅通过 data 属性 API 就能使用所有的 Bootstrap 插件，无需写一行 JavaScript 代码。这是
	Bootstrap 中的一等 API，也应该是你的首选方式。
	<br /> 话又说回来，在某些情况下可能需要将此功能关闭。因此，我们还提供了关闭 data 属性 API 的方法，即解除以 data-api
	为命名空间并绑定在文档上的事件。就像下面这样：
	<br /> $(document).off('.data-api')
	<br /> 另外，如果是针对某个特定的插件，只需在 data-api 前面添加那个插件的名称作为命名空间，如下：
	<br /> $(document).off('.alert.data-api')
	<br />

	<h3>1.3编程方式的 API</h3>
	我们为所有 Bootstrap 插件提供了纯 JavaScript 方式的 API。所有公开的 API
	都是支持单独或链式调用方式，并且返回其所操作的元素集合（注：和jQuery的调用形式一致）。
	<br /> $('.btn.danger').button('toggle').addClass('fat')
	<br /> 所有方法都可以接受一个可选的 option
	对象作为参数，或者一个代表特定方法的字符串，或者什么也不提供（在这种情况下，插件将会以默认值初始化）：
	<br /> $('#myModal').modal() // 以默认值初始化
	<br /> $('#myModal').modal({ keyboard: false }) // initialized with no
	keyboard
	<br /> $('#myModal').modal('show') // 初始化后立即调用 show 方法
	<br /> 每个插件还通过 Constructor
	属性暴露了其原始的构造函数：$.fn.popover.Constructor。如果你想获取某个插件的实例，可以直接通过页面元素获取：$('[rel="popover"]').data('popover')。
	<br /> 默认设置
	<br /> 每个插件都可以通过修改其自身的 Constructor.DEFAULTS 对象从而改变插件的默认设置：
	<br /> $.fn.modal.Constructor.DEFAULTS.keyboard = false // 将模态框插件的
	`keyboard` 默认选参数置为 false
	<br />

	<h3>1.4避免命名空间冲突</h3>
	某些时候可能需要将 Bootstrap 插件与其他 UI
	框架共同使用。在这种情况下，命名空间冲突随时可能发生。如果不幸发生了这种情况，你可以通过调用插件的 .noConflict 方法恢复其原始值。
	<br /> var bootstrapButton = $.fn.button.noConflict() // return
	$.fn.button to previously assigned value
	<br /> $.fn.bootstrapBtn = bootstrapButton // give $().bootstrapBtn the
	Bootstrap functionality
	<br />

	<h3>1.5事件</h3>
	Bootstrap
	为大部分插件所具有的动作提供了自定义事件。一般来说，这些事件都有不定式和过去式两种动词的命名形式，例如，不定式形式的动词（例如
	show）表示其在事件开始时被触发；而过去式动词（例如 shown ）表示在动作执行完毕之后被触发。
	<br /> 从 3.0.0 版本开始，所有 Bootstrap 事件的名称都采用命名空间方式。
	<br /> 所有以不定式形式的动词命名的事件都提供了 preventDefault 功能。这就赋予你在动作开始执行前将其停止的能力。
	<br /> $('#myModal').on('show.bs.modal', function (e) {
	<br /> if (!data) return e.preventDefault() // 阻止模态框的展示
	<br /> })
	<br />

	<h3>1.6版本号</h3>
	每个 Bootstrap 的 jQuery 插件的版本号都可以通过插件的构造函数上的 VERSION
	属性获取到。例如工具提示框（tooltip）插件：
	<br /> $.fn.tooltip.Constructor.VERSION // => "3.3.7"
	<br />

	<h3>1.7未对禁用 JavaScript 的浏览器提供补救措施</h3>
	Bootstrap 插件未对禁用 JavaScript 的浏览器提供补救措施。如果你对这种情况下的用户体验很关心的话，请添加
	&lt;noscript&gt;标签向你的用户进行解释（并告诉他们如何启用 JavaScript），或者按照你自己的方式提供补救措施。
	<br /> 第三方工具库
	<br /> Bootstrap 官方不提供对第三方 JavaScript 工具库的支持，例如 Prototype 或 jQuery
	UI。除了 .noConflict 和为事件名称添加命名空间，还可能会有兼容性方面的问题，这就需要你自己来处理了。
	<br />

	<h2>2.过渡效果 transition.js</h2>
	<h3>2.1关于过渡效果</h3>
	对于简单的过渡效果，只需将 transition.js 和其它 JS 文件一起引入即可。如果你使用的是编译（或压缩）版的
	bootstrap.js 文件，就无需再单独将其引入了。
	<br />

	<h3>2.2包含的内容</h3>
	Transition.js 是针对 transitionEnd 事件的一个基本辅助工具，也是对 CSS
	过渡效果的模拟。它被其它插件用来检测当前浏览器对是否支持 CSS 的过渡效果。
	<br />

	<h3>2.3禁用过度效果</h3>
	通过下面的 JavaScript 代码可以在全局范围禁用过渡效果，并且必须将此代码放在 transition.js （或
	bootstrap.js 或 bootstrap.min.js）后面，确保在 js 文件加载完毕后再执行下面的代码：
	<br /> $.support.transition = false
	<br />

	<h2>3.模态框 modal.js</h2>
	模态框经过了优化，更加灵活，以弹出对话框的形式出现，具有最小和最实用的功能集。
	<br /> 不支持同时打开多个模态框
	<br /> 千万不要在一个模态框上重叠另一个模态框。要想同时支持多个模态框，需要自己写额外的代码来实现。
	<br /> 模态框的 HTML 代码放置的位置
	<br /> 务必将模态框的 HTML 代码放在文档的最高层级内（也就是说，尽量作为 body
	标签的直接子元素），以避免其他组件影响模态框的展现和/或功能。
	<br /> 对于移动设备的附加说明
	<br /> 这里提供了在移动设备上使用模态框有一些附加说明。请参考浏览器支持章节。
	<br /> Due to how HTML5 defines its semantics, the autofocus HTML
	attribute has no effect in Bootstrap modals. To achieve the same
	effect, use some custom JavaScript:
	<br /> $('#myModal').on('shown.bs.modal', function () {
	<br /> $('#myInput').focus()
	<br /> })
	<br />

	<h3>3.1静态实例</h3>
	以下模态框包含了模态框的头、体和一组放置于底部的按钮。
	<div class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					<p>One fine body&hellip;</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<h3>3.2动态实例</h3>
	点击下面的按钮即可通过 JavaScript 启动一个模态框。此模态框将从上到下、逐渐浮现到页面前。
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal">Launch demo modal
	</button>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<p>One fine body&hellip;</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>

	<br /> 增强模态框的可访问性
	<br /> 务必为 .modal 添加 role="dialog" 和 aria-labelledby="..."
	属性，用于指向模态框的标题栏；为 .modal-dialog 添加 aria-hidden="true" 属性。
	<br /> 另外，你还应该通过 aria-describedby 属性为模态框 .modal 添加描述性信息。
	<br /> 嵌入 YouTube 视频（天朝无用）
	<br /> 在模态框中嵌入 YouTube 视频需要增加一些额外的 JavaScript 代码，用于自动停止重放等功能，这些代码并没有在
	Bootstrap 中提供。请参考这份发布在 Stack Overflow 上的文章。
	<br />
	<br />

	<h3>3.3可选尺寸</h3>
	模态框提供了两个可选尺寸，通过为 .modal-dialog 增加一个样式调整类实现。
	<br />
	<!-- Large modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target=".bs-example-modal-lg">Large modal</button>

	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<p>One fine body&hellip;</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>

	<!-- Small modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target=".bs-example-modal-sm">Small modal</button>

	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<p>One fine body&hellip;</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>

	<h3>3.4禁止动画效果</h3>
	如果你不需要模态框弹出时的动画效果（淡入淡出效果），删掉 .fade 类即可。
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal33">Launch demo
		modal</button>
	<div class="modal" tabindex="-1" id="myModal33" role="dialog"
		aria-labelledby="...">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<p>One fine body&hellip;</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>

	<h3>3.5Using the grid system</h3>
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal44">Launch demo
		modal</button>
	<div class="modal fade" tabindex="-1" role="dialog" id="myModal44"
		aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">.col-md-4</div>
						<div class="col-md-4 col-md-offset-4">.col-md-4
							.col-md-offset-4</div>
					</div>
					<div class="row">
						<div class="col-md-3 col-md-offset-3">.col-md-3
							.col-md-offset-3</div>
						<div class="col-md-2 col-md-offset-4">.col-md-2
							.col-md-offset-4</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">.col-md-6
							.col-md-offset-3</div>
					</div>
					<div class="row">
						<div class="col-sm-9">
							Level 1: .col-sm-9
							<div class="row">
								<div class="col-xs-8 col-sm-6">Level 2: .col-xs-8
									.col-sm-6</div>
								<div class="col-xs-4 col-sm-6">Level 2: .col-xs-4
									.col-sm-6</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>