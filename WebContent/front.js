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

function dbController($scope)
{
  $scope.currentPiano = localCache().get("currentP", "object");
  $scope.pianos = localCache().get("piano", "object");
  console.log($scope.pianos);
  $scope.test = "hey!";
  $scope.numPianos = $scope.pianos.length;

  $scope.openPage = function(selectedPiano)
  {
    console.log(selectedPiano.year);
    $scope.currentPiano = selectedPiano;
    // var result = $.grep($scope.pianos, function(e){ return e.byui_piano_id == selectedID; });
    window.open("pianoInfo.html","_self");
    console.log($scope.currentPiano.year);
    localCache().save("currentP", selectedPiano);
  };

  loadPiano = function()
  {
      // console.log($scope.currentPiano.year);
      var cp = localCache().get("currentP", "object");
      //console.log(cp);
      $scope.currentPiano = cp;
      console.log($scope.currentPiano);
       // $scope.currentPiano = localCache().get("currentP", "object");
      // console.log($scope.currentPiano.year);
  }
}

	
