<nav class="navbar navbar-inverse">
    <div class="container-fluid"/>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Web Template</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar">
        <#if loggedIn >
            <ul class="nav navbar-nav">
                <#if templateName == "/uk/co/mruoc/view/customers.ftl">
                    <li class="active"><a href="/customers">Customers</a></li>
                <#else>
                    <li><a href="/customers">Customers</a></li>
                </#if>
                <#if templateName == "/uk/co/mruoc/view/createCustomer.ftl">
                    <li class="active"><a href="/createCustomer">Create Customer</a></li>
                <#else>
                    <li><a href="/createCustomer">Create Customer</a></li>
                </#if>
                <li><a href="/swagger">Swagger</a></li>
            </ul>
        </#if>
        <ul class="nav navbar-nav navbar-right">
            <#if loggedIn >
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="${userInfo.pictureUrl!}" alt="${userInfo.name!}" style="width:20px; height:20px; border-radius:50%; margin:0px;"><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#" onclick="return false;">${userInfo.username}</a></li>
                        <li><a href="/logout">Logout</a></li>
                    </ul>
                </li>
            <#else>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Login<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <#if canAuth('google')>
                            <li><a href=${getAuthUrl('google')}>Google</a></li>
                        </#if>
                        <#if canAuth('github')>
                            <li><a href=${getAuthUrl('github')}>GitHub</a></li>
                        </#if>
                        <#if canAuth('fake')>
                            <li><a href=${getAuthUrl('fake')}>Fake</a></li>
                        </#if>
                    </ul>
                </li>

            </#if>
        </ul>
    </div>
</nav>