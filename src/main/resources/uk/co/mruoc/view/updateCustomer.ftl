<html>
<head>
    <title>Update Customer</title>
    <#include "includes/header.ftl"/>
</head>
<body>
    <div class="container">
        <#include "includes/navBar.ftl"/>
        <h2>Update Customer</h2>
        <form action="updateCustomer" method="post" class="form-horizontal" role="form">
            <input type="hidden" id="accountNumber" name="accountNumber" value="${customer.accountNumber}"/>
            <#include "includes/customerForm.ftl"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Update</button>
                    <a href="deleteCustomer?accountNumber=${customer.accountNumber}" class="btn btn-default">Delete</a>
                </div>

            </div>
        </form>
        <#include "includes/error.ftl"/>
    </div>
</body>
</html>