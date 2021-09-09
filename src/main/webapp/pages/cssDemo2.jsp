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
<title>Bootstrap css demo2</title>

<!-- Bootstrap -->
<!-- <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<h1>Bootstrap css demo2</h1>

	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<!--  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> -->
	<script src="${ctx}/jquery/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<!-- <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

	<b>http://localhost:9011/pages/cssDemo2.jsp</b>
	<h2>6.表单</h2>
	<h3>6.1基本实例</h3>
	单独的表单控件会被自动赋予一些全局样式。所有设置了 .form-control
	类的&lt;input&gt;、&lt;textarea&gt; 和 &lt;select&gt;元素都将被默认设置宽度属性为 width:
	100%;。 将 label 元素和前面提到的控件包裹在 .form-group 中可以获得最好的排列。
	<form>
		<div class="form-group">
			<label for="exampleInputEmail1">Email address</label> <input
				type="email" class="form-control" id="exampleInputEmail1"
				placeholder="Email">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label> <input
				type="password" class="form-control" id="exampleInputPassword1"
				placeholder="Password">
		</div>
		<div class="form-group">
			<label for="exampleInputFile">File input</label> <input type="file"
				id="exampleInputFile">
			<p class="help-block">Example block-level help text here.</p>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox"> Check me out
			</label>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	不要将表单组和输入框组混合使用:不要将表单组直接和输入框组混合使用。建议将输入框组嵌套到表单组中使用。

	<h3>6.2内联表单</h3>
	为&lt;form&gt; 元素添加 .form-inline 类可使其内容左对齐并且表现为 inline-block 级别的控件。
	只适用于视口（viewport）至少在 768px 宽度时（视口宽度再小的话就会使表单折叠）。
	<br /> 可能需要手动设置宽度
	<br /> 在 Bootstrap 中，输入框和单选/多选框控件默认被设置为 width: 100%;
	宽度。在内联表单，我们将这些元素的宽度设置为 width:
	auto;，因此，多个控件可以排列在同一行。根据你的布局需求，可能需要一些额外的定制化组件。
	<br /> 一定要添加 label 标签
	<br /> 如果你没有为每个输入控件设置 label 标签，屏幕阅读器将无法正确识别。对于这些内联表单，你可以通过为 label 设置
	.sr-only 类将其隐藏。还有一些辅助技术提供label标签的替代方案，比如 aria-label、aria-labelledby 或
	title 属性。如果这些都不存在，屏幕阅读器可能会采取使用 placeholder
	属性，如果存在的话，使用占位符来替代其他的标记，但要注意，这种方法是不妥当的。
	<br />
	<h4>例1：</h4>
	<form class="form-inline">
		<div class="form-group">
			<label for="exampleInputName2">Name</label> <input type="text"
				class="form-control" id="exampleInputName2" placeholder="Jane Doe">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail2">Email</label> <input type="email"
				class="form-control" id="exampleInputEmail2"
				placeholder="jane.doe@example.com">
		</div>
		<button type="submit" class="btn btn-default">Send invitation</button>
	</form>
	<h4>例2：</h4>
	<form class="form-inline">
		<div class="form-group">
			<label class="sr-only" for="exampleInputEmail3">Email address</label>
			<input type="email" class="form-control" id="exampleInputEmail3"
				placeholder="Email">
		</div>
		<div class="form-group">
			<label class="sr-only" for="exampleInputPassword3">Password</label> <input
				type="password" class="form-control" id="exampleInputPassword3"
				placeholder="Password">
		</div>
		<div class="checkbox">
			<label> <input type="checkbox"> Remember me
			</label>
		</div>
		<button type="submit" class="btn btn-default">Sign in</button>
	</form>
	<h4>例3：</h4>
	<form class="form-inline">
		<div class="form-group">
			<label class="sr-only" for="exampleInputAmount">Amount (in
				dollars)</label>
			<div class="input-group">
				<div class="input-group-addon">$</div>
				<input type="text" class="form-control" id="exampleInputAmount"
					placeholder="Amount">
				<div class="input-group-addon">.00</div>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Transfer cash</button>
	</form>

	<h3>6.3水平排列的表单</h3>
	通过为表单添加 .form-horizontal 类，并联合使用 Bootstrap 预置的栅格类，可以将 label
	标签和控件组水平并排布局。这样做将改变 .form-group 的行为，使其表现为栅格系统中的行（row），因此就无需再额外添加 .row
	了。
	<form class="form-horizontal">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="inputEmail3"
					placeholder="Email">
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="inputPassword3"
					placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label> <input type="checkbox"> Remember me
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Sign in</button>
			</div>
		</div>
	</form>

	<h3>6.4被支持的控件</h3>
	表单布局实例中展示了其所支持的标准表单控件。

	<h4>输入框</h4>
	包括大部分表单控件、文本输入域控件，还支持所有 HTML5 类型的输入控件：
	text、password、datetime、datetime-local、date、month、time、week、number、email、url、search、tel
	和 color。
	<br /> 必须添加类型声明
	<br /> 只有正确设置了 type 属性的输入控件才能被赋予正确的样式。
	<br />
	<input type="text" class="form-control" placeholder="Text input">
	输入控件组
	<br /> 如需在文本输入域 &lt;input&gt; 前面或后面添加文本内容或按钮控件，请参考输入控件组。
	<br />

	<h4>文本域</h4>
	支持多行文本的表单控件。可根据需要改变 rows 属性。
	<textarea class="form-control" rows="3"></textarea>

	<h4>多选和单选框</h4>
	多选框（checkbox）用于选择列表中的一个或多个选项，而单选框（radio）用于从多个选项中只选择一个。
	可支持不可选择属性disabled
	<br /> Disabled checkboxes and radios are supported, but to provide a
	"not-allowed" cursor on hover of the parent &lt;label&gt;, you'll need
	to add the .disabled class to the parent .radio, .radio-inline,
	.checkbox, or .checkbox-inline. 默认外观（堆叠在一起）
	<div class="checkbox">
		<label> <input type="checkbox" value=""> Option one is
			this and that&mdash;be sure to include why it's great
		</label>
	</div>
	<div class="checkbox disabled">
		<label> <input type="checkbox" value="" disabled>
			Option two is disabled
		</label>
	</div>

	<div class="radio">
		<label> <input type="radio" name="optionsRadios"
			id="optionsRadios1" value="option1" checked> Option one is
			this and that&mdash;be sure to include why it's great
		</label>
	</div>
	<div class="radio">
		<label> <input type="radio" name="optionsRadios"
			id="optionsRadios2" value="option2"> Option two can be
			something else and selecting it will deselect option one
		</label>
	</div>
	<div class="radio disabled">
		<label> <input type="radio" name="optionsRadios"
			id="optionsRadios3" value="option3" disabled> Option three is
			disabled
		</label>
	</div>
	<h5>内联单选和多选框</h5>
	通过将 .checkbox-inline 或 .radio-inline
	类应用到一系列的多选框（checkbox）或单选框（radio）控件上，可以使这些控件排列在一行。
	<label class="checkbox-inline"> <input type="checkbox"
		id="inlineCheckbox1" value="option1"> 1
	</label>
	<label class="checkbox-inline"> <input type="checkbox"
		id="inlineCheckbox2" value="option2"> 2
	</label>
	<label class="checkbox-inline"> <input type="checkbox"
		id="inlineCheckbox3" value="option3"> 3
	</label>

	<label class="radio-inline"> <input type="radio"
		name="inlineRadioOptions" id="inlineRadio1" value="option1"> 1
	</label>
	<label class="radio-inline"> <input type="radio"
		name="inlineRadioOptions" id="inlineRadio2" value="option2"> 2
	</label>
	<label class="radio-inline"> <input type="radio"
		name="inlineRadioOptions" id="inlineRadio3" value="option3"> 3
	</label>

	<h5>不带label文本的Checkbox 和 radio</h5>
	如果需要 &lt;label&gt; 内没有文字，输入框（input）正是你所期望的。 目前只适用于非内联的 checkbox 和
	radio。 请记住，仍然需要为使用辅助技术的用户提供某种形式的 label（例如，使用 aria-label）。
	<div class="checkbox">
		<label> <input type="checkbox" id="blankCheckbox"
			value="option1" aria-label="...">
		</label>
	</div>
	<div class="radio">
		<label> <input type="radio" name="blankRadio" id="blankRadio1"
			value="option1" aria-label="...">
		</label>
	</div>

	<h4>下拉列表（select）</h4>
	注意，很多原生选择菜单 - 即在 Safari 和 Chrome 中 - 的圆角是无法通过修改 border-radius 属性来改变的。
	<select class="form-control">
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
	</select> 对于标记了 multiple 属性的 &lt;select&gt; 控件来说，默认显示多选项。
	<select multiple class="form-control">
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
	</select>

	<h4>6.5静态控件</h4>
	如果需要在表单中将一行纯文本和 label 元素放置于同一行，为&lt;p&gt;元素添加 .form-control-static 类即可。
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<p class="form-control-static">email@example.com</p>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="inputPassword"
					placeholder="Password">
			</div>
		</div>
	</form>

	<form class="form-inline">
		<div class="form-group">
			<label class="sr-only">Email</label>
			<p class="form-control-static">email@example.com</p>
		</div>
		<div class="form-group">
			<label for="inputPassword2" class="sr-only">Password</label> <input
				type="password" class="form-control" id="inputPassword2"
				placeholder="Password">
		</div>
		<button type="submit" class="btn btn-default">Confirm
			identity</button>
	</form>

	<h4>6.6焦点状态</h4>
	们将某些表单控件的默认 outline 样式移除，然后对 :focus 状态赋予 box-shadow 属性。

	<h4>6.7禁用状态</h4>
	<input class="form-control" id="disabledInput" type="text"
		placeholder="Disabled input here..." disabled>

	<b>被禁用的 fieldset</b> 为&lt;fieldset&gt;设置 disabled
	属性,可以禁用&lt;fieldset&gt;中包含的所有控件。 &lt;a&gt; 标签的链接功能不受影响
	<br /> 默认情况下，浏览器会将 &lt;fieldset disabled&gt;
	内所有的原生的表单控件（&lt;input&gt;、&lt;select&gt; 和 &lt;button&gt;
	元素）设置为禁用状态，防止键盘和鼠标与他们交互。然而，如果表单中还包含 &lt;a ... class="btn btn-*"&gt;
	元素，这些元素将只被赋予 pointer-events: none 属性。正如在关于 禁用状态的按钮
	章节中（尤其是关于锚点元素的子章节中）所描述的那样，该 CSS 属性尚不规范，并且在 Opera 18 及更低版本的浏览器或 Internet
	Explorer 11 总没有得到全面支持，并且不会阻止键盘用户 能够获取焦点或激活这些链接。所以为了安全起见，建议使用自定义
	JavaScript 来禁用这些链接。 跨浏览器兼容性
	<br /> 虽然 Bootstrap 会将这些样式应用到所有浏览器上，Internet Explorer 11 及以下浏览器中的
	&lt;fieldset&gt; 元素并不完全支持 disabled 属性。因此建议在这些浏览器上通过 JavaScript
	代码来禁用&lt;fieldset&gt;。

	<form>
		<fieldset disabled>
			<div class="form-group">
				<label for="disabledTextInput">Disabled input</label> <input
					type="text" id="disabledTextInput" class="form-control"
					placeholder="Disabled input">
			</div>
			<div class="form-group">
				<label for="disabledSelect">Disabled select menu</label> <select
					id="disabledSelect" class="form-control">
					<option>Disabled select</option>
				</select>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox"> Can't check this
				</label>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</fieldset>
	</form>

	<h4>6.8只读状态</h4>
	为输入框设置 readonly
	属性可以禁止用户修改输入框中的内容。处于只读状态的输入框颜色更浅（就像被禁用的输入框一样），但是仍然保留标准的鼠标状态。
	<input class="form-control" type="text"
		placeholder="Readonly input here…" readonly>

	<h4>6.9校验状态</h4>
	Bootstrap 对表单控件的校验状态，如 error、warning 和 success 状态，都定义了样式。使用时，添加
	.has-warning、.has-error 或 .has-success 类到这些控件的父元素即可。任何包含在此元素之内的
	.control-label、.form-control 和 .help-block 元素都将接受这些校验状态的样式。
	<br /> 将验证状态传达给辅助设备和盲人用户
	<br /> 使用这些校验样式只是为表单控件提供一个可视的、基于色彩的提示，但是并不能将这种提示信息传达给使用辅助设备的用户 -
	例如屏幕阅读器 - 或者色盲用户。
	<br /> 为了确保所有用户都能获取正确信息，Bootstrap 还提供了另一种提示方式。例如，你可以在表单控件的&lt;label&gt;
	标签上以文本的形式显示提示信息（就像下面代码中所展示的）；
	<br /> 包含一个 Glyphicon 字体图标 （还有赋予 .sr-only 类的文本信息 - 参考Glyphicon
	字体图标实例）；或者提供一个额外的 辅助信息 块。另外，对于使用辅助设备的用户，无效的表单控件还可以赋予一个
	aria-invalid="true" 属性。
	<br />
	<div class="form-group has-success">
		<label class="control-label" for="inputSuccess1">Input with
			success</label> <input type="text" class="form-control" id="inputSuccess1"
			aria-describedby="helpBlock2"> <span id="helpBlock2"
			class="help-block">A block of help text that breaks onto a new
			line and may extend beyond one line.</span>
	</div>
	<div class="form-group has-warning">
		<label class="control-label" for="inputWarning1">Input with
			warning</label> <input type="text" class="form-control" id="inputWarning1">
	</div>
	<div class="form-group has-error">
		<label class="control-label" for="inputError1">Input with
			error</label> <input type="text" class="form-control" id="inputError1">
	</div>
	<div class="has-success">
		<div class="checkbox">
			<label> <input type="checkbox" id="checkboxSuccess"
				value="option1"> Checkbox with success
			</label>
		</div>
	</div>
	<div class="has-warning">
		<div class="checkbox">
			<label> <input type="checkbox" id="checkboxWarning"
				value="option1"> Checkbox with warning
			</label>
		</div>
	</div>
	<div class="has-error">
		<div class="checkbox">
			<label> <input type="checkbox" id="checkboxError"
				value="option1"> Checkbox with error
			</label>
		</div>
	</div>
	<h5>6.91添加额外的图标</h5>
	你还可以针对校验状态为输入框添加额外的图标。只需设置相应的 .has-feedback 类并添加正确的图标即可。
	<br /> 反馈图标（feedback icon）只能使用在文本输入框 &lt;input class="form-control"&gt;
	元素上。
	<br /> 图标、label 和输入控件组
	<br /> 对于不带有 label
	标签的输入框以及右侧带有附加组件的输入框组，需要手动为其图标定位。为了让所有用户都能访问你的网站，我们强烈建议为所有输入框添加 label
	标签。如果你不希望将 label 标签展示出来，可以通过添加 .sr-only 类来实现。如果的确不能添加 label 标签，请调整图标的
	top 值。对于输入框组，请根据你的实际情况调整 right 值。
	<br /> 向辅助技术设备传递图标的含义
	<br /> 为了确保辅助技术- 如屏幕阅读器 - 正确传达一个图标的含义，额外的隐藏的文本应包含在 .sr-only 类中，并明确关联使用了
	aria-describedby 的表单控件。
	<br /> 或者，以某些其他形式（例如，文本输入字段有一个特定的警告信息）传达含义，例如改变与表单控件实际相关联的 &lt;label
	&gt;的文本。
	<br /> 虽然下面的例子已经提到各自表单控件本身的 &lt;label &gt;文本的验证状态，上述技术（使用 .sr-only 文本 和
	aria-describedby) ）已经包括了需要说明的目的。
	<br />
	<div class="form-group has-success has-feedback">
		<label class="control-label" for="inputSuccess2">Input with
			success</label> <input type="text" class="form-control" id="inputSuccess2"
			aria-describedby="inputSuccess2Status"> <span
			class="glyphicon glyphicon-ok form-control-feedback"
			aria-hidden="true"></span> <span id="inputSuccess2Status"
			class="sr-only">(success)</span>
	</div>
	<div class="form-group has-warning has-feedback">
		<label class="control-label" for="inputWarning2">Input with
			warning</label> <input type="text" class="form-control" id="inputWarning2"
			aria-describedby="inputWarning2Status"> <span
			class="glyphicon glyphicon-warning-sign form-control-feedback"
			aria-hidden="true"></span> <span id="inputWarning2Status"
			class="sr-only">(warning)</span>
	</div>
	<div class="form-group has-error has-feedback">
		<label class="control-label" for="inputError2">Input with
			error</label> <input type="text" class="form-control" id="inputError2"
			aria-describedby="inputError2Status"> <span
			class="glyphicon glyphicon-remove form-control-feedback"
			aria-hidden="true"></span> <span id="inputError2Status"
			class="sr-only">(error)</span>
	</div>
	<div class="form-group has-success has-feedback">
		<label class="control-label" for="inputGroupSuccess1">Input
			group with success</label>
		<div class="input-group">
			<span class="input-group-addon">@</span> <input type="text"
				class="form-control" id="inputGroupSuccess1"
				aria-describedby="inputGroupSuccess1Status">
		</div>
		<span class="glyphicon glyphicon-ok form-control-feedback"
			aria-hidden="true"></span> <span id="inputGroupSuccess1Status"
			class="sr-only">(success)</span>
	</div>

	<h5>6.92为水平排列的表单和内联表单设置可选的图标</h5>
	<form class="form-horizontal">
		<div class="form-group has-success has-feedback">
			<label class="control-label col-sm-3" for="inputSuccess3">Input
				with success</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="inputSuccess3"
					aria-describedby="inputSuccess3Status"> <span
					class="glyphicon glyphicon-ok form-control-feedback"
					aria-hidden="true"></span> <span id="inputSuccess3Status"
					class="sr-only">(success)</span>
			</div>
		</div>
		<div class="form-group has-success has-feedback">
			<label class="control-label col-sm-3" for="inputGroupSuccess2">Input
				group with success</label>
			<div class="col-sm-9">
				<div class="input-group">
					<span class="input-group-addon">@</span> <input type="text"
						class="form-control" id="inputGroupSuccess2"
						aria-describedby="inputGroupSuccess2Status">
				</div>
				<span class="glyphicon glyphicon-ok form-control-feedback"
					aria-hidden="true"></span> <span id="inputGroupSuccess2Status"
					class="sr-only">(success)</span>
			</div>
		</div>
	</form>

	<h5>6.93可选的图标与设置 .sr-only 类的 label</h5>
	如果你使用 .sr-only 类来隐藏表单控件的 &lt;label &gt;（而不是使用其它标签选项，如 aria-label 属性），
	一旦它被添加，Bootstrap 会自动调整图标的位置。
	<div class="form-group has-success has-feedback">
		<label class="control-label sr-only" for="inputSuccess5">Hidden
			label</label> <input type="text" class="form-control" id="inputSuccess5"
			aria-describedby="inputSuccess5Status"> <span
			class="glyphicon glyphicon-ok form-control-feedback"
			aria-hidden="true"></span> <span id="inputSuccess5Status"
			class="sr-only">(success)</span>
	</div>
	<div class="form-group has-success has-feedback">
		<label class="control-label sr-only" for="inputGroupSuccess4">Input
			group with success</label>
		<div class="input-group">
			<span class="input-group-addon">@</span> <input type="text"
				class="form-control" id="inputGroupSuccess4"
				aria-describedby="inputGroupSuccess4Status">
		</div>
		<span class="glyphicon glyphicon-ok form-control-feedback"
			aria-hidden="true"></span> <span id="inputGroupSuccess4Status"
			class="sr-only">(success)</span>
	</div>

	<h4>6.10控件尺寸</h4>
	通过 .input-lg 类似的类可以为控件设置高度，通过 .col-lg-* 类似的类可以为控件设置宽度。
	<h5>I 高度尺寸</h5>
	创建大一些或小一些的表单控件以匹配按钮尺寸。
	<input class="form-control input-lg" type="text"
		placeholder=".input-lg">
	<input class="form-control" type="text" placeholder="Default input">
	<input class="form-control input-sm" type="text"
		placeholder=".input-sm">

	<select class="form-control input-lg">
		<option value=".input-lg">.input-lg</option>
	</select>
	<select class="form-control">
		<option value="Default select">Default input</option>
	</select>
	<select class="form-control input-sm">
		<option value=".input-sm">.input-sm</option>
	</select>

	<h5>II 水平排列的表单组的尺寸</h5>
	<form class="form-horizontal">
		<div class="form-group form-group-lg">
			<label class="col-sm-2 control-label" for="formGroupInputLarge">Large
				label</label>
			<div class="col-sm-10">
				<input class="form-control" type="text" id="formGroupInputLarge"
					placeholder="Large input">
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-2 control-label" for="formGroupInputSmall">Small
				label</label>
			<div class="col-sm-10">
				<input class="form-control" type="text" id="formGroupInputSmall"
					placeholder="Small input">
			</div>
		</div>
	</form>

	<h5>III 调整列（column）尺寸</h5>
	用栅格系统中的列（column）包裹输入框或其任何父元素，都可很容易的为其设置宽度。
	<div class="row">
		<div class="col-xs-2">
			<input type="text" class="form-control" placeholder=".col-xs-2">
		</div>
		<div class="col-xs-3">
			<input type="text" class="form-control" placeholder=".col-xs-3">
		</div>
		<div class="col-xs-4">
			<input type="text" class="form-control" placeholder=".col-xs-4">
		</div>
	</div>

	<h2>7.按钮</h2>
	<h3>7.1可作为按钮使用的标签或元素</h3>
	为 &lt;a&gt;、&lt;button&gt; 或 &lt;input&gt; 元素添加按钮类（button class）即可使用
	Bootstrap 提供的样式。
	<a class="btn btn-default" href="#" role="button">Link</a>
	<button class="btn btn-default" type="submit">Button</button>
	<input class="btn btn-default" type="button" value="Input">
	<input class="btn btn-default" type="submit" value="Submit">

	针对组件的注意事项
	<br /> 虽然按钮类可以应用到 &lt;a&gt; 和&lt;button&gt;
	元素上，但是，导航和导航条组件只支持&lt;button&gt; 元素。 跨浏览器展现 我们总结的最佳实践是：强烈建议尽可能使用
	&lt;button&gt; 元素来获得在各个浏览器上获得相匹配的绘制效果。 另外，我们还发现了 Firefox &lt;30
	版本的浏览器上出现的一个 bug，其表现是：阻止我们为基于 &lt;input&gt; 元素所创建的按钮设置 line-height 属性，
	这就导致在 Firefox 浏览器上不能完全和其他按钮保持一致的高度。

	<h3>7.2预定义样式</h3>
	使用下面列出的类可以快速创建一个带有预定义样式的按钮。

	<!-- Standard button -->
	<button type="button" class="btn btn-default">（默认样式）Default</button>

	<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
	<button type="button" class="btn btn-primary">（首选项）Primary</button>

	<!-- Indicates a successful or positive action -->
	<button type="button" class="btn btn-success">（成功）Success</button>

	<!-- Contextual button for informational alert messages -->
	<button type="button" class="btn btn-info">（一般信息）Info</button>

	<!-- Indicates caution should be taken with this action -->
	<button type="button" class="btn btn-warning">（警告）Warning</button>

	<!-- Indicates a dangerous or potentially negative action -->
	<button type="button" class="btn btn-danger">（危险）Danger</button>

	<!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
	<button type="button" class="btn btn-link">（链接）Link</button>

	<h3>7.3尺寸</h3>
	需要让按钮具有不同尺寸吗？使用 .btn-lg、.btn-sm 或 .btn-xs 就可以获得不同尺寸的按钮。
	<p>
		<button type="button" class="btn btn-primary btn-lg">（大按钮）Large
			button</button>
		<button type="button" class="btn btn-default btn-lg">（大按钮）Large
			button</button>
	</p>
	<p>
		<button type="button" class="btn btn-primary">（默认尺寸）Default
			button</button>
		<button type="button" class="btn btn-default">（默认尺寸）Default
			button</button>
	</p>
	<p>
		<button type="button" class="btn btn-primary btn-sm">（小按钮）Small
			button</button>
		<button type="button" class="btn btn-default btn-sm">（小按钮）Small
			button</button>
	</p>
	<p>
		<button type="button" class="btn btn-primary btn-xs">（超小尺寸）Extra
			small button</button>
		<button type="button" class="btn btn-default btn-xs">（超小尺寸）Extra
			small button</button>
	</p>

	通过给按钮添加 .btn-block 类可以将其拉伸至父元素100%的宽度，而且按钮也变为了块级（block）元素。
	<button type="button" class="btn btn-primary btn-lg btn-block">（块级元素）Block
		level button</button>
	<button type="button" class="btn btn-default btn-lg btn-block">（块级元素）Block
		level button</button>

	<h3>7.4激活状态</h3>
	当按钮处于激活状态时，其表现为被按压下去（底色更深、边框夜色更深、向内投射阴影）。对于 &lt;button&gt;元素，是通过
	:active 状态实现的。对于 &lt;a&gt;元素，是通过 .active 类实现的。然而，你还可以将 .active 应用到
	&lt;button&gt; 上（包含 aria-pressed="true" 属性)），并通过编程的方式使其处于激活状态。
	<h4>7.41 button 元素</h4>
	由于 :active 是伪状态，因此无需额外添加，但是在需要让其表现出同样外观的时候可以添加 .active 类。
	<button type="button" class="btn btn-primary btn-lg active">Primary
		button</button>
	<button type="button" class="btn btn-default btn-lg active">Button</button>

	<h4>7.42 链接（&lt;a&gt;）元素</h4>
	可以为基于 &lt;a &gt; 元素创建的按钮添加 .active 类。
	<a href="#" class="btn btn-primary btn-lg active" role="button">Primary
		link</a>
	<a href="#" class="btn btn-default btn-lg active" role="button">Link</a>

	<h3>7.5 禁用状态</h3>
	通过为按钮的背景设置 opacity 属性就可以呈现出无法点击的效果。
	<h4>7.51 button 元素</h4>
	为 &lt;button&gt;元素添加 disabled 属性，使其表现出禁用状态。
	<button type="button" class="btn btn-lg btn-primary"
		disabled="disabled">Primary button</button>
	<button type="button" class="btn btn-default btn-lg"
		disabled="disabled">Button</button>
	跨浏览器兼容性
	<br /> 如果为 &lt;button&gt;元素添加 disabled 属性，Internet Explorer 9
	及更低版本的浏览器将会把按钮中的文本绘制为灰色，并带有恶心的阴影，目前我们还没有解决办法。
	<h4>7.52 链接（&lt;a&gt;）元素</h4>
	为基于 &lt;a&gt; 元素创建的按钮添加 .disabled 类。
	<a href="#" class="btn btn-primary btn-lg disabled" role="button">Primary
		link</a>
	<a href="#" class="btn btn-default btn-lg disabled" role="button">Link</a>

	链接的原始功能不受影响
	<br /> 上面提到的类只是通过设置 pointer-events: none 来禁止 &lt;a&gt;
	元素作为链接的原始功能，但是，这一 CSS 属性并没有被标准化， 并且 Opera 18
	及更低版本的浏览器并没有完全支持这一属性，同样，Internet Explorer 11 也不支持。 In addition, even in
	browsers that do support pointer-events: none, keyboard navigation
	remains unaffected, meaning that sighted keyboard users and users of
	assistive technologies will still be able to activate these links.
	因此，为了安全起见， 建议通过 JavaScript 代码来禁止链接的原始功能。

	<h2>8.图片</h2>
	<h3>8.1响应式图片</h3>
	在 Bootstrap 版本 3 中，通过为图片添加 .img-responsive 类可以让图片支持响应式布局。 其实质是为图片设置了
	max-width: 100%;、 height: auto; 和 display: block; 属性，从而让图片在其父元素中更好的缩放。

	如果需要让使用了 .img-responsive 类的图片水平居中，请使用 .center-block 类，不要用 .text-center。
	请参考助手类章节 了解更多关于 .center-block 的用法。
	<img src="${ctx}/image/Tulips.jpg" class="img-responsive"
		alt="Responsive image">

	<h3>8.2图片形状</h3>
	通过为 &lt;img&gt; 元素添加以下相应的类，可以让图片呈现不同的形状。 请时刻牢记：Internet Explorer 8 不支持
	CSS3 中的圆角属性。

	<img src="${ctx}/image/Tulips.jpg" alt="img-rounded"
		class="img-rounded">
	<img src="${ctx}/image/Tulips.jpg" alt="img-circle" class="img-circle">
	<img src="${ctx}/image/Tulips.jpg" alt="img-thumbnail"
		class="img-thumbnail">

	<h2>9.辅助类</h2>
	<h3>9.1情境文本颜色</h3>
	通过颜色来展示意图，Bootstrap 提供了一组工具类。这些类可以应用于链接，并且在鼠标经过时颜色可以还可以加深，就像默认的链接一样。
	<p class="text-muted">Fusce dapibus, tellus ac cursus commodo,
		tortor mauris nibh.</p>
	<p class="text-primary">Nullam id dolor id nibh ultricies vehicula
		ut id elit.</p>
	<p class="text-success">Duis mollis, est non commodo luctus, nisi
		erat porttitor ligula.</p>
	<p class="text-info">Maecenas sed diam eget risus varius blandit
		sit amet non magna.</p>
	<p class="text-warning">Etiam porta sem malesuada magna mollis
		euismod.</p>
	<p class="text-danger">Donec ullamcorper nulla non metus auctor
		fringilla.</p>

	<h3>9.2情境背景色</h3>
	和情境文本颜色类一样，使用任意情境背景色类就可以设置元素的背景。链接组件在鼠标经过时颜色会加深，就像上面所讲的情境文本颜色类一样。
	<p class="bg-primary">Fusce dapibus, tellus ac cursus commodo,
		tortor mauris nibh.</p>
	<p class="bg-success">Nullam id dolor id nibh ultricies vehicula ut
		id elit.</p>
	<p class="bg-info">Duis mollis, est non commodo luctus, nisi erat
		porttitor ligula.</p>
	<p class="bg-warning">Etiam porta sem malesuada magna mollis
		euismod.</p>
	<p class="bg-danger">Donec ullamcorper nulla non metus auctor
		fringilla.</p>

	<h3>9.3关闭按钮</h3>
	通过使用一个象征关闭的图标，可以让模态框和警告框消失。
	<button type="button" class="close" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>

	<h3>9.4三角符号</h3>
	通过使用三角符号可以指示某个元素具有下拉菜单的功能。注意，向上弹出式菜单中的三角符号是反方向的。
	<span class="caret"></span>

	<h3>9.5三快速浮动</h3>
	通过添加一个类，可以将任意元素向左或向右浮动。!important 被用来明确 CSS 样式的优先级。这些类还可以作为 mixin（参见
	less 文档） 使用。
	<div>
		<div class="pull-left">
			<div style="background: red; width: 100px; height: 100px;">red</div>
		</div>
		<div class="pull-right">
			<div style="background: yellow; width: 100px; height: 100px;">yellow</div>
		</div>
	</div>
	不能用于导航条组件中
	<br /> 排列导航条中的组件时可以使用这些工具类：.navbar-left 或 .navbar-right 。
	参见导航条文档以获取更多信息。

	<br />
	<br />
	<br />
	<br />
	<br />
	<h3>9.6让内容块居中</h3>
	为任意元素设置 display: block 属性并通过 margin 属性让其中的内容居中。下面列出的类还可以作为 mixin 使用。
	<div class="center-block"
		style="background: red; width: 100px; height: 100px;">hahaha</div>


	<h3>9.7清除浮动</h3>
	通过为父元素添加 .clearfix 类可以很容易地清除浮动（float）。这里所使用的是 Nicolas Gallagher 创造的
	micro clearfix 方式。此类还可以作为 mixin 使用。
	<div class="clearfix"
		style="background: green; width: 300px; height: 300px;">
		<div class="pull-left">
			<div style="background: red; width: 100px; height: 100px;">red</div>
		</div>
		<div class="pull-right">
			<div style="background: yellow; width: 200px; height: 200px;">yellow</div>
		</div>
	</div>

	<h3>9.8显示或隐藏内容</h3>
	.show 和 .hidden 类可以强制任意元素显示或隐藏(对于屏幕阅读器也能起效)。这些类通过 !important 来避免 CSS
	样式优先级问题， 就像 quick floats 一样的做法。注意，这些类只对块级元素起作用，另外，还可以作为 mixin 使用。 .hide
	类仍然可用，但是它不能对屏幕阅读器起作用，并且从 v3.0.1 版本开始就不建议使用了。请使用 .hidden 或 .sr-only 。
	另外，.invisible 类可以被用来仅仅影响元素的可见性，也就是说，元素的 display
	属性不被改变，并且这个元素仍然能够影响文档流的排布。
	<div class="show">
		<div style="background: red; width: 100px; height: 100px;">red</div>
	</div>
	<div class="hidden">
		<div style="background: red; width: 100px; height: 100px;">red</div>
	</div>

	<h3>9.9屏幕阅读器和键盘导航</h3>
	.sr-only 类可以对屏幕阅读器以外的设备隐藏内容。.sr-only 和 .sr-only-focusable
	联合使用的话可以在元素有焦点的时候再次显示出来（例如，使用键盘导航的用户）。对于遵循 可访问性的最佳实践 很有必要。这个类也可以作为
	mixin 使用。
	<a class="sr-only sr-only-focusable" href="#content">Skip to main
		content</a>

	<h3>9.10图片替换</h3>
	使用 .text-hide 类或对应的 mixin 可以用来将元素的文本内容替换为一张背景图。
	<h1 class="text-hide"
		style="background: red; width: 100px; height: 100px;">Custom
		heading</h1>
</body>
</html>