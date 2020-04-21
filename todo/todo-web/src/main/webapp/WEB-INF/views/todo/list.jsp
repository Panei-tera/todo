<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/app/css/list.css">
<div id="wrapper">
    <h1>Todo List</h1>
    <div id="todoForm">
    	<t:messagesPanel />
        <form:form
           action="${pageContext.request.contextPath}/create"
            method="post" modelAttribute="todoForm">
            <form:input path="todoTitle" />
            <form:errors path="todoTitle" />
            <form:button>Create Todo</form:button>
        </form:form>
    </div>
    <hr />
    <div id="todoList">
        <ul>
            <c:forEach items="${todos}" var="todo">
                <li><c:choose>
                        <c:when test="${todo.finished}">
                            <span class="strike">
                            <!-- (5) -->
                            ${f:h(todo.todoTitle)}
                            </span>
                        </c:when>
                        <c:otherwise>
                            ${f:h(todo.todoTitle)}
                            <form:form
                                action="${pageContext.request.contextPath}/finish"
                                method="post"
                                modelAttribute="todoForm"
                                cssStyle="display: inline-block;">
                                <!-- (2) -->
                                <form:hidden path="todoId"
                                    value="${f:h(todo.todoId)}" />
                                <form:button>Finish</form:button>
                            </form:form>
		                    <form:form
		                        action="${pageContext.request.contextPath}/delete"
		                        method="post" modelAttribute="todoForm"
		                        cssStyle="display: inline-block;">
		                        <form:hidden path="todoId"
		                            value="${f:h(todo.todoId)}" />
		                        <form:button>Delete</form:button>
		                    </form:form>
                         </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
        </ul>
    </div>
    
</div>
