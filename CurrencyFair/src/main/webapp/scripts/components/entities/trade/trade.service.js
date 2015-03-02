'use strict';

angular.module('currencyfairtestApp')
    .factory('Trade', function ($resource) {
        return $resource('api/trades/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.timePlaced = new Date(data.timePlaced);
                    return data;
                }
            }
        });
    });
