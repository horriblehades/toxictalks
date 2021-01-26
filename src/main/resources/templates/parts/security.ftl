<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    isAdmin = user.isAdmin()
    isUser = user.isUser()
    currentUserId = user.getId()
    >
<#else>
    <#assign
    isAdmin = false
    isUser = false
    currentUserId = -1
    >
</#if>