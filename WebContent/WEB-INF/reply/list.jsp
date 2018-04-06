<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


 <!-- /.panel -->

                            <ul class="timeline">
								
								<c:forEach items="${replyList}" var="reply">                            
                            
                                <li>
                                    <div class="timeline-badge"><i class="fa fa-check"></i>
                                    </div>
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">${reply.gno eq reply.rno ?' 댓글':'    대 댓 글' }  <c:out value="${reply.replyer}"/></h4>
                                            <p><small class="text-muted"><i class="fa fa-clock-o"></i>${reply.replydate} </small>
                                            </p>
                                        </div>
                                        <div class="timeline-body">
                                            <p>
                                              <c:out value="${reply.reply}"/>
                                              <button class='rerebtn' data-rno='re_${reply.rno}'>RERE</button>
                                            </p>
                                            
                                            <div class='rere' id='re_${reply.rno}'>
                                              
                                              <form action="/reply/add" method="post">
                                                <input type='hidden' name='bno' value='${reply.bno}'>
                                                <input type='hidden' name='gno' value='${reply.gno}'>
                                                <input type='hidden' name='replyer' value='${member.userid}'>
                                                <input type='text' name='reply' value='RE.....'>
                                                <button>RE RE BUTTON</button>
                                              </form>
                                              
                                              
                                            </div>
                                            
                                        </div>
                                    </div>
                                </li>
                                
                                </c:forEach>
                            </ul>
<script>

$(document).ready(function(){
	
	$(".rere").hide();
	
	
	$(".rerebtn").on("click", function(e){
		
		var target = $(this);
		var rno = target.attr("data-rno");
		
		
		console.log("===========" + rno );
		
		$("#"+rno).toggle('slow');
	});
	
});







</script>

