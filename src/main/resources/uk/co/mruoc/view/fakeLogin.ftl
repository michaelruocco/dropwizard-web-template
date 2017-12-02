<html>
<head>
    <title>Fake Login</title>
    <#include "includes/header.ftl"/>
</head>
<body>
    <div class="container">
        <#include "includes/navBar.ftl"/>
        <form action="oauth2fakeLogin" method="post" class="form-horizontal" role="form">
            <div class="form-group">
                <label class="control-label col-sm-2" for="userId">Fake User ID:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="Enter account number" value="${userId!}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Login</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>