angular.module("registration_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let inputUsernameEl = document.getElementById('inputUsername');
        let exampleInputFullNameEl = document.getElementById('exampleInputFullName');
        let inputEmailEl = document.getElementById('inputEmail');
        let inputPasswordEl = document.getElementById('inputPassword');
        let inputFullNameLabel = document.getElementById('inputFullNameLabel');
        let inputEmailLabel = document.getElementById('inputEmailLabel');
        let inputUsernameLabel = document.getElementById('inputUsernameLabel');
        let inputPasswordLabel = document.getElementById('inputPasswordLabel');
        inputUsernameEl.addEventListener('input', () => {
            inputFullNameLabel.style.color = 'white';
            inputEmailLabel.style.color = 'white';
            inputPasswordLabel.style.color = 'white';
            inputUsernameLabel.style.color = 'white';
            $scope.message = '';
        });
        $scope.sendForm = function(auth){
            console.log($scope.auth);
            $http({
                method: "POST",
                url: "/api/clients",
                data: JSON.stringify($scope.auth)
            }).then(
                (data) => {
                    resultMessageEl.style.color = 'green';
                    $scope.message = 'Користувач успішно зареєстрований';
                    inputUsernameEl.value = '';
                    exampleInputFullNameEl.value = '';
                    inputEmailEl.value = '';
                    inputPasswordEl.value = '';
                },
                (error) => {
                    //resultMessageEl.style.color = 'red';
                    // inputUsernameLabel.style.color = 'red';
                    // inputFullNameLabel.style.color = 'red';
                    // inputEmailLabel.style.color = 'red';
                    // inputPasswordLabel.style.color = 'red';
                    $scope.message = 'Неунікальний username або email!';
                }
            );
        }
    });