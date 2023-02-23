angular.module('market').controller('cartController', function ($scope, $http, $localStorage) {
    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.createOrder = function () {
        if ($scope.isUserLoggedIn()) {
            $http.post('http://localhost:5555/core/api/v1/orders/delivery', $scope.deliveryDto)
                .then(function (response) {
                   // $scope.loadCart();
        })} else {
            alert('Для оформления заказа необходимо войти в учетную запись');
        }
        /* $http({
             url: 'http://localhost:5555/core/api/v1/orders/delivery',
             method: 'POST',
             params: {
                 address: $scope.address,
                 phone: $scope.phone
             }
         //$http.post('http://localhost:5555/core/api/v1/orders')
             .then(function (response) {
                 $scope.loadCart();
             })});
     }

     $scope.guestCreateOrder = function () {
         alert('Для оформления заказа необходимо войти в учетную запись');
     }*/
    };
    $scope.loadCart();
})