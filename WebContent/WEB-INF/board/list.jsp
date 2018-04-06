<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Lists</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board List
			     <button class="btn-xs pull-right"><a href='/board/register'>Make New</a></button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>Bno</th>
							<th>Title</th>
							<th>Writer</th>
							<th>RegDate</th>
							<th>ViewCnt</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="vo">
							<tr class="odd gradeX">
								<td><c:out value="${vo.bno}"/></td>
								<td><c:out value="${vo.title}"/></td>
								<td><c:out value="${vo.writer}"/></td>
								<td class="center"><c:out value="${vo.regdate}"/></td>
								<td class="center"><c:out value="${vo.viewcnt}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<%@include file="../includes/footer.jsp"%>
