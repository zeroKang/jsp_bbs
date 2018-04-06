<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Modify/Delete</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify/Delete</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<form action="/board/modify" method="post" target="zero">
					<input type='hidden' name='page' value='${cri.page}'>
					<div class="form-group">
						<label>Bno</label> <input class="form-control" name='bno'
							value='<c:out value="${vo.bno }"/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>Title</label> <input class="form-control" name='title'
							value='<c:out value="${vo.title }"/>'>
					</div>

					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" rows="3" name='content'><c:out
								value="${vo.content }" /></textarea>
					</div>

					<div class="form-group">
						<label>Writer</label> <input class="form-control" name='writer'
							value='<c:out value="${vo.writer }"/>' readonly="readonly">
					</div>

					<button class="btn btn-default">Modify</button>
				</form>

				<form role="form" action="/board/remove" method="post" target="zero">
					<input type='hidden' name='page' value='${cri.page}'> <input
						type='hidden' name='bno' value='${vo.bno}'> <input
						type='hidden' name='keyword' value='${cri.keyword}'> <input
						type='hidden' name='type' value='${cri.type}'>
					<button class="btn btn-default">Delete</button>
				</form>

				<form role="form" action="/board/list" method="get">
					<input type='hidden' name='page' value='${cri.page}'> <input
						type='hidden' name='keyword' value='${cri.keyword}'> <input
						type='hidden' name='type' value='${cri.type}'>
					<button class="btn btn-default">LIST</button>
				</form>



			</div>
			<!--  end panel-body -->
		</div>
		<!-- end panel -->
	</div>
</div>
<!-- /.row -->

<style>
iframe {
	width: 0px;
	height: 0px;
	border: 0px;
}
</style>

<script>
	function showMsg(msg) {
		alert(msg);
		self.location = "/board/list?${cri.getLink(null)}";
	}
</script>


<iframe name='zero'> </iframe>

<%@include file="../includes/footer.jsp"%>
