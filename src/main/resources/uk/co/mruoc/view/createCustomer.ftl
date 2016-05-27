<html>
<head>
    <title>Create Customer</title>
    <#include "includes/header.ftl"/>
</head>
<body>
    <div class="container">
        <#include "includes/navBar.ftl"/>
        <h2>Create Customer</h2>
        <form action="createCustomer" method="post" class="form-horizontal" role="form">
            <#include "includes/customerForm.ftl"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Create</button>
                </div>
            </div>
        </form>
        <#include "includes/error.ftl"/>
    </div>
</body>
</html>