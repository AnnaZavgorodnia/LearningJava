<!DOCTYPE html>
<#import "/spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login form's Main</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
</head>
<body>
    <div class="container" style="margin-top: 60px">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <h2 class="page-header"><@spring.message "login.page.form.name"/></h2>
                <#if error>
                    <div class="alert alert-danger" role="alert"><@spring.message "login.page.error"/></div>
                </#if>
                <#if logout>
                    <div class="alert alert-info" role="alert"><@spring.message "login.page.logout"/></div>
                </#if>
                <form method="post" style="margin-bottom: 30px" name="form" autocomplete="off">
                    <div class="form-group">
                        <label for="username"><@spring.message "login.page.username"/></label>
                        <input type="text"
                               class="form-control"
                               id="username"
                               placeholder="Username"
                               name="username"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="password"><@spring.message "login.page.password"/></label>
                        <input type="password"
                               class="form-control"
                               id="password"
                               placeholder="Password"
                               name="password"
                               required>
                    </div>
                    <button type="submit" class="btn btn-primary" style="margin-top:30px">
                        <@spring.message "login.page.button"/>
                    </button>
                    <#-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
                </form>
            </div>
        </div>
    </div>
</body>
</html>