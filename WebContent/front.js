//First we need to create the Angular module.
//The array is where we pass in the dependancies for the module.
//The dependancies are the ngRoute module. (That module allows routing to happen.)
var app = angular.module('dbPianistic', ['ngRoute']);
//If you refer to the app in other files after this one, you can simply say
// var app = angular.module('dbPianistic');
// which is the "getter" for the module whereas the one in this file is the "setter".

//Sets up the routing.
app.config(function ($routeProvider){
  $routeProvider.
    when('/', { //This line indicates the path you put in the url
      templateUrl: 'main.html', //The HTML file containing the html for it.
      controller: 'DBController' //The controller to use for it.
    }).
    when('/piano', {
      templateUrl: 'pianoInfo.html',
      controller: 'DBController'
    }).
    otherwise({
      redirectTo: '/' //This is where it will go automatically if anything else is entered (a not valid route.)
    });
});

//The "main" controller.
//The array below is the best practice for passing in information to the controller
//It is minification safe.  The strings describe the parameters so that when
//they are minified (turned into single letters) it won't cause issues.
app.controller('DBController', ['$scope', '$http', function ($scope, $http){
  $scope.test = "hey!";
}]);

/**
 * Generally all your controllers would be in their own script files.
 * The app.js file would be your "main" file with the initial 'app =' declaration
 * It would also have the app.config data in it.
 */

/* This is what you had before.
function dbController($scope)
{
  $scope.currentPiano;
  $scope.pianos = localCache().get("piano", "object");
  console.log($scope.pianos);
  $scope.test = "hey!";

  $scope.openPage = function(selectedPiano)
  {
    console.log(selectedPiano.year);
    $scope.currentPiano = selectedPiano;
    // var result = $.grep($scope.pianos, function(e){ return e.byui_piano_id == selectedID; });
    window.open("pianoInfo.html","_self");
    console.log($scope.currentPiano.year);
  };
}
*/


 var localCache = function () {
       //TODO: Add some more cross-browser stuff here.
       var cache = window.sessionStorage;

       /**
        * Stores the value into the cache.  Will convert objects to strings.
        * key - a string value
        * value - string or object.  If object, it is converted to string.
        */
       var save = function (key, value) {
           if (typeof key !== "string")
           {
               throw "Error: Key must be a string";
           }
           if (typeof value === 'object')
           {
               if (value instanceof Date)
               {
                   value = value.toString();
               }
               else
               {
                   value = JSON.stringify(value);
               }
           }
           cache.setItem(key, value);
       };

       /**
        * Retrieves a value from the cache.
        * key - string value
        * type - optional parameter.  If it equals "object" a conversion to
        *        object will occur. "date" will convert it to a JS Date object.
        */
       var get = function (key, type) {
           if (typeof key !== "string")
           {
               throw "Error: Key must be a string";
           }
           if ((typeof type !== "undefined") && (type === "object"))
           {
               return JSON.parse(cache.getItem(key));
           }
           if ((typeof type !== "undefined") && (type === "date"))
           {
               return new Date(cache.getItem(key));
           }
           return cache.getItem(key);
       };

       return {
           save:save,
           get:get
       };
   };

var getInfo = function()
{
	$.getJSON("http://localhost:8080/DBPianistic2/DBServlet", null, function(data, textStatus, xhr){
//		console.log(data);
		localCache().save("piano", data);
		localCache().get("piano", "object");
		console.log(localCache().get("piano", "object"));
	});
};




