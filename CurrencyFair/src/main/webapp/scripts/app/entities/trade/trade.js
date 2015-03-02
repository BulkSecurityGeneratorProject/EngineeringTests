'use strict';

angular.module('currencyfairtestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('trade', {
                parent: 'entity',
                url: '/trade',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/trade/trades.html',
                        controller: 'TradeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('trade');
                        return $translate.refresh();
                    }]
                }
            })
            .state('tradeDetail', {
                parent: 'entity',
                url: '/trade/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/trade/trade-detail.html',
                        controller: 'TradeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('trade');
                        return $translate.refresh();
                    }]
                }
            });
    });
