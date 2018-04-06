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
								<td><a href='/board/view?${pm.cri.getLink(pm.page)}&bno=${vo.bno}'><c:out value="${vo.title}"/></a></td>
								<td><c:out value="${vo.writer}"/></td>
								<td class="center"><c:out value="${vo.regdate}"/></td>
								<td class="center"><c:out value="${vo.viewcnt}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div>
				
				<form action="/board/list" method="get">
				  <input type='hidden' name='page' value='1'/>
				  <select name="type">
				    <option value="">---</option>
				    <option value="T" <c:out value="${pm.cri.type eq 'T'?'selected':''}"/>>제목</option>
				    <option value="C" <c:out value="${pm.cri.type eq 'C'?'selected':''}"/>>내용</option>
				    <option value="W" <c:out value="${pm.cri.type eq 'W'?'selected':''}"/>>작성자</option>
				    <option value="TC" <c:out value="${pm.cri.type eq 'TC'?'selected':''}"/>>제목 + 내용</option>
				    <option value="TW" <c:out value="${pm.cri.type eq 'TW'?'selected':''}"/>>제목 + 작성자</option>
				    <option value="TCW" <c:out value="${pm.cri.type eq 'TCW'?'selected':''}"/>>제목 + 내용 + 작성자</option>
				  </select>
				  <input type='text' name='keyword' value='<c:out value="${pm.cri.keyword }"/>'>
				  <button>Search</button>
				</form>
				
				</div>
				
				
				<div>
				
				
<ul class="pagination">
  <c:if test="${pm.prev}"><li><a href="/board/list?${pm.cri.getLink(pm.start -1) }"> 이전</a>  </li></c:if>
  <c:forEach begin="${pm.start}" end="${pm.end}" var="num">
  <li class="${num == pm.page?'active':''}"><a href="/board/list?${pm.cri.getLink(num)}"> ${num}</a>  </li>
  </c:forEach>
  <c:if test="${pm.next}"><li> <a href="/board/list?${pm.cri.getLink(pm.end + 1) }"> 다음</a>  </li></c:if>
</ul>
				
				
				</div>
				
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<%@include file="../includes/footer.jsp"%>



