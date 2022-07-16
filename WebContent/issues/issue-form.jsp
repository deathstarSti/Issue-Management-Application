
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="issues-header.jsp" /> 
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${issue != null}">
					<form action="<%=request.getContextPath()%>/issues/update" method="post">
				</c:if>
				<c:if test="${issue == null}">
					<form action="<%=request.getContextPath()%>/issues/insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${issue != null}">
                                    Edit issue
                                </c:if>
						<c:if test="${issue == null}">
                                    Add New issue
                                </c:if>
					</h2>
				</caption>

				<c:if test="${issue != null}">
					<input type="hidden" name="id"
						value="<c:out value='${issue.id}' />" />
				</c:if>


				<fieldset class="form-group">
					<label>Title</label> <input type="text"
						value="<c:out value='${issue.title}' />" class="form-control"
						name="title" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Description</label> 
					<textarea class="form-control" name="description" required="required" rows=4><c:out value='${issue.description}' /></textarea>
						
				</fieldset>

				<fieldset class="form-group">
					<label>Type</label> <select name="type" class="form-control"
						required="required">
						<option value="Bug"
							<c:if test="${issue.type == 'Bug'}">selected</c:if>>Bug</option>
						<option value="Feature"
							<c:if test="${issue.type == 'Feature'}">selected</c:if>>Feature</option>
					</select>
				</fieldset>

				
					<fieldset class="form-group">
					<c:if test="${issue != null}">
						<label>Assigned To</label>
					</c:if>
					<c:if test="${issue == null}">
						<label>Assign To</label>
					</c:if>
						 <select name="assignedTo"
							class="form-control">
							<option value="-1">--SELECT USER --
								</option>
							<c:forEach var="user" items="${listUsers}">
								<option value="<c:out value="${user.id}" />"
									<c:if test="${issue.assignedTo == user.id}">selected</c:if>><c:out
										value="${user.username}" />
								</option>

							</c:forEach>
						</select>
					</fieldset>
				

				<c:if test="${issue != null}">
					<fieldset class="form-group">
						<label>Created By</label> <select name="createdBy"
							class="form-control" disabled="disabled">
							<c:forEach var="user" items="${listUsers}">
								<option value="<c:out value="${user.id}" />"
									<c:if test="${issue.createdBy == user.id}">selected</c:if>><c:out
										value="${user.username}" />
								</option>

							</c:forEach>
						</select>
					</fieldset>
				</c:if>

				<c:if test="${issue != null}">
					<fieldset class="form-group">
						<label>Created At</label> <input type="date"
							value="<c:out value='${issue.createdAt}' />" class="form-control"
							name="createdAt" required="required" disabled="disabled">
					</fieldset>
				</c:if>
<!-- 
				<c:if test="${issue != null}">
					<fieldset class="form-group">
						<label>Edited At</label> <input type="date"
							value="<c:out value='${issue.editedAt}' />" class="form-control"
							name="editedAt">
					</fieldset>
				</c:if>
		 -->		
				<c:if test="${issue != null}">
					<fieldset class="form-group">
						<label>Status</label> <select name="status" class="form-control"
							required="required">
							<option value="New"
								<c:if test="${issue.status == 'New'}">selected</c:if>>New
							</option>
							<option value="In Progress"
								<c:if test="${issue.status == 'In Progress'}">selected</c:if>>In
								Progress</option>
							<option value="Resolved"
								<c:if test="${issue.status == 'Resolved'}">selected</c:if>>Resolved</option>
						</select>
					</fieldset>
				</c:if>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>