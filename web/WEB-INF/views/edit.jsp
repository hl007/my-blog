<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<!doctype html>
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="description" content="Free Bootstrap Themes by HTML5XCSS3 dot com - Free Responsive Html5 Templates" />
	<meta name="author" content="#" />

	<link rel="stylesheet" href="/resources/css/bootstrap.min.css"  type="text/css" />

	<!-- Owl Carousel Assets -->
	<link href="/resources/owl-carousel/owl.carousel.css" rel="stylesheet" />
	<!-- <link href="owl-carousel/owl.theme.css" rel="stylesheet"> -->

	<!-- Custom CSS -->
	<link rel="stylesheet" href="/resources/css/style.css" />

	<!-- Custom Fonts -->
	<link rel="stylesheet" href="/resources/font-awesome-4.4.0/css/font-awesome.min.css"  type="text/css" />
	<link href='http://fonts.googleapis.com/css?family=Asap:400,700' rel='stylesheet' type='text/css' />

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="/resources/js/html5shiv.js"></script>
		<script src="/resources/js/respond.min.js"></script>
	<![endif]-->

	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="/resources/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resources/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resources/kindeditor/kindeditor-all.js"></script>
	<script charset="utf-8" src="/resources/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="/resources/kindeditor/plugins/code/prettify.js"></script>
    <script charset="utf-8" src="/resources/kindeditor/plugins/code/code.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '/resources/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resources/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resources/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body class="sub-page">
	<!--////////////////////////////////////////Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="/home">RMX</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a class="page-scroll" href="/home">Blog</a>
					</li>
					<sec:authorize access="isAuthenticated() and principal.username=='hl007'">
						<li>
							<a class="page-scroll" href="/edit">Edit</a>
						</li>
					</sec:authorize>
					<li>
						<a class="page-scroll" href="/about">About</a>
					</li>
					<li>
						<a class="page-scroll" href="/contact">Contact</a>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!-- Navigation -->

	<!-- Background Gradients-->
	<div  class="site-gradients">
		<div class="site-gradients-media">
			<figure>
				<img src="/resources/images/banner1.jpg" alt="PcLGXNjMTdiFVKTrElCl__DSC2245" sizes="(max-width: 1617px) 100vw, 1617px" height="1080" width="1617" />
			</figure>
		</div>
	</div>

	<header class="container">
		<div class="site-branding">
			<h1 class="site-title">
				<a href="/home">
					<span>RMX</span>
				</a>
			</h1>

			<h2 class="site-description">Welcome
				<sec:authorize access="isAuthenticated()">
					&nbsp;<strong style="color:palevioletred"><sec:authentication property="principal.username" /></strong>
				</sec:authorize>!
				<sec:authorize access="isAuthenticated()">
					&nbsp;&nbsp;<a onclick="document.getElementById('_form').submit();" style="color:red">Logout</a>
				</sec:authorize>
				<sf:form id="_form" method="post" action="/logout">
					<input type="hidden" name="name" value="value" />
				</sf:form>
			</h2>
		</div>
	</header>


	<!-- /////////////////////////////////////////Content -->
	<div id="page-content" class="single-page">
		<sf:form name="edit" method="post" action="/blog" enctype="multipart/form-data">
			<div class="container">
				<input type="text"  class="form-control input-lg"  name="title" id="title" placeholder="Your Title" required="required" />
			</div>
			<div class="container" >
				<textarea name="summary" style="height: 100px" class="form-control input-lg" placeholder="Your summary" required="required"></textarea>
			</div>
			<div class="container" >
				<textarea name="content" cols="100" rows="8" style="height:600px;text-align:center;"></textarea>
			</div>

			<div class="container">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<input type="text" class="form-control input-lg" name="tag1" id="tag1" placeholder="Your Tag1" required="required" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<input type="text" class="form-control input-lg" name="tag2" id="tag2" placeholder="Your Tag2" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<input type="text" class="form-control input-lg" name="tag3" id="tag3" placeholder="Your Tag3" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<input type="text" class="form-control input-lg" name="tag4" id="tag4" placeholder="Your Tag4" />
						</div>
					</div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<input type="file" name="picture" id="test" style="display:none" onChange="document.edit.path.value=this.value"
					   			accept="image/jpeg,image/png,image/gif" />
							<input type="button" value="UploadPicture" class="btn btn-skin btn-block"  onclick="document.edit.picture.click()" />
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<input name="path" readonly style="border:none"/>
						</div>
					</div>
				</div>
			</div>

			<div class="container">
				<button type="submit" class="btn btn-skin btn-block" name="submit" id="submit">Submit</button>
			</div>
			<br/>
			<br/>
		</sf:form>

	</div>

	<!-- jQuery -->
	<script type="text/javascript" src="/resources/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/resources/js/agency.js"></script>

	<!-- Plugin JavaScript -->
	<script src="/resources/js/jquery.easing.min.js"></script>
	<script src="/resources/js/classie.js"></script>
	<script src="/resources/js/cbpAnimatedHeader.js"></script>

	<!-- Google Map -->
	<script>
    $('.maps').click(function () {
        $('.maps iframe').css("pointer-events", "auto");
    });

    $( ".maps" ).mouseleave(function() {
        $('.maps iframe').css("pointer-events", "none");
    });
</script>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>