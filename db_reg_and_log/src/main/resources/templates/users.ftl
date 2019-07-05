<!DOCTYPE html>
<html lang="en">
<#import "/spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
</head>
<body ng-app="users_form" ng-controller="UserCtrl">

<div class="container">
    <div class="row justify-content-center">
        <h1 data-ng-init="getUsers()"><@spring.message "all.users.users"/></h1>
    </div>

    <table class="table" ng-model="users">
        <thead class="thead-dark">
        <tr>
            <th><@spring.message "all.users.column.id"/></th>
            <th><@spring.message "all.users.column.username"/></th>
            <th><@spring.message "all.users.column.first.name"/></th>
            <th><@spring.message "all.users.column.last.name"/></th>
            <th><@spring.message "all.users.column.email"/></th>
            <th><@spring.message "all.users.column.role"/></th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="u in users">
            <td>{{u.id}}</td>
            <td>{{u.username}}</td>
            <td>{{u.firstName}}</td>
            <td>{{u.lastName}}</td>
            <td>{{u.email}}</td>
            <td>{{u.role}}</td>
        </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript" src="/js/users.js"></script>
</body>
</html>