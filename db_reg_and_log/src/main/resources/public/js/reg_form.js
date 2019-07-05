angular.module("registration_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let inputUsernameEl = document.getElementById('inputUsername');
        let exampleInputFirstNameEl = document.getElementById('exampleInputFirstName');
        let exampleInputLastNameEl = document.getElementById('exampleInputLastName');
        let inputEmailEl = document.getElementById('inputEmail');
        let inputPasswordEl = document.getElementById('inputPassword');
        let inputFirstNameLabel = document.getElementById('inputFirstNameLabel');
        let inputLastNameLabel = document.getElementById('inputLastNameLabel');
        let inputEmailLabel = document.getElementById('inputEmailLabel');
        let inputUsernameLabel = document.getElementById('inputUsernameLabel');
        let inputPasswordLabel = document.getElementById('inputPasswordLabel');
        exampleInputFirstNameEl.addEventListener('input', () => {
            inputFirstNameLabel.style.color = 'black';
            inputLastNameLabel.style.color = 'black';
            inputEmailLabel.style.color = 'black';
            inputPasswordLabel.style.color = 'black';
            inputUsernameLabel.style.color = 'black';
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
                inputUsernameEl.value = '';
                exampleInputFirstNameEl.value = '';
                exampleInputLastNameEl.value = '';
                inputEmailEl.value = '';
                inputPasswordEl.value = '';
            },
            (error) => {
                resultMessageEl.style.color = 'red';
                inputUsernameLabel.style.color = 'red';
                inputFirstNameLabel.style.color = 'red';
                inputLastNameLabel.style.color = 'red';
                inputEmailLabel.style.color = 'red';
                inputPasswordLabel.style.color = 'red';
                inputUsernameEl.value = '';
                exampleInputFirstNameEl.value = '';
                exampleInputLastNameEl.value = '';
                inputEmailEl.value = '';
                inputPasswordEl.value = '';
                $scope.message = 'Неунікальний username або email!';
            }
        );
        }
    });