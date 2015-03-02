angular.module('currencyfairtestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('currencypairtracker', {
                parent: 'admin',
                url: '/currencypairtracker',
                data: {
                    roles: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/currencypairtracker/currencypairtracker.html',
                        controller: 'CurrencyPairTrackerController'
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
