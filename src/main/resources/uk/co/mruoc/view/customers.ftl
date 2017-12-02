<#include "includes/header.ftl"/>

<#list customers>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Name</th>
                <th>Balance</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <#items as customer>
                <tr>
                    <td><a href="updateCustomer?accountNumber=${customer.accountNumber}">${customer.fullName}</a></td>
                    <td>${customer.balance}</td>
                    <td><a href="deleteCustomer?accountNumber=${customer.accountNumber}" class="btn btn-default btn-xs">Delete</a>
                <tr>
            </#items>
        </tbody>
    </table>
<#else>
    <h2>No customers found</h2>
</#list>

<#include "includes/footer.ftl"/>