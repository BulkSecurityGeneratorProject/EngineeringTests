angular.module('currencyfairtestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('realtimetrade', {
                parent: 'admin',
                url: '/realtimetrade',
                data: {
                    roles: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/realtimetrade/realtimetrade.html',
                        controller: 'RealtimeTradeController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('tracker');
                        return $translate.refresh();
                    }]
                }
            });
    });
