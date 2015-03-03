angular.module('currencyfairtestApp')
    .controller('RealtimeTradeController', function ($scope) {
        // This controller uses a Websocket connection to receive user activities in real-time.

        $scope.trades = []; //{pairName: {pairName:null, transactionVolume:null}};
        
        var stompClient = null;
        var socket = new SockJS('/websocket/realtimetrade');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            stompClient.subscribe('/topic/realtimetrade', function(activity){
                showActivity(JSON.parse(activity.body));
            });
        });
        
        function showActivity(data) 
        {
        	$scope.trades.push(data);
        	console.log($scope.trades);
        	$scope.$apply();
        };
    });
