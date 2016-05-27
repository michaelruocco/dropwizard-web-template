<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <div>
    <#list customers>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Balance</th>
                <th>Action</th>
            </tr>
            <#items as customer>
                <tr>
                    <td><a href="updateCustomer?id=${customer.accountNumber}">${customer.fullName}</a></td>
                    <td>${customer.balance}</td>
                    <td><a href="deleteCustomer?id=${customer.accountNumber}">delete</a></td>
                <tr>
            </#items>
        </table>
    <#else>
        No customers found
    </#list>
    <a href="createCustomer">Add Customer</a>
    <a href="${contextPath}">Home</a>
</body>
</html>