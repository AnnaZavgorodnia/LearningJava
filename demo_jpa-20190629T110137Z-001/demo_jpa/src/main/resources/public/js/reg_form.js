angular.module("registration_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let exampleInputFirstNameEl = document.getElementById('exampleInputFirstName');
        let exampleInputLastNameEl = document.getElementById('exampleInputLastName');
        let exampleInputEmailEl = document.getElementById('exampleInputEmail');
        let inputFirstNameLabel = document.getElementById('inputFirstNameLabel');
        let inputLastNameLabel = document.getElementById('inputLastNameLabel');
        let inputEmailLabel = document.getElementById('inputEmailLabel');
        let inputRoleLabel = document.getElementById('inputRoleLabel');
        exampleInputFirstNameEl.addEventListener('input', () => {
            inputFirstNameLabel.style.color = 'black';
            inputLastNameLabel.style.color = 'black';
            inputEmailLabel.style.color = 'black';
            inputRoleLabel.style.color = 'black';
            $scope.message = '';
        });
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/api/reg_user",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                    resultMessageEl.style.color = 'green';
                    $scope.message = 'Користувач успішно зареєстрований';
                    exampleInputFirstNameEl.value = '';
                    exampleInputLastNameEl.value = '';
                    exampleInputEmailEl.value = '';
                },
                (error) => {
                    resultMessageEl.style.color = 'red';
                    inputFirstNameLabel.style.color = 'red';
                    inputLastNameLabel.style.color = 'red';
                    inputEmailLabel.style.color = 'red';
                    inputRoleLabel.style.color = 'red';
                    exampleInputFirstNameEl.value = '';
                    exampleInputLastNameEl.value = '';
                    exampleInputEmailEl.value = '';
                    $scope.message = 'Неунікальний email!';
                }
        );
        }
    });