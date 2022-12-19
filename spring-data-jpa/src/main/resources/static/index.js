angular.module('goods', []).controller('indexController', function ($scope, $http) {
    let contextPath = 'http://localhost:8888/jpa/api_1.0/products';
    $scope.pageNumber = 1;
    $scope.totalPages = 1;

    $scope.loadProducts = function (page) {
        if (page > 0 && page <= $scope.totalPages) {
            $http.get(contextPath + '/?page=' + page)
                .then(function (response) {
                    $scope.ProductsList = response.data.content;
                    $scope.pageNumber = response.data.number + 1;
                    $scope.totalPages = response.data.totalPages;
                    $scope.lastPosition = response.data.last && response.data.numberOfElements == 1;
                });
        };
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/' + productId)
            .then(function (response) {
                if ($scope.lastPosition) {
                    $scope.pageNumber--;
                };
                $scope.loadProducts($scope.pageNumber);
            });
    };

    $scope.changeCart = function (productId, delta) {
        $http({
            url: contextPath + '/change_cart',
            method: 'GET',
            params: {
                id: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts($scope.pageNumber);
        });
    };

    $scope.loadProducts(1);
})