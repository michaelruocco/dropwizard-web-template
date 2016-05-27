<html>
<head>
    <title>Create Customer</title>
</head>
<body>
    <form action="createCustomer" method="post">
        <#include "includes/customerForm.ftl">
        <input type="submit" value="Create">
    </form>
    <#include "includes/error.ftl">
    <a href="listCustomers">Back</a>
</body>
</html>