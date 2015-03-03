angular.module('currencyfairtestApp')
    .controller('CurrencyPairTrackerController', function ($scope) {
        // This controller uses a Websocket connection to receive user activities in real-time.

        $scope.currencyPairs = []; //{pairName: {pairName:null, transactionVolume:null}};
        
        var stompClient = null;
        var socket = new SockJS('/websocket/currencypair');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            stompClient.subscribe('/topic/currencypair', function(activity){
                showActivity(JSON.parse(activity.body));
            });
        });
        
        var getCurrencyPair = function (pairName) {
        	$scope.shouldRun = true;
        	$scope.currencyPair = null;
        	angular.forEach($scope.currencyPairs, function(currencyPair) 
        	{
        		if($scope.shouldRun)
        		{
        			if(currencyPair.pairName == pairName)
        			{
        				$scope.shouldRun = false;
        				$scope.currencyPair = currencyPair;
        			} 
        			
        		}
            });
        	return $scope.currencyPair;
        };

        function showActivity(data) 
        {
        	var currencyPair = getCurrencyPair(data.pairName);
        	if(currencyPair == null)
        	{
        		data.count = 1;
        		$scope.currencyPairs.push(data);
        	}
        	else
        	{
        		currencyPair.transactionVolume += data.transactionVolume;
        		currencyPair.count += 1;
        	}
            $scope.$apply();
        };
    });
