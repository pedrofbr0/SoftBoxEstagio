
function arrayToList(array){

  var list = {
  };
 
  for (let i = array.length-1; i>=0; i--){
    list = {value: array[i], rest: list}
  }		
  
  return list;
}

function listToArray(list){
  let array = []
  
  for (let node = list; node.value!=undefined; node = node.rest) {
		array.push(node.value); 		
  }
  
  return array;
}

function prepend (element,list){
 	list = {value: element, rest: list};
  	return list;
}

function nth(list,n){
 
  var elemento = 0;
  var no = list;
  
  for (let i = 0; i < n; i++){
		no = no.rest;    
  }
 
  return no.value;
}

console.log(arrayToList([10, 20]));
// → {value: 10, rest: {value: 20, rest: null}}
console.log(listToArray(arrayToList([10, 20, 30])));
// → [10, 20, 30]
console.log(prepend(10, prepend(20, null)));
// → {value: 10, rest: {value: 20, rest: null}}
console.log(nth(arrayToList([10, 20, 30]), 1));
// → 20
