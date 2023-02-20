angular.module('market').controller('regController', function ($scope, $http) {
    $scope.Registration = function () {
        $http({
            url: 'http://localhost:5555/auth/api/v1/register',
            method: 'POST',
            params: {
                registerUserDto: $scope.registerUserDto
            }
        }).then(function (response) {});
    };})
