'use strict';

angular.module('currencyfairtestApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
