<nav class="navbar navbar-inverse">
    <div class="container-fluid"/>
        <div class="navbar-header">
            <a class="navbar-brand" href="/web-template">Web Template</a>
        </div>
        <ul class="nav navbar-nav">
            <#if templateName == "/uk/co/mruoc/view/customers.ftl">
                <li class="active"><a href="customers">Customers</a></li>
            <#else>
                <li><a href="customers">Customers</a></li>
            </#if>
            <#if templateName == "/uk/co/mruoc/view/createCustomer.ftl">
                <li class="active"><a href="createCustomer">Create Customer</a></li>
            <#else>
                <li><a href="createCustomer">Create Customer</a></li>
            </#if>
            <li><a href="swagger">Swagger</a></li>
        </ul>
    </div>
</nav>