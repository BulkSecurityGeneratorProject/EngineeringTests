'use strict';

angular.module('currencyfairtestApp')
    .controller('TradeController', function ($scope, Trade) {
        $scope.trades = [];
        $scope.loadAll = function() {
            Trade.query(function(result) {
               $scope.trades = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Trade.save($scope.trade,
                function () {
                    $scope.loadAll();
                    $('#saveTradeModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.trade = Trade.get({id: id});
            $('#saveTradeModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.trade = Trade.get({id: id});
            $('#deleteTradeConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Trade.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteTradeConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.trade = {userId: null, currencyFrom: null, currencyTo: null, amountSell: null, amountBuy: null, rate: null, timePlaced: null, originatingCountry: null, id: null};
        };
    });
