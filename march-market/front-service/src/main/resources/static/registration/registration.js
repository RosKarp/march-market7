angular.module('market').controller('regController', function ($scope, $http) {
    $scope.registration = function () {
        $http.post('http://localhost:5555/auth/api/v1/register', $scope.registerUserDto)
            .then(function successCallback(response) {
                alert('Регистрация прошла успешно');
                $scope.registerUserDto.password = '';
                $scope.registerUserDto.username = '';
                $scope.registerUserDto.confirmPassword = '';
                $scope.registerUserDto.email = '';
            }, function errorCallback(response) {
                alert(response.data.message);
            });
    };})
