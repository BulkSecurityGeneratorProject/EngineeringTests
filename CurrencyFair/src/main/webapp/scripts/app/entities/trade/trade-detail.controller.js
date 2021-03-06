'use strict';

angular.module('currencyfairtestApp')
    .controller('TradeDetailController', function ($scope, $stateParams, Trade) {
        $scope.trade = {};
        $scope.load = function (id) {
            Trade.get({id: id}, function(result) {
              $scope.trade = result;
            });
        };
        $scope.load($stateParams.id);
    });
