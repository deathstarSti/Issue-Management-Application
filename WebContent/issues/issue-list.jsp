 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="issues-header.jsp" /> 
            <div class="row">
                

                <div class="container">
                    <h3 class="text-center">List of Issues</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/issues/new" class="btn btn-success">Add
New Issue</a>
                    </div>
                     <div class="container text-right">
						 <a href="<%=request.getContextPath()%>/issues/" class="btn btn-primary">All Issues</a>
						 <a href="<%=request.getContextPath()%>/issues/bug" class="btn btn-info">Bug Issues</a>
                        <a href="<%=request.getContextPath()%>/issues/feature" class="btn btn-info">Feature Issues</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Type</th>
                                <th>Status</th>
                                <th colspan=2></th>
                            </tr>
                        </thead>
                        <tbody>
                           
                            <c:forEach var="issue" items="${listIssues}">

                                <tr>
                                    <td>
                                        <c:out value="${issue.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${issue.title}" />
                                    </td>
                                    <td>
                                        <c:out value="${issue.description}" />
                                    </td>
                                    <td>
                                        <c:out value="${issue.type}" />
                                    </td>
                                     <td>
                                        <c:out value="${issue.status}" />
                                    </td>
                                    <td><a href="<%=request.getContextPath()%>/issues/edit?id=<c:out value='${issue.id}' />">Edit</a>
                                    </td>
                                    <td>   
                                    <a href="<%=request.getContextPath()%>/issues/delete?id=<c:out value='${issue.id}' />" onclick="return getDeleteConfirmation()">Delete</a></td>
                                </tr>
                            </c:forEach>
                          
                        </tbody>

                    </table>
                </div>
            </div>
            <script>
				

				function getDeleteConfirmation() {
					var retVal = confirm("Are you sure you want to Delete the Issue?");
					if( retVal == true ) {
						
						return true;
					} else {
						
						return false;
					}
            	}
			</script>
        </body>

        </html>