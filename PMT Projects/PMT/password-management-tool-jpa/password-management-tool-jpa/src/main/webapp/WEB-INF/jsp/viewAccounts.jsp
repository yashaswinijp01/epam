<%@include file="header.jsp" %>
    <script>
        $(".toggle-password").click(function () {

            $(this).toggleClass("fa-eye fa-eye-slash");
            var input = $($(this).attr("toggle"));
            if (input.attr("type") == "password") {
                input.attr("type", "text");
            } else {
                input.attr("type", "password");
            }
        });
    </script>
    <div class="container">
        <h1 class="m-4">List Of All Accounts</h1>
        <div id="accordion" class="m-4">
            <c:forEach items="${allGroups}" var="group">

                <c:set var="sizeOfList" value="${group.accounts.size()}" />
                <div class="card m-4">
                    <div class="card-header">
                       <h6>Group Name: <a class="collapsed card-link" data-toggle="collapse" href="#${group.groupName}">
                            ${group.groupName} </a></h6>
                    </div>
                    <div id="${group.groupName}" data-parent="#accordion">
                        <div class="card-body">
                            <c:if test="${group.groupId != 0}">
                                <a href="/groups/group/${group.groupId}">Update Group</a>
                                <c:if test="${sizeOfList == 0}"><br></br>
                                        <form action="/groups/group/delete/${group.groupId}"
                                           method="POST">  <button type="submit">Delete</button>
                                        </form>
                                </c:if>
                            </c:if>
                            <div class="row">
                                <c:forEach items="${group.accounts}" var="account">
                                <li class="list-group-item">
                                             <h6>   Account Name : ${account.accountName}</h6></li>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">
                                                    User Name : ${account.userName}
                                                </li>
                                                <li class="list-group-item">
                                                    Password : ${account.password}
                                                </li>
                                                <li class="list-group-item">
                                                    URL : <br>
                                                    <a href="${account.url}" target="_blank"> ${account.url}</a>

                                                </li>
                                            </ul>
                                            <div class="row m-2">
                                                <a href="/accounts/account/${account.accountId}">Update</a>
                                                <form action="/accounts/account/delete/${account.accountId}"
                                                    method="POST">
                                                    <button type="submit">Delete</button>
                                                </form>

                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>
        <script>
            $(".toggle-password").click(function () {

                $(this).toggleClass("fa-eye fa-eye-slash");
                var input = $($(this).attr("toggle"));
                if (input.attr("type") == "password") {
                    input.attr("type", "text");
                } else {
                    input.attr("type", "password");
                }
            });
        </script>
    </div>