'use strict';

angular.module('currencyfairtestApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


