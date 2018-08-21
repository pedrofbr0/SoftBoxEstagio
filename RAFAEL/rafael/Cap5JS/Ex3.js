function negate (predicateFunc) {
    return function () {
        return !predicateFunc.apply(this, arguments);
    };
}

function every(array, test) {
  	//return !array.some(negate(test));
	
  	for(let element of array){
     	if(!test(element)){
          return false;
        }
    }
  
  	return true;
}



console.log(every([1, 3, 5], n => n < 10));
// → true
console.log(every([2, 4, 16], n => n < 10));
// → false
console.log(every([], n => n < 10));
// → true
